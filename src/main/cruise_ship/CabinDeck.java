package cruise_ship;

import shared.Human;

public class CabinDeck extends Deck {
    private Cabin[] cabins = new Cabin[450];

    public CabinDeck(DeckID id){
        super(id);
        for (int i = 0; i<150; i++){
            cabins[i] = new Cabin(CabinLocation.OuterLeft, id.name()+ "-OL-"+ i+1);
            cabins[i+150] = new Cabin(CabinLocation.InnerCenter, id.name()+ "-IC-"+ i+1);
            cabins[i+300] = new Cabin(CabinLocation.OuterRight, id.name()+ "-OR-"+ i+1);
        }
    }

    public void addPassenger(int cabinID, Human passenger){
        cabins[cabinID-1].addPassenger(passenger);
    }

    public Cabin[] getCabins() {
        return cabins;
    }
}
