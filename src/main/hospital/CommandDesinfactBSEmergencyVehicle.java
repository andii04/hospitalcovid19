package hospital;

public class CommandDesinfactBSEmergencyVehicle implements ICommand {

    DesinfactionRobot robot;
    BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle;
    public CommandDesinfactBSEmergencyVehicle( DesinfactionRobot robot, BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle){
        this.robot = robot;
        this.bioSafetyEmergencyVehicle = bioSafetyEmergencyVehicle;
    }

    @Override
    public void execute() {
        robot.visit(bioSafetyEmergencyVehicle);
    }

}
