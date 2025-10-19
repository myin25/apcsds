import java.util.*;
import java.awt.*;

/**
 * SmartestPlayer class (not functional yet)
 * @author Melody Yin
 * @version 4-9-22
 */
public class SmartestPlayer extends Player
{
    /**
     * Instantiates SmartestPlayer object
     * @param b         player's board
     * @param n         player's name
     * @param c         player's color
     */
    public SmartestPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }
    
    /**
     * Determines next move for the player
     * @return next move
     */
    public Move nextMove()
    {
        // for each move that this player can make, call nextmovehelper to
        // repeatedly determine the next optimal move that either this
        // player or the opponent can make
        ArrayList<Move> listofmoves = new ArrayList<Move>();
        Color currcol = swtch(this.getColor());
        Move currmove;
        
        ArrayList<Move> moves = (this.getBoard()).allMoves(this.getColor());
        if (moves.size() == 0)
        {
            return null;
        }
        
        Move bestmove = moves.get(0);
        int bestscore = 0;
        for (Move move : moves)
        {
            this.getBoard().executeMove(move);
            while (true)
            {
                currmove = nextMoveHelper(currcol);
                if (currmove == null)
                {
                    break;
                }
                moves.add(currmove);
                this.getBoard().executeMove(currmove);
                currcol = swtch(this.getColor());
            }
            
            if (score() > bestscore)
            {
                bestscore = score();
                bestmove = move;
            }
            
            // undo all the moves
            for (int i = listofmoves.size() - 1; i >= 0; i++)
            {
                this.getBoard().undoMove(listofmoves.get(i));
            }
        }
        
        return bestmove;
    }
    
    /**
     * @param col           the original color
     * @return the opposite of the color given
     */
    private Color swtch(Color col)
    {
        if (col == Color.BLACK)
        {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
    
    /**
     * Helper that repeatedly determines the optimal move that an opponent
     *      would make
     * @return opponent/self's next move
     */
    private Move nextMoveHelper(Color thiscol)
    {
        ArrayList<Move> moves = (this.getBoard()).allMoves(thiscol);
        
        if (moves.size() == 0)
        {
            return null;
        }
        
        Move bestmove = moves.get(1);
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
     * @return the value of the board (the higher the value,
     *      the better the position of the player)
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
