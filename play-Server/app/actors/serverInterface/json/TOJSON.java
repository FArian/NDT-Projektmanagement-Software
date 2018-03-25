package actors.serverInterface.json;

import actors.Lobby;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Personal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by F.Arian on 06.11.17
 */
public class TOJSON {


    /**
     * Mapper
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Object sendLocalHostAddress(String hostIP) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("HostAddress", hostIP);
        return root;
    }

    public static Object replayMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("Replay message", message);
        return root;
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
    public static Object message(String key, int n) {
        JsonNode msg = MAPPER.createObjectNode();
        JsonNode root = MAPPER.createObjectNode();
        ((ObjectNode) msg).put("Message", n);
        ((ObjectNode) root).put(key, msg);
        return root;
    }


    /**
     * boolean answer
     *
     * @return
     */
    public static Object booleanAnswer(String key, boolean answer) {
        ObjectNode root = MAPPER.createObjectNode();
        root.put(key, answer);
        return root;
    }

    /**
     * @param key
     * @param object
     * @return String Json Message
     */
    public static Object sendObjectWithKey(String key, Object object) {

        JsonNode root = MAPPER.createObjectNode();
        JsonNode node = MAPPER.convertValue(object, JsonNode.class);
        ((ObjectNode) root).put(key, node);
        return root;
    }

    /**
     *
     * @param objects
     * @return Json String ready to send
     */
    public static Object sendListObject(String key,List<Object> objects) {
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

        return null;
    }


}
