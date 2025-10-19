import java.awt.*;
import java.util.*;

/**
 * Represents Queen piece in chess
 * @author Melody Yin
 * @version 4-9-22
 */
public class Queen extends Piece
{
    /**
     * Constructor for objects of class Queen
     * @param col           queen's color
     * @param fileName      pathway to image of piece
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
    }

    /**
     * @return a list of all possible places the queen piece can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> possibledests = new ArrayList<Location>();
        Location loc = getLocation();
        
        sweep(possibledests, Location.NORTH);
        sweep(possibledests, Location.NORTHEAST);
        sweep(possibledests, Location.EAST);
        sweep(possibledests, Location.SOUTHWEST);
        sweep(possibledests, Location.WEST);
        sweep(possibledests, Location.SOUTHEAST);
        sweep(possibledests, Location.SOUTH);
        sweep(possibledests, Location.NORTHWEST);
        
        return possibledests;
    }
}
