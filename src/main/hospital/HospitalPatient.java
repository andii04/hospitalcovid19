package hospital;

import shared.Human;

public class HospitalPatient extends Human {
    private int id;

    //workaround for casting(specifications) in a HospitalPatient
    public HospitalPatient(Human human) {
        super(human.getFirstName(), human.getLastName(), human.getBirthDate(), human.getNationality(), human.isSmoking(), human.isHasAsthma(),
                human.isHasHIV(), human.isInfectedCOVID19(), human.isHasFever(), human.isHasTaste(), human.isHasMouthProtection(), human.getClothing());
    }

    public int getId() {
        return id;
    }
    //set the patientID
    public void setId(int id) {
        this.id = id;
    }
}
