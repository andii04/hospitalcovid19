import shared.Human;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String... args) {
        createHumans();

    }

    public static boolean createHumans(){
        BufferedReader in = null;
        String[]vorname = null;
        String[]nachname = null;
        String[]birthdate = null;
        String[] nationality = null;
        String[] isSmoking = null;
        String[] hasAsthma = null;
        String[] hasHIV = null;
        String[] hasInfection = null;
        List<Human> humanList = null;

        String line = "";

        try {
            in = new BufferedReader(new FileReader("data/name.txt"));
            String zeile = null;
            int count = 0;
            while ((zeile = in.readLine()) != null) {
                String[]name = zeile.split(" ");
                vorname[count] = name[0];
                nachname[count] = name[1];
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

        for( int i = 0; i < vorname.length; i++){
            Human human = new Human.Builder()
                    .setFirstName(vorname[i])
                    .setLastName(nachname[i])
                    .setBirthDate(birthdate[i])
                    .setNationality(null)
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