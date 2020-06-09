package cruise_ship;

public class CabinDeck extends Deck {

    public CabinDeck(DeckID id){
        super(id);
        Cabin[] cabins = new Cabin[450];
        for (int i = 0; i<150; i++){
            cabins[i] = new Cabin(CabinLocation.InnerCenter, "Deck: "+id.name()+ " Room number: "+ i);
            cabins[i+150] = new Cabin(CabinLocation.OuterLeft, "Deck: "+id.name()+ " Room number: "+ i+150);
            cabins[i+300] = new Cabin(CabinLocation.OuterRight, "Deck: "+id.name()+ " Room number: "+ i+300);
        }
    }

}
