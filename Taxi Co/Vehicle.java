
/**
 * The superclass of all vehicles for TaxiCo
 * contains common fields and methods for all vehicles
 * 
 * @author Edward Taylor based on original code by David J Barnes
 * @version 11/01/17
 */
public class Vehicle
{
    // instance variables - replace the example below with your own
    private String id;
    private String destination;
    private String location;

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle(String id)
    {
        // initialise instance variables
        this.id = id;
        destination = null;
    }

    /**
     * Return the ID of the taxi.
     * @return The ID of the taxi.
     */
    public String getID()
    {
        return id;
    }

    /**
     * Return the destination of the taxi.
     * @return The destination of the taxi.
     */
    public String getDestination()
    {
        if (destination == null){
            return " This Taxi is Currently Available";
        }
        else {
            return " headed for " + destination;
        }

    }
    /**
     * Check if the provided destination matches this shuttle's
     * @param destination the destination to be checked
     * @return true if supplied destination matches
     */
    public Boolean getDestinationShuttle(String destination)
    {
        if (this.destination.equals(destination)){
            return true;
        }
        else {
            return false;
        }
        
    }

    /**
     * Set the intented destination of the taxi.
     * @param destination The intended destination.
     */
    public void setDestination(String destination)
    {
        this.destination = destination;
    }

    /**
     * Return the location of the vehicle.
     * @return The location of the vehicle.
     */
    public String getLocation()
    {
        return location;
    }
    /**
     * Set the location of the vehicle
     * @param the new location of the vehicle
     */

    public void setLocation(String location)
    {
        this.location = location;
    }
    /**
     * Test to see which type of vehicle this is
     * and print according status information 
     * @param the vehicle to be checked
     */

    public void identify(Vehicle whatAmI)
    {
        if(isTaxi(whatAmI)){
            Taxi taxi = (Taxi) whatAmI;
            taxi.getStatus();
        }
        else
        {
            Shuttle shuttle = (Shuttle) whatAmI;
            shuttle.getStatus();
        }

    }
    
    /**
     * Boolean test of whether a vehicle is a taxi or not
     * @param the vehicle to be checked
     * @return true if vehicle is a taxi
     */

    public boolean isTaxi(Vehicle whatAmI)
    { 
        if (whatAmI instanceof Taxi){
            return true;
        }
        else {
            return false;
        }

    }
    /**
     * Check to see if a vehicle has a destination
     * @param the vehicle to be checked
     * @return true if already has a destination
     */

    public boolean hasDestination(Vehicle vehicle)
    {
        if (destination == null) {
            return false;
        }

            else {
                return true;
            }

        }
    }
