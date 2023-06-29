package battleship;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.drawBoard();
        player.giveShipLocation(new Carrier());
        player.drawBoard();
        player.giveShipLocation(new Battleship());
        player.drawBoard();
        player.giveShipLocation(new Submarine());
        player.drawBoard();
        player.giveShipLocation(new Cruiser());
        player.drawBoard();
        player.giveShipLocation(new Destroyer());
        player.drawBoard();
        player.startGame();
        player.fire();

    }
}


