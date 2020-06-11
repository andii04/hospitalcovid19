package hospital;


public class HospitalBed {
    HospitalPatient humanInBed;
    String[] roomInfo;

    public boolean isEmpty() {
        return humanInBed == null;
    }

    public void setInfo(String[] roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String[] getRoomInfo() {
        return roomInfo;
    }

    public HospitalPatient getHuman() {
        return humanInBed;
    }
}

