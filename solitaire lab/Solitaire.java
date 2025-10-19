import java.util.*;

/**
 * Solitaire class that simulates game
 * @author Melody Yin
 * @version 11/8/21
 */
public class Solitaire
{
    /**
     * main class; calls on class
     * @param args command line parameter
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;
    
    /**
     * Constructor that initializes stock, waste, foundations, and piles
     *      to be empty Stacks of Cards and then creates stock of cards
     *      + deals cards. Also creates new SolitaireDisplay.
     * object.
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        piles = new Stack[7];
        for (int i = 0; i < piles.length; i++)
        {
            piles[i] = new Stack();
        }
        
        for (int i = 0; i < foundations.length; i++)
        {
            foundations[i] = new Stack();
        }

        stock = new Stack<Card>();
        waste = new Stack<Card>();
        
        createStock();
        deal();
        
        display = new SolitaireDisplay(this);
    }

    /**
     * Makes deck of 52 cards and then randomly chooses 1 card at a time to
     * move from deck to stock.
     * @postcondition stock is filled with a shuffled deck
     */
    private void createStock()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        String[] suits = new String[4];
        suits[0] = "c";
        suits[1] = "d";
        suits[2] = "h";
        suits[3] = "s";
        
        for (int i = 1; i <= 13; i++)
        {
            for (String j : suits)
            {
                cards.add(new Card(i, j));
            }
        }
        
        for (int i = 0; i < 52; i++)
        {
            int max = cards.size() - 1;
            int temp = (int) Math.floor(Math.random() * (max + 1));
            stock.push(cards.get(temp));
            cards.remove(temp);
        }
    }
    
    /**
     * Returns card at the top of stock
     * @return the card on top of the stock, or null if the stock is empty
     */
    public Card getStockCard()
    {
        if (stock.isEmpty())
        {
            return null;
        }
        return (stock.peek());
    }

    /**
     * Returns card on top of waste
     * @return the card on top of the waste,or null if the waste is empty
    */
    public Card getWasteCard()
    {
        if (waste.isEmpty())
        {
            return null;
        }
        return (waste.peek());
    }

    /**
     * Gets card from specified foundation stack
     * @param index is specified foundation stack index
     * @precondition 0 <= index < 4
     * @postcondition returns the card on top of the given
                   foundation, or null if the foundation
                   is empty
     * @return top card on foundation or null
     */
    public Card getFoundationCard(int index)
    {
        if (foundations[index] == null || foundations[index].isEmpty())
        {
            return null;
        }
        else
        {
            return foundations[index].peek();
        }
    }

    /**
     * Gets reference to specified pile index
     * @param index is specified pile stack index
     * @precondition 0 <= index < 7
     * @postcondition returns a reference to the given pile
     * @return reference to given pile
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }
    
    /**
     * Deals the shuffled cards in the stock into the 7 piles
     * @precondition the deck has already been shuffled and stock filled
     * @postcondition 28 cards are dealed into stacks of size 
     *      1, 2, 3, 4, 5, 6, and 7,
     *          which are stored in the array piles.
     */
    private void deal()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < i + 1; j++)
            {
                piles[i].push(stock.pop());
            }
            piles[i].peek().turnUp();
        }
    }
    
    /**
     * Moves top 3 cards from stock to top of the waste and if there is less
     *          than 3, transfer what's left onto the waste
     * @postcondition top three/two/one cards from stock are moved to waste
     *          if the stock is empty, then nothing happens
     */
    private void dealThreeCards()
    {
        int counter = 0;
        while (!stock.isEmpty() && counter < 3)
        {
            waste.push(stock.pop());
            
            waste.peek().turnUp();
            counter++;
        }
    }
    
    /**
     * Repeatedly moves top card from waste to top of stock until the waste 
     *      is empty
     * @postcondition all the cards from waste are now in stock
     */
    private void resetStock()
    {
        int counter = 0;
        while (!waste.isEmpty())
        {
            stock.push(waste.pop());
            stock.peek().turnDown();
        }
    }

    /**
     * Called when stock is clicked and determines if you need to deal
     *      0/1/2/3 cards or if you need to refill the stock.
     * @postcondition 0/1/2/3 cards are moved to waste or stock is reset
     */
    public void stockClicked()
    {
        if (!display.isWasteSelected() && !display.isPileSelected())
        {
            if (!stock.isEmpty())
            {
                dealThreeCards();
            }
            else
            {
                resetStock();
            }
        }
    }

    /** 
     * Called when the waste is clicked and selects/deselects waste.
     * @postcondition waste is selected
     */
    public void wasteClicked()
    {
        if (!waste.isEmpty() && !display.isWasteSelected() &&
                        !display.isPileSelected())
        {
            display.selectWaste();
        }
        
        else
        {
            if (display.isWasteSelected())
            {
                display.unselect();
            }
        }
        
    }

    /**
     * Called when given foundation is clicked
     * @param index is the specified foundation (0, 1, 2, or 3) that the user
     *      clicked on
     * @precondition 0 <= index < 4
     * @postcondition
     *      if the previously selected card was from the waste, move top waste
     *          card to the top of the given foundation
     *      if the previously selected card was from one of the piles, move top
     *          card from specified pile to given foundation
     *      if the foundation was chosen first (in order to move a card from 
     *          the foundation to a pile), then select the specified foundation.
     *          may also deselect the foundation if it was previously selected.
     */
    public void foundationClicked(int index)
    {
        // if the selected card is a waste card
        if (display.isWasteSelected())
        {
            if (canAddToFoundation(waste.peek(), index))
            {
                foundations[index].push(waste.pop());
            }
            
            display.unselect();
        }
        
        // if the selected card is a pile card
        else if (display.isPileSelected())
        {
            if (canAddToFoundation(piles[display.selectedPile()].peek(), index))
            {
                foundations[index].push(piles[display.selectedPile()].pop());
                display.unselect();
            }
        }
        
        // if the foundation is clicked before anything else, might be
        //      transferring card from foundation to pile
        else if (!display.isPileSelected() && !display.isWasteSelected())
        {
            if (!foundations[index].isEmpty())
            {
                System.out.println("clicking nonempty foundation");
                // if it was already selected, then deselect it
                if (display.isFoundationSelected())
                {
                    display.unselect();
                }
                
                else
                {
                    display.selectFoundation(index);
                }
            }
        }
    }

    /**
     * Called when the given pile is clicked
     * @param index is the index of the pile clicked by the user
     * @precondition  0 <= index < 7
     * @postcondition
     *      if the previously selected card was from the waste,
     *          move the top card from the waste to the top of
     *          the pile that was clicked
     *      if the previously selected card was from a foundation,
     *          move the top card from the specified
     *          foundation to the top of the pile that was
     *          clicked
     *      if the card was face down when it was clicked,
     *          turn the card face up
     *      if the previously selected card was from another
     *          pile, move the top card from the previously
     *          selected pile to the pile that was clicked
     *      if the previously selected card was the same one
     *          as the one that was clicked, deselect it
     */
    public void pileClicked(int index)
    {
        
        // transferring a card from waste to a pile
        if (display.isWasteSelected() && canAddToPile(waste.peek(), index))
        {
            piles[index].push(waste.pop());
            display.unselect();
        }
        
        // transferring a card from foundation to a pile
        else if (display.isFoundationSelected() &&
            !foundations[display.selectedFoundationIndex()].isEmpty() &&
            canAddToPile(foundations[display.selectedFoundationIndex()].peek(),
                index))
        {
            piles[index].push(foundations[display.selectedFoundationIndex()]
                        .pop());
            display.unselect();
        }
        
        // turning up a card if it's face down
        else if (!piles[index].isEmpty() && !display.isPileSelected() 
                && !display.isWasteSelected() && 
                !piles[index].peek().isFaceUp())
        {
            piles[index].peek().turnUp();
        }
        
        else
        {
            // check to see if you are selecting the pile.
            if (!display.isPileSelected() && !piles[index].isEmpty())
            {
                display.selectPile(index);
            }
            
            // check to see if you are going to deselect the pile.
            else if (display.isPileSelected() && 
                    index == display.selectedPile())
            {
                display.unselect();
            }
            
            else if (display.selectedPile() != -1)
            {
                // check to see if you are going to just move one card.
                if (display.selectedPile() > -1 &&
                    canAddToPile(piles[display.selectedPile()].peek(), index))
                {
                    piles[index].push(piles[display.selectedPile()].pop());
                    display.unselect();
                }
                
                else // check to see if you can move the entire pile.
                {
                    Stack<Card> temp;
                    temp = removeFaceUpCards(display.selectedPile());
                    
                    // if you can, then add to new pile
                    if (canAddToPile(temp.peek(), index))
                    {
                        addToPile(temp, index);
                    }
                    
                    // otherwise, replace the cards
                    else
                    {
                        addToPile(temp, display.selectedPile());
                    }
                    
                    display.unselect();
                }
            }
        }
    }
    
    /**
     * Called by the pileClicked function to see if you can legally
     *      add a selected card to a specified pile
     * @param card is the card you are checking to see if you can add
     * @param index is the pile you are checking to see if you can add a card to
     * @precondition 0 <= index < 7
     * @return true if you can legally add a selected card
     *      false otherwise
     */
    private boolean canAddToPile(Card card, int index)
    {
        // if the pile is empty, you can only add a king of any color
        if (piles[index].isEmpty())
        {
            if (card.rank == 13)
            {
                return true;
            }
            return false;
        }
        
        Card tempcard = piles[index].peek();
        
        if ((card.rank == tempcard.rank - 1) &&
                    (card.isRed() != tempcard.isRed()))
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Pops all of the faceup cards from selected pile and returns them
     * @precondition 0 <= index < 7
     * @postcondition all faceup cards from selected pile are removed
     * @return all faceup cards from selected pile
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> temp = new Stack<Card>();
        
        while (!piles[index].isEmpty() && piles[index].peek().isFaceUp())
        {
            temp.push(piles[index].pop());
        }
        
        return temp;
    }
    
    /**
     * Adds cards to specified pile
     * @param cards is a stack of cards we are adding to specified pile
     * @param index is the specified pile that cards are being added to
     * @precondition 0 <= index < 7
     * @postcondition all cards from cards stack are added to
     *      specified pile
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        while (!cards.isEmpty())
        {
            piles[index].push(cards.pop());
        }
    }
    
    /**
     * Determines if you can add a specific card to a specific foundation
     * @param card is the card you are checking to see if you can add
     * @param index is the foundation you are checking to
     *              see if you can add a card to
     * @precondition 0 <= index < 4
     * @return true if you can legally add a selected card
     *      false otherwise
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        // if the foundation is empty, you can only add an ace
        if (foundations[index].isEmpty())
        {
            if (card.rank == 1)
            {
                return true;
            }
            return false;
        }
        
        // if the foundation isn't empty, you have to add the
        //          same suit and +1 in value
        Card tempcard = foundations[index].peek();
        
        if ((card.rank == tempcard.rank + 1) && (card.suit == tempcard.suit))
        {
            return true;
        }
        
        return false;
    }
    /**
     * Determines if the user has won the game
     * @return true if they won
     *      false if otherwise
     */
    public boolean won()
    {
        boolean won = true;
        
        // loop through foundations to make sure they are all in order
        for (int i = 0; i < foundations.length; i++)
        {
            if (foundations[i].isEmpty() || foundations[i].peek().rank != 13)
            {
                won = false;
                break;
            }
        }
        
        return won;
    }
}