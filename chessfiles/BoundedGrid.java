import java.util.ArrayList;

/**
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 *
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 * 
 * @version 4-8-22
 * 
 * @param <E>               the object being kept in the grid
 */

public class BoundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }

    /**
     * @return the number of rows in the grid
     */
    public int getNumRows()
    {
        return occupantArray.length;
    }

    /**
     * @return the number of columns in the grid
     */
    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }

    /**
     * Determines if the provided location is within the bounds of the grid.
     * @param loc                   the location whose validity is being
     *                              determined
     * @return true if the location is within the bounds, false otherwise
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Finds all locations on the grid that are occupied (aren't null)
     * @return occupied locations on the grid
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    /**
     * Retrieves the object stored at a given location
     * @param loc                   the location that information is being 
     *                              retrieved from
     * @return the object at location loc
     */
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; 
        // unavoidable warning
    }

    /**
     * Adds a specified object to a given location on the grid
     * @param loc                   the location where the new object is 
     *                              being added
     * @param obj                   the object being added to the grid
     * @return the previous occupant of the grid (null if there was none)
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Removes an object from a specified location on the grid
     * @param loc                   the location from which the object is 
     *                              being removed from
     * @return the object that was just removed from the grid (null if it
     *                              was empty)
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
