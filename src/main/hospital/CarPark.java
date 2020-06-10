package hospital;

import java.util.ArrayList;

public class CarPark {
    private ArrayList<MedicalStaff> onCallStaffList = new ArrayList<>();
    private ArrayList<EmergencyVehicle> emergencyVehicles = new ArrayList<>();

    public CarPark(int numberOfBSVehicles, int numberOfEmergencyVehicle, int numberOfMedicalStaff){
        for(int i = 0;i<numberOfBSVehicles;i++) {
            BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle = new BioSafetyEmergencyVehicle(20000+i);
            emergencyVehicles.add(bioSafetyEmergencyVehicle);
        }
        for(int i = 0;i<numberOfEmergencyVehicle;i++) {
            EmergencyVehicle emergencyVehicle= new EmergencyVehicle(10000+i);
            emergencyVehicles.add(emergencyVehicle);
        }
        for(int i =0;i<numberOfMedicalStaff;i++){
            onCallStaffList.add(new MedicalStaff("MedicalStaff-" + Character.getNumericValue(i)));
        }
    }
    public void park(EmergencyVehicle emergencyVehicle){
        ArrayList<MedicalStaff> medicalStaffsVehicle = emergencyVehicle.getAllMedicalStaffs();
        for(int i=0;i<medicalStaffsVehicle.size();i++){
            onCallStaffList.add(medicalStaffsVehicle.get(i));
            emergencyVehicles.remove(i);
        }
        emergencyVehicles.add(emergencyVehicle);

    }
    public void leave(EmergencyVehicle emergencyVehicle){
        emergencyVehicles.remove(emergencyVehicle);
    }


    public EmergencyVehicle getFreeEmergencyVehicle(){
        for (int i=0;i<emergencyVehicles.size();i++){
            if(!(emergencyVehicles.get(i) instanceof BioSafetyEmergencyVehicle)){
                return emergencyVehicles.get(i);
            }
        }
        return null;
    }
    public BioSafetyEmergencyVehicle getFreeBSEmergencyVehicle(){
        for (int i=0;i<emergencyVehicles.size();i++){
            if(emergencyVehicles.get(i) instanceof BioSafetyEmergencyVehicle){
                return (BioSafetyEmergencyVehicle) emergencyVehicles.get(i);
            }
        }
        return null;
    }
}
