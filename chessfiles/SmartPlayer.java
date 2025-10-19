import java.util.*;
import java.awt.*;

/**
 * SmartPlayer class
 * @author Melody Yin
 * @version 4-9-22
 */
public class SmartPlayer extends Player
{
    /**
     * Instantiates SmartPlayer object
     * @param b             player's board
     * @param n             player's name
     * @param c             player's color
     */
    public SmartPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }
    
    /**
     * @return the player's next move
     */
    public Move nextMove()
    {
        ArrayList<Move> moves = (this.getBoard()).allMoves(this.getColor());
        
        if (moves.size() == 0)
        {
            Game.endGame();
            return null;
        }
        
        Move bestmove = moves.get(0);
        int maxscore = -5000000;
        int tempscore;
        for (Move move : moves)
        {
            this.getBoard().executeMove(move);
            tempscore = score();
            if (tempscore > maxscore)
            {
                maxscore = tempscore;
                bestmove = move;
            }
            this.getBoard().undoMove(move);
        }
        return bestmove;
    }
    
    /**
     * Sums up all piece values on the board that belong to itself and
     *      subtracts all the piece values of the opponent to evaluate
     *      how well it is doing in the game
     */
    private int score()
    {
        int oppscore = 0;
        int myscore = 0;
        ArrayList<Location> occlocs = this.getBoard().getOccupiedLocations();
        
        for (Location loc : occlocs)
        {
            Piece currpiece = (this.getBoard()).get(loc);
            if (currpiece.getColor() == this.getColor())
            {
                myscore += currpiece.getValue();
            }
            else
            {
                oppscore += currpiece.getValue();
            }
        }
        
        return myscore - oppscore;
    }
}
