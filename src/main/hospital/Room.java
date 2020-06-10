package hospital;

public class Room {
    private int id;
    private HospitalBed[] hospitalBedSpace;

    public Room(int id, int anzahl, int belegt){
        this.id = id;
        hospitalBedSpace = new HospitalBed[anzahl];
        for (int i = 0;i<=belegt;i++){
            hospitalBedSpace[i] = new HospitalBed();
        }
    }
}
