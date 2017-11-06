package rzeznika.ndtClient.network;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rzeznika.ndtClient.controller.GameController;
import rzeznika.ndtClient.controller.Information;
import rzeznika.ndtClient.controller.Login;
import rzeznika.ndtClient.controller.PlayerObserver;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.model.Position;
import rzeznika.ndtClient.model.Resource;

/**
 * Created by F.Arian on 01.07.17.
 */

public class Client extends Observable {
    /**
     * is set true when the player has chosen a valid color
     */
    private boolean isValidColor = false;
    /**
     * is set true when there is a response to the color message
     */
    private boolean hasAnswer = false;
    /**
     * is true in the beginning and switched to false after the first two rounds
     */
    private boolean isGameStart = true;

    /**
     * Variable to check and set if the client is running correctly
     */
    private boolean running;

    private WebSocketEcho listener;
    private OkHttpClient okHttpClient;



    private static int id;
    public WebSocket gameWebSocket;
    private Request request;
    private JsonNode receiveMsg;
    private PlayerObserver playerObserver;
    private Game game;
    private GameController gameController;
    private String clientStatus;
    private Login log;

    public Client(String url) {
        okHttpClient = new OkHttpClient();
        this.listener = new WebSocketEcho();
        this.request = new Request.Builder().url(url).build();
        this.gameWebSocket = okHttpClient.newWebSocket(request, listener);
        log = new Login("ClientLog");
        log.getLogin().info("Client gestartet");
        this.running = true;
        this.clientStatus = "";
        this.game = new Game();
        this.gameController = new GameController(game);
    }
    public static int getId() {
        return id;
    }


    private class WebSocketEcho extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 9999;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            System.out.println("Opening Socket");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            log.getLogin().info("Message :" + text + " erhalten!");
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getJsonFactory();
            JsonParser jp = null;
            try {
                jp = factory.createJsonParser(text);
                receiveMsg = mapper.readTree(jp);
                clientMsgActor(receiveMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
            System.out.println("Closing : " + code + " / " + reason);

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
        }
    }


    public synchronized void clientMsgActor(JsonNode json) {
        String jsonKey = json.fields().next().getKey();

        switch (jsonKey) {
            case "Hallo":
                gameWebSocket.send(TOJSON.clientConnection().toString() + "\n");
                break;
            case "Willkommen":
                setId(json.get("Willkommen").get("id").intValue());
                log.getLogin().info("Folgende ID :" + this.id + " vom Server erhalten");
                gameController.setOwnId(this.id);
            case "Serverantwort":
                String msg = json.path("Serverantwort").textValue();
                if (json.get("Serverantwort").textValue().equals(msg)) {
                    setChanged();
                    notifyObservers(new Information("ColorUsed"));
                }

                log.getLogin().info("Serverantwort:" + json.get("Serverantwort").toString());
                if (isGameStart && json.get("Serverantwort").textValue().equals("Farbe bereits vergeben")) {
                    this.hasAnswer = true;
                }
                break;
            case "Spiel gestartet":
                ArrayList<Player> players1 = this.playerObserver.getPlayersReady();
                if (players1.get(0).getId() != this.id) {
                    ArrayList<Player> newPlayers = new ArrayList<Player>();
                    for (int i = 0; i < players1.size(); i++) {
                        if (players1.get(i).getId() == this.id) {
                            newPlayers.add(players1.remove(i));
                            newPlayers.addAll(players1);
                            players1 = newPlayers;
                        }
                    }

                }
                // Game need 4 Players for Start
                if (players1.size() == 3) {
                    players1.add(new Player());
                }
                this.gameController.setPlayers(players1);
                this.game.setPlayer(players1);
                this.game = gameController.getGame();
                this.gameController.gameStarted();
                this.gameController.getGame().setGameboard(FROMJSON.giveCard(json));
                setChanged();
                notifyObservers(new Information("GameStarted"));
                break;
            case "Statusupdate":
                JsonNode player = json.path("Statusupdate").get("Spieler");
                String status = player.path("Status").textValue();
                int messageId = player.path("id").intValue();
                this.gameController.setStatus(messageId, status);
                String name = null;
                String color = null;
                if (player.has("Rohstoffe") && player.path("Rohstoffe").has("Unbekannt")) {
                    if (!player.path("Status").textValue().equals("Spiel starten")
                            && !player.path("Status").textValue().equals("Wartet auf Spielbeginn")
                            && !player.path("Status").textValue().equals("Verbindung verloren")) {
                        int amount = player.path("Rohstoffe").path("Unbekannt").intValue();
                        this.gameController.sendOpponentResAmount(messageId, amount);
                    }
                } else if (player.has("Rohstoffe") && messageId == this.id) {
                    this.gameController.sendOwnResAmount(getAmountofRes(player.path("Rohstoffe")));
                }
                if (status.equals("Spiel starten")) {
                    if (player.has("Farbe")) {
                        name = player.path("Name").textValue();
                        color = player.path("Farbe").textValue();

                    }
                    this.playerObserver.updatePlayer(messageId, color, name);

                } else if (status.equals("Wartet auf Spielbeginn")) {
                    name = player.path("Name").textValue();
                    color = player.path("Farbe").textValue();
                    playerObserver.updatePlayerWaiting(messageId, color, name);
                    if (messageId == this.id) {
                        this.gameController.setName(this.id, player.path("Name").textValue());
                        this.gameController.setColor(this.id, player.path("Farbe").textValue());
                        this.hasAnswer = true;
                        //TODO for View
                        this.isValidColor = true;
                        this.isGameStart = false;
                    }
                } else if (status.equals("Verbindung verloren")) {
                    this.playerObserver.removePlayerWithConnectionLost(messageId);
                }
                if (!status.equals("Spiel starten") && !status.equals("Wartet auf Spielbeginn") &&
                        !status.equals("Verbindung verloren")) {
                    int vicPoints = player.path("Siegpunkte").intValue();
                    this.gameController.setVictoryPoints(messageId, vicPoints);
                }
                if (player.has("Längste Handelsstraße")) {
                    if (player.fields().next().getKey().equals("Längste Handelsstraße")) {
                        this.gameController.setHasLongestStreet(player.path("id").intValue());
                    }
                }
                if (player.has("Größte Rittermacht")) {
                    if (player.fields().next().getKey().equals("Größte Rittermacht")) {
                        this.gameController.setHasKnightPower(player.path("id").intValue());
                    }
                }

                break;
            case "Würfelwurf":
                this.gameController.setDices(FROMJSON.dices(json));
                break;
            case "Ertrag":
                int msgId = json.path("Ertrag").path("Spieler").intValue();
                if (this.id == msgId) {
                    ArrayList<Resource> resources = FROMJSON.getStuff(json.get("Ertrag").get("Rohstoffe"));
                    this.gameController.addResList(msgId, resources);
                }
                break;
            case "Kosten":
                int newMsgId = json.get("Kosten").get("Spieler").intValue();
                JsonNode price = json.get("Kosten");
                ArrayList<Player> players = this.game.getPlayer();
                if (newMsgId == this.id) {
                    ArrayList<Resource> resources = FROMJSON.getStuff(price.get("Rohstoffe"));
                    for (int i = 0; i < players.size(); i++) {
                        if (players.get(i).getStatus().equals("Räuber versetzen") && players.get(i).getId() != this.id) {
                            if (resources.size() > 0) {
                                //for Android Monitor
                                System.out.println(this.getClass() + players.get(i).getName());

                                this.gameController.removeRobberResource(players.get(i), resources.get(0));
                            }
                        }
                    }
                    while (resources.size() > 0) {
                        this.gameController.removeResource(newMsgId, resources.remove(0));
                    }
                }
                break;
            case "Bauvorgang":
                String[] building = FROMJSON.buildingS(json);
                Position[] positions = FROMJSON.buildingAtPosition(json);
                if (building[1].equals("Dorf")) {
                    this.gameController.setSettlement(this.gameController.getGameLogic().locationToCross(positions),
                            this.gameController.getGame().getPlayer(Integer.parseInt(building[0])));
                } else if (building[1].equals("Stadt")) {
                    gameController.setCity(gameController.getGameLogic().locationToCross(positions),
                            gameController.getGame().getPlayer(Integer.parseInt(building[0])));
                } else if (building[1].equals("Straße")) {
                    this.gameController.setStreet(this.gameController.getGameLogic().locationToEdge(positions),
                            this.gameController.getGame().getPlayer(Integer.parseInt(building[0])));
                    if (Integer.parseInt(building[0]) == this.id && this.game.getRound() < 3) {
                        this.game.increaseRound();
                    }
                }
                break;
            case "Längste Handelsstraße":
                int playerID = FROMJSON.longestStreet(json.get("Längste Handelsstraße"));
                if (playerID != 0) {
                    gameController.setHasLongestStreet(playerID);
                } else {
                    gameController.resetHasLongestStreet();
                }
                break;
            case "Entwicklungskarte gekauft":
                JsonNode devCard = json.get("Entwicklungskarte gekauft");
                if (devCard.get("Entwicklungskarte").textValue().equals("Unbekannt")) {  //unsicher mit euquals TODO
                    //  gameController.addDevelopmentCard(devCard.get("Spieler").intValue(), 1); //TODO
                } else {
                    if (devCard.get("Entwicklungskarte").textValue().equals("Siegpunkt")) {
                        gameController.addVictoryCard(devCard.get("Spieler").intValue());
                    }
                    gameController.addDevelopmentCard(devCard.get("Spieler").intValue(), FROMJSON.getDevelopmentCard(devCard));
                }
            case "Räuber versetzt":
                int newIndex = gameController.getGameLogic()
                        .getTileIndex(new Position(json.path("Räuber versetzt").path("Ort").get("x").intValue(),
                                json.path("Räuber versetzt").path("Ort").get("y").intValue()));
                gameController.setRobber(newIndex);
                break;
            case "Chatnachricht":
                // gameWebSocket.send(TOJSON.chatMessage(this.player.getId()).toString()+"\n");
                break;
            case "Chatnachricht senden":
                gameWebSocket.send(TOJSON.chatMessageToSend().toString() + "\n");
                //TODO
                break;
            case "Handelsangebot":
                int tradeSend = json.path("Handelsangebot").get("Spieler").intValue();
                game.getTrade().setTradeSend(tradeSend);
                int[][] trade = FROMJSON.tradeRequest(json.get("Handelsangebot"));
                gameController.getTradeLogic().setTradeIndex(trade[1][0]);
                if (trade[0][0] != id) {
                    gameController.getTradeLogic().tradeRequest(trade[3], trade[2]);
                }
                break;
            case "Handelsangebot zurückgezogen":
                int declinedId = json.path("Handelsangebot zurückgezogen").get("Spieler").intValue();
                Player declined = game.getPlayer(declinedId);
                gameController.getTradeLogic().setTradeCanceled(declined);
                if (declinedId == game.getTrade().getTradeSend() && declinedId != this.id) {
                    gameController.getTradeLogic().setTradeWithdrawn();
                }
                break;
            case "Handelsangebot angenommen":
                if (FROMJSON.tradeAccepted2(json.get("Handelsangebot angenommen"))) {
                    gameController.getTradeLogic()
                            .setAccepted(FROMJSON.tradeAccepted(json.get("Handelsangebot angenommen")));
                } else {
                    gameController.getTradeLogic().setAcceptedFalse(
                            game.getPlayer(json.path("Handelsangebot angenommen").get("Mitspieler").intValue()));
                }
                break;
            case "Handel ausgeführt":
                gameController.getTradeLogic().setTradeDone();
                break;
            case "Handelsangebot abgebrochen":
                Player playerDeclined = game.getPlayer(json.path("Handelsangebot abgebrochen").get("Spieler").intValue());
                gameController.getTradeLogic().setTradeCanceled(playerDeclined);
                break;
            case "Fehler":
                gameController.setErrorMessage(FROMJSON.errorMessage(json));
                log.getLogin().info(json.get("Fehler").toString());
                break;
            case "Ritter ausspielen":
                JsonNode location = json.get("Ritter ausspielen").get("Ort");
                Position position = new Position(location.get("x").intValue(), location.get("y").intValue());
                int tileIndex = game.getGameboard().getLandIndex(position);
                int playerId = json.path("Ritter auspielen").get("Spieler").intValue();
                if (json.get("Ritter ausspielen").has("Ziel")) {
                    int targetId = json.path("Ritter ausspielen").get("Ziel").intValue();
                    gameController.setKnightTarget(targetId);
                }
                gameController.setRobber(tileIndex);
                gameController.devCardPlayed("Ritter", playerId);
                break;
            case "Straßenbaukarte ausspielen":
                int playerId1 = json.path("Straßenbaukarte ausspielen").get("Spieler").intValue();
                gameController.devCardPlayed("Straßenbau", playerId1);
                break;
            case "Monopol":
                int playerId2 = json.path("Monopol").get("Spieler").intValue();
                gameController.devCardPlayed("Monopol", playerId2);
                break;
            case "Erfindung":
                int playerId3 = json.path("Erfindung").get("Spieler").intValue();
                gameController.devCardPlayed("Erfindung", playerId3);
                break;
            case "Spiel beendet":
                int winner = json.path("Spiel beendet").get("Sieger").intValue();
                if (winner == -1) {
                    gameController.setConnectionLost();
                } else {
                    gameController.gameEnd(winner);
                }
                running = false;
                log.getLogin().info("Spiel wurde vom Server beendet.");
                break;
            default:
                gameWebSocket.send(TOJSON.jsonError("Client unterstützt diese Nachricht-Key nicht ! " +
                        "bitte korrigieren Sie Ihre Nachricht key").toString() + "\n");
                break;

        }
    }

     /*-----------------------------------------------------------------------------------*/

    /**
     * Setter for the local id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for isValidColor
     *
     * @return boolean
     */
    public boolean isValidColor() {
        return isValidColor;
    }

    /**
     * Setter for hasAnswer
     *
     * @param hasAnswer
     */
    public void sethasAnswer(boolean hasAnswer) {
        this.hasAnswer = hasAnswer;
    }

    /**
     * Getter for hasAnswer
     *
     * @return boolean
     */
    public boolean isHasAnswer() {
        return hasAnswer;
    }

    /**
     * assigns resources to current player
     *
     * @param json
     * @return Resource for player
     */
    public int getAmountofRes(JsonNode json) {
        int result = 0;
        if (json.has("Wolle")) {
            result = result + json.path("Wolle").intValue();
        }
        if (json.has("Erz")) {
            result = result + json.path("Erz").intValue();
        }
        if (json.has("Lehm")) {
            result = result + json.path("Lehm").intValue();
        }
        if (json.has("Holz")) {
            result = result + json.path("Holz").intValue();
        }
        if (json.has("Getreide")) {
            result = result + json.path("Getreide").intValue();
        }

        return result;
    }

    /**
     * Sends the players choice for name and color to the server
     *
     * @param name
     * @param color
     */
    public void setColorName(String name, String color) {
        String output = TOJSON.player(name, color).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends a start request to the server
     */
    public void wantToStart() {
        String output = TOJSON.start().toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Send the request to roll the dices to the server
     */
    public void rollDices() {
        String output = TOJSON.rollDices().toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the message that the player wants to end the move to the server
     */
    public void endMove() {
        String output = TOJSON.endMove().toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends a chat message to the server
     *
     * @param message
     */
    public void sendChat(String message) {
        String output = TOJSON.chatRequest(message).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);

    }
    // ------------------------- Development cards -----------------

    /**
     * Sends the request that a player wants to buy a development card
     */
    public void buyDevelopmentCard() {
        String output = TOJSON.buyDevelopmentCard().toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that the player wants to play a monopoly card
     *
     * @param resources
     */
    public void playMonopol(String resources) {
        String output = TOJSON.monopol(resources).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that the player wants to play a knight card.
     *
     * @param pos    position of the robber
     * @param target player who is robbed
     */
    public void playKnight(Position pos, int target) {
        String output = TOJSON.useKnight(pos, target).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that the player wants to use a street building
     * card. Is used when the player only has one street left to build.
     *
     * @param pos position of the street
     */
    public void buildStreetsCard1(Position[] pos) {
        String output = TOJSON.buildStreets(pos).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that the player wants to use a street building
     * card.
     *
     * @param pos1 position of sreet1
     * @param pos2 position of street2
     */
    public void buildStreetsCard2(Position[] pos1, Position[] pos2) {
        String output = TOJSON.buildStreets(pos1, pos2).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that a player wants to play an invention card.
     *
     * @param res
     */
    public void playInventionCard(int[] res) {
        String output = TOJSON.invention(res).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }
    // -----------------------Robber-------------------------------

    public void setRobber(Position pos, int target) {
        String output = TOJSON.robberSet(pos, target).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the cards that the player had to give away because of the robber
     *
     * @param res
     */
    public void giveCards(int[] res) {
        System.out.println(this.getClass() + TOJSON.cardAway(res).toString());
        String output = TOJSON.cardAway(res).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }
    // -----------------Construction--------------------------

    /**
     * Sends a build request to the server. Used for streets, settlements and
     * cities.
     *
     * @param type type of building
     * @param pos  location of the building
     */
    public void buildRequest(String type, Position[] pos) {
        String output = TOJSON.buildRequest(type, pos).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);

    }
    // ------------------Trade ---------------------------------

    /**
     * sends a see trade information to the server
     *
     * @param give resources to give away
     * @param get  resource to get
     */
    public void seaTrade(int[] give, int[] get) {
        String output = TOJSON.seaTrade(give, get).toString() + "\n";
        log.getLogin().info(output);
        gameWebSocket.send(output);
    }

    /**
     * Sends the information that the player wants to trade to the server
     *
     * @param give resources to give away
     * @param get  resources to get
     */
    public void trade(int[] give, int[] get) {
        String output = TOJSON.trade(give, get).toString() + "\n";
        gameWebSocket.send(output);
        log.getLogin().info("An Server : " + output);
    }

    /**
     * Sends the information that the player has accepted the trade to the
     * server
     */
    public void acceptTrade() {
        String output = TOJSON.tradeAccept(game.getTrade().getTradeIndex()).toString() + "\n";
        gameWebSocket.send(output);
        log.getLogin().info("An Server : " + output);
    }

    /**
     * Sends the information that the player has accepted the trade to the
     * server
     */
    public void noAcceptTrade() {
        String output = TOJSON.noTradeAccept(game.getTrade().getTradeIndex()).toString() + "\n";
        gameWebSocket.send(output);
        log.getLogin().info("An Server : " + output);
    }

    /**
     * Sends the information that the player has declined a trade to the server
     */
    public void declineTrade() {
        String output = TOJSON.tradeDecline(game.getTrade().getTradeIndex()).toString() + "\n";
        gameWebSocket.send(output);
        log.getLogin().info("An Server : " + output);

    }

    /**
     * Sends the information that the player has accepted the trade with player
     * with playerID
     */
    public void doTrade(int playerID) {
        String output = TOJSON.doTrade(game.getTrade().getTradeIndex(), playerID).toString() + "\n";
        gameWebSocket.send(output);
        log.getLogin().info("An Server : " + output);
    }

    public PlayerObserver getPlayerObserver() {
        return this.playerObserver;
    }

    /**
     * Getter for the status of the client, represents the status of the player
     *
     * @return String status
     */
    public String getClientStatus() {
        return this.clientStatus;
    }

    /**
     * Setter for the status of the client, represents the status of the player
     * in game
     *
     * @param status
     */
    public void setStatus(String status) {
        this.clientStatus = status;
    }

}
