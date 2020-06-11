package shared;

import cruise_ship.CabinDeck;

import java.util.ArrayList;
import java.util.List;

public class Lung {
    int nochmalneumachen = 500;
    private Cell[][][] structure = new Cell[10][10][2];

    private char[] liquidParticle = new char[nochmalneumachen];

    public Lung(){

        for(int i=0; i<structure.length; i++){
            for(int x= 0; x <structure[0].length; x++){
                for(int y = 0; y <structure[0][0].length; y++){
                    structure[i][x][y] = new LungCell();
                }
            }
        }
    }


    public void createInfectedCell(char virus, int i, int x, int y){
        structure[i][x][y] = null;
        structure[i][x][y] = new InfectedCell();
        structure[i][x][y].getStructure()[1][1][1] = virus;         // in the middle

    }

    public Cell[][][] getStructure() {
        return structure;
    }
}
