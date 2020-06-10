package shared;

import java.util.Random;

public class LungCell extends Cell {

    char[]charpool = new char[3];
    char[][][] lungCell = new char[3][3][3];

    public void initialiseLungCell(){
        Random r = new Random();
        for (int i = 0; i < lungCell.length; i++)
        {
            for (int a = 0; a < lungCell[i].length; a++)
            {
                for (int b = 0; b < lungCell[i][a].length; b++)
                {
                    //sollte gehen
                    lungCell[i][a][b] = charpool[r.nextInt(charpool.length)];
                }
            }
        }
    }

    public LungCell() {
        charpool[0] = 'd';
        charpool[1] = 'n';
        charpool[2] = 'a';
        initialiseLungCell();
    }

    public char[][][] getLungCell() {
        return lungCell;
    }
}
