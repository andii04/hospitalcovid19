package hospital;

public class CommandCloseDoor implements ICommand {
    private String signature;

    public CommandCloseDoor(EmergencyVehicle emergencyVehicle, String signature){
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    private EmergencyVehicle emergencyVehicle;
    @Override
    public void execute() {
        emergencyVehicle.close(signature);
    }
}
