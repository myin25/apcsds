import java.io.*;

/**
 * Predicts author of unknown document using metrics characteristic of
 * each author
 *
 * @author Melody Yin
 * @version 24-05-22
 */
public class FindAuthor
{
    /**
     * Predicts author of unknown document using characteristic statistics
     * of other known authors
     * @param arg               default input
     */
    public static void main(String[] arg)
    {
        File [] namedFiles = new File("./SignatureFiles").listFiles();
        double[] weights = {11, 33, 50, 0.4, 4};
        
        // compute the stats of all the known files
        for (int i = 1; i <= 5; i++)
        {
            String unknownFileName = "./MysteryText/mystery" + i + ".txt";
            try
            {
                FileReader reader = new FileReader(new File(unknownFileName));
                Scanner scanner = new Scanner(reader);
                Document doc = new Document(scanner);
                Metrics metrics = new Metrics(doc);
                String mysteryAuthor = "";
                double minDiff = Integer.MAX_VALUE;
                double tempDiff;
                
                doc.parseDocument();
                
                metrics.computeStats();
                double[] elements = metrics.elements();
                
                for (File namedFile : namedFiles)
                {
                    tempDiff = 0;
                    java.util.Scanner namedscanner = 
                        new java.util.Scanner(namedFile);
                    String author = namedscanner.nextLine();
                    
                    double[] authorelements = new double[5];
                    for (int j = 0; j < 5; j++)
                    {
                        authorelements[j] = namedscanner.nextDouble();
                    }
                    
                    for (int j = 0; j < 5; j++)
                    {
                        tempDiff += weights[j] * Math.abs(authorelements[j]
                            - elements[j]);
                    }
                    
                    if (tempDiff < minDiff)
                    {
                        mysteryAuthor = author;
                        minDiff = tempDiff;
                    }
                }
                
                System.out.println("Minimum difference : " + minDiff);
                System.out.println("Closest author match: " + mysteryAuthor);
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Could not find file " + 
                    "./MysteryText/mystery" + i + ".txt");
            }
        }
    }
}
