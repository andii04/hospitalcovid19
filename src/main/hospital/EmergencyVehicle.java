package hospital;

public class EmergencyVehicle {
    private String registeredKeySignature;
    private boolean isClosed = true;
    private long serialNumber;
    private boolean isFlashingLightOn;

    public String[][][] getAmbientAir() {
        return ambientAir;
    }

    public void setAmbientAir(String[][][] ambientAir) {
        this.ambientAir = ambientAir;
    }

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
    public void accept(IVisitorRobot visitor){
        visitor.visit(this);
    }
}
