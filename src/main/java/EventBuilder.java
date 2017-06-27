import java.util.Date;

public class EventBuilder {
    private Date dateTime;
    private int kadonId;
    private String eventName;
    private String stage;
    private String oldValue;
    private String newValue;
    private String carType;
    private Integer kadonId0;

    public EventBuilder setDateTime(Date dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public EventBuilder setKadonId(int kadonId) {
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

    public EventBuilder setKadonId0(Integer kadonId0) {
        this.kadonId0 = kadonId0;
        return this;
    }

    public Event createEvent() {
        return new Event(dateTime, kadonId, eventName, stage, oldValue, newValue, carType);
    }
}