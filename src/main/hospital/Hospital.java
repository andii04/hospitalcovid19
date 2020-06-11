package hospital;

import java.util.ArrayList;
import java.util.LinkedList;
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
        ((EmergencyDepartment) floors.get(0).getDepartments(1)).setCarPark(carPark);
        ((BSEmergencyDepartment) floors.get(0).getDepartments(0)).setHospital(this);
        ((EmergencyDepartment) floors.get(0).getDepartments(1)).setHospital(this);
        LinkedList<HospitalBed> oneFreeBed = new LinkedList<>();
        oneFreeBed.add(getFreeBed());
        ((BSEmergencyDepartment)getFloor(0).getDepartments(0)).setEmptyHospitalBedList(oneFreeBed);
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

    public HospitalBed getFreeBed() {
        for (int i = 1; i <= 2; i++) {
            for (int j = 0; j <= floors.get(i).getDepartments(0).getNumberOfStations(); j++) {
                for (int k = 0; k <= floors.get(i).getDepartments(0).getStation(j).getNumberOfRooms(); k++) {
                    for (int l = 0; l <= floors.get(i).getDepartments(0).getStation(j).getRoom(k).getNumberOfBeds(); l++) {
                        if (floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l) !=null &&
                                floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l).isEmpty()) {
                            System.out.println("Hospital: Found free bed in Department "+ Floor.getNameDepartmentFloor(i) +
                                    " / Station "+floors.get(i).getDepartments(j).getName()+
                                    " / Room " + k+1 +
                                    " /Bed " + l);
                            HospitalBed freeBed = floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l);
                            floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).setHospitalBed(l,null);
                            System.out.println("Hospital: Bed moved to Emergency department");
                            return freeBed;
                        }
                    }
                }
            }
        }
        return null;
    }

    public String[] getFreeSpace() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j <= floors.get(i).getDepartments(0).getNumberOfStations(); j++) {
                for (int k = 0; k <= floors.get(i).getDepartments(0).getStation(j).getNumberOfRooms(); k++) {
                    for (int l = 0; l <= floors.get(i).getDepartments(0).getStation(j).getRoom(k).getNumberOfBeds(); l++) {
                        if (floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getHospitalBed(l) == null) {
                            String[] roomInfo = new String[5];
                            roomInfo[0] = Integer.toString(i);
                            roomInfo[1] = floors.get(i).getDepartments(0).getName().toString();
                            roomInfo[2] = floors.get(i).getDepartments(0).stations.get(j).getName();
                            roomInfo[3] = floors.get(i).getDepartments(0).stations.get(j).rooms.get(k).getRoomID().toString();
                            roomInfo[4] = Integer.toString(l);
                            System.out.println("Hospital: Found free place in Department "+ Floor.getNameDepartmentFloor(i) +
                                    " / Station "+floors.get(i).getDepartments(j).getName()+
                                    " / Room " + k+1 +
                                    " / PlaceID " + l);
                            return roomInfo;
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