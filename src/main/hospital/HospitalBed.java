package hospital;


public class HospitalBed {
    HospitalPatient humanInBed;
    String[] roomInfo;
    public boolean isEmpty(){
        if(humanInBed ==null) return true;
        return false;
    }

    public void setInfo(String[] roomInfo) {
        this.roomInfo = roomInfo;
    }
    public String[] getRoomInfo(){
        return  roomInfo;
    }
}
