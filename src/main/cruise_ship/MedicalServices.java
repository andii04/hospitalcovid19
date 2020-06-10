package cruise_ship;


import com.google.common.eventbus.Subscribe;

public class MedicalServices {

    @Subscribe
    public void stringEvent(String event) {
        System.out.println(event);
    }

    private void releaseEmergencyCall(String message){

    }


}
