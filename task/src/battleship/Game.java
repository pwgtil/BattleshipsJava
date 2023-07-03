package battleship;

import java.util.Scanner;

public class Game {

    static final String MSG_PLACE_YOUR_SHIPS = "%s, place your ships on the game field\n";
    static final String MSG_ITS_YOUR_TURN = "%s, it's your turn:";
    static final String MSG_PRESS_ENTER = "Press Enter and pass the move to another player\n...";
    static final Scanner sc = new Scanner(System.in);

    PlayerMoves player1;
    PlayerMoves player2;
    PlayerMoves currentPlayer;

    Game() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        currentPlayer = player1;
    }

    public void initializationOfBoards() {
        // Player 1 set up the board
        playerBoardSetup(player1);
        passMove();
        // Player 2 set up the board
        playerBoardSetup(player2);
    }

    private void playerBoardSetup(PlayerMoves player) {
        System.out.printf(MSG_PLACE_YOUR_SHIPS + "\n", player.getNickname());
        player.printFoggedBoard();
        player.setShipLocationAndDrawBoard(new Ship(ShipType.CARRIER));
        player.setShipLocationAndDrawBoard(new Ship(ShipType.BATTLESHIP));
        player.setShipLocationAndDrawBoard(new Ship(ShipType.SUBMARINE));
        player.setShipLocationAndDrawBoard(new Ship(ShipType.CRUISER));
        player.setShipLocationAndDrawBoard(new Ship(ShipType.DESTROYER));
    }

    public void startGame() {
        // Game goes until one player wins
        while (player1.checkIfGameContinues() && player2.checkIfGameContinues()){
            passMove();
            currentPlayer.printBothBoards(getEnemy());
            System.out.printf(MSG_ITS_YOUR_TURN + "\n\n", currentPlayer.getNickname());
            getEnemy().fire();
            switchPlayer();
        }

    }

    private void switchPlayer() {
        this.currentPlayer = getEnemy();
    }

    private PlayerMoves getEnemy() {
        return currentPlayer == player1 ? player2 : player1;
    }

    public void passMove() {
        System.out.println(MSG_PRESS_ENTER);
        sc.nextLine();
    }

}
