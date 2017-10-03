
/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String name;
    //
    private int age;

    /**
     * Constructor for objects of class Person
     */
    public Person(String myName, int myAge)
    
    {
        name= myName;
        age= myAge;
        // initialise instance variables
        
    }

    /**
     * return name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * change age
     */
    
    public void setAge (int amount)
    {
        age= amount;

    }
    /**
     * GET NAME
     */
    public void printDetails ()
    {
        System.out.println ("The name of this person is "+ name);


}
}