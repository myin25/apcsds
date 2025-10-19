import java.util.Stack;
        
/**
* Stringutil has two main functions: reversing a string
*       and determining if a string is a palindrome
* 
* @author Anu Datar
* @author Melody Yin
* @version 10/27/2017
*/
public class StringUtil
{
    /**
    * reverse a string using a stack
    * @param str is the string being reversed
    * @return the reversed string
    */
    public static String reverseString(String str)
    {
        Stack<String> stack = new Stack<String>();
        
        for (int i = 0; i < str.length(); i++)
        {
            stack.push(str.substring(i, i + 1));
        }
        
        String newstr = "";
        while (!stack.isEmpty())
        {
            newstr += stack.pop();
        }
        
        return newstr;
    }
        
    /**
     * uses the reverseString function to determine if
     *      a string is a palindrome or not
     * @param s is the string we are determining to be a palindrome or not
     * @return true if s is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s)
    {
        if (s.equals(reverseString(s)))
        {
            return true;
        }
        return false;
    }
        
    /**
     * The tester for checking that reverse and isPalindrome work well.
     * @param args 
     */
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("** Oops Something went wrong." + 
                    "Check your reverse method **");

        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("** Oops Something went wrong." +
                    "Check your reverse method **");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("** Oops Something went wrong." +
                    "Check your reverse method **");
        else
            System.out.println("Success " + test + " matched " +
                    reverseString(test));
            
        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("** Oops Something went wrong. " +
                    "Check your reverse method **");
    }
}
