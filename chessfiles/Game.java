import java.awt.*;
import java.util.*;

/**
 * Simulates Chess game
 * @author Melody Yin
 * @version 4-9-22
 */
public class Game
{
    static boolean gameOver = false;
    
    /**
     * Runs the game (initializes pieces + begins running the play() function)
     * @param args      default argument
     */
    public static void main(String[] args)
    {
        Board board = new Board();
        
        for (int i = 0; i < 8; i++)
        {
            Piece blackPawn0 = new Pawn(Color.BLACK, "black_pawn.gif");
            blackPawn0.putSelfInGrid(board, new Location(1, i));
        }
        
        for (int i = 0; i < 8; i++)
        {
            Piece whitePawn0 = new Pawn(Color.WHITE, "white_pawn.gif");
            whitePawn0.putSelfInGrid(board, new Location(6, i));
        }
        
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));
        
        Piece blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        Piece whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));
        
        Piece blackRook1 = new Rook(Color.BLACK, "black_rook.gif");
        blackRook1.putSelfInGrid(board, new Location(0, 0));
        Piece blackRook2 = new Rook(Color.BLACK, "black_rook.gif");
        blackRook2.putSelfInGrid(board, new Location(0, 7));
        Piece whiteRook1 = new Rook(Color.WHITE, "white_rook.gif");
        whiteRook1.putSelfInGrid(board, new Location(7, 0));
        Piece whiteRook2 = new Rook(Color.WHITE, "white_rook.gif");
        whiteRook2.putSelfInGrid(board, new Location(7, 7));
        
        Piece blackKnight1 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location (0, 1));
        Piece blackKnight2 = new Knight(Color.BLACK, "black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location (0, 6));
        
        Piece whiteKnight1 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location (7, 1));
        Piece whiteKnight2 = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location (7, 6));
        
        Piece blackBishop1 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location (0, 2));
        Piece blackBishop2 = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location (0, 5));
        
        Piece whiteBishop1 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop1.putSelfInGrid(board, new Location (7, 2));
        Piece whiteBishop2 = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location (7, 5));
        
        BoardDisplay display = new BoardDisplay(board);
        
        Player player1 = new HumanPlayer(board, "White", Color.WHITE, display);
        Player player2 = new SmarterPlayer(board, "Black", Color.BLACK);
        
        play(board, display, player1, player2);
        /*ArrayList<Move> possibledests = board.allMoves(Color.WHITE);
        for (Move dest : possibledests)
        {
            display.setColor(dest.getDestination(), Color.PINK);
        }*/
    }
    
    /**
     * Executes a player's move, updates the display, and then
     *      waits half a second
     * @param board             board used for the game
     * @param display           to be updated after the move
     * @param player            active player
     */
    private static void nextTurn(Board board, BoardDisplay display,
            Player player)
    {
        display.setTitle(player.getName());
        
        Move pmove = player.nextMove();
        
        if (pmove == null)
        {
            endGame();
        }
        else
        {
            board.executeMove(pmove);
            if (checked(board, player.getColor()))
            {
                System.out.println("checked");
            }
            display.clearColors();
            try { Thread.sleep(500) ; }
            catch ( InterruptedException e) 
            { System.out.println("InterruptedException"); }
        }
    }
    
    /**
     * Determines if a king of the opposite color of the 
     *      given color is checked
     * @param board             game board
     * @param col               current player's color
     * @return if the opponent's king is checked
     */
    private static boolean checked(Board board, Color col)
    {
        for (Move move : board.allMoves(col))
        {
            if (move.getVictim() instanceof King)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Repeatedly switches the active player and calls nextTurn function
     * @param board         board that the game is being played on
     * @param display       the display that needs to be updated
     * @param white         player 1
     * @param black         player 2
     */
    public static void play(Board board, BoardDisplay display, 
        Player white, Player black)
    {
        Player active = white;
        while (!gameOver)
        {
            nextTurn(board, display, active);
            
            if (active == white)
            {
                active = black;
            }
            else
            {
                active = white;
            }
        }
    }
    
    /**
     * used by other classes to recognize that the game is over
     */
    public static void endGame()
    {
        System.out.println("endGame");
        gameOver = true;
    }
}
