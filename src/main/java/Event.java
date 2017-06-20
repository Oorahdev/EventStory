import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.mortbay.util.ajax.JSON;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.List;

/**
 * Created by slan on 6/12/2017.
 */
public class Event implements Comparator<Event> {
    private Date dateTime;
    private Integer KadonId;
    private String EventName;
    private String Stage;
    private String OldValue;
    private String NewValue;
    private String CarType;

    public Event(Integer kadonId) {
        KadonId = kadonId;
    }

    public List<Event> getEventsList() {
        return EventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        EventsList = eventsList;
    }

    public List<Event> EventsList;

    public Integer getKadonId() {
        return KadonId;
    }

    public void setKadonId(Integer kadonId) {
        KadonId = kadonId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getStage() {
        return Stage;
    }

    public void setStage(String stage) {
        Stage = stage;
    }

    public String getOldValue() {
        return OldValue;
    }

    public void setOldValue(String oldValue) {
        OldValue = oldValue;
    }

    public String getNewValue() {
        return NewValue;
    }

    public void setNewValue(String newValue) {
        NewValue = newValue;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public Date getDateTime(){
        return dateTime;
    }

    public void setDateTime(Date datetime){
        this.dateTime = datetime;
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

    public List<Event> event;

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



   @Override
    public int compare(Event one, Event two) {

     return one.getDateTime().compareTo(two.getDateTime());
    }


    /*
    public boolean sort(JsonNode one, JsonNode two){
        if (one.get("_source").get("@timestamp") == null || two.get("_source").get("@timestamp")== null)
            return false;
        return one.get("_source").get("@timestamp").equals(two.get("_source").get("@timestamp"));
    }

*/
}
