package hospital;

public class Case implements ICase {
    private int bedIDinRoom;
    private int floorID;
    private String department;
    private String stationID;
    private int roomID;
    private String lastUpdate;
    private String firstName;
    private String lastName;
    private String birthDate;
    private boolean isSmoking;
    private boolean hasAsthma;
    private boolean hasHIV;
    private boolean isInfectedCOVID19;
    private boolean hasFever;
    private boolean hasTaste;
    private int patientID;

    public Case(int patientID, String firstName, String lastName, String birthDate, boolean isSmoking, boolean hasAsthma, boolean hasHIV, boolean isInfectedCOVID19, boolean hasFever, boolean hasTaste, int floorID, String department, String stationID, int roomID, int bedIDinRoom, String lastUpdate) {
        this.floorID = floorID;
        this.department = department;
        this.stationID = stationID;
        this.roomID = roomID;
        this.bedIDinRoom = bedIDinRoom;
        this.lastUpdate = lastUpdate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isSmoking = isSmoking;
        this.hasAsthma = hasAsthma;
        this.hasHIV = hasHIV;
        this.isInfectedCOVID19 = isInfectedCOVID19;
        this.hasFever = hasFever;
        this.hasTaste = hasTaste;
        this.patientID = patientID;
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

    public int getID() {
        return patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public boolean hasCovid19() {
        return  isInfectedCOVID19;
    }
}
