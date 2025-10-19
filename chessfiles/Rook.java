import java.awt.*;
import java.util.*;

/**
 * Represents Rook piece in chess
 * @author Melody Yin
 * @version 4-9-22
 */
public class Rook extends Piece
{
    /**
     * Creates new rook object
     * @param col           rook color
     * @param fileName      pathway to rook's image
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 5);
    }
    
    /**
     * @return all possible places on the board that the rook can move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> possibledests = new ArrayList<Location>();
        Location loc = getLocation();
        
        sweep(possibledests, Location.NORTH);
        sweep(possibledests, Location.EAST);
        sweep(possibledests, Location.WEST);
        sweep(possibledests, Location.SOUTH);
        
        return possibledests;
    }
}
