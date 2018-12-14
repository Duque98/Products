

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StockManagerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StockManagerTest
{
    private StockManager stockMan1;
    private Client client1;
    private Client client2;
    private Product p1;
    private Product p2;


    /**
     * Default constructor for test class StockManagerTest
     */
    public StockManagerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        stockMan1 = StockManager.getInstance();
        Client client = new Client(); 
        Client client1 = new Client();
        Product p1 = new Product(); 
        Product p2 = new Product(); 
    }

    @Test
    public void testStockManager(){
     
        assertEquals(stockMan1, stockMan1.getInstance()); 
    }
    
        @Test
    public void testAddClient(){
        
        assertEquals(0, stockMan1.getClientList().size()); 
        stockMan1.addClient(client1);
        assertEquals(1, stockMan1.getClientList().size()); 
    }
    
            @Test
    public void testDeleteClient(){
        
        assertEquals(1, stockMan1.getClientList().size()); 
        stockMan1.deleteClient(client1);
        assertEquals(0, stockMan1.getClientList().size()); 
    }
    
            @Test
    public void testAddProduct(){
        
        assertEquals(0, stockMan1.getStockList().size()); 
        stockMan1.addProduct(p1);
        assertEquals(1, stockMan1.getStockList().size()); 
    }

    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
