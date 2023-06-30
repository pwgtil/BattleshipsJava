package battleship;

public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.CARRIER));
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.BATTLESHIP));
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.SUBMARINE));
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.CRUISER));
        player.drawFullBoard();
        player.giveShipLocation(new Ship(ShipType.DESTROYER));
        player.drawFullBoard();
        player.startGame();
        player.fire();
        player.drawFullBoard();

    }
}


