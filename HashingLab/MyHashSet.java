import java.util.*;
/**
* MyHashSet simulates a hash table with the functions:
*       toBucketIndex(Object obj)
*       size()
*       contains()
*       add(Object obj)
*       remove(Object obj)
*       toString()
* @author Anu Datar
* @author Melody Yin
* @version 1-31-22
* @param <E> is the type of object contained in MyHashSet
*/

public class MyHashSet<E>
{
    private static final int NUM_BUCKETS = 5;
    private LinkedList<E>[] buckets;
    private int size;

    /**
     * Constructor which initializes buckets to be size NUM_BUCKETS and size
     *      to be 0.
     * @postcondition buckets is initialized along with size
     */
    public MyHashSet()
    {
        buckets = new LinkedList[NUM_BUCKETS];
        size = 0;
        
        // initialize buckets
        for (int i = 0; i < NUM_BUCKETS; i++)
        {
            buckets[i] = new LinkedList();
        }
    }

    /**
     * @param obj is the obj we are looking for
     * @return the index of the bucket where obj might be found
     */
    private int toBucketIndex(Object obj)
    {
        return obj.hashCode() % NUM_BUCKETS;
    }

    /**
     * @return the number of items stored in the list
     */
    public int size()
    {
        return size;
    }

    /**
     * Determines if a hash table contains obj in one of its buckets by
     *      retrieving the object's hash code and checking in the bucket to
     *      find obj.
     * @param obj is the object we are looking for
     * @return true if the hash table contains obj
     *         false if the hash table does not contain obj
     */
    public boolean contains(Object obj)
    {
        int ind = toBucketIndex(obj);
        return buckets[ind].contains(obj);
    }

    /**
     * Adds object to hash table if it is not already present.
     * @param obj is the object we are looking for
     * @return true if the object was added
     *         false if it was already present in the hash table
     */
    public boolean add(E obj)
    {
        int ind = toBucketIndex(obj);
        if (contains(obj))
        {
            return false;
        }
        buckets[ind].add(obj);
        size += 1;
        return true;
    }

    /**
     * Removes object from hash table if it is present.
     * @param obj is the object we are looking for
     * @return true if the object was removed
     *         false if it wasn't in the hash table
     */
    public boolean remove(Object obj)
    {
        int ind = toBucketIndex(obj);
        if (!contains(obj))
        {
            return false;
        }
        buckets[ind].remove(obj);
        size -= 1;
        return true;
    }

    /**
     * Converts the hash table into string for printing
     * @return string representation of hash table
     */
    public String toString()
    {
        String s = "";
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i].size() > 0)
                s += i + ":" + buckets[i] + " ";
        return s;
    }
    
    /**
     * @return reference to new iterator
     */
    public Iterator iterator()
    {
        return new MyHashSetIterator();
    }
    
    /**
     * MyArrayListIterator class that implements Iterator interface and 
     *  allows user to iterate through list
     * @author Anu Datar
     * @author Melody Yin
     * @version 2-1-22
     */
    private class MyHashSetIterator implements Iterator
    {
        private int currBucket;
        private int indInBucket;
        private int totalInd;
        private Object next;
        
        /**
         * Sets default values
         */
        public MyHashSetIterator()
        {
            currBucket = 0;
            indInBucket = -1;
            totalInd = 0;
        }
        
        /**
         * @return false if end of list is reached
         *      otherwise, return true.
         */
        public boolean hasNext()
        {
            if (totalInd < size())
            {
                return true;
            }
            return false;
        }
        
        /**
         * returns next element - increments current bucket by 1 if reached
         *      end of current bucket's contents
         * @return next element in the set
         */
        public E next()
        {
            indInBucket += 1;
            while (indInBucket >= buckets[currBucket].size())
            {
                currBucket += 1;
                indInBucket = 0;
            }
            
            totalInd += 1;
            
            next = buckets[currBucket].get(indInBucket);
            
            return (E) next;
        }
        
        /**
         * remove current object
         */
        public void remove()
        {
            MyHashSet.this.remove(next);
            indInBucket -= 1;
            totalInd -= 1;
        }
    }
}