package shared;

public class ImmuneSystem {
    Human human;

    public ImmuneSystem(Human human){
        this.human = human;
    }
    public void work(){
        for(int lung= 0; lung <1; lung++){
            for(int i=0; i<human.getLungs().get(lung).getStructure().length; i++){
                for(int x= 0; x <human.getLungs().get(lung).getStructure()[0].length; x++){
                    for(int y = 0; y <human.getLungs().get(0).getStructure()[0][0].length; y++){
                        if(human.getLungs().get(0).getStructure()[i][x][y].getClass().toString().equals("InfectedCell")){
                            System.out.println("JA");
                        }
                        else{
                            System.out.println("Ne");
                        }
                    }
                }
            }
        }
        //human.getLungs().get(0).getStructure().length;

    }
}
