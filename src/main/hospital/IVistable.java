package hospital;

public interface IVistable {
    //can be visitable from a visitor
    void accept(IVisitorRobot visitor);
}
