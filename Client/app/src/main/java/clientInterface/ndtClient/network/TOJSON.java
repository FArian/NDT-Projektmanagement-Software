package rzeznika.ndtClient.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import rzeznika.ndtClient.model.DevelopmentCard;
import rzeznika.ndtClient.model.HarberType;
import rzeznika.ndtClient.model.LandTile;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.model.Position;
import rzeznika.ndtClient.model.Resource;

/**
 * Created by F.Arian on 16.06.17.
 */

public class TOJSON {


    static ObjectMapper mapper = new ObjectMapper();

    public static Object helloVersion() {
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Version", "...");
        ((ObjectNode) root).put("Hallo", msg);
        return root;
    }

    //---------------------------------Connection-------------------------------------

    /**
     * Translates the request and information to connect a Android Client with the server
     *
     * @return
     */

    public static Object clientConnection() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Version", "AndroidClient 0.1 (sepgroup02)");
        ((ObjectNode) root).put("Hallo", msg);

        return root;
    }

    /**
     * Translates the request and information to connect a KI with the server
     *
     * @return
     */

    public static Object kiConnection() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Version", "AndroidClient 1.0 (KI)");
        ((ObjectNode) root).put("Hallo", msg);

        return root;
    }

    public static Object chatMessage(int id) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Absender", id);
        ((ObjectNode) msg).put("Nachricht", "Help me, Obi-Wan Kenobi.");
        ((ObjectNode) root).put("Chatnachricht", msg);
        return root;
    }

    public static Object chatMessageToSend() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Nachricht", "Help me, Obi-Wan Kenobi.");
        ((ObjectNode) root).put("Chatnachricht", msg);
        return root;
    }

    //---------------------------Game Start , Card , Titles-----------------------------------------

    /**
     * translates a specific position
     *
     * @param pos
     * @return JsonNode
     */
    public static Object positionJson(Position pos) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("x", pos.getX());
        ((ObjectNode) msg).put("y", pos.getY());
        return msg;
    }

    public static Object positionJson(Position[] pos) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        for (int i = pos.length - 1; i >= 0; i--) {
            ((ObjectNode) msg).put("Position", String.valueOf(positionJson(pos[i])));
        }
        return msg;
    }

    /**
     * Translate the land of the game to a JSONNode
     */
    public static Object landTiles(LandTile landTile) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tile = mapper.createObjectNode();
        ((ObjectNode) tile).put("Ort", String.valueOf(positionJson(landTile.getPosition())));
        Resource resource = landTile.getType();
        if (landTile.getType() != null) {
            if (resource.equals(Resource.WOOL)) {
                ((ObjectNode) tile).put("Type", "Weideland");
            }
            if (resource.equals(Resource.BRICK)) {
                ((ObjectNode) tile).put("Type", "Hügelland");
            }
            if (resource.equals(Resource.LUMBER)) {
                ((ObjectNode) tile).put("Type", "Wald");
            }
            if (resource.equals(Resource.ORE)) {
                ((ObjectNode) tile).put("Type", "Gebirge");
            }
            if (resource.equals(Resource.GRAIN)) {
                ((ObjectNode) tile).put("Type", "Ackerland");
            }
            if (resource.equals(Resource.SEA)) {
                ((ObjectNode) tile).put("Type", "Meer");
            } else {
                ((ObjectNode) tile).put("Type", "Wüste");
            }
            if (landTile.getType() != Resource.SEA && landTile.getType() != null) {
                ((ObjectNode) tile).put("Zahl", landTile.getChipNr());
            }
        }

        return tile;
    }

    /**
     * Translates the harbors with location and type
     *
     * @param harbor
     * @param pos
     * @return JsonNode
     */
    public static Object harbor(HarberType harbor, Position[] pos) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode harbors = mapper.createObjectNode();
        for (int i = pos.length - 1; i <= 0; i--) {
            ((ObjectNode) harbors).put("Ort", String.valueOf(positionJson(pos[i])));
            if (harbors.equals(harbor.GENERIC)) {
                ((ObjectNode) harbors).put("Type", "Hafen");
            }
            if (harbors.equals(harbor.WOOL)) {
                ((ObjectNode) harbors).put("Type", "Wolle Hafen");
            }
            if (harbors.equals(harbor.BRICK)) {
                ((ObjectNode) harbors).put("Type", "Lehm Hafen");
            }
            if (harbors.equals(harbor.LUMBER)) {
                ((ObjectNode) harbors).put("Type", "Holz Hafen");
            }
            if (harbors.equals(harbor.ORE)) {
                ((ObjectNode) harbors).put("Type", "Erz Hafen");
            }
            if (harbors.equals(harbor.GRAIN)) {
                ((ObjectNode) harbors).put("Type", "Getreide Hafen");
            }
        }
        return harbors;
    }

    public static Object statusUpdate(String newStatus, Player player, boolean unknown) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode playerMsg = mapper.createObjectNode();
        JsonNode playerJson = mapper.createObjectNode();
        ((ObjectNode) playerJson).put("id", player.getId());
        ((ObjectNode) playerJson).put("name", player.getName());
        ((ObjectNode) playerJson).put("farbe", player.getColor());
        ((ObjectNode) playerJson).put("Status", newStatus);
        ((ObjectNode) playerJson).put("Rittermacht", player.getKnights());
        ((ObjectNode) playerJson).put("Größte Rittermacht", player.isHasKnightPower());
        ((ObjectNode) playerJson).put("Längste Handelsstraße", player.isHasLongestStreet());
        if (unknown) {
            int vicpoints = player.getPlayerVictoryPoints() - player.getDevCardAmount()[4];
            ((ObjectNode) playerJson).put("Siegpunkte", vicpoints);
            JsonNode res = mapper.createObjectNode();
            ((ObjectNode) res).put("Unbekannt", player.getResources().size());
            ((ObjectNode) playerJson).put("Rohstoffe", res);
            JsonNode devCard = mapper.createObjectNode();
            ((ObjectNode) devCard).put("Unbekannt", player.getDevelopmentCards().size());
            ((ObjectNode) playerJson).put("Entwicklungskarten", devCard);
        } else {
            ((ObjectNode) playerJson).put("Siegpunkte", player.getPlayerVictoryPoints());
            ((ObjectNode) playerJson).put("Siegpunkte", player.getPlayerVictoryPoints());
            ((ObjectNode) playerJson).put("Rohstoffe", String.valueOf(resources(player.getResAmountArray())));
            ((ObjectNode) playerJson).put("Entwicklungskarten", String.valueOf(developmentCards(player)));
        }
        ((ObjectNode) playerMsg).put("Spieler", playerJson);
        ((ObjectNode) root).put("Statusupdate", playerMsg);

        return root;

    }

    /**
     * Translates a status update. Does not contain the full player object. Can
     * be used when the player have not chosen a color and name yet
     *
     * @param id
     * @return JsonNode
     */
    public static Object statusGameStartWithoutColor(int id) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg2 = mapper.createObjectNode();
        JsonNode msg1 = mapper.createObjectNode();
        ((ObjectNode) msg2).put("id", id);
        ((ObjectNode) msg2).put("Status", "Spiel starten");
        ((ObjectNode) msg1).put("Spieler", msg2);
        ((ObjectNode) root).put("Statusupdate", msg1);
        return root;
    }

    /**
     * Translates a status update. Can be used when the game has not started yet
     * but the player is ready for game start
     *
     * @param id
     * @param name
     * @param color
     * @param status
     * @return JsonNode
     */
    public static Object statusGameStart(int id, String name, String color, String status) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg2 = mapper.createObjectNode();
        JsonNode msg1 = mapper.createObjectNode();
        ((ObjectNode) msg2).put("id", id);
        ((ObjectNode) msg2).put("Name", name);
        ((ObjectNode) msg2).put("Status", status);
        ((ObjectNode) msg2).put("Farbe", color);
        ((ObjectNode) msg1).put("Farbe", msg2);
        ((ObjectNode) root).put("Statusupdate", msg1);
        return root;
    }

    // ---------------ERROR MESSAGES--------------------------

    /**
     * Translates the error message that the color is already used
     *
     * @return JsonNode
     */
    public static Object colorError() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Serverantwort", "Farbe bereits vergeben");
        return root;
    }

    /**
     * Translated the error message that the player has to chose a color first
     *
     * @return JSONObject
     */
    public static Object colorError2() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Meldung", "Du musst erst eine Farbe wählen");
        ((ObjectNode) root).put("Fehler", msg);
        return root;
    }

    /*------------------GENERAL STUFF----------------------------------------*/

    /**
     * Translates the action rollDices to a JsonNode
     *
     * @return JsonNode
     */

    public static Object rollDices() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Würfeln", "Value");
        return root;
    }

    /**
     * Message with the result of the dices and the player who rolled them
     *
     * @param id
     * @param dices
     * @return JsonNode
     */

    public static Object diceResult(int id, int[] dices) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Spieler", id);
        ((ObjectNode) msg).put("Spieler", id);
        ((ObjectNode) msg).put("Wurf", String.valueOf(dices));
        ((ObjectNode) root).put("Würfelwurf", msg);
        return root;
    }

    /**
     * Translates the action that a player want to end his turn to a JsonNode
     *
     * @return JsonNode
     */
    public static Object endMove() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Zug beenden", "Ende");
        return root;
    }

    /**
     * Translates a message for the chat to a JsonNode
     *
     * @param message
     * @return JsonNode
     */

    public static Object chatRequest(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Nachricht", message);
        ((ObjectNode) root).put("Chatnachricht senden", msg);
        return root;
    }

    public static Object chatMessage(int id, String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        if (id != 0) {
            ((ObjectNode) msg).put("Absender", id);
        }
        ((ObjectNode) msg).put("Nachricht", message);
        ((ObjectNode) root).put("Chatnachricht", msg);

        return root;
    }
    /*------------------PLAYER-----------------------------------------*/

    /**
     * Translates the whole status of a player to a JsonNode
     *
     * @param player
     * @return JsonNode
     */
    public static Object playerStatus(Player player) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("id", player.getId());
        ((ObjectNode) root).put("Name", player.getName());
        ((ObjectNode) root).put("Farbe", player.getColor());
        ((ObjectNode) root).put("Status", player.getStatus());
        ((ObjectNode) root).put("Siegpunkte", player.getPlayerVictoryPoints());
        ((ObjectNode) root).put("Rohstoffe", String.valueOf(resources(player.getResAmountArray())));
        return root;
    }

    /**
     * Translates a players name and color to a JsonNode
     *
     * @param name  name of the player
     * @param color color of the player
     * @return JsonNode
     */
    public static Object player(String name, String color) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Name", name);
        ((ObjectNode) msg).put("Farbe", color);
        ((ObjectNode) root).put("Spieler", msg);
        return root;
    }


    /*------------------RESOURCES-----------------------------------*/

    /**
     * Translates a specific amount of resources to a JSONNode Can be used to
     * send all resources of a player, the resources for trading or the
     * resources a player gets.
     *
     * @param res amount of resources
     * @return JsonNode
     */

    public static Object resources(int[] res) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        if (res[0] > 0) {
            ((ObjectNode) root).put("Getreide", res[0]);
        }
        if (res[1] > 0) {
            ((ObjectNode) root).put("Wolle", res[1]);
        }
        if (res[2] > 0) {
            ((ObjectNode) root).put("Erz", res[2]);
        }
        if (res[3] > 0) {
            ((ObjectNode) root).put("Holz", res[3]);
        }
        if (res[4] > 0) {
            ((ObjectNode) root).put("Lehm", res[4]);
        }
        return root;
    }

    /*-----------------CONSTRUCTION-------------------*/

    /**
     * @param type int  0 = settlement, 1 = city, else = street
     * @param pos  position of the building
     * @return JsonNode
     */
    public static Object buildRequest(String type, Position[] pos) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        if (type.equals("Dorf")) {
            ((ObjectNode) msg).put("Typ", "Dorf");
        } else if (type.equals("Stadt")) {
            ((ObjectNode) msg).put("Typ", "Stadt");
        } else {
            ((ObjectNode) msg).put("Typ", "Stra\u00dfe");
        }
        ((ObjectNode) msg).put("Ort", String.valueOf(positionJson(pos)));
        ((ObjectNode) root).put("Bauen", msg);
        return root;

    }

    /**
     * Translates the action buildStreet to a JsonNode
     *
     * @param pos    position of the street
     * @param player player who built the street
     * @return JsonNode
     */
    public static Object street(Position[] pos, Player player) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Eigent\u00fcmer", Integer.toString(player.getId()));
        ((ObjectNode) root).put("Typ", "Stra\u00dfe");
        ((ObjectNode) root).put("Ort", String.valueOf(positionJson(pos)));
        return root;
    }

    /**
     * Translates the action buildSettlement to a JsonNode
     *
     * @param player player who has built a settlement
     * @param pos    position of the settlement
     * @return JsonNode
     */
    public static Object settlement(Position[] pos, Player player) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Eigent\u00fcmer", Integer.toString(player.getId()));
        ((ObjectNode) root).put("Typ", "Dorf");
        ((ObjectNode) root).put("Ort", String.valueOf(positionJson(pos)));
        return root;
    }

    /**
     * Translates the action buildCity to a JsonNode
     *
     * @return JsonNode
     */
    public static Object city(Position[] pos, Player player) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Eigent\u00fcmer", Integer.toString(player.getId()));
        ((ObjectNode) root).put("Typ", "Stadt");
        ((ObjectNode) root).put("Ort", String.valueOf(positionJson(pos)));
        return root;
    }

    /*------------------DEVELOPMENT-------------------*/

    public static Object developmentCards(Player player) {
        int[] amount = player.getDevCardAmount();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Monopol", amount[0]);
        ((ObjectNode) root).put("Straßenbau", amount[1]);
        ((ObjectNode) root).put("Erfindung", amount[2]);
        ((ObjectNode) root).put("Ritter", amount[3]);
        ((ObjectNode) root).put("Siegpunkt", amount[4]);
        return root;
    }

    /**
     * used to send a developmentcard buy Request to server
     *
     * @return JsonNode
     */
    public static Object buyDevelopmentCard() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Entwicklungskarte kaufen", "...");
        return root;
    }

    /**
     * A Jsonobject used to tell the Server how u wana use the Knightcard
     *
     * @param pos    new Tile
     * @param target the Player u wanna steal from
     * @return knight object
     */
    public static Object useKnight(Position pos, int target) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Ort", String.valueOf(positionJson(pos)));
        if (target != 0) {
            ((ObjectNode) msg).put("Ziel", target);
        }
        ((ObjectNode) root).put("Ritter ausspielen", msg);

        return root;
    }

    /**
     * Builds the JsonNode that is send to the Server to use a Streetbuilding
     * card
     *
     * @param pos1
     * @param pos2
     * @return JsonNode
     */
    public static Object buildStreets(Position[] pos1, Position[] pos2) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Straße 1", String.valueOf(positionJson(pos1)));
        ((ObjectNode) msg).put("Straße 2", String.valueOf(positionJson(pos2)));
        ((ObjectNode) root).put("Straßenbaukarte ausspielen", msg);
        return root;

    }

    /**
     * Same as the one above, but used if there can only be build one more Street
     *
     * @param pos
     * @return JsonNode
     */
    public static Object buildStreets(Position[] pos) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Straße 1", String.valueOf(positionJson(pos)));
        ((ObjectNode) root).put("Straßenbaukarte ausspielen", msg);
        return root;
    }
    /**
     * Message that contains the String error
     *
     * @return JsonNode
     */
    public static Object jsonError(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Nachricht", message);
        ((ObjectNode) root).put("Chatnachricht", msg);
        return root;

    }

    /**
     * Message that a player wants to use an invention card
     *
     * @param res
     * @return JsonNode
     */
    public static Object invention(int[] res) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Rohstoffe", String.valueOf(resources(res)));
        ((ObjectNode) root).put("Erfindung", msg);
        return root;
    }

    /**
     * Message that a player wants to use a monopoly card
     *
     * @param res
     * @return JsonNode
     */
    public static Object monopol(String res) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Rohstoff", res);
        ((ObjectNode) root).put("Monopol", msg);
        return root;
    }
        /*------------------ROBBER-------------------*/

    /**
     * used to set the robber If there is no target use 0
     *
     * @param pos
     * @param target
     * @return JsonNode
     */
    public static Object robberSet(Position pos, int target) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Ort", String.valueOf(positionJson(pos)));
        if (target != 0) {
            ((ObjectNode) msg).put("Ziel", target);
        }
        ((ObjectNode) root).put("Räuber versetzen", msg);
        return root;
    }

    /**
     * to give away Cards
     *
     * @param res
     * @return JsonNode
     */
    public static Object cardAway(int[] res) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Abgeben", String.valueOf(resources(res)));
        ((ObjectNode) root).put("Karten abgeben", msg);
        return root;

    }
        /*-----------------SPECIAL CARDS----------------------------*/

    /**
     * Message that a player has bought a developmentcard. Type is visible just
     * for the player who bought the card.
     *
     * @param player
     * @param dev
     * @param self
     * @return JsonNode
     */
    public static Object sendDevelopmentCard(int player, DevelopmentCard dev, boolean self) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Spieler", player);
        if (self) {
            switch (dev) {
                case KNIGHT:
                    ((ObjectNode) msg).put("Entwicklungskarte", "Ritter");
                    break;
                case MONOPOLY:
                    ((ObjectNode) msg).put("Entwicklungskarte", "Monopol");
                    break;
                case ROADBUILDING:
                    ((ObjectNode) msg).put("Entwicklungskarte", "Straßenbau");
                    break;
                case VICTORYPOINT:
                    ((ObjectNode) msg).put("Entwicklungskarte", "Siegpunkt");
                    break;
                case INVENTION:
                    ((ObjectNode) msg).put("Entwicklungskarte", "Erfindung");
                    break;
            }
        } else {
            ((ObjectNode) msg).put("Entwicklungskarte", "Unbekannt");
        }
        ((ObjectNode) root).put("Entwicklungskarte gekauft", msg);
        return root;

    }
    /*-----------------TRADE----------------------------*/

    /**
     * sends a see trade request to server
     *
     * @param give resources to give
     * @param get  resource to get
     * @return JsonNode
     */
    public static Object seaTrade(int[] give, int[] get) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Angebot", String.valueOf(resources(give)));
        ((ObjectNode) msg).put("Nachfrage", String.valueOf(resources(get)));
        ((ObjectNode) root).put("Seehandel", msg);
        return root;
    }

    /**
     * sends a player trade request to server
     *
     * @param give resources to give
     * @param get  resource to get
     * @return JsonNode
     */
    public static Object trade(int[] give, int[] get) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Angebot", String.valueOf(resources(give)));
        ((ObjectNode) msg).put("Nachfrage", String.valueOf(resources(get)));
        ((ObjectNode) root).put("Handel anbieten", msg);
        return root;
    }


    /**
     * used to send trade accept to server
     *
     * @param index
     * @return JsonNode
     */
    public static Object tradeAccept(int index) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Handel id", index);
        ((ObjectNode) msg).put("Annehmen", true);
        ((ObjectNode) root).put("Handel annehmen", msg);
        return root;
    }


    /**
     * used to send trade decline to server
     *
     * @param index
     * @return JsonNode
     */
    public static Object noTradeAccept(int index) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Handel id", index);
        ((ObjectNode) msg).put("Annehmen", false);
        ((ObjectNode) root).put("Handel annehmen", msg);
        return root;
    }


    /**
     * used to send trade decline to server, after it was already accepted before
     *
     * @param index
     * @return JsonNode
     */
    public static Object tradeDecline(int index) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Handel id", index);
        ((ObjectNode) root).put("Handel abbrechen", msg);
        return root;
    }


    /**
     * used to send trade decline to server, after it was already accepted before
     *
     * @param index
     * @return JsonNode
     */
    public static Object tradeDecline2(int id, int index) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Spieler", id);
        ((ObjectNode) msg).put("Handel id", index);
        ((ObjectNode) root).put("Handelsangebot zurückgezogen", msg);
        return root;
    }


    /**
     * used to finally accept and do a trade
     *
     * @param tradeId
     * @return JsonNode
     */
    public static Object doTrade(int tradeId, int player) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Handel id", tradeId);
        ((ObjectNode) msg).put("Mitspieler", player);
        ((ObjectNode) root).put("Handel abschließen", msg);
        return root;
    }
    /**
     * Translates the start request to a JsonNode
     *
     * @return JsonNode
     */
    public static Object start() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();

        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Soiel starten", msg);
        return root;
    }
}

