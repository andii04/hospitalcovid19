package hospital;

public class CommandOpenDoor implements ICommand {

    private String signature;

    public CommandOpenDoor(EmergencyVehicle emergencyVehicle, String signature){
        this.emergencyVehicle = emergencyVehicle;
        this.signature = signature;
    }

    private EmergencyVehicle emergencyVehicle;
    @Override
    public void execute() {
        emergencyVehicle.open(signature);
    }
}
