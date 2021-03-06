

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClientTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClientTest
{
    private Client client1;
    private HomeProduct homeProd1;
    private FoodProduct foodProd1;
    private EntertainmentProduct entertai1;
     private EntertainmentProduct entertai2;

    /**
     * Default constructor for test class ClientTest
     */
    public ClientTest()
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
        client1 = new Client(1, "Pedro", 25, "Cáceres");
        homeProd1 = new HomeProduct(1, "homeprod", 5, 2, 10.0f, "kitchen");
        foodProd1 = new FoodProduct(2, "foodprod", 5, 2, 20.0f, "december");
        entertai1 = new EntertainmentProduct(3, "enprod", 5, 2, 30.0f);
        entertai2 = new EntertainmentProduct(4, "enprod2", 5, 2, 40.0f);
        client1.addProductFavourite(homeProd1, "hp");
        client1.addProductFavourite(foodProd1, "fp");
        client1.addProductFavourite(entertai1, "ep");
        client1.addProductFavourite(entertai2, "ep2"); 
        client1.postComment("ep", "good", 5);
    }
    
    @Test
    public void testClient(){
        Client client2 = new Client(1, "Pedro", 25, "Caceres");
        assertEquals(25, client2.getAge());
        assertEquals(1, client2.getId());
        assertEquals("Pedro", client2.getName());
        assertEquals("Caceres", client2.getActualLocation()); 
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

    @Test
    public void testAddProduct()
    {
        client1.addProductFavourite(entertai2, "ep2");
        assertEquals(entertai2, client1.getFavouriteProductList().get("ep2")); 
    }
    
    @Test
    public void testDeleteProduct()
    {
        client1.deleteProductFavourite("ep2");
        assertEquals(null, client1.getFavouriteProductList().get("ep2")); 
    }
    @Test
    public void testFindProductByName()
    {
        Product p = client1.findProductbyName("ep");
        assertEquals(p, client1.getFavouriteProductList().get("ep")); 
    }
    
        @Test
    public void testFindProduct()
    { 
        assertEquals(client1.findProductFavourite(homeProd1), 
        client1.getFavouriteProductList().containsValue(homeProd1)); 
    }
    
                @Test
    public void testPostComment()
    { 

        assertEquals(1, (int)entertai1.getNumberOfComments()); 
        assertEquals(0, (int)homeProd1.getNumberOfComments()); 
        assertEquals(1, entertai1.getCommentSet().size());
    }
}

