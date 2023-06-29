package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Board {

    //--STATICS---------------------------------------------------------------------------------------------------------
    //--Setting up mappings-------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    final static String MSG_ERR_TOO_CLOSE = "Error! You placed it too close to another one. Try again:";
    final static char ICON_FOG = '~';
    final static char ICON_SHIP = 'O';
    final static char ICON_HIT = 'X';
    final static char ICON_MISS = 'M';

    static final int boardSize = 10;

    public static final HashMap<Integer, String> rowInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(i, String.valueOf((char) (i + 'A')));
            }
        }
    };
    public static final HashMap<Integer, String> colInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(i, String.valueOf(i + 1));
            }
        }
    };

    public static final HashMap<String, Integer> rowStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(String.valueOf((char) (i + 'A')), i);
            }
        }
    };
    public static final HashMap<String, Integer> colStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
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

        char[][] display = new char[Board.boardSize][Board.boardSize];
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
        for (int row = 0; row < Board.boardSize; row++) {
            output.append(rowInt2Str.get(row));
            for (int col = 0; col < Board.boardSize; col++) {
                if (fog && display[row][col] == ICON_SHIP){
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
        Shelling shelling = new Shelling();
        shelling.setLocation(new Position[]{coordinates});
        // TODO: 2023-06-29 add check if already shelled
        areas.add(shelling);
    }

    private boolean hitOrMiss(Area shelling) {
        for (Area area : areas) {
            if (area instanceof Ship && area.positions.contains(shelling.positions.get(0))) {
                return true;
            }

        }
        return false;
    }


    public boolean lastShotResult() {
        return hitOrMiss(areas.get(areas.size() - 1));
    }
}
