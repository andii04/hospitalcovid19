package hospital;

public class CommandDesinfactNormalEmergencyVehicle implements ICommand {
    DesinfactionRobot robot;
    EmergencyVehicle emergencyVehicle;

    public CommandDesinfactNormalEmergencyVehicle(DesinfactionRobot robot, EmergencyVehicle emergencyVehicle) {
        this.robot = robot;
        this.emergencyVehicle = emergencyVehicle;
    }

    @Override
    public void execute() {
        robot.visit(emergencyVehicle);
    }

}