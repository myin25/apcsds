
/**
 * Write a description of class mergesort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mergesort
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class mergesort
     */
    public mergesort()
    {
        int[] arr = {3, 0, 4, 6, 2};
        sort(arr, 0, 4);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void sort(int[] arr, int left, int right)
    {
        if (arr.length > 0)
        {
            int mid = (left + right)/2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, middle, right);
        }
    }
    
    public static void merge(int[] arr, int a, int m, int b)
    {
        int[] larray = new int[m - a];
        int[] rarray = new int[b - m];
        
        for (int i = a; i < m; i++)
        {
            larray[i - a] = arr[i];
        }
        
        for (int i = m + 1; i < b; i++)
        {
            rarray[i - m] = arr[
        }
    }
}
