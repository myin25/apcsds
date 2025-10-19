/** a collection of static methods for operating on binary search trees
 * @author Anu Datar
 * @author Melody Yin
 * @version 12-11-21
 */
public abstract class BSTUtilities
{
    /**
     * @param t is a binary search tree in ascending order
     * @param x is the value being looked for in t
     * @param display shows the program's progress
     * @return true if t contains the value x;
     *         otherwise, returns false
    */
    public static boolean contains(TreeNode t, Comparable x, 
                                    TreeDisplay display)
    {
        // break condition - reached end of tree w/o finding x
        if (t == null)
        {
            return false;
        }
        
        // if found, return true. otherwise, return false or recursion results
        if (x.compareTo(t.getValue()) == 0)
        {
            return true;
        }
        else
        {
            display.visit(t);
            return false || contains(t.getLeft(), x, display) ||
                contains(t.getRight(), x, display);
        }
    }

    /**
     * @param t is a binary search tree in ascending order
     * @param x is the value to be inserted into t
     * @param display shows progress of program
     * @return a new tree containing x if empty;
     *    otherwise, returns t, with x having been inserted
     *    at the appropriate position to maintain the binary
     *    search tree property; x is ignored if it is a
     *    duplicate of an element already in t; only one new
     *    TreeNode is created in the course of the traversal
    */
    public static TreeNode insert(TreeNode t, Comparable x, 
                                    TreeDisplay display)
    {
        // check if t is null
        if (t != null)
        {
        
            display.visit(t);
            
            // otherwise, check to see if x is less --> 
            //      if less, check if left of current is null
            if (x.compareTo(t.getValue()) < 0)
            {
                if (t.getLeft() == null)
                {
                    t.setLeft(new TreeNode(x));
                }
                
                else
                {
                    insert(t.getLeft(), x, display);
                }
                
                return t;
            }
            // if the values are equal, don't do anything
            else if (x.compareTo(t.getValue()) == 0)
            {
                return t;
            }
            
            // if x is more, go to the right
            else
            {
                if (t.getRight() == null)
                {
                    t.setRight(new TreeNode(x));
                }
                
                else
                {
                    insert(t.getRight(), x, display);
                }
                
                return t;
            }
        }
        
        // if reached the leaf node
        return new TreeNode(x);
    }

    /**
     * precondition:  t is a binary search tree in ascending order
     * postcondition: returns a pointer to a binary search tree,
     * in which the value at node t has been deleted
     * (and no new TreeNodes have been created)
    */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        // tree is empty
        if (t == null)
        {
            return null;
        }
        
        // leaf node --> return null
        if (t.getLeft() == null && t.getRight() == null)
        {
            return null;
        }
        // only left child
        if (t.getRight() == null)
        {
            return t.getLeft();
        }
        // only right child
        if (t.getLeft() == null)
        {
            return t.getRight();
        }
        
        // 2 children --> max of leftmost
        TreeNode maxLeft = rightmost(t.getLeft());
        Object maxValue = maxLeft.getValue();
        
        // moving maxLeft's value to current node
        t.setValue(maxValue);
        
        // remove value from left subtree via recursion
        t.setLeft(delete(t.getLeft(), (Comparable) maxValue, display));
        return t;
    }
    
    /**
     * @param t is the root of the tree
     * @return the rightmost node in a tree
     */
    private static TreeNode rightmost(TreeNode t)
    {
        // implement this recursively
        if (t == null) // break condition
        {
            return null;
        }
        else if (t.getRight() == null) // break condition: rightmost found
        {
            return t;
        }
        // continue to recurse
        return rightmost(t.getRight());
    }

    /**
     * @param t is a binary search tree in ascending order
     * @param x is the value we are deleting from t
     * @param display shows the progress of the program
     * @return a pointer to a binary search tree,
     *          in which the value x has been deleted (if present)
     *          (and no new TreeNodes have been created)
    */
    public static TreeNode delete(TreeNode t, Comparable x, 
                                    TreeDisplay display)
    {
        // tree is empty
        if (t == null)
        {
            return null;
        }
        
        display.visit(t);
        // less than current node --> recurse in left
        if (x.compareTo(t.getValue()) < 0) 
        {
            t.setLeft(delete(t.getLeft(), x, display));
        }
        
        // current node is what we're looking for
        else if (x.compareTo(t.getValue()) == 0) 
        {
            // find max of rightmost and set current node to max of rightmost
            // preserve left and right
            TreeNode temp = deleteNode(t, display);
            if (temp != null)
            {
                t.setValue(temp.getValue());
                t.setLeft(temp.getLeft());
                t.setRight(temp.getRight());
            }
            else
            {
                t = null;
            }
        }
            
        else // too large --> go to the right subtree
        {
            t.setRight(delete(t.getRight(), x, display));
        }
        
        return t;
    }
}