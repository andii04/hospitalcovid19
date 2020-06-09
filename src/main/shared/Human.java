package shared;

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
    }
    public Human(){

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

        public Human build() {
            return new Human(firstName, lastName, birthDate, nationality, isSmoking, hasAsthma, hasHIV, isInfectedCOVID19, hasFever, hasTaste, hasMouthProtection, clothing);
        }
    }
}
