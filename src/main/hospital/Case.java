package hospital;

public class Case implements ICase {
    private int bedIDinRoom;
    private int floorID;
    private String department;
    private String stationID;
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

    @Override
    public int getBedIDinRoom() {
        return bedIDinRoom;
    }

    @Override
    public void setBedIDinRoom(int bedIDinRoom) {
        this.bedIDinRoom = bedIDinRoom;
    }

    @Override
    public int getFloorID() {
        return floorID;
    }

    @Override
    public void setFloorID(int floorID) {
        this.floorID = floorID;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getStationID() {
        return stationID;
    }

    @Override
    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    @Override
    public int getRoomID() {
        return roomID;
    }

    @Override
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public String getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
