package hospital;

public interface IVisitorRobot {

    //can visit different vehicles

    void visit(EmergencyVehicle emergencyVehicle);

    void visit(BioSafetyEmergencyVehicle bioSafetyEmergencyVehicle);
}
