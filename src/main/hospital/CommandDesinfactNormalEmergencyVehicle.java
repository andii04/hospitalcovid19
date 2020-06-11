package hospital;

public class CommandDesinfactNormalEmergencyVehicle implements ICommand {
    DisinfectionRobot robot;
    EmergencyVehicle emergencyVehicle;

    public CommandDesinfactNormalEmergencyVehicle(DisinfectionRobot robot, EmergencyVehicle emergencyVehicle) {
        this.robot = robot;
        this.emergencyVehicle = emergencyVehicle;
    }

    @Override
    public void execute() {
        robot.visit(emergencyVehicle);
    }

}