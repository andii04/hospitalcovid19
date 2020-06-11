package hospital;

public interface ICase {
    int getBedIDinRoom();

    //getter und setter for ReadOnlyInterface
    void setBedIDinRoom(int bedIDinRoom);

    int getFloorID();

    void setFloorID(int floorID);

    String getDepartment();

    void setDepartment(String department);

    String getStationID();

    void setStationID(String stationID);

    int getRoomID();

    void setRoomID(int roomID);

    String getLastUpdate();

    void setLastUpdate(String lastUpdate);
}
