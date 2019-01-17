/**
 * Demonstrate the StockManager and Product classes.
 * The demonstration becomes properly functional as
 * the StockManager class is completed.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class StockDemo
{
    // The stock manager.
    private StockManager sww;
    /**
     * Create a StockManager and populate it with a few
     * sample products.
     */
    public StockDemo()
    {
        sww = sww.getInstance();
    }
    
    public StockManager getManager()
    {
        return sww;
    }
   
}
