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
        //random number as pin for idcard
        myPinforCard = Integer.toString(r.nextInt((99999 - 10000 + 1) + 10000));
        this.idCard = new IDCard(Configuration.AES, myPinforCard);
        myNormalClothes = new Clothing();
        myProtectiveSuit = new ProtectiveSuit();
        setClothing(myNormalClothes);
    }

    //for showing id card
    public IDCard showIDCard() {
        return idCard;
    }

    //disinfect and protection off
    public void disinfect() {
        System.out.println("MedicalStaff: Medical Staff ID " + id + " disinfect");
        takeProtectionOff();
    }

    //take protection off & wear normal clothes
    private void takeProtectionOff() {
        setClothing(myNormalClothes);
        System.out.println("MedicalStaff: Medical Staff ID " + id + " protection off");
        hasProtection = false;
    }


    //take the protection off
    public void takeProtectionOn() {
        setClothing(myProtectiveSuit);
        System.out.println("MedicalStaff: Medical Staff ID " + id + " protection on");
        hasProtection = true;
    }

    //medical staff can control the remotController for the robot
    public void disinfectVehicle(Object vehicle, RemoteControlRobot remoteControlRobot) {
        if (vehicle instanceof BioSafetyEmergencyVehicle) {
            System.out.println("MedicalStaff: disinfect BioSafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForBSEmergencyVehicle((BioSafetyEmergencyVehicle) vehicle);
        } else {
            System.out.println("MedicalStaff: disinfect SafetyEmergencyVehicle now form MedicalStaff");
            remoteControlRobot.startRobotForNormalEmergencyVehicle((EmergencyVehicle) vehicle);
        }
    }


    //open & close for EmergencyVehicle
    public void openBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.open(idCard, myPinforCard);
    }

    public void closeBioSafetyVehicle(BioSafetyEmergencyVehicle vehicle) {
        vehicle.close(idCard, myPinforCard);
    }

    //open & close for EmergencyVehicle
    public void openEmergencyVehicle(Key key) {
        key.buttonOpenVehicle();
    }
    public void closeEmergencyVehicle(BioSafetyEmergencyVehicle vehicle, Key key) {
        key.buttonCloseVehicle();
    }


    //bring passenger to his bad from the stretcher
    public void goWithPassengerToBed(Stretcher stretcher, HospitalBed hospitalBed) {
        System.out.println("MedicalStaff: helps Passenger from stretcher in bed");
        stretcher.transfer(hospitalBed);
    }

    //get the stretcher out
    public Stretcher getStretcherOutOfVehicle(BioSafetyEmergencyVehicle vehicle) {
        return vehicle.getStretcher();
    }

    //do stretcher down
    public void operateStretcherDown(Stretcher stretcher) {
        stretcher.down();
    }

    //do strethcer up
    public void operateStretcherUp(Stretcher stretcher) {
        stretcher.up();
    }

    //Passenger will get on the stretcher
    public void helpPassengerOnStretcher(Stretcher stretcher, Human quarantineHuman) {
        stretcher.position(quarantineHuman);
    }

    //stretcher back in vehicle
    public void getStretcherInVehicle(Stretcher stretcher, BioSafetyEmergencyVehicle vehicle) {
        vehicle.getStretcherIn(stretcher);
    }
}
