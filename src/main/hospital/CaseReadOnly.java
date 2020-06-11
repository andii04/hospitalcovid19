package hospital;

public class CaseReadOnly implements ICase {

    private int bedIDinRoom;
    private int floorID;
    private String department;
    private String stationID;
    private int roomID;
    private String lastUpdate;

    //Setter leer lassen ->

    public CaseReadOnly(int bedIDinRoom, int floorID, String department, String stationID, int roomID, String lastUpdate) {
        this.bedIDinRoom = bedIDinRoom;
        this.floorID = floorID;
        this.department = department;
        this.stationID = stationID;
        this.roomID = roomID;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int getBedIDinRoom() {
        return bedIDinRoom;
    }

    @Override
    public void setBedIDinRoom(int bedIDinRoom) {

    }

    @Override
    public int getFloorID() {
        return floorID;
    }

    @Override
    public void setFloorID(int floorID) {

    }

    @Override
    public String getDepartment() {
        return department;
    }

    @Override
    public void setDepartment(String department) {

    }

    @Override
    public String getStationID() {
        return stationID;
    }

    @Override
    public void setStationID(String stationID) {

    }

    @Override
    public int getRoomID() {
        return roomID;
    }

    @Override
    public void setRoomID(int roomID) {

    }

    @Override
    public String getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(String lastUpdate) {

    }
}
