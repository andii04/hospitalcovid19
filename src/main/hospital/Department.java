package hospital;

import java.util.ArrayList;

public class Department {
    private DepartmentsName name;

    ArrayList<Station> stations;
    public Department(DepartmentsName name, ArrayList<Station> stations) {
        this.stations = stations;
        this.name = name;
    }

    public Department(){

    }
}
