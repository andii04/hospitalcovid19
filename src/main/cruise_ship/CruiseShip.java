package cruise_ship;

import com.google.common.eventbus.EventBus;

public class CruiseShip {
    private String name = "Symphony of the Seas";
    private EventBus eventBus;

    public CruiseShip(String name, EventBus eventBus) {
        this.name = name;
        this.eventBus = eventBus;

    }

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

        private String name = "Symphony of the Seas";
        private EventBus eventBus;

        public Builder(String name, EventBus eventBus) {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public Builder boarding(){
            return this;
        }

        public Builder notifyHospital(String message){
            return this;
        }

        public Builder startSimulation(){
            return this;
        }

        public Builder build() {
            return new Builder(name,eventBus);
        }
    }
}
