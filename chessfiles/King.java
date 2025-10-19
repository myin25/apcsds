import java.awt.*;
import java.util.*;

/**
 * Represents King piece in chess
 * @author Melody Yin
 * @version 4-9-22
 */
public class King extends Piece
{
    /**
     * Constructor for objects of class King
     * @param col           piece color
     * @param fileName      pathway to image of piece
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Determines all of the possible destinations for the piece
     * @return a list of all possible destinations
     */
    public ArrayList<Location> destinations()
    {
        int[] row = { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] col = { -1, 0, 1, 1, 1, 0, -1, -1 };

        ArrayList<Location> possibledests = new ArrayList<Location>();
        Location loc = getLocation();
        
        for (int i = 0; i < row.length; i++)
        {
            Location temploc = new Location(loc.getRow() + row[i],
                        loc.getCol() + col[i]);
            if (isValidDestination(temploc))
            {
                possibledests.add(temploc);
            }
        }
        
        return possibledests;
    }
}
