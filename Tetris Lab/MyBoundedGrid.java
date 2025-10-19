import java.util.*;
/**
 * Simulates grid which can contain, remove, and add items inside
 *
 * @author Anu Datar
 * @author Melody Yin
 * @param <E> the kind of element being stored in the grid
 * @version 3-2-22
 */
public class MyBoundedGrid<E>
{
    Object[][] grid;
    // BlockDisplay display;
    int numrow;
    int numcol;
    
    /**
     * Constructor for objects of class MyBoundedGrid
     * @param nrow              the number of rows in the grid
     * @param ncol              the number of cols in the grid
     */
    public MyBoundedGrid(int nrow, int ncol)
    {
        grid = new Object[nrow][ncol];
        numrow = nrow;
        numcol = ncol;
        // display = new BlockDisplay((MyBoundedGrid<Block>) this);
    }
    
    /**
     * @return the number of rows in the grid
     */
    public int getNumRows()
    {
        return numrow;
    }
    
    /**
     * @return the number of columns in the grid
     */
    public int getNumCols()
    {
        return numcol;
    }
    
    /**
     * Determines if a given location is within the bounds of the grid
     * @param loc           the location we are determining validity of
     * @return true if the location is within the bounds of the grid
     *          false if the location is not
     */
    public boolean isValid(Location loc)
    {
        if (loc == null)
        {
            return false;
        }
        
        return ((0 <= loc.getRow() && loc.getRow() <= numrow - 1) &&
               (0 <= loc.getCol() && loc.getCol() <= numcol - 1));
    }
    
    /**
     * Inserts a specified item in a specified location and returns the
     *      previous occupant of that location
     * @param location      the location we are inserting the new item in
     * @param toinsert      the item we are inserting into the grid
     * @return null if the cell was empty before insertion
     *          the previous occupant of the cell if the cell wasn't empty
     *              before insertion
     */
    public E put(Location location, E toinsert)
    {
        if (!isValid(location))
        {
            return null;
        }
        
        E old = (E) grid[location.getRow()][location.getCol()];
        grid[location.getRow()][location.getCol()] = toinsert;
        return old;
    }
    
    /**
     * @param location      the location we are looking for an object in
     * @return null if the location is invalid
     *              otherwise, the item at the specified location
     */
    public E get(Location location)
    {
        if (!isValid(location))
        {
            return null;
        }
        
        return ((E) grid[location.getRow()][location.getCol()]);
    }
    
    /**
     * @param location          the location of the cell that is being 
     *                              emptied
     * @return the previous occupant of the now empty cell
     * @postcondition the cell at the specified location in the grid is empty
     */
    public E remove(Location location)
    {
        if (!isValid(location))
        {
            return null;
        }
        
        E old = (E) grid[location.getRow()][location.getCol()];
        if (old != null)
        {
            grid[location.getRow()][location.getCol()] = null;
        }
        
        return old;
    }
    
    /**
     * @return all of the occupied locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (int row = 0; row < numrow; row++)
        {
            for (int col = 0; col < numcol; col++)
            {
                if (grid[row][col] != null)
                {
                    locs.add(new Location(row, col));
                }
            }
        }
        
        return locs;
    }
}
