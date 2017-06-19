import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.xml.internal.bind.v2.util.QNameMap;
import jdk.internal.org.objectweb.asm.TypeReference;
import jdk.nashorn.internal.ir.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;

import static java.awt.SystemColor.info;
import static spark.Spark.*;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.*;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import sun.util.resources.cldr.st.CurrencyNames_st;

import static org.elasticsearch.common.xcontent.XContentFactory.*;

/**
 * Created by slan on 6/8/2017.
 */
public class trial {

    public static void main(String args[]) throws Exception {

        Settings settings = Settings.builder()
                .put("cluster.name", "Oorah").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("http://oorah-admire04:9200"), 9200));



        IndexResponse response = client.prepareIndex("newindex", "logs")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("hits", "hits")
                      //  .field("postDate", new Date())
                      //  .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();

        String _index = response.getIndex();
        String _type = response.getType();
        String _id = response.getId();
        long _version = response.getVersion();
        RestStatus status = response.status();

       // GetResponse getResponse = client.prepareGet("newindex", "logs", "AVvAqdx88HYEMTt5PRbN").get();

        SearchResponse searchResponse = client.prepareSearch("newindex")
                .setTypes("logs")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("KadonID", "838383"))                 // Query
               // .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        System.out.println(searchResponse);

client.close();

        Scanner userInput = new Scanner(System.in);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            System.out.println("Please enter a donation id");
            int id = userInput.nextInt();


            HttpGet httpget = new HttpGet("http://oorah-admire04:9200/newindex/_search?q=KadonID:" + id + "");
            //HttpGet httpget = new HttpGet("http://oorah-admire04:9200/newindex/_source/_search?stored_fields=KadonID?q=KadonID" + id+ "");

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println(responseBody);
       //     SearchResponse firstResponse = responseBody.prepareSearch().execute().actionGet();

            final JsonNode arrNode = new ObjectMapper().readTree(responseBody).get("hits").get("hits");



            System.out.println(arrNode);







            if (arrNode.isArray()) {
                for (final JsonNode objNode : arrNode) {
                    final Iterable<JsonNode> itereable = () -> objNode.get("_source").elements();
                    itereable.forEach(elem -> System.out.println(elem.asText()));
                    System.out.println("*********************************");

                    }


/*
                            if (objNode.get("_source").get("@timestamp") != null){
                               String Gettext = String.valueOf(objNode.get("_source").get("@timestamp"));
                               Date date = formatDate(Gettext);
                               System.out.println(date);
                            }
                            if (objNode.get("_source").get("KadonID") != null){
                                System.out.println("KadonId: " + objNode.get("_source").get("KadonID").asText());
                            }
                            if (objNode.get("_source").get("EventName") != null){
                                System.out.println("Event Name: " + objNode.get("_source").get("EventName").asText());
                            }
                            if (objNode.get("_source").get("Stage") != null){
                                System.out.println("Stage: " + objNode.get("_source").get("Stage").asText());
                            }
                            if (objNode.get("_source").get("OldValue") != null) {
                                System.out.println("Old Value: " + objNode.get("_source").get("OldValue").asText());
                            }
                            if (objNode.get("_source").get("NewValue") != null){
                                System.out.println("New Value: " + objNode.get("_source").get("NewValue").asText());
                            }
                            if (objNode.get("_source").get("CarType") != null) {
                                System.out.println("Car Type: " + objNode.get("_source").get("CarType").asText());
                            }

                            System.out.println("***************************************");
                            */
                        }





        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Date formatDate(String date){
Date response = new Date();
        try{

            String finaltext = date.substring(1);
            String last = finaltext.replace("T"," ");
            String really = last.replace("Z", " ");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            response = formatter.parse(really);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
return response;
    }

    public static String sortDate(String json){


        return "";
    }
}
