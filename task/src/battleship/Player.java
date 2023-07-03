package battleship;

import java.util.Scanner;

interface PlayerInfo {
    String getNickname();
    void printFoggedBoard();
    void printBothBoards(PlayerMoves enemy);
}

interface PlayerMoves extends PlayerInfo {
    void setShipLocationAndDrawBoard(Ship ship);
    void fire();
    boolean checkIfGameContinues();
}

public class Player implements PlayerMoves {
    static final String MSG_ENTER_COORDINATES = "Enter the coordinates of the %s (%d cells):";
    static final String MSG_DASH_BAR = "---------------------";


    private final BoardUtils board;
    private final String nickname;

    @Override
    public String getNickname() {
        return nickname;
    }

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

    @Override
    public void setShipLocationAndDrawBoard(Ship ship) {
        setShipLocation(ship);
        drawFullBoard();
    }

    @Override
    public void printFoggedBoard() {
        System.out.println(board.getBoardForDisplay(true));
    }

    @Override
    public void fire() {
        coordinatesPromptAndCheck();
        String result = board.lastShotResult();
        System.out.println(result);
    }

    private void drawFullBoard() {
        System.out.println(board.getBoardForDisplay(false));
    }


    // Uncomment the below code to have a chance to correct fire when incorrect location was set
    private void coordinatesPromptAndCheck() {
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

    @Override
    public boolean checkIfGameContinues() {
        return !board.allShipsShelled();
    }

    @Override
    public void printBothBoards(PlayerMoves enemy) {
        enemy.printFoggedBoard();
        System.out.println(MSG_DASH_BAR);
        drawFullBoard();
    }
}

