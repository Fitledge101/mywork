import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * InputReader reads typed text input from the standard text terminal. 
 * The text typed by a user is then chopped into words, and a list of words 
 * is provided.
 * 
 * @author     David J. Barnes and Michael Kölling
 * @version    2016.11.15
 */
public class InputReader
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a list of words.
     *
     * @return  A list of Strings, where each String is one of the 
     *          words typed by the user.
     */
    public LinkedList<String> getInput() 
    {
        System.out.print("> ");                // print prompt
        String inputLine = reader.nextLine().trim().toLowerCase();

        List<String> words = Arrays.asList(inputLine.split(" "));
        Iterator<String> it = words.iterator();
        LinkedList<String> wordsToReturn = new LinkedList<>();
        while(it.hasNext()) {
            String word = it.next().trim();
            if(!word.isEmpty()) {
                wordsToReturn.add(word);
            }
        }
                
        return wordsToReturn;
    }
    
    /**
     * Convert the given string to an integer.
     * NB: It is assumed that the string has already been checked
     * for validity. An exception will be thrown if the string
     * is not a valid integer.
     * @param word The string to be converted.
     * @return The integer represented by the parameter.
     */
    public int convertToInteger(String word)
    {
        return Integer.parseInt(word);
    }
    
    /**
     * Determine whether the given string is an integer or not.
     * @return true if it is an integer, false otherwise.
     */
    public boolean isAnInteger(String word)
    {
        boolean ok;
        try {
            Integer.parseInt(word);
            ok = true;
        }
        catch(NumberFormatException ex) {
            // Non-integer string.
            ok = false;
        }
        return ok;
    }
}
