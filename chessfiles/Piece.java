import java.awt.*;
import java.util.*;

/**
 * Represents a piece in chess
 * @author Anu Datar
 * @author Melody Yin
 * @version 4-9-22
 */
public abstract class Piece
{
    
    //the board this piece is on
    private Board board;
    
    //the location of this piece on the board
    private Location location;
    
    //the color of the piece
    private Color color;
    
    //the file used to display this piece
    private String imageFileName;
    
    //the approximate value of this piece in a game of chess
    private int value;
    
    /**
     * Constructs a new Piece with the given attributes
     * @param col               color of the piece
     * @param fileName          pathway to image of the piece
     * @param val               value of the piece
     */
    public Piece(Color col, String fileName, int val)
    {
        color = col;
        imageFileName = fileName;
        value = val;
    }
    
    /**
     * @return the board this piece is on
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * @return the location of this piece on the board
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * @return the color of this piece
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * @return the name of the file used to display this piece
     */
    public String getImageFileName()
    {
        return imageFileName;
    }
    
    /**
     * @return a number representing the relative value of this piece
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed. <br />
     * Precondition: (1) This piece is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. <br />
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. <br />
     * Precondition: (1) This piece is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }
    
    /**
     * @param dest          the destination that is getting its validity
     *                      checked
     * @return if the given destination is valid (if the destination
     *      is within the board's bounds and doesn't contain a piece
     *      of the same color)
     */
    public boolean isValidDestination(Location dest)
    {
        if (!board.isValid(dest))
        {
            return false;
        }
        if (board.get(dest) == null)
        {
            return true;
        }
        else if (board.get(dest).getColor() != this.getColor())
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * @return a list of possible destinations depending on the piece
     */
    public abstract ArrayList<Location> destinations();
    
    /**
     * Recieves a list (may be empty, might not be empty) and adds all possible
     *      locations that a piece can reach in a given direction. 
     * @param dests         list of possible places that a piece can reach
     * @param direction     the direction that the method is going to sweep
     */
    public void sweep(ArrayList<Location> dests, int direction)
    {
        Location loc = location.getAdjacentLocation(direction);
        
        while (isValidDestination(loc) == true && board.get(loc) ==  null)
        {
            dests.add(loc);
            loc = loc.getAdjacentLocation(direction);
        }
        
        if (isValidDestination(loc) && 
            board.get(loc).getColor() != this.getColor())
        {
            dests.add(loc);
        }
    }
}