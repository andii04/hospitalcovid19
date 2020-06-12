package hospital;

import shared.Human;

public class Stretcher implements IStretcher {

    public Stretcher(){
        isDown =false;
    }
    private boolean isDown;
    private Human human;

    //transfer the passenger in the stretcher
    public void position(Human human) {
        this.human = human;
    }

    //transfer the passenger out of the stretcher
    public void transfer(HospitalBed hospitalBed) {
        hospitalBed.setPatient(human);
        human = null;
    }

    @Override
    public void up() {
        isDown = false;

    }

    @Override
    public void down() {
        isDown = true;
    }
}
