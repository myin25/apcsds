import java.util.*;

public class NewArrayListTester
{
    //determines whether it prints each operation
    private static final boolean DEBUG = true;

    public static void main(String[] args)
    {
        MyArrayList<Integer> your = new MyArrayList<Integer>();
        ArrayList<Integer> real = new ArrayList<Integer>();
        your.add(4);
        real.add(4);
        your.add(5);
        real.add(5);
        System.out.println("dees nuts");
        System.out.println(your.toString());
        System.out.println(real.toString());
        System.out.println(your.toString().equals(real.toString()));
        System.out.println(your.getCapacity());
        your.add(6);
        System.out.println(your.getCapacity());
    }


    private static void debug(String s)
    {
        if (DEBUG)
            System.out.println(s);
    }

    private static int random(int n)
    {
        return (int)(Math.random() * n);
    }
}