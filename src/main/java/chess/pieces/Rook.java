package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

    public Rook(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'r';
    }

    @Override
    protected void move(PieceMoveContext context) {
        for(int i = 1; i < 9; i = i + 2){
            Direction direction = Direction.getDirection(i);
            Position position = context.getPosition();
            do position = nextPosition(position, direction);
            while (context.add(position));
        }
    }
}
