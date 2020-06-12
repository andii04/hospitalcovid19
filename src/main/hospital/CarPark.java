package hospital;

import java.util.ArrayList;
import java.util.Random;

public class CarPark {
    private KeyStore keyStore;
    private ArrayList<MedicalStaff> onCallStaffList = new ArrayList<>();
    private ArrayList<EmergencyVehicle> emergencyVehicles = new ArrayList<>();

    public CarPark(int numberOfBSVehicles, int numberOfEmergencyVehicle, int numberOfMedicalStaff) {
        keyStore = new KeyStore();

        //generade Medical persons
        for (int i = 0; i < numberOfMedicalStaff; i++) {
            onCallStaffList.add(new MedicalStaff("MedicalStaff-" + i));
        }
        //generate BSVehicle and register IDCards of medicalStaff
        for (int i = 0; i < numberOfBSVehicles; i++) {
            String signature = KeyStore.randomString(5);
            keyStore.createKey(10000 + i, signature);
            BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle = new BioSafetyEmergencyVehicle(20000 + i, signature);
            for (MedicalStaff medicalStaff : onCallStaffList) {
                bioSafetyEmergencyVehicle.registerIDCard(medicalStaff.showIDCard());

            }
            emergencyVehicles.add(bioSafetyEmergencyVehicle);
        }
        //generate normal vehicle and a suitable key to open it later
        for (int i = 0; i < numberOfEmergencyVehicle; i++) {
            String signature = KeyStore.randomString(5);
            keyStore.createKey(10000 + i, signature);
            EmergencyVehicle emergencyVehicle = new EmergencyVehicle(10000 + i, signature);
            emergencyVehicles.add(emergencyVehicle);
        }
    }

    //park vehicle -->  vehicle back in vehiclePool --> MedicalStaff back in pool
    public void park(EmergencyVehicle emergencyVehicle) {
        ArrayList<MedicalStaff> medicalStaffsVehicle = emergencyVehicle.getAllMedicalStaffs();
        for (MedicalStaff medicalStaff : medicalStaffsVehicle) {
            onCallStaffList.add(medicalStaff);
        }
        for (int i = medicalStaffsVehicle.size() - 1; i >= 0; i--) {
            emergencyVehicle.removeMedicalStaffs(i);
        }
        emergencyVehicles.add(emergencyVehicle);
    }

    //select a random crew for the vehicle
    public ArrayList<MedicalStaff> getCrewforBSEmergencyVehicle(int number) {
        ArrayList<MedicalStaff> chosenPersons = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < number; i++) {
            MedicalStaff chosenPerson = onCallStaffList.get(r.nextInt(onCallStaffList.size()));
            onCallStaffList.remove(chosenPerson);
            chosenPerson.takeProtectionOn();
            chosenPersons.add(chosenPerson);
        }
        return chosenPersons;
    }

    //remove vehicle from the vehiclePool and turn flashlight on
    public void leave(EmergencyVehicle emergencyVehicle) {
        emergencyVehicles.remove(emergencyVehicle);
        emergencyVehicle.isFlashingLightOn();
    }

    //select a free EmergencyVehicle
    public EmergencyVehicle getFreeEmergencyVehicle() {
        for (int i = 0; i < emergencyVehicles.size(); i++) {
            if (!(emergencyVehicles.get(i) instanceof BioSafetyEmergencyVehicle)) {
                return emergencyVehicles.get(i);
            }
        }
        return null;
    }

    //select a free BSEmergencyVehicle
    public BioSafetyEmergencyVehicle getFreeBSEmergencyVehicle() {
        for (int i = 0; i < emergencyVehicles.size(); i++) {
            if (emergencyVehicles.get(i) instanceof BioSafetyEmergencyVehicle) {
                return (BioSafetyEmergencyVehicle) emergencyVehicles.get(i);
            }
        }
        return null;
    }
}
