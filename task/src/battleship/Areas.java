package battleship;

import java.util.ArrayList;
import java.util.HashSet;

abstract class Area {
    public ArrayList<Position> getPositions() {
        return positions;
    }

    protected ArrayList<Position> positions = new ArrayList<>();

    public HashSet<Position> setLocation(Position beginning, Position end) {
        return new HashSet<>();
    }
}

abstract class Ship extends Area {

    final static String MSG_ERR_WRONG_LOCATION = "Error! Wrong ship location! Try again:";
    final static String MSG_ERR_WRONG_LENGTH = "Error! Wrong length of the %s! Try again:";

    final public int noOfCells;
    final public String name;
    protected int hitPoints;



    Ship(int noOfCells, String name) {
        this.hitPoints = this.noOfCells = noOfCells;
        this.name = name;
    }

    @Override
    public HashSet<Position> setLocation(Position beginning, Position end) {

        HashSet<Position> areaOfInfluence;
        int colMin, colMax, rowMin, rowMax;

        // Boundaries preliminary check
        if (beginning.positionInBounds() && end.positionInBounds()) {

            // Vertical or Horizontal placement AND size check
            if (beginning.col == end.col) {
                colMin = colMax = beginning.col;
                if (Math.abs(beginning.row - end.row) + 1 == noOfCells) {
//                    locationOK = true;
                    rowMin = Math.min(beginning.row, end.row);
                    rowMax = Math.max(beginning.row, end.row);
                    // All ok let's set up our ship
                    this.positions = fillShipLocation(rowMin, rowMax, colMin, colMax);
                    areaOfInfluence = fillAreaOfInfluence(this.positions);
                } else {
                    throw new IllegalArgumentException(String.format(MSG_ERR_WRONG_LENGTH, name));
                }
            } else if (beginning.row == end.row) {
                rowMin = rowMax = beginning.row;
                if (Math.abs(beginning.col - end.col) + 1 == noOfCells) {
//                    locationOK = true;
                    colMin = Math.min(beginning.col, end.col);
                    colMax = Math.max(beginning.col, end.col);
                    // All ok let's set up our ship
                    this.positions = fillShipLocation(rowMin, rowMax, colMin, colMax);
                    areaOfInfluence = fillAreaOfInfluence(this.positions);
                } else {
                    throw new IllegalArgumentException(String.format(MSG_ERR_WRONG_LENGTH, name));
                }
            } else {
                throw new IllegalArgumentException(MSG_ERR_WRONG_LOCATION);
            }
        } else {
            throw new IllegalArgumentException(MSG_ERR_WRONG_LOCATION);
        }
        return areaOfInfluence;
    }

    private HashSet<Position> fillAreaOfInfluence(ArrayList<Position> positions) {
        HashSet<Position> areaOfInfluence = new HashSet<>();
        for (Position position : positions) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Position temp = new Position(position.row + i, position.col + j);
                    if (temp.positionInBounds()) {
                        areaOfInfluence.add(temp);
                    }
                }
            }
        }
        return areaOfInfluence;
    }

    private ArrayList<Position> fillShipLocation(int rowMin, int rowMax, int colMin, int colMax) {
        ArrayList<Position> shipLocation = new ArrayList<>();
        for (int row = rowMin; row <= rowMax; row++) {
            for (int col = colMin; col <= colMax; col++) {
                Position position = new Position(row, col);
                shipLocation.add(position);
            }
        }
        return shipLocation;
    }
}

class Shelling extends Area {

    @Override
    public HashSet<Position> setLocation(Position beginning, Position end) {
        // TODO: 2023-06-29 complete method. next stage 
        return new HashSet<>();
    }
}

class Carrier extends Ship {
    Carrier() {
        super(5, "Aircraft Carrier");
    }
}

class Battleship extends Ship {
    public Battleship() {
        super(4, "Battleship");
    }
}

class Submarine extends Ship {

    public Submarine() {
        super(3, "Submarine");
    }
}

class Cruiser extends Ship {

    public Cruiser() {
        super(3, "Cruiser");
    }
}

class Destroyer extends Ship {

    public Destroyer() {
        super(2, "Destroyer");
    }
}