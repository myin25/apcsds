/**
* Representation of (x, y) pairs. Contains the following methods:
*       MyLocation(int r, int c)
*       getRow()
*       getCol()
*       equals(Object other)
*       toString()
*       compareTo(Object x)
* @author Anu Datar
* @author Melody Yin
* @version 2-24-22
*/
public class MyLocation implements Comparable
{
    private int row;
    private int col;

    /**
     * Constructor: MyLocation()
     * Usage: MyLocation loc = new MyLocation(row, col);
     * -----------------------------
     * creates a MyLocation object with the given row & col
     * 
     * @param r - row of this MyLocation
     * @param c - column of this MyLocaiton
     */
    public MyLocation(int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * Method: getRow()
     * Usage: loc.getRow();
     * @return the object's row
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Method: getCol()
     * Usage: loc.getCol();
     * @return the object's column
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Method: equals()
     * Usage: loc.equals(Object other);
     * @param other         the object being compared to this object
     * @return if the x and y values of this object match the other's
     */
    public boolean equals(Object other)
    {
        return ((row == ((MyLocation) other).getRow()) &&
                (col == ((MyLocation) other).getCol()));
    }

    /**
     * Method: toString()
     * Usage: loc.toString();
     * @return a string representation of this object
       */
    public String toString()
    {
        return "(" + row + "," + col + ")";
    }

    /**
     * Method: compareTo()
     * Usage: loc.compareTo(Object x);
     * @param x         the object being compared to this object
     * @return a number less than zero if x > this object
     *         if they are equal, return 0
     *         else, return a number larger than zero
     */
    public int compareTo(Object x)
    {
        // check if its equal
        if (this.equals(x))
        {
            System.out.println("equal");
            return 0;
        }
        
        // row is higher priority than col
        if (this.getRow() == ((MyLocation) x).getRow())
        {
            System.out.println("less than");
            return (this.getCol() - ((MyLocation) x).getCol());
        }
        System.out.println("more than");
        return (this.getRow() - ((MyLocation) x).getRow());
    }
}