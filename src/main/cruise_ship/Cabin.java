package cruise_ship;

import shared.Human;

import java.util.ArrayList;
import java.util.List;

public class Cabin {
    private String id;
    private CabinLocation location;
    private String EventMessage = "Emergency";
    private List<Human> passengers = new ArrayList<>();
    private CruiseShip cruiseShip;

    public void releaseEmergencyCall(int cabinID, int passengerID){
        cruiseShip.getEventBus().post("Emergency-"+cabinID+"-"+passengerID);

    }
    public Cabin(CabinLocation location, String id, CruiseShip cruiseShip){
        this.location = location;
        this.id = id;
        this.cruiseShip = cruiseShip;
    }

    public void addPassenger(Human passenger) {
        this.passengers.add(passenger);
    }

    public List<Human> getPassengers() {
        return passengers;
    }
}
