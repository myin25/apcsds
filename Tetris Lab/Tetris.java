import java.io.File;
import javax.sound.sampled.*;
import java.net.URL;
import java.awt.Color;

/**
 * Simulates the Tetris game.
 * Implements the ArrowListener interface in order to 
 *
 * @author Melody Yin
 * @version 3-10-22
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad active;
    private int rowsCleared = 0;
    private int rowPoint;
    private int level;
    private int score;
    private int[] scoreAddOns = {0, 40, 100, 300, 1200};
    
    /**
     * Initializes instance variables for a MyBoundedGrid<Block> grid and a 
     *      BlockDisplay display, which keep track of the game and display it
     *      for the user. The grid defaults to 20 rows and 10 columns, and 
     *      the display initializes as a blank black screen with the window 
     *      title "Tetris".
     * It then begins the game by initializing the active tetrad to a random
     *      tetrad and calling on the play() function, which will run the
     *      rest of the game.
     * @postcondition Blank 10 x 20 Tetris board is created.
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setArrowListener(this);
        
        display.setTitle("Tetris");
        display.showBlocks();
        
        rowPoint = 0;
        level = 0;
        score = 0;
        
        active = new Tetrad(grid);
        play();
    }
    
    /**
     * Whenever the window detects that an up arrow key has been pressed, it 
     *      calls on this function in order to rotate the currently active 
     *      tetrad 90 degrees.
     * @precondition The active tetrad has already been initialized.
     * @postcondition The active tetrad has been rotated 90 degrees.
     */
    public void upPressed()
    {
        active.rotate();
        display.showBlocks();
    }
    
    /**
     * Whenever the window detects that a right arrow key has been pressed,
     *      it calls on this function in order to move the currently active
     *      tetrad one block to the right. It does this by calling on the
     *      translate(numRow, numCol) function, which moves each block in
     *      the currently active tetrad one to the right.
     * @precondition The active tetrad has already been initialized.
     * @postcondition The active tetrad has been translated one column to the
     *      right.
     */
    public void rightPressed()
    {
        active.translate(0, 1);
        display.showBlocks();
    }
    
    /**
     * Whenever the window detects that a down arrow key has been pressed,
     *      it calls on this function in order to move the currently active
     *      tetrad one block down. It does this by calling on the
     *      translate(numRow, numCol) function, which moves each block in
     *      the currently active tetrad one down.
     * @precondition The active tetrad has already been initialized.
     * @postcondition The active tetrad has been translated one row down.
     */
    public void downPressed()
    {
        active.translate(1, 0);
        display.showBlocks();
    }
    
    /**
     * Whenever the window detects that a left arrow key has been pressed,
     *      it calls on this function in order to move the currently active
     *      tetrad one block to the left. It does this by calling on the
     *      translate(numRow, numCol) function, which moves each block in
     *      the currently active tetrad one to the left.
     * @precondition The active tetrad has already been initialized.
     * @postcondition The active tetrad has been translated one column to the
     *      left.
     */
    public void leftPressed()
    {
        active.translate(0, -1);
        display.showBlocks();
    }
    
    /**
     * Whenever the window detects that a space key has been pressed,
     *      it calls on this function in order to repatedly move the 
     *      currently active tetrad one block down until it's not possible
     *      to anymore. It does this by calling on the translate(numRow, 
     *      numCol) function, which moves each block in the currently active
     *      tetrad one down.
     * @precondition The active tetrad has already been initialized.
     * @postcondition The active tetrad has been translated as far down as
     *      possible.
     */
    public void spacePressed()
    {
        boolean temp = true;
        
        while (temp)
        {
            temp = active.translate(1, 0);
        }
        
        display.showBlocks();
    }
    
    /**
     * Simulates the Tetris game. Called on by the Tetris constructor. While
     *      true, repeatedly pauses for 1 second, translates the active 
     *      tetrad down one row, and redraws the display. When the active
     *      tetrad has reached the bottom of the screen, it generates a new
     *      random tetrad and makes that the active tetrad.
     */
    public void play()
    {
        try
        {
            File f = new File("./Tetris-Theme-Tetris-Soundtrack.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            while (!active.isGameOver())
            {
                try
                {
                    Thread.sleep(1000 - level * 100);
                    
                    while (active.translate(1, 0))
                    {
                        display.showBlocks();
                        Thread.sleep(1000 - level * 100);
                    }
                    
                    clearCompletedRows();
                    
                    active = new Tetrad(grid);
                    
                }
                catch (InterruptedException e)
                {
                    // ignore exception
                }
                display.showBlocks();
            }
            
            clip.stop();
            stream.close();
            display.setTitle("Game Over");
            display.isGameOver(true);
        }
        catch (Exception e)
        {
            System.out.println("error!");
        }
    }
    
    /**
     * Clears any row that the user has completed. Called after each active
     *      tetrad has finished falling by the play() method. It does this
     *      by determining for each row if it's completed. If it is, it
     *      calls on a helper method to remove the row.
     * @postcondition All completed rows on the screen are removed and the
     *      rest of the blocks are moved down one.
     */
    public void clearCompletedRows()
    {
        int row = grid.getNumRows() - 1;
        rowPoint = 0;
        
        while (row >= 0)
        {
            if (isComplete(row))
            {
                clearRow(row);
            }
            else
            {
                row -= 1;
            }
        }
        calculateScore();
    }
    
    /**
     * Determines if a row is complete (completely filled with blocks).
     * @param row                       the row that we are checking for 
     *                                      completion
     * @return true if the row is complete, false otherwise.
     */
    private boolean isComplete(int row)
    {
        for (int col = 0; col < grid.getNumCols(); col++)
        {
            if (grid.get(new Location(row, col)) == null)
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Clears the given row and moves the rest of the blocks above the row
     *      down one row.
     * @parameter row               the row that is being removed from the 
     *                                  tetris grid
     * @postcondition The row formerly at specified row has now been removed.
     */
    private void clearRow(int row)
    {
        for (int col = 0; col < grid.getNumCols(); col++)
        {
            grid.get(new Location(row, col)).removeSelfFromGrid();
        }
        
        for (int r = row - 1; r >= 0; r--)
        {
            for (int c = 0; c < grid.getNumCols(); c++)
            {
                Location loc = new Location(r, c);
                if (grid.get(loc) != null)
                {
                    grid.get(loc).moveTo(new Location(r + 1, c));
                }
            }
        }
        
        rowPoint++;
        rowsCleared++;
        
        if (rowsCleared % 10 == 0 && level < 10)
        {
            level++;
            display.setLevel(level);
        }
        
    }
    
    /**
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver()
    {
        return active.isGameOver();
    }
    
    /**
     * Calculates player score (depends on how many rows cleared at once)
     */
    private void calculateScore()
    {
        int temp = rowPoint;
        
        score += scoreAddOns[temp] * (level + 1);
        display.setScore(score);
        this.rowPoint = 0;
    }
}
