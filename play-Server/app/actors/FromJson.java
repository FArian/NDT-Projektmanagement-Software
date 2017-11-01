package actors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Position;
import models.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F.Arian on 09.07.17.
 */
public class FromJson {

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
            ObjectMapper mapper = new ObjectMapper();
            JsonNode helper = mapper.createArrayNode();
            helper.withArray("Ort").get(i);
            pos[i] = new Position(helper.get("x").intValue(), helper.get("y").intValue());
        }
        return pos;
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
            for (int i = 0; i < res.path("Holz").intValue(); i++) {
                resources.add(Resource.Holz);
            }
        }
        if (res.has("Lehm")) {
            for (int i = 0; i < res.path("Lehm").intValue(); i++) {
                resources.add(Resource.Lehm);
            }

        }
        if (res.has("Wolle")) {
            for (int i = 0; i < res.path("Wolle").intValue(); i++) {
                resources.add(Resource.Wolle);
            }

        }
        if (res.has("Getreide")) {
            for (int i = 0; i < res.path("Getreide").intValue(); i++) {
                resources.add(Resource.Getreide);
            }

        }
        if (res.has("Erz")) {
            for (int i = 0; i < res.path("Erz").intValue(); i++) {
                resources.add(Resource.Erz);
            }

        }
        return resources;
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
     * translates the JSONObject showing a players resources in a int array
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
        resources[0] = grain;
        resources[1] = wool;
        resources[2] = ore;
        resources[3] = lumber;
        resources[4] = brick;


        return resources;
    }
}
