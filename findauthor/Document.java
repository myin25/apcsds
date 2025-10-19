import java.util.List;
import java.util.ArrayList;

/**
 * Directs scanning and parsing of a document
 *
 * @author Melody Yin
 * @version 18 May 2022
 */
public class Document
{
    private Scanner scanner;
    /**
     * List used to store Sentences because each element is easily 
     * accessible. Also, the length of the phrase is initially unknown, so
     * using a flexible data structure like List means that all the 
     * Sentences can be stored correctly. The List is also easily
     * traversible, since a simple for loop can be implemented.
     */
    private List<Sentence> sentences;
    private Token currentToken;
    
    /**
     * Constructor for objects of class Document
     * @param tscan             the document's scanner
     */
    public Document(Scanner tscan)
    {
        scanner = tscan;
        sentences = new ArrayList<Sentence>();
        getNextToken();
    }
    
    /**
     * set currentToken to next token
     */
    private void getNextToken()
    {
        currentToken = scanner.nextToken();
    }
    
    /**
     * 'eats' the current token (advances the scanner by 1 token) if the
     * expected token (token) matches the current token. otherwise, throw a
     * runtime exception
     * @param token                     the current token
     */
    public void eat(Token token)
    {
        if (token.toString().equals(currentToken.toString()))
        {
            getNextToken();
        }
        else
        {
            throw new RuntimeException("Parameter (" + token + 
                ") doesn't match expected token (" + currentToken + ")");
        }
    }
    
    /**
     * @return the phrase that the current token is a part of
     * @postcondition           the currentToken is advanced to the token
     * immediately after the phrase that was just parsed through
     */
    public Phrase parsePhrase()
    {
        Phrase currentPhrase = new Phrase();
        Scanner.TOKEN_TYPE currtokentype = currentToken.getType();
        
        while (currtokentype != Scanner.TOKEN_TYPE.END_OF_FILE && 
            currtokentype != Scanner.TOKEN_TYPE.END_OF_SENTENCE &&
            currtokentype != Scanner.TOKEN_TYPE.END_OF_PHRASE)
        {
            if (currtokentype == Scanner.TOKEN_TYPE.WORD)
            {
                currentPhrase.addToken(currentToken);
            }
            
            eat(currentToken);
            currtokentype = currentToken.getType();
        }
        
        return currentPhrase;
    }
    
    /**
     * @return the sentence that the current token is a part of
     * @postcondition           the currentToken is advanced to the token
     * immediately after the sentence that was just parsed through
     */
    public Sentence parseSentence()
    {
        Sentence sentence = new Sentence();
        Scanner.TOKEN_TYPE currtokentype = currentToken.getType(); 
        
        while (currtokentype != Scanner.TOKEN_TYPE.END_OF_FILE && 
            currtokentype != Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            sentence.addPhrase(parsePhrase());
            currtokentype = currentToken.getType(); 
            
            if (currtokentype != Scanner.TOKEN_TYPE.END_OF_FILE)
            {
                eat(currentToken);
            }
        }
        
        return sentence;
    }
    
    /**
     * converts the document into Sentence, Phrase, and Token objects and 
     * stores it in a List
     */
    public void parseDocument()
    {
        Scanner.TOKEN_TYPE currtokentype = currentToken.getType();
        
        while (currtokentype != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            if (currtokentype != Scanner.TOKEN_TYPE.WORD)
            {
                eat(currentToken);
            }
            else
            {
                sentences.add(parseSentence());
            }
            
            currtokentype = currentToken.getType();
        }
    }
    
    /**
     * @return a deep copy of the sentences stored in the document (a deep
     * copy means that each object in the returned list has been duplicated,
     * and not just a reference is returned)
     */
    public List<Sentence> getSentences()
    {
        List<Sentence> tsentences = new ArrayList<Sentence>();
        Sentence tsentence;
        
        for (Sentence sentence : sentences)
        {
            tsentence = new Sentence();
            List<Phrase> phrases = sentence.getPhrases();
            for (Phrase phrase : phrases)
            {
                tsentence.addPhrase(phrase);
            }
            
            tsentences.add(tsentence);
        }
        
        return tsentences;
    }
}
