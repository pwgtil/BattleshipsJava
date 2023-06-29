package battleship;

import java.util.Scanner;

public class Player {
    static final String MSG_ENTER_COORDINATES = "Enter the coordinates of the %s (%d cells):";
    static final String MSG_GAME_STARTS = "The game starts!";
    static final String MSG_TAKE_A_SHOT = "Take a shot!";
    static final String MSG_YOU_MISS = "You missed!";
    static final String MSG_YOU_HIT = "You hit a ship!";

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

    public void drawBoard() {
        System.out.println(board.getBoardForDisplay());
    }

    public void startGame() {
        System.out.println(MSG_GAME_STARTS + "\n");
        System.out.println(board.getBoardForDisplay());
    }


    public void fire() {
        coordinatesPromptAndCheck();
        drawBoard();
        String result = board.lastShotResult() ? MSG_YOU_HIT : MSG_YOU_MISS;
        System.out.println(result + "\n");
    }

    public void coordinatesPromptAndCheck(){
        System.out.println(MSG_TAKE_A_SHOT);
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

    public void win() {
        // TODO: 2023-06-29 complete method. Next stage
    }
}

