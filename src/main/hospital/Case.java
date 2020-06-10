package hospital;

public class Case {
    private int floorID;
    private String department;
    private String stationID;
    private int roomID;
    private String lastUpdate;

    public Case(int floorID, String department, String stationID, int roomID, String lastUpdate){
        this.floorID =floorID;
        this.department = department;
        this.stationID =stationID;
        this.roomID =roomID;
        this.lastUpdate = lastUpdate;
    }
}
