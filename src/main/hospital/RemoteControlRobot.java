package hospital;


public class RemoteControlRobot {
    DesinfactionRobot robot;
    public RemoteControlRobot(DesinfactionRobot robot){
        this.robot = robot;
    }

    void startRobotForBSEmergencyVehicle(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle){
        CommandDesinfactBSEmergencyVehicle commandDesinfactBSEmergencyVehicle = new CommandDesinfactBSEmergencyVehicle(robot, bioSafetyEmergencyVehicle);
    }

    void startRobotForNormalEmergencyVehicle(EmergencyVehicle emergencyVehicle){
        CommandDesinfactNormalEmergencyVehicle commandDesinfactNormalEmergencyVehicle = new CommandDesinfactNormalEmergencyVehicle(robot, emergencyVehicle);
    }


}
