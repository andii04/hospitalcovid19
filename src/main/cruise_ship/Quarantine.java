package cruise_ship;

import shared.Human;

public class Quarantine {
    private CruiseShip cruiseShip;
    private Human human;

    public Quarantine(CruiseShip cruiseShip) {
        this.cruiseShip = cruiseShip;
    }

    public void addPassenger(Human passenger) {
        this.human = passenger;
    }

    public Human getHuman() {
        return human;
    }
}
