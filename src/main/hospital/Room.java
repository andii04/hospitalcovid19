package hospital;

public class Room {
    private int id;
    private HospitalBed[] hospitalBedSpace;

    public Room(int id, int anzahlPlaetze, int anzahlBetten) {
        this.id = id;
        hospitalBedSpace = new HospitalBed[anzahlPlaetze];
        for (int i = 0; i < anzahlBetten; i++) {
            hospitalBedSpace[i] = new HospitalBed();
        }
    }

    public HospitalBed[] getHospitalBeds() {
        return hospitalBedSpace;
    }

    public HospitalBed getHospitalBed(int i) {
        return hospitalBedSpace[i];
    }

    public int getNumberOfBeds() {
        return hospitalBedSpace.length;
    }

    public Integer getRoomID() {
        return id;
    }

    public void setHospitalBed(int i, HospitalBed hospitalBed) {
        hospitalBedSpace[i] = hospitalBed;
    }
}
