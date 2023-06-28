package battleship;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testTestEquals() {
        assertEquals(new Position(0, 0), new Position(0, 0));
        assertNotSame(new Position(1, 0), new Position(0, 0));
        assertNotSame(new Position(0, 1), new Position(0, 0));
        assertNotSame(new Object(), new Position(0, 0));
        assertNotSame(new Position(0, 0), new Position(0, 9));
    }

    public void testInteger2StringNum() {
        assertEquals("B", Position.integer2StringLetter(1));
        assertEquals("C", Position.integer2StringLetter(2));
        assertEquals("D", Position.integer2StringLetter(3));
        assertEquals("J", Position.integer2StringLetter(9));
        assertEquals("A", Position.integer2StringLetter(0));
    }

    public void testInteger2StringLetter() {
        assertEquals("2", Position.integer2StringNum(1));
        assertEquals("3", Position.integer2StringNum(2));
        assertEquals("4", Position.integer2StringNum(3));
        assertEquals("10", Position.integer2StringNum(9));
        assertEquals("1", Position.integer2StringNum(0));
    }

    public void testStringNum2Int() {
        assertEquals(0, Position.stringNum2Int("1"));
        assertEquals(1, Position.stringNum2Int("2"));
        assertEquals(2, Position.stringNum2Int("3"));
        assertEquals(5, Position.stringNum2Int("6"));
        assertEquals(9, Position.stringNum2Int("10"));
    }

    public void testStringLetter2Int() {
        assertEquals(0, Position.stringLetter2Int("A"));
        assertEquals(1, Position.stringLetter2Int("B"));
        assertEquals(2, Position.stringLetter2Int("C"));
        assertEquals(5, Position.stringLetter2Int("F"));
        assertEquals(9, Position.stringLetter2Int("J"));
    }

    public void testPositionInBounds() {
        assertTrue(new Position(0, 0).positionInBounds());
        assertTrue(new Position(0, 4).positionInBounds());
        assertTrue(new Position(3, 0).positionInBounds());
        assertTrue(new Position(2, 9).positionInBounds());
        assertTrue(new Position(9, 0).positionInBounds());

        assertFalse(new Position(10,10).positionInBounds());
        assertFalse(new Position(-1,0).positionInBounds());
        assertFalse(new Position(11,1).positionInBounds());
        assertFalse(new Position(1,600).positionInBounds());
    }
}