package cruise_ship;

import shared.Human;

import java.util.ArrayList;
import java.util.List;

public class Quarantine {
    private CruiseShip cruiseShip;
    private List<Human> passengers = new ArrayList<>();
    public Quarantine(CruiseShip cruiseShip){
        this.cruiseShip = cruiseShip;
    }
    public void addPassenger(Human passenger) {
        this.passengers.add(passenger);
    }

    public List<Human> getPassengers() {
        return passengers;
    }
}
