import java.awt.*;
import java.util.*;

/**
 * Simulates Bishop piece in chess
 * @author Melody Yin
 * @version 4-8-22
 */
public class Bishop extends Piece
{
    /**
     * Initializes bishop class with color and pathway to image
     * @param col               bishop's color (black/white)
     * @param fileName          path to bishop's image
     */
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 5);
    }
    
    /**
     * @return a list of possible locations that a bishop can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> possibledests = new ArrayList<Location>();
        Location loc = getLocation();
        
        sweep(possibledests, Location.NORTHEAST);
        sweep(possibledests, Location.SOUTHEAST);
        sweep(possibledests, Location.NORTHWEST);
        sweep(possibledests, Location.SOUTHWEST);
        
        return possibledests;
    }
}
