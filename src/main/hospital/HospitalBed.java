package hospital;


import shared.Human;

public class HospitalBed {
    Human human;

    public boolean isEmpty() {
        return human == null;
    }

    public Human getHuman() {
        return human;
    }
    public void setPatient(Human human){
        human = human;
    }
}

