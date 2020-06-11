package hospital;

public class CommandDesinfactBSEmergencyVehicle implements ICommand {

    DisinfectionRobot robot;
    BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle;

    public CommandDesinfactBSEmergencyVehicle(DisinfectionRobot robot, BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle) {
        this.robot = robot;
        this.bioSafetyEmergencyVehicle = bioSafetyEmergencyVehicle;
    }

    @Override
    public void execute() {
        robot.visit(bioSafetyEmergencyVehicle);
    }

}
