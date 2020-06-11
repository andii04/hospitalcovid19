package hospital;


public class RemoteControlRobot {
    DisinfectionRobot robot;

    public RemoteControlRobot(DisinfectionRobot robot) {
        this.robot = robot;
    }

    void startRobotForBSEmergencyVehicle(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle) {
        System.out.println("RemoteControlRobot: pressed disinfection for biosafetyvehicle");
        CommandDesinfactBSEmergencyVehicle commandDesinfactBSEmergencyVehicle = new CommandDesinfactBSEmergencyVehicle(robot, bioSafetyEmergencyVehicle);
        commandDesinfactBSEmergencyVehicle.execute();
    }

    void startRobotForNormalEmergencyVehicle(EmergencyVehicle emergencyVehicle) {
        System.out.println("RemoteControlRobot: pressed disinfection for normal vehicle");
        CommandDesinfactNormalEmergencyVehicle commandDesinfactNormalEmergencyVehicle = new CommandDesinfactNormalEmergencyVehicle(robot, emergencyVehicle);
        commandDesinfactNormalEmergencyVehicle.execute();
    }


}
