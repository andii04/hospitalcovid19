package cruise_ship;

public class Ticket {
    private BookingClass bookingClass;
    private DeckID deckID;
    private String cabinID;

    public DeckID getDeckID() {
        return deckID;
    }

    public void setDeckID(DeckID deckID) {
        this.deckID = deckID;
    }

    public String getCabinID() {
        return cabinID;
    }

    public void setCabinID(String cabinID) {
        this.cabinID = cabinID;
    }
}
