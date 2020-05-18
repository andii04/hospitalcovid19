package cruise_ship;

import com.google.common.eventbus.EventBus;

public class CruiseShip {
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

    public void boarding(){

    }

    public void notifyHospital(String message){

    }

    public void startSimulation(){

    }

    private class Builder{

    }
}
