package hospital;

import java.util.ArrayList;

public class Floor {
    private int id;
    private ArrayList<Department> departments;

    public Floor(int id, ArrayList<Department> departments) {
        this.id = id;
        this.departments = departments;
    }

    //get on which floor the department is
    public static int getFloorofDepartment(DepartmentsName departmentsName) {
        switch (departmentsName) {
            case Emergency_Department:
                return 0;
            case Critical_Care:
                return 1;
            case Pulmonology:
                return 2;
            case Radiology:
                return 3;
            case Cardiology:
                return 4;
            case General_Surgery:
                return 5;
            case Oncology:
                return 6;
        }
        return 0;
    }

    //get the department on a certain floor
    public static DepartmentsName getNameDepartmentFloor(int floorID) {
        switch (floorID) {
            case 0:
                return DepartmentsName.Emergency_Department;
            case 1:
                return DepartmentsName.Critical_Care;
            case 2:
                return DepartmentsName.Pulmonology;
            case 3:
                return DepartmentsName.Radiology;
            case 4:
                return DepartmentsName.Cardiology;
            case 5:
                return DepartmentsName.General_Surgery;
            case 6:
                return DepartmentsName.Oncology;
        }
        return null;
    }

    public Department getDepartments(int departmentOnFloor) {
        return departments.get(departmentOnFloor);
    }
}
