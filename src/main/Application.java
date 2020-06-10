import com.google.common.eventbus.EventBus;
import cruise_ship.*;
import hospital.*;
import shared.Human;
import shared.Nationality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
                    .setHasTaste(false)
                    .setHasMouthProtection(false)
                    .setClothing(null)
                    .build();
            humanList.add(passenger);
        }

        return true;
    }

    public static void simulationStart(){

    }

    public static void createHospital(){
        hospital = new Hospital.Builder()
                .setName("Hospital")
                .setFloors(createFloors())
                .build();
    }

    private static Stack<Floor> createFloors(){
        Stack<Floor> floors = new Stack<Floor>();
        ArrayList<Room> roomList;

        //0
        ArrayList<Department> departmentsFloor0 = new ArrayList<>();
        departmentsFloor0.add(new BSEmergencyDepartment());
        departmentsFloor0.add(new EmergencyDepartment());
        floors.push(new Floor(0, departmentsFloor0));

        //1
        ArrayList<Station> stationsCriticalCare = new ArrayList<>();
        for(int j = 41;j<=45; j++){
            //create rooms
            roomList= new ArrayList<>();
            for(int i =1;i<=20;i++){
                roomList.add(new Room(i,3,3));
            }
            stationsCriticalCare.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department criticalCare = new Department(DepartmentsName.Critical_Care, stationsCriticalCare);
        floors.push(new Floor(1,new ArrayList<Department>(){{add(criticalCare);}}));

        //2
        ArrayList<Station> stationsPulmonology = new ArrayList<>();
        for(int j = 41;j<=45; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=30;i++){
                if(j ==41){
                    roomList.add(new Room(i, 2,2));
                }
                else if(j==42){
                    roomList.add(new Room(i, 4,4));
                }
                else {
                    roomList.add(new Room(i, 6,6));
                }
            }
            stationsPulmonology.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
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
        for(int j = 41;j<=43; j++){
            roomList= new ArrayList<>();
            if (j==41){
                for(int i =1;i<=5;i++){
                    roomList.add(new Room(i, 1,1));
                }
            }
            else {
                for(int i =1;i<=10;i++){
                    if (j==42){
                        roomList.add(new Room(i, 2,2));
                    }
                    else if(j==43){
                        roomList.add(new Room(i, 3,3));
                    }
                }
            }
            stationsPulmonology.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department cardiology = new Department(DepartmentsName.Cardiology, stationsCardiology);
        floors.push(new Floor(4,new ArrayList<Department>(){{add(cardiology);}}));


        ArrayList<Station> stationsSurgery = new ArrayList<>();
        for(int j = 41;j<=42; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=3;i++){
                if(j ==41){
                    roomList.add(new Room(i, 1,0));
                }
                else if(j==42){
                    roomList.add(new Room(i, 2,0));
                }
            }
            stationsSurgery.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department surgery = new Department(DepartmentsName.General_Surgery, stationsSurgery);
        floors.push(new Floor(5,new ArrayList<Department>(){{add(surgery);}}));

        //5 oncology
        ArrayList<Station> stationsOncology = new ArrayList<>();
        for(int j = 41;j<=43; j++){
            roomList= new ArrayList<>();
            for(int i =1;i<=5;i++){
                if(j ==41){
                    roomList.add(new Room(i, 1,0));
                }
                else if(j==42){
                    roomList.add(new Room(i, 2,2));
                }
                else if(j==41){
                    roomList.add(new Room(i, 3,3));
                }
            }
            stationsOncology.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department oncology = new Department(DepartmentsName.Oncology, stationsOncology);
        floors.push(new Floor(6,new ArrayList<Department>(){{add(oncology);}}));

        return floors;
    }
}
