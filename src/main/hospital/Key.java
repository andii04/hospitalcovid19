package hospital;

import java.security.Signature;
import java.util.ArrayList;

public class Key {
    private String signature;

    ArrayList<ICommand>  commands;

    public Key(EmergencyVehicle emergencyVehicle, String signature){
        this.signature = signature;
        commands  = new ArrayList<>();
        commands.add(new CommandOpenDoor(emergencyVehicle,signature));
        commands.add(new CommandCloseDoor(emergencyVehicle, signature));
    }

    private void buttonCloseVehicle(){
        commands.get(1).execute();
    }
    private void buttonOpenVehicle(){
        commands.get(0).execute();
    }

}
