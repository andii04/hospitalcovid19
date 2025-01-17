package shared;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class TCell extends Cell {
    public boolean tryToDestroy(Human human) {
        //Specification Page 1
        //
        int age = 0;
        double year = 0;
        int smoke = 0;
        int asthma = 0;
        int hiv = 0;
        double destroyPercent = 90;
        try {
            //Get the Birthday of Person
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(human.getBirthDate());
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            //calculate the age between birthday and date now
            age = Period.between(localDate, LocalDate.now()).getYears();
        } catch (Exception e) {
            System.out.println("Cannot Convert Date");
        }
        if (human.isSmoking()) {
            //Sub -10% for smoking
            smoke = 10;
        }
        if (human.isHasAsthma()) {
            asthma = 15;
            //Sub -15% for Asthma pre-illness
        }
        if (human.isHasHIV()) {
            hiv = 30;
            //Sub -30% for HIV pre-illness
        }
        if (age > 40) {
            // Sub -0,75% for every year over 40
            year = (age - 40) * 0.75;
        }

        destroyPercent = 90 - year - smoke - asthma - hiv;
        //System.out.println(destroyPercent+" "+ LocalDate.now()+ " "+  age);
        return Math.random() * 100 < destroyPercent;

    }
}
