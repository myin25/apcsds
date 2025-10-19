/**
* Rectangle represents a rectangle with the values length and width. It has 
*       the functions:
*           getLength()
*           getWidth()
*           toString()
*           equals(Rectangle rect2)
*           hashCode()
* @author Anu Datar
* @author Melody Yin
* @version 1-31-22
*/
public class Rectangle
{
    private int length;
    private int width;

    /**
     * Constructor function, sets length and width to be specified values
     * @param len is the length of the rectangle
     * @param w is the width of the rectangle
     */
    public Rectangle(int len, int w)
    {
        length = len;
        width = w;
    }

    /**
     * @return the length of the rectangle
     */
    public int getLength()
    {
        return length;
    }

    /**
     * @return the width of the rectangle
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return the rectangle converted into string form (length x width)
     */
    public String toString()
    {
        return length + "x" + width;
    }
    
    /**
     * Compares length and width of rect2 with rectangle to determine if they
     *      are equal. Overrides default equals() function.
     * @param rect2 is the rectangle being compared to
     * @return true if rect2 and rectangle have the same dimensions
     *         false if they have different dimensions
     */
    public boolean equals(Object rect2)
    {
        return (length == ((Rectangle) rect2).getLength() &&
                width == ((Rectangle) rect2).getWidth());
    }
    public boolean equals(Rectangle rect2)
    {
        return (length == rect2.getLength() &&
                width == rect2.getWidth());
    }    
    /**
     * Computes the hashcode of the rectangle and returns it
     * @return hashcode of the rectangle
     */
    public int hashCode()
    {
        return (457 * length + 269 * width);
    }
}