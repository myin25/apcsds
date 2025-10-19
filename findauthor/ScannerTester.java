import java.io.*;

/**
 * Class that tests the Scanner class.
 *
 * @author Anu Datar
 * @author Melody Yin
 * @version 05/17/2018
 */
public class ScannerTester
{
    /**
     *  Main tester method 
     *
     * @param  str array of String objects 
     */
    public static void main(String[] str) throws FileNotFoundException
    {
        /**FileReader reader = new FileReader(new 
                File("./MysteryText/mystery1.txt"));
        Scanner scanner = new Scanner(reader);
        
        while (scanner.hasNextToken())
        {
            System.out.println(scanner.nextToken());
        }*/
        
        StringReader reader = new StringReader("hello there, are you " +
                "okay? ok. thank you.");
        Scanner scanner = new Scanner(reader);
        Document doc = new Document(scanner);
        doc.parseDocument();
        System.out.println(doc.getSentences());
    }
}
