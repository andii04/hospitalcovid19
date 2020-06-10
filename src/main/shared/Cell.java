package shared;

import java.util.Random;

public class Cell {
    private char[][][] structure = new char[3][3][3];
    char[]charpool = new char[3];

    public void initialiseLungCell(){
        Random r = new Random();
        for (int i = 0; i < structure.length; i++)
        {
            for (int a = 0; a < structure[i].length; a++)
            {
                for (int b = 0; b < structure[i][a].length; b++)
                {
                    //sollte gehen
                    structure[i][a][b] = charpool[r.nextInt(charpool.length)];
                }
            }
        }
    }

    public Cell() {
        charpool[0] = 'd';
        charpool[1] = 'n';
        charpool[2] = 'a';
        initialiseLungCell();
    }
}
