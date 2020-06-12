package cruise_ship;

import java.util.ArrayList;

public class SkyDeckLocation {
    SkyDeskLocationType type;
    private ArrayList<Passenger> passengerList = new ArrayList<>();
    private int capacity;

    public SkyDeckLocation(SkyDeskLocationType type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }
}
