package actors.serverInterface;

import actors.Lobby;
import actors.serverInterface.json.TOJSON;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Personal;
import models.RT.Film;
import models.RT.Isotope;
import models.RT.RT_Camera;
import models.ServerLog;
import models.dosimeter.*;
import models.material.*;
import models.processing.Developer;
import models.processing.Fixer;
import play.libs.Json;

import java.io.IOException;
import java.util.List;


/**
 * Created by F.Arian on 06.11.17.
 */
public class Interface extends UntypedActor {
    private ActorRef out;
    private ServerLog log;
    private JsonFactory jsonFactory;
    private ObjectMapper mapper;
    private JsonParser parser;
    private JsonNode jsonNode;
    private String replayMessage;
    private Lobby lobby;
    private String email;
    private Personal personal;

    public Interface(ActorRef out, Lobby lobby) {
        this.lobby = lobby;
        this.setOut(out);
        this.mapper = new ObjectMapper();
        this.setJsonFactory(getMapper().getFactory());
        this.setLog(new ServerLog());
        this.setReplayMessage("i'm new client ");
        this.getLog().info(this.getReplayMessage());
    }

    /**
     * @param out
     * @param lobby
     * @return
     */
    public static Props props(ActorRef out, Lobby lobby) {
        return Props.create((Class<?>) Interface.class, out, lobby);
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

    public Personal getPersonal() {
        return personal;
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
    private synchronized void messageActor(JsonNode json) throws JsonProcessingException {

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
                this.personal = Json.fromJson(json.get("PERSONAL"), Personal.class);
                lobby.addPersonalInLobby(this.personal);

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
                FilmBadge filmBadge = Json.fromJson(json.get("Create FILMBADGE"), FilmBadge.class);
                lobby.addFilmBadgeInLobby(filmBadge);
                /**
                 * send object to Client with key
                 */
                out.tell(TOJSON.sendObjectWithKey("MyFilmBadge", filmBadge).toString(), self());
                break;
            case "Create TLD":
                TLD tld = Json.fromJson(json.get("Create TLD"), TLD.class);
                lobby.addTldInLobby(tld);

                break;
            case "Create DOSIMETER":
                PocketDosimeter dosimeter = Json.fromJson(json.get("Create DOSIMETER"), PocketDosimeter.class);
                lobby.addDosimeterInLobby(dosimeter);
                System.out.println(lobby.getDosimetersInLobby());
                break;
            case "Create GEIGER":
                GeigerAlarm geigerAlarm = Json.fromJson(json.get("Create GEIGER"), GeigerAlarm.class);
                lobby.addGeigerInLobby(geigerAlarm);
                System.out.println(lobby.getGeigerAlarmsInLobby());
                break;
            case "Create RADIOMETER":
                Radiometer radiometer = Json.fromJson(json.get("Create RADIOMETER"), Radiometer.class);
                lobby.addRadiometerInLobby(radiometer);

                System.out.println(lobby.getRadiometersInLobby());
                break;
            case "Create LEAD ARON":
                LeadAron leadAron = Json.fromJson(json.get("Create LEAD ARON"), LeadAron.class);
                lobby.addLeadAronInLobby(leadAron);

                System.out.println(lobby.getLeadAronsInLobby());
                break;
            case "Create RADIATION SINGS":
                RadiationSigns radiationSigns = Json.fromJson(json.get("Create RADIATION SINGS"), RadiationSigns.class);
                lobby.addRadiationSignsInLobby(radiationSigns);
                System.out.println(lobby.getRadiationSignsInLobby());
                break;
            case "Create HANDLING TONGS":
                HandlingTongs handlingTongs = Json.fromJson(json.get("Create HANDLING TONGS"), HandlingTongs.class);
                lobby.addHandlingTongsInLobby(handlingTongs);
                System.out.println(lobby.getHandlingTongsInLobby());
                break;
            case "Create EMERGENCY STORAGE CONTAINER":
                EmergencyStorageContainer container = Json.fromJson(json.get("Create EMERGENCY STORAGE CONTAINER"), EmergencyStorageContainer.class);
                lobby.addEmergencyStorageContainerInLobby(container);
                System.out.println(lobby.getContainersInLobby());
                break;
            case "Create RT CAMERA":
                Isotope isotope = Json.fromJson(json.get("Create RT CAMERA").get("isotope"), Isotope.class);
                RT_Camera rt_camera = Json.fromJson(json.get("Create RT CAMERA"), RT_Camera.class);
                rt_camera.setIsotope(isotope);
                lobby.addRT_CameraInLobby(rt_camera);
                break;
            case "Create RT FILM":
                Film film = Json.fromJson(json.get("Create RT FILM"), Film.class);
                lobby.addFilmInLobby(film);
                System.out.println(lobby.getFilmsInLobby());
                break;
            case "Create IQI":
                IQI iqi = Json.fromJson(json.get("Create IQI"), IQI.class);
                lobby.addIqiInLobby(iqi);
                break;
            case "Create FILM DEVELOPER":
                Developer developer = Json.fromJson(json.get("Create FILM DEVELOPER"), Developer.class);
                lobby.addDeveloperInLobby(developer);
                break;
            case "Create FILM FIXER":
                Fixer fixer = Json.fromJson(json.get("Create FILM FIXER"), Fixer.class);
                lobby.addFixerInLobby(fixer);
                break;
            case "Resource":
                String value = json.get("Resource").asText();
                if (value.equals("Personal") && lobby.getPersonalToReady().size() != 0) {
                }
                if (value.equals("Material") && lobby.getDevelopersToReady().size() != 0) {
                    mapper.enable(SerializationFeature.INDENT_OUTPUT);
                    String js = String.valueOf(Json.toJson(lobby.getDevelopersToReady()));
                    out.tell(js, self());
                    System.out.println("JS " + js);
                }
                break;
            // answer if user is new ?
            case "USER_EMAIL":
                this.email = json.get("USER_EMAIL").get("Message").asText();
                if (lobby.getPersonalInLobby().isEmpty()) {
                    boolean val = true;
                    String stringVal = Boolean.toString(val);
                    out.tell(TOJSON.message("USER_IS_NEW", stringVal).toString(), self());

                } else {
                    for (int i = 0; i < lobby.getPersonalInLobby().size(); i++) {
                        if (email.equals(lobby.getPersonalInLobby().get(i).getEmail())) {
                            personal = lobby.getPersonalInLobby().get(i);
                            out.tell(TOJSON.sendObjectWithKey("USER_IN_LOBBY", personal).toString(), self());
                        }

                    }

                }
                break;

            case "UPDATEBIRTHDAY":
                personal = Json.fromJson(json.get("UPDATEBIRTHDAY"), Personal.class);
                for (Personal p : lobby.getPersonalInLobby()) {
                    if (personal.getEmail().equals(p.getEmail()) && !personal.equals(null)) {
                        p.setBirthday(personal.getBirthday());
                    }
                }
                break;
            case "PERSONALSLIST":
                List<Personal> personalList = lobby.getPersonalInLobby();
                String objectToJson = mapper.writeValueAsString(personalList);


                for (int i = 0; i < lobby.getPersonalInLobby().size(); i++) {
                    System.out.println("Object in Lobby " + lobby.getPersonalInLobby().get(i));
                    log.info("Object in Lobby " + lobby.getPersonalInLobby().get(i));
                }
                out.tell(TOJSON.sendObjectWithKey("PERSONAL_SEND", objectToJson).toString(), self());

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
