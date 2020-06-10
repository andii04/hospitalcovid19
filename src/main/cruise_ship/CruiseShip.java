package cruise_ship;

import com.google.common.eventbus.EventBus;
import shared.Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CruiseShip {
    private String name;
    private EventBus eventBus;
    private Deck[] deck = new Deck[9];
    List<Human> humanList = new ArrayList<>();
    List<Cabin> cabinList = new ArrayList<>();


    public CruiseShip(String name, EventBus eventBus1, List<Human> humanList) {
        this.name = name;
        this.humanList = humanList;
        EventBus eventBus = new EventBus();

        createCruiseShip();
        boarding();
        startSimulation();
        CabinDeck cabinDeck = (CabinDeck) deck[7];
        //System.out.println(cabinDeck.getCabins()[449].getPassengers().get(0).getLungLeft().getStructure()[0][0][0].getLungCell()[0][0][0]);
        //System.out.println(cabinList.size());
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
            int countHuman = 0;
            while ((zeile = in.readLine()) != null) {
                String[] column =  zeile.split(";");
                if(Integer.parseInt(column[3]) == 2){
                    setPassengerToRoom(column, countHuman, false);
                    countHuman++;
                }
                setPassengerToRoom(column, countHuman, true);

                countHuman++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPassengerToRoom(String[] column, int count, boolean firstPerson){
        humanList.get(count).getTicket().setCabinID(column[2]);
        humanList.get(count).getTicket().setDeckID(DeckID.valueOf(column[0]));
        CabinDeck cabinDeck = (CabinDeck) deck[DeckID.valueOf(column[0]).getIndex()];

        String cabinInfo[] = column[2].split("-");
        int cabinID =0;
        switch (column[1]){
            case "OuterLeft":
                cabinID = Integer.parseInt(cabinInfo[2]);
                break;
            case "InnerCenter":
                cabinID = Integer.parseInt(cabinInfo[2])+150;
                break;
            case "OuterRight":
                cabinID = Integer.parseInt(cabinInfo[2])+300;
                break;
        }
        cabinDeck.addPassenger(cabinID, humanList.get(count));
        if(firstPerson){
            cabinList.add(cabinDeck.getCabins()[cabinID-1]);
        }
    }

    public void notifyHospital(String message){

    }

    public void startSimulation(){
        System.out.println("Simulation in Cruise Ship starts");
        for(int day = 0; day < 1; day++){
            //System.out.println("Tag "+ day+1 + " startet");
            int seats = 250;
            int restaurants = 5;
            int phasenInRestaurant = humanList.size() / (seats*restaurants) + 1;
            int sumRestaurantPhase = restaurants*phasenInRestaurant;
            ArrayList<Human>[] restaurantPerPhase = new ArrayList[sumRestaurantPhase];
            for (int i = 0; i < sumRestaurantPhase; i++) {
                restaurantPerPhase[i] = new ArrayList<Human>();
            }

            Random rand = new Random();

            for (Cabin actCabin: cabinList) {
                boolean freeRestaurantFound = false;

                while (freeRestaurantFound == false){
                    int randNumb = rand.nextInt(sumRestaurantPhase);
                    if(restaurantPerPhase[randNumb].size()<seats){
                        for (Human h: actCabin.getPassengers()) {
                            restaurantPerPhase[randNumb].add(h);

                        }
                        freeRestaurantFound=true;
                    }
                }
            }
            for(int j = 0; j<restaurantPerPhase.length; j++){
                int fiftyPercent = restaurantPerPhase[j].size()/2;
                List<Human> selectetHumanForGroup = new ArrayList<>();
                for (int i = 0; i < fiftyPercent; i++){
                    boolean alreadypresent = true;
                    int randNum =0;
                    while (alreadypresent == true){
                        alreadypresent= false;
                        randNum = rand.nextInt(restaurantPerPhase[j].size());
                        for (Human hum: selectetHumanForGroup) {
                            if(hum==restaurantPerPhase[j].get(randNum)){
                                alreadypresent=true;
                                break;
                            }
                        }
                    }

                    selectetHumanForGroup.add(restaurantPerPhase[j].get(randNum));
                }

                if (selectetHumanForGroup.size()%2 != 0) {
                    selectetHumanForGroup.remove(rand.nextInt(selectetHumanForGroup.size()));
                }

                for(int group = 0; group < selectetHumanForGroup.size() ; group= group+2){
                    if(selectetHumanForGroup.get(group).isInfectedCOVID19()){
                        System.out.println("Infectet");
                        if (Math.random() * 100 < 30) {
                            System.out.println("Hustet");
                        }
                    }
                    if(selectetHumanForGroup.get(group+1).isInfectedCOVID19()){
                        System.out.println("Infectet");
                        if (Math.random() * 100 < 30) {
                            System.out.println("Hustet");
                        }
                    }
                }

                //System.out.println(restaurantPerPhase[j].size());
                //System.out.println(selectetHumanForGroup.size());
            }
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
