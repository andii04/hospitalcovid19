package hospital;

import cruise_ship.CruiseShip;
import shared.Human;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class EmergencyDepartment extends Department {
    //Mergency Department has Access to the carPark, and free rooms
    CarPark carPark;
    Hospital hospital;
    private HospitalPatient currentHospitalPatient;

    public LinkedList<HospitalBed> getEmptyHospitalBedList() {
        return emptyHospitalBedList;
    }

    public void setEmptyHospitalBedList(LinkedList<HospitalBed> emptyHospitalBedList) {
        this.emptyHospitalBedList = emptyHospitalBedList;
    }

    private LinkedList<HospitalBed> emptyHospitalBedList;

   public EmergencyDepartment(){
       emptyHospitalBedList = new LinkedList<>();
       for(int i = 0;i<10;i++){
            emptyHospitalBedList.add(new HospitalBed());
        }
    }


    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }
    public void setHospital(Hospital hospital){
       this.hospital = hospital;
    }


    public void welcome(EmergencyVehicle vehicle) {
        Random r = new Random();
        MedicalStaff chosenMedicalStaffForTransfer = vehicle.getMedicalStaffs(r.nextInt(3));
        Stretcher stretcher = unload(vehicle);
        chosenMedicalStaffForTransfer.goWithPassengerToBed(stretcher, emptyHospitalBedList.get(emptyHospitalBedList.size()-1));
        currentHospitalPatient = (HospitalPatient) emptyHospitalBedList.get(emptyHospitalBedList.size()-1).getHuman();
        currentHospitalPatient.setId(hospital.getNewHospitalPatientID());
        register(currentHospitalPatient);
    }


    private void register(HospitalPatient hospitalPatient) {
        //System.out.println("EmergencyDepartment: Registering patient" + hospitalPatient.getLastName() +"now");
        String[] spaceInfo = hospital.getFreeSpace();
        Case newCase = new Case(Integer.parseInt(spaceInfo[0]), spaceInfo[1], spaceInfo[2], Integer.parseInt(spaceInfo[3]), Integer.parseInt(spaceInfo[4]), new SimpleDateFormat().toString());
        newCase.printCase();
        //@todo datenschutz
        move(emptyHospitalBedList.get(emptyHospitalBedList.size()-1), newCase);
    }

    public void move(HospitalBed hospitalBed, Case thecase) {
       System.out.println("EmergencyDepartment: Patient with ID will now be moved to his room");
       hospital.getFloor(thecase.getFloorID()).getDepartments(0)
               .getStation(Station.getStationNumberFromNameID(thecase.getStationID()))
               .getRoom(thecase.getRoomID()).setHospitalBed(thecase.getBedIDinRoom(),hospitalBed);
       emptyHospitalBedList.remove(hospitalBed);
       emptyHospitalBedList.add(hospital.getFreeBed());
    }

    private Stretcher unload(EmergencyVehicle vehicle) {
        return vehicle.getStretcher();
    }


    public void receiveCoronaEmergency(CruiseShip cruiseShip) {
        System.out.println("EmergencyDepartment: Received Emergency from cruiseship");
        ArrayList<MedicalStaff> crew = carPark.getCrewforBSEmergencyVehicle(3);
        BioSafetyEmergencyVehicle vehicle = carPark.getFreeBSEmergencyVehicle();
        Random r = new Random();
        MedicalStaff doorOpener = crew.get(r.nextInt(crew.size()));
        doorOpener.openBioSafetyVehicle(vehicle);
        for (int i = 0; i < 3; i++) {
            vehicle.addMedicalStaffs(crew.get(i));
        }
        vehicle.setFlashingLightOn();
        carPark.leave(vehicle);
        vehicle.move("CruiseShip");

        //@todo just for testing
        vehicle.getStretcher().position(new Human());
        ((BSEmergencyDepartment)hospital.getFloor(0).getDepartments(0)).welcome(vehicle);
    }
}
