package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'b';
    }

    @Override
    protected void move(PieceMoveContext context) {
        for(int i = 2; i < 9; i = i + 2){
            Direction direction = Direction.getDirection(i);
            Position position = context.getPosition();
            do position = nextPosition(position, direction);
            while (context.add(position));
        }
    }


}
