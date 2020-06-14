package cruise_ship;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import hospital.*;
import shared.Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CruiseShip {
    List<Human> humanList = new ArrayList<>();
    List<Cabin> cabinList = new ArrayList<>();
    private String name;
    private EventBus eventBus;
    private Deck[] deck = new Deck[9];
    private int count = 0;
    private Hospital hospital;


    //Constructor to create the Cruise Ship
    public CruiseShip(String name, EventBus eventBus, List<Human> humanList, Hospital hospital) {
        this.name = name;
        this.humanList = humanList;
        this.eventBus = eventBus;
        this.hospital = hospital;
    }

    public void eventBus() {
        //Register the EventBus Listener
        SkyDeck skyDeck = (SkyDeck) deck[8];
        eventBus.register(skyDeck.getMedicalService());
        eventBus.register(this);
    }

    public void createCruiseShip() {
        //Create the different decks of the Ship with its deck number
        deck[1] = new CabinDeck(DeckID.I, this);
        deck[2] = new CabinDeck(DeckID.II, this);
        deck[3] = new CabinDeck(DeckID.III, this);
        deck[4] = new CabinDeck(DeckID.IV, this);
        deck[5] = new CabinDeck(DeckID.V, this);
        deck[6] = new CabinDeck(DeckID.VI, this);
        deck[7] = new CabinDeck(DeckID.VII, this);
        deck[8] = new SkyDeck(DeckID.VIII, this);

        //Boarding the Ship
        boarding();
        eventBus();

        //Start the simulation
        startSimulation();
    }

    public void boarding() {
        BufferedReader in = null;
        String zeile = null;

        //Assign the Humans to the Rooms like the File
        try {
            in = new BufferedReader(new FileReader("data/cruise_ship_passenger_assignment.txt"));
            int countHuman = 0;
            //Step all Lines in the File
            while ((zeile = in.readLine()) != null) {
                String[] column = zeile.split(";");
                //Check if in the Room one Passenger or two Passenger and call the Function to set (1times or 2times)
                if (Integer.parseInt(column[3]) == 2) {
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

    public void setPassengerToRoom(String[] column, int count, boolean firstPerson) {
        //Define the Deck and the Position of the Cabin
        CabinDeck cabinDeck = (CabinDeck) deck[DeckID.valueOf(column[0]).getIndex()];
        String[] cabinInfo = column[2].split("-");
        int cabinID = 0;
        switch (column[1]) {
            case "OuterLeft":
                cabinID = Integer.parseInt(cabinInfo[2]);
                break;
            case "InnerCenter":
                cabinID = Integer.parseInt(cabinInfo[2]) + 150;
                break;
            case "OuterRight":
                cabinID = Integer.parseInt(cabinInfo[2]) + 300;
                break;
        }
        //Add the Passenger to the Cabin
        cabinDeck.addPassenger(cabinID, humanList.get(count));
        if (firstPerson) {
            //Add the Cabin to a CabinList with all Cabins
            cabinList.add(cabinDeck.getCabins()[cabinID - 1]);
        }

        //Every Human has a Ticket and now set the Cabin and the Deck to it
        humanList.get(count).getTicket().setCabinID(column[2]);
        humanList.get(count).getTicket().setDeckID(DeckID.valueOf(column[0]));
    }

    //Listener of the EventBus
    @Subscribe
    public void notifyHospital(String event) {
        System.out.println("CruiseShip: Calling Hospital");
        if (event == "QuarantineOccupied") {
            CommandCOVID19Emergency commandCOVID19Emergency = new CommandCOVID19Emergency(hospital, this);
            commandCOVID19Emergency.execute();
        }

    }

    public void startSimulation() {
        //Simulation starts
        System.out.println("Simulation in Cruise Ship starts");

        //simulate 14 days
        for (int day = 1; day <= 14; day++) {
            System.out.println();
            System.out.println("Day " + day + " starts:");

            //Restaurant

            System.out.println("Phase I Restaurant starts:");
            int seats = 250;
            int restaurants = 5;

            //Define the Count of Day Phases with Restaurant
            int phasenInRestaurant = humanList.size() / (seats * restaurants) + 1;
            int sumRestaurantPhase = restaurants * phasenInRestaurant;

            //Create for each Phase a ArrayList with the Passenger are in the Restaurant in this Phase
            ArrayList<Human>[] restaurantPerPhase = new ArrayList[sumRestaurantPhase];
            for (int i = 0; i < sumRestaurantPhase; i++) {
                restaurantPerPhase[i] = new ArrayList<Human>();
            }

            Random rand = new Random();

            for (Cabin actCabin : cabinList) {
                boolean freeRestaurantFound = false;

                //For each Cabin set a Phase
                while (freeRestaurantFound == false) {
                    int randNumb = rand.nextInt(sumRestaurantPhase);
                    if (restaurantPerPhase[randNumb].size() < seats) {
                        //Phase has free seats
                        for (Human h : actCabin.getPassengers()) {
                            //Place the Passengers of the Cabin in this Restaurant Phase
                            restaurantPerPhase[randNumb].add(h);
                        }
                        freeRestaurantFound = true;
                    }
                }
            }
            for (int j = 0; j < restaurantPerPhase.length; j++) {
                //50% of the Passengers in the Restaurant should bild a group
                int fiftyPercent = restaurantPerPhase[j].size() / 2;
                List<Human> selectetHumanForGroup = new ArrayList<>();

                //Select 50% Random Passengers
                for (int i = 0; i < fiftyPercent; i++) {
                    boolean alreadypresent = true;
                    int randNum = 0;
                    while (alreadypresent == true) {
                        alreadypresent = false;
                        randNum = rand.nextInt(restaurantPerPhase[j].size());
                        for (Human hum : selectetHumanForGroup) {
                            if (hum == restaurantPerPhase[j].get(randNum)) {
                                alreadypresent = true;
                                break;
                            }
                        }
                    }

                    //Put the random Passenger to a List
                    selectetHumanForGroup.add(restaurantPerPhase[j].get(randNum));
                }

                //If odd then delete a random Passenger
                if (selectetHumanForGroup.size() % 2 != 0) {
                    selectetHumanForGroup.remove(rand.nextInt(selectetHumanForGroup.size()));
                }

                for (int group = 0; group < selectetHumanForGroup.size(); group = group + 2) {
                    //Check if the Passenger one of the group has Covid19 and can infect the other
                    if (selectetHumanForGroup.get(group).isInfectedCOVID19()&&!selectetHumanForGroup.get(group + 1).isInfectedCOVID19()) {
                        //coughs with a probability of 30 percent
                        if (Math.random() * 100 < 30) {
                            System.out.println("Infected Person Cought and infected a other Person in the Restaurant");
                            selectetHumanForGroup.get(group + 1).breathe(selectetHumanForGroup.get(group).dryCough());
                        }
                    }
                    //Check if the Passenger two of the group has Covid19 and can infect the other
                    if (selectetHumanForGroup.get(group + 1).isInfectedCOVID19()&&!selectetHumanForGroup.get(group).isInfectedCOVID19()) {
                        //coughs with a probability of 30 percent
                        if (Math.random() * 100 < 30) {
                            System.out.println("Infected Person Cought and infected a other Person in the Restaurant");
                            selectetHumanForGroup.get(group).breathe(selectetHumanForGroup.get(group + 1).dryCough());
                        }
                    }
                }
            }

            //Phase 2
            System.out.println();
            System.out.println("Phase II FitnessArea / Cinema / ShoppingMall starts:");

            //Copy all Cabins in a new List, where can remove
            List<Cabin> cabinsToAssign = new ArrayList<>();
            cabinsToAssign.addAll(cabinList);

            //FitnessArea

            int numberOfFitnessAreas = 3;
            ArrayList<Human>[] passengerInFitnessArea = new ArrayList[numberOfFitnessAreas];
            for (int i = 0; i < numberOfFitnessAreas; i++) {
                passengerInFitnessArea[i] = new ArrayList<Human>();
            }
            for (int j = 0; j < passengerInFitnessArea.length; j++) {
                int capacityFitnessArea = 150;
                boolean fill = true;
                //Select random Cabins to the FitnessArea
                while (fill == true) {
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h : cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityFitnessArea--;
                        if (capacityFitnessArea <= 1) {
                            fill = false;
                        }
                        //Put the Passenger of the Cabin in the Fitnessarea
                        passengerInFitnessArea[j].add(h);

                    }
                    //Remove the Cabin from the List that they are not reselect from another
                    cabinsToAssign.remove(randomCabin);
                }

                //Select 20 random Passengers for Groub Bilding
                List<Human> passengerGroup = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    int randPassenger = rand.nextInt(passengerInFitnessArea[j].size());
                    passengerGroup.add(passengerInFitnessArea[j].get(randPassenger));
                    passengerInFitnessArea[j].remove(randPassenger);
                }

                //Infection in the 4passenger group
                for (int group = 0; group < passengerGroup.size(); group = group + 4) {
                    //Check if the Passenger of the group has Covid19 and can infect the other
                    if (passengerGroup.get(group).isInfectedCOVID19()) {
                        //coughs with a probability of 50 percent
                        if (Math.random() * 100 < 50) {
                            int infectcount = 0;
                            //Infect the others if they don't have
                            if(!passengerGroup.get(group + 1).isInfectedCOVID19()){
                                passengerGroup.get(group + 1).breathe(passengerGroup.get(group).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 2).isInfectedCOVID19()){
                                passengerGroup.get(group + 2).breathe(passengerGroup.get(group).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 3).isInfectedCOVID19()){
                                passengerGroup.get(group + 3).breathe(passengerGroup.get(group).dryCough());
                                infectcount++;
                            }
                            System.out.println("Infected Person Cought and infected "+infectcount+" other Person(s) in FitnessArea");
                        }
                    }
                    if (passengerGroup.get(group + 1).isInfectedCOVID19()) {
                        //coughs with a probability of 50 percent
                        if (Math.random() * 100 < 50) {
                            int infectcount = 0;
                            //Infect the others if they don't have
                            if(!passengerGroup.get(group).isInfectedCOVID19()){
                                passengerGroup.get(group).breathe(passengerGroup.get(group+1).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 2).isInfectedCOVID19()){
                                passengerGroup.get(group + 2).breathe(passengerGroup.get(group+1).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 3).isInfectedCOVID19()){
                                passengerGroup.get(group + 3).breathe(passengerGroup.get(group+1).dryCough());
                                infectcount++;
                            }
                            System.out.println("Infected Person Cought and infected "+infectcount+" other Person(s) in FitnessArea");
                        }
                    }
                    if (passengerGroup.get(group + 2).isInfectedCOVID19()) {
                        //coughs with a probability of 50 percent
                        if (Math.random() * 100 < 50) {
                            //Infect the others if they don't have
                            int infectcount = 0;
                            if(!passengerGroup.get(group).isInfectedCOVID19()){
                                passengerGroup.get(group).breathe(passengerGroup.get(group+2).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 1).isInfectedCOVID19()){
                                passengerGroup.get(group + 1).breathe(passengerGroup.get(group+2).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 3).isInfectedCOVID19()){
                                passengerGroup.get(group + 3).breathe(passengerGroup.get(group+2).dryCough());
                                infectcount++;
                            }
                            System.out.println("Infected Person Cought and infected "+infectcount+" other Person(s) in FitnessArea");
                        }
                    }
                    if (passengerGroup.get(group + 3).isInfectedCOVID19()) {
                        //coughs with a probability of 50 percent
                        if (Math.random() * 100 < 50) {
                            //Infect the others if they don't have
                            int infectcount = 0;
                            if(!passengerGroup.get(group).isInfectedCOVID19()){
                                passengerGroup.get(group).breathe(passengerGroup.get(group+3).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 1).isInfectedCOVID19()){
                                passengerGroup.get(group + 1).breathe(passengerGroup.get(group+3).dryCough());
                                infectcount++;
                            }
                            if(!passengerGroup.get(group + 2).isInfectedCOVID19()){
                                passengerGroup.get(group + 2).breathe(passengerGroup.get(group+3).dryCough());
                                infectcount++;
                            }
                            System.out.println("Infected Person Cought and infected "+infectcount+" other Person(s) in FitnessArea");
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
            for (int j = 0; j < passengerInCinema.length; j++) {
                int capacityCinema = 225;
                boolean fill = true;

                //Select random Cabins to the Cinema
                while (fill == true) {
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h : cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityCinema--;
                        if (capacityCinema <= 1) {
                            fill = false;
                        }
                        //Put the Passenger of the Cabin in the Cinema
                        passengerInCinema[j].add(h);

                    }
                    //Remove the Cabin from the List that they are not reselect from another
                    cabinsToAssign.remove(randomCabin);
                }
                for (int i = 0; i < passengerInCinema[j].size(); i++) {
                    //Infection to the left and the right Passenger
                    if (passengerInCinema[j].get(i).isInfectedCOVID19()) {
                        //coughs with a probability of 50 percent
                        if (Math.random() * 100 < 50) {
                            //Infect the others if they don't have
                            int infectetcount = 0;
                            if (i != 0) {
                                if(!passengerInCinema[j].get(i -1).isInfectedCOVID19()){
                                    passengerInCinema[j].get(i - 1).breathe(passengerInCinema[j].get(i).dryCough());
                                    infectetcount++;
                                }
                            }
                            if (i != passengerInCinema[j].size() - 1) {
                                if(!passengerInCinema[j].get(i +1).isInfectedCOVID19()){
                                    passengerInCinema[j].get(i + 1).breathe(passengerInCinema[j].get(i).dryCough());
                                    infectetcount++;
                                }
                            }
                            System.out.println("Infected Person Cought and infected "+infectetcount+" other Persons in Cinema");
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
            for (int j = 0; j < passengerInMall.length; j++) {
                int capacityMall = 1000;
                boolean fill = true;

                ////Select random Cabins to the Mall
                while (fill == true) {
                    int randomCabin = rand.nextInt(cabinsToAssign.size());
                    for (Human h : cabinsToAssign.get(randomCabin).getPassengers()) {
                        capacityMall--;
                        if (capacityMall <= 1) {
                            fill = false;
                        }
                        ////Put the Passenger of the Cabin in the Mall
                        passengerInMall[j].add(h);
                    }
                    //Remove the Cabin from the List that they are not reselect from another
                    cabinsToAssign.remove(randomCabin);
                }
                //simulate 250 meetings
                for (int i = 0; i < 250; i++) {
                    //Select two Random Passenger in the Mall
                    int randomPassager1 = rand.nextInt(passengerInMall[j].size());
                    int randomPassager2 = rand.nextInt(passengerInMall[j].size());
                    if (passengerInMall[j].get(randomPassager1).isInfectedCOVID19()) {
                        if (Math.random() * 100 < 30) {
                            //Infect the others if they don't have
                            if(!passengerInMall[j].get(i +1).isInfectedCOVID19()) {
                                System.out.println("Infected Person Cought and infected a other Persons in the ShoppingMall");
                                passengerInMall[j].get(randomPassager2).breathe(passengerInMall[j].get(randomPassager1).dryCough());
                            }
                        }
                    }
                    if (passengerInMall[j].get(randomPassager2).isInfectedCOVID19()) {
                        if (Math.random() * 100 < 30) {
                            //Infect the others if they don't have
                            System.out.println("Infected Person Cought and infected a other Persons in the ShoppingMall");
                            passengerInMall[j].get(randomPassager2).breathe(passengerInMall[j].get(randomPassager2).dryCough());
                        }
                    }
                }
            }

            System.out.println();
            for (Human h : humanList) {
                //Activate the immuneSystem fpr every Passenger
                h.visitImmuneSysteme();
            }

            for (int i = 0; i < cabinList.size(); i++) {
                for (int h = 0; h < cabinList.get(i).getPassengers().size(); h++) {
                    //Check every if a person has Fever
                    if (cabinList.get(i).getPassengers().get(h).isHasFever()) {
                        //If they has Fever a Event "Emergeny" is triggerd
                        cabinList.get(i).releaseEmergencyCall(i, h);
                    }
                }
            }

        }
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void vehicleArrive(BioSafetyEmergencyVehicle vehicle) {
        Random r = new Random();
        int randomInt = r.nextInt(vehicle.getNumberOfMedicalStaffs());
        MedicalStaff chosenPersonFromVehicle = vehicle.getMedicalStaffs(randomInt);
        //medicalStaff gets Stretcher out
        Stretcher stretcher = chosenPersonFromVehicle.getStretcherOutOfVehicle(vehicle);
        //medicalStaff sets stretcher down
        chosenPersonFromVehicle.operateStretcherDown(stretcher);
        //dein medical muss den holen und an meinen hier drunter übergeben
        Human quarantineHuman = ((SkyDeck) deck[8]).getMedicalService().getMedicalAssistant().getPassengerFromQuarantine();
        //medicalStaff sets human on stretcher
        chosenPersonFromVehicle.helpPassengerOnStretcher(stretcher, quarantineHuman);
        ((SkyDeck) deck[8]).getMedicalService().getQuarantine().addPassenger(null);
        //medicalStaff sets stretcher up
        chosenPersonFromVehicle.operateStretcherUp(stretcher);
        //medicalStaff sets stretcher back in vehicle
        chosenPersonFromVehicle.getStretcherInVehicle(stretcher, vehicle);


        // @todo fährt zurck zum Chain f responsibility entrance
        //Spread virus in Car
        //Cought up to 10times
        for(int i = 0; i<10; i++){
            //With a probability of 60 percent
            if (Math.random() * 100 < 60) {
                vehicle.changeAmbientAir(quarantineHuman.dryCough());
            }
        }
        //move back
        vehicle.move("Hospital");


        ((BSEmergencyDepartment) hospital.getFloor(0).getDepartments(0)).welcome(vehicle);
    }

    public List<Cabin> getCabinList() {
        return cabinList;
    }

    public List<Human> getHumanList() {
        return humanList;
    }

    public static class Builder {

        List<Human> humanList = new ArrayList<Human>();
        private String name;
        private EventBus eventBus;
        private Hospital hospital;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHospital(Hospital hospital) {
            this.hospital = hospital;
            return this;
        }

        public Builder setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public Builder setHumanList(List<Human> humanList) {
            this.humanList = humanList;
            return this;
        }

        public CruiseShip build() {
            return new CruiseShip(name, eventBus, humanList, hospital);
        }
    }
}
