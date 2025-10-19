/**
* Moves the blocks around based on user input.
* @author Anu Datar
* @author Melody Yin
* @version 3-14-22
*/
public interface ArrowListener
{
    /**
     * Called when up arrow is pressed
     */
    void upPressed();
    
    /**
     * Called when down arrow is pressed
     */
    void downPressed();
    
    /**
     * Called when left arrow is pressed
     */
    void leftPressed();
    
    /**
     * Called when right arrow is pressed
     */
    void rightPressed();
    
    /**
     * Called when space bar is pressed
     */
    void spacePressed();
}