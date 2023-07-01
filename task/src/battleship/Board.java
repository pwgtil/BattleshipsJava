package battleship;

import java.util.*;

public class Board {

    //------------------------------------------------------------------------------------------------------------------
    //--STATICS---------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    static final String MSG_ERR_TOO_CLOSE = "Error! You placed it too close to another one. Try again:";
    static final String MSG_YOU_MISS = "You missed. Try again:";
    static final String MSG_YOU_HIT = "You hit a ship! Try again:";
    static final String MSG_YOU_HIT_AND_SANK_SHIP = "You sank a ship! Specify a new target:";
    static final String MSG_YOU_WIN = "You sank the last ship. You won. Congratulations!";
    final static char ICON_FOG = '~';
    final static char ICON_SHIP = 'O';
    final static char ICON_HIT = 'X';
    final static char ICON_MISS = 'M';

    static final int BOARD_SIZE = 10;

    public static final HashMap<Integer, String> rowInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < BOARD_SIZE; i++) {
                put(i, String.valueOf((char) (i + 'A')));
            }
        }
    };
    public static final HashMap<Integer, String> colInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < BOARD_SIZE; i++) {
                put(i, String.valueOf(i + 1));
            }
        }
    };

    public static final HashMap<String, Integer> rowStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < BOARD_SIZE; i++) {
                put(String.valueOf((char) (i + 'A')), i);
            }
        }
    };
    public static final HashMap<String, Integer> colStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < BOARD_SIZE; i++) {
                put(String.valueOf(i + 1), i);
            }
        }
    };

    //-----------------------------------------------------------------------------------------------------------------
    //--Main stuff -------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    ArrayList<Area> areas;
    HashSet<Position> areaOfInfluence;


    Board() {
        areas = new ArrayList<>();
        areaOfInfluence = new HashSet<>();
    }

    public String getBoardForDisplay(boolean fog) {

        char[][] display = new char[Board.BOARD_SIZE][Board.BOARD_SIZE];
        Arrays.stream(display).forEach(row -> Arrays.fill(row, ICON_FOG));

        for (Area area : areas) {
            if (area instanceof Shelling) {
                for (Position pos : area.positions) {
                    if (display[pos.row][pos.col] == ICON_SHIP) {
                        display[pos.row][pos.col] = ICON_HIT;
                    } else {
                        display[pos.row][pos.col] = ICON_MISS;
                    }
                }
            }
            if (area instanceof Ship) {
                for (Position pos : area.positions) {
                    if (display[pos.row][pos.col] == ICON_MISS) {
                        display[pos.row][pos.col] = ICON_HIT;
                    } else {
                        display[pos.row][pos.col] = ICON_SHIP;
                    }
                }
            }

        }
        StringBuilder output = new StringBuilder();
        output.append(" ");
        for (String s : colStr2Int.keySet()) {
            output.append(" ").append(s);
        }
        output.append("\n");
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            output.append(rowInt2Str.get(row));
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                if (fog && display[row][col] == ICON_SHIP) {
                    output.append(" ").append(ICON_FOG);
                } else {
                    output.append(" ").append(display[row][col]);
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

    public void processShipPlacement(Ship ship, Position beginning, Position end) {
        ship.setLocation(new Position[]{beginning, end});
        checkAreaOfInfluence(ship);
        areas.add(ship);
        this.areaOfInfluence.addAll(ship.areaOfInfluence);
    }

    private void checkAreaOfInfluence(Ship ship) {
        for (Position position : areaOfInfluence) {
            if (ship.getPositions().contains(position)) {
                throw new IllegalArgumentException(MSG_ERR_TOO_CLOSE);
            }
        }
    }

    public void processShelling(Position coordinates) {
        List<Shelling> location = areas.stream()
                .filter(a -> a instanceof Shelling)
                .map(Shelling.class::cast)
                .filter(s -> s.positions.get(0).equals(coordinates))
                .toList();
        boolean alreadyShelled = location.size() > 0;
        if (!alreadyShelled) {
            Shelling shelling = new Shelling();
            shelling.setLocation(new Position[]{coordinates});
            areas.add(shelling);
        } else {
            areas.remove(location.get(0));
            location.get(0).noOfShootsAt++;
            areas.add(location.get(0));
        }
    }

    private Result checkHitAndProcess(Area shelling) {
        for (Area area : areas) {
            if (area instanceof Ship && area.positions.contains(shelling.positions.get(0))) {
                if (((Shelling) shelling).noOfShootsAt > 1) {
                    return Result.HIT;
                }
                if (((Ship) area).hitShipAndCheckIfDead()) {
                    if (allShipsShelled()) {
                        return Result.WIN;
                    } else {
                        return Result.HIT_AND_SUNK;
                    }
                } else {
                    return Result.HIT;
                }
            }
        }
        return Result.MISS;
    }


    public String lastShotResult() {
        return checkHitAndProcess(areas.get(areas.size() - 1)).msg;
    }

    public boolean allShipsShelled() {
        return areas.stream().filter(a -> a instanceof Ship).noneMatch(s -> ((Ship) s).isAlive());
    }

    enum Result {
        MISS(MSG_YOU_MISS), HIT(MSG_YOU_HIT), HIT_AND_SUNK(MSG_YOU_HIT_AND_SANK_SHIP), WIN(MSG_YOU_WIN);
        final String msg;

        Result(String msg) {
            this.msg = msg;
        }
    }

}
