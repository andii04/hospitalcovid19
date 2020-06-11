package hospital;

public class CommandCloseDoor implements ICommand {
    private String signature;
    private EmergencyVehicle emergencyVehicle;

    public CommandCloseDoor(EmergencyVehicle emergencyVehicle, String signature) {
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    @Override
    public void execute() {
        emergencyVehicle.close(signature);
    }
}
