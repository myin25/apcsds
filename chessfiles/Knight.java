import java.awt.*;
import java.util.*;

/**
 * Represents Knight piece in a chess game
 * @author Melody Yin
 * @version 4-9-22
 */
public class Knight extends Piece
{
    /**
     * Constructor for objects of class King
     * @param col           piece color
     * @param fileName      pathway for the piece's image
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Finds all possible places that the piece can move to
     * @return all possible places the piece can move to
     */
    public ArrayList<Location> destinations()
    {
        int[] row = {2, 2, 1, 1, -1, -1, -2, -2};
        int[] col = {-1, 1, -2, 2, -2, 2, -1, 1};

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
