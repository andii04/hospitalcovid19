package hospital;

import cruise_ship.CruiseShip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class EmergencyDepartment extends Department {
    //Mergency Department has Access to the carPark, and free rooms
    CarPark carPark;
    Hospital hospital;
    private HospitalPatient currentHospitalPatient;
    private LinkedList<HospitalBed> emptyHospitalBedList;

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }


    public void welcome(EmergencyVehicle vehicle) {

    }


    private void register(HospitalPatient hospitalPatient) {

    }

    public void move(HospitalBed hospitalBed, Case thecase) {

    }

    private Stretcher unload(EmergencyVehicle vehicle) {
        return vehicle.getStretcher();
    }


    public void receiveCoronaEmergency(CruiseShip cruiseShip) {
        System.out.println("Received Emergency from cruiseship");
        ArrayList<MedicalStaff> crew = carPark.getCrewforBSEmergencyVehicle(3);
        BioSafetyEmergencyVehicle vehicle = carPark.getFreeBSEmergencyVehicle();
        Random r = new Random();
        MedicalStaff doorOpener = crew.get(r.nextInt(crew.size()));
        doorOpener.openBioSafetyVehicle(vehicle);
        for (int i = 0; i < 3; i++) {
            vehicle.addMedicalStaffs(crew.get(i));
        }
        carPark.leave(vehicle);
        vehicle.move("CruiseShip");
    }
}
