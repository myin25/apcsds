import java.awt.Color;
/**
* class Block encapsulates a Block abstraction which can be placed into a 
* Gridworld style grid
* You are expected to comment this class according to the style guide.
* @author Anu Datar
* @author Melody Yin
* @version 3-2-22
*/
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    
    /**
    * Constructs a blue block, because blue is the greatest color ever!
    * @postcondition color, grid, and location are all initialized
    */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    
    /**
    * @return the color of the block
    */
    public Color getColor()
    {
        return color;
    }
    
    /**
    * Sets color to new color
    * @param newColor       the new color of the block
    * @postcondition block is a new color
    */
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    
    /**
    * @return the grid that the block is in
    */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }
    
    /**
    * @return the location of the block
    */
    public Location getLocation()
    {
        return location;
    }
    
    /**
    * Deletes block from grid and sets grid/location to be default values
    * @postcondition grid/location are default values, block deleted from
    *       grid
    */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;
    }
    
    /**
    * Adds block to grid in specified location + sets grid and loc to be new
    *       specified grid/loc
    * @param gr             the grid the block is being added to
    * @param loc            the location we are adding the block to
    */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        // if the space is already occupied, set that to be zero
        if (gr.get(loc) != null)
        {
            Block temp = gr.get(loc);
            temp.removeSelfFromGrid();
        }
        
        grid = gr;
        location = loc;
        grid.put(loc, this);
    }

    /**
    * Moves block to new location and erases previous location
    * @param newLocation            the new location of the block
    */
    public void moveTo(Location newLocation)
    {
        MyBoundedGrid<Block> gr = grid;
        Location loc = newLocation;
        
        this.removeSelfFromGrid();
        location = newLocation;
        this.putSelfInGrid(gr, loc);
    }

    /**
    * @return a string with the location and color of this block
    */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}