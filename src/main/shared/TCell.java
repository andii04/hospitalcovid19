package shared;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class TCell extends Cell {
    public boolean tryToDestroy(Human human){
        int age = 0;
        double year = 0;
        int smoke = 0;
        int asthma = 0;
        int hiv = 0;
        double destroyPercent = 90;
        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(human.getBirthDate());
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            age = Period.between(localDate, LocalDate.now()).getDays();
        }
        catch (Exception e){
            System.out.println("Cannot Convert Date");
        }
        if(human.isSmoking()){
            smoke=10;
        }
        if(human.isHasAsthma()){
            asthma=15;
        }
        if(human.isHasHIV()){
            hiv = 30;
        }
        if(age>40){
            year = (age-40)*0.75;
        }

        destroyPercent = 90 - year - smoke - asthma - hiv;
        if (Math.random() * 100 < destroyPercent) {
            System.out.println("Zelle zerstöret");
            return true;
        }
        return false;

    }
}
