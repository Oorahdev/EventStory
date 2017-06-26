import java.util.Date;
import java.util.List;

public class EventBuilder {
    private Date dateTime;
    private Integer kadonId;
    private String eventName;
    private String stage;
    private String oldValue;
    private String newValue;
    private String carType;
    private List<Event> eventsList;
    private List<Event> event;

    public EventBuilder setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public EventBuilder setKadonId(Integer kadonId) {
        this.kadonId = kadonId;
        return this;
    }

    public EventBuilder setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventBuilder setStage(String stage) {
        this.stage = stage;
        return this;
    }

    public EventBuilder setOldValue(String oldValue) {
        this.oldValue = oldValue;
        return this;
    }

    public EventBuilder setNewValue(String newValue) {
        this.newValue = newValue;
        return this;
    }

    public EventBuilder setCarType(String carType) {
        this.carType = carType;
        return this;
    }

    public EventBuilder setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
        return this;
    }

    public EventBuilder setEvent(List<Event> event) {
        this.event = event;
        return this;
    }

    public Event createEvent() {
        return new Event(dateTime, kadonId, eventName, stage, oldValue, newValue, carType, eventsList, event);
    }
}