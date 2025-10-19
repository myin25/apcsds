import java.util.*;

/**
 * TreeUtil contains the following methods for manipulating binary trees:
 *          find the leftmost and rightmost nodes of the tree
 *          find the maximum depth of a tree
 *          create a random tree of a specified depth
 *          count the number of nodes or leaves in the tree
 *          visit the values of a binary tree in preorder, inorder, or postorder
 *          represent tree in a linear form (in a list)
 *          save given tree in a file
 *          builds tree given a linear representation of the tree
 *          read a file name and builds a tree based on the file's contents
 *          plays a game of twenty questions with the user
 *          make a duplicate of a tree
 *          determine if two trees have the same shape
 *          create morse tree
 *          decode morse code
 *          
 * @author Melody Yin
 * @version 12/3/21
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    private static final boolean DEBUG = false;
    
    /**
     * Returns the leftmost node in the tree, given the root node
     * @param t             the root node of the tree
     * @return the leftmost node
     */
    public static Object leftmost(TreeNode t)
    {
        if (t == null)
        {
            return null;
        }
        // implement with a loop
        while (t.getLeft() != null)
        {
            t = t.getLeft();
        }
        return t.getValue();
    }

    /**
     * Returns the rightmost node in the tree, given the root node
     * @param t             the root node of the tree
     * @return the rightmost node in the tree
     */
    public static Object rightmost(TreeNode t)
    {
        // implement this recursively
        if (t == null) // break condition
        {
            return null;
        }
        else if (t.getRight() == null) // break condition: rightmost found
        {
            return t.getValue();
        }
        // continue to recurse
        return rightmost(t.getRight());
    }

    /**
     * Returns the max depth of the tree (the height of the tree)
     * @param t             the root node of the tree
     * @return the max depth of the tree
     */
    public static int maxDepth(TreeNode t)
    {
        if (t == null) // tree doesn't have any nodes
        {
            return 0;
        }

        // if not at the leaf node, return max of going to left or right
        return 1 + Math.max(maxDepth(t.getLeft()), maxDepth(t.getRight()));
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance
     * tree is provided.
     * @param depth         the depth of the tree
     * @return a reference that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }

    /**
     * counts number of nodes in tree
     * @param t             the root node of the tree
     * @return the number of nodes in tree
     */
    public static int countNodes(TreeNode t)
    {
        if (t == null) // breaking condition
        {
            return 0;
        }
        // otherwise, account for current node and recurse to left + right
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }

    /**
     * counts the number of leaves in the tree
     * @param t             the root node of the tree
     * @return the number of leaves in the tree
     */
    public static int countLeaves(TreeNode t)
    {
        if (t == null) // breaking condition
        {
            return 0;
        }
        // if the current node is a leaf, return 1 and break
        if (t.getLeft() == null && t.getRight() == null)
        {
            return 1;
        }
        // otherwise, keep recursing until leaf is found
        return countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }

    /**
     * puts values of binary tree into preorder: root, left, right
     * @param t             the parent node of tree
     * @param display       the graphic display for the process
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        if (t != null) // break condition
        {
            display.visit(t);
            preOrder(t.getLeft(), display);
            preOrder(t.getRight(), display);
        }
    }

    /**
     * puts values of binary tree into inorder: left, root, right
     * @param t             the parent node of tree
     * @param display       the graphic display for the process
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        if (t == null) // break condition
        {
            return;
        }
        inOrder(t.getLeft(), display);
        display.visit(t);
        inOrder(t.getRight(), display);
    }

    /**
     * puts values of binary tree into postorder: left, right, root
     * @param t             the parent node of tree
     * @param display       the graphic display for the process
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        if (t == null) // break condition
        {
            return;
        }

        // recurse left
        postOrder(t.getLeft(), display);
        // recurse right
        postOrder(t.getRight(), display);
        // mark current
        display.visit(t);
    }

    /**
     * takes in list of strings and fills list with contents of tree,
     *      including $ markers this should follow a preorder logic:
     *      root, left, right
     * @param t             the root of the tree
     * @param list          the list to be filled with the tree's contents
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        if (t == null) // if null, add signifier
        {
            list.add("$");
        }
        else
        {
            // otherwise, add the node and then the left and then right
            list.add(String.valueOf(t.getValue()));
            fillList(t.getLeft(), list);
            fillList(t.getRight(), list);
        }
    }

    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName      the name of the file to create which will hold 
     *                          the data values in the tree
     * @param t             the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        FileUtil fl = new FileUtil();
        List<String> list = new ArrayList<String>();
        fillList(t, list);
        Iterator<String> iter = list.iterator();
        fl.saveFile(fileName, iter);
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid
     *          description of a binary tree with String values.  Null 
     *          nodes are indicated by "$" markers
     * @param it            the iterator which will iterate over the 
     *                          tree description
     * @return the pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        // breaking condition: reached end of list
        if (!it.hasNext())
        {
            return null;
        }

        String temp = it.next();

        // if null, just return null
        if (temp.equals("$"))
        {
            return null;
        }

        // otherwise, set left and right nodes
        TreeNode t = new TreeNode(temp);
        t.setLeft(buildTree(it)); // set left to reference to left subtree
        t.setRight(buildTree(it)); // set right to reference right subtree
        return t;
    }

    /**
     * read a file description of a tree and then build the tree
     * @param           fileName is a valid file name for a file that
     *                      describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        FileUtil fu = new FileUtil();
        Iterator<String> it = fu.loadFile(fileName);
        return buildTree(it);
    }

    /**
     * utility method that waits for a user to type text into Std Input 
     *              and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }

    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the 
     *                      user questions as it walks down the given knowledge
     *                      tree, lighting up the display as it goes; modifies
     *                      the tree to include information learned.
     * @param t             the pointer to the root of the game tree
     * @param display       the graphical display which will show the progress
     *                              of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {
        // if t isn't null, visit node + ask question
        // else, do nothing
        if (t != null)
        {
            // visit node
            display.visit(t);
            
            // ask user if it is node value
            // if it is, check if leaf --> lose or win
            //      if not leaf, recurse
            System.out.println("Is it a " + t.getValue() + "? (y or n)");
            // check for y/n or yes/no
            boolean guessedright = getUserInput().substring(0, 1).equals("y");
            
            // check if leaf; determine if win/loss
            if (t.getLeft() == null && t.getRight() == null)
            {
                if (guessedright)
                {
                    System.out.println("I win!");
                    return;
                }
                else // guessed wrong --> update binary tree
                {
                    System.out.println("I give up. What is it?");
                    String temp = getUserInput();
                    System.out.println("What distinguishes a " + temp +
                                        " from a " + t.getValue() + "?");
                    
                    String diff = getUserInput();
                    Object root = t.getValue();
                    
                    
                    // change nodes around + add new left and right at current
                    t.setValue(diff);
                    t.setLeft(new TreeNode(temp));
                    t.setRight(new TreeNode(root));
                }
            }
            else // if not at leaf node, then make decision to go left or right
            {
                if (guessedright) // correct means go left
                {
                    twentyQuestionsRound(t.getLeft(), display);
                }
                else // otherwise, go right
                {
                    twentyQuestionsRound(t.getRight(), display);
                }
            }
        }
    }

    /** 
     * Plays a game of 20 questions. Begins by reading in a starting file
     *      and then plays multiple rounds until the user enters "quit".
     *      The final tree is then saved.
     */
    public static void twentyQuestions()
    {
        TreeNode temp = loadTree("knowledge.txt");
        TreeDisplay display = new TreeDisplay();
        // debug
        display.displayTree(temp);
        
        System.out.println("Continue the game? (yes or quit)");
        while (!getUserInput().equals("quit"))
        {
            twentyQuestionsRound(temp, display);
            
            // update knowledge.txt
            saveTree("knowledge.txt", temp);
            
            System.out.println("Continue the game? (yes or quit)");
        }
        
        display.displayTree(temp);
    }

    /**
     * copy a binary tree
     * @param t             the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if (t == null) // if tree is null, just return nothing
        {
            return null;
        }
        return new TreeNode(t.getValue(), copy(t.getLeft()),
                            copy(t.getRight()));
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1            pointer to the root of the first tree
     * @param t2            pointer to the root of the second tree
     * @return true if t1 and t2 have the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        // both empty
        if (t1 == null && t2 == null)
        {
            return true;
        }
        
        // not empty --> compare left + right
        if (t1 !=  null && t2 != null)
        {
            return (sameShape(t1.getLeft(), t2.getLeft())) &&
                        sameShape(t1.getLeft(), t2.getLeft());
        }
        
        // otherwise, return false
        return false;
    }

    /**
     * Generate a tree for decoding Morse code
     * @param display       the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }

    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree       is the partial decoding tree
     * @param letter             is the letter to add
     * @param code               is the Morse code for letter
     * @param display            is the display that will show progress as
     *                              the method walks down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
    String code, TreeDisplay display)
    {
        if (code.substring(0, 1).equals("."))
        {
            if (decodingTree.getLeft() != null) // recurse to the left
            {
                // if len == 1, set value to left node + keep potential children
                if (code.length() == 1)
                {
                    decodingTree.getLeft().setValue(letter);
                }
                else
                {
                    insertMorse(decodingTree.getLeft(), letter, 
                                code.substring(1), display);
                } 
            }
            else // initialize left
            {
                // if the left is null, create a new node
                if (code.length() == 1)
                { 
                    decodingTree.setLeft(new TreeNode(letter));
                }
                else
                {
                    decodingTree.setLeft(new TreeNode(""));
                    insertMorse(decodingTree.getLeft(), letter, 
                                    code.substring(1), display);
                }
            }
        }
        else // go right
        {
            if (decodingTree.getRight() != null) // recurse to the right
            {
                // if len = 1, set value to right + preserve potential children
                if (code.length() == 1)
                {
                    decodingTree.getRight().setValue(letter);
                }
                else
                {
                    insertMorse(decodingTree.getRight(), letter, 
                                code.substring(1), display);
                }
            }
            else // initialize right
            {
                // if the right is null, create a new node
                if (code.length() == 1)
                {
                    decodingTree.setRight(new TreeNode(letter));
                }
                else
                {
                    decodingTree.setRight(new TreeNode(""));
                    insertMorse(decodingTree.getRight(), letter,
                                code.substring(1), display);
                }
            }
        }
    }

    /**
     * decodes Morse code by walking the decoding tree according to input code
     * @param decodingTree       is the Morse code decoding tree
     * @param cipherText         is Morse code made of dots, dashes, and spaces
     * @param display            is the display object that shows decoding
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, 
                                        TreeDisplay display)
    {
        String[] text = cipherText.split(" "); 
        // System.out.println("len text " + text.length);
        String answer = "";
        TreeNode temp;
        
        for (int j = 0; j < text.length; j++) // iterate through each character
        {
            String character = text[j];
            temp = createDecodingTree(display);
            // iterate through each morse character
            for (int i = 0; i < character.length(); i++) 
            {
                if (character.substring(i, i + 1).equals("."))
                {
                    temp = temp.getLeft();
                    display.visit(temp);
                }
                else
                {
                    temp = temp.getRight();
                    display.visit(temp);
                }
            }
            answer = answer + temp.getValue();
        }
        return answer;
    }

    /**
     * optional work
     * @param expTree is expression to be evaluated
     * @return evaluated expression
     */
    public static int eval(TreeNode expTree)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * converts expression to tree
     * @param exp is expression to be converted
     * @return root of tree
     */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */

    private static void debugPrint(String out)
    {
        if (DEBUG) System.out.println("debug: " + out);
    }
}
 