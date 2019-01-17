
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

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
        Product p1 = new Product(1,"p1",100,10,10.0f); 
        Product p2 = new Product(2,"p2",100,10,10.0f); 
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
    
      @Test
    public void testDeleteProduct(){

        assertEquals(1, stockMan1.getStockList().size()); 
        stockMan1.deleteProduct(p1);
        assertEquals(0, stockMan1.getStockList().size()); 
    }
    
/*Aqui da error*/
    @Test
    public void testAddToOrder(){
        Product p3 = new Product(19,"p3",100,10,10.0f);
        Client c1 = new Client();
        ArrayList prueba = new ArrayList<Product>();
        stockMan1.addProduct(p3);
        c1.addProductFavourite(p3, "p3Fav");
        prueba = c1.prepareOrder();
        c1.makeOrder(prueba);
    
        assertEquals(1,stockMan1.getOrderList().size());
    }
    
    @Test
    public void testDelivery(){
        Product p4 = new Product(18,"p4",100,10,10.0f);
        stockMan1.addProduct(p4);
        
        assertEquals(Integer.valueOf(100), p4.getQuantity());
        
        stockMan1.delivery(p4, 50);
        
        assertEquals(Integer.valueOf(150), p4.getQuantity());
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
