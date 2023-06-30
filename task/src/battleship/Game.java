package battleship;

import java.util.Scanner;

public class Game {

    static final String MSG_PLACE_YOUR_SHIPS = "%s, place your ships on the game field\n";
    static final String MSG_ITS_YOUR_TURN = "%s, it's your turn:";
    static final String MSG_PRESS_ENTER = "Press Enter and pass the move to another player\n...";
    static final Scanner sc = new Scanner(System.in);

    Player player1;
    Player player2;
    Player currentPlayer;

    Game() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
    }

    public void initializationOfBoards() {
        // Player 1 set up the board
        playerBoardSetup(player1);
        // Player 2 set up the board
        playerBoardSetup(player2);
    }

    private void playerBoardSetup(Player player) {
        System.out.printf(MSG_PLACE_YOUR_SHIPS + "\n", player.nickname);
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.CARRIER));
        player.drawFullBoard();
//        player.giveShipLocation(new Ship(ShipType.BATTLESHIP));
//        player.drawFullBoard();
//        player.giveShipLocation(new Ship(ShipType.SUBMARINE));
//        player.drawFullBoard();
//        player.giveShipLocation(new Ship(ShipType.CRUISER));
//        player.drawFullBoard();
//        player.giveShipLocation(new Ship(ShipType.DESTROYER));
//        player.drawFullBoard();
        passMove();
    }

    public void startGame() {
        // Game goes until one player wins
    }

    private void switchPlayer(Player player) {
        this.currentPlayer = player == player1 ? player2 : player1;
    }

    public void passMove() {
        System.out.println(MSG_PRESS_ENTER);
        sc.nextLine();
    }

}
