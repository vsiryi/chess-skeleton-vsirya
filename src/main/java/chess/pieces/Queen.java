package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

/**
 * The Queen
 */
public class Queen extends Piece{
    public Queen(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'q';
    }

    @Override
    protected void move(PieceMoveContext context) {
        for(int i = 1; i < 9; i++){
            Direction direction = Direction.getDirection(i);
            Position position = context.getPosition();
            do position = nextPosition(position, direction);
            while (context.add(position));
        }
    }
}
