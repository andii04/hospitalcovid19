import com.google.common.eventbus.EventBus;
import cruise_ship.*;
import hospital.Department;
import hospital.Hospital;
import hospital.Room;
import hospital.Station;
import shared.Human;
import shared.Nationality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            Human human = new Human.Builder()
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
            humanList.add(human);
        }

        return true;
    }

    public static void simulationStart(){

    }

    public static void createHospital(){
        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Station> stationsCriticalCare = new ArrayList<>();

        for(int j = 41;j<=45; j++){
            //create rooms
            ArrayList<Room> roomList= new ArrayList<>();
            for(int i =1;i<=20;i++){
                roomList.add(new Room(i, 0));
            }
            stationsCriticalCare.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department criticalCare = new Department(stationsCriticalCare);
        departments.add(criticalCare);

        ArrayList<Station> stationsPulmonology = new ArrayList<>();
        for(int j = 41;j<=45; j++){
            //create rooms
            ArrayList<Room> roomList= new ArrayList<>();
            for(int i =1;i<=30;i++){
                //abcdef is different @todo
                roomList.add(new Room(i, 0));
            }
            stationsPulmonology.add(new Station(String.valueOf(j), roomList)); //or Character.toString((j)
        }
        Department Pulmonology = new Department(stationsPulmonology);
        departments.add(Pulmonology);

        //Radiology
        ArrayList<Station> stationRadiology = new ArrayList<>();
        ArrayList<Room> roomList = new ArrayList<>();
        for(int i =1;i<=2;i++){
            roomList.add(new Room(i, 0));
        }
        stationRadiology.add(new Station("A", roomList));
        Department Radiology = new Department(stationRadiology);
        departments.add(Radiology);

        hospital = new Hospital.Builder()
                .setName("Hospital")
                .setDepartmentsList(departments)
                .build();
    }
}
