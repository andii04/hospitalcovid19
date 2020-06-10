package hospital;

import java.util.ArrayList;

public class BioSafetyEmergencyVehicle extends EmergencyVehicle{
    private ArrayList<IDCard> registeredIDCardList = new ArrayList<>();

    public BioSafetyEmergencyVehicle(long serialNumber) {
        super(serialNumber);
    }

    public void open(IDCard idCard){

    }
    public void close(IDCard idCard){

    }
}
