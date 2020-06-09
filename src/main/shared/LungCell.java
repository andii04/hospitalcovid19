package shared;

public class LungCell extends Cell {

    char[]charpool = new char[3];
    char[][][] lungCell = new char[3][3][3];

    public void arraytest(){
        for (int i = 0; i < lungCell.length; i++)
        {
            for (int a = 0; a < lungCell[i].length; a++)
            {
                for (int b = 0; b < lungCell[i][a].length; b++)
                {
                    //hier anpassen
                    lungCell[i][a][b] = charpool[0];
                }
            }
        }
    }

    public LungCell() {
        charpool[0] = 'd';
        charpool[1] = 'n';
        charpool[2] = 'a';
    }
}
