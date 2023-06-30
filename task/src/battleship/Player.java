package battleship;

import java.util.Scanner;

public class Player {
    static final String MSG_ENTER_COORDINATES = "Enter the coordinates of the %s (%d cells):";
    static final String MSG_GAME_STARTS = "The game starts!";
    static final String MSG_TAKE_A_SHOT = "Take a shot!";

    private final Board board;

    Player() {
        board = new Board();
    }

    public void giveShipLocation(Ship ship) {
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

    public void drawFullBoard() {
        System.out.println(board.getBoardForDisplay(false));
    }

    public void drawFoggedBoard() {
        System.out.println(board.getBoardForDisplay(true));
    }

    public void startGame() {
        System.out.println(MSG_GAME_STARTS + "\n");
        drawFoggedBoard();
        System.out.println(MSG_TAKE_A_SHOT + "\n");
    }


    public void fire() {
        coordinatesPromptAndCheck();
        drawFoggedBoard();
        String result = board.lastShotResult();
        System.out.println(result);
    }

    public void coordinatesPromptAndCheck() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String coordinates = scanner.nextLine().trim();
                Position position = getPositionFromText(coordinates);
                board.processShelling(position);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean endGame() {
        return board.allShipsShelled();
    }
}

