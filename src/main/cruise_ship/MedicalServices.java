package cruise_ship;


import com.google.common.eventbus.Subscribe;
import shared.Human;

public class MedicalServices {
    private Quarantine quarantine;
    private CruiseShip cruiseShip;

    public MedicalServices(CruiseShip cruiseShip){
        this.cruiseShip = cruiseShip;
        quarantine = new Quarantine(cruiseShip);
    }

    @Subscribe
    public void listen(String event) {
        Human medicalAssistant = new MedicalAssistant();
        int cabinID = Integer.parseInt(event.split("-")[1]);
        int passengerinCell = Integer.parseInt(event.split("-")[2]);

        cruiseShip.cabinList.get(cabinID).getPassengers().get(passengerinCell).setHasMouthProtection(true);
        quarantine.addPassenger(cruiseShip.cabinList.get(cabinID).getPassengers().get(passengerinCell));
        cruiseShip.cabinList.get(cabinID).getPassengers().remove(passengerinCell);

    }

    public Quarantine getQuarantine() {
        return quarantine;
    }

    private void releaseEmergencyCall(String message){

    }


}
