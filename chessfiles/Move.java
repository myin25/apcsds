/**
 * Represents a single move, in which a piece moves to a destination
 * location. Since a move can be undone, also keeps track of the source
 * location and any captured victim.
 * @author Anu Datar
 * @version 4-8-22
*/
public class Move
{
    private Piece piece;          // the piece being moved
    private Location source;      // the location being moved from
    private Location destination; // the location being moved to
    private Piece victim;         // any captured piece at the destination
    
    /**
     * Constructs a new move for moving the given piece to the given
     * destination.
     * @param piece             the piece making the move
     * @param destination       the place that the piece is moving to
     * @postcondition new Move object is created (but not necessarily
     * executed yet)
     */
    public Move(Piece piece, Location destination)
    {
        this.piece = piece;
        this.source = piece.getLocation();
        this.destination = destination;
        this.victim = piece.getBoard().get(destination);
    
        if (source.equals(destination))
            throw new IllegalArgumentException("Both source" + 
                    " and dest are " + source);
    }
    
    /**
     * @return the piece being moved
     */
    public Piece getPiece()
    {
        return piece;
    }
    
    /**
     * @return the location being moved from
     */
    public Location getSource()
    {
        return source;
    }
    
    /**
     * @return the location being moved to
     */
    public Location getDestination()
    {
        return destination;
    }
    
    /**
     * @return the piece being captured at the destination, if any
     */
    public Piece getVictim()
    {
        return victim;
    }
    
    /**
     * @return a string description of the move
     */
    public String toString()
    {
        String newstr = piece + " from " + source + " to ";
        newstr += destination + " containing " + victim;
        return newstr;
    }
    
    /**
     * @return true if this move is equivalent to the given one
     * @param x             the object being compared to
     */
    public boolean equals(Object x)
    {
        Move other = (Move)x;
        return piece == other.getPiece() && source.equals(other.getSource()) &&
            destination.equals(other.getDestination()) &&
            victim == other.getVictim();
    }
    
    /**
     * @return a hash code for this move, such that equivalent moves have
     * the same hash code
     */
    public int hashCode()
    {
        return piece.hashCode() + source.hashCode() + destination.hashCode();
    }
}