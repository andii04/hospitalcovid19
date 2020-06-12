package hospital;

import cruise_ship.CruiseShip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class EmergencyDepartment extends Department {
    //Mergency Department has Access to the carPark, and free rooms
    CarPark carPark;
    Hospital hospital;
    private HospitalPatient currentHospitalPatient;
    private LinkedList<HospitalBed> emptyHospitalBedList;

    public EmergencyDepartment(DepartmentsName departmentsName) {
        super(departmentsName, null);
        emptyHospitalBedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            emptyHospitalBedList.add(new HospitalBed());
        }
    }

    public LinkedList<HospitalBed> getEmptyHospitalBedList() {
        return emptyHospitalBedList;
    }

    public void setEmptyHospitalBedList(LinkedList<HospitalBed> emptyHospitalBedList) {
        this.emptyHospitalBedList = emptyHospitalBedList;
    }

    public void addBed(HospitalBed hospitalBed) {
        emptyHospitalBedList.add(hospitalBed);
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }


    public void welcome(EmergencyVehicle vehicle) {
        Random r = new Random();
        MedicalStaff chosenMedicalStaffForTransfer = vehicle.getMedicalStaffs(r.nextInt(3));
        Stretcher stretcher = unload(vehicle);
        chosenMedicalStaffForTransfer.goWithPassengerToBed(stretcher, emptyHospitalBedList.get(emptyHospitalBedList.size() - 1));
        currentHospitalPatient = new HospitalPatient(emptyHospitalBedList.get(emptyHospitalBedList.size() - 1).getHuman());
        currentHospitalPatient.setId(hospital.getNewHospitalPatientID());
        emptyHospitalBedList.get(emptyHospitalBedList.size() - 1).setPatient(currentHospitalPatient);
        register(currentHospitalPatient);
    }


    private void register(HospitalPatient hospitalPatient) {
        System.out.println(this.getName() + " : Registering patient with ID " + hospitalPatient.getId() + " now");
        String[] spaceInfo = hospital.getFreeSpace();
        Case newCase = new Case(Integer.parseInt(spaceInfo[0]), spaceInfo[1], spaceInfo[2], Integer.parseInt(spaceInfo[3]), Integer.parseInt(spaceInfo[4]), new SimpleDateFormat().toString());
        newCase.printCase();
        //@todo datenschutz
        move(emptyHospitalBedList.get(emptyHospitalBedList.size() - 1), newCase);
        addBed(hospital.getBedByStation(spaceInfo[1], spaceInfo[2]));
    }

    public void move(HospitalBed hospitalBed, Case thecase) {
        System.out.println(this.getName() + " : Patient with ID " + currentHospitalPatient.getId() + " will now be moved to his room now");
        hospital.getFloor(thecase.getFloorID()).getDepartments(0)
                .getStation(Station.getStationNumberFromNameID(thecase.getStationID()))
                .getRoom(thecase.getRoomID() - 1).setHospitalBed(thecase.getBedIDinRoom() - 1, hospitalBed);
        emptyHospitalBedList.remove(hospitalBed);
    }

    private Stretcher unload(EmergencyVehicle vehicle) {
        return vehicle.getStretcher();
    }


    public void receiveCoronaEmergency(CruiseShip cruiseShip) {
        System.out.println(this.getName() + " : Received Emergency from cruiseShip");
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

        cruiseShip.vehicleArrive(vehicle);
        //@todo just for testing
        //((BSEmergencyDepartment) hospital.getFloor(0).getDepartments(0)).welcome(vehicle);
    }
}
