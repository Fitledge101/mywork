import java.awt.Color;
import java.util.LinkedList;
import java.util.HashMap;
/**
 * Assists DrawingTool by providing and invoking mutator methods.
 * Manages reader input and pen objects
 * @author Edward Taylor
 * @version (02/12/2016)
 */

public class DrawAssist
{

    private LinkedList<String> command = new LinkedList<String>();
    private InputReader reader;
    private HashMap<String, Pen> pens  = new HashMap<>();
    private Pen selectedPen;
    private Canvas canvas; 
    private int parameterOne;
    private int parameterTwo;

    /**
     * Constructor for objects of class DrawAssist
     * @param width of canvas
     * @param height of canvas
     */

    public DrawAssist(int width, int height)
    {

        LinkedList<String> command = new LinkedList<String>();
        reader = new InputReader();
        pens = new HashMap<>();
        canvas = new Canvas("Drawing Program", width, height);
        parameterOne = 50;
        parameterTwo = 50;
        selectedPen = null;

    }

    /**
     * Allows DrawingTool to fill the LinkedList with commands 
     * using the reader
     */

    public void initialiseAssist()
    {

        command = reader.getInput();
    }

    /**
     * Allows DrawingTool to retrieve commands from the reader
     * @param position in LinkedList
     */

    public String getCommand(int position)
    {
        String word = command.get(position);
        return word;
    }

    /**
     * Returns currently selected pen
     */

    private Pen getPen()
    {

        return selectedPen;
    }

    /**
     * Creates a new pen and selects it for use unless a pen 
     * of that name currently exists in which case it will select 
     * that pen
     * @param name of the pen
     */

    public void createPen(String name)
    {

        if (pens.containsKey(name))
        {
            selectPen(name);
            System.out.println("Now using pen " + name);
            
            
        }
        else
        {
            Pen nextPen = new Pen(0,0, canvas);
            nextPen.setColor(Color.BLACK);
            nextPen.penDown();
            pens.put(name, nextPen);
            selectedPen = pens.get(name);

        }

    }

    /**
     * Selects a pen for use
     * @param name of the pen
     */

    public void selectPen(String name)
    {
        if (pens.containsKey(name)) 
        {
            selectedPen = pens.get(name);
            System.out.println("Now using pen " + name);

        }

        else 
        {
            invalidCommand(6);
        }
    }

    /**
     * Deletes a pen if it exists
     * @param name of the pen
     */

    public void deletePen(String name)
    {
        if (pens.containsKey(name)) 
        {
            pens.remove(name);
            selectedPen = null;
            System.out.println("" + name + " deleted");
            System.out.println("Select or create a new pen to continue drawing");

        }

        else
        {

            invalidCommand(6);
        }
    }

    /**
     * Lifts the selected pen from the canvas
     */

    public void upPen()
    {
        if (noPen())
        {
            invalidCommand(2);

        }
        
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        
        else
        {
            selectedPen.penUp();
        }
    }

    /**
     * Puts the selected pen onto the canvas
     */

    public void downPen()
    {
        if (noPen())
        {
            invalidCommand(2);

        }
        
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        
        else
        {

            selectedPen.penDown();

        }

    }

    /**
     * Moves the selected pen
     * @parameter the distance to be moved
     */

    public void movePen(String secondWord)
    {
        if (!isAnInteger(secondWord))
        {
            invalidCommand(4);

        }

        else if (noPen())
        {
            invalidCommand(2);

        }
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        else if (isAnInteger(secondWord))
        {
            parameterOne = reader.convertToInteger(secondWord);
            selectedPen.move(parameterOne);

        }

    }
    /**
     * Moves the selected pen to specified coordinates
     * @parameter the coordinates to be moved to
     */
    public void moveToPen(String secondWord, String thirdWord)
    { 

        if (!isAnInteger(secondWord) || !isAnInteger(thirdWord))
        {
            invalidCommand(4);

        }
        else if (noPen())
        {
            invalidCommand(2);

        }
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        else if (isAnInteger(secondWord) && isAnInteger(thirdWord))
        {
            parameterOne = reader.convertToInteger(secondWord);
            parameterTwo = reader.convertToInteger(thirdWord);
            selectedPen.moveTo(parameterOne, parameterTwo);

        }

    }
    /**
     * Turns the selected pen 
     * @parameter degrees to turn the pen
     */

    public void turnPen(String secondWord)
    {
        if (!isAnInteger(secondWord))
        {
            invalidCommand(4);

        }
        else if (noPen())
        {
            invalidCommand(2);

        }
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        else if (isAnInteger(secondWord))
        {
            parameterOne = reader.convertToInteger(secondWord);
            selectedPen.turn(parameterOne);

        }

        
    }
    /**
     * Turns the selected pen 
     * @parameter the angle to be turned to
     */

    public void turnToPen(String secondWord)
    {
        
        if (!isAnInteger(secondWord))
        {
            invalidCommand(4);

        }
        
        else if (noPen())
        {
            invalidCommand(2);

        }
        
        else if (selectedPen == null)
        {
            invalidCommand(3);

        }
        
        else if (isAnInteger(secondWord))
        {
            parameterOne = reader.convertToInteger(secondWord);
            selectedPen.turnTo(parameterOne);

        }

    }
    
    /**
     * Sets the color of the selected pen
     * @parameter the choice of colour
     */

    public void colourSet(int choice)
    {
        if (!isSize(2))
        {
            invalidCommand(9);
        }
        else if (noPen())
        {
            invalidCommand(2);

        }
        else if (selectedPen == null)
        {
            invalidCommand(3);
        }
        else
        {
            if(choice==1)
            {
                getPen().setColor(Color.RED);
            }
            if (choice == 2)
            {
                getPen().setColor(Color.BLUE);
            }
            if (choice == 3)
            {
                getPen().setColor(Color.GREEN);

            }
            if (choice == 4)
            {
                getPen().setColor(Color.BLACK);
            }
            if (choice ==5)
            {
                getPen().setColor(Color.MAGENTA);
            }
        }
    }
    /**
     * Returns true if there are no pens being stored
     */

    private boolean noPen()
    {

        if (pens.size() == 0)
        {

            return true;
        }

        else
        {
            return false;
        }
    }
    

    /**
     * Returns true if the size entered matches the size of the LinkedList
     * @parameter size you want to check against
     */

    public boolean isSize(int size)
    {
        if (command.size() == (size))
        {
            return true;
        }

        else 
        {
            return false;
        }

    }

    /**
     * Returns true if a string is a valid integer
     * @parameter word you want to check
     */

    private boolean isAnInteger(String word)
    {
        if (reader.isAnInteger(word))
        {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns true if there are no commands
     */

    public boolean isEmpty()
    {
        if (command.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prints invalid command responses
     * @parameter which command
     */

    public void invalidCommand(int choice)
    {

        if(choice==1)
        {
            System.out.println("Invalid Command");

        }

        if (choice == 2)
        {
            System.out.println("Create a pen first");

        }
        if (choice == 3)
        {
            System.out.println("Select a pen first");

        }

        if (choice == 4)
        {
            System.out.println("Parameter must be an integer");
        }

        if (choice == 5)
        {
            System.out.println("Valid commands contain 3 or fewer words");
        }

        if (choice == 6)
        {
            System.out.println("No such pen exists");
        }

        if (choice == 7)
        {
            System.out.println("Colour not recognised");
            System.out.println("Available Colours are blue, black");
            System.out.println(", magenta, red and green");
        }

        if (choice == 8)
        {
            System.out.println("First word not a valid command");
        }

        if (choice == 9)
        {
            System.out.println("Colour commands contain two words");
        }
        
        if (choice == 10)
        {
            System.out.println("Only the command moveto may contain three words");
        }

    }

    

    /**
     * Prints the help menu
     */

    public void printHelp()
    {

        System.out.println("Must create a pen before drawing operations can be performed");
        System.out.println("Typing pen (String name) will attempt to create and select a new pen of that name");
        System.out.println("If a pen of that name already exists it will select it for use");
        System.out.println("Typing delete (String name) will delete the pen of that name");
        System.out.println("Typing select (String name) will select the pen of that name");
        System.out.println("Typing up will raise the pen off the canvas");
        System.out.println("Typing down will put the pen back into contact with the canvas");
        System.out.println("Typing move (int) eg. move 50  will move the pen in the current direction by the specified integer");
        System.out.println("Typing moveto (int,int) eg moveto 50 50 will move the pen to the specified coordinates");
        System.out.println("Typing turn (int) eg turn 50 will turn the pen clockwise by the specified angle");
        System.out.println("Typing turnto (int) eg turnto 50 will turn to the specified direction (0 is right, 90 is down, 180 is left, 270 is up)");
        System.out.println("Typing colour(String colour) eg colour red will set a new colour. Available colours are: red, blue, magenta, green and black");
        System.out.println("Default value if no integer supplied will be 50");
        System.out.println("Default pen colour is black");
        System.out.println("All commands must have the correct number of words");
        System.out.println("Typing bye will end the session");

    }

}
