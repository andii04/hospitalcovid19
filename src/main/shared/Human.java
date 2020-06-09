package shared;

import cruise_ship.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Human {
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
    private Lung lungRight;
    private Lung lungLeft;

    public Human(String firstName, String lastName, String birthDate, Nationality nationality, boolean isSmoking, boolean hasAsthma, boolean hasHIV, boolean isInfectedCOVID19, boolean hasFever, boolean hasTaste, boolean hasMouthProtection, Clothing clothing){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality =  nationality;
        this.isSmoking = isSmoking;
        this.hasAsthma = hasAsthma;
        this.hasHIV = hasHIV;
        this.isInfectedCOVID19 = isInfectedCOVID19;
        this.hasFever = hasFever;
        this.hasTaste = hasTaste;
        this.hasMouthProtection = hasMouthProtection;
        this.clothing = clothing;

        ticket = new Ticket();

        lungRight = new Lung();
        lungLeft = new Lung();
    }

    public Human(){
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void dress (Clothing clothing){

    }

    public void undress(){

    }

    public void breathe(String air){

    }

    public String dryCough(){
        return "placeholder";
    }

    public void viralInfectionProcess(){

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

    public Clothing getClothing() {
        return clothing;
    }

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


        public Human build() {
            return new Human(firstName, lastName, birthDate, nationality, isSmoking, hasAsthma, hasHIV, isInfectedCOVID19, hasFever, hasTaste, hasMouthProtection, clothing);
        }
    }
}
