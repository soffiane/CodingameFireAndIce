public class Building {

    Position p;
    BuildingType t;
    int owner;

    public Building(int x, int y, int t, int owner) {

        this.p = new Position(x,y);
        this.t = BuildingType.get(t);
        this.owner = owner;

    }

    public void debug() {

        System.err.println(t+" at "+p.x+" "+p.y+" owned by "+owner);

    }

    public boolean isHQ() {

        return t.equals(BuildingType.HQ);

    }

    public boolean isOwned() {

        return owner == 0;

    }
}
