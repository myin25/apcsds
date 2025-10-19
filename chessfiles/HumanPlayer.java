import java.util.*;
import java.awt.*;

/**
 * Takes input from a human playing the game
 * @author Melody Yin
 * @version 4-9-22
 */
public class HumanPlayer extends Player
{
    BoardDisplay display;
    
    /**
     * Initializes board, name, color, and display
     * @param b         player's board
     * @param n         player's name
     * @param c         player's color
     * @param d         player's display
     */
    public HumanPlayer(Board b, String n, Color c, BoardDisplay d)
    {
        super(b, n, c);
        display = d;
    }
    
    /**
     * Determines the player's next move by taking input from the board
     * @return the player's move
     */
    public Move nextMove()
    {
        Move playersMove = null;
        ArrayList<Move> possiblemoves = (this.getBoard()).allMoves(
                this.getColor());
        while (!possiblemoves.contains(playersMove))
        {
            playersMove = display.selectMove();
        }
        
        return playersMove;
    }
}
