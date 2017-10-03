/**
 * A taxi.
 * Taxis have a unique ID, a location and sometimes a destination.
 * They are either free or occupied.
 * 
 * @author Edward Taylor based on original code by David J. Barnes 
 * @version 16/01/17
 */
public class Taxi extends Vehicle
{

    // Whether it is free or not.
    private boolean free;

    /**
     * Constructor for objects of class Taxi.
     * @param base The name of the company's base.
     * @param id This taxi's unique id.
     */
    public Taxi(String base, String id)
    {

        super(id);
        setLocation(base);

        free = true;

    }

    /**
     * Book this taxi to the given destination.
     * The status of the taxi will no longer be free.
     * @param destination The taxi's destination.
     */
    public void book(String destination)
    {
        setDestination(destination);
        free = false;
    }

    /**
     * Print the status of this taxi to the terminal.
     * 
     */
    public void getStatus()
    {
        System.out.println("" + getID() + " at " + getLocation() +
            getDestination());
    }

    
    
    /**
     * Indicate that this taxi has arrived at its destination.
     * As a result, it will be free.
     */
    public void arrived()
    {
        setLocation(getDestination());
        setDestination(null);
        free = true;
    }
}
