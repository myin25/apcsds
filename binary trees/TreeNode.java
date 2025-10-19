/**
 * TreeNode class that imitates node in binary tree; will have a value and left/right node
 * @author Melody Yin
 * @version 12-6-21
 */
public class TreeNode
{
    private Object value;
    private TreeNode left;
    private TreeNode right;

    /**
     * constructs object with given value and null for left/right nodes
     * @param initValue is the value of the node
     */
    public TreeNode(Object initValue)
    { 
        this(initValue, null, null);
    }

    /**
     * constructs object with given value along with given left and right nodes
     * @param initValue is the node's value
     * @param initLeft is a reference to the left node
     * @param initRight is a reference to the right node
     */
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    { 
        value = initValue; 
        left = initLeft; 
        right = initRight; 
    }

    /**
     * @return value of the node
     */
    public Object getValue() { return value; }
    
    /**
     * @return reference to left node
     */
    public TreeNode getLeft() { return left; }
    
    /**
     * @return reference to right node
     */
    public TreeNode getRight() { return right; }

    /**
     * set value of object to input
     * @param theNewValue is the new value of the node
     */
    public void setValue(Object theNewValue) { value = theNewValue; }
    
    /**
     * set left node to input
     * @param theNewLeft is the new left node
     */
    public void setLeft(TreeNode theNewLeft) { left = theNewLeft; }
    
    /**
     * set right node to input
     * @param theNewRight is the new right node
     */
    public void setRight(TreeNode theNewRight) { right = theNewRight; }
}