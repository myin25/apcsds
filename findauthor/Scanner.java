import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 *  
 * @author Mr. Page
 * @author Melody Yin
 * @version 16 May 2022
 */

public class Scanner
{
    private Reader in;
    private String currentChar; 
    private boolean endOfFile;
    
    /**define symbolic constants for each type of token*/
    public static enum TOKEN_TYPE
    { 
        /**
         * Represents a token of type WORD, which consists of a consecutive 
         * sequence of letters, digits, and special characters.
         */
        WORD, 
       
        /**
         * Represents a token of type END_OF_SENTENCE, which signifies the 
         * end of a sentence.
         */
        END_OF_SENTENCE,
        
        /**
         * Represents a token of type END_OF_FILE, which signifies the end
         * of a file.
         */
        END_OF_FILE,
        
        /**
         * Represents a token of type END_OF_PHRASE, which signifies the end
         * of a phrase consisting of words and digits.
         */
        END_OF_PHRASE,
        
        /**
         * Represents a token of type DIGIT, which is a numeric value 
         * ranging from 0 to 9.
         */
        DIGIT,
        
        /**
         * Represents a token of type UNKNOWN, which may be a whitespace or
         * any other previously undefined type.
         */
        UNKNOWN
    };
    
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }
    
    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Takes in String object and compares to currentChar. If they match, 
     * advance input stream (calling getNextChar()). Otherwise, throw 
     * IllegalArgumentException.
     * @param str                      string to be compared with currentChar
     */
    private void eat(String str)
    {
        if (str.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException("Parameter (" + str + 
                ") doesn't match expected char (" + currentChar + ")");
        }
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a letter, false otherwise
     */
    private boolean isLetter(String str)
    {
        return str.compareTo("a") >= 0 && str.compareTo("z") <= 0;
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a digit, false otherwise
     */
    private boolean isDigit(String str)
    {
        return str.compareTo("0") >= 0 && str.compareTo("9") <= 0;
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a special character, false otherwise
     */
    private boolean isSpecialChar(String str)
    {
        return str.equals("-") || str.equals("'");
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a phrase terminator, false otherwise
     */
    private boolean isPhraseTerminator(String str)
    {
        return str.equals(";") || str.equals(":") || str.equals(",");
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a sentence terminator, false otherwise
     */
    private boolean isSentenceTerminator(String str)
    {
        return str.equals("?") || str.equals("!") || str.equals(".");
    }
    
    /**
     * @param str                   string to be checked
     * @return true if str is a white space, false otherwise
     */
    private boolean isWhiteSpace(String str)
    {
        return str.equals(" ") || str.equals("\n") || str.equals("\t") ||
            str.equals("\r");
    }
    
    /**
     * @return true if we haven't reached the end of the file yet, false
     *      otherwise
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }
    
    /**
     * If the current character is a letter, keep advancing the reader until
     * either a whitespace, a phrase terminator, a sentence terminator, or 
     * the end of the file is reached. If the consecutive tokens are a 
     * letter, a digit, or a special character (' or -), then these strings 
     * are appended to a temporary string, and once a terminator or 
     * whitespace is reached, the entire string is returned in the form of a
     * WORD Token.
     * 
     * If the current character is a digit, return a new Token of type DIGIT.
     * If the current character is a phrase terminator, return a new Token of
     * type END_OF_PHRASE.
     * If the current character is a sentence terminator, return a new Token
     * of type END_OF_SENTENCE.
     * If the end of the file is reached, return a new Token of type 
     * END_OF_FILE.
     * 
     * @return a Token of type UNKNOWN otherwise.
     */
    public Token nextToken()
    {
        // skip the characters that are spaces (unnecessary)
        while (hasNextToken() && isWhiteSpace(currentChar))
        {
            eat(currentChar);
        }
        
        // if we have reached the end of the file, then stop eating
        if (!hasNextToken())
        {
            return new Token(TOKEN_TYPE.END_OF_FILE, null);
        }
        
        String str = currentChar.toLowerCase();
        if (isLetter(str)) // keep adding tokens until end of word is reached
        {
            String string = "";
            while (endOfFile == false && (isLetter(str) || isSpecialChar(str) ||
                    isDigit(str)))
            {
                string += str;
                eat(currentChar);
                str = currentChar.toLowerCase();
            }
            
            return new Token(Scanner.TOKEN_TYPE.WORD, string);
        }
        
        if (isDigit(currentChar)) // advance scanner and return digit
        {
            str = currentChar.toLowerCase();
            eat(currentChar);
            return new Token(Scanner.TOKEN_TYPE.DIGIT, str);
        }
        else if (isPhraseTerminator(currentChar)) // advance scanner, return
        {
            str = currentChar.toLowerCase();
            eat(currentChar);
            return new Token(Scanner.TOKEN_TYPE.END_OF_PHRASE, str);
        }
        else if (isSentenceTerminator(currentChar)) // advance scanner, return
        {
            str = currentChar.toLowerCase();
            eat(currentChar);
            // System.out.println("sentence");
            return new Token(Scanner.TOKEN_TYPE.END_OF_SENTENCE, str);
        }
        else if (endOfFile)
        {
            str = currentChar.toLowerCase();
            eat(currentChar);
            // System.out.println("end");
            return new Token(Scanner.TOKEN_TYPE.END_OF_FILE, str);
        }
        
        // whitespace
        str = currentChar.toLowerCase();
        eat(currentChar);
        return new Token(Scanner.TOKEN_TYPE.UNKNOWN, str);
    }
}
