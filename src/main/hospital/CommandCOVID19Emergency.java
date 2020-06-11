package hospital;

import cruise_ship.CruiseShip;

public class CommandCOVID19Emergency implements ICommand{
    Hospital hospital;
    CruiseShip cruiseShip;
    public CommandCOVID19Emergency(Hospital hospital, CruiseShip cruiseShip){
        this.hospital = hospital;
        this.cruiseShip =cruiseShip;
    }
    public void execute(){
        ((EmergencyDepartment) (hospital.getFloor(0).getDepartments(1))).receiveCoronaEmergency(cruiseShip);
    };
}
