import java.awt.Color;
import java.lang.Math;
import java.util.concurrent.Semaphore;

/**
 * Generates a random Tetrad that can be moved around by a user/program.
 *
 * @author Melody Yin
 * @version 3-10-22
 */
public class Tetrad
{
    private Block[] blocks;
    private MyBoundedGrid<Block> grid;
    private Color color;
    private int shapenum;
    private Semaphore lock;
    private boolean done;
    
    /**
     * Initializes random Tetrad block by generating a random number from
     *      1 to 7 and then creating the corresponding block. It then adds
     *      the new Tetrad to the grid in the middle.
     * @param gr                the grid that the Tetrad is being stored in
     * @postcondition blocks, grid, color, shapenum, and lock are all 
     *      initialized.
     */
    public Tetrad(MyBoundedGrid<Block> gr)
    {
        blocks = new Block[4];
        grid = gr;
        
        // Generate random number + tetrad
        // I = CYAN, T = PURPLE, O = YELLOW, J = BLUE, S = GREEN, Z = RED,
        //      L = ORANGE
        int randomBlock = (int) (Math.random() * 7);
        shapenum = randomBlock;
        Location[] locs = new Location[4];
        if (randomBlock == 0) // I block
        {
            color = Color.CYAN;
            for (int i = 0; i < 4; i++)
            {
                locs[i] = new Location(i, 4);
            }
        }
        else if (randomBlock == 1) // T block
        {
            color = Color.MAGENTA;
            for (int i = 0; i < 3; i++)
            {
                locs[i] = new Location(0, 3 + i);
            }
            
            locs[3] = new Location(1, 4);
        }
        else if (randomBlock == 2) // O block
        {
            color = Color.YELLOW;
            int count = 0;
            for (int r = 0; r < 2; r++)
            {
                for (int c = 0; c < 2; c++)
                {
                    locs[count] = new Location(r, 4 + c);
                    count++;
                }
            }
        }
        else if (randomBlock == 3) // J block
        {
            color = Color.BLUE;
            
            for (int i = 0; i < 3; i++)
            {
                locs[i] = new Location(i, 5);
            }
            
            locs[3] = new Location(2, 4);
        }
        else if (randomBlock == 4) // S block
        {
            color = Color.GREEN;
            
            locs[0] = new Location(1, 4);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 3);
            locs[3] = new Location(0, 5);
        }
        else if (randomBlock == 5) // Z block
        {
            color = Color.RED;
            
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 3);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);
        }
        else // L block
        {
            color = Color.ORANGE;
            
            for (int i = 0; i < 3; i++)
            {
                locs[i] = new Location(i, 4);
            }
            
            locs[3] = new Location(2, 5);
        }
        
        done = areEmpty(grid, locs);
        addToLocations(grid, locs);
        lock = new Semaphore(1, true);
    }
    
    /**
     * Given a grid and a series of locations, iterates through each location
     *      and adds a new block to the grid at the specified locations.
     * @param gr                the grid the blocks are being added to
     * @param locs              the locations the blocks are being added to
     */
    private void addToLocations(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for (int i = 0; i < 4; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(color);
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }
    
    /**
     * Removes the tetrad from the grid.
     * @return the old locations of the tetrad.
     */
    private Location[] removeBlocks()
    {
        Block temp;
        Location[] old = new Location[4];
        
        for (int i = 0; i < 4; i++)
        {
            temp = blocks[i];
            old[i] = temp.getLocation();
            temp.removeSelfFromGrid();
        }
        
        return old;
    }
    
    /**
     * @param gr                the grid we are looking for empty spots in
     * @param locs              the locations we are checking to be empty
     * @return true if all of the locations are empty, false otherwise
     */
    public boolean areEmpty(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for (int i = 0; i < 4; i++)
        {
            if (!gr.isValid(locs[i]) || gr.get(locs[i]) != null)
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * @param deltaRow                 the number of rows to translate
     *                                     the tetrad
     * @param deltaCol                 the number of cols to translate the
     *                                     tetrad
     * @return true if we can translate the tetrad there, false otherwise
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            
            Location[] oldloc = removeBlocks();
            Location[] newloc = new Location[4];
            
            for (int i = 0; i < 4; i++)
            {
                newloc[i] = new Location(oldloc[i].getRow() + deltaRow, 
                                         oldloc[i].getCol() + deltaCol);
            }
            
            if (areEmpty(grid, newloc))
            {
                addToLocations(grid, newloc);
                return true;
            }
            
            addToLocations(grid, oldloc);
            return false;
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
    
    /**
     * @return true if we can rotate the tetrad. otherwise, return false.
     */
    public boolean rotate()
    {
        try
        {
            lock.acquire();
            
            if (shapenum == 2) // O block
            {
                return true;
            }
            
            Location[] oldloc = removeBlocks();
            Location[] newloc = new Location[4];
            int row;
            int col;
            
            for (int i = 0; i < 4; i++)
            {
                row = oldloc[1].getRow() - oldloc[1].getCol() + 
                                    oldloc[i].getCol();
                col = oldloc[1].getRow() + oldloc[1].getCol() - 
                                    oldloc[i].getRow();
                newloc[i] = new Location(row, col);
            }
            
            if (areEmpty(grid, newloc))
            {
                addToLocations(grid, newloc);
                return true;
            }
            
            addToLocations(grid, oldloc);
            return false;
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
    
    /**
     * @return true if the game is over (player has lost), false otherwise
     */
    public boolean isGameOver()
    {
        return !done;
    }
}
