

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EntertainmentProductTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EntertainmentProductTest
{
    /**
     * Default constructor for test class EntertainmentProductTest
     */
    public EntertainmentProductTest()
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
    }
 @Test
    public void testEntertainmentProduct()
    {
        EntertainmentProduct product1 = new EntertainmentProduct(1, "Milk", 10, 2, 100.0f);
        assertEquals(1, (int)new Integer(product1.getID()));
        assertEquals("Milk", product1.getName());
        assertEquals(100.0f, (float)new Float(product1.getPrice()), 0.1f);
        assertEquals(10, (int)new Integer(product1.getQuantity()));
        assertEquals(2, (int)new Integer(product1.getStock()));
        assertEquals(0, (int)new Integer(product1.getNumberOfComments()));
    }
    
    @Test
    public void testLike()
    {
        HomeProduct product1 = new HomeProduct(1, "Milk", 10, 2, 100.0f, "kitchen");
        assertEquals(0, (int)new Integer(product1.getLikes()));
        product1.Like(); 
        assertEquals(1, (int)new Integer(product1.getLikes()));
    }
    @Test
    public void testUnlike()
    {
        HomeProduct product1 = new HomeProduct(1, "Milk", 10, 2, 100.0f, "kitchen");
        assertEquals(0, (int)new Integer(product1.getUnlikes()));
        product1.Unlike(); 
        assertEquals(1, (int)new Integer(product1.getUnlikes()));
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
