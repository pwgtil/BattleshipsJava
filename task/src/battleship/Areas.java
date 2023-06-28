package battleship;

import java.util.ArrayList;
import java.util.HashSet;

abstract class Area {
    ArrayList<Position> positions = new ArrayList<>();

    public HashSet<Position> setLocation(Position beginning, Position end) {
        return new HashSet<>();
    }
}

abstract class Ship extends Area {
    final public int noOfCells;
    final public String name;
    protected int hitPoints;


    Ship(int noOfCells, String name) {
        this.hitPoints = this.noOfCells = noOfCells;
        this.name = name;
    }

    @Override
    public HashSet<Position> setLocation(Position beginning, Position end) {

        HashSet<Position> areaOfInfluence = new HashSet<>();
        boolean isVertical = false;
        boolean isHorizontal = false;
        int colMin, colMax, rowMin, rowMax;

        // Boundaries preliminary check
        if (beginning.positionInBounds() && end.positionInBounds()) {

            // Vertical or Horizontal placement AND size check
            if (beginning.col == end.col) {
                isVertical = true;
                colMin = colMax = beginning.col;
                if (Math.abs(beginning.row - end.row) + 1 == noOfCells) {
//                    locationOK = true;
                    rowMin = Math.min(beginning.row, end.row);
                    rowMax = Math.max(beginning.row, end.row);
                    // All ok let's set up our ship
                    this.positions = fillShipLocation(rowMin, rowMax, colMin, colMax);
                    areaOfInfluence = fillAreaOfInfluence(this.positions);
                }
            } else if (beginning.row == end.row) {
                isHorizontal = true;
                rowMin = rowMax = beginning.row;
                if (Math.abs(beginning.col - end.col) + 1 == noOfCells) {
//                    locationOK = true;
                    colMin = Math.min(beginning.col, end.col);
                    colMax = Math.max(beginning.col, end.col);
                    // All ok let's set up our ship
                    this.positions = fillShipLocation(rowMin, rowMax, colMin, colMax);
                    areaOfInfluence = fillAreaOfInfluence(this.positions);
                }
            }
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
        return new HashSet<>();
    }
}

class Carrier extends Ship {
    Carrier() {
        super(5, "aircraft carrier");
    }
}

class Battleship extends Ship {
    public Battleship() {
        super(4, "battleship");
    }
}

class Submarine extends Ship {

    public Submarine() {
        super(3, "submarine");
    }
}

class Cruiser extends Ship {

    public Cruiser() {
        super(3, "cruiser");
    }
}

class Destroyer extends Ship {

    public Destroyer() {
        super(2, "destroyer");
    }
}