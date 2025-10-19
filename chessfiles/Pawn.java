import java.awt.*;
import java.util.*;

/**
 * Represents Pawn piece in chess
 * @author Melody Yin
 * @version 4-9-22
 */
public class Pawn extends Piece
{
    /**
     * Constructor for objects of class Pawn
     * @param col           pawn color
     * @param fileName      pathway to image of pawn
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * @return a list of possible locations for a pawn to move to
     */
    public ArrayList<Location> destinations()
    {
        int[] row;
        int[] col;
        int openingRow;
        
        if (this.getColor() == Color.WHITE)
        {
            int[] trow = {-1, -2, -1, -1};
            int[] tcol = {0, 0, 1, -1};
            row = trow;
            col = tcol;
            openingRow = 6;
        }
        else
        {
            int[] trow = {1, 2, 1, 1};
            int[] tcol = {0, 0, 1, -1};
            row = trow;
            col = tcol;
            openingRow = 1;
        }

        ArrayList<Location> possibledests = new ArrayList<Location>();
        Location loc = getLocation();
        
        Location temploc;
        temploc = new Location(loc.getRow() + row[0], loc.getCol() + col[0]);
        if (isValidDestination(temploc) && 
            this.getBoard().get(temploc) == null)
        {
            possibledests.add(temploc);
        }
        
        // can only move two steps forward during its opening move
        temploc = new Location(loc.getRow() + row[1], loc.getCol() + col[1]);
        if (loc.getRow() == openingRow && 
            this.isValidDestination(new Location(loc.getRow() + row[0], 
                loc.getCol() + col[0])))
        {
            possibledests.add(temploc);
        }
        
        // can only move diagonally when taking
        for (int i = 2; i < 4; i++)
        {
            temploc = new Location(loc.getRow() + row[i], 
                    loc.getCol() + col[i]);
            if (isValidDestination(temploc) && 
                    this.getBoard().get(temploc) != null)
            {
                possibledests.add(temploc);
            }
        }
        return possibledests;
    }
}
