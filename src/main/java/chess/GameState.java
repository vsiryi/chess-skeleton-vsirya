package chess;


import chess.pieces.*;

import java.util.*;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = Player.White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    private Map<Position, Set<Position>> availableMoves;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<>();
        availableMoves = new HashMap<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getSeparatePlayer(){
        return currentPlayer == Player.White ? Player.Black : Player.White;
    }

    public GameState copy(){
        GameState copy = new GameState();
        copy.currentPlayer = currentPlayer;
        copy.positionToPieceMap.putAll(positionToPieceMap);
        copy.availableMoves.putAll(availableMoves);
        return copy;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(Player.White), new Position("a1"));
        placePiece(new Knight(Player.White), new Position("b1"));
        placePiece(new Bishop(Player.White), new Position("c1"));
        placePiece(new Queen(Player.White), new Position("d1"));
        placePiece(new King(Player.White), new Position("e1"));
        placePiece(new Bishop(Player.White), new Position("f1"));
        placePiece(new Knight(Player.White), new Position("g1"));
        placePiece(new Rook(Player.White), new Position("h1"));
        placePiece(new Pawn(Player.White), new Position("a2"));
        placePiece(new Pawn(Player.White), new Position("b2"));
        placePiece(new Pawn(Player.White), new Position("c2"));
        placePiece(new Pawn(Player.White), new Position("d2"));
        placePiece(new Pawn(Player.White), new Position("e2"));
        placePiece(new Pawn(Player.White), new Position("f2"));
        placePiece(new Pawn(Player.White), new Position("g2"));
        placePiece(new Pawn(Player.White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Player.Black), new Position("a8"));
        placePiece(new Knight(Player.Black), new Position("b8"));
        placePiece(new Bishop(Player.Black), new Position("c8"));
        placePiece(new Queen(Player.Black), new Position("d8"));
        placePiece(new King(Player.Black), new Position("e8"));
        placePiece(new Bishop(Player.Black), new Position("f8"));
        placePiece(new Knight(Player.Black), new Position("g8"));
        placePiece(new Rook(Player.Black), new Position("h8"));
        placePiece(new Pawn(Player.Black), new Position("a7"));
        placePiece(new Pawn(Player.Black), new Position("b7"));
        placePiece(new Pawn(Player.Black), new Position("c7"));
        placePiece(new Pawn(Player.Black), new Position("d7"));
        placePiece(new Pawn(Player.Black), new Position("e7"));
        placePiece(new Pawn(Player.Black), new Position("f7"));
        placePiece(new Pawn(Player.Black), new Position("g7"));
        placePiece(new Pawn(Player.Black), new Position("h7"));

        prepareAvailableMoves(positionToPieceMap, availableMoves);
    }

    /**
     * Get the piece at the position specified by the String
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    public List<String> list(){
        List<String> moves = new ArrayList<>();
        for(Position position :  positionToPieceMap.keySet()){
            Piece piece = positionToPieceMap.get(position);
            if(piece.getOwner() == currentPlayer){
                Set<Position> availableMoves = this.availableMoves.get(position);
                if (availableMoves != null) {
                    for(Position available : availableMoves){
                        moves.add(String.format("%s %s", position.toString(), available.toString()));
                    }
                }
            }
        }
        return moves;
    }

    public boolean move(String from, String to){
        Piece piece = getPieceAt(from);
        if(piece == null || piece.getOwner() != getCurrentPlayer()){
            return false;
        }

        Position pt = new Position(to);
        if(!pt.onBoard()){
            return false;
        }

        Position pf = new Position(from);

        if(!availableMoves.containsKey(pf) || !availableMoves.get(pf).contains(pt)){
            return false;
        }

        movePiece(piece, pf, pt);
        prepareAvailableMoves(positionToPieceMap, availableMoves);
        switchPlayer();
        return true;
    }

    private void switchPlayer(){
        currentPlayer = currentPlayer == Player.White ? Player.Black : Player.White;
    }

    public boolean isCheck(){
        Position king = getPlayerKingPosition(getCurrentPlayer());
        return king == null || getAvailableMovesForPlayer(getSeparatePlayer()).contains(king);
    }

    public boolean isEndOfGame(){
        if(isCheck()){
            //needs to get all moves, that keep King in game. If no moves available - End of Game

            //prepare copy of available moves
            Map<Position, Set<Position>> safeMoves = new HashMap<>();

            for(Position from : availableMoves.keySet()){
                if(!isCurrentUserPiece(from)){
                    continue;
                }
                Set<Position> available = availableMoves.get(from);
                Set<Position> real = new HashSet<>();
                for(Position to : available){
                    GameState copyState = this.copy();
                    copyState.move(from.toString(), to.toString());
                    copyState.switchPlayer();
                    if(!copyState.isCheck()){
                        real.add(to);
                    }
                }

                if(real.size() > 0){
                    safeMoves.put(from, real);
                }
            }
            availableMoves = safeMoves;
            return availableMoves.size() == 0;
        } else {
            return false;
        }
    }

    private void prepareAvailableMoves(Map<Position, Piece> positionToPieceMap,
                                       Map<Position, Set<Position>> availableMoves){
        availableMoves.clear();
        for(Position position :  positionToPieceMap.keySet()){
            Piece piece = positionToPieceMap.get(position);
            Set<Position> available = piece.move(position, positionToPieceMap);
            availableMoves.put(position, available);
        }
    }

    private Set<Position> getAvailableMovesForPlayer(Player player){
        Set<Position> moves = new HashSet<>();
        for(Position position :  positionToPieceMap.keySet()){
            Piece piece = positionToPieceMap.get(position);
            if (piece.getOwner() == player) {
                Set<Position> pieceMoves = availableMoves.get(position);
                if(pieceMoves != null){
                    moves.addAll(pieceMoves);
                }
            }
        }
        return moves;
    }

    private Position getPlayerKingPosition(Player player){
        for(Position position : positionToPieceMap.keySet()){
            Piece piece = positionToPieceMap.get(position);
            if (piece.getOwner() == player && piece instanceof King) {
                return position;
            }
        }
        return null;
    }

    /**
     * Method to place a piece at a given position
     * @param piece The piece to place
     * @param position The position
     */
    private void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    private void movePiece(Piece piece, Position from, Position to){
        positionToPieceMap.remove(from);
        placePiece(piece, to);
    }

    private boolean isCurrentUserPiece(Position position){
        Piece piece = getPieceAt(position);
        return piece.getOwner() == getCurrentPlayer();
    }
}
