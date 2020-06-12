package hospital;

import shared.Clothing;
import shared.Configuration;
import shared.Human;
import shared.ProtectiveSuit;

import java.util.Random;

public class MedicalStaff extends Human {
    private String id;
    private boolean hasProtection = false;
    private String myPinforCard;
    private IDCard idCard;

    private Clothing myNormalClothes;
    private ProtectiveSuit myProtectiveSuit;

    public MedicalStaff(String id) {
        this.id = id;
        Random r = new Random();
        myPinforCard = Integer.toString(r.nextInt((99999 - 10000 + 1) + 10000));
        this.idCard = new IDCard(Configuration.AES, myPinforCard);
        myNormalClothes = new Clothing();
        myProtectiveSuit = new ProtectiveSuit();
        setClothing(myNormalClothes);
    }

    public IDCard showIDCard() {
        return idCard;
    }

    public void disinfect() {
        System.out.println("MedicalStaff: Medical Staff ID " + id + " disinfect");
        takeProtectionOff();
    }

    private void takeProtectionOff() {
        setClothing(myNormalClothes);
        System.out.println("MedicalStaff: Medical Staff ID " + id + " protection off");
        hasProtection = false;
    }

    public void disinfectVehicle(Object vehicle, RemoteControlRobot remoteControlRobot) {
        if (vehicle instanceof BioSafetyEmergencyVehicle) {
            System.out.println("MedicalStaff: disinfect BioSafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForBSEmergencyVehicle((BioSafetyEmergencyVehicle) vehicle);
        } else {
            System.out.println("MedicalStaff: disinfect SafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForNormalEmergencyVehicle((EmergencyVehicle) vehicle);
        }
    }

    public void takeProtectionOn() {
        setClothing(myProtectiveSuit);
        System.out.println("MedicalStaff: Medical Staff ID " + id + " protection on");
        hasProtection = true;
    }


    public void openBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.open(idCard, myPinforCard);
    }

    public void closeBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.close(idCard, myPinforCard);
    }

    public void openEmergencyVehicle(Key key) {
        key.buttonOpenVehicle();
    }

    public void closeEmergencyVehicle(BioSafetyEmergencyVehicle vehicle, Key key) {
        key.buttonCloseVehicle();
    }


    public void goWithPassengerToBed(Stretcher stretcher, HospitalBed hospitalBed) {
        System.out.println("MedicalStaff: helps Passenger from stretcher in bed");
        stretcher.transfer(hospitalBed);
    }

    public Stretcher getStretcherOutOfVehicle(BioSafetyEmergencyVehicle vehicle) {
        return vehicle.getStretcher();
    }

    public void operateStretcherDown(Stretcher stretcher) {
        stretcher.down();
    }

    public void operateStretcherUp(Stretcher stretcher) {
        stretcher.up();
    }

    public void helpPassengerOnStretcher(Stretcher stretcher, Human quarantineHuman) {
        stretcher.position(quarantineHuman);
    }

    public void getStretcherInVehicle(Stretcher stretcher, BioSafetyEmergencyVehicle vehicle) {
        vehicle.getStretcherIn(stretcher);
    }
}
