package rzeznika.ndtClient.network;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import rzeznika.ndtClient.model.DevelopmentCard;
import rzeznika.ndtClient.model.Position;
import rzeznika.ndtClient.model.Resource;

/**
 * Created by F.Arian on 02.07.17.
 */

public class FROMJSON {
    /**
     * Extracts information from a JsonNode and returns the information result
     *
     * @param json
     * @return pos [0] = player, pos [1]=dice1 and pos [2]=dice2
     */
    public static int[] dices(JsonNode json) {
        int[] result = new int[3];
        JsonNode dices = json.get("Würfelwurf");
        String dice = dices.path("Wurf").textValue();
        int playerId = dices.path("Spieler").intValue();
        result[0] = playerId;
        result[1] = Integer.parseInt("" + dice.charAt(1));
        result[2] = Integer.parseInt("" + dice.charAt(2));
        return result;
    }

    /**
     * welcomes a player
     *
     * @param json json object
     * @return id of the new player
     */
    public static int welcomeToPlayer(JsonNode json) {
        JsonNode welcome = json.get("Willkommen");
        int playerID = welcome.get("id").intValue();
        return playerID;

    }


    /**
     * Translates The card object from the server to an String[][]
     * card[0]  = landtypes;
     * card[1]  = landnumbers;
     * card[2]  = harbortypes;
     * card[3]  = harborlocationsSea1;
     * card[4]  = harborlocationsLand1;
     * card[5]  = landpos1;
     * card[6]  = landpos2;
     * card[7]  = seapos1;
     * card[8]  = seapos2;
     * card[9]  = harborlocationsSea2;
     * card[10] = harborlocationsLand2;
     * ----------------------------------
     *
     * @param json
     * @return card [?][]
     */


    public static String[][] giveCard(JsonNode json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode landArray = mapper.createArrayNode();
        JsonNode harborArray = mapper.createArrayNode();
        JsonNode location = mapper.createArrayNode();

        String[][] card = new String[11][];
        ArrayList<String> landtypesList = new ArrayList<String>();
        ArrayList<String> landPosList1 = new ArrayList<String>();
        ArrayList<String> seaPosList1 = new ArrayList<String>();
        ArrayList<String> landPosList2 = new ArrayList<String>();
        ArrayList<String> seaPosList2 = new ArrayList<String>();
        ArrayList<Integer> landnumbersList = new ArrayList<Integer>();
        String[] seapos2 = new String[18];
        String[] landpos2 = new String[19];
        String[] seapos1 = new String[18];
        String[] landpos1 = new String[19];
        String[] landtypes = new String[19];
        String[] landnumbers = new String[19];
        String[] harbortypes = new String[9];
        String[] harborlocationsSea1 = new String[9];
        String[] harborlocationsSea2 = new String[9];
        String[] harborlocationsLand1 = new String[9];
        String[] harborlocationsLand2 = new String[9];
        // Felder Array in local JsonNode Array
        landArray = json.get("Spiel gestartet").get("Karte").withArray("Felder");
        //------------------------------------------------------------------------------------------
        for (int i = 0; i < 37; i++) {
            JsonNode tile = landArray.get(i);
            int x = tile.get("Ort").get("x").intValue();
            int y = tile.get("Ort").get("y").intValue();
            if ((y == 2 && x > -3 && x < 1) ||
                    (y == 1 && x > -3 && x < 2) ||
                    (y == 0 && x > -3 && x < 3) ||
                    (y == -1 && x > -2 && x < 3) ||
                    (y == -2 && x > -1 && x < 3)) {
                landtypesList.add(landArray.get(i).asText("Type"));
                landPosList1.add(x + "");
                landPosList2.add(y + "");
                if (!landArray.get(i).get("Typ").textValue().equals("Wüste")) {
                    landnumbersList.add(landArray.get(i).get("Zahl").intValue());
                }

            } else {
                seaPosList1.add(x + "");
                seaPosList2.add(y + "");
            }
        }
        //------------------------------------------------------------------------------------------
        for (int i = 0; i < 19; i++) {
            landpos1[i] = landPosList1.remove(0);
            landpos2[i] = landPosList2.remove(0);
            landtypes[i] = landtypesList.remove(0);
            if (!landtypes[i].equals("Wüste")) {
                landnumbers[i] = landnumbersList.remove(0).toString();
            }

        }
        for (int i = 0; i < 18; i++) {
            seapos1[i] = seaPosList1.remove(0);
            seapos2[i] = seaPosList2.remove(0);
        }
        harborArray = json.get("Spiel gestartet").get("Karte").withArray("Häfen");
        for (int i = 0; i < 9; i++) {
            JsonNode harbor = harborArray.get(i);
            harbortypes[i] = harbor.get("Typ").textValue();
            location = harbor.withArray("Ort");
            String sea1 = "" + location.get(0).get("x").intValue();
            String sea2 = "" + location.get(0).get("y").intValue();
            String land1 = "" + location.get(1).get("x").intValue();
            String land2 = "" + location.get(1).get("y").intValue();
            harborlocationsSea1[i] = sea1;
            harborlocationsSea2[i] = sea2;
            harborlocationsLand1[i] = land1;
            harborlocationsLand2[i] = land2;
        }
        card[0] = landtypes;
        card[1] = landnumbers;
        card[2] = harbortypes;
        card[3] = harborlocationsSea1;
        card[4] = harborlocationsLand1;
        card[5] = landpos1;
        card[6] = landpos2;
        card[7] = seapos1;
        card[8] = seapos2;
        card[9] = harborlocationsSea2;
        card[10] = harborlocationsLand2;
        return card;
    }

    /**
     * Translates a building object to a Position[]
     *
     * @param json
     * @return Position[]
     */
    public static Position[] building(JsonNode json) {
        Position[] pos = null;
        if (json.get("Typ").textValue().equals("Straße")) {
            pos = new Position[2];
        } else {
            pos = new Position[3];
        }
        for (int i = 0; i < pos.length; i++) {
            JsonNode help = json.withArray("Ort").get(i);
            pos[i] = new Position(help.get("x").intValue(), help.get("y").intValue());
        }
        return pos;
    }

    /**
     * Translates a building object to a position[]
     *
     * @param json
     * @return Position[]
     */
    public static Position[] buildingP(JsonNode json) {
        Position[] pos = null;

        if (json.get("Bauvorgang").get("Gebäude").get("Typ").textValue().equals("Straße")) {
            pos = new Position[2];
        } else {
            pos = new Position[3];
        }
        for (int i = 0; i < pos.length; i++) {
            JsonNode help = json.get("Bauvorgang").get("Gebäude").withArray("Ort")
                    .get(i);
            pos[i] = new Position(help.get("x").intValue(), help.get("y").intValue());
        }
        return pos;

    }

    /**
     * Translates a building object to a string
     *
     * @param json
     * @return result in pos[0]= OwnerID and in pos[1]=type
     */
    public static String[] buildingS(JsonNode json) {
        String[] result = new String[2];
        JsonNode msg = json.get("Bauvorgang");
        JsonNode building = msg.get("Gebäude");
        String OwnerID = String.valueOf(building.get("Eigentümer"));
        //String id2= building.get("Eigentümer").textValue();
        String type = building.get("Typ").textValue();
        result[0] = OwnerID;
        result[1] = type;
        return result;
    }

    /**
     * Translates a building object to a position[]
     *
     * @param json
     * @return Position[]
     */

    public static Position[] buildingAtPosition(JsonNode json) {
        Position[] positions = null;
        if (json.get("Bauvorgang").get("Gebäude").get("Typ").textValue().equals("Straße")) {
            //Position for Street
            positions = new Position[2];
        } else {
            //Position for City and Settlement
            positions = new Position[3];
        }
        for (int i = 0; i < positions.length; i++) {
            JsonNode jsonPos = json.get("Bauvorgang").get("Gebäude").withArray("Ort").get(i);
            positions[i] = new Position(jsonPos.get("x").intValue(), jsonPos.get("y").intValue());
        }

        return positions;
    }
    /*------------------RESOURCES-----------------------------------*/

    /**
     * translates the JsonNode
     *
     * showing a players resources in a int array
     *
     * @param json
     * @return 0: grain 1: wool 2:ore 3:lumber 4: brick
     */
    public static int[] resources(JsonNode json) {
        int[] resources = new int[5];
        int grain = 0;
        int wool = 0;
        int ore = 0;
        int lumber = 0;
        int brick = 0;
        if (json.has("Getreide")) {
            grain = json.get("Getreide").intValue();
        }
        if (json.has("Wolle")) {
            wool = json.get("Wolle").intValue();
        }
        if (json.has("Erz")) {
            ore = json.get("Erz").intValue();
        }
        if (json.has("Holz")) {
            lumber = json.get("Holz").intValue();
        }
        if (json.has("Lehm")) {
            brick = json.get("Lehm").intValue();
        }

        resources[3] = lumber;
        resources[4] = brick;
        resources[1] = wool;
        resources[0] = grain;
        resources[2] = ore;
        return resources;
    }

    /**
     * gets chat messages
     *
     * @param json json object
     */
    public static String[] chatMessage(JsonNode json) {
        JsonNode chat = json.get("Chatnachricht");
        String absender = "";
        if (json.get("Chatnachricht").toString().contains("Absender")) {
            absender = String.valueOf(chat.get("Absender").intValue());
        } else {
            absender = "Server";
        }
        String nachricht = chat.get("Nachricht").textValue();
        String[] chatPack = new String[2];

        chatPack[0] = absender;
        chatPack[1] = nachricht;
        return chatPack;

    }

    /**
     * @param json object
     * @return "Farbe bereits vergeben"
     */
    public static String errorMessage(JsonNode json) {
        JsonNode error = json.get("Fehler");
        String farbErros = error.get("Meldung").textValue();
        return farbErros;
    }


    /**
     * Extracts information from a JsonNode and returns the information packed
     * in a ArrayList with Resources.
     *
     * @param res Resource
     * @return ArrayList<Resources> list of resources
     */
    public static ArrayList<Resource> getStuff(JsonNode res) {
        ArrayList<Resource> resources = new ArrayList<Resource>();
        if (res.has("Holz")) {
            for (int i = 0; i < res.path("Holz").intValue(); i++) { //unsicher TODO
                resources.add(Resource.LUMBER);
            }
        }
        if (res.has("Lehm")) {
            for (int i = 0; i < res.path("Lehm").intValue(); i++) {
                resources.add(Resource.BRICK);
            }

        }
        if (res.has("Wolle")) {
            for (int i = 0; i < res.path("Wolle").intValue(); i++) {
                resources.add(Resource.WOOL);
            }

        }
        if (res.has("Getreide")) {
            for (int i = 0; i < res.path("Getreide").intValue(); i++) {
                resources.add(Resource.GRAIN);
            }

        }
        if (res.has("Erz")) {
            for (int i = 0; i < res.path("Erz").intValue(); i++) {
                resources.add(Resource.ORE);
            }

        }
        return resources;
    }


/*------------------DEVELOPMENT-------------------*/

    /**
     * translates the JsonNode showing players developmentcards in a int
     * array
     *
     * @param json
     * @return 0: grain 1: wool 2:ore 3:lumber 4: brick
     */

    public static int[] developmentCards(JsonNode json) {
        int[] cards = new int[5];
        int knight = 0;
        int street = 0;
        int monopol = 0;
        int invention = 0;
        int victorypoints = 0;
        if (json.has("Ritter")) {
            knight = json.get("Ritter").intValue();
        }
        if (json.has("Straßenbau")) {
            street = json.get("Straßenbau").intValue();
        }
        if (json.has("Monopol")) {
            monopol = json.get("Monopol").intValue();
        }
        if (json.has("Erfindung")) {
            invention = json.get("Erfindung").intValue();
        }
        if (json.has("Siegpunkt")) {
            victorypoints = json.get("Siegpunkt").intValue();
        }
        cards[0] = knight;
        cards[1] = street;
        cards[2] = monopol;
        cards[3] = invention;
        cards[4] = victorypoints;
        return cards;

    }

    /**
     * returns a String naming the newly purchased developmentcard
     *
     * @param json
     * @return new Developmentcard
     */

    public static DevelopmentCard getDevelopmentCard(JsonNode json) {
        DevelopmentCard newCard = null;
        String card = json.get("Entwicklungskarte").textValue();
        if (card.equals("Ritter")) {
            newCard = DevelopmentCard.KNIGHT;
        }
        if (card.equals("Straßenbau")) {
            newCard = DevelopmentCard.ROADBUILDING;
        }
        if (card.equals("Monopol")) {
            newCard = DevelopmentCard.MONOPOLY;
        }
        if (card.equals("Erfindung")) {
            newCard = DevelopmentCard.INVENTION;
        }
        if (card.equals("Siegpunkt")) {
            newCard = DevelopmentCard.VICTORYPOINT;
        }
        return newCard;
    }

    /**
     * Used to translate the JsonNode the server sends after a knight is used
     * to a int array
     *
     * @param json
     * @return 0:new Tile 1: id of robbed player 2:Player who played the knight
     */

    public static int[] useKnight(JsonNode json) {
        int[] informations = new int[4];
        int tileX = json.path("Ritter ausspielen").path("Ort").get("x").intValue(); //unsicher!
        int tileY = json.path("Ritter ausspielen").path("Ort").get("y").intValue();
        int target = json.path("Ritter ausspielen").get("Ziel").intValue();
        int player = json.path("Ritter ausspielen").get("Spieler").intValue();

        informations[0] = tileX;
        informations[1] = tileY;
        informations[2] = target;
        informations[3] = player;
        return informations;
    }

    /**
     * used to Translate the JsonNode the server sends to tell that a
     * Streetbuilding card is used
     *
     * @param json
     * @return 0: player who used the card saved as x, 1:location of street1 2:
     * (may be null) location of street2
     */

    public static Position[] buildStreets(JsonNode json) {
        Position[] informations = new Position[5];
        informations[0] = new Position(json.get("Spieler").intValue(), 0);
        informations[1] = new Position(json.withArray("Straße 1").get(0).get("x").intValue(),
                json.withArray("Straße 1").get(0).get("y").intValue());
        informations[2] = new Position(json.withArray("Straße 1").get(1).get("x").intValue(),
                json.withArray("Straße 1").get(1).get("y").intValue());
        if (json.has("Straße 2")) {
            informations[3] = new Position(json.withArray("Straße 2").get(0).get("x").intValue(),
                    json.withArray("Straße 2").get(0).get("y").intValue());
            informations[4] = new Position(json.withArray("Straße 2").get(1).get("x").intValue(),
                    json.withArray("Straße 2").get(1).get("y").intValue());
        }
        return informations;
    }

    /**
     * Translates a roadbuilding object to a List Position[]
     *
     * @param json json object
     * @return List Position[] list of street positions
     */
    public static List<Position[]> getRoadBuilding(JsonNode json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonStreet1 = mapper.createArrayNode();
        JsonNode jsonStreet2 = mapper.createArrayNode();
        List<Position[]> streets = new ArrayList<Position[]>();
        Position[] streetOne = new Position[2];
        jsonStreet1 = json.withArray("Straße 1");
        streetOne[0] = new Position(jsonStreet1.get(0).get("x").intValue(), jsonStreet1.get(0).get("y").intValue());
        streetOne[1] = new Position(jsonStreet1.get(1).get("x").intValue(), jsonStreet1.get(1).get("y").intValue());
        streets.add(streetOne);
        if (json.has("Straße 2")) {
            Position[] streetTwo = new Position[2];
            jsonStreet2 = json.withArray("Straße 2");
            streetTwo[0] = new Position(jsonStreet2.get(0).get("x").intValue(), jsonStreet2.get(0).get("y").intValue());
            streetTwo[1] = new Position(jsonStreet2.get(1).get("x").intValue(), jsonStreet2.get(1).get("y").intValue());
            streets.add(streetTwo);

        }

        return streets;
    }

    /**
     * Translates the monopol object to a String.  gives the resource type that
     * the player chose
     *
     * @param json json object
     * @return String resource
     */
    public static String monopol(JsonNode json) {
        return json.get("Rohstoff").textValue();
    }

    /*------------------ROBBER-------------------*/

	/*-----------------SPECIAL CARDS----------------------------*/


    /**
     * reads out who gets the longest street card
     *
     * @param json json object
     * @return player ID id of the player with the longest street
     */
    public static int longestStreet(JsonNode json) {
        int index = 0;
        if (json.has("Spieler")) {
            index = json.get("Spieler").intValue();
        }
        return index;
    }

    /**
     * reads out who gets the knightpower card
     *
     * @param json json object
     * @return player ID id of the player with the biggest knightpower
     */
    public static int knightpower(JsonNode json) {
        int index = 0;
        index = json.get("Spieler").intValue();
        return index;
    }

    /*------------------TRADE-------------------*/

    /**
     * gets the accepting players id out of a trade accept
     *
     * @param json json object
     * @return player id id of the accepting player
     */
    public static int tradeAccepted(JsonNode json) {
        return json.get("Mitspieler").intValue();
    }

    /**
     * shows if a trade is accepted
     *
     * @param json json object
     * @return boolean true if trade is accepted
     */
    public static boolean tradeAccepted2(JsonNode json) {
        return json.get("Annehmen").booleanValue();
    }

    /**
     * used to give out and remove resources due to a trade
     *
     * @param json object
     * @return players wo trade
     */
    public static int[] tradeDone(JsonNode json) {
        int[] help = new int[2];
        help[0] = json.get("Spieler").intValue();
        help[1] = json.get("Mitspieler").intValue();
        return help;
    }

    /**
     * tells that a trade was declined
     *
     * @param json json object
     * @return players who trade
     */
    public static int[] tradeDecline(JsonNode json) {
        int[] help = new int[2];
        help[0] = json.get("Spieler").intValue();
        help[1] = json.get("Handel id").intValue();
        return help;
    }

    /**
     * used to read out a traderequest
     *
     * @param json json object
     * @return int[]: player who trades, the trade ID, the resources he gives and gets
     */
    public static int[][] tradeRequest(JsonNode json) {
        int[][] request = new int[4][];
        int[] player = new int[1];
        int[] tradeID = new int[1];
        int[] get = new int[5];
        int[] give = new int[5];
        player[0] = json.get("Spieler").intValue();
        tradeID[0] = json.get("Handel id").intValue();


        get = resources(json.path("Angebot"));
        give = resources(json.path("Nachfrage"));

        request[0] = player;
        request[1] = tradeID;
        request[2] = get;
        request[3] = give;
        return request;
    }
    /*-----------------------GAME END------------------------- */

    /**
     * @param json json object
     * @return Sting[] playerId and message
     */
    public static String[] winner(JsonNode json) {
        JsonNode wins = json.path("Spiel beendet"); //unsicher TODO
        String message = wins.get("Nachricht").textValue();
        int playerID = wins.get("Sieger").intValue();
        String[] result = new String[2];
        result[0] = message;
        result[1] = String.valueOf(playerID);
        return result;

    }

    /**
     * Translates a json object. Returns the amount of all the resources
     *
     * @param resources all resources
     * @return int amount of resources
     */
    public static int resAmountAll(JsonNode resources) {
        int amount = 0;
        if (resources.has("Holz")) {
            amount = amount + resources.get("Holz").intValue();
        }
        if (resources.has("Lehm")) {
            amount = amount + resources.get("Lehm").intValue();
        }
        if (resources.has("Wolle")) {
            amount = amount + resources.get("Wolle").intValue();
        }
        if (resources.has("Getreide")) {
            amount = amount + resources.get("Getreide").intValue();
        }
        if (resources.has("Erz")) {
            amount = amount + resources.get("Erz").intValue();
        }

        return amount;
    }


}











