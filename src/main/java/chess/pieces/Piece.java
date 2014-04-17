package chess.pieces;

import chess.PieceMoveContext;
import chess.Player;
import chess.Position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;

    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * Get position that available for movement. Use for EMPTY board.
     *
     * @param to Position where piece located
     * @return Set of position where piece may move
     */
    public Set<Position> move(Position to){
        return move(to, Collections.EMPTY_MAP);
    }

    /**
     * Get position that available for movement
     *
     * @param to Position where piece located
     * @param positionToPieceMap Other pieces places
     * @return Set of position where piece may move
     */
    public Set<Position> move(Position to, Map<Position, Piece> positionToPieceMap){
        PieceMoveContext context = new PieceMoveContext(to, owner, positionToPieceMap);
        move(context);
        return context.getMoves();
    }

    protected abstract void move(PieceMoveContext context);

    protected abstract char getIdentifyingCharacter();

    protected Position nextPosition(Position from, Direction direction){
        if(Player.Black.equals(owner)){
            direction = Direction.getDirection(direction.getValue() + 4);
        }

        Position position = null;
        switch (direction){
            case FORWARD:
                position = new Position(from.getColumn(), from.getRow() + 1);
                break;
            case FORWARD_RIGHT:
                position = new Position((char)((int)from.getColumn() + 1), from.getRow() + 1);
                break;
            case RIGHT:
                position = new Position((char)((int)from.getColumn() + 1), from.getRow());
                break;
            case BACK_RIGHT:
                position = new Position((char)((int)from.getColumn() + 1), from.getRow() - 1);
                break;
            case BACK:
                position = new Position(from.getColumn(), from.getRow() - 1);
                break;
            case BACK_LEFT:
                position = new Position((char)((int)from.getColumn() - 1), from.getRow() - 1);
                break;
            case LEFT:
                position = new Position((char)((int)from.getColumn() - 1), from.getRow());
                break;
            case FORWARD_LEFT:
                position = new Position((char)((int)from.getColumn() - 1), from.getRow() + 1);
                break;
        }

        return position;
    }


}
