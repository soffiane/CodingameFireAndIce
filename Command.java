public class Command {

    CommandType t;
    Position p;
    int idOrLevel;

    public Command(CommandType t, int idOrLevel, Position p) {
        this.t = t;
        this.p = p;
        this.idOrLevel = idOrLevel;
    }

    public String get() {
        return t.toString() + " " + idOrLevel + " " + p.x + " " + p.y + ";";
    }

}
