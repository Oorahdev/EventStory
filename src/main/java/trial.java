import com.fasterxml.jackson.databind.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.get;


/**
 * Created by slan on 6/8/2017.
 */
public class trial {

    public static void main(String args[]) throws Exception {

        List<JsonNode> list = new ArrayList<>();
        List<Event> eventList = new ArrayList<>();

        //Scanner userInput = new Scanner(System.in);

        CloseableHttpClient httpclient = HttpClients.createDefault();


            get("/event", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                String id =
                        req.queryParams("id");

           // System.out.println("Please enter a donation id");
          //  int id = userInput.nextInt();

               // try {
            HttpGet httpget = new HttpGet("http://oorah-admire04:9200/newindex/_search?q=KadonID:" + id + "");

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


            final JsonNode arrNode = new ObjectMapper().readTree(responseBody).get("hits").get("hits");

/*
                if (arrNode.isArray()) {
                    for (final JsonNode objNode : arrNode) {
                        final Iterable<JsonNode> itereable = () -> objNode.get("_source").elements();
                        itereable.forEach(elem ->  list.add(elem));
                      //  itereable.forEach(element -> event.getDateTime());
                        itereable.forEach(elems -> System.out.println(elems.asText()));
                        System.out.println("*********************************");
                    }

*/
                    if (arrNode.isArray()) {
                        for (final JsonNode objNode : arrNode) {

                            Event event = new Event(Integer.parseInt(id));
                            if (objNode.get("_source").get("@timestamp") != null) {
                                String Gettext = String.valueOf(objNode.get("_source").get("@timestamp"));
                                Date date = formatDate(Gettext);
                                event.setDateTime(date);
                             //   System.out.println(event.getDateTime());

                                if (objNode.get("_source").get("EventName") != null){
                                    String eventName = ("Event Name: " + objNode.get("_source").get("EventName").asText());
                                    event.setEventName(eventName);
                              //      System.out.println(event.getEventName());
                                }
                                eventList.add(event);
                            }

                     
                        }


                    System.out.println("Before Sort");
                    System.out.println("*********************************");

                    for (int i= 0; i<eventList.size(); i++){
                        System.out.println(eventList.get(i).getDateTime());
                        System.out.println(eventList.get(i).getEventName());
                    }



                    System.out.println("After sort");
                    System.out.println("*********************************");

                        for (int i= 0; i<eventList.size(); i++){
                            System.out.println(eventList.get(i).getDateTime());
                            System.out.println(eventList.get(i).getEventName());
                        }
                        }


        model.put("template","templates/event.vtl");
                return new ModelAndView(model, "templates/layout.vtl");

            },new VelocityTemplateEngine());


    }

    public static java.util.Date formatDate(String date) {
        Date response = new Date();
        try {

            String finaltext = date.substring(1);
            String last = finaltext.replace("T", " ");
            String really = last.replace("Z", " ");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            response = formatter.parse(really);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return response;
    }

}


