package hospital;

import java.util.ArrayList;

public class BioSafetyEmergencyVehicle extends EmergencyVehicle {
    private ArrayList<IDCard> registeredIDCardList = new ArrayList<>();

    private IDCardReader idCardReader;

    public BioSafetyEmergencyVehicle(long serialNumber, String signature) {
        super(serialNumber, signature);
        idCardReader = new IDCardReader();
    }

    public void open(IDCard idCard, String pin) {
        if (idCardReader.verify(idCard, pin)&&registeredIDCardList.contains(idCard)) {
            isClosed = false;
        }

    }

    public void close(IDCard idCard, String pin) {
        if (idCardReader.verify(idCard, pin)&&registeredIDCardList.contains(idCard)) {
            isClosed = true;
        }
    }
}
