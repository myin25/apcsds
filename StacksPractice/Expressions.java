import java.util.*;

/**
 * Expressions class that can check if parentheses
 *      are balanced and perform infix/postfix operations
 * @author Anu Datar
 * @author Melody Yin
 * @version 10/27/2017
 */

public class Expressions
{
    /** parenthesis matching : An expression is said to be balanced if
     * every opener has a corresponding closer, in the right order
     * {, [ or ( are the only types of brackets allowed
     * @param   expression containing operands operators 
     *      and any of the 3 supportedbrackets
     * @return  true is the parenthesis are balanced         
    //          false otherwise
     */
    public static boolean matchParenthesis(String expression)
    {
        System.out.println("expression evaluating " + expression);
        Stack<String> parentheses = new Stack<String>();
        List<String> typesofparentheses = new ArrayList<String>(
            Arrays.asList("[", "{", "(", ")", "}", "]"));
        for (int i = 0; i < expression.length(); i++)
        {
            // if the character is a number or a character, just skip
            if (!typesofparentheses.contains(expression.substring(i, i + 1)))
            {
            }
            
            // if the character is an opening parentheses,
            //          add to parentheses stack
            else if (expression.substring(i, i + 1).equals("(") || 
                    expression.substring(i, i + 1).equals("[") || 
                    expression.substring(i, i + 1).equals("{"))
            {
                parentheses.push(expression.substring(i, i + 1));
            }
            
            // if the character is an closing ) and the
            //      top of the parentheses stack is the opening (
            // making sure that parentheses isn't empty to prevent an error
            else if (expression.substring(i, i + 1).equals(")") && 
                     !parentheses.isEmpty() && parentheses.peek().equals("("))
            {
                parentheses.pop();
            }
            
            // if the character is an closing ] and the top of 
            //          the parentheses stack is the opening [
            // making sure that parentheses isn't empty to prevent an error
            else if (expression.substring(i, i + 1).equals("]") && 
                    !parentheses.isEmpty() && parentheses.peek().equals("["))
            {
                parentheses.pop();
            }
            
            // if the character is an closing } and the top of the 
            //      parentheses stack is the opening {
            // making sure that parentheses isn't empty to prevent an error
            else if (expression.substring(i, i + 1).equals("}") && 
                    !parentheses.isEmpty() && parentheses.peek().equals("{"))
            {
                parentheses.pop();
            }
            
            // if the character isn't a number and it's not a balanced 
            //      parentheses set, then return false
            else
            {
                return false;
            }
        }
        
        return parentheses.isEmpty();
    }
    
    /**
     * returns a string in postfix form
     *      if given an expression in infix form as a parameter, 
     *      does this conversion using a Stack
     * @param expr valid expression in infix form
     * @precondition only integers and operators (*, /, +, -, %) are included
     *          parentheses are balanced
     * @return equivalent expression in postfix form
    */
    public static String infixToPostfix(String expr)
    {
        Stack<String> stack = new Stack<String>();
        String strPostfix = "";
        List<String> typesofoperators = new ArrayList<String>(
            Arrays.asList("*", "/", "+", "-", "%"));
            
        for (int i = 0; i < expr.length(); i++)
        {
            String temp = expr.substring(i, i + 1);
            
            // if operator, push to stack
            if (typesofoperators.contains(temp))
            {
                while (!stack.isEmpty() && ranker(temp) <= ranker(stack.peek()))
                {
                    strPostfix += stack.pop();
                    strPostfix += " ";
                }
                
                stack.push(temp);
            }
            
            // if opening parenthesis
            else if (temp.equals("("))
            {
                stack.push(temp);
            }
            
            // if closing parenthesis, loop through stack until ( is encountered
            //      this assumes that the parentheses are already balanced
            else if (temp.equals(")"))
            {
                while (!stack.isEmpty())
                {
                    if (!stack.peek().equals("("))
                    {
                        strPostfix += stack.pop();
                        strPostfix += " ";
                    }
                    else
                    {
                        stack.pop();
                        break;
                    }
                }
            }
            
            // if operand, add to result
            else
            {
                strPostfix += temp;
            }
        }
        
        // pop out all remaining operators
        strPostfix += " ";
        while (!stack.isEmpty())
        {
            strPostfix += stack.pop();
            strPostfix += " ";
        }
        
        System.out.println("postfix " + strPostfix);
        return strPostfix;
    }
    
    /**
     * 'ranking' function that determines the precendence of an operator
     * @param operator is the operator we are determining the precedence of
     * @return 1 if the operator is + or -
     *          return 2 if the operator is * or / or %
     */
    private static int ranker(String operator)
    {
        // lowest priority 
        if (operator.equals("+") || operator.equals("-"))
        {
            return 1;
        }
        
        else if (operator.equals("*") || operator.equals("/") ||
                operator.equals("%"))
        {
            return 2;
        }
        
        return -1;
    }

    /** returns the value of an expression in postfix form and does this
     *          computation using a Stack
     * @param expr valid expression in postfix form
     * @return value of the expression
     * @precondition postfix expression  
     *               contains numbers and operators + - * / and %
     *               and that operands and operators are separated by spaces
    */
    public static double evalPostfix(String expr)
    {
        Stack<Integer> postfixOperands = new Stack<Integer>();
        String[] arr = expr.split(" ");
        
        for (int i = 0; i < arr.length; i++)
        {
            
            // multiplication
            if (arr[i].equals("*"))
            {
                postfixOperands.push(postfixOperands.pop() *
                        postfixOperands.pop());
            }
            
            // division
            else if (arr[i].equals("/"))
            {
                int temp = postfixOperands.pop();
                postfixOperands.push(postfixOperands.pop()
                        / temp);
            }
            
            // mod
            else if (arr[i].equals("%"))
            {
                int temp = postfixOperands.pop();
                postfixOperands.push(postfixOperands.pop()
                        % temp);
            }
            
            // addition
            else if (arr[i].equals("+"))
            {
                postfixOperands.push(postfixOperands.pop()
                        + postfixOperands.pop());
            }
            
            // subtraction
            else if (arr[i].equals("-"))
            {
                postfixOperands.push(postfixOperands.pop()
                        - postfixOperands.pop());
            }
            
            // number
            else if (arr[i].length() != 0)
            {
                postfixOperands.push(Integer.valueOf(arr[i]));
            }
        }
        return postfixOperands.peek();
    }

    /**
     * Tester to check if infix to postfix and evaluate postfix work well
     * @param args user input
     */
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   
        
        exp = " ( 2 + 3 ) * 5";
        test(exp, 25);
        
        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }
    
    /**
     * Tester that runs infixtopostfix testing situations
     * @param expr is string being changed from infix to postfix
     * @param expect is the expected answer
     */
    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);        
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        System.out.println("Expected: " + expect);
        if (val == expect)
        {
            System.out.print("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }
    
    /**
     * Tester that runs palindrome/parentheses testing situations
     * @param ex is the string you're trying to match parentheses on
     * @param expected is the expected result
     */
    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + 
                ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check :" +
                "matchParen(" + ex + ")");
            System.out.println(" returned " + act + 
                " but should have returned " + expected);
        }
    }
}
