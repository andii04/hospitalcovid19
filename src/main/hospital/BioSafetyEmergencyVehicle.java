package hospital;

import shared.TwoPinConnector;

import java.util.ArrayList;

public class BioSafetyEmergencyVehicle extends EmergencyVehicle implements IVistable {
    private ArrayList<IDCard> registeredIDCardList = new ArrayList<>();
    private IDCardReader idCardReader;

    private TwoPinConnector twoPinConnector;

    public BioSafetyEmergencyVehicle(long serialNumber, String signature) {
        super(serialNumber, signature);
        idCardReader = new IDCardReader();
    }

    public void open(IDCard idCard, String pin) {
        if (idCardReader.verify(idCard, pin) && registeredIDCardList.contains(idCard)) {
            System.out.println("BioSafetyVehicle: Door opened");
            isClosed = false;
        } else {
            System.out.println("BioSafetyVehicle: Doors not opened. Wrong idcard or pin");
        }

    }

    public void close(IDCard idCard, String pin) {
        if (idCardReader.verify(idCard, pin) && registeredIDCardList.contains(idCard)) {
            System.out.println("BioSafetyVehicle: Door closed");
            isClosed = true;
        } else {
            System.out.println("BioSafetyVehicle: Doors not closed. Wrong idcard or pin");
        }
    }

    void registerIDCard(IDCard idCard) {
        registeredIDCardList.add(idCard);
    }
}
