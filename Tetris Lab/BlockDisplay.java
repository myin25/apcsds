import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author Anu Datar
 * 
 * Changed block size and added a split panel display for next block and Score
 * 
 * @author Ryan Adolf
 * @version 1.0
 * 
 * Fixed the lag issue with block rendering 
 * Removed the JPanel
 */
// Used to display the contents of a game board
public class BlockDisplay extends JComponent implements KeyListener
{
    private static final Color BACKGROUND = Color.BLACK;
    private static final Color BORDER = Color.BLACK;

    private static final int OUTLINE = 2;
    private static final int BLOCKSIZE = 20;

    private MyBoundedGrid<Block> board;
    private JFrame frame;
    private ArrowListener listener;
    
    private boolean gameOver = false;
    private int score = 0;
    private int level = 0;

    /**
    * Constructs a new display for displaying the given board
    * @param board          the board being displayed
    */
    public BlockDisplay(MyBoundedGrid<Block> board)
    {
        this.board = board;

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });

        //Wait until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        //Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.addKeyListener(this);

        //Display the window.
        this.setPreferredSize(new Dimension(
                BLOCKSIZE * board.getNumCols() + 100,
                BLOCKSIZE * board.getNumRows()));

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Draws the game
     * @param g             draws the game
     */
    public void paintComponent(Graphics g)
    {
        if (!gameOver)
        {
            g.setColor(BACKGROUND);
            g.fillRect(0, 0, getWidth() + 150, getHeight() + 150);
            g.setColor(BORDER);
            g.fillRect(0, 0, BLOCKSIZE * board.getNumCols() + OUTLINE, 
                            BLOCKSIZE * board.getNumRows());
            for (int row = 0; row < board.getNumRows(); row++)
                for (int col = 0; col < board.getNumCols(); col++)
                {
                    Location loc = new Location(row, col);
    
                    Block square = board.get(loc);
    
                    if (square == null)
                        g.setColor(BACKGROUND);
                    else
                        g.setColor(square.getColor());
    
                    g.fillRect(col * BLOCKSIZE + OUTLINE / 2, 
                            row * BLOCKSIZE + OUTLINE / 2,
                        BLOCKSIZE - OUTLINE, BLOCKSIZE - OUTLINE);
                }
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier", Font.PLAIN , 24));
            g.drawString("SCORE", 200, 200);
            g.drawString("LEVEL", 200, 257);
            g.drawString(new Integer(score).toString(), 200, 225);
            g.drawString(new Integer(level).toString(), 200, 282);
        }
        else
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.RED);
            g.setFont(new Font("Courier", Font.PLAIN, 30));
            g.drawString("GAME OVER", 10, 100);
            g.drawString("SCORE:" + score, 10, 150);
        }

    }

    /**
     * Redraws the board to include the pieces and border colors.
     */
    public void showBlocks()
    {
        repaint();
    }

    /**
     * Sets the title of the window.
     * @param title             the new title of the window
     */
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    /**
     * Determines if a key is hit
     * @param e                     key is hit by user
     */
    public void keyTyped(KeyEvent e)
    {
    }

    /**
     * Determines if a key is released
     * @param e                     key is released by user
     */
    public void keyReleased(KeyEvent e)
    {
    }

    /**
     * If a key is pressed, determine what will happen
     * @param e                     key is pressed by user
     */
    public void keyPressed(KeyEvent e)
    {
        if (listener == null)
            return;
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            listener.leftPressed();
        else if (code == KeyEvent.VK_RIGHT)
            listener.rightPressed();
        else if (code == KeyEvent.VK_DOWN)
            listener.downPressed();
        else if (code == KeyEvent.VK_UP)
            listener.upPressed();
        else if (code == KeyEvent.VK_SPACE)
            listener.spacePressed();
    }

    /**
     * Sets arrow listener
     * @param listenr              new ArrowListener
     */
    public void setArrowListener(ArrowListener listenr)
    {
        this.listener = listenr;
    }
    
    /**
     * Changes game status to input
     * @param t                 if the game is over
     */
    public void isGameOver(boolean t)
    {
        gameOver = t;
    }
    
    /**
     * Sets score to input
     * @param score             new score
     */
    public void setScore(int score)
    {
        this.score = score;
    }
    
    /**
     * Sets level to input
     * @param level             new level
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
}
