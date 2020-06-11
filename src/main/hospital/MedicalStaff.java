package hospital;

import shared.Configuration;
import shared.Human;

public class MedicalStaff extends Human {
    private String id;
    private boolean hasProtection = false;
    private IDCard idCard;

    public MedicalStaff(String id){
        this.id = id;
        this.idCard = new IDCard(Configuration.AES);
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

    public void takeProtectionOn(){
        hasProtection= true;
    }

    public void goWithPassengerToBed(HospitalBed bedForPassenger, Stretcher stretcher) {
        stretcher.transfer(bedForPassenger);
    }

    public void openBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.open(idCard,"pin");
        //@todo
    }

    public void closeBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.close(idCard,"pin");
    }
    public void openEmergencyVehicle(Key key) {
        key.buttonOpenVehicle();
    }

    public void closeEmergencyVehicle(BioSafetyEmergencyVehicle vehicle, Key key) {
        key.buttonCloseVehicle();
    }
}
