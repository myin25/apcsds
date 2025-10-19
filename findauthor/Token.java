
/**
 * Represents an input Token of a certain type and a specified value.
 *
 * @author Anu Datar
 * @author Melody Yin
 * @version 16 May, 2022
 */
public class Token
{
    private Scanner.TOKEN_TYPE type;
    private String value;

    /**
     * Constructor for objects of class Token
     * @param ttype             token type
     * @param str               value of the token
     */
    public Token(Scanner.TOKEN_TYPE ttype, String str)
    {
        type = ttype;
        value = str;
    }

    /**
     * @return the type of the token
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }
    
    /**
     * @return the value of the token
     */
    public String getValue()
    {
        return value;
    }
    
    /**
     * @return the string version of the token
     */
    public String toString()
    {
        return ("Token of type " + type + " with value " + value);
    }
    
    /**
     * @param other                 the token that this is being compared to
     * @return true if other is equal to this in terms of stored value and
     * type, false otherwise.
     */
    public boolean equals(Token other)
    {
        if (this.getValue().equals(other.getValue()) && 
            this.getType().equals(other.getValue()))
        {
            return true;
        }
        return false;
    }
}
