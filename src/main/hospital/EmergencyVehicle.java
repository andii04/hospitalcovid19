package hospital;

public class EmergencyVehicle {
    private String registeredKeySignature;
    private boolean isClosed = true;
    private long serialNumber;
    private boolean isFlashingLightOn;
    private String[][][] ambientAir = new String[50][50][50];

    public void open(String keySignature){
        if(keySignature == registeredKeySignature){
            isClosed = false;
            System.out.println("Vehicle opened");
        }
    }
    public void close(String keySignature){
        if(keySignature == registeredKeySignature){
            isClosed = true;
            System.out.println("Vehicle closed");
        }
    }
    public void move(String location){

    }
}
