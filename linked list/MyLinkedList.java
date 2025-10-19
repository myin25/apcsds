import java.util.Iterator;
import java.util.ListIterator;

/**
 * simulates linked list with doubly linked nodes
 * @author Melody Yin
 * @param <E> specifies type of element
 * @version 10/26/21
 */
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;
    /**
     * initializes linkedlist values
     * @postcondition first, last, and size variables are initialized
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * converts linked list to a string for printing
     * @return string version of linked list
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /** 
    * gets node at specified index
    * @parameter index is the location of the element we are trying to retrieve
    * @precondition  0 <= index <= size / 2
    * @postcondition starting from first, returns the node
    *               with given index (where index 0
    *                 returns first)
    */               
    private DoubleNode getNodeFromFirst(int index)
    {
        if (index == 0)
        {
            return first;
        }
        
        DoubleNode node = first;
        for (int i = 0; i < index; i++)
        {
            node = node.getNext();
        }
        return node;
    }

    /** 
    * gets node at specified index
    * @parameter index is the location of the element we are trying to retrieve
    * @precondition  size / 2 <= index < size
    * @postcondition starting from last, returns the node
    *               with given index (where index size-1
    *               returns last)
    */               
    private DoubleNode getNodeFromLast(int index)
    {
        if (index == size - 1)
        {
            return last;
        }
        
        DoubleNode node = first;
        for (int i = size - 1; i > index; i++)
        {
            node = node.getPrevious();
        }
        return node;
    }

    /** 
    * gets node at specified index
    * @parameter index is the location of the element we are trying to retrieve
    * @precondition  0 <= index < size
    * @postcondition starting from first or last (whichever
    *               is closer), returns the node with given index
    */               
    private DoubleNode getNode(int index)
    {
        if (index >= size / 2 && index < size)
        {
            return getNodeFromLast(index);
        }
        else
        {
            return getNodeFromFirst(index);
        }
    }

    /**
     * returns size of linked list
     * @return size of linked list
     */
    public int size()
    {
        return size;
    }

    /**
     * gets element at specified index
     * @param index is the location of element we are trying to get
     * @return element at specified index
     */
    public E get(int index)
    {
        return (E) getNode(index).getValue();
    }

    /** 
    * changes element at specified position to specified value
    * @param index is specified position of change
    * @param obj is the value the element is being changed to
    * @postcondition replaces the element at position index with obj
                   returns the element formerly at the specified position
    * @return the previous value of the element
    */
    public E set(int index, E obj)
    {
        DoubleNode node = first;
        for (int i = 0; i < index; i++)
        {
            node = node.getNext();
        }

        E temp = (E) node.getValue();
        node.setValue(obj);
        return temp;
    }

    /**
    * adds object at the end of the list
    * @param obj is the value being appended to the end of the list
    * @postcondition appends obj to end of list
    * @return true
    */
    public boolean add(E obj)
    {
        DoubleNode node = first;
        for (int i = 0; i < size; i++)
        {
            node = node.getNext();
        }
        node.setValue(obj);
        size += 1;
        return true;
    }

    /** 
    * removes element at specified index
    * @param index is location that element is being removed from
    * @postcondition removes element from position index, moving elements
    *               at position index + 1 and higher to the left
    *               (subtracts 1 from their indices) and adjusts size
    * @return the element formerly at the specified position
    */
    public E remove(int index)
    {
        if (index == 0)
        {
            E temp = (E) first.getValue();
            first = first.getNext();
            size -= 1;
            return temp;
        }
        
        if (index >= size - 1)
        {
            E temp = (E) last.getValue();
            last = last.getPrevious();
            if (last != null)
            {
                last.setNext(null);
            }
            size -= 1;
            return temp;
        }
        
        DoubleNode node = getNode(index);
        
        E temp = (E) node.getValue();
        node = node.getPrevious();
        node.setNext(node.getNext().getNext());
        if (node.getNext() != null)
        {
            node.getNext().setPrevious(node);
        }
        node = node.getNext();
        
        if (node != null)
        {
            node.setPrevious(node.getPrevious().getPrevious());
        }
        
        size -= 1;
        return temp;
    }

    /** 
    * adds specified value at specified position
    * @param index specifies position that value insertion will occur
    * @param obj is element being added to list
    * @obj specifies value being inserted
    * @precondition  0 <= index <= size
    * @postcondition inserts obj at position index,
    *                moving elements at position index and higher
    *                to the right (adds 1 to their indices) and adjusts size
    */
    public void add(int index, E obj)
    {
        if (index == 0)
        {
            addFirst(obj);
        }
        else if (index >= size)
        {
            addLast(obj);
        }
        else
        {
            DoubleNode currnode = first; //keeps track of where you are
            for (int i = 0; i < index; i++) // get to node - 1
            {
                currnode = currnode.getNext();
            }
            DoubleNode addnode = new DoubleNode(obj);
            addnode.setPrevious(currnode.getPrevious());
            addnode.setNext(currnode);
            currnode.getPrevious().setNext(addnode);
            currnode.setPrevious(addnode);
            size += 1;
        }
    }

    /**
     * adds new element at the beginning of the list
     * @param obj specifies value being inserted
     * @postcondition new element at the beginning of the list; 
     *              everything else moves one index to the right
     */
    public void addFirst(E obj)
    {
        if (first == null)
        {
            first = new DoubleNode(obj);
            last = first;
        }
        else
        {
            DoubleNode node = new DoubleNode(obj);
            node.setNext(first);
            first.setPrevious(node);
            first = node;
        }
        size += 1;
    }
    
    /**
     * adds new element at the end of the list
     * @param obj is the object being inserted
     * @obj specifies value being inserted
     * @postcondition new element at the end of the list;
     */
    public void addLast(E obj)
    {
        if (last == null)
        {
            first = new DoubleNode(obj);
            last = first;
        }
        else
        {
            DoubleNode node = new DoubleNode(obj);
            node.setPrevious(last);
            last.setNext(node);
            last = node;
        }
        size += 1;
    }

    /**
     * @return the first element of the list
     */
    public E getFirst()
    {
        return (E) first.getValue();
    }

    /**
     * @return the last element of the list
     */
    public E getLast()
    {
        return (E) last.getValue();
    }

    /**
     * removes first element of the list
     * @precondition already has at least one element in the list to remove
     * @postcondition first element of the list is changed; 
     *          variable first is changed to null/next element
     * @return the previous first element of the list
     */
    public E removeFirst()
    {
        if (first == null)
        {
            return null;
        }
        
        E temp = (E) first.getValue();
        
        if (first.getNext() == null)
        {
            first = null;
        }
        else
        {
            first = first.getNext();
        }
        
        size -= 1;
        return temp;
    }

    /**
     * removes the last element of the list
     * @precondition already has at least one element in the list to remove
     * @postcondition last element of the list is changed; variable
     *                  last is changed to null/previous element
     * @return the previous last element of the list
     */
    public E removeLast()
    {
        if (last == null) //empty linked list
        {
            return null;
        }
        
        E temp = (E) last.getValue(); // store original last element
        
        // if the length of the linked list is 1 then make everything null
        if (last.getPrevious() == null)
        {
            last = null;
            first = null;
            return temp;
        }
        
        // else, change last to be the previous element
        last = last.getPrevious();
        last.setNext(null);
        
        size -= 1;
        
        return temp;
    }

    /**
     * initializes iterator
     * @return reference to newly initialized iterator
     */
    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    /**
     * replacement iterator class
     */
    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;
        
        /**
         * initializes default iterator values
         */
        public MyLinkedListIterator()
        {
            nextNode = first;
        }
        
        /**
         * @return true if not at the end of the list; return false otherwise
         */
        public boolean hasNext()
        {
            if (nextNode == null)
            {
                return false;
            }
            return true;
        }
        
        /**
         * adjusts nextNode to be next value in the linkedlist
         * @postcondition nextNode is changed to be next element in list
         * @return nextNode if not at end of list; throws exception if
         *                  at end of list
         */
        public E next()
        {
            if (hasNext()) //alr initialized nextnode to be first node
            {
                E temp = (E) nextNode.getValue();
                nextNode = nextNode.getNext();
                return temp;
            }
            throw new IndexOutOfBoundsException("Reached list end!");
        }

        /**
         * removes last returned node
         * @postcondition last returned node is removed from list
         */
        public void remove()
        {
            if (nextNode.getPrevious() == first)
            {
                removeFirst();
            }
            else if (nextNode == null)
            {
                removeLast();
            }
            else
            {
                nextNode.getPrevious().getPrevious().setNext(nextNode);
                nextNode.setPrevious(nextNode.getPrevious().getPrevious());
                size--;
            }
        }
    }
}