package battleship;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.drawFullBoard();
        player.giveShipLocation(new Carrier());
        player.drawFullBoard();
        player.giveShipLocation(new Battleship());
        player.drawFullBoard();
        player.giveShipLocation(new Submarine());
        player.drawFullBoard();
        player.giveShipLocation(new Cruiser());
        player.drawFullBoard();
        player.giveShipLocation(new Destroyer());
        player.drawFullBoard();
        player.startGame();
        player.fire();
        player.drawFullBoard();

    }
}


