import java.util.*;
import java.awt.*;

/**
 * SmarterPlayer class - looks 2 steps ahead in a game
 * @author Melody Yin
 * @version 4-9-22
 */
public class SmarterPlayer extends Player
{
    /**
     * Instantiates SmartPlayer object
     * @param b             player's board
     * @param n             player's name
     * @param c             player's color
     */
    public SmarterPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }
    
    /**
     * @return the player's next move
     */
    public Move nextMove()
    {
        // for each move that this player can make, call nextmovehelper to
        // determine what optimal move that the opponent would make
        ArrayList<Move> moves = (this.getBoard()).allMoves(this.getColor());
        
        // if no moves are possible, then game is over
        if (moves.size() == 0)
        {
            return null;
        }
        
        Move bestmove = moves.get(1);
        int maxscore = 0;
        int tempscore;
        for (Move move1 : moves)
        {
            // find the opponent's move to counter the first move and then
            // find your optimal move to counter that
            this.getBoard().executeMove(move1);
            Move opptimalmove;
            if (this.getColor() == Color.BLACK)
            {
                opptimalmove = nextMoveHelper(Color.WHITE);
            }
            else
            {
                opptimalmove = nextMoveHelper(Color.BLACK);
            }
            
            // if optimalmove is null, then the opponent has no more moves
            if (opptimalmove == null)
            {
                return nextMoveHelper(this.getColor());
            }
            
            // otherwise, execute the opponent's optimal move and then find
            // your counter + execute it
            this.getBoard().executeMove(opptimalmove);
            Move myoptimalmove = nextMoveHelper(this.getColor());
            this.getBoard().executeMove(myoptimalmove);
            tempscore = score();
            if (tempscore > maxscore)
            {
                maxscore = tempscore;
                bestmove = move1;
            }
            
            // undo optimal move then the other player's move then move1
            this.getBoard().undoMove(myoptimalmove);
            this.getBoard().undoMove(opptimalmove);
            this.getBoard().undoMove(move1);
        }
        return bestmove;
    }
    
    /**
     * Helps to determine the optimal move the opponent/self would make
     * @param thiscol               color of the active player
     * @return the player's optimal move (which player is determined by
     *      thiscol)
     */
    private Move nextMoveHelper(Color thiscol)
    {
        ArrayList<Move> moves = (this.getBoard()).allMoves(thiscol);
        
        if (moves.size() == 0)
        {
            return null;
        }
        
        Move bestmove = moves.get(0);
        int maxscore = 0;
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
     * Sums up piece values on the board of self and subtracts
     *      opponent's piece values
     * @return the value of the board
     */
    private int score()
    {
        int oppscore = 0;
        int myscore = 0;
        ArrayList<Location> occloc = this.getBoard().getOccupiedLocations();
        
        for (Location loc : occloc)
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
