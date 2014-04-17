package chess;

import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }

    @Test
    public void testMiddleGameList() {
        // Start the game
        state.reset();

        //White move
        assertTrue("Correct move", state.move("e2", "e4"));

        //Black move
        assertFalse("Incorrect move, because black's move", state.move("a2", "a3"));
        assertTrue("Correct move", state.move("d7", "d5"));

        //White kill pawn
        assertTrue("Correct move", state.move("e4", "d5"));
        assertFalse("No check...", state.isCheck());

        //Black move
        assertTrue("Look at knight moves", state.list().contains("b8 c6"));
        assertTrue("Knight move", state.move("b8", "c6"));

        //White move
        assertTrue("Correct move", state.move("f2", "f3"));

        //Black make check
        assertTrue("Check!", state.move("d8", "h4"));
        assertTrue("Really check!", state.isCheck());
        assertFalse("End of Game?", state.isEndOfGame());

        //White
        assertEquals("Only 2 available moves", 2, state.list().size());
        assertTrue("King go away", state.list().contains("e1 e2"));
        assertTrue("Pawn defence", state.list().contains("g2 g3"));
    }
}
