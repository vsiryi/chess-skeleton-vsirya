package chess.pieces;

import chess.Player;
import chess.Position;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.*;

/**
 * @author Vitalii Siryi
 */
public class PieceTest {

    @Test
    public void testPawnMoveFields() throws Exception {
        {
            Piece piece = new Pawn(Player.White);

            Set<Position> moves = piece.move(new Position("d3"));
            assertTrue("Move to d4 available", moves.contains(new Position("d4")));
            assertFalse("Move to d5 not available", moves.contains(new Position("d5")));
            assertFalse("Move to d2 not available", moves.contains(new Position("d2")));
            assertFalse("Move to e3 not available", moves.contains(new Position("e3")));

            moves = piece.move(new Position("e8"));
            assertFalse("Position e9 out of board", moves.contains(new Position("e9")));
            assertFalse("Move to e7 not available", moves.contains(new Position("e7")));

            moves = piece.move(new Position("a2"));
            assertTrue("Move to a3 available", moves.contains(new Position("a3")));
            assertTrue("Move to a4 available", moves.contains(new Position("a4")));
            assertFalse("Move to a5 not available", moves.contains(new Position("a5")));
        }

        {
            Piece piece = new Pawn(Player.Black);

            Set<Position> moves = piece.move(new Position("d3"));
            assertFalse("Move to d4 not available", moves.contains(new Position("d4")));
            assertTrue("Move to d2 available", moves.contains(new Position("d2")));

            moves = piece.move(new Position("a7"));
            assertTrue("Move to a6 available", moves.contains(new Position("a6")));
            assertTrue("Move to a7 available", moves.contains(new Position("a5")));
            assertFalse("Move to a4 not available", moves.contains(new Position("a4")));
        }
    }

    @Test
    public void testBishopMoveFields() throws Exception {
        {
            Piece piece = new Bishop(Player.Black);

            Set<Position> moves = piece.move(new Position("d3"));
            assertTrue("Move to c2 available", moves.contains(new Position("c2")));
            assertTrue("Move to b1 available", moves.contains(new Position("b1")));
            assertTrue("Move to g6 available", moves.contains(new Position("g6")));
            assertTrue("Move to f1 available", moves.contains(new Position("f1")));
            assertTrue("Move to c4 available", moves.contains(new Position("c4")));

            assertFalse("Move to c3 not available", moves.contains(new Position("c3")));
            assertFalse("Move to f4 not available", moves.contains(new Position("f4")));
            assertFalse("Move to d5 not available", moves.contains(new Position("d5")));
        }
    }

}
