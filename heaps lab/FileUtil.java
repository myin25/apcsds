import java.io.*;
import java.util.*;

/**
 * FileUtil class that can manipulate and access files
 * @author Anu Datar
 * @version 12-4-21
 */
public class FileUtil
{
    /**
     * loads a file from given file name and returns a string
     * @param fileName is the name of the file to be loaded from
     * @return string of file contents
     */
    public static Iterator<String> loadFile(String fileName)
    {
        try
        {
            Scanner in = new Scanner(new File(fileName));
            List<String> list = new ArrayList<String>();
            while (in.hasNextLine())
                list.add(in.nextLine());
            in.close();
            return list.iterator();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Given a string, write the string to a file of a given name
     * @param fileName is name of file data is being saved to
     * @param data is an iterator that goes through all the data
     */
    public static void saveFile(String fileName, Iterator<String> data)
    {
        try
        {
            PrintWriter out = new PrintWriter(
                new FileWriter(fileName), true);
            while (data.hasNext())
                out.println(data.next());
            out.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}