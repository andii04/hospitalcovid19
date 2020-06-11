package shared;

import java.util.Random;

public class ImmuneSystem implements IVisitableImmuneSystem{

    public ImmuneSystem(){
    }
    public void work(Human human){
        Random random = new Random();
        for(int lung= 0; lung <2; lung++){
            for(int i=0; i<human.getLungs().get(lung).getStructure().length; i++){
                for(int x= 0; x <human.getLungs().get(lung).getStructure()[0].length; x++){
                    for(int y = 0; y <human.getLungs().get(0).getStructure()[0][0].length; y++){
                        if(human.getLungs().get(lung).getStructure()[i][x][y].getClass()== InfectedCell.class){
                            TCell randTCell = human.getLungs().get(lung).gettCells().get(random.nextInt(human.getLungs().get(lung).gettCells().size()));
                            randTCell.tryToDestroy(human);
                        }
                    }
                }
            }
        }
        //human.getLungs().get(0).getStructure().length;

    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
