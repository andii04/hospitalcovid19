package cruise_ship;

import shared.Human;

import java.util.ArrayList;
import java.util.List;

public class Quarantine {
    private CruiseShip cruiseShip;
    private Human human;
    public Quarantine(CruiseShip cruiseShip){
        this.cruiseShip = cruiseShip;
    }
    public void addPassenger(Human passenger) {
        this.human = passenger;
    }

    public Human getHuman() {
        return human;
    }
}
