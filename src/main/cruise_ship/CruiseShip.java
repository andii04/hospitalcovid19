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
    private int count= 0;


    public CruiseShip(String name, EventBus eventBus, List<Human> humanList) {
        this.name = name;
        this.humanList = humanList;
        this.eventBus = eventBus;

        createCruiseShip();
        boarding();

        eventBus();
        startSimulation();
        SkyDeck skyDeck = (SkyDeck) deck[8];
        System.out.println(skyDeck.getMedicalService().getQuarantine().getPassengers().size());



    }

    public void eventBus(){
        SkyDeck skyDeck = (SkyDeck) deck[8];
        eventBus.register(skyDeck.getMedicalService());
        //eventBus.post("Hello");
    }

    public void createCruiseShip(){
        deck[1] = new CabinDeck(DeckID.I, this);
        deck[2] = new CabinDeck(DeckID.II, this);
        deck[3] = new CabinDeck(DeckID.III, this);
        deck[4] = new CabinDeck(DeckID.IV, this);
        deck[5] = new CabinDeck(DeckID.V, this);
        deck[6] = new CabinDeck(DeckID.VI, this);
        deck[7] = new CabinDeck(DeckID.VII, this);
        deck[8] = new SkyDeck(DeckID.VIII, this);
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
        for(int day = 1; day <= 14; day++){
            System.out.println();
            System.out.println("Day "+ day + " starts:");

            //Restaurant
            System.out.println("Phase I Restaurant starts:");
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
                        //System.out.println("Infectet");
                        if (Math.random() * 100 < 30) {
                            System.out.println("Infected Person Cought and infected a other Person");
                            selectetHumanForGroup.get(group+1).breathe(selectetHumanForGroup.get(group).dryCough());
                        }
                    }
                    if(selectetHumanForGroup.get(group+1).isInfectedCOVID19()){
                        //System.out.println("Infectet");
                        if (Math.random() * 100 < 90) {
                            System.out.println("Infected Person Cought and infected a other Person");
                            selectetHumanForGroup.get(group).breathe(selectetHumanForGroup.get(group+1).dryCough());
                        }
                    }
                }

                //System.out.println(restaurantPerPhase[j].size());
                //System.out.println(selectetHumanForGroup.size());
            }

            //Phase 2
            System.out.println();
            System.out.println("Phase II FitnessArea / Cinema / ShoppingMall starts:");
            List<Cabin> cabinsToAssign = new ArrayList<>();
            cabinsToAssign.addAll(cabinList);

            //FitnessArea


            int numberOfFitnessAreas = 3;
            ArrayList<Human>[] passengerInFitnessArea = new ArrayList[numberOfFitnessAreas];
            for (int i = 0; i < numberOfFitnessAreas; i++) {
                passengerInFitnessArea[i] = new ArrayList<Human>();
            }
            for(int j = 0; j<passengerInFitnessArea.length; j++){
                int capacityFitnessArea = 150;
                boolean fill = true;
                while (fill == true){
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h: cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityFitnessArea--;
                        if(capacityFitnessArea <=1){
                            fill = false;
                        }
                        passengerInFitnessArea[j].add(h);

                    }
                    cabinsToAssign.remove(randomCabin);
                }
                List<Human> passengerGroup = new ArrayList<>();
                for (int i = 0; i<20; i++){
                    int randPassenger = rand.nextInt(passengerInFitnessArea[j].size());
                    passengerGroup.add(passengerInFitnessArea[j].get(randPassenger));
                    passengerInFitnessArea[j].remove(randPassenger);
                }
                for(int group = 0; group < passengerGroup.size() ; group= group+4) {
                    if(passengerGroup.get(group).isInfectedCOVID19()){
                        if (Math.random() * 100 < 50) {
                            System.out.println("Infected Person Cought and infected four other Persons in FitnessArea");
                            passengerGroup.get(group+1).breathe(passengerGroup.get(group).dryCough());
                            passengerGroup.get(group+2).breathe(passengerGroup.get(group).dryCough());
                            passengerGroup.get(group+3).breathe(passengerGroup.get(group).dryCough());
                        }
                    }
                    if(passengerGroup.get(group+1).isInfectedCOVID19()){
                        if (Math.random() * 100 < 50) {
                            System.out.println("Infected Person Cought and infected four other Persons in FitnessArea");
                            passengerGroup.get(group).breathe(passengerGroup.get(group+1).dryCough());
                            passengerGroup.get(group+2).breathe(passengerGroup.get(group+1).dryCough());
                            passengerGroup.get(group+3).breathe(passengerGroup.get(group+1).dryCough());
                        }
                    }
                    if(passengerGroup.get(group+2).isInfectedCOVID19()){
                        if (Math.random() * 100 < 50) {
                            System.out.println("Infected Person Cought and infected four other Persons in FitnessArea");
                            passengerGroup.get(group).breathe(passengerGroup.get(group+2).dryCough());
                            passengerGroup.get(group+1).breathe(passengerGroup.get(group+2).dryCough());
                            passengerGroup.get(group+3).breathe(passengerGroup.get(group+2).dryCough());
                        }
                    }
                    if(passengerGroup.get(group+3).isInfectedCOVID19()){
                        if (Math.random() * 100 < 50) {
                            System.out.println("Infected Person Cought and infected four other Persons in FitnessArea");
                            passengerGroup.get(group).breathe(passengerGroup.get(group+3).dryCough());
                            passengerGroup.get(group+1).breathe(passengerGroup.get(group+3).dryCough());
                            passengerGroup.get(group+2).breathe(passengerGroup.get(group+3).dryCough());
                        }
                    }
                }
            }


            //Cinema

            int numberOfCinemas = 2;
            ArrayList<Human>[] passengerInCinema = new ArrayList[numberOfCinemas];
            for (int i = 0; i < numberOfCinemas; i++) {
                passengerInCinema[i] = new ArrayList<Human>();
            }
            for(int j = 0; j<passengerInCinema.length; j++){
                int capacityCinema = 225;
                boolean fill = true;
                while (fill == true){
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h: cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityCinema--;
                        if(capacityCinema <=1){
                            fill = false;
                        }
                        passengerInCinema[j].add(h);

                    }
                    cabinsToAssign.remove(randomCabin);
                }
                for (int i = 0; i<passengerInCinema[j].size();i++) {
                    if (passengerInCinema[j].get(i).isInfectedCOVID19()) {
                        if (Math.random() * 100 < 50) {
                            System.out.println("Infected Person Cought and infected two other Persons in Cinema");
                            if (i != 0) {
                                passengerInCinema[j].get(i - 1).breathe(passengerInCinema[j].get(i).dryCough());
                            }
                            if (i != passengerInCinema[j].size() - 1) {
                                passengerInCinema[j].get(i + 1).breathe(passengerInCinema[j].get(i).dryCough());
                            }
                        }
                    }
                }
            }


            //ShoppingMall

            int numberOfShoppingMalls = 2;
            ArrayList<Human>[] passengerInMall = new ArrayList[numberOfShoppingMalls];
            for (int i = 0; i < numberOfShoppingMalls; i++) {
                passengerInMall[i] = new ArrayList<Human>();
            }
            for(int j = 0; j<passengerInMall.length; j++){
                int capacityMall = 1000;
                boolean fill = true;
                while (fill == true){
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h: cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityMall--;
                        if(capacityMall <=1){
                            fill = false;
                        }
                        passengerInMall[j].add(h);

                    }
                    cabinsToAssign.remove(randomCabin);
                }
                for(int i = 0; i<250; i++){
                    int randomPassager1 = rand.nextInt(passengerInMall[j].size());
                    int randomPassager2 = rand.nextInt(passengerInMall[j].size());
                    if(passengerInMall[j].get(randomPassager1).isInfectedCOVID19()){
                        if (Math.random() * 100 < 30) {
                            System.out.println("Infected Person Cought and infected two other Persons in the ShoppingMall");
                            passengerInMall[j].get(randomPassager2).breathe(passengerInMall[j].get(randomPassager1).dryCough());
                        }
                    }
                    if(passengerInMall[j].get(randomPassager2).isInfectedCOVID19()){
                        if (Math.random() * 100 < 30) {
                            System.out.println("Infected Person Cought and infected two other Persons in the ShoppingMall");
                            passengerInMall[j].get(randomPassager2).breathe(passengerInMall[j].get(randomPassager2).dryCough());
                        }
                    }
                }

            }

            System.out.println();
            System.out.println("All infected Person on Day " + day);
            for (Human h:humanList) {
                h.visitImmuneSysteme();
            }

            for (int i = 0; i< cabinList.size(); i++) {
                for (int h= 0;  h<cabinList.get(i).getPassengers().size(); h++) {
                    if(cabinList.get(i).getPassengers().get(h).isHasFever()){
                        cabinList.get(i).releaseEmergencyCall(i, h);
                    }
                }
            }

        }
        for (Human h:humanList) {
            if(h.isInfectedCOVID19()){
                count++;
            }
        }
        System.out.println("Infizierte nach 14 Tagen: "+ count);

    }

    public EventBus getEventBus() {
        return eventBus;
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
