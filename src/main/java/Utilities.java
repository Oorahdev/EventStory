import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

public class Utilities {


    public static JsonNode mapJsonToMessage(JSONObject bytes) {
        Donation newDonationEvent = null;

        ObjectMapper mapper = new ObjectMapper();

        JsonFactory factory = mapper.getFactory();
        JsonParser parser = null;
        JsonNode node = null;
        try {
            parser = factory.createParser(String.valueOf(bytes));
            node = mapper.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;

    }

    public static JsonNode getNodefromString(String json){


        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = null;
        JsonNode node = null;
        try {
            parser = factory.createParser(json);
             node = mapper.readTree(parser);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return node;

    }






    public static String toPascalCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}