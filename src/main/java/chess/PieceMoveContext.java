package chess;

import chess.pieces.Piece;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Vitalii Siryi
 */
public class PieceMoveContext {

    private Position position;
    private Player owner;
    private Map<Position, Piece> positionToPieceMap;
    private Set<Position> moves = new HashSet<>();

    public PieceMoveContext(Position position, Player owner, Map<Position, Piece> positionToPieceMap) {
        this.position = position;
        this.owner = owner;
        this.positionToPieceMap = positionToPieceMap;
    }

    public Position getPosition() {
        return position;
    }

    public Map<Position, Piece> getPositionToPieceMap() {
        return positionToPieceMap;
    }

    public Set<Position> getMoves() {
        return moves;
    }

    public boolean add(Position move){
        return add(move, true);
    }

    public boolean add(Position move, boolean killForward){
        if(move == null){
            return false;
        }

        if(move.onBoard()){
            Piece piece = positionToPieceMap.get(move);
            if(piece == null){
                moves.add(move);
                return true;
            } else {
                if(!owner.equals(piece.getOwner()) && killForward){
                    moves.add(move);
                }
                return false;
            }
        }  else {
            return false;
        }
    }
}
