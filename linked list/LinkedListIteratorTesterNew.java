import java.util.ArrayList;
/**
 * Write a description of class LinkedListIteratorTesterNew here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LinkedListIteratorTesterNew
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class LinkedListIteratorTesterNew
     */
    public LinkedListIteratorTesterNew()
    {
    }
    
    public static void main(String[] args)
    {
        MyLinkedList<Integer> mylist = new MyLinkedList<Integer>();
        for (int i = 0; i < 5; i++)
        {
            mylist.addLast(i);
        }
        
        System.out.println(mylist);
        mylist.add(3, 69);
        System.out.println(mylist);
    }
}
