
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
        
    }

    @Test
    public void testStockManager(){
        assertEquals(stockMan1, stockMan1.getInstance()); 
    }

    @Test
    public void testAddClient(){
        Client client1 = new Client();
        assertEquals(0, stockMan1.getClientList().size()); 
        stockMan1.addClient(client1);
        assertEquals(1, stockMan1.getClientList().size()); 
    }

    @Test
    public void testDeleteClient(){
        client1 = new Client(); 
        stockMan1.getClientList().add(client1);
        assertEquals(1, stockMan1.getClientList().size()); 
        stockMan1.deleteClient(client1);
        assertEquals(0, stockMan1.getClientList().size()); 
    }

    @Test
    public void testAddProduct(){
        Product p1 = new Product(1,"p1",100,10,10.0f); 
        assertEquals(0, stockMan1.getStockList().size()); 
        stockMan1.addProduct(p1);
        assertEquals(1, stockMan1.getStockList().size()); 
    }
    

    
    @Test
    public void testDelivery(){
        Product p4 = new Product(18,"p4",100,50,10.0f);
        stockMan1.addProduct(p4);
        client1 = new StandardClient(25, "Pepe", 25, "Caceres"); 
        client1.addProductFavourite(p4, "p4fav");
        
        assertEquals(Integer.valueOf(100), p4.getQuantity());
        
        client1.makeOrder(client1.prepareOrder());
             
        assertEquals(Integer.valueOf(50), p4.getQuantity());
        
        client1.makeOrder(client1.prepareOrder());
        
        assertEquals(Integer.valueOf(51), p4.getQuantity());
    }
    
    @Test
    public void testDeleteProduct(){
        Product p1 = new Product(); 
        stockMan1.getStockList().add(p1);
        assertEquals(1, stockMan1.getStockList().size()); 
        stockMan1.deleteProduct(p1);
        assertEquals(0, stockMan1.getStockList().size()); 
    }
    
    @Test
    public void testAddToOrder(){
        Client client1 = new VipClient();
        Product p3 = new Product(19,"p3",100,10,10.0f);
        ArrayList prueba = new ArrayList<Product>();
        
        stockMan1.addProduct(p3);
        client1.addProductFavourite(p3, "p3Fav");
        prueba = client1.prepareOrder();
        client1.makeOrder(prueba);
    
        assertEquals(1,stockMan1.getOrderList().size());
    }
    
    @Test
    public void testFindProduct(){
        Product p1 = new Product(1,"p1",100,10,10.0f);
        stockMan1.getStockList().add(p1);
        assertEquals(p1, stockMan1.findProduct(p1));
    }
    
    
        @Test
    public void testNumberInStock(){
        Product p1 = new Product(1,"p1",100,10,10.0f);
        Product p2 = new Product(2,"p1",0,10,10.0f);
        stockMan1.getStockList().add(p1);
        assertEquals(100, stockMan1.numberInStock(p1));
        assertEquals(0, stockMan1.numberInStock(p2));
    }
    
    
    @Test
    public void testGetMostCommented(){
        Product p1 = new Product(1,"p1",100,10,10.0f);
        Product p2 = new Product(2,"p1",100,10,10.0f);
        stockMan1.getStockList().add(p1);
        stockMan1.getStockList().add(p2);
        p1.postComment("", "", 1);
        p1.postComment("", "", 1);
        p2.postComment("", "", 1);
        
        assertEquals(p1, stockMan1.getMostCommented());
    }
    
    @Test
    public void testGetBestClient(){
        Product p1 = new Product(1,"p1",100,10,10.0f);
        Product p2 = new Product(2,"p1",100,10,100.0f);
        stockMan1.getStockList().add(p1);
        stockMan1.getStockList().add(p2);
        
        Client client1 = new VipClient(1, "Pedro", 25, "Caceres"); 
        Client client2 = new VipClient(2, "Maria", 25, "Caceres"); 
        stockMan1.getClientList().add(client1);
         stockMan1.getClientList().add(client2);
        client1.addProductFavourite(p1,"p1fav");
        client2.addProductFavourite(p2,"p1fav");
        
        client1.makeOrder(client1.prepareOrder());
        client2.makeOrder(client2.prepareOrder());
        
        assertEquals(client2, stockMan1.getBestClient());
        
        Client client3 = new StandardClient(3, "Pedro", 25, "Caceres"); 
        Client client4 = new StandardClient(4, "Maria", 25, "Caceres");
        client3.addProductFavourite(p1,"p1fav");
        client4.addProductFavourite(p2,"p1fav");
        stockMan1.getClientList().add(client3);
         stockMan1.getClientList().add(client4);
        
        client3.makeOrder(client3.prepareOrder());
        client4.makeOrder(client4.prepareOrder());
        
        assertEquals(client4, stockMan1.getBestClient());
    }
    
    
    @Test 
    public void testMostSold(){
        
        Product p1 = new Product(1,"p1",100,10,10.0f);
        Product p2 = new Product(2,"p1",100,10,100.0f);
        stockMan1.getStockList().add(p1);
        stockMan1.getStockList().add(p2);
        
        Client client1 = new VipClient(1, "Pedro", 25, "Caceres"); 
        Client client2 = new StandardClient(2, "Maria", 25, "Caceres"); 
        stockMan1.getClientList().add(client1);
        stockMan1.getClientList().add(client2);
        client1.addProductFavourite(p1,"p1fav");
        client2.addProductFavourite(p2,"p1fav");
        
        client1.makeOrder(client1.prepareOrder());
        client2.makeOrder(client2.prepareOrder());   
        
        assertEquals(p2, stockMan1.getMostSold()); 
        assertNotEquals(p1, stockMan1.getMostSold()); 
        
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        stockMan1.getClientList().clear(); 
        stockMan1.getOrderList().clear(); 
        stockMan1.getStockList().clear(); 
    }
}
