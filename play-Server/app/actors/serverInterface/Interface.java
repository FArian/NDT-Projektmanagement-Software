package actors.serverInterface;

import actors.serverInterface.json.TOJSON;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Interface extends UntypedActor {
    private ObservableMail observableMail;
    private ActorRef out;
    private ServerLog log;
    private JsonFactory jsonFactory;
    private ObjectMapper mapper;
    private JsonParser parser;
    private JsonNode jsonNode;
    private String replayMessage;

    public Interface(ActorRef out, ObservableMail observableMail) {
        this.setObservableMail(observableMail);
        this.setOut(out);
        this.setMapper(new ObjectMapper());
        this.setJsonFactory(getMapper().getFactory());
        this.setLog(new ServerLog());
        this.setReplayMessage("i'm new client ");
        this.getLog().info(this.getReplayMessage());
    }

    public String getReplayMessage() {
        return replayMessage;
    }

    public void setReplayMessage(String replayMessage) {
        this.replayMessage = replayMessage;
    }

    public ServerLog getLog() {
        return log;
    }

    public void setLog(ServerLog log) {
        this.log = log;
    }

    public ObservableMail getObservableMail() {
        return observableMail;
    }

    public void setObservableMail(ObservableMail observableMail) {
        this.observableMail = observableMail;
    }


    public void setOut(ActorRef out) {
        this.out = out;
    }

    public ActorRef getOut() {
        return out;
    }

    public JsonFactory getJsonFactory() {
        return jsonFactory;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public JsonParser getParser() {
        return parser;
    }

    public JsonNode getJsonNode() {
        return jsonNode;
    }

    public void setJsonFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void setParser(JsonParser parser) {
        this.parser = parser;
    }

    public void setJsonNode(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    @Override
    public void onReceive(Object message) {

        if (message instanceof String) {
            this.setJsonFactory(this.getMapper().getFactory());
            JsonFactory factory = mapper.getFactory();
            try {
                System.out.println(" Receive Message in OnReceive Server : " + message);
                parser = factory.createParser((String) message);
                this.setParser(jsonFactory.createParser((String) message));
                this.setJsonNode(this.getMapper().readTree(this.getParser()));
                if (this.getJsonNode() != null) {
                    this.messageActor(this.getJsonNode());
                } else {
                    this.getLog().info("You have null jsonNode");
                    System.out.println("You have null jsonNode");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            /**
             * if incoming jsonNode not json
             */
            System.out.println("Type of your jsonNode is not valid or not Json");

        }


    }


    public static Props props(ActorRef out, ObservableMail observableMail) {
        return Props.create(Interface.class, out, observableMail);
    }

    /**
     * jsonNode Actors management for messages from Client and answer back to client
     *
     * @param json
     */
    private synchronized void messageActor(JsonNode json) {
        String jsonKey = json.fields().next().getKey();
        //------------------Message Type ------------------------------
        switch (jsonKey) {
            case "Hello":
                this.out.tell(TOJSON.replayMessage("Hey Client , im Server ").toString() + "\n", self());
                this.output(TOJSON.replayMessage("Hey Client , im Server"));

                break;
            case "Start":
                this.out.tell(TOJSON.message("Start","OK").toString() + "\n", self());
                this.output(TOJSON.message("Start","OK"));
                break;
            case "Safety Activity":
                this.out.tell(TOJSON.message("Hi","Safety").toString() + "\n", self());
        }

    }

    /**
     * TODO
     * @param txt
     */
    private void output(final Object txt){


        new Thread(new Runnable() {
            @Override
            public void run() {
                out.tell(txt.toString() + "\n\n",self());

            }
        });

    }

}
