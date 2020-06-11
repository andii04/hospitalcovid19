package hospital;

import shared.Human;

public class Stretcher implements IStretcher {
    private boolean isDown;
    private Human human;

    public void position(Human human) {
        this.human = human;
    }

    public void transfer(HospitalBed hospitalBed) {
        hospitalBed.setPatient( human );
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
