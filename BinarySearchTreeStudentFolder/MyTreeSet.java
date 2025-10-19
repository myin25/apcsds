/**
 * implementation of a tree set which has the following methods
 *      size - returns the size of the treeset
 *      contains - returns true/false depending on if an element is in the set
 *      add - add an object to the tree set
 *      remove - removes element from set
 *      toString - converts tree to string
 * @author Melody Yin
 * @author Anu Datar
 * @param <E> is the element type in the set
 * @version 1-14-22
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * initializes new treeset object
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        display.setDelay(1);
    }

    /**
     * @return size of the tree
     */
    public int size()
    {
        return size;
    }

    /**
     * uses BSTUtilities's contains() to determine if an element appears in
     *      a tree. BSTUtilities's contains() uses recursion to look in the
     *      left and right subtrees of the current node until a leaf node is
     *      reached and the object is not found or the object is found.
     * @param obj is the object we are looking for in the tree
     * @return true if tree contains object, false otherwise
     */
    public boolean contains(Object obj)
    {
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /**
     * uses contains to see if obj is present in set. if obj is not present 
     *      in set, adds obj and returns true; otherwise returns false.
     *      increases size by 1.
     * @param obj is the object to be added
     * @return true if object is not in set, false otherwise
    */
    public boolean add(E obj)
    {
        if (!contains(obj))
        {
            root = BSTUtilities.insert(root, (Comparable) obj, display);
            size += 1;
            return true;
        }
        return false;
    }

    /**
     * if obj is present in set, removes obj and returns true; otherwise
     *      returns false. decreases size by 1.
     * @param obj is the object to be removed
     * @return true if object is in set, false otherwise
     */
    public boolean remove(Object obj)
    {
        if (contains(obj))
        {
            root = BSTUtilities.delete(root, (Comparable) obj, display);
            size -= 1;
            return true;
        }
        return false;
    }

    /**
     * converts tree to string using helper method
     * @return tree converted to string
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * recurses down the tree and returns the left subtree's root value, root
     *      value, and right subtree's root value
     * @param t node of tree to be converted to string
     * @return tree printed in inorder format
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
    }
}