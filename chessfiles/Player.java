import java.util.*;
import java.awt.*;

/**
 * Represents various players 
 * @author Melody Yin
 * @version 4-9-22
 */
public abstract class Player
{
    private Board board;
    private String name;
    private Color color;

    /**
     * Constructor for objects of class Player
     * @param b             player's board
     * @param n             player's name
     * @param c             player's color
     */
    public Player(Board b, String n, Color c)
    {
        board = b;
        name = n;
        color = c;
    }
    
    /**
     * @return the player's board
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return the player's color
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * @return the player's move
     */
    public abstract Move nextMove();
}
