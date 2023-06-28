package battleship;

import java.time.temporal.ValueRange;
import java.util.ArrayList;

abstract class Ship {
    final public int noOfCells;
    final public String name;
    protected int hitPoints;
    ArrayList<Position> ship;
    ArrayList<Position> hits;

    Ship(int noOfCells, String name) {
        this.hitPoints = this.noOfCells = noOfCells;
        this.name = name;
    }

    public boolean setLocation(Position beginning, Position end) {

        boolean locationOK = false;
        boolean isVertical = false;
        boolean isHorizontal = false;
        int colMin, colMax, rowMin, rowMax;

        // Boundaries preliminary check
        if (Position.positionInBounds(beginning) && Position.positionInBounds(end)) {

            // Vertical or Horizontal placement AND size check
            if (beginning.col == end.col) {
                isVertical = true;
                colMin = colMax = beginning.col;
                if (Math.abs(beginning.row - end.row) + 1 == noOfCells) {
                    locationOK = true;
                    rowMin = Math.min(beginning.row, end.row);
                    rowMax = Math.max(beginning.row, end.row);
                    // All ok let's set up our ship
                    this.ship = fillShipLocation(rowMin, rowMax, colMin, colMax);
                }
            } else if (beginning.row == end.row) {
                isHorizontal = true;
                rowMin = rowMax = beginning.row;
                if (Math.abs(beginning.col - end.col) + 1 == noOfCells) {
                    locationOK = true;
                    colMin = Math.min(beginning.col, end.col);
                    colMax = Math.max(beginning.col, end.col);
                    // All ok let's set up our ship
                    this.ship = fillShipLocation(rowMin, rowMax, colMin, colMax);
                }
            }
        }
        return locationOK;
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


