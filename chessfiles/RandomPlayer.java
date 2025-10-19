import java.util.*;
import java.awt.*;

/**
 * Represents RandomPlayer - plays by selecting a random move
 * @author Melody Yin
 * @version 4-9-22
 */
public class RandomPlayer extends Player
{
    /**
     * Creates new RandomPlayer object
     * @param b             player's board
     * @param n             player's name
     * @param c             player's color
     */
    public RandomPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }
    
    /**
     * Randomly picks a move and returns it
     * @return next move
     */
    public Move nextMove()
    {
        ArrayList<Move> moves = (this.getBoard()).allMoves(this.getColor());
        
        if (moves.size() == 0)
        {
            return null;
        }
        
        int randint = (int) (Math.random() * (double) moves.size());
        return moves.get(randint);
    }
}
