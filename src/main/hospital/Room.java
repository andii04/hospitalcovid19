package hospital;

public class Room {
    private int id;
    private HospitalBed[] hospitalBedSpace;

    //generate free beds or empty spaces
    public Room(int id, int numberPlaces, int numberBedsAlready) {
        this.id = id;
        hospitalBedSpace = new HospitalBed[numberPlaces];
        for (int i = 0; i < numberBedsAlready; i++) {
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
