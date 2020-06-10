package shared;

public class Lung {
    int nochmalneumachen = 500;
    private LungCell[][][] structure;
    private char[] liquidParticle = new char[nochmalneumachen];

    public Lung(){
        structure  = new LungCell[25][10][2];
    }

    public LungCell[][][] getStructure() {
        return structure;
    }
}
