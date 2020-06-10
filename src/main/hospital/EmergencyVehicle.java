package hospital;

import java.util.ArrayList;

public class EmergencyVehicle  implements IVistable{
    private String registeredKeySignature;
    private boolean isClosed = true;
    private long serialNumber;
    private boolean isFlashingLightOn;

    public MedicalStaff getMedicalStaffs(int id) {
        return medicalStaffs.get(id);
    }

    public void addMedicalStaffs(MedicalStaff medicalStaff) {
        medicalStaffs.add(medicalStaff);
    }
    public void removeMedicalStaffs(int id){
        medicalStaffs.remove(id);
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

    }
    public void accept(IVisitorRobot visitor){
        visitor.visit(this);
    }
}
