import com.google.common.eventbus.EventBus;
import cruise_ship.*;
import hospital.Hospital;
import shared.Human;
import shared.Nationality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) {
        List<Human> humanList = new ArrayList<Human>();
        createHumans(humanList);
        CruiseShip cruiseShip = new CruiseShip.Builder()
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
}