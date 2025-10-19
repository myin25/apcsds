
/**
 * Tests HeapUtil classes
 *
 * @author Melody Yin
 * @version 1/7/21
 */
public class Main
{
    /**
     * Creates Integer array of length 12 and initializes random values for
     *      indices 1-12 to test methods for HeapUtils class
     * @param args 
     */
    public static void main(String[] args)
    {
        Integer[] array = new Integer[12];
        for (int i = 1; i < array.length; i++)
        {
            array[i] = (int)(Math.random() * 99 + 1);
        }
        HeapDisplay hd = new HeapDisplay();
        hd.displayHeap(array, 11);
        
        // test building a heap
        HeapUtils hu = new HeapUtils();
        /*hu.buildHeap(array, 11);
        hd.displayHeap(array, 11);
        
        // test removal
        hu.remove(array,11);
        for (int i = 0; i <= 11; i++)
        {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        hd.displayHeap(array, 10);
        
        // test insertion
        Integer temp = 53;
        hu.insert(array, temp, 11);
        hd.displayHeap(array, 10);*/
        
        // test heapsort
        hu.heapSort(array, 11);
        hd.displayHeap(array, 11);
        for (int i = 1; i <= 11; i++)
        {
            System.out.print(array[i] + " ");
        }
    }
}
