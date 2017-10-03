import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StockManager
{
    // A list of the products.
    private ArrayList<Product> stock;

    /**
     * Initialise the stock manager.
     */
    public StockManager()
    {
        stock = new ArrayList<>();
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {

        stock.add(item);
    }

    public void printProductDetails()
    {
        for (Product product : stock)
        {

            String printProduct = product.toString();
            System.out.println(printProduct);
        }

    }

    public Product findProduct(int id)
    {
        for (Product product : stock)
        {
            if(product.getID() == id)
            {
                return product;
            }}

        return null;
    }

    public int numberInStock(int id)
    {if (id <= 0)
        {
            return 0;}

        if 
        (id == findProduct(id).getID())
        {
            int stockLevel = findProduct(id).getQuantity();
            return stockLevel;

        }

        return 0;
    }

    public void delivery(int amount, int id)
    {
        if (id == findProduct(id).getID())
        {
            findProduct(id).increaseQuantity(amount);

        }

    }

    public void lowStock(int stockSearch)

    {
        for (Product product : stock)
        {if (product.getQuantity() <= stockSearch)
                System.out.println(product.toString());
        }
    }

    public Product findProduct(String name)
    {
        boolean finished = false;
        while(!finished){
            for (Product product : stock)
            {String b1 = product.getName();
                String b2 = name;
                if (b1.equals(b2)){

                    finished = true;
                    return product;
                }}}
        return null;
    }

}

