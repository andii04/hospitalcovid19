package hospital;


import shared.Human;

public class HospitalBed {
    Human human;

    //check if bed is empty for other patients
    public boolean isEmpty() {
        return human == null;
    }

    //get the human from a bed
    public Human getHuman() {
        return human;
    }

    //set a patient in a bed
    public void setPatient(Human human) {
        this.human = human;
    }
}

