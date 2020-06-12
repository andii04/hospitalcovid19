package hospital;

import java.util.ArrayList;
import java.util.Random;

public class EmergencyVehicle implements IVistable {
    boolean isClosed = true;
    String[] cleanAIRPool = new String[]{"a", "i", "r"};
    private String registeredKeySignature;
    private long serialNumber;
    private boolean isFlashingLightOn;
    private String location;
    private Stretcher stretcher;
    private ArrayList<MedicalStaff> medicalStaffs;
    private Random r = new Random();
    private String[][][] ambientAir = new String[50][50][50];

    public EmergencyVehicle(long serialNumber, String signature) {
        medicalStaffs = new ArrayList<>();
        this.serialNumber = serialNumber;
        location = "CarPark";
        stretcher = new Stretcher();
        registeredKeySignature = signature;
        initializeAmbientAir();
    }

    private void initializeAmbientAir() {
        String[][][] ambientAir = getAmbientAir();
        for (int i = 0; i < ambientAir.length; i++) {
            for (int a = 0; a < ambientAir[i].length; a++) {
                for (int b = 0; b < ambientAir[i][a].length; b++) {
                    {
                        ambientAir[i][a][b] = cleanAIRPool[r.nextInt(cleanAIRPool.length)];
                    }
                }
            }
        }
        setAmbientAir(ambientAir);
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public boolean isFlashingLightOn() {
        return isFlashingLightOn;
    }

    public void setFlashingLightOn() {
        System.out.println("Vehicle: Flashlight on");
        isFlashingLightOn = true;
    }

    public Stretcher getStretcher() {
        return stretcher;
    }

    //get stretcher out of vehicle
    public Stretcher getStretcherOut() {
        Stretcher stretcherOut = stretcher;
        this.stretcher = null;
        return stretcherOut;
    }

    //get stretcher back in vehicle
    public void getStretcherIn(Stretcher stretcher) {
        this.stretcher = stretcher;
    }

    public MedicalStaff getMedicalStaffs(int id) {
        return medicalStaffs.get(id);
    }

    public void addMedicalStaffs(MedicalStaff medicalStaff) {
        medicalStaffs.add(medicalStaff);
    }

    public void removeMedicalStaffs(int id) {
        medicalStaffs.remove(id);
    }

    public int getNumberOfMedicalStaffs() {
        return medicalStaffs.size();
    }

    public ArrayList<MedicalStaff> getAllMedicalStaffs() {
        return medicalStaffs;
    }

    public String[][][] getAmbientAir() {
        return ambientAir;
    }

    public void setAmbientAir(String[][][] ambientAir) {
        this.ambientAir = ambientAir;
    }

    //open only with valid signature
    public void open(String keySignature) {
        if (keySignature == registeredKeySignature) {
            isClosed = false;
            System.out.println("Vehicle: Vehicle opened");
        }
    }

    //close only with valid signature
    public void close(String keySignature) {
        if (keySignature == registeredKeySignature) {
            isClosed = true;
            System.out.println("Vehicle: Vehicle closed");
        }
    }

    //move vehicle to location
    public void move(String location) {
        System.out.println("Vehicle: Vehicle moving to " + location);
        this.location = location;

    }

    //let visitor accept vehicle for disinfect
    public void accept(IVisitorRobot visitor) {
        visitor.visit(this);
    }


    public void setFlashingLightOff() {
        System.out.println("Vehicle: Flashlight off");
        isFlashingLightOn = false;
    }
}
