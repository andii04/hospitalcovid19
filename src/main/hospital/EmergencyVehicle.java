package hospital;

import java.util.ArrayList;

public class EmergencyVehicle  implements IVistable{
    private String registeredKeySignature;
    boolean isClosed = true;
    private long serialNumber;

    public boolean isFlashingLightOn() {
        return isFlashingLightOn;
    }

    public void setFlashingLightOn() {
        isFlashingLightOn = true;
    }

    private boolean isFlashingLightOn;
    private String location;

    public Stretcher getStretcher() {
        return stretcher;
    }


    private Stretcher stretcher;

    public EmergencyVehicle(long serialNumber, String signature){
        this.serialNumber = serialNumber;
        location = "CarPark";
        stretcher = new Stretcher();
        registeredKeySignature = signature;
    }

    public MedicalStaff getMedicalStaffs(int id) {
        return medicalStaffs.get(id);
    }

    public void addMedicalStaffs(MedicalStaff medicalStaff) {
        medicalStaffs.add(medicalStaff);
    }
    public void removeMedicalStaffs(int id){
        medicalStaffs.remove(id);
    }
    public int getNumberOfMedicalStaffs(){
        return medicalStaffs.size();
    }
    public ArrayList<MedicalStaff> getAllMedicalStaffs(){
        return medicalStaffs;
    }

    private ArrayList<MedicalStaff> medicalStaffs;

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
        this.location = location;

    }
    public void accept(IVisitorRobot visitor){
        visitor.visit(this);
    }

    public void setFlashingLightOff() {
        isFlashingLightOn =false;
    }
}
