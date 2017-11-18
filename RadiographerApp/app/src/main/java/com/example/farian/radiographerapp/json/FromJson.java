package com.example.farian.radiographerapp.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by F.Arian on 08.11.17.
 */

public class FromJson {


    /**
     * Mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * welcomes a client
     * @param json object
     * @return id of the new client
     */
    public static int welcomeClient(JsonNode json) {
        JsonNode welcome = json.get("Welcome");
        int radiographerID = welcome.get("id").intValue();
        return radiographerID;

    }
}
