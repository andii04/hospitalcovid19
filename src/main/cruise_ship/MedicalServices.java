package cruise_ship;


import com.google.common.eventbus.Subscribe;
import shared.Clothing;
import shared.Human;
import shared.ProtectiveSuit;

public class MedicalServices {
    private Quarantine quarantine;
    private CruiseShip cruiseShip;
    private MedicalAssistant medicalAssistant;


    public MedicalServices(CruiseShip cruiseShip) {
        this.cruiseShip = cruiseShip;
        quarantine = new Quarantine(cruiseShip);
    }

    @Subscribe
    public void listen(String event) {
        String[] test = event.split("-");
        if (test[0].equals("Emergency")) {
            System.out.println(event);
            medicalAssistant = new MedicalAssistant(this, quarantine);
            int cabinID = Integer.parseInt(event.split("-")[1]);
            int passengerinCell = Integer.parseInt(event.split("-")[2]);
            medicalAssistant.takeIntoQuarantine(cruiseShip.cabinList.get(cabinID).getPassengers().get(passengerinCell));
            cruiseShip.cabinList.get(cabinID).getPassengers().remove(passengerinCell);
        }
    }

    public MedicalAssistant getMedicalAssistant() {
        return medicalAssistant;
    }

    public Quarantine getQuarantine() {
        return quarantine;
    }

    public void releaseEmergencyCall(String message) {
        cruiseShip.getEventBus().post(message);
    }


}
