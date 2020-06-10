package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hospital {
    private String name;
    ArrayList<Department> departments;
    CarPark carPark;
    Stack<Floor> floors;

    public Hospital(String name, ArrayList<Department> departments){
        this.name = name;
        this.departments = departments;
        carPark = new CarPark();
        floors = new Stack<>();
        for(int i = 0;i<7;i++){
            floors.add(new Floor(i));
        }
    }


    //own Methods


    public void inGoingEmergency(ICommand command){
        command.execute();
    }

    public static class Builder{

        private String name;
        ArrayList<Department> departments = new ArrayList<Department>();

        public Hospital.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Hospital.Builder setDepartmentsList(ArrayList<Department> departments){
            this.departments =departments;
            return this;
        }

        public Hospital build() {
            return new Hospital(name, departments);
        }
    }
}