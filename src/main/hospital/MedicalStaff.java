package hospital;

import shared.Human;

public class MedicalStaff extends Human {
    private String id;
    private boolean hasProtection = false;

    public MedicalStaff(String id){
        this.id = id;
    }
    public void disinfect(){
        System.out.println("Medical Staff ID "+ id +" desinfect");
        hasProtection = false;
    }

    public void disinfectVehicle(Object vehicle, RemoteControlRobot remoteControlRobot){
        if(vehicle instanceof BioSafetyEmergencyVehicle){
            System.out.println("disinfect BioSafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForBSEmergencyVehicle((BioSafetyEmergencyVehicle) vehicle);
        }
        else {
            System.out.println("disinfect SafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForNormalEmergencyVehicle((EmergencyVehicle) vehicle);
        }
    }
}
