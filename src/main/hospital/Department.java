package hospital;

import java.util.ArrayList;

public class Department {
    ArrayList<Station> stations;
    private DepartmentsName name;

    public Department(DepartmentsName name, ArrayList<Station> stations) {
        this.stations = stations;
        this.name = name;
    }

    public Station getStation(int i) {
        return stations.get(i);
    }

    public int getNumberOfStations() {
        return stations.size();
    }

    public DepartmentsName getName() {
        return name;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

}
