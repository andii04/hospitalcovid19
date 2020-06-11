package hospital;

public interface ICase {
    //getter und setter for ReadOnlyInterface
    void setBedIDinRoom(int bedIDinRoom);

    void setFloorID(int floorID);

    void setDepartment(String department);

    void setStationID(String stationID);

    void setRoomID(int roomID);

    void setLastUpdate(String lastUpdate);

    int getBedIDinRoom();

    int getFloorID();

    String getDepartment();

    String getStationID();

    int getRoomID();

    String getLastUpdate();
}
