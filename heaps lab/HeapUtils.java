
/**
 * HeapUtils contains methods to manipulate a given heap. It is able to:
 *      heapify a given tree - change order of the elements to satisfy a heap's
 *          order property (min/max)
 *      
 *
 * @author Melody Yin
 * @version 1/7/22
 */
public class HeapUtils
{
    /**
     * Given a complete binary tree represented as an array, heapify converts
     *      the tree to satisfy a max heap property. Starting at the
     *      last nonleaf node, heapify 'bubbles up' the largest values to the
     *      top of the heap.
     *      
     * If the root of the tree is larger than both of its children, then the
     *      code terminates, having satisfied the heap order condition for
     *      the subtree rooted at index.
     * If one or more of the root's children is larger, then the larger of 
     *      the two children is swapped with the root and heapify is called 
     *      again, but on the subtree that the larger child was originally
     *      from.
     *      
     * Runtime: O log(n)
     * 
     * @param heap          the tree to be heapified, represented as an array
     * @param index         the index of the root of the tree that is going
     *                          to be heapified
     * @param heapSize      the size of the heap
     */
    public static void heapify(Comparable[] heap, int index, int heapSize)
    {
        int max = index;
        
        // if not at leaf node + left node > curr node
        if (index * 2 <= heapSize && heap[2 * index].compareTo(heap[max]) > 0)
        {
            max = 2 * index;
        }
        // if not at leaf node + right node > curr node
        if (index * 2 + 1 <= heapSize && heap[2 * index + 1].compareTo(heap[max]) > 0)
        {
            max = 2 * index + 1;
        }
        
        // if max is changed, swap + recurse down subtree
        if (max != index)
        {
            System.out.println("before");
            for (int i = 0; i < heapSize; i++)
            {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
            
            swap(heap, index, max);
            
            System.out.println("after");
            for (int i = 0; i < heapSize; i++)
            {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
            
            heapify(heap, max, heapSize);
        }
        
        for (int i = 0; i < heapSize; i++)
        {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
    
    private static void swap(Comparable[] heap, int index, int maxchild)
    {
        Comparable temp = heap[index];
        heap[index] = heap[maxchild];
        heap[maxchild] = temp;
    }
    
    /**
     * Given a complete binary tree represented as an array, buildHeap
     *      moves the elements in it to satisfy the max heap order
     *      property by iterating through each nonleaf node, starting
     *      from the last one, and calling heapify on it.
     *      
     * Runtime: O(n)
     *      
     * @param heap is a complete binary tree represented as an array
     * @param heapSize is the size of the heap
     */
    public static void buildHeap(Comparable[] heap, int heapSize)
    {
        // loop through all non leaf nodes
        for (int i = heapSize / 2; i > 0; i--)
        {
            System.out.println("at subtree " + i);
            heapify(heap, i, heapSize);
        }
    }
    
    /**
     * Insert copies the heap into an array with length (heap length + 1). It
     *      then adds a new value to the end of the array. In order to 
     *      satisfy the heap order property, it 'bubbles up' the new value to
     *      its proper position, where its children are lesser than it and
     *      its parent is larger.
     * 
     * Runtime: O log(n)
     * 
     * @param heap is the heap represented as an array
     * @param item is the new value to be added to the heap
     * @param heapSize is the size of the heap
     * @return the heap with item inserted at its correct place
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
    {
        // create new array
        Comparable[] newheap = new Comparable[heap.length + 1];
        
        // insert elements
        for (int i = 0; i <= heapSize; i++)
        {
            newheap[i] = heap[i];
        }
        
        // insert new element
        newheap[heapSize + 1] = item;
        
        // bubble up the element to its proper place
        int index = heapSize + 1;
        while (index > 1)
        {
            // if parent is smaller than child, switch
            if (newheap[index / 2].compareTo(newheap[index]) >= 0)
            {
                break;
            }
            
            swap(newheap, index / 2, index);
            index /= 2;
        }
        return newheap;
    }
    
    /**
     * Remove deletes the root of the heap. It does this by swapping the last
     *      leaf node with the root (to preserve the structure of the tree) 
     *      and decreasing heapSize by one. Heapify is then called on the new
     *      root of the tree, and the former root of the heap is returned.
     * 
     * Runtime: O log(n)
     * 
     * @param heap is the heap represented as an array
     * @param heapSize is the size of the heap
     * @return the former root value
     */
    public static Comparable remove(Comparable[] heap, int heapSize)
    {
        swap(heap, 1, heapSize);
        heapify(heap, 1, heapSize - 1);
        heapSize -= 1;
        return heap[heapSize];
    }
    
    /**
     * given a heap represented as an array, heapSort sorts it using the
     *      remove and buildHeap methods.
     * 
     * Runtime: O n log(n)
     * 
     * @param heap is a heap represented as an array
     * @param heapSize is the size of the heap
     * @postcondition heap is sorted in ascending order
     */
    public static void heapSort(Comparable[] heap, int heapSize)
    {
        buildHeap(heap, heapSize);
        for (int i = 1; i <= heapSize; i++)
        {
            remove(heap, heapSize - i + 1);
        }
    }
}
