package battleship;

import java.time.temporal.ValueRange;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Position {
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getRowDisplay() {
        return rowDisplay;
    }

    public String getColDisplay() {
        return colDisplay;
    }

    int row;
    int col;

    String rowDisplay;
    String colDisplay;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public boolean setDisplayValues() {
        try {
            this.rowDisplay = Position.integer2StringLetter(this.row);
            this.colDisplay = Position.integer2StringNum(this.col);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //--STATICS---------------------------------------------------------------------------------------------------------
    //--Core parsers between internal state and display state of positions (A -> 1 etc.)--------------------------------
    //------------------------------------------------------------------------------------------------------------------

    static public int stringNum2Int(String num) throws IllegalArgumentException {
        if (Board.colStr2Int.containsKey(num)){
            return Board.colStr2Int.get(num);
        } else {
            throw new IllegalArgumentException(String.format("Error. Expected a number between 1 and %d.", Board.boardSize));
        }
    }

    static public int stringLetter2Int(String letter) throws IllegalArgumentException {
        if (Board.rowStr2Int.containsKey(letter)){
            return Board.rowStr2Int.get(letter);
        } else {
            throw new IllegalArgumentException(String.format("Error. Expected a single capital letter between A and %s.", Board.colInt2Str.get(Board.boardSize - 1)));
        }
    }

    static public String integer2StringNum(int number) throws IllegalArgumentException {
        if (Board.colInt2Str.containsKey(number)){
            return Board.colInt2Str.get(number);
        } else {
            throw new IllegalArgumentException(String.format("Error. Expected a number between 0 and %d.", Board.boardSize - 1));
        }
    }

    static public String integer2StringLetter(int number) throws IllegalArgumentException {
        if (Board.rowInt2Str.containsKey(number)){
            return Board.rowInt2Str.get(number);
        } else {
            throw new IllegalArgumentException(String.format("Error. Expected a number between 0 and %d.", Board.boardSize - 1));
        }
    }

    //--STATICS---------------------------------------------------------------------------------------------------------
    //--Static checks section-------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    static public boolean positionInBounds(Position position) {
        ValueRange range = ValueRange.of(0, Board.boardSize - 1);
        if (range.isValidIntValue(position.col) && range.isValidIntValue(position.row)) {
            return true;
        } else {
            return false;
        }
    }
}


