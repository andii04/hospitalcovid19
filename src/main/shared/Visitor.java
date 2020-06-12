package shared;

public class Visitor implements IVisitorImmuneSystem {
    ImmuneSystem immuneSystem = new ImmuneSystem();

    public void visit(ImmuneSystem immuneSystem) {
        this.immuneSystem = immuneSystem;
    }

    public void active(Human human) {
        immuneSystem.work(human);
    }

    public boolean check(Human human) {
        return immuneSystem.hasInfection(human);
    }
}
