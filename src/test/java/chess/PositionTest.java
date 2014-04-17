package chess;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic Unit Tests for the Position class
 */
public class PositionTest {

    @Test
    public void testStringParsingConstructor() {
        Position pos = new Position("d5");

        assertEquals("The column should be 'd'", 'd', pos.getColumn());
        assertEquals("The row should be 5", 5, pos.getRow());
    }

    @Test
    public void testPositionEquality() {
        Position one = new Position('a', 1);
        Position other = new Position('a', 1);

        assertEquals("The positions should equal each other", one, other);
    }

    @Test
    public void testPositionOnBoard() {
        {
            Position pos = new Position("d5");
            assertTrue("Position d5 on board", pos.onBoard());
        }

        {
            Position pos = new Position("h8");
            assertTrue("Position h8 on board", pos.onBoard());
        }

        {
            Position pos = new Position("a1");
            assertTrue("Position a1 on board", pos.onBoard());
        }

        {
            Position pos = new Position("d9");
            assertFalse("Position d9 out of board", pos.onBoard());
        }

        {
            Position pos = new Position("s2");
            assertFalse("Position s2 out of board", pos.onBoard());
        }
    }
}
