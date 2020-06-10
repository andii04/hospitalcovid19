package cruise_ship;

import shared.Human;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    private String id;
    private CabinLocation location;
    private List<Human> passengers = new ArrayList<>();

    public void releaseEmergencyCall(){

    }
    public Cabin(CabinLocation location, String id){
        this.location = location;
        this.id = id;
    }

    public void addPassenger(Human passenger) {
        this.passengers.add(passenger);
    }

    public List<Human> getPassengers() {
        return passengers;
    }
}
