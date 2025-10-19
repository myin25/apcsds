import java.util.*;

/**
 * Allows the user to play Jotto. Has the following methods:
 *      echo()
 *      dictionaryIndex(String str)
 *      getRandomWord()
 *      getRandomWord(int len)
 *      commonLetters(word1, word2)
 *      jotto()
 *
 * @author Melody Yin
 * @version 1-24-22
 */
public class WordGame
{
    private WordGameDisplay wgdisplay;
    private ArrayList<String> dict;
    
    /**
     * Constructor for objects of class WordGame - creates a new WordGameDisplay
     *      and stores it as an instance variable.
     * @postcondition wgdisplay is initialized and dictionary has loaded
     *      words from dictionary file
     */
    public WordGame()
    {
        wgdisplay = new WordGameDisplay();
        
        dict = new ArrayList<String>();
        Iterator it = wgdisplay.loadWords("words.txt");
        while (it.hasNext())
        {
            dict.add((String) it.next());
        }
    }
    
    /**
     * Sets title of display to "The Echo Game" and prompts user to enter a 
     *      word. When user enters a word, window echos back what the user
     *      typed.
     * @postcondition 'echo' game is played in a popup with user     
     */
    public void echo()
    {
        wgdisplay.setTitle("The Echo Game");
        String output = "Enter a word.";
        wgdisplay.setText(output);
        
        while (true)
        {
            String guess = wgdisplay.getGuess();
            output += "\n";
            
            int wordind = dictionaryIndex(guess);
            // check to see if it is a word
            if (wordind != -1)
            {
                output += "\"" + guess + "\" " + "is word #" + wordind +
                            ".";
            }
            else
            {
                output += "\"" + guess + "\" is not a word";
            }
            
            output += "\n";
            
            output += "Enter a word.";
            wgdisplay.setText(output);
        }
    }
    
    /**
     * Returns the index of a given string in a dictionary using 
     *      an iterative implementation of binary search.
     *      Binary search - checks the middle of the array. If the string is
     *      greater in value than the middle, look in the right half of the
     *      array. If the string is equal to the middle, return the index.
     *      If the string is lesser in value, check the left half of the
     *      array. Repeat until the array size is 0 or the item is found.
     * @param str is the string being looked for in the dictionary
     * @return the index of a string in the dictionary
     *      returns -1 if the string is not found
     */
    public int dictionaryIndex(String str)
    {
        int left = 0;
        int right = dict.size();
        int mid;
        
        while (left <= right)
        {
            mid = left + (right - left)/2;
            int compare = (dict.get(mid)).compareTo(str);
            if (compare < 0)
            {
                left = mid + 1;
            }
            else if (compare == 0)
            {
                return mid;
            }
            else
            {
                right = mid - 1;
            }
        }
        
        // if you can't find it, return -1
        return - 1;
    }
    
    /**
     * Chooses a random word from the dictionary and returns it
     * @return a random word from dictionary
     */
    public String getRandomWord()
    {
        int ind = (int) (dict.size() * Math.random());
        return dict.get(ind);
    }
    
    /**
     * Finds a random word from the dictionary of a specified length using
     *      getRandomWord and returns it
     * @param len is the length of the word
     * @return a random word of length len from dictionary
     */
    public String getRandomWord(int len)
    {
        String word;
        while (true)
        {
            word = getRandomWord();
            if (word.length() == len)
            {
                return word;
            }
        }
    }
    
    /**
     * Finds the number of letters that two words have in common using 
     *      recursion
     * @param word1 is the first word
     * @param word2 is the second word
     * @return the number of letters that two words have in common
     */
    public int commonLetters(String word1, String word2)
    {
        if (word1.equals("") || word2.equals(""))
        {
            return 0;
        }
        
        String ch = word1.substring(0, 1);
        
        for (int j = 0; j < word2.length(); j++)
        {
            if (word2.substring(j, j + 1).equals(ch))
            {
                word2 = word2.substring(0, j) + word2.substring(1 + j);
                
                return 1 + commonLetters(word1.substring(1), word2);
            }
        }
        
        return commonLetters(word1.substring(1), word2);
    }
    
    /**
     * plays jotto with the user - selects as random word of len n, starting
     *      at len 2, and has the user guess the word. if the user gets the
     *      word incorrect, jotto prints out the number of words that their
     *      guess has in common with the random word. if the user gets the
     *      word right, jotto will choose a new random word of length n + 1.
     *      the game continues until the user quits. if the user enters 
     *      'pass', the game will reveal the random word and then choose a
     *      new word of len n.
     */
    public void jotto()
    {
        int numletters = 2;
        String word;
        String output = "";
        
        while (true)
        {
            wgdisplay.setTitle("Jotto (" + numletters + " Letters)");
            word = getRandomWord(numletters);
            int counter = 1;
            output += "Guess my " + numletters + "-letter word. \n";
            wgdisplay.setText(output);
            
            while (true)
            {
                String guess = wgdisplay.getGuess();
                if (guess.equals("pass"))
                {
                    output += "Was \"" + word + "\". Play again! \n";
                    wgdisplay.setText(output);
                    word = getRandomWord(numletters);
                }
                
                else
                {
                    int wordind = dictionaryIndex(guess);
                    
                    // check to see if it is a word
                    if (wordind != -1)
                    {
                        if (guess.length() != numletters)
                        {
                            output += counter + "." + "\t" + guess + "\t" + 
                                        "Word must be " + numletters + 
                                        " letters" + "\n";
                        }
                        // check if it matches
                        else if (guess.equals(word))
                        {
                            output += counter + "." + "\t" + guess + "\t" + 
                                        "That's it! \n";
                            wgdisplay.setText(output);
                            break;
                        }
                        else
                        {
                            output += counter + "." + "\t" + guess + "\t" + 
                                    commonLetters(word, guess);
                        }
                    }
                    else
                    {
                        output += counter + "." + "\t" + guess + "\t" + 
                                "That's not a word!";
                    }
                
                    output += "\n";
                    
                    wgdisplay.setText(output);
                    
                    counter += 1;
                }
            }
            
            numletters ++;
        }
    }
}
