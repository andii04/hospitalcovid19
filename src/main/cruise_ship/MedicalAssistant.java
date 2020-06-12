package cruise_ship;

import shared.Clothing;
import shared.Human;
import shared.ProtectiveSuit;

public class MedicalAssistant extends Human {
    private int id;
    private CruiseShip cruiseShip;
    private Quarantine quarantine;
    private MedicalServices medicalServices;
    private Clothing myNormalClothes;
    private ProtectiveSuit myProtectiveSuit;

    public MedicalAssistant(MedicalServices medicalServices, Quarantine quarantine){
        this.medicalServices = medicalServices;
        this.quarantine = quarantine;
        myNormalClothes = new Clothing();
        myProtectiveSuit = new ProtectiveSuit();
        setClothing(myNormalClothes);
    }
    private void takeProtectionOff() {
        setClothing(myNormalClothes);
        System.out.println("MedicalAssistant take protection off");
    }


    //take the protection off
    public void takeProtectionOn() {
        setClothing(myProtectiveSuit);
        System.out.println("MedicalAssistant take protection ofn");
    }

    public void takeIntoQuarantine(Human passenger) {
        //change Clothing to Protective Suit
        takeProtectionOn();

        //take a Mask to the Infected Passenger
        passenger.setHasMouthProtection(true);

        //Put him to Quarantine Room
        quarantine.addPassenger(passenger);

        //Press Button "QuarantineOccupied" and trigger a event
        medicalServices.releaseEmergencyCall("QuarantineOccupied");
    }

    public Human getPassengerFromQuarantine(){
        return quarantine.getHuman();
    }

    public void disinfect() {

    }
}
