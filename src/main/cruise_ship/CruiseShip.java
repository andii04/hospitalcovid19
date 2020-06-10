package cruise_ship;

import com.google.common.eventbus.EventBus;
import shared.Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CruiseShip {
    private String name;
    private EventBus eventBus;
    private Deck[] deck = new Deck[9];
    List<Human> humanList = new ArrayList<Human>();


    public CruiseShip(String name, EventBus eventBus, List<Human> humanList) {
        this.name = name;
        this.eventBus = eventBus;
        this.humanList = humanList;
        createCruiseShip();
        boarding();
        startSimulation();
    }
    public void createCruiseShip(){
        deck[1] = new CabinDeck(DeckID.I);
        deck[2] = new CabinDeck(DeckID.II);
        deck[3] = new CabinDeck(DeckID.III);
        deck[4] = new CabinDeck(DeckID.IV);
        deck[5] = new CabinDeck(DeckID.V);
        deck[6] = new CabinDeck(DeckID.VI);
        deck[7] = new CabinDeck(DeckID.VII);
        deck[8] = new SkyDeck(DeckID.VIII);
    }

    public void boarding(){
        BufferedReader in = null;
        String zeile = null;
        try {
            in = new BufferedReader(new FileReader("data/cruise_ship_passenger_assignment.txt"));
            int count = 0;
            while ((zeile = in.readLine()) != null) {
                String[] column =  zeile.split(";");
                //System.out.println(column[3]);
                if(column[3] == "2"){
                    humanList.get(count).getTicket().setCabinID(column[2]);
                    humanList.get(count).getTicket().setDeckID(DeckID.valueOf(column[0]));
                    count++;
                    //TODO
                }
                humanList.get(count).getTicket().setCabinID(column[2]);
                humanList.get(count).getTicket().setDeckID(DeckID.valueOf(column[0]));
                count++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notifyHospital(String message){

    }

    public void startSimulation(){
        System.out.println(humanList.get(10).getTicket().getCabinID());
        for (int actDay = 0 ; actDay <14 ; actDay++){

        }

    }

    public static class Builder{

        private String name;
        private EventBus eventBus;
        List<Human> humanList = new ArrayList<Human>();

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }
        public Builder setHumanList(List<Human> humanList){
            this.humanList =humanList;
            return this;
        }

        public CruiseShip build() {
            return new CruiseShip(name, eventBus, humanList);
        }
    }
}
