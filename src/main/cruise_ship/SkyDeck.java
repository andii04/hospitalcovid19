package cruise_ship;

public class SkyDeck extends Deck {

    public SkyDeck(DeckID id){
        super(id);
        SkyDeckLocation[] skyDeckLocations = new SkyDeckLocation[12];
        skyDeckLocations[0] = new SkyDeckLocation(SkyDeskLocationType.FitnessArea, 50);
        skyDeckLocations[1] = new SkyDeckLocation(SkyDeskLocationType.FitnessArea, 50);
        skyDeckLocations[2] = new SkyDeckLocation(SkyDeskLocationType.FitnessArea, 50);
        skyDeckLocations[3] = new SkyDeckLocation(SkyDeskLocationType.Restaurant, 250);
        skyDeckLocations[4] = new SkyDeckLocation(SkyDeskLocationType.Restaurant, 250);
        skyDeckLocations[5] = new SkyDeckLocation(SkyDeskLocationType.Restaurant, 250);
        skyDeckLocations[6] = new SkyDeckLocation(SkyDeskLocationType.Restaurant, 250);
        skyDeckLocations[7] = new SkyDeckLocation(SkyDeskLocationType.Restaurant, 250);
        skyDeckLocations[8] = new SkyDeckLocation(SkyDeskLocationType.Cinema, 225);
        skyDeckLocations[9] = new SkyDeckLocation(SkyDeskLocationType.Cinema, 225);
        skyDeckLocations[10] = new SkyDeckLocation(SkyDeskLocationType.ShoppingMall, 1000);
        skyDeckLocations[11] = new SkyDeckLocation(SkyDeskLocationType.ShoppingMall, 1000);
        MedicalServices medicalService = new MedicalServices();
    }
}
