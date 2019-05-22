import java.util.*;

class Game {

    private List<Unit> units;
    private List<Building> buildings;
    private int gold;
    private int income;
    private List<Command> output;
    private Map<Position, String> map;

    public Game() {

        units = new ArrayList<>();
        buildings = new ArrayList<>();
        output = new ArrayList<>();
        map = new HashMap<>();

    }

    // not useful in Wood 3

    public void init(Scanner in) {

        int numberMineSpots = in.nextInt();

        for (int i = 0; i < numberMineSpots; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
        }

    }


    public void update(Scanner in) {

        units.clear();
        buildings.clear();
        output.clear();

        // READ TURN INPUT

        gold = in.nextInt();
        int income = in.nextInt();
        int opponentGold = in.nextInt();
        int opponentIncome = in.nextInt();

        for (int i = 0; i < 12; i++) {
            String line = in.next();
            for (int j = 0; i < line.length(); j++) {
                map.put(new Position(i, j), String.valueOf(line.charAt(j)));
            }
            System.err.println(line);
        }

        int buildingCount = in.nextInt();

        for (int i = 0; i < buildingCount; i++) {
            int owner = in.nextInt();
            int buildingType = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            buildings.add(new Building(x, y, buildingType, owner));
        }

        int unitCount = in.nextInt();

        for (int i = 0; i < unitCount; i++) {
            int owner = in.nextInt();
            int unitId = in.nextInt();
            int level = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            units.add(new Unit(x, y, unitId, level, owner));
        }
    }


    public void buildOutput() {
        // @TODO "core" of the AI
        trainUnits();
        moveUnits();
    }

    private void trainUnits() {
        units.stream().filter(u -> u.isOwned() && gold > 10).forEach(
                myUnit -> output.add(new Command(CommandType.TRAIN, myUnit.level, myUnit.p)));
    }


    // move to the center

    private void moveUnits() {
        for (Unit u : units) {
            if (u.isOwned()) {
                Position currentUnitPosition = u.p;
                Position positionUp = new Position(currentUnitPosition.x, currentUnitPosition.y + 1);
                Position positionDown = new Position(currentUnitPosition.x, currentUnitPosition.y - 1);
                Position positionLeft = new Position(currentUnitPosition.x + 1, currentUnitPosition.y);
                Position positionRight = new Position(currentUnitPosition.x - 1, currentUnitPosition.y);
                boolean canGoUp = checkForMove(positionUp);
                boolean canGoDown = checkForMove(positionDown);
                boolean canGoLeft = checkForMove(positionLeft);
                boolean canGoRight = checkForMove(positionRight);
                output.add(new Command(CommandType.MOVE, u.id, positionUp));
            }
        }
    }

    private boolean checkForMove(Position futurePosition) {
        final String s = map.get(futurePosition);
        if (s.equals("#")) {
            return false;
        }
        return true;
    }

    private double calculateDistanceToOpponentHQ(Position unitPosition, Position opponentHqPosition) {
        return Math.sqrt(Math.pow(opponentHqPosition.x - unitPosition.x, 2.0) + Math.pow(opponentHqPosition.y - unitPosition.y, 2.0));
    }

    // train near the HQ for now

    private Position findTrainingPosition() {
        Building HQ = getOpponentHQ();
        if (HQ.p.x == 0) {
            return new Position(0, 1);
        }
        return new Position(11, 10);
    }

    public void output() {
        StringBuilder s = new StringBuilder("WAIT;");
        output.stream().forEach(c -> s.append(c.get()));
        System.out.println(s);
    }

    public void debug() {
        units.stream().forEach(Unit::debug);
        buildings.stream().forEach(Building::debug);
    }

    private Building getHQ() {
        final Optional<Building> hq = buildings.stream().filter(b -> b.isHQ() && b.isOwned()).findFirst();
        return (hq.isPresent() ? hq : Optional.<Building>empty()).get();
    }

    private Building getOpponentHQ() {
        final Optional<Building> opponentHQ = buildings.stream().filter(b -> b.isHQ() && !b.isOwned()).findFirst();
        return (opponentHQ.isPresent() ? opponentHQ : Optional.<Building>empty()).get();
    }

}