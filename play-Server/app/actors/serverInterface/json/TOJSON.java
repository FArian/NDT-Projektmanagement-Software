package actors.serverInterface.json;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by F.Arian on 06.11.17
 */
public class TOJSON {


    public static Object sendLocalHostAdress(String hostIP) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.createObjectNode();
        ((ObjectNode) root).put("HostAddress", hostIP);
        return root;
    }


}
