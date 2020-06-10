package hospital;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Floor {
    private int id;
    private ArrayList<Department> departments;
    public Floor(int id, ArrayList<Department> departments){
        this.id = id;
        this.departments = departments;
    }
    public Department getDepartments(int departmentOnFloor){
        return departments.get(departmentOnFloor);
    }
}
