import java.awt.*;
import java.util.*;

/**
 * Represesents a rectangular game board, containing Piece objects.
 * @author Anu Datar
 * @author Melody Yin
 * @version 4-8-22
 */
public class Board extends BoundedGrid<Piece>
{
    /**
     * Constructs a new Board with the given dimensions
     */
    public Board()
    {
        super(8, 8);
    }

    /**
     * Undoes a specified move
     * @precondition               move has already been made on the board
     * @postcondition              piece has moved back to its source, and any 
     *                             captured piece is returned to its location
     * @param move             the move that is being undone
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();
        
        piece.moveTo(source);
        if (victim != null)
        {
            victim.putSelfInGrid(piece.getBoard(), dest);
        }
    }

    /**
     * Makes a list of all possible moves by a player
     * @param color                 the player identifier (their color)
     * @return a list of all possible moves for a player
     */
    public ArrayList<Move> allMoves(Color color)
    {
        ArrayList<Location> piecelocs = this.getOccupiedLocations();
        ArrayList<Move> moves = new ArrayList<Move>();
        
        for (Location loc : piecelocs)
        {
            Piece tpiece = this.get(loc);
            if (tpiece.getColor().equals(color))
            {
                for (Location dest : tpiece.destinations())
                {
                    moves.add(new Move(tpiece, dest));
                }
            }
        }
        
        return moves;
    }
    
    /**
     * Executes a given move by moving the specified piece to a new location
     * @param move              the specified move (provides potential 
     *                          victims, source, destination, etc.)
     */
    public void executeMove(Move move)
    {
        (move.getPiece()).moveTo(move.getDestination());
    }
}