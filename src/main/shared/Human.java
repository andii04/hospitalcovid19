package shared;

import cruise_ship.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Human {
    Visitor visit = new Visitor();
    private String firstName;
    private String lastName;
    private String birthDate;
    private Nationality nationality;
    private boolean isSmoking;
    private boolean hasAsthma;
    private boolean hasHIV;
    private boolean isInfectedCOVID19;
    private boolean hasFever;
    private boolean hasTaste;
    private boolean hasMouthProtection;
    private Clothing clothing;
    private Ticket ticket;
    private List<Lung> lungs = new ArrayList<>();
    private ImmuneSystem immuneSystem;
    private int incubationPeriod = 0;
    public Human(String firstName, String lastName, String birthDate, Nationality nationality, boolean isSmoking, boolean hasAsthma, boolean hasHIV, boolean isInfectedCOVID19, boolean hasFever, boolean hasTaste, boolean hasMouthProtection, Clothing clothing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.isSmoking = isSmoking;
        this.hasAsthma = hasAsthma;
        this.hasHIV = hasHIV;
        this.isInfectedCOVID19 = isInfectedCOVID19;
        this.hasFever = hasFever;
        this.hasTaste = hasTaste;
        this.hasMouthProtection = hasMouthProtection;
        this.clothing = clothing;


        ticket = new Ticket();
        lungs.add(new Lung());
        lungs.add(new Lung());
        immuneSystem = new ImmuneSystem();
        immuneSystem.accept(visit);

    }

    public Human() {
    }

    public void visitImmuneSysteme() {
        //Visitor for ImmuneSystem
        //Execute the replikation of the virus in the Lung
        visit.active(this);

        //Check if there is a virus Particle in the Lung
        if (visit.check(this)) {
            //If there is a Virus Particle The Passenger is Infected COVID19
            isInfectedCOVID19 = true;
        }
        //If infected increase incubationPeriod
        if (isInfectedCOVID19) {
            incubationPeriod++;
        }

        // After a incubationPeriod of 6 days get Fever and lose Taste
        if (incubationPeriod >= 6) {
            hasFever = true;
            hasTaste = false;
        }
    }

    public ImmuneSystem getImmuneSystem() {
        return immuneSystem;
    }

    public List<Lung> getLungs() {
        return lungs;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void breathe(String air) {
        //get the Air from another person
        Random random = new Random();
        char[] airParticle = air.toCharArray();

        //For every Particle search a Random cell and place the particle
        for (char p : airParticle) {
            lungs.get(random.nextInt(2)).createInfectedCell(p, random.nextInt(10), random.nextInt(10), random.nextInt(2));
        }
    }

    //function spread 10 virus particle
    public String dryCough() {
        return "vvvvvvvvvv";
    }

    public void viralInfectionProcess() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public boolean isHasAsthma() {
        return hasAsthma;
    }

    public boolean isHasHIV() {
        return hasHIV;
    }

    public boolean isInfectedCOVID19() {
        return isInfectedCOVID19;
    }

    public boolean isHasFever() {
        return hasFever;
    }

    public boolean isHasTaste() {
        return hasTaste;
    }

    public boolean isHasMouthProtection() {
        return hasMouthProtection;
    }

    public void setHasMouthProtection(boolean hasMouthProtection) {
        this.hasMouthProtection = hasMouthProtection;
    }

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    //Human Builder

    public static class Builder {
        private String firstName;
        private String lastName;
        private String birthDate;
        private Nationality nationality;
        private boolean isSmoking;
        private boolean hasAsthma;
        private boolean hasHIV;
        private boolean isInfectedCOVID19;
        private boolean hasFever;
        private boolean hasTaste;
        private boolean hasMouthProtection;
        private Clothing clothing;


        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setNationality(Nationality nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder setSmoking(boolean smoking) {
            isSmoking = smoking;
            return this;
        }

        public Builder setHasAsthma(boolean hasAsthma) {
            this.hasAsthma = hasAsthma;
            return this;
        }

        public Builder setHasHIV(boolean hasHIV) {
            this.hasHIV = hasHIV;
            return this;
        }

        public Builder setInfectedCOVID19(boolean infectedCOVID19) {
            isInfectedCOVID19 = infectedCOVID19;
            return this;
        }

        public Builder setHasFever(boolean hasFever) {
            this.hasFever = hasFever;
            return this;
        }

        public Builder setHasTaste(boolean hasTaste) {
            this.hasTaste = hasTaste;
            return this;
        }

        public Builder setHasMouthProtection(boolean hasMouthProtection) {
            this.hasMouthProtection = hasMouthProtection;
            return this;
        }

        public Builder setClothing(Clothing clothing) {
            this.clothing = clothing;
            return this;
        }


        //build function
        public Human build() {
            return new Human(firstName, lastName, birthDate, nationality, isSmoking, hasAsthma, hasHIV, isInfectedCOVID19, hasFever, hasTaste, hasMouthProtection, clothing);
        }
    }
}
