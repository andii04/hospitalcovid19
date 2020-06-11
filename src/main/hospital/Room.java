package hospital;

public class Room {
    public HospitalBed[] getHospitalBeds(){
        return hospitalBedSpace;
    }
    private int id;

    public HospitalBed getHospitalBed(int i) {
        return hospitalBedSpace[i];
    }
    public int getNumberOfBeds(){
        return hospitalBedSpace.length;
    }

    private HospitalBed[] hospitalBedSpace;

    public Room(int id, int anzahlPlaetze, int anzahlBetten){
        this.id = id;
        hospitalBedSpace = new HospitalBed[anzahlPlaetze];
        for (int i = 0;i<=anzahlBetten;i++){
            hospitalBedSpace[i] = new HospitalBed();
        }
    }

    public Integer getRoomID() {
        return  id;
    }
}
