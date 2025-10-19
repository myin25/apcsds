import java.util.LinkedList;
import java.util.Queue;

/**
 * Subclass of TreeDisplay that can convert a binary tree stored as an array
 *      into a heap. Includes the following methods:
 *          displayHeap(Comparable[] heapArray, int heapSize) - draws heap as
 *              a tree given the heap represented as an array
 *          heapArrayToTree(Comparable[] heapArray, int heapSize) - builds 
 *              a tree out of the heap, which is given as an array
 * @author Melody Yin
 * @author Anu Datar
 * @version 1/7/22
 */
public class HeapDisplay extends TreeDisplay
{
    
    /**
     * Intitializes HeapDisplay object by calling on superclass TreeDisplay
     * @postcondition creates a frame with a new TreeDisplay component.
     */
    public HeapDisplay()
    {
        super();
    }
    
    /**
     * given a heap expressed as an array (heapArray) and the size of the
     *      heap (heapSize), calls on helper method to construct a heap out 
     *      of the array and then displays the heap
     * @param heapArray - array representation of the heap
     * @param heapSize - current size of the heap
     * @postcondition frame now shows the heap as a tree
     */
    public void displayHeap(Comparable[] heapArray, int heapSize)
    {
        TreeNode root = heapArrayToTree(heapArray, heapSize);
        displayTree(root);
    }
    
    /**
     * converts a binary tree stored as an array into a TreeNode based tree
     * so that it can be displayed using the TreeDisplay
     * @param heapArray - the array representation of the tree
     * @param heapSize is the current size of the heap.  It starts at 
     *        location 1 in the heapArray and ends at location heapSize
     * @return a TreeNode representation of the tree
     */
    private static TreeNode heapArrayToTree(Comparable[] heapArray, 
            int heapSize)
    {
        // scan the array and build the tree using breadth first
        TreeNode root = new TreeNode(heapArray[1]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        // loop through all of the nonleaves in the array
        // for each nonleaf, create left and right nodes with corresponding
        //      values from the array
        for (int i = 1; i <= (heapSize / 2); i++)
        {
            TreeNode temp = queue.remove();
            int indexLeft = i * 2;
            int indexRight = i * 2 + 1;
            
            // add to the queue so that the children of left can be
            //      added too
            temp.setLeft(new TreeNode(heapArray[indexLeft]));
            queue.add(temp.getLeft());
            
            // check if at rightmost leaf node + repeat steps from left
            //      node initialization
            if (indexRight <= heapSize) 
            {
                temp.setRight(new TreeNode(heapArray[indexRight]));
                queue.add(temp.getRight());
            }
        }
        
        return root;
    }
}
