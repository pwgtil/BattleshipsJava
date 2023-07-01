package battleship;

import java.util.Scanner;

public class Player {
    static final String MSG_ENTER_COORDINATES = "Enter the coordinates of the %s (%d cells):";
    static final String MSG_DASH_BAR = "---------------------";


    private final Board board;
    public final String nickname;

    Player(String name) {
        this.board = new Board();
        this.nickname = name;
    }

    private void setShipLocation(Ship ship) {
        System.out.printf(MSG_ENTER_COORDINATES + "%n", ship.name, ship.noOfCells);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String[] coordinates = scanner.nextLine().trim().split(" ", 2);
                Position beginning = getPositionFromText(coordinates[0]);
                Position end = getPositionFromText(coordinates[1]);
                board.processShipPlacement(ship, beginning, end);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Position getPositionFromText(String text) {
        return new Position(
                Position.stringLetter2Int(text.substring(0, 1).trim()),
                Position.stringNum2Int(text.substring(1).trim())
        );
    }

    public void setShipLocationAndDrawBoard(Ship ship) {
        setShipLocation(ship);
        drawFullBoard();
    }

    private void drawFullBoard() {
        System.out.println(board.getBoardForDisplay(false));
    }

    public void drawFoggedBoard() {
        System.out.println(board.getBoardForDisplay(true));
    }


    public void fire() {
        coordinatesPromptAndCheck();
        String result = board.lastShotResult();
        System.out.println(result);
    }

    // Uncomment the below code to have a chance to correct fire when incorrect location was set
    public void coordinatesPromptAndCheck() {
//        while (true) {
            try {
                String coordinates = Game.sc.nextLine().trim();
                Position position = getPositionFromText(coordinates);
                board.processShelling(position);
//                break;
            } catch (IllegalArgumentException e) {
                //System.out.println(e.getMessage());
            }
//        }
    }

    public boolean gameContinues() {
        return !board.allShipsShelled();
    }

    public void printBothBoards(Player enemy) {
        enemy.drawFoggedBoard();
        System.out.println(MSG_DASH_BAR);
        drawFullBoard();
    }
}

