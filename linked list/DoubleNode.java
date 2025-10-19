/**
* DoubleNode class simulates the actions of a doubly linked node in a
*       linked list
* @author Melody Yin
* @version 10/26/21
*/
public class DoubleNode
{
    private Object value;
    private DoubleNode previous;
    private DoubleNode next;
        
    /**
     * creates value,previous,next for doublenode object 
     * @param v is the value of the node
     * @
     */
    public DoubleNode(Object v)
    {
        value = v;
        previous = null;
        next = null;
    }

    /**
     * @return value of the node
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * @return previous node
     */
    public DoubleNode getPrevious()
    {
        return previous;
    }

    /**
     * @return next node
     */
    public DoubleNode getNext()
    {
        return next;
    }

    /**
     * sets the value of the node to specified value
     * @param v is the value the node is to be set to
     */
    public void setValue(Object v)
    {
        value = v;
    }

    /**
     * sets previous node to specified node
     * @param p is the new previous node
     */
    public void setPrevious(DoubleNode p)
    {
        previous = p;
    }

    /**
     * sets next node to specified node
     * @param n is the new next node
     */
    public void setNext(DoubleNode n)
    {
        next = n;
    }
}