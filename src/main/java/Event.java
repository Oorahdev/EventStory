
import java.util.*;

import java.util.List;

/**
 * Created by slan on 6/12/2017.
 */
public class Event implements Comparator<Event> {
    private Date dateTime;
    private int KadonId;
    private String EventName;

    public Event(Date dateTime, int kadonId, String eventName, String stage, String oldValue, String newValue, String carType) {
        this.dateTime = dateTime;
        KadonId = kadonId;
        EventName = eventName;
        Stage = stage;
        OldValue = oldValue;
        NewValue = newValue;
        CarType = carType;
    }

    private String Stage;
    private String OldValue;
    private String NewValue;
    private String CarType;

    public Event(Integer kadonId) {
        KadonId = kadonId;
    }

    public Event() {
    }

    public int getKadonId() {
        return KadonId;
    }

    public void setKadonId(int kadonId) {
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }



    @Override
    public int compare(Event one, Event two) {
            Date thisTime = one.getDateTime();
            Date anotherTime = two.getDateTime();
            return (thisTime.compareTo(anotherTime) > 0 ? -1 : (thisTime == anotherTime ? 0 : 1));
    }
}