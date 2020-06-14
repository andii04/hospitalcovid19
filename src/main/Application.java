import com.google.common.eventbus.EventBus;
import cruise_ship.*;
import hospital.*;
import shared.Human;
import shared.Nationality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    static CruiseShip cruiseShip;
    static Hospital hospital;


    //Main starts
    public static void main(String... args) {
        //Declare a List for all Humans
        List<Human> humanList = new ArrayList<Human>();

        //Function to fill the List
        createHumans(humanList);

        //Function to create and declare the Hospital
        createHospital();

        //Create the Cruise Ship with Name "Symphony of the Seas"
        cruiseShip = new CruiseShip.Builder()
                .setName("Symphony of the Seas")
                .setEventBus(new EventBus())
                .setHumanList(humanList)
                .setHospital(hospital)
                .build();
        cruiseShip.createCruiseShip();

        System.out.println("Simulation finished");
        System.out.println();
        System.out.println("Analysis:");
        lambdaAnalysisShip();
        lambdaAnalysisHospital();
    }

    private static void lambdaAnalysisHospital() {
        System.out.println("Hospital:");
        Map<String, Character> mapNametoStation = new HashMap<>();
        //1)
        hospital.getFloor(1).getDepartments(0).getStations().forEach(station -> {
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        mapNametoStation.put(room.getHospitalBed(i).getHuman().getLastName(),station.getName().charAt(0));
                    }
                }
            });
        });
        System.out.println("List of patients in stations sorted");
        mapNametoStation.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
                .sorted(Map.Entry.comparingByKey())
                .forEach(stringStringEntry -> System.out.println("On Station " + stringStringEntry.getValue()+ " is Patient " + stringStringEntry.getKey()));


        //2)
        AtomicInteger counterPatients = new AtomicInteger();
        hospital.getFloor(1).getDepartments(0).getStations().forEach(station -> {
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        counterPatients.getAndIncrement();
                    }
                }
            });
        });
        System.out.println("Total patients in hospital: " + counterPatients);

        //3)
        HashMap<Character, Integer> counterStation = new HashMap<>();
        hospital.getFloor(1).getDepartments(0).getStations().forEach(station -> {
            AtomicInteger counterPatientsStation = new AtomicInteger();
            station.getRooms().forEach(room -> {
                for(int i=0;i<room.getNumberOfBeds();i++){
                    if(room.getHospitalBed(i)!=null && room.getHospitalBed(i).getHuman()!=null){
                        counterPatientsStation.getAndIncrement();
                    }
                }
                counterStation.put(station.getName().charAt(0),counterPatientsStation.intValue());
            });
        });
        counterStation.entrySet().stream().forEach(entry -> System.out.println("On Station " + entry.getKey() + " are " + entry.getValue() + " patients."));
    }

    private static void lambdaAnalysisShip() {
        System.out.println("CruiseShip:");
        AtomicInteger passengerOnBoard = new AtomicInteger();
        AtomicInteger infectedPassenger = new AtomicInteger();
        AtomicInteger smoker = new AtomicInteger();
        AtomicInteger preIllness = new AtomicInteger();

        cruiseShip.getHumanList().forEach(human -> {
            passengerOnBoard.getAndIncrement();
            if(human.isInfectedCOVID19()){
                infectedPassenger.getAndIncrement();
                if(human.isSmoking()){
                    smoker.getAndIncrement();
                }
                if(human.isHasHIV() || human.isHasAsthma()){
                    preIllness.getAndIncrement();
                }
            }
        });

        AtomicInteger passengerOnBoardNow = new AtomicInteger();
        AtomicInteger infectedPassengerOnBoard = new AtomicInteger();
        List<DeckID> decks = new ArrayList<>();
        List<Nationality> nationality = new ArrayList<>();
        List<CabinLocation> bookingLocation = new ArrayList<>();
        cruiseShip.getCabinList().forEach(cabin -> {
            cabin.getPassengers().forEach(human -> {
                passengerOnBoardNow.getAndIncrement();
                if(human.isInfectedCOVID19()){
                    infectedPassengerOnBoard.getAndIncrement();
                    decks.add(cabin.getDeckid());
                    nationality.add(human.getNationality());
                    bookingLocation.add(cabin.getLocation());
                }
            });
        });
        Map<DeckID, Long> resultAfterDeck =
                decks.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        Map<Nationality, Long> resultAfterNationality =
                nationality.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        Map<DeckID, Long> finalMapAfterDeck = new LinkedHashMap<>();
        //Sort a map and add to finalMap
        resultAfterDeck.entrySet().stream()
                .sorted(Map.Entry.<DeckID, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMapAfterDeck.put(e.getKey(), e.getValue()));

        Map<Nationality, Long> finalMapAfterNationality = new LinkedHashMap<>();
        //Sort a map and add to finalMap
        resultAfterNationality.entrySet().stream()
                .sorted(Map.Entry.<Nationality, Long>comparingByValue()
                        .reversed()).forEachOrdered(e -> finalMapAfterNationality.put(e.getKey(), e.getValue()));

        System.out.println("After 14 Days there are " + infectedPassenger.get()+" Infected People and "+infectedPassengerOnBoard + " are actually on Board");
        System.out.println(((double)infectedPassenger.get() / (double)passengerOnBoard.get())*100.0 + "% of the passengers on board are infected");
        System.out.println("Infected Passenger After Deck: "+ finalMapAfterDeck);
        System.out.println("Infected Passenger After Nationality: "+ finalMapAfterNationality);
        System.out.println(((double)smoker.get() / (double) infectedPassenger.get())*100 + "% of the infected Passenger are smoking");
        System.out.println(((double)preIllness.get() / (double) infectedPassenger.get())*100 + "% of the infected Passenger have a pre-illness");


        //what the hack
        /*
        hospital.getFloor(1).getDepartments(0).getStations().stream()
                .forEach(station -> station.getRooms().stream()
                .forEach(room -> Arrays.stream(room.getHospitalBeds())
                .forEach(hospitalBed -> hospitalBed.getHuman())));
        hospital.getFloor(1).getDepartments(0).getStations().stream()
                .forEach(station -> station.getRooms().stream()
                        .forEach(room -> Arrays.stream(room.getHospitalBeds())
                                .filter(hospitalBed -> hospitalBed.isEmpty()==false).count()));

        hospital.getFloor(1).getDepartments(0).getStations().stream()
                .map(station -> station.getRooms().stream()
                        .forEach(room -> Arrays.stream(room.getHospitalBeds())
                                .collect(hospitalBed -> hospitalBed.getHuman().getFirstName())));
        hospital.getFloor(1).getDepartments(0).getStations().stream()
                .forEach(station -> station.getRooms()
                        .forEach(room -> Arrays.stream(room.getHospitalBeds())
                                .collect(station,(hospitalBed) -> hospitalBed.getHuman()));

         */
    }

    public static boolean createHumans(List<Human> humanList) {
        BufferedReader in = null;
        List<String> firstname = new ArrayList<String>();
        List<String> lastname = new ArrayList<String>();
        String[] birthdate = null;
        String[] nationality = null;
        String[] isSmoking = null;
        String[] hasAsthma = null;
        String[] hasHIV = null;
        String[] hasInfection = null;

        String line = "";

        //Read all the .csv and .txt Files and set the value to local Variables

        try {
            in = new BufferedReader(new FileReader("data/name.txt"));
            String zeile = null;
            int count = 0;
            while ((zeile = in.readLine()) != null) {
                String[] name = zeile.split(" ");
                firstname.add(name[0]);
                lastname.add(name[1]);
                count++;
            }
            in = new BufferedReader(new FileReader("data/birthdate.csv"));
            while ((line = in.readLine()) != null) {
                birthdate = line.split(";");
            }
            in = new BufferedReader(new FileReader("data/nationality.csv"));
            while ((line = in.readLine()) != null) {
                nationality = line.split(";");
            }
            in = new BufferedReader(new FileReader("data/smoking.csv"));
            while ((line = in.readLine()) != null) {
                isSmoking = line.split(";");
            }
            in = new BufferedReader(new FileReader("data/asthma.csv"));
            while ((line = in.readLine()) != null) {
                hasAsthma = line.split(";");
            }
            in = new BufferedReader(new FileReader("data/hiv.csv"));
            while ((line = in.readLine()) != null) {
                hasHIV = line.split(";");
            }
            in = new BufferedReader(new FileReader("data/covid19_infection_status.csv"));
            while ((line = in.readLine()) != null) {
                hasInfection = line.split(";");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Fill the local Variables of one Human to class Human
        System.out.println("Create Humans");
        for (int i = 0; i < birthdate.length; i++) {
            int mensch = i + 1;
            Human passenger = new Passenger.Builder()
                    .setFirstName(firstname.get(i))
                    .setLastName(lastname.get(i))
                    .setBirthDate(birthdate[i])
                    .setNationality(Nationality.valueOf(nationality[i]))
                    .setSmoking(Boolean.parseBoolean(isSmoking[i]))
                    .setHasAsthma(Boolean.parseBoolean(hasAsthma[i]))
                    .setHasHIV(Boolean.parseBoolean(hasHIV[i]))
                    .setInfectedCOVID19(Boolean.parseBoolean(hasInfection[i]))
                    .setHasFever(false)
                    .setHasTaste(true)
                    .setHasMouthProtection(false)
                    .setClothing(null)
                    .build();

            //Put the Human in the List
            humanList.add(passenger);
        }

        return true;
    }

    public static void simulationStart() {

    }

    public static void hospitlSimulation() {
        createHospital();
    }

    public static void createHospital() {
        hospital = new Hospital.Builder()
                .setName("Hospital")
                .setFloors(createFloors())
                .setCarPark(new CarPark(3, 7, 25))
                .build();
    }

    //for generating the whole floor with the rooms, departments, stations etc.
    private static Stack<Floor> createFloors() {
        Stack<Floor> floors = new Stack<Floor>();
        ArrayList<Room> roomList;

        //0 (BSED/ED)
        ArrayList<Department> departmentsFloor0 = new ArrayList<>();
        departmentsFloor0.add(new BSEmergencyDepartment());
        EmergencyDepartment emergencyDepartment = new EmergencyDepartment(DepartmentsName.Emergency_Department);
        departmentsFloor0.add(emergencyDepartment);
        floors.push(new Floor(0, departmentsFloor0));

        //1
        ArrayList<Station> stationsCriticalCare = new ArrayList<>();
        for (int j = 65; j <= 69; j++) {
            //create rooms
            roomList = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                roomList.add(new Room(i, 3, 3));
            }
            stationsCriticalCare.add(new Station(Character.toString((char) j), roomList)); //or Character.toString((j)
        }
        Department criticalCare = new Department(DepartmentsName.Critical_Care, stationsCriticalCare);
        floors.push(new Floor(1, new ArrayList<Department>() {{
            add(criticalCare);
        }}));

        //2
        ArrayList<Station> stationsPulmonology = new ArrayList<>();
        for (int j = 65; j <= 69; j++) {
            roomList = new ArrayList<>();
            for (int i = 1; i <= 30; i++) {
                if (j == 65) {
                    roomList.add(new Room(i, 2, 2));
                } else if (j == 66) {
                    roomList.add(new Room(i, 4, 4));
                } else {
                    roomList.add(new Room(i, 6, 6));
                }
            }
            stationsPulmonology.add(new Station(Character.toString((char) j), roomList)); //or Character.toString((j)
        }
        Department pulmology = new Department(DepartmentsName.Pulmonology, stationsPulmonology);
        floors.push(new Floor(2, new ArrayList<Department>() {{
            add(pulmology);
        }}));

        //Radiology 3
        ArrayList<Station> stationRadiology = new ArrayList<>();
        roomList = new ArrayList<>();
        roomList.add(new Room(1, 1, 1));
        roomList.add(new Room(2, 1, 1));
        stationRadiology.add(new Station("A", roomList));
        Department radiology = new Department(DepartmentsName.Radiology, stationRadiology);
        floors.push(new Floor(3, new ArrayList<Department>() {{
            add(radiology);
        }}));

        //4
        ArrayList<Station> stationsCardiology = new ArrayList<>();
        for (int j = 65; j <= 67; j++) {
            roomList = new ArrayList<>();
            if (j == 65) {
                for (int i = 1; i <= 5; i++) {
                    roomList.add(new Room(i, 1, 1));
                }
            } else {
                for (int i = 1; i <= 10; i++) {
                    if (j == 66) {
                        roomList.add(new Room(i, 2, 2));
                    } else if (j == 67) {
                        roomList.add(new Room(i, 3, 3));
                    }
                }
            }
            stationsPulmonology.add(new Station(Character.toString((char) j), roomList)); //or Character.toString((j)
        }
        Department cardiology = new Department(DepartmentsName.Cardiology, stationsCardiology);
        floors.push(new Floor(4, new ArrayList<Department>() {{
            add(cardiology);
        }}));


        ArrayList<Station> stationsSurgery = new ArrayList<>();
        for (int j = 65; j <= 66; j++) {
            roomList = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                if (j == 65) {
                    roomList.add(new Room(i, 1, 0));
                } else if (j == 66) {
                    roomList.add(new Room(i, 2, 0));
                }
            }
            stationsSurgery.add(new Station(Character.toString((char) j), roomList)); //or Character.toString((j)
        }
        Department surgery = new Department(DepartmentsName.General_Surgery, stationsSurgery);
        floors.push(new Floor(5, new ArrayList<Department>() {{
            add(surgery);
        }}));

        //5 oncology
        ArrayList<Station> stationsOncology = new ArrayList<>();
        for (int j = 65; j <= 67; j++) {
            roomList = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                if (j == 65) {
                    roomList.add(new Room(i, 1, 0));
                } else if (j == 66) {
                    roomList.add(new Room(i, 2, 2));
                } else if (j == 67) {
                    roomList.add(new Room(i, 3, 3));
                }
            }
            stationsOncology.add(new Station(Character.toString((char) j), roomList)); //or Character.toString((j)
        }
        Department oncology = new Department(DepartmentsName.Oncology, stationsOncology);
        floors.push(new Floor(6, new ArrayList<Department>() {{
            add(oncology);
        }}));

        return floors;
    }
}
