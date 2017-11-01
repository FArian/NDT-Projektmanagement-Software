package actors;

/**
 * Created by F.Arian on 14.06.17.
 */

import akka.actor.*;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.*;
import java.util.*;

/**
 * Class that handles incoming messages from a client. Represents one client,
 * that represents a player in the running game. Runs in an own Thread. Provides
 * methods that handle actions of the player in game. Uses "UTF-8".
 */

public class GameActor extends UntypedActor {
    /**
     * the protocol version that is supported
     */
    private static final String PROTOCOL = "1.0";

    /**
     * the current status of the client. Equals the status of the player ingame
     */
    private String status;
    /**
     * the id that the server has given to the client/player
     */
    private static int id;
    private static String playerColor = "";
    private ActorRef out;
    /**
     * the player that the client represents ingame
     */
    private Player player;
    /**
     * the messageWriter for this game session
     */
    private MessageWriter messageWriter;
    private static CardLogic cardLogic;
    /**
     * logger
     */
    private ServerLog log;
    /**
     * the version of the protocol that the server supports
     */
    private String protocol;
    /**
     * contains methods for the serverLogic
     */
    private static ServerLogic serverLogic;
    private static List<DevelopmentCard> newDevCards;
    private static GameLogic gameLogic;
    /**
     * tells if a trade is active
     **/
    private boolean tradeActive = false;
    /**
     * contains methods for the construction logic
     */
    private ConstructorLogic constructorLogic;
    /**
     * the gameObservable for the game that the client is put
     */
    private GameObservable gameObservable;
    /**
     * the game in which the client is active
     */
    private Game game;

    /**
     * shows if the client has already played a development card this round
     */
    private boolean devCardPlayed;
    /**
     * result of the last time the client rolled the dices - not for the result
     * of the other players dices!
     */
    private int owndice;
    /**
     * boolean value if the clients version of the protocol matches the one from
     * the server
     */
    private boolean protocolOkay;
    /**
     * boolean value if the client is a AI
     */
    private boolean isKI;


    public GameActor(ActorRef out, GameObservable gameObservable) {
        this.gameObservable = gameObservable;
        this.out = out;
        this.game = gameObservable.getGame();
        this.constructorLogic = new ConstructorLogic(game);
        this.messageWriter = gameObservable.getmWriter();
        this.serverLogic = new ServerLogic(game);
        this.gameLogic = new GameLogic(game);
        this.cardLogic = new CardLogic(game);
        this.status = "Spiel starten";
        this.setPlayer();
        this.player.setId(0);
        this.protocolOkay = false;
        this.isKI = false;
        this.owndice = 0;
        this.devCardPlayed = false;
        this.newDevCards = new ArrayList<DevelopmentCard>();
        this.log = new ServerLog();
        this.log.info("Ich bin ein neuer Client als GameActor !");
        player.setStatus("Spiel starten");

    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public void setId(int id) {
        this.id = id;
    }


    /**
     * Creates and sets a new (!) Player that the client represents in game.
     * Should only be called in the constructor.
     */
    private void setPlayer() {
        this.player = new Player();
        ArrayList<Player> players = gameObservable.getPlayers();
        players.add(player);
    }


    public static Props props(ActorRef out, GameObservable gameObservable) {
        return Props.create(GameActor.class, out, gameObservable);
    }

    public void onReceive(Object message) {
        if (message instanceof String) {
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getFactory();
            JsonParser parser = null;
            try {

                System.out.println(" Receive Message in OnReceive Server : " + message);
                parser = factory.createParser((String) message);
                JsonNode receiveMessage = mapper.readTree(parser);
                this.msgActor(receiveMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * msgActor is manegment for Messages from Client and answer back to Client
     *
     * @param json
     */

    private synchronized void msgActor(JsonNode json) {

        String jsonKey = json.fields().next().getKey();
        //------------------Message Type ------------------------------
        switch (jsonKey) {

            case "Hallo":
                String hello = json.path("Hallo").get("Version").textValue();
                if (json.path("Hallo").has("Version")) {

                    if (json.path("Hallo").has("(KI)")) {
                        this.isKI = true;
                    } else {
                        this.protocolOkay = true;
                        this.gameObservable.addGameActor(this);
                        this.setId(this.gameObservable.getAndIncreaseIDCounter());
                        this.player.setId(this.id);
                        this.gameObservable.checkAndSetGameStart();
                        out.tell(TOJSON.sendId(this.id).toString() + "\n", self());
                        log.info("Info: Send to Client " + TOJSON.sendId(this.getId()).toString());
                    }
                } else {
                    out.tell(TOJSON.wrongProtocol().toString() + "\n", self());
                    //TODO Socket close
                }
                break;
            case "Serverantwort":
                out.tell(TOJSON.okay().toString() + "\n", self());
                break;
            case "Spiel starten":
                if (player.getColor() != null) {
                    synchronized (gameObservable) {
                        if (gameObservable.isAvailable(player.getColor())) {
                            gameObservable.setColorUsed(player.getColor());
                            setStatus("Wartet auf Spielbeginn");
                            out.tell(TOJSON.okay().toString() + "\n", self());
                            gameObservable.checkAndSetGameStart();
                        } else {
                            log.info(this.getClass() + "Anmeldung mit bereits vergebener Farbe");
                            out.tell(TOJSON.colorError().toString() + "\n", self());
                        }
                    }
                } else {
                    out.tell(TOJSON.colorError2().toString() + "\n", self());
                }
                break;
            case "Würfeln":
                if (status.equals("Würfeln")) {
                    owndice = handleRollDice();
                    gameObservable.setDice(owndice);
                    if (owndice != 7) {
                        gameObservable.handleEarning(owndice);
                        setStatus("Handeln oder Bauen");
                    } else {
                        handleSeven();
                    }

                }
                break;
            case "Zug beenden":
                if (status.equals("Handeln oder Bauen")) {
                    setStatus("Warten");
                    devCardPlayed = false;
                    this.newDevCards.clear();
                    gameObservable.nextOne();
                } else {
                    out.tell(TOJSON.jsonError("Du bist nicht am Zug.").toString() + "\n", self());
                }
                break;
            case "Chatnachricht senden":
                String message = json.path("Chatnachricht senden").get("Nachricht").textValue();
                if (message.equals("") || message.equals("\n")) {
                    out.tell(TOJSON.jsonError("Leere Nachrichten werden nicht gesendet.").toString() + "\n", self());
                } else {
                    messageWriter.sendChat(this.id, message);
                    this.handleCheats(message);
                }
                break;
            case "Bauen":
                if (status.equals("Dorf bauen") || status.equals("Straße bauen") || status.equals("Handeln oder Bauen")) {
                    JsonNode building = json.get("Bauen");
                    if (building.has("Typ") && building.has("Ort")) {
                        this.handleBuildRequest(building);
                    } else {
                        out.tell(TOJSON.jsonError("Unzulässige Aktion").toString() + "\n", self());
                    }

                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt nicht bauen").toString() + "\n", self());
                }
                break;
            case "Räuber versetzen":
                if (status.equals("Räuber versetzen")) {
                    JsonNode moveRobber = json.get("Räuber versetzen");
                    handleMoveRobber(moveRobber);
                } else {
                    out.tell(TOJSON.jsonError(
                            "Du darfst jetzt nicht den Räuber versetzen").toString() + "\n", self());
                }
                break;
            case "Karten abgeben":
                if (status.equals("Karten wegen Räuber abgeben")) {
                    JsonNode givenCards = json.get("Karten abgeben");
                    handleGivenCards(givenCards);
                    if (gameObservable.getClients().get(gameObservable.getWhosTurn()).getId() == this.id && owndice == 7) {
                        while (!gameObservable.isCardsForSevenFinished()) {
                            try {
                                System.out.println(this.getClass() + " Thread von GameObservable: " + player.getColor()
                                        + " wartet darauf, dass andere Spieler Karten abgeben wegen Räuber. ");
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        setStatus("Räuber versetzen");

                    }
                } else {
                    out.tell(TOJSON.jsonError("Du musst keine Karten abgeben!").toString() + "\n", self());
                }
                break;
            case "Entwicklungskarte kaufen":
                if (status.equals("Handeln oder Bauen")) {
                    handleBuyDevCard();
                } else {
                    out.tell(TOJSON.jsonError("Du kannst gerade keine Entwicklungskarte kaufen").toString() + "\n", self());
                }
                break;
            case "Ritter ausspielen":
                if (status.equals("Handeln oder Bauen") || status.equals("Würfeln")) {
                    if (devCardPlayed) {
                        out.tell(TOJSON.jsonError("Du hast in dieser Runde bereits eine Entwicklungskarte ausgespielt")
                                .toString() + "\n", self());
                    } else {
                        if (cardLogic.isCardOkay(this.player, DevelopmentCard.Ritter, this.newDevCards)) {
                            if (json.get("Ritter ausspielen").has("Ort")) {
                                handlePlayKnight(json.get("Ritter ausspielen"));
                            } else {
                                out.tell(TOJSON.jsonError("Kein valides Json!").toString() + "\n", self());
                            }
                        } else {
                            out.tell(TOJSON
                                    .jsonError(
                                            "Du hast keine Ritterkarte." +
                                                    "Karten die du erst diese Runde gekauft hast," +
                                                    "können erst nächste Runde ausgespielt werden!")
                                    .toString() + "\n", self());
                        }

                    }
                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt keine Entwicklungskarte ausspielen").toString() + "\n", self());
                }

                break;
            case "Straßenbaukarte ausspielen":
                if (status.equals("Handeln oder Bauen") || status.equals("Würfeln")) {
                    if (devCardPlayed) {
                        out.tell(TOJSON.jsonError("Du hast in dieser Runde bereits eine Entwicklungskarte ausgespielt")
                                .toString() + "\n", self());
                    } else {
                        if (cardLogic.isCardOkay(this.player, DevelopmentCard.Straßenbau, this.newDevCards)) {
                            handleRoadBuildingCard(json.get("Straßenbaukarte ausspielen"));
                        } else {
                            out.tell(TOJSON
                                    .jsonError(
                                            "Du hast keine Straßenbaukarte. Karten die du erst diese Runde gekauft hast, können erst nächste Runde ausgespielt werden!")
                                    .toString() + "\n", self());
                        }

                    }
                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt keine Entwicklungskarte ausspielen").toString() + "\n", self());
                }

                break;
            case "Monopol":
                if (status.equals("Handeln oder Bauen") || status.equals("Würfeln")) {
                    if (devCardPlayed) {
                        out.tell(TOJSON.jsonError("Du hast in dieser Runde bereits eine Entwicklungskarte ausgespielt")
                                .toString() + "\n", self());
                    } else {
                        if (cardLogic.isCardOkay(this.player, DevelopmentCard.Monopol, this.newDevCards)) {
                            if (json.get("Monopol").has("Rohstoff")) {
                                handleMonopolyCard(json.get("Monopol"));
                            } else {
                                out.tell(TOJSON.jsonError("Kein valides JSONObject!").toString() + "\n", self());
                            }
                        } else {
                            out.tell(TOJSON
                                    .jsonError(
                                            "Du hast keine Monopolkarte. Karten die du erst diese Runde gekauft hast, können erst nächste Runde ausgespielt werden!")
                                    .toString() + "\n", self());
                        }
                    }
                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt keine Entwicklungskarte ausspielen").toString() + "\n", self());
                }
                break;
            case "Erfindung":
                if (status.equals("Handeln oder Bauen") || status.equals("Würfeln")) {
                    if (devCardPlayed) {
                        out.tell(TOJSON.jsonError("Du hast in dieser Runde bereits eine Entwicklungskarte ausgespielt") + "\n", self());
                    } else {
                        if (cardLogic.isCardOkay(this.player, DevelopmentCard.Erfindung, this.newDevCards)) {
                            if (json.get("Erfindung").has("Rohstoffe")) {
                                int[] resources = FromJson.resources(json.get("Erfindung").get("Rohstoffe"));
                                handleInventionCard(resources);
                            }

                        }
                    }
                }
                break;
            case "Seehandel":
                if (status.equals("Handeln oder Bauen")) {
                    int[] get = FromJson.resources(json.get("Seehandel").get("Nachfrage"));
                    int[] give = FromJson.resources(json.get("Seehandel").get("Angebot"));
                    if (validResources(give)) {
                        gameObservable.seeTrade(player, give, get);
                    } else {
                        out.tell(TOJSON.jsonError("Du hast zu wenig Rohstoffe").toString() + "\n", self());
                    }
                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt nicht handeln").toString() + "\n", self());
                }
                break;
            case "Handel anbieten":
                if (status.equals("Handeln oder Bauen")) {
                    int[] get = FromJson.resources(json.get("Handel anbieten").get("Nachfrage"));
                    int[] give = FromJson.resources(json.get("Handel anbieten").get("Angebot"));
                    if (validResources(give)) {
                        gameObservable.setTrade(player, give, get);
                        game.getTrade().resetAccepter();
                    } else {
                        out.tell(TOJSON.jsonError("Du hast zu wenig Rohstoffe").toString() + "\n", self());
                    }
                } else {
                    out.tell(TOJSON.jsonError("Du kannst jetzt nicht handeln").toString() + "\n", self());
                }
                break;
            case "Handel annehmen":
                if (tradeActive) {
                    if (json.get("Handel annehmen").get("Annehmen").booleanValue()) {
                        if (validResources(gameObservable.getGame().getTrade().getGet())) {
                            game.getTrade().setAccepter(player.getId());
                            messageWriter.TradeAccept(player, true, json.get("Handel annehmen").get("Handel id").intValue());
                        } else {
                            out.tell(TOJSON.jsonError("Du hast zu wenig Rohstoffe").toString() + "\n", self());
                        }

                    } else {
                        tradeActive = false;
                        messageWriter.TradeAccept(player, false, json.get("Handel annehmen").get("Handel id").intValue());
                    }
                } else {
                    out.tell(TOJSON.jsonError("Kein Handel aktiv").toString() + "\n", self());
                }
                break;
            case "Handel abschließen":
                if (tradeActive) {
                    int help = json.get("Handel abschließen").get("Mitspieler").intValue();
                    if (game.getTrade().isInAccepter(help)) {
                        gameObservable.doTrade(player, help);
                        messageWriter.doTrade(player, help);
                    } else {
                        out.tell(TOJSON.jsonError("Dieser Spieler hat nicht angenommen").toString() + "\n", self());
                    }
                } else {
                    out.tell(TOJSON.jsonError("Kein Handel aktiv").toString() + "\n", self());
                }
                break;
            case "Handel abbrechen":
                if (tradeActive) {
                    if (!status.equals("Handeln oder Bauen")) {
                        game.getTrade().removeAccepter(id);
                        messageWriter.TradeBreak(player, game.getTrade().getTradeIndex());

                    } else {
                        messageWriter.TradeBreak(player, game.getTrade().getTradeIndex());
                    }

                } else {
                    out.tell(TOJSON.jsonError("Kein Handel aktiv").toString() + "\n", self());
                }
                break;
            case "Spieler":
                if (status.equals("Spiel starten")) {
                    JsonNode playerJson = json.get("Spieler");
                    if (playerJson.has("Name") && playerJson.has("Farbe")) {
                        String color = playerJson.get("Farbe").textValue();
                        String name = playerJson.get("Name").textValue();
                        log.info("Name :" + name + "und Farbe :" + color + " saved !");
                        player.setColor(color);
                        player.setName(name);
                        out.tell(TOJSON.okay().toString() + "\n", self());
                        messageWriter.sendStatusUpdate(this.player);

                    }
                } else {
                    out.tell(TOJSON.jsonError("Kein valides Spielerobjekt").toString() + "\n", self());
                }
                break;
            default:
                out.tell(TOJSON.jsonError("Server unterstützt diese Nachricht-Key nicht ! " +
                        "bitte korrigieren Sie Ihre Nachricht key  ").toString() + "\n", self());
                break;

        }
    }

    
    /**
     * checks if the player has euqal or more resources than int[] res
     *
     * @param res
     * @return boolean
     */
    private boolean validResources(int[] res) {
        int[] check = player.getResAmountArray();
        for (int i = 0; i < 5; i++) {
            if (res[i] > check[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that handles the development card invention. Checks if there are
     * enough resources left in the game. May changes values of the players or
     * the game! Writes messages to the clients!
     *
     * @param resources
     */
    private void handleInventionCard(int[] resources) {
        int[] resSend = new int[5];
        resSend[0] = 0; //Grain
        resSend[1] = 0; //Wool
        resSend[2] = 0; //Ore
        resSend[3] = 0; //Lumber
        resSend[4] = 0; //Brick
        for (int i = 0; i < resSend.length; i++) {
            while (resources[i] > 0) {
                switch (i) {
                    case 0:
                        if (game.hasResLeft(Resource.Getreide)) {
                            player.addResource(Resource.Getreide);
                            game.removeResources(Resource.Getreide);
                            resSend[i]++;
                        } else {
                            out.tell(TOJSON.jsonError("Game geprüft ,leider gibt es kein Getreide mehr!").toString() + "\n", self());
                        }
                        break;
                    case 1:
                        if (game.hasResLeft(Resource.Wolle)) {
                            player.addResource(Resource.Wolle);
                            game.removeResources(Resource.Wolle);
                            resSend[i]++;
                        } else {
                            out.tell(TOJSON.jsonError("Game geprüft ,leider gibt es keine Wolle mehr!").toString() + "\n", self());
                        }
                        break;
                    case 2:
                        if (game.hasResLeft(Resource.Erz)) {
                            player.addResource(Resource.Erz);
                            game.removeResources(Resource.Erz);
                            resSend[i]++;
                        } else {
                            out.tell(TOJSON.jsonError("Game geprüft ,leider gibt es kein Erz mehr!").toString() + "\n", self());
                        }
                        break;
                    case 3:
                        if (game.hasResLeft(Resource.Holz)) {
                            player.addResource(Resource.Holz);
                            game.removeResources(Resource.Holz);
                            resSend[i]++;
                        } else {
                            out.tell(TOJSON.jsonError("Game geprüft ,leider gibt es kein Holz mehr!").toString() + "\n", self());
                        }
                        break;
                    case 4:
                        if (game.hasResLeft(Resource.Lehm)) {
                            player.addResource(Resource.Lehm);
                            game.removeResources(Resource.Lehm);
                            resSend[i]++;
                        } else {
                            out.tell(TOJSON.jsonError("Game geprüft ,leider gibt es kein Lehm mehr!").toString() + "\n", self());
                        }
                        break;
                }
                resources[i]--;
            }
        }
        devCardPlayed = true;
        player.removeDevCards(DevelopmentCard.Erfindung);
        messageWriter.sendInventionCard(player, resSend);

    }

    private void handleMonopolyCard(JsonNode monopol) {
        Resource res = null;
        switch (monopol.get("Rohstoff").textValue()) {

            case "Holz":
                res = Resource.Holz;
                break;
            case "Getreide":
                res = Resource.Getreide;
                break;
            case "Wolle":
                res = Resource.Wolle;
                break;
            case "Lehm":
                res = Resource.Lehm;
                break;
            case "Erz":
                res = Resource.Erz;
                break;
        }
        if (res != null) {
            String type = monopol.get("Rohstoff").textValue();
            devCardPlayed = true;
            player.removeDevCards(DevelopmentCard.Monopol);
            messageWriter.sendMonopolyCard(player, type);
            gameObservable.handleMonopolyRes(player, res, type);
        }
    }

    private void handleRoadBuildingCard(JsonNode jsonNode) {
        if (player.getPlayerAvailableStreet() < 1) {
            out.tell(TOJSON.jsonError("Du hast keine Straßen mehr zur Verfügung").toString() + "\n", self());
        } else {
            List<Position[]> streets = FromJson.getRoadBuilding(jsonNode);
            if (streets.size() > 1 && player.getPlayerAvailableStreet() == 1) {
                out.tell(TOJSON.jsonError("Leider hast du nur noch eine Straße zur Verfügung!").toString() + "\n", self());
            } else {
                for (int i = 0; i < streets.size(); i++) {
                    Position[] street = streets.get(i);
                    Edge edge = gameLogic.locationToEdge(street);
                    if (gameObservable.getConstructionLogic().isStreetLocationPossible(this.player, edge)) {
                        gameObservable.setStreet(this.player, street, false);
                        devCardPlayed = true;
                        player.removeDevCards(DevelopmentCard.Ritter);
                        messageWriter.sendRoadBuilding(this.player, streets);

                    } else {
                        out.tell(
                                TOJSON.jsonError("Du kannst an dieser Position keine Straße bauen!").toString() + "\n", self());
                    }
                }
            }
        }
    }

    /**
     * handles the case when the client wants to play a knight card. May changes
     * values of the players or the game! Writes messages to the clients!
     *
     * @param playKnight
     */
    private void handlePlayKnight(JsonNode playKnight) {
        Position pos = setRobber(playKnight);
        if (pos != null) {
            int target = handleTarget(playKnight, game.getGameboard().getLand(pos));
            player.removeDevCards(DevelopmentCard.Ritter);
            player.addKnights();
            devCardPlayed = true;
            messageWriter.sendKnightPlayed(player, pos, target);
            gameObservable.checkForLargestArmy(player);
        }
    }

    /**
     * Method that handles the case when the client wants to buy a development card.
     * May changes values of the players or the game! Writes messages to
     * the clients!
     */
    private void handleBuyDevCard() {
        if (gameObservable.getCardLogic().developmentCardPossible(id)) {
            DevelopmentCard dev = game.drawCard();
            log.info("Spieler: " + id + " hat folgende Entwicklungskarte gekauft: " + dev);
            if (dev != null) {
                player.addDevCards(dev);
                System.out.println("DER SPIELER HAT FOLGENDE ENTWICKLUNGSKARTEN: ");
                for (DevelopmentCard d : player.getDevelopmentCards()) {
                    System.out.println(d);
                }
                //--------
                this.newDevCards.add(dev);
                player.removeResource(Resource.Getreide);
                player.removeResource(Resource.Wolle);
                player.removeResource(Resource.Erz);
                //--------
                game.addResources(Resource.Getreide);
                game.addResources(Resource.Wolle);
                game.addResources(Resource.Erz);
                messageWriter.sendCosts(this.player, "Entwicklungskarte", null, true);

            } else {
                out.tell(TOJSON.jsonError("Es gibt keine Entwicklungskarten " +
                        "mehr!").toString() + "\n", self());
            }
        } else {
            out.tell(
                    TOJSON.jsonError("Du hast nicht genügend Rohstoffe um " +
                            "eine Entwicklungskarte zu kaufen!").toString()
                            + "\n", self());
        }
    }

    /**
     * Handles the case when the player hat to give cards because someone rolled
     * seven and his amount of cards was over 7. May changes values of the
     * players or the game! Writes messages to the clients!
     *
     * @param givenCards
     */
    private void handleGivenCards(JsonNode givenCards) {
        JsonNode res = givenCards.get("Abgeben");
        ArrayList<Resource> resources = FromJson.getStuff(res);
    }

    /**
     * Handles the case for moving the robber. May changes values of the players
     * or the game! Writes messages to the clients!
     *
     * @param moveRobber
     */
    private void handleMoveRobber(JsonNode moveRobber) {
        Position pos = setRobber(moveRobber);
        if (pos != null) {
            int target = handleTarget(moveRobber, game.getGameboard().getLand(pos));
            messageWriter.sendRobberSet(player, pos, target);
            setStatus("Handeln oder Bauen");
        }
    }

    /**
     * Handles stealing a card from another player. May changes values of the
     * players or the game! Writes messages to the clients!
     *
     * @param moveRobber
     * @param tile
     * @return
     */
    private int handleTarget(JsonNode moveRobber, LandTile tile) {
        int target = -1;
        if (moveRobber.has("Ziel")) {
            target = moveRobber.get("Ziel").intValue();
            Player targetPlayer = game.getPlayer(target);
            if (targetPlayer != null && tile.hasBuilding(targetPlayer)) {
                Resource stolenCard = serverLogic.handelStealCard(target);
                if (stolenCard != null) {
                    targetPlayer.removeResource(stolenCard);
                    System.out.println(this.getClass() + " Dem Spieler: " + target
                            + " wurde folgender Rohstoff geklaut: " + stolenCard);
                    this.player.addResource(stolenCard);
                    messageWriter.sendRobberCostAndEarning(this.player, targetPlayer, stolenCard);
                } else {
                    out.tell(TOJSON.jsonError("Der gewünschte Spieler hat keine Rohstoffe").toString() + "\n", self());
                }
            }
        }
        return target;
    }

    /**
     * Handles all cases of build requests from the client (except for building
     * with a development card). May changes values of the players or the game!
     * Writes messages to the clients!
     *
     * @param building
     */
    private void handleBuildRequest(JsonNode building) {
        Position[] pos = FromJson.building(building);
        String type = building.get("Typ").textValue();
        switch (type) {
            case "Dorf":
                Cross location = gameLogic.locationToCross(pos);
                // STARTPHASE-----------------
                if (gameObservable.getRound() < 3) {
                    if (constructorLogic.isFreeSettlementPossible(id, location)) {
                        gameObservable.setSettlement(player, pos, false);
                        if (gameObservable.getRound() == 2) {
                            int[] startRes = serverLogic.getStartResources(this, location);
                            messageWriter.sendEarnings(player, startRes);
                            gameObservable.removeStartRes(startRes);
                        }
                        setStatus("Straße bauen");
                    } else {
                        out.tell(TOJSON.jsonError(
                                "Hoppla, das hat nicht funktioniert! " +
                                        "Bitte prüfe deine Rohstoffe oder " +
                                        "deinen gewählten Ort.").toString() + "\n", self());
                    }
                } else {
                    if (constructorLogic.isSettlementPossible(id, gameLogic.locationToCross(pos))) {
                        messageWriter.sendCosts(this.player, "Dorf", null, true);
                        gameObservable.setSettlement(player, pos, true);
                    } else {
                        out.tell(TOJSON.jsonError(
                                "Hoppla, das hat nicht funktioniert! " +
                                        "Bitte prüfe deine Rohstoffe oder " +
                                        "deinen gewählten Ort.").toString() + "\n", self());
                    }
                }
                break;
            case "Straße":
                Edge edge = gameLogic.locationToEdge(pos);
                if (gameObservable.getRound() > 3) {
                    if (constructorLogic.isFreeStreetPossible(id, edge.getCross1(), edge.getCross2())) {
                        gameObservable.setStreet(player, pos, false);
                        setStatus("Warten");
                        gameObservable.nextOne();
                    } else {
                        out.tell(TOJSON.jsonError("" +
                                "Hoppla, das hat nicht funktioniert!" +
                                "Bitte prüfe deine Rohstoffe oder " +
                                "deinen gewählten Ort.").toString() + "\n", self());
                    }
                } else {
                    if (constructorLogic.isFreeStreetPossible(id, edge.getCross1(), edge.getCross2())) {
                        messageWriter.sendCosts(this.player, "Straße", null, true);
                        gameObservable.setStreet(player, pos, true);
                    } else {
                        out.tell(TOJSON.jsonError("Hoppla, das hat nicht funktioniert! " +
                                "Bitte prüfe deine Rohstoffe oder " +
                                "deinen gewählten Ort.").toString() + "\n", self());
                    }
                }
                break;
            case "Stadt":
                Cross loc = gameLogic.locationToCross(pos);
                if (constructorLogic.isCityPossible(id, loc)) {
                    messageWriter.sendCosts(player, "Stadt", null, true);
                    gameObservable.setCity(this.player, pos, true);
                } else {
                    out.tell(TOJSON.jsonError("Hoppla, das hat nicht funktioniert! " +
                            "Bitte prüfe deine Rohstoffe oder " +
                            "deinen gewählten Ort.").toString() + "\n", self());
                }

                break;
        }
    }

    /**
     * Checks a message for cheat commands May changes values of the players or
     * the game! Writes messages to the clients!
     *
     * @param message
     */

    private void handleCheats(String message) {
        switch (message) {
            case "MÄHHHHH":
                giveCheatedRes(Resource.Wolle);
                break;
            case "NOMNOMNOM":
                giveCheatedRes(Resource.Getreide);
                break;
            case "ICHUNDMEINHOLZ":
                giveCheatedRes(Resource.Holz);
                break;
            case "KOHLEKOHLEKOHLE":
                giveCheatedRes(Resource.Erz);
                break;
            case "BRICKTASTIC":
                giveCheatedRes(Resource.Lehm);
                break;
            case "ICHBINKÖNIG":
                giveCheatedRes(Resource.Lehm);
                giveCheatedRes(Resource.Holz);
                giveCheatedRes(Resource.Getreide);
                giveCheatedRes(Resource.Wolle);
                giveCheatedRes(Resource.Erz);
                break;
        }
    }

    /**
     * Extra method for giving cheated resources. May changes values of the
     * players or the game! Writes messages to the clients!
     *
     * @param res
     */
    @SuppressWarnings("incomplete-switch")
    private void giveCheatedRes(Resource res) {
        for (int i = 0; i < 5; i++) {
            player.addResource(res);
        }
        int[] resources = new int[5];
        resources[0] = 0;
        resources[1] = 0;
        resources[2] = 0;
        resources[3] = 0;
        resources[4] = 0;
        switch (res) {
            case Lehm:
                resources[4] = 5;
                break;
            case Getreide:
                resources[0] = 5;
                break;
            case Holz:
                resources[3] = 5;
                break;
            case Erz:
                resources[2] = 5;
                break;
            case Wolle:
                resources[1] = 5;
                break;

        }
        messageWriter.sendEarnings(player, resources);

    }


    /**
     * Handles the case that the client wants to roll the dices. May changes
     * values of the players or the game! Writes messages to the clients!
     *
     * @return dices result = dices[0]+dices[1]
     */
    private int handleRollDice() {
        int[] dices = serverLogic.rollDices();
        messageWriter.sendDices(player, dices);
        return dices[0] + dices[1];
    }


    public String getStatus() {
        return this.status;
    }

    public int getId() {
        return this.id;
    }

    public ActorRef getOut() {
        return out;
    }

    public void sendCardToClient(String card) {
        out.tell(card + "\n", self());
    }

    public void sendDiceToClient(Object json) {
        out.tell(json.toString() + "\n", self());
    }

    public void sendStatusUpdateToClient(String message) {
        out.tell(message.toString() + "\n", self());
    }

    private void handleSeven() {
        gameObservable.checkResforSeven(player);
        if (player.getRes() > 7) {
            setStatus("Karten wegen Räuber abgeben");
        } else {
            setStatus("Warten");
            if (!gameObservable.isCardsForSevenFinished()) {
                out.tell(TOJSON.chatMessage(0, "Bitte warte. Andere Spieler müssen noch Karten abgeben wegen dem Räuber!").toString() + "\n", self());

            }
            while (!gameObservable.isCardsForSevenFinished()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            setStatus("Räuber versetzen");
        }
    }

    public void sendError() {
        out.tell(TOJSON.jsonError().toString() + "\n", self());
    }

    public void sendEarningsToClient(Player player, int[] resources) {
        out.tell(TOJSON.earnings(player, resources).toString() + "\n", self());
    }

    public void sendEarningsToClient(boolean unknow, Player robber, Resource stolenCard) {
        out.tell(TOJSON.robberEarning(unknow, robber, stolenCard) + "\n", self());

    }

    public void sendChatToCleint(int idChat, String messageChat) {
        out.tell(TOJSON.chatMessage(idChat, messageChat).toString() + "\n", self());
    }

    public void sendBuildingToClient(String message) {
        out.tell(message.toString() + "\n", self());
    }

    public void sendLongestStreetToClient(Player player) {
        out.tell(TOJSON.longestStreet(player).toString() + "\n", self());
    }

    public void sendLongestStreetResetToClient() {
        out.tell(TOJSON.longestStreetReset().toString() + "\n", self());
    }

    /**
     * Setter if a trade is active
     *
     * @param tradeActive
     */
    public void setTradeActive(boolean tradeActive) {
        this.tradeActive = tradeActive;

    }

    public void sendWinnerToClient(Player player) {
        out.tell(TOJSON.sendWinner(player).toString() + "\n", self());
    }

    public void sendCostsToClient(String output) {
        out.tell(output + "\n", self());
    }

    public void sendCostesForEveryOne(String output) {
        out.tell(output + "\n", self());
    }

    public void sendCostesOutPutUnknow(String output) {
        out.tell(output + "\n", self());
    }


    public void sendRobberSetToClient(Player player, Position pos, int target) {
        out.tell(TOJSON.robberSet(player, pos, target).toString() + "\n", self());
    }

    public void sendRobberCostToClient(boolean unknown, Player targetPlayer, Resource stolenCard) {
        out.tell(TOJSON.robberCost(unknown, targetPlayer, stolenCard).toString() + "\n", self());
    }


    /**
     * Sets the robber if the location is okay. May changes values of the
     * players or the game! Writes messages to the clients!
     *
     * @param moveRobber with moveRobber position
     * @return pos
     */
    private Position setRobber(JsonNode moveRobber) {
        int x = moveRobber.path("Ort").get("x").intValue();
        int y = moveRobber.path("Ort").get("y").intValue();
        Position pos = new Position(x, y);
        LandTile tile = game.getGameboard().getLand(pos);
        if (tile.isRobber()) {
            out.tell(TOJSON.jsonError("Der Räuber steht bereits auf diesem Feld, " +
                    "bitte wähle ein anderes").toString() + "\n", self());
        } else {
            LandTile oldRobber = game.getGameboard().getLand()[game.getGameboard().getRobber()];
            oldRobber.setRobber(false);
            game.getGameboard().setRobber(game.getGameboard().getLandIndex(pos));
            tile.setRobber(true);
        }
        return pos;
    }

    public void sendKnightPlayedToClient(Player player, Position pos, int target) {
        out.tell(TOJSON.knightPlayed(player, pos, target).toString() + "\n", self());
    }

    public void sendKnightPowerToClient(Player player) {
        out.tell(TOJSON.knightPower(player).toString() + "\n", self());
    }

    public void sendRoadBuildingToClient(Player player, List<Position[]> streets) {
        out.tell(TOJSON.roadBuilding(player, streets).toString() + "\n", self());
    }

    public void sendMonopolyCardToClient(Player player, String type) {
        out.tell(TOJSON.monopolyCard(player, type).toString() + "\n", self());
    }

    public void sendMonopolyCostsToClient(Player opponent, String type, int costs) {
        out.tell(TOJSON.monopolyCosts(opponent, type, costs) + "\n", self());
    }

    public void sendMonopolyEarningToClient(Player player, String type, int earning) {
        out.tell(TOJSON.monopolyEarning(player, type, earning).toString() + "\n", self());
    }

    public void sendInventionCardToClient(Player player, int[] resSend) {
        out.tell(TOJSON.inventionCard(player, resSend).toString() + "\n", self());
        out.tell(TOJSON.earnings(player, resSend).toString() + "\n", self());
    }

    public void sendTradeRequestToClient(Player player, int[] give, int[] get, int tradeIndex) {
        out.tell(TOJSON.tradeRequest(player, give, get, tradeIndex).toString() + "\n", self());
    }


    public void TradeAcceptToClient(Player player, boolean bool, int tradeIndex) {
        out.tell(TOJSON.tradeAcceptServer(player, tradeIndex, bool).toString() + "\n", self());
    }

    public void doTradeToClient(Player player, int id) {
        out.tell(TOJSON.doTrade(player, id).toString() + "\n", self());
    }

    public void TradeBreakToClient(Player player, int index) {
        out.tell(TOJSON.tradeDecline2(player.getId(), index).toString() + "\n", self());
    }

    public String toString() {
        return "Status :" + getPlayer().toString() + "KI: " + isKI + "!";
    }

}