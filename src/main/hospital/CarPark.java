package hospital;

import java.util.ArrayList;

public class CarPark {
    private ArrayList<MedicalStaff> onCallStaffList = new ArrayList<>();
    ArrayList<EmergencyVehicle> emergencyVehicles = new ArrayList<>();

    public CarPark(){
        for(int i = 0;i<10;i++){
            if (i<3){
                BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle = new BioSafetyEmergencyVehicle();
                emergencyVehicles.add(bioSafetyEmergencyVehicle);
            }
            else{
                EmergencyVehicle emergencyVehicle = new EmergencyVehicle();
                emergencyVehicles.add(emergencyVehicle);
            }
        }
    }
    public void park(EmergencyVehicle emergencyVehicle){

    }
    public void leave(EmergencyVehicle emergencyVehicle){

    }
}
