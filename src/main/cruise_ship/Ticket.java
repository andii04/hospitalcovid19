package cruise_ship;

public class Ticket {
    private BookingClass bookingClass;
    private DeckID deckID;
    private String cabinID;


    public void setDeckID(DeckID deckID) {
        this.deckID = deckID;
    }

    public void setCabinID(String cabinID) {
        this.cabinID = cabinID;
    }
}
