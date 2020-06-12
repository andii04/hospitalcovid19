package cruise_ship;


public enum DeckID {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8);

    private int index;

    DeckID(int i) {
        this.index = i;
    }

    public int getIndex() {
        return index;
    }
}
