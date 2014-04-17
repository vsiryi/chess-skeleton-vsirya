package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

/**
 * The Pawn
 */
public class Pawn extends Piece {

    public Pawn(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'p';
    }

    @Override
    protected void move(PieceMoveContext context) {
        Position next = nextPosition(context.getPosition(), Direction.FORWARD);
        context.add(next, false);
        if (
                (context.getPosition().getRow() == 2 && Player.White.equals(getOwner()) ||
                        (context.getPosition().getRow() == 7 && Player.Black.equals(getOwner())))
                ) {
            context.add(nextPosition(next, Direction.FORWARD), false);
        }

        checkIfEnemyLocated(nextPosition(context.getPosition(), Direction.FORWARD_RIGHT), context);
        checkIfEnemyLocated(nextPosition(context.getPosition(), Direction.FORWARD_LEFT), context);
    }

    private void checkIfEnemyLocated(Position position, PieceMoveContext context){
        Piece piece = context.getPositionToPieceMap().get(position);
        if(piece != null && !piece.getOwner().equals(getOwner())){
            context.add(position);
        }
    }

}
