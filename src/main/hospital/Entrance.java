package hospital;

//@todo ka https://www.geeksforgeeks.org/chain-responsibility-design-pattern/
public class Entrance {
    Entrance nextEntrance;
    String name;

    public Entrance() {

    }

    public void enter(String s){
        if(name.equals(s)){

        }
        else{
            //super.enter()
        }
    }
}
