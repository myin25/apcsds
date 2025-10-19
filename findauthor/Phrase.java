import java.util.List;
import java.util.ArrayList;

/**
 * A collection of Token objects of type WORD, surrounded on each side by
 * some kind of punctuation.
 *
 * @author Melody
 * @version 18 May 2022
 */
public class Phrase
{
    /**
     * List used to store Tokens because each element is easily 
     * accessible. Also, the length of the phrase is initially unknown, so
     * using a flexible data structure like List means that all the 
     * Tokens can be stored correctly. The List is also easily
     * traversible, since a simple for loop can be implemented.
     */
    private List<Token> tokens;

    /**
     * Constructor for objects of class Phrase
     */
    public Phrase()
    {
        tokens = new ArrayList<Token>();
    }

    /**
     * Adds token to the phrase's list of tokens
     * Big O Performance: O(1)
     * @param token             the token to be added to the list
     */
    public void addToken(Token token)
    {
        tokens.add(token);
    }
    
    /**
     * @return a deep copy of the tokens (not just returning a reference,
     * duplicates each object contained in the list and returns)
     */
    public List getTokens()
    {
        List<Token> tokenscopy = new ArrayList<Token>();
        
        for (Token token : tokens)
        {
            tokenscopy.add(new Token(token.getType(), token.getValue()));
        }
        return tokenscopy;
    }
    
    /**
     * @return a string format of the Phrase
     */
    public String toString()
    {
        String str = "Phrase with the following words contained \n";
        for (Token token : tokens)
        {
            str += " \t";
            str += token.toString();
            str += "\n";
        }
        
        return str;
    }
}
