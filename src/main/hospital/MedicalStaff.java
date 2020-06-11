package hospital;

import shared.Configuration;
import shared.Human;

import java.util.Random;

public class MedicalStaff extends Human {
    private String id;
    private boolean hasProtection = false;
    private String myPinforCard;
    private IDCard idCard;

    public MedicalStaff(String id) {
        this.id = id;
        Random r = new Random();
        myPinforCard = Integer.toString(r.nextInt((99999 - 10000 + 1) + 10000));
        this.idCard = new IDCard(Configuration.AES, myPinforCard);
    }

    public IDCard showIDCard() {
        return idCard;
    }

    public void disinfect() {
        System.out.println("Medical Staff ID " + id + " desinfect");
        hasProtection = false;
    }

    public void disinfectVehicle(Object vehicle, RemoteControlRobot remoteControlRobot) {
        if (vehicle instanceof BioSafetyEmergencyVehicle) {
            System.out.println("disinfect BioSafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForBSEmergencyVehicle((BioSafetyEmergencyVehicle) vehicle);
        } else {
            System.out.println("disinfect SafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForNormalEmergencyVehicle((EmergencyVehicle) vehicle);
        }
    }

    public void takeProtectionOn() {
        hasProtection = true;
    }

    public void goWithPassengerToBed(HospitalBed bedForPassenger, Stretcher stretcher) {
        stretcher.transfer(bedForPassenger);
    }

    public void openBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.open(idCard, "pin");
        //@todo
    }

    public void closeBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.close(idCard, "pin");
    }

    public void openEmergencyVehicle(Key key) {
        key.buttonOpenVehicle();
    }

    public void closeEmergencyVehicle(BioSafetyEmergencyVehicle vehicle, Key key) {
        key.buttonCloseVehicle();
    }
}
