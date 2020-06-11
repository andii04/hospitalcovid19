import com.google.common.eventbus.EventBus;
import cruise_ship.*;
import hospital.*;
import shared.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    static CruiseShip cruiseShip;
    static Hospital hospital;



    public static void main(String... args) {


        List<Human> humanList = new ArrayList<Human>();
        createHumans(humanList);
        cruiseShip = new CruiseShip.Builder()
                .setName("Symphony of the Seas")
                .setEventBus(new EventBus())
                .setHumanList(humanList)
                .build();


        hospitlSimulation();

    }

    private static void lambdaAnalysis() {
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

    public static boolean createHumans(List<Human> humanList){
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

        try {
            in = new BufferedReader(new FileReader("data/name.txt"));
            String zeile = null;
            int count = 0;
            while ((zeile = in.readLine()) != null) {
                String[]name = zeile.split(" ");
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


        for( int i = 0; i < birthdate.length; i++){
            int mensch = i+1;
            System.out.println("Erstellt Mensch Nr." + mensch);
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
            humanList.add(passenger);
        }

        return true;
    }

    public static void simulationStart(){

    }

    public static void hospitlSimulation(){
        createHospital();
        CommandCOVID19Emergency command = new CommandCOVID19Emergency(hospital, cruiseShip);
        command.execute();
        //((BSEmergencyDepartment)(hospital.getFloor(0).getDepartments(0))).welcome();
    }

    public static void createHospital(){
        hospital = new Hospital.Builder()
                .setName("Hospital")
                .setFloors(createFloors())
                .setCarPark(new CarPark(3,7,25))
                .build();
    }

    private static Stack<Floor> createFloors(){
        Stack<Floor> floors = new Stack<Floor>();
        ArrayList<Room> roomList;

        //0 (BSED/ED)
        ArrayList<Department> departmentsFloor0 = new ArrayList<>();
        departmentsFloor0.add(new BSEmergencyDepartment());
        EmergencyDepartment emergencyDepartment = new EmergencyDepartment();
        departmentsFloor0.add(emergencyDepartment);
        floors.push(new Floor(0, departmentsFloor0));

        //1
        ArrayList<Station> stationsCriticalCare = new ArrayList<>();
        for(int j = 65;j<=69; j++){
            //create rooms
            roomList= new ArrayList<>();
            for(int i =1;i<=20;i++){
                roomList.add(new Room(i,3,3));
            }
            stationsCriticalCare.add(new Station(Character.toString ((char) j), roomList)); //or Character.toString((j)
        }
        Department criticalCare = new Department(DepartmentsName.Critical_Care, stationsCriticalCare);
        floors.push(new Floor(1,new ArrayList<Department>(){{add(criticalCare);}}));

        //2
        ArrayList<Station> stationsPulmonology = new ArrayList<>();
        for(int j = 65;j<=69; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=30;i++){
                if(j ==65){
                    roomList.add(new Room(i, 2,2));
                }
                else if(j==66){
                    roomList.add(new Room(i, 4,4));
                }
                else {
                    roomList.add(new Room(i, 6,6));
                }
            }
            stationsPulmonology.add(new Station(Character.toString ((char) j), roomList)); //or Character.toString((j)
        }
        Department pulmology = new Department(DepartmentsName.Pulmonology, stationsPulmonology);
        floors.push(new Floor(2,new ArrayList<Department>(){{add(pulmology);}}));

        //Radiology 3
        ArrayList<Station> stationRadiology = new ArrayList<>();
        roomList= new ArrayList<>();
        roomList.add(new Room(1,1,1));
        roomList.add(new Room(2,1,1));
        stationRadiology.add(new Station("A",roomList));
        Department radiology = new Department(DepartmentsName.Radiology, stationRadiology);
        floors.push(new Floor(3,new ArrayList<Department>(){{add(radiology);}}));

        //4
        ArrayList<Station> stationsCardiology = new ArrayList<>();
        for(int j = 65;j<=67; j++){
            roomList= new ArrayList<>();
            if (j==65){
                for(int i =1;i<=5;i++){
                    roomList.add(new Room(i, 1,1));
                }
            }
            else {
                for(int i =1;i<=10;i++){
                    if (j==66){
                        roomList.add(new Room(i, 2,2));
                    }
                    else if(j==67){
                        roomList.add(new Room(i, 3,3));
                    }
                }
            }
            stationsPulmonology.add(new Station(Character.toString ((char) j), roomList)); //or Character.toString((j)
        }
        Department cardiology = new Department(DepartmentsName.Cardiology, stationsCardiology);
        floors.push(new Floor(4,new ArrayList<Department>(){{add(cardiology);}}));


        ArrayList<Station> stationsSurgery = new ArrayList<>();
        for(int j = 65;j<=66; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=3;i++){
                if(j ==65){
                    roomList.add(new Room(i, 1,0));
                }
                else if(j==66){
                    roomList.add(new Room(i, 2,0));
                }
            }
            stationsSurgery.add(new Station(Character.toString ((char) j), roomList)); //or Character.toString((j)
        }
        Department surgery = new Department(DepartmentsName.General_Surgery, stationsSurgery);
        floors.push(new Floor(5,new ArrayList<Department>(){{add(surgery);}}));

        //5 oncology
        ArrayList<Station> stationsOncology = new ArrayList<>();
        for(int j = 65;j<=67; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=5;i++){
                if(j ==65){
                    roomList.add(new Room(i, 1,0));
                }
                else if(j==66){
                    roomList.add(new Room(i, 2,2));
                }
                else if(j==67){
                    roomList.add(new Room(i, 3,3));
                }
            }
            stationsOncology.add(new Station(Character.toString ((char) j), roomList)); //or Character.toString((j)
        }
        Department oncology = new Department(DepartmentsName.Oncology, stationsOncology);
        floors.push(new Floor(6,new ArrayList<Department>(){{add(oncology);}}));

        return floors;
    }
}
