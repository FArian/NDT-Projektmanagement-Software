package com.androidjson.firebasegooglelogin_androidjsoncom.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by F.Arian on 08.11.17.
 */

public class ToJson {
    /**
     * Mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Object helloServer() {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Message", "hello server here's client");
        ((ObjectNode) root).put("Hello", msg);
        return root;
    }

    /**
     * Translates the request and information to connect a Android Client with the server
     *
     * @return
     */
    public static Object clientConnection(String clientType) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Client", clientType);
        ((ObjectNode) root).put("Hello", msg);
        return root;
    }

    /**
     *
     * @param key
     * @param object
     * @return String Json Message
     */
    public static String sendObjectWithRoot(String key, Object object) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(object);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(key, jsonElement);
        return jsonObject.toString();
    }

    /**
     * Translates the request and information to connect a Android Client with the server
     *
     * @return
     */
    public static Object message(String key, String message) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Message", message);
        ((ObjectNode) root).put(key, msg);
        return root;
    }

    /**
     * Translates the request and information to connect a Android Client with the server
     *
     * @return
     */
    public static Object message(String key, String id, String message) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put(id, message);
        ((ObjectNode) root).put(key, msg);
        return root;
    }

    /**
     * boolean answer
     *
     * @return
     */
    public static Object booleanAnswer(String key, boolean answer) {
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) root).put(key, answer);
        return root;
    }

}
