import java.util.Iterator;
import java.util.ListIterator;
import java.util.ConcurrentModificationException;

/**
 * MyArrayList class that has methods to modify/access/create an ArrayList
 * @param <E>
 * @author Anu Datar
 * @author Melody Yin
 * @version 26/10/21
 */
public class MyArrayList<E>
{
    private int size;
    private Object[] values;  //(Java doesn't let us make an array of type E)
    private boolean modified;

    /**
     * constructor that initializes size and list
     * @postcondition integer size and integer array values are intialized
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    /**
     * converts array to string for printing
     * @return array converted into string
     */
    public String toString()
    {
        if (size == 0)
        {
            return "[]";
        }

        String s = "[";
        for (int i = 0; i < size - 1; i++)
        {
            s += values[i] + ", ";
        }
        return s + values[size - 1] + "]";
    }

    /**
     * doubles capacity of array if the current one is too short to store all
     *      of the elements entered by the user
     * @postcondition replaces the array with one that is twice as long and
     * copies all of the old elements into it
     */
    private void doubleCapacity()
    {
        if (size == 0)
        {
            values = new Object[1];
        }
        else
        {
            Object[] temp = new Object[getCapacity() * 2];
            for (int i = 0; i < size; i++)
            {
                temp[i] = values[i];
            }
            values = temp;
        }
    }

    /**
     * returns the length of the array
     * @return the length of the array
     */
    public int getCapacity()
    {
        return values.length;
    }

    /**
     * returns number of elements in the list entered by the user
     * @return list size
     */
    public int size()
    {
        return size;
    }

    /**
     * returns value in list at specified index
     * @param index is the location of the element user is trying to retrieve
     * @return value in list at specified index
     */
    public E get(int index)
    {
        if (index > size)
        {
            throw new RuntimeException("NoSuchElement");
        }
        return (E)values[index];

        //(You will need to promise the return value is of type E.)
    }

    /**
     * replaces the element at position index with obj
     * @param index is the location of the element to be changed
     * @param obj is the value that the element is going to be replaced with
     * @return element formerly at the specified position
     * @postcondition element at specified index is replaced with specified
     *          value
     */
    public E set(int index, E obj)
    {
        Object temp = values[index];
        values[index] = obj;
        modified = true;
        return (E) temp;

        //(You will need to promise the return value is of type E.)
    }

    /**
     * appends obj to end of list
     * @param obj is added to the end of the list
     * @return true
     * @postcondition appends obj to end of list
     */
    public boolean add(E obj)
    {
        /* if values is already full, call doubleCapacity before adding */

        if (getCapacity() <= size)
        {
            doubleCapacity();
        }
        values[size] = obj;
        size += 1;
        modified = true;
        return true;
    }

    /**
     * removes element from position index, moving elements
     *      at position index + 1 and higher to the left
     *      (subtracts 1 from their indices) and adjusts size
     *      returns the element formerly at the specified position
     * @param index specifies location of element to be removed
     * @return the element that was removed from the list
     * @precondition
     * @postcondition removes element from specified position in the list
     */
    public E remove(int index)
    {
        Object temp = values[index];
        size = size - 1;
        for (int i = index; i < size; i++)
        {
            values[i] = values[i + 1];
        }
        values[size] = null;
        modified = true;
        return (E)temp;

        //(You will need to promise the return value is of type E.)
    }

    /**
     * instantiates new iterator
     * @return new iterator
     * @postcondition instantiates new iterator
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }

    /**
     * instantiates new iterator
     * @return new iterator
     * @postcondition instantiates new iterator
     */
    public ListIterator<E> listIterator()
    {
        return new MyArrayListListIterator();
    }

    /**
     * inserts object at position index
     * @param index specifies where to insert new value
     * @param obj is the new value being added
     * @return
     * @precondition  0 <= index <= size
     * @postcondition inserts obj at position index,
     *      moving elements at position index and higher
     *      to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        for (int i = size - 1; i >= index; i--)
        {
            values[i + 1] = values[i];
        }
        values[index] = obj;
        size = size + 1;
    }

    /**
     * MyArrayListIterator class that implements Iterator interface and allows
     *  user to iterate through list
     * @author Anu Datar
     * @author Melody Yin
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        private int nextIndex;

        /**
         * initializes list iterator object
         * @postcondition initializes nextIndex to 0
         */
        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

        /**
         * determines if there is at least one more element until the end of
         *      the list
         * @return false if end of list is reached; otherwise, return true
         */
        public boolean hasNext()
        {
            if (nextIndex + 1 < size)
            {
                return true;
            }
            return false;
        }

        /**
         * returns next element in list
         * @return next element in list
         * @postcondition increments nextIndex by 1
         */
        public E next()
        {
            nextIndex = nextIndex + 1;
            return (E) values[nextIndex - 1];

            //(You will need to promise the return value is of type E.)
        }

        /**
         * removes the last element that was returned by next
         * @postcondition last element returned by next is removed
         */
        public void remove()
        {
            for (int i = nextIndex; i < size; i++)
            {
                values[i] = values[i + 1];
            }
            size -= 1;
        }

    }
    
    /**
     * arraylistlistiterator class that acts as iterator for arraylist
     *      instead of the normal iterator
     * 
     */
    private class MyArrayListListIterator extends MyArrayListIterator
        implements ListIterator<E>
    {
        // note the extends MyArrayListIterator 
        // Remember this class thus inherits the methods from the parent class.

        private int nextIndex;
        private int previousIndex;
        private boolean forward; //direction of traversal

        /**
         * Constructs a new MyArrayListListIterator
         */
        public MyArrayListListIterator()
        {
            nextIndex = 0;
            previousIndex = -1;
            forward = true;
        }
        
        
        /**
         * decides if reached the end of the list
         * @return true if not at the end of the list and false if at end
         */
        public boolean hasNext()
        {
            if (nextIndex + 1 < size)
            {
                return true;
            }
            return false;
        }

        /**
         * Returns next element and moves pointer forward
         * @return next Object in MyArryaList
         */
        public E next()
        {
            forward = true;
            nextIndex += 1;
            previousIndex += 1;
            return (E) values[nextIndex - 1];
        }

        /**
         * Adds element before element that would be returned by next
         * @param obj is the element we are supposed to add
         */
        public void add(E obj)
        {
            for (int i = size - 1; i >= nextIndex; i--)
            {
                values[i + 1] = values[i];
            }
            values[nextIndex] = obj;
            size = size + 1;
        }

        /**
         * Determines whether there is another element in MyArrayList
         * while traversing in the backward direction
         * @return true if there is another element, false otherwise
         */
        public boolean hasPrevious()
        {
            if (previousIndex == -1)
            {
                return false;
            }
            return true;
        }

        /**
         * Returns the previous element
         */
        public E previous()
        {
            if (!hasPrevious())
            {
                return null;
            }
            forward = false;
            nextIndex -= 1;
            previousIndex -= 1;
            return (E) values[nextIndex - 1];
        }

        /**
         * Returns index of the next element 
         * @return index of element that would be 
         *         returned by a call to next()
         */
        public int nextIndex()
        {
            return nextIndex;
        }

        /**
         * Returns index of the previous element
         * @return index of element that would be returned by a call to
         *          previous()
         */
        public int previousIndex()
        {
            return previousIndex;
        }

        /**
         * Removes element that was returned by next() pr previous()
         * USE direction FOR THIS
         */
        public void remove()
        {
            int temp = 0;
            if (forward == true)
            {
                temp = nextIndex;
            }
            else
            {
                temp = previousIndex;
            }
            for (int i = temp; i < size; i++)
            {
                values[i] = values[i + 1];
            }
            size -= 1;
        }

        /**
         * sets value before/after to the value given
         * @param obj is the value we're setting before/after tot
         * @postcondition value before/after is set to specified value
         */
        public void set(E obj)
        {
            int index;
            if (forward == true)
            {
                index = nextIndex;
            }
            else
            {
                index = previousIndex;
            }
            Object temp = values[index];
            values[index] = obj;
        }
    }
}