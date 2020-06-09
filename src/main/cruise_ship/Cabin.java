package cruise_ship;

public class Cabin {
    private String id;
    private CabinLocation location;

    public void releaseEmergencyCall(){

    }
    public Cabin(CabinLocation location, String id){
        this.location = location;
        this.id = id;
    }
}
