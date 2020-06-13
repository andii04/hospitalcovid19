package shared;

import java.util.Random;

public class Cell {
    char[] charpool = new char[3];
    private char[][][] structure = new char[3][3][3];

    public Cell() {
        charpool[0] = 'd';
        charpool[1] = 'n';
        charpool[2] = 'a';
        initialiseLungCell();
    }

    public void initialiseLungCell() {
        //initialise random chars
        Random r = new Random();
        for (int i = 0; i < structure.length; i++) {
            for (int a = 0; a < structure[i].length; a++) {
                for (int b = 0; b < structure[i][a].length; b++) {
                    structure[i][a][b] = charpool[r.nextInt(charpool.length)];
                }
            }
        }
    }

    public char[][][] getStructure() {
        return structure;
    }
}
