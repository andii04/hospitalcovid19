import shared.Human;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Application {
    public static void main(String... args) {
        createHumans();

    }

    public static boolean createHumans(){
        File name = new File("data/name.txt");
        File birthday = new File("data/birthdate.csv");
        if (!name.canRead() || !name.isFile())
            System.exit(0);

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader("data/name.txt"));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
                System.out.println("Gelesene Zeile: " + zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}