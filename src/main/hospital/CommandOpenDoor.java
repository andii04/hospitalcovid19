package hospital;

public class CommandOpenDoor implements ICommand {

    private String signature;
    private EmergencyVehicle emergencyVehicle;

    public CommandOpenDoor(EmergencyVehicle emergencyVehicle, String signature) {
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    @Override
    public void execute() {
        emergencyVehicle.open(signature);
    }
}
