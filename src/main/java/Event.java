import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.mortbay.util.ajax.JSON;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by slan on 6/12/2017.
 */
public abstract class Event implements Comparable {
    private Date dateTime;
    private String KadonId;
    private String EventName;
    private String Stage;
    private String OldValue;
    private String NewValue;
    private String CarType;

    public List<Event> getEventsList() {
        return EventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        EventsList = eventsList;
    }

    private List<Event> EventsList;

    public String getKadonId() {
        return KadonId;
    }

    public void setKadonId(String kadonId) {
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


/*
    //@Override
    public boolean sort(JsonNode one, JsonNode two){
        if (one.get("_source").get("@timestamp") == null || two.get("_source").get("@timestamp")== null)
            return false;
        return one.get("_source").get("@timestamp").equals(two.get("_source").get("@timestamp"));
    }
    */

}
