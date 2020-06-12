package shared;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImmuneSystem implements IVisitableImmuneSystem {
    private List<TCell> tCells = new ArrayList<>();

    public ImmuneSystem() {
    }

    public void work(Human human) {
        Random random = new Random();
        for (int lung = 0; lung < 2; lung++) {
            for (int i = 0; i < human.getLungs().get(lung).getStructure().length; i++) {
                for (int x = 0; x < human.getLungs().get(lung).getStructure()[0].length; x++) {
                    for (int y = 0; y < human.getLungs().get(0).getStructure()[0][0].length; y++) {
                        //Check if Cell is infected
                        if (human.getLungs().get(lung).getStructure()[i][x][y].getClass() == InfectedCell.class) {
                            tCells.add(new TCell());
                            int randomNr = random.nextInt(tCells.size());
                            TCell randTCell = tCells.get(randomNr);
                            if (randTCell.tryToDestroy(human)) {
                                human.getLungs().get(lung).getStructure()[i][x][y] = new LungCell();
                                tCells.remove(randomNr);
                            }
                        }
                        if (human.getLungs().get(lung).getStructure()[i][x][y].getClass() == InfectedCell.class) {
                            //Replikation
                            int replicatCount = random.nextInt(4) + 2;
                            for (int j = 0; j < replicatCount; j++) {
                                boolean infectedCell = true;
                                int count = 0;
                                while (infectedCell == true) {

                                    int randomLung = random.nextInt(2);
                                    int randomCellI = random.nextInt(10);
                                    int randomCellX = random.nextInt(10);
                                    int randomCellY = random.nextInt(2);
                                    if (human.getLungs().get(randomLung).getStructure()[randomCellI][randomCellX][randomCellY].getClass() != InfectedCell.class) {
                                        human.getLungs().get(randomLung).createInfectedCell('v', randomCellI, randomCellX, randomCellY);
                                        infectedCell = false;
                                    } else {
                                        if (count > 200) {
                                            infectedCell = false;
                                            //System.out.println("All Cells are infected");
                                        }
                                        count++;
                                        //System.out.println("Cell already infected");
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        hasInfection(human);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public boolean hasInfection(Human human) {
        int countInfectedCells = 0;
        for (int lung = 0; lung < 2; lung++) {
            for (int i = 0; i < human.getLungs().get(lung).getStructure().length; i++) {
                for (int x = 0; x < human.getLungs().get(lung).getStructure()[0].length; x++) {
                    for (int y = 0; y < human.getLungs().get(0).getStructure()[0][0].length; y++) {
                        if (human.getLungs().get(lung).getStructure()[i][x][y].getStructure()[1][1][1] == 'v') {
                            countInfectedCells++;
                        }
                    }
                }
            }
        }

        return countInfectedCells > 0;
    }
}