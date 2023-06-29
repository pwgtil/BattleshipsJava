package battleship;

import java.util.Scanner;

public class Player {
    static final String MSG_ENTER_COORDINATES = "Enter the coordinates of the %s (%d cells):";
    private final Board board;

    Player() {
        board = new Board();
    }

    public void giveShipLocation(Ship ship) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(MSG_ENTER_COORDINATES + "%n", ship.name, ship.noOfCells);
        while (true) {
            try {
                String[] coordinates = scanner.nextLine().trim().split(" ", 2);
                Position beginning = new Position(
                        Position.stringLetter2Int(coordinates[0].substring(0, 1).trim()),
                        Position.stringNum2Int(coordinates[0].substring(1).trim())
                );
                Position end = new Position(
                        Position.stringLetter2Int(coordinates[1].substring(0, 1).trim()),
                        Position.stringNum2Int(coordinates[1].substring(1).trim())
                );
                board.processShipPlacement(ship, beginning, end);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void drawBoard(){
        System.out.println(board.getBoardForDisplay());
    }



    public Shelling shoot() {
        // TODO: 2023-06-29 complete method. Next stage
        return new Shelling();
    }

    public void win() {
        // TODO: 2023-06-29 complete method. Next stage
    }
}

