package hospital;

public interface IVisitorRobot {

    void visit(EmergencyVehicle emergencyVehicle);
    void visit(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle);
}
