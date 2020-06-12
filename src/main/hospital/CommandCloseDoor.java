package hospital;

public class CommandCloseDoor implements ICommand {
    private String signature;
    private EmergencyVehicle emergencyVehicle;

    //Command to close the door of EmergencyVehicle from a key
    public CommandCloseDoor(EmergencyVehicle emergencyVehicle, String signature) {
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    @Override
    public void execute() {
        emergencyVehicle.close(signature);
    }
}
