package cruise_ship;

public class CabinDeck extends Deck {

    public CabinDeck(DeckID id){
        super(id);
        Cabin[] cabins = new Cabin[451];
        for (int i = 1; i<=150; i++){
            cabins[i] = new Cabin(CabinLocation.OuterLeft, id.name()+ "-OL-"+ i);
            cabins[i+150] = new Cabin(CabinLocation.InnerCenter, id.name()+ "-IC-"+ i);
            cabins[i+300] = new Cabin(CabinLocation.OuterRight, id.name()+ "-OR-"+ i);
        }
    }

}
