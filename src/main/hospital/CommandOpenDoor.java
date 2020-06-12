package hospital;

public class CommandOpenDoor implements ICommand {

    private String signature;
    private EmergencyVehicle emergencyVehicle;

    //Command to open the door of EmergencyVehicle from a key
    public CommandOpenDoor(EmergencyVehicle emergencyVehicle, String signature) {
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    @Override
    public void execute() {
        emergencyVehicle.open(signature);
    }
}
