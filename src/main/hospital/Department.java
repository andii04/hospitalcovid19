package hospital;

import java.util.ArrayList;

public class Department {
    private String name;

    ArrayList<Station> stations;
    public Department(ArrayList<Station> stations) {
        this.stations = stations;
    }
}
