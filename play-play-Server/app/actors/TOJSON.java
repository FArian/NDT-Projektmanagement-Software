package actors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.libs.Json;
import scala.util.parsing.json.JSONArray;

import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by F.Arian on 20.06.17.
 */
public class TOJSON {


    public static Object sendLocalHostAdress(String hostIP) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("HostAddress", hostIP);
        return root;
    }


    public static ObjectNode childNode(JsonNode json, String jsonKey, String jsonValue) {

        return ((ObjectNode) json).put(jsonKey, jsonValue);
    }

    public JsonNode rootNode(JsonNode json, String jsonKey, JsonNode childNode) {

        return ((ObjectNode) json).put(jsonKey, childNode);
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

    //---------------------------Server Messages------------------------

    public static Object sendVersion() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Version", "...");
        ((ObjectNode) msg).put("Protokoll", "0.1");
        ((ObjectNode) root).put("Hallo", msg);
        return root;
    }

    /**
     * Translates the id the server wants to send to a client
     *
     * @param id
     * @return id
     */
    public static Object sendId(int id) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();

        ((ObjectNode) msg).put("id", id);
        ((ObjectNode) root).put("Willkommen", msg);
        return root;
    }

    public static Object wrongProtocol() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();

        ((ObjectNode) root).put("Serverantwort", "Diese Protokollversion wird nicht unterstützt!").toString();
        return root;
    }

    /**
     * Translates the welcome message for a new client to a JsonNode
     *
     * @param protocol
     * @return JsonNode
     */
    public static Object welcomClient(String protocol) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Version", "Gruppe2 Server 0.1");
        ((ObjectNode) msg).put("Protokoll", protocol);
        ((ObjectNode) root).put("Hallo", msg);
        return root;
    }

    public static Object okay() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Serverantwort", "OK");
        return root;
    }

    /**
     * Message that contains the String error
     *
     * @return JsonNode
     */
    public static Object jsonError() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode msg = mapper.createObjectNode();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) msg).put("Nachricht", "Kein valides Spielerobjekt");
        ((ObjectNode) root).put("Chatnachricht", msg);
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
        ((ObjectNode) playerJson).put("Größte Rittermacht", player.isHasKnighPower());
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
            ((ObjectNode) playerJson).put("Rohstoffe", valueOf(resources(player.getResAmountArray())));
            ((ObjectNode) playerJson).put("Entwicklungskarten", valueOf(developmentCards(player)));
        }
        ((ObjectNode) playerMsg).put("Spieler", playerJson);
        ((ObjectNode) root).put("Statusupdate", playerMsg);

        return root;

    }

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
     * Translates the card of the game to a valid JsonNode
     *
     * @param landTiles
     * @param harbors
     * @param pos
     * @param rob
     * @return JsonNode
     */

    public static Object card(LandTile[] landTiles, HarberType[] harbors, Position[][] pos, Position rob) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode outer = mapper.createObjectNode();
        JsonNode outer2 = mapper.createObjectNode();
        ObjectNode card = mapper.createObjectNode();
        ObjectNode tiles = mapper.createObjectNode();
        ObjectNode harbor = mapper.createObjectNode();
        JsonNode buildings = mapper.createObjectNode();
        ObjectNode robber = mapper.createObjectNode();
        ObjectNode temp = mapper.createObjectNode();

        for (int i = 0; i < 37; i++) {
            tiles.putObject(valueOf(valueOf(landTiles(landTiles[i]))));

        }
        for (int i = 0; i < 9; i++) {
            harbor.putObject(valueOf(valueOf(harbor(harbors[i], pos[i]))));
        }
        robber.put("x", rob.getX());
        robber.put("y", rob.getY());
        card.put("Felder", tiles);
        card.put("Häfen", harbor);
        card.put("Gebäude", buildings);
        card.put("Räuber", robber);
        outer.put("Karte", card);
        ((ObjectNode) outer2).put("Spiel gestartet", outer);

        return outer2;

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
            ((ObjectNode) harbors).put("Ort", valueOf(positionJson(pos[i])));
            if (harbors.equals(harbor.GENERIC)) {
                ((ObjectNode) harbors).put("Type", "Hafen");
            }
            if (harbors.equals(harbor.Wolle)) {
                ((ObjectNode) harbors).put("Type", "Wolle Hafen");
            }
            if (harbors.equals(harbor.Lehm)) {
                ((ObjectNode) harbors).put("Type", "Lehm Hafen");
            }
            if (harbors.equals(harbor.Holz)) {
                ((ObjectNode) harbors).put("Type", "Holz Hafen");
            }
            if (harbors.equals(harbor.Erz)) {
                ((ObjectNode) harbors).put("Type", "Erz Hafen");
            }
            if (harbors.equals(harbor.GENERIC)) {
                ((ObjectNode) harbors).put("Type", "Getreide Hafen");
            }
        }
        return harbors;
    }

    /**
     * Translate the land of the game to a JSONNode
     */
    public static Object landTiles(LandTile landTile) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tile = mapper.createObjectNode();
        ((ObjectNode) tile).put("Ort", valueOf(positionJson(landTile.getPosition())));
        Resource resource = landTile.getType();
        if (landTile.getType() != null) {
            if (resource.equals(Resource.Wolle)) {
                ((ObjectNode) tile).put("Type", "Weideland");
            }
            if (resource.equals(Resource.Lehm)) {
                ((ObjectNode) tile).put("Type", "Hügelland");
            }
            if (resource.equals(Resource.Holz)) {
                ((ObjectNode) tile).put("Type", "Wald");
            }
            if (resource.equals(Resource.Erz)) {
                ((ObjectNode) tile).put("Type", "Gebirge");
            }
            if (resource.equals(Resource.Getreide)) {
                ((ObjectNode) tile).put("Type", "Ackerland");
            }
            if (resource.equals(Resource.Meer)) {
                ((ObjectNode) tile).put("Type", "Meer");
            } else {
                ((ObjectNode) tile).put("Type", "Wüste");
            }
            if (landTile.getType() != Resource.Meer && landTile.getType() != null) {
                ((ObjectNode) tile).put("Zahl", landTile.getChipNr());
            }
        }

        return tile;
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
        ((ObjectNode) msg).put("Wurf", (Json.toJson(dices)));
        ((ObjectNode) root).put("Würfelwurf", msg);
        return root;
    }

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
            ((ObjectNode) msg).put("Position", valueOf(positionJson(pos[i])));
        }
        return msg;
    }

    /**
     * Translates the chat message to a JsonNode . Can contain a player. When
     * no player should be send, please enter 0 as parameter for the id.
     *
     * @param id
     * @param message
     * @return JsonNode
     */
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

    /**
     * Translates a resource to a string (german)
     *
     * @param resource
     * @return String
     */
    public static String translateRes(Resource resource) {
        String res = "";
        switch (resource) {
            case Lehm:
                res = "Lehm";
                break;
            case Getreide:
                res = "Getreide";
                break;
            case Holz:
                res = "Holz";
                break;
            case Erz:
                res = "Erz";
                break;
            case Meer:
                res = "Meer";
                break;
            case Wolle:
                res = "Wolle";
                break;
        }
        return res;
    }

    /**
     * Message with the earnings for a player. Visible to all players.
     *
     * @param player
     * @param resources
     * @return JsonNode
     */
    public static Object earnings(Player player, int[] resources) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        JsonNode msg = mapper.createObjectNode();
        ((ObjectNode) msg).put("Spieler", player.getId());
        ((ObjectNode) msg).put("Rohstoffe", resources(resources).toString());
        ((ObjectNode) root).put("Ertrag", msg);
        return root;
    }

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


    /**
     * Message with a new building. Can be a street, settlement or city.
     *
     * @param id
     * @param type
     * @param pos
     * @return
     */
    public static Object building(int id, String type, Position[] pos) {
        ObjectMapper mapper = new ObjectMapper();
        JSONArray location = (JSONArray) positionJson(pos);
        JsonNode root = mapper.createObjectNode();
        JsonNode inner = mapper.createObjectNode();
        ObjectNode building = mapper.createObjectNode();
        building.put("Eigentümer", id);
        building.put("Typ", type);
        building.put("Ort", valueOf(location));
        ((ObjectNode) inner).put("Gebäude", building);
        ((ObjectNode) root).put("Bauvorgang", inner);
        return root;
    }

    /**
     * Message that a player has earned the longest street
     *
     * @param player
     * @return JsonNode
     */
    public static Object longestStreet(Player player) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode longestStreet = mapper.createObjectNode();
        ObjectNode root = mapper.createObjectNode();
        longestStreet.put("Spieler", player.getId());
        root.put("Längste Handelsstraße", longestStreet);
        return root;

    }

    public static Object longestStreetReset() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode msg = mapper.createObjectNode();
        ObjectNode root = mapper.createObjectNode();
        root.put("Längste Handelstraße", msg);
        return root;
    }

    /**
     * Message that a player has won the game
     *
     * @param player
     * @return JsonNode
     */
    public static Object sendWinner(Player player) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode winner = mapper.createObjectNode();
        ObjectNode root = mapper.createObjectNode();
        String winnerString = "Spieler " + player.getName() + " hat das Spiel gewonnen.";
        winner.put("Nachricht", winnerString);
        winner.put("Sieger", player.getId());
        root.put("Spiel beendet", winner);
        return root;
    }

    /**
     * Message with the costs for a player. Can be set unknown, when other
     * players should not get the type of the resources. Type can be "Dorf",
     * "Straße", "Stadt", "Entwicklungskarte". These costs can not be unknown!
     * In these cases there is no int[] resources needed. For type "Karten wegen
     * Räuber abgeben" and "Handel" there is a int[] resources needed! These
     * costs can be unknown.
     * Resourcen : getreide, wolle, erz, holz, lehm
     *
     * @param player
     * @param type
     * @param resources
     * @param unknown
     * @return JsonNode
     */

    public static Object costs(Player player, String type, int[] resources, boolean unknown) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode jsonRes = mapper.createObjectNode();
        ObjectNode costsJson = mapper.createObjectNode();
        costsJson.put("Spieler", player.getId());
        int[] res = new int[5];
        switch (type) {
            case "Dorf":
                res[0] = 1;
                res[1] = 1;
                res[2] = 0;
                res[3] = 1;
                res[4] = 1;
                costsJson.put("Rohstoffe", valueOf(resources(res)));
                break;
            case "Straße":
                res[0] = 0;
                res[1] = 0;
                res[2] = 0;
                res[3] = 1;
                res[4] = 1;
                costsJson.put("Rohstoffe", valueOf(resources(res)));
                break;
            case "Stadt":
                res[0] = 2;
                res[1] = 0;
                res[2] = 3;
                res[3] = 0;
                res[4] = 0;
                costsJson.put("Rohstoffe", valueOf(resources(res)));
                break;
            case "Entwicklungskarte":
                res[0] = 1;
                res[1] = 1;
                res[2] = 1;
                res[3] = 0;
                res[4] = 0;
                costsJson.put("Rohstoffe", valueOf(resources(res)));
                break;
            case "Karten wegen Räuber abgeben":
                jsonRes = (ObjectNode) resources(resources);
                if (unknown) {
                    costsJson.put("Rohstoffe", valueOf(unknownRes(FromJson.resAmountAll(jsonRes))));
                } else {
                    costsJson.put("Rohstoffe", jsonRes);
                }
                break;
            case "Handel":
                jsonRes = (ObjectNode) resources(resources);
                costsJson.put("Rohstoffe", jsonRes);
                break;
        }
        root.put("Kosten", costsJson);
        return root;
    }

    /**
     * Translates an amount of resources. Can be used if the type of the res
     * should be unknown.
     *
     * @param amount
     * @return
     */
    private static Object unknownRes(int amount) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("Unbekannt", amount);
        return root;

    }

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
        ((ObjectNode) msg).put("Ort", valueOf(positionJson(pos)));
        if (target != 0) {
            ((ObjectNode) msg).put("Ziel", target);
        }
        ((ObjectNode) root).put("Räuber versetzen", msg);
        return root;
    }

    /**
     * Message that the robber was moved to another position
     *
     * @param player
     * @param pos
     * @param target
     * @return JSONObject
     */
    public static Object robberSet(Player player, Position pos, int target) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode robberSet = mapper.createObjectNode();
        ObjectNode msg = mapper.createObjectNode();
        msg.put("Spieler", player.getId());
        JsonNode location = mapper.createObjectNode();
        ((ObjectNode) location).putObject(valueOf(positionJson(pos)));
        msg.put("Ort", location);
        if (target != -1) {
            msg.put("Ziel", target);
        }
        robberSet.put("Räuber versetzt", msg);
        return robberSet;

    }

    /**
     * Message for the costs that a player gained because someone stole a card.
     * Just visible for involved player
     *
     * @param unknown
     * @param targetPlayer
     * @param stolenCard
     * @return JsonNode
     */

    public static Object robberCost(boolean unknown, Player targetPlayer, Resource stolenCard) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode costs = mapper.createObjectNode();
        costs.put("Spieler", targetPlayer.getId());
        if (unknown) {
            costs.put("Rohstoffe", valueOf(unknownRes(1)));
        } else {
            costs.put("Rohstoffe", valueOf(resources(stolenCard)));
        }
        return root;

    }

    /**
     * Translates one specific resource to a resources JSONObject
     *
     * @param stolenCard
     * @return JsonNode
     */
    public static Object resources(Resource stolenCard) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resources = mapper.createObjectNode();
        if (stolenCard == Resource.Holz) {
            resources.put("Holz", 1);
        }
        if (stolenCard == Resource.Lehm) {
            resources.put("Lehm", 1);
        }
        if (stolenCard == Resource.Wolle) {
            resources.put("Wolle", 1);
        }
        if (stolenCard == Resource.Getreide) {
            resources.put("Getreide", 1);
        }
        if (stolenCard == Resource.Erz) {
            resources.put("Erz", 1);
        }
        return resources;
    }

    /**
     * Message for the earnings that a player gained because he stole a card.
     * Just visible for involved player
     *
     * @param unknown
     * @param robber
     * @param stolenCard
     * @return JsonNode
     */
    public static Object robberEarning(boolean unknown, Player robber, Resource stolenCard) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode earnings = mapper.createObjectNode();
        earnings.put("Spieler", robber.getId());
        if (unknown) {
            earnings.put("Rohstoffe", valueOf(unknownRes(1)));
        } else {
            earnings.put("Rohstoffe", valueOf(resources(stolenCard)));
        }
        root.put("Ertrag", earnings);

        return root;
    }

    /**
     * Message that a player has used a knight card
     *
     * @param player
     * @param pos
     * @param target
     * @return JsonNode
     */
    public static Object knightPlayed(Player player, Position pos, int target) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode location = mapper.createObjectNode();
        ObjectNode knightPlayed = mapper.createObjectNode();
        knightPlayed.put("Spieler", player.getId());
        location.putObject(valueOf(positionJson(pos)));
        knightPlayed.put("Ort", location);
        if (target != -1) {
            knightPlayed.put("Ziel", target);
        }
        root.put("Ritter ausspielen", knightPlayed);
        return root;

    }

    /**
     * Message that a player has earned the largest army
     *
     * @param player
     * @return JsonNode
     */
    public static Object knightPower(Player player) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode knightPower = mapper.createObjectNode();
        knightPower.put("Spieler", player.getId());
        root.put("Größte Rittermacht", knightPower);
        return root;
    }

    /**
     * Translates the message that a player wants to use a roadbuilding card
     *
     * @param player
     * @param streets
     * @return JsonNode
     */
    public static Object roadBuilding(Player player, List<Position[]> streets) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode inner = mapper.createObjectNode();
        JsonNode streetOne = mapper.createArrayNode();
        JsonNode streetTwo = mapper.createArrayNode();
        inner.put("Spieler", player.getId());
        ((ObjectNode) streetOne).putObject(valueOf(positionJson(streets.get(0))));
        inner.put("Straße 1", streetOne);
        if (streets.size() > 1) {
            ((ObjectNode) streetTwo).putObject(valueOf(positionJson(streets.get(1))));
            inner.put("Straße 2", streetTwo);
        }

        root.put("Straßenbaukarte ausspielen", inner);
        return root;

    }

    /**
     * Message that a player has used a monopoly card
     *
     * @param player
     * @param type
     * @return JsonNode
     */
    public static Object monopolyCard(Player player, String type) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode monopoly = mapper.createObjectNode();
        monopoly.put("Spieler", player.getId());
        monopoly.put("Rohstoff", type);
        root.put("Monopol", monopoly);
        return root;

    }

    /**
     * Translates the cost of a monopoly card
     *
     * @param opponent   player
     * @param type
     * @param costAmount
     * @return JsonNode
     */
    public static Object monopolyCosts(Player opponent, String type, int costAmount) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode cost = mapper.createObjectNode();
        ObjectNode resource = mapper.createObjectNode();
        cost.put("Spieler", opponent.getId());
        resource.put(type, costAmount);
        cost.put("Rohstoffe", resource);
        root.put("Kosten", cost);

        return root;
    }

    /**
     * Translated the earning from a monopoly card
     *
     * @param player
     * @param type
     * @param earningA
     * @return JsonNode
     */
    public static Object monopolyEarning(Player player, String type, int earningA) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode earning = mapper.createObjectNode();
        ObjectNode resource = mapper.createObjectNode();
        earning.put("Spieler", player.getId());
        resource.put(type, earningA);
        root.put("Ertrag", earning);
        return root;

    }

    /**
     * Message from the server that a player has used an invention card
     *
     * @param player
     * @param resources
     * @return JsonNode
     */
    public static Object inventionCard(Player player, int[] resources) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode invention = mapper.createObjectNode();
        invention.put("Spieler", player.getId());
        invention.put("Rohstoffe", valueOf(resources(resources)));
        root.put("Erfindung", invention);
        return root;

    }

    public static Object tradeRequest(Player player, int[] give, int[] get, int tradeIndex) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        ObjectNode inner = mapper.createObjectNode();
        inner.put("Handel id", tradeIndex);
        inner.put("Spieler", player.getId());
        inner.put("Angebot", valueOf(resources(give)));
        inner.put("Nachfrage", valueOf(resources(get)));
        root.put("Handelsangebot", inner);
        return root;

    }

    public static Object tradeAcceptServer(Player player, int index, boolean bool) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode tradeAccept = mapper.createObjectNode();
        ObjectNode inner = mapper.createObjectNode();
        inner.put("Handel id", index);
        inner.put("Mitspieler", player.getId());
        inner.put("Annehmen", bool);
        tradeAccept.put("Handelsangebot angenommen", inner);

        return tradeAccept;

    }

    public static Object doTrade(Player player, int id) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode tradeAccept = mapper.createObjectNode();
        ObjectNode inner = mapper.createObjectNode();
        inner.put("Spieler", player.getId());
        inner.put("Mitspieler", id);
        tradeAccept.put("Handel ausgeführt", inner);
        return tradeAccept;

    }

    /**
     * used to send trade decline to server, after it was already accepted before
     *
     * @param index
     * @return
     */
    public static Object tradeDecline2(int id, int index) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode tradeAccept = mapper.createObjectNode();
        ObjectNode inner = mapper.createObjectNode();
        inner.put("Spieler", id);
        inner.put("Handel id", index);
        tradeAccept.put("Handelsangebot zurückgezogen", inner);
        return tradeAccept;
    }
}
