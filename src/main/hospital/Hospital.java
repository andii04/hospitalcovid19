package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Hospital {
    private String name;
    ArrayList<Department> departments;
    CarPark carPark;
    Stack<Floor> floors;

    public Hospital(String name, Stack<Floor> floors){
        this.name = name;
        this.floors = floors;
        carPark = new CarPark();
        }


    //own Methods


    public void inGoingEmergency(ICommand command){
        command.execute();
    }

    public Floor getFloor(int floorID){
        return floors.get(floorID);
    }
    public Department getDepartment(int floorID, int departmentOnFloor){
        return floors.get(floorID).getDepartments(departmentOnFloor);
    }

    public static class Builder{

        private String name;
        Stack<Floor> floors;

        public Hospital.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Hospital.Builder setFloors(Stack<Floor> floors){
            this.floors =floors;
            return this;
        }

        public Hospital build() {
            return new Hospital(name, floors);
        }
    }
}