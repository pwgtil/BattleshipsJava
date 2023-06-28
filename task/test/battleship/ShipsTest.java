package battleship;

import junit.framework.TestCase;

public class ShipsTest extends TestCase {

    Ship carrier;
    Ship battleship;
    Ship submarine;
    Ship cruiser;
    Ship destroyer;

    public void setUp() throws Exception {
        super.setUp();
        carrier = new Carrier();
        battleship = new Battleship();
        submarine = new Submarine();
        cruiser = new Cruiser();
        destroyer = new Destroyer();

    }

    public void testNameAndSize() {
        assertEquals("aircraft carrier", carrier.name);
        assertEquals("battleship", battleship.name);
        assertEquals("submarine", submarine.name);
        assertEquals("cruiser", cruiser.name);
        assertEquals("destroyer", destroyer.name);

        assertEquals(5, carrier.noOfCells);
        assertEquals(4, battleship.noOfCells);
        assertEquals(3, submarine.noOfCells);
        assertEquals(3, cruiser.noOfCells);
        assertEquals(2, destroyer.noOfCells);
    }

    public void testSetLocation() {
        assertTrue(carrier.setLocation(new Position(0, 1), new Position(0, 5)).size() > 0);
        assertTrue(carrier.setLocation(new Position(0, 2), new Position(0, 6)).size() > 0);
        assertTrue(carrier.setLocation(new Position(0, 3), new Position(0, 7)).size() > 0);
        assertTrue(carrier.setLocation(new Position(0, 4), new Position(0, 8)).size() > 0);
        assertTrue(carrier.setLocation(new Position(1, 5), new Position(1, 9)).size() > 0);

        assertTrue(carrier.setLocation(new Position(1, 9), new Position(5, 9)).size() > 0);
        assertTrue(carrier.setLocation(new Position(2, 9), new Position(6, 9)).size() > 0);
        assertTrue(carrier.setLocation(new Position(3, 9), new Position(7, 9)).size() > 0);
        assertTrue(carrier.setLocation(new Position(4, 9), new Position(8, 9)).size() > 0);
        assertTrue(carrier.setLocation(new Position(5, 5), new Position(9, 5)).size() > 0);

        assertFalse(carrier.setLocation(new Position(0, 0), new Position(0, 5)).size() > 0);
        assertFalse(carrier.setLocation(new Position(1, 0), new Position(0, 5)).size() > 0);
        assertFalse(carrier.setLocation(new Position(1, 0), new Position(0, 5)).size() > 0);
        assertFalse(carrier.setLocation(new Position(-10, 0), new Position(0, 5)).size() > 0);
        assertFalse(carrier.setLocation(new Position(0, 400), new Position(0, 5)).size() > 0);
        assertFalse(carrier.setLocation(new Position(0, 0), new Position(800, 5)).size() > 0);
    }
}