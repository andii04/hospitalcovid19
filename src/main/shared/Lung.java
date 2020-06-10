package shared;

public class Lung {
    int nochmalneumachen = 500;
    private Cell[][][] structure;
    private char[] liquidParticle = new char[nochmalneumachen];

    public Lung(){
        structure  = new LungCell[25][10][2];
    }

    public void createInfectedCell(char virus, int i, int x, int y){
        //System.out.println(structure[i][x][y].);
        //structure[i][x][y] = (InfectedCell) structure[i][x][y];

    }

    public Cell[][][] getStructure() {
        return structure;
    }
}
