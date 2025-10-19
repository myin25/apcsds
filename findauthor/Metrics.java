import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Returns a non-negative real number indicating the similarity of two 
 * linguistic signatures. The smaller the number, the more similar the 
 * signatures. Zero indicates identical signatures.
 * sig1 and sig2 are 6 element lists with the following elements
 * 0  : author name (a string)
 * 1  : average word length (float)
 * 2  : TTR (float)
 * 3  : Hapax Legomana Ratio (float)
 * 4  : average sentence length (float)
 * 5  : average sentence complexity (float)
 * weight is a list of multiplicative weights to apply to each linguistic 
 * feature. weight[0] is ignored.
 *
 * @author Anu Datar
 * @author Melody Yin
 * @version 19 May 2022
 */
public class Metrics
{
    private Document document;
    private double[] elements;
    
    /**
     * Constructor for objects of class Metrics
     * @param doc               document whose metrics are to be evaluated
     */
    public Metrics(Document doc)
    {
        document = doc;
        elements = new double[5];
    }
    
    /**
     * @return the number of phrases present in a document
     */
    private int phraseCount()
    {
        int phrasecount = 0;
        
        for (Sentence sentence : document.getSentences())
        {
            phrasecount += sentence.getPhrases().size();
        }
        
        return phrasecount;
    }
    
    /**
     * @return the number of words present in a document
     */
    private int wordCount()
    {
        int wordcount = 0;
        
        for (Sentence sentence : document.getSentences())
        {
            for (Phrase phrase : (List<Phrase>) sentence.getPhrases())
            {
                wordcount += phrase.getTokens().size();
            }
        }
        
        return wordcount;
    }
    
    /**
     * @return the number of characters present in a document
     */
    private int charCount()
    {
        int charcount = 0;
        
        for (Sentence sentence : document.getSentences())
        {
            for (Phrase phrase : (List<Phrase>) sentence.getPhrases())
            {
                for (Token token : (List<Token>) phrase.getTokens())
                {
                    charcount += token.getValue().length();
                }
            }
        }
        
        return charcount;
    }
    
    /**
     * @return the average word length in characters
     */
    public double getavgWordLen()
    {
        int wordcount = wordCount();
        int charcount = charCount();
        
        double avg = ((double) charcount) / wordcount;
        elements[0] = avg;
        return avg;
    }
    
    /**
     * @return the type to token ratio (the number of different words to total
     * words)
     */
    public double gettypeTokRatio()
    {
        Set<String>[] uniquewords = new HashSet[26];
        
        for (int i = 0; i < uniquewords.length; i++)
        {
            uniquewords[i] = new HashSet<String>();
        }
        
        for (Sentence sentence : document.getSentences())
        {
            for (Phrase phrase : (List<Phrase>) sentence.getPhrases())
            {
                for (Token token : (List<Token>) phrase.getTokens())
                {
                    String val = token.getValue();
                    uniquewords[val.substring(0, 1).compareTo("a")].add(val);
                }
            }
        }
        
        int numunique = 0; 

        for (Set<String> beginningletter : uniquewords)
        {
            numunique += beginningletter.size(); 
        }
        
        double ratio = ((double) numunique) / wordCount();
        elements[1] = ratio;
        return ratio;
    }
    
    /**
     * @return the hapax legomana ratio (the number of words who appear once
     * to total words)
     */
    public double getHapaxLegomanaRatio()
    {
        int numunique = 0;
        
        Set<String>[] words = new HashSet[26];
        for (int i = 0; i < words.length; i++)
        {
            words[i] = new HashSet<String>();
        }
        
        Set<String>[] dupes = new HashSet[26]; 
        for (int i = 0; i < words.length; i++)
        {
            dupes[i] = new HashSet<String>();
        }
        
        for (Sentence sentence : document.getSentences())
        {
            for (Phrase phrase : (List<Phrase>) sentence.getPhrases())
            {
                for (Token token : (List<Token>) phrase.getTokens())
                {
                    String val = token.getValue();
                    int hashRow = val.substring(0, 1).compareTo("a");
                    
                    if (words[hashRow].add(val) == true)
                    {
                        numunique += 1;
                    }
                    else
                    {
                        if (dupes[hashRow].add(val))
                        {
                            numunique -= 1;
                        }
                    }
                }
            }
        }
        
        double ratio  = ((double) numunique) / wordCount();
        elements[2] = ratio;
        return ratio;
    }
    
    /**
     * @return the average sentence length (total words / number of sentences)
     */
    public float getAverageSentenceLength()
    {
        float avgLen = ((float) wordCount()) / document.getSentences().size();
        elements[3] = avgLen;
        return avgLen;
    }
    
    /**
     * @return the average number of phrases in a sentence (total phrases / 
     * number of sentences);
     */
    public float getAverageSentenceComplexity()
    {
        float avg = ((float) phraseCount()) / document.getSentences().size();
        elements[4] = avg;
        return avg;
    }
    
    /**
     * compute all 5 stats : average word length, TTR, Hapax Legomana Ratio,
     * average sentence length, average sentence complexity
     */
    public void computeStats()
    {
        getavgWordLen();
        gettypeTokRatio();
        getHapaxLegomanaRatio();
        getAverageSentenceLength();
        getAverageSentenceComplexity();
    }
    
    /**
     * @return all 5 stats
     */
    public double[] elements()
    {
        return elements;
    }
}
