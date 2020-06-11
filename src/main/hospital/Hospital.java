package hospital;

import java.util.ArrayList;
import java.util.Stack;

public class Hospital {
    ArrayList<Department> departments;
    CarPark carPark;
    Stack<Floor> floors;
    private String name;

    public Hospital(String name, Stack<Floor> floors, CarPark carPark) {
        this.name = name;
        this.floors = floors;
        this.carPark = carPark;

        //later have access to the vehicles
        ((BSEmergencyDepartment) floors.get(0).getDepartments(0)).setCarPark(carPark);
    }


    //own Methods


    public void inGoingEmergency(ICommand command) {
        command.execute();
    }

    public Floor getFloor(int floorID) {
        return floors.get(floorID);
    }

    public Department getDepartment(int floorID, int departmentOnFloor) {
        return floors.get(floorID).getDepartments(departmentOnFloor);
    }

    public CarPark getCarPark() {
        return carPark;
    }

    public HospitalBed getFreePlace() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j <= floors.get(i).getDepartments(0).getNumberOfStations(); j++) {
                for (int k = 0; k <= floors.get(i).getDepartments(0).getStation(j).getNumberOfRooms(); k++) {
                    for (int l = 0; l <= floors.get(i).getDepartments(0).getStation(j).getRoom(k).getNumberOfBeds(); l++) {
                        if (floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l).isEmpty()) {
                            String[] roomInfo = new String[4];
                            roomInfo[0] = Integer.toString(i);
                            roomInfo[1] = floors.get(i).getDepartments(0).getName().toString();
                            roomInfo[2] = floors.get(i).getDepartments(0).stations.get(j).getName();
                            roomInfo[3] = floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getRoomID().toString();
                            floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l).setInfo(roomInfo);
                            return floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static class Builder {

        Stack<Floor> floors;
        CarPark carPark;
        private String name;

        public Hospital.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Hospital.Builder setFloors(Stack<Floor> floors) {
            this.floors = floors;
            return this;
        }

        public Hospital.Builder setCarPark(CarPark carPark) {
            this.carPark = carPark;
            return this;
        }

        public Hospital build() {
            return new Hospital(name, floors, carPark);
        }
    }
}