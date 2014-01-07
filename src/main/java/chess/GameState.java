package chess;


/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';

    private Player currentPlayer = Player.White;

    /**
     * Create the game state.
     */
    public GameState() {
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
