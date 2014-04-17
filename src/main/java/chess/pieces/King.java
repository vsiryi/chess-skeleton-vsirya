package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;

/**
 * The King class
 */
public class King extends Piece {
    public King(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'k';
    }

    @Override
    protected void move(PieceMoveContext context) {
        for(int i = 1; i < 9; i++){
            context.add(nextPosition(context.getPosition(), Direction.getDirection(i)));
        }
    }
}
