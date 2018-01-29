package actors.serverInterface;

import actors.Lobby;
import actors.serverInterface.json.TOJSON;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Personal;
import models.RT.Film;
import models.RT.Isotope;
import models.RT.RT_Camera;
import models.ServerLog;
import models.Team;
import models.dosimeter.*;
import models.enums.ISOTOPETYPE;
import models.enums.MODEL;
import models.enums.NAME;
import models.enums.TYPE;
import models.material.*;
import models.processing.Developer;
import models.processing.Fixer;
import play.libs.Json;

import java.io.IOException;


/**
 * Created by F.Arian on 06.11.17.
 */
public class Interface extends UntypedActor {
    private ObservableMail observableMail;
    private ActorRef out;
    private ServerLog log;
    private JsonFactory jsonFactory;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonParser parser;
    private JsonNode jsonNode;
    private String replayMessage;
    private Lobby lobby;

    public Interface(ActorRef out, ObservableMail observableMail) {
        this.lobby = new Lobby();
        this.setObservableMail(observableMail);
        this.setOut(out);
        this.setMapper(new ObjectMapper());
        this.setJsonFactory(getMapper().getFactory());
        this.setLog(new ServerLog());
        this.setReplayMessage("i'm new client ");
        this.getLog().info(this.getReplayMessage());
    }

    public static Props props(ActorRef out, ObservableMail observableMail) {
        return Props.create(Interface.class, out, observableMail);
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

    public ActorRef getOut() {
        return out;
    }

    public void setOut(ActorRef out) {
        this.out = out;
    }

    public JsonFactory getJsonFactory() {
        return jsonFactory;
    }

    public void setJsonFactory(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public JsonParser getParser() {
        return parser;
    }

    public void setParser(JsonParser parser) {
        this.parser = parser;
    }

    public JsonNode getJsonNode() {
        return jsonNode;
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
                this.out.tell(TOJSON.message("Start", "OK").toString() + "\n", self());
                this.output(TOJSON.message("Start", "OK"));
                break;
            case "PERSONAL":
                Isotope isotope = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
                Isotope isotope2 = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
                RT_Camera camera = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope);
                RT_Camera camera2 = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope2);
                Personal personal = new Personal("Far-had", "arian", "13.02.1983");
                Personal personal2 = new Personal("Farhad", "arian", "13.02.1983");
                Team team = new Team(personal, TYPE.RT, camera);
                Team team2 = new Team(personal2, TYPE.RT, camera2);
                //JsonNode personalJson = Json.toJson(camera.toString());
                //this.out.tell(personalJson.toString().replace("\\n", "\n"), self());
                break;
            case "NEW_USER_ASK":
                String email = json.get("NEW_USER_ASK").textValue();
                boolean check = false;
                if (lobby != null) {
                    check = lobby.isNewPersonal(email);
                }

                out.tell(TOJSON.booleanAnswer("NEW_USER_ANSWER", check).toString() + "\n", self());
                break;
            case "id":
                System.out.println("counter " + jsonKey);
                out.tell(TOJSON.message("Report", "I have your report message").toString() + "\n", self());
                break;
            case "REPORT":
                System.out.println("REPORT" + jsonKey);
                break;
            case "Safety Warning":
                //TODO send message to HPS
                out.tell(TOJSON.message("Warning answer", "I have your message").toString() + "\n", sender());
                break;
            case "Create FILMBADGE":
                FilmBadge filmBadge = Json.fromJson(json, FilmBadge.class);
                lobby.addFilmBadgeInLobby(filmBadge);

                /**
                 * send object to Client with key
                 */
                out.tell(TOJSON.sendObjectWithKey("MyFilmBadge", filmBadge).toString(), self());
                break;
            case "Create TLD":
                TLD tld = Json.fromJson(json, TLD.class);
                lobby.addTldInLobby(tld);
                break;
            case "Create DOSIMETER":
                PocketDosimeter dosimeter = Json.fromJson(json, PocketDosimeter.class);
                //TODO
                break;
            case "Create GEIGER":
                GeigerAlarm geigerAlarm = Json.fromJson(json, GeigerAlarm.class);
                //TODO
                break;
            case "Create RADIOMETER":
                Radiometer radiometer = Json.fromJson(json, Radiometer.class);
                //TODO
                break;
            case "Create LEAD ARON":
                LeadAron leadAron = Json.fromJson(json, LeadAron.class);
                //TODO
                break;
            case "Create RADIATION SINGS":
                RadiationSigns radiationSigns = Json.fromJson(json, RadiationSigns.class);
                //TODO
                break;
            case "Create HANDLING TONGS":
                HandlingTongs handlingTongs = Json.fromJson(json, HandlingTongs.class);
                //TODO
                break;
            case "Create EMERGENCY STORAGE CONTAINER":
                EmergencyStorageContainer container = Json.fromJson(json, EmergencyStorageContainer.class);
                //TODO
                break;
            case "Create RT CAMERA":
                RT_Camera rt_camera = Json.fromJson(json, RT_Camera.class);
                //TODO
                break;
            case "Create RT FILM":
                Film film = Json.fromJson(json, Film.class);
                //TODO
                break;
            case "Create IQI":
                IQI iqi = Json.fromJson(json, IQI.class);
                //TODO
                break;
            case "Create FILM DEVELOPER":
                Developer developer = Json.fromJson(json, Developer.class);
                //TODO
                break;
            case "Create FILM FIXER":
                Fixer fixer = Json.fromJson(json, Fixer.class);
                //TODO
                break;

        }

    }

    /**
     * TODO
     *
     * @param txt
     */
    private void output(final Object txt) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                out.tell(txt.toString() + "\n\n", self());

            }
        });

    }

}
