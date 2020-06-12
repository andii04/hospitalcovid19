package hospital;

public class CommandDesinfactNormalEmergencyVehicle implements ICommand {
    DisinfectionRobot robot;
    EmergencyVehicle emergencyVehicle;

    //Command to desinfact normal vehicle
    public CommandDesinfactNormalEmergencyVehicle(DisinfectionRobot robot, EmergencyVehicle emergencyVehicle) {
        this.robot = robot;
        this.emergencyVehicle = emergencyVehicle;
    }

    @Override
    public void execute() {
        robot.visit(emergencyVehicle);
    }

}