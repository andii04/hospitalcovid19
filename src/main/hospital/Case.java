package hospital;

public class Case {
    private int bedIDinRoom;
    private int floorID;
    private String department;
    private String stationID;

    public int getBedIDinRoom(){
        return bedIDinRoom;
    }
    public int getFloorID() {
        return floorID;
    }

    public String getDepartment() {
        return department;
    }

    public String getStationID() {
        return stationID;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    private int roomID;
    private String lastUpdate;

    public Case(int floorID, String department, String stationID, int roomID, int bedIDinRoom, String lastUpdate) {
        this.floorID = floorID;
        this.department = department;
        this.stationID = stationID;
        this.roomID = roomID;
        this.bedIDinRoom = bedIDinRoom;
        this.lastUpdate = lastUpdate;
    }

    public void printCase() {
        //@todo rint data of case
    }
}
