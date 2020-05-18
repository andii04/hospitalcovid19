package cruise_ship;

import com.google.common.eventbus.EventBus;

public class Builder {
    //komische scheisse dieses Buildern pattern
    private String name = "Symphony of the Seas";
    private EventBus eventBus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public Builder boarding(){

    }

    public Builder notifyHospital(String message){

    }

    public Builder startSimulation(){

    }

}
