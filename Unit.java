public class Unit {
    Position p;
    int level;
    int owner;
    int id;

    public Unit(int x, int y, int id, int level, int owner) {
        this.p = new Position(x,y);
        this.id = id;
        this.level = level;
        this.owner = owner;
    }

    public void debug() {
        System.err.println("unit of level "+level+" at "+p.x+" "+p.y+" owned by "+owner);
    }

    public boolean isOwned() {
        return owner == 0;
    }
}
