package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

    @Override
    protected void move(PieceMoveContext context) {
        for(int i = 1; i < 9; i = i + 2){
            Direction direction = Direction.getDirection(i);
            Position semiPosition = nextPosition(nextPosition(context.getPosition(), direction), direction);
            context.add(nextPosition(semiPosition, Direction.getDirection(i + 2)));
            context.add(nextPosition(semiPosition, Direction.getDirection(i - 2)));
        }
    }
}
