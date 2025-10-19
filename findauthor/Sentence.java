import java.util.List;
import java.util.ArrayList;

/**
 * A collection of Phrase objects, surrounded on each side by
 * some kind of punctuation.
 *
 * @author Melody
 * @version 18 May 2022
 */
public class Sentence
{
    /**
     * List used to store Phrases because each element is easily 
     * accessible. Also, the length of the phrase is initially unknown, so
     * using a flexible data structure like List means that all the 
     * Phrases can be stored correctly. The List is also easily
     * traversible, since a simple for loop can be implemented.
     */
    private List<Phrase> phrases;

    /**
     * Constructor for objects of class Phrase
     */
    public Sentence()
    {
        phrases = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to the list of phrases in this sentence
     * Big O Performance: O(1)
     * @param phrase            phrase to be added
     */
    public void addPhrase(Phrase phrase)
    {
        phrases.add(phrase);
    }
    
    /**
     * @return a deep copy of the sentence (not just a reference, returns an
     * exact copy of the sentence)
     */
    public List getPhrases()
    {
        List<Phrase> phrasescopy = new ArrayList<Phrase>();
        List<Token> tokens;
        for (Phrase phrase : phrases)
        {
            tokens = phrase.getTokens();
            Phrase tphrase = new Phrase();
            for (Token token : tokens)
            {
                tphrase.addToken(token);
            }
            
            phrasescopy.add(tphrase);
        }
        return phrasescopy;
    }
    
    /**
     * @return a string representation of the Sentence
     */
    public String toString()
    {
        String str = "Sentence with the following phrases contained \n";
        for (Phrase phrase : phrases)
        {
            str += "  ";
            str += phrase.toString();
            str += "\n";
        }
        
        return str;
    }
}
