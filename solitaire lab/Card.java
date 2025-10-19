
/**
 * Simulates card from solitaire
 *
 * @author Melody Yin
 * @version 10/28/21
 */
public class Card
{
    int rank;
    String suit;
    boolean isFaceUp;

    /**
     * Constructor for objects of class Card
     * @param cardrank rank of the card (1-13)
     * @param cardsuit suit of the card (c, d, h, s)
     */
    public Card(int cardrank, String cardsuit)
    {
        rank = cardrank;
        suit = cardsuit;
        isFaceUp = false;
    }
    
    /**
     * Returns the rank of the card
     * @return rank of the card
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Returns the rank of the card
     * @return suit of the card
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Returns if the card suit is red or not
     * @return true if card suit is d or h
     *          return false if card suit is c or s
     */
    public boolean isRed()
    {
        return (suit.equals("d") || suit.equals("h"));
    }
    
    /**
     * Returns if the card is face up or not
     * @return true if card is faceup
     *          return false if the card is face down
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    
    /**
     * flips the card to be face up
     * @postcondition isFaceUp becomes true
     */
    public void turnUp()
    {
        isFaceUp = true;
    }
    
    /**
     * flips the card to be face down
     * @postcondition isFaceUp becomes false
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    
    /**
     * Returns the path to file name; used for displaying cards 
     *      in the user interface
     * @return path to file name
     */
    public String getFileName()
    {
        if (!isFaceUp)
        {
            return "cards/backapcsds.gif";
        }
        String temp = String.valueOf(rank);
        
        // determine if you need to change the value of the card to be king
        // or queen or jack or ace
        if (temp.equals("1"))
        {
            temp = "a";
        }
        else if (temp.equals("11"))
        {
            temp = "j";
        }
        else if (temp.equals("12"))
        {
            temp = "q";
        }
        else if (temp.equals("13"))
        {
            temp = "k";
        }
        else if (temp.equals("10"))
        {
            temp = "t";
        }
        
        temp = "cards/" + temp + suit + ".gif";
        return temp;
    }
}
