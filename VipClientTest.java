

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class VipClientTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VipClientTest
{
    /**
     * Default constructor for test class VipClientTest
     */
    public VipClientTest()
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
    public void testVipClient()
    {
        VipClient vipClien1 = new VipClient(1, "Pedro", 25, "Caceres");
        assertEquals(25, vipClien1.getAge());
        assertEquals(1, vipClien1.getId());
        assertEquals("Pedro", vipClien1.getName());
        assertEquals("Caceres", vipClien1.getActualLocation());
    }
    
    @Test
    public void testVipComment(){
        VipClient vC1 = new VipClient(2, "Juan", 25, "Caceres");
        VipClient vC2 = new VipClient(3, "Pepe", 25, "Caceres");
        Product p1 = new Product();
        assertEquals(0, p1.getCommentSet().size());
        vC1.addProductFavourite(p1, "pruebaVip");
        vC1.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        vC1.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        vC2.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        vC2.addProductFavourite(p1, "pruebaVip1");
        vC2.postComment(p1);
        assertEquals(2, p1.getCommentSet().size());
        
    }
    @Test
    public void testPrepareOrder(){
       VipClient vC3 = new VipClient(4, "Sara", 25, "Caceres");  
       Product p2 = new Product();
       vC3.addProductFavourite(p2, "p2Fav");
       ArrayList prueba1 = new ArrayList<Product>();
       prueba1 = vC3.prepareOrder();
       assertEquals(1, prueba1.size());
    }
    
    @Test 
    public void testGetPriceOrder(){
        VipClient vC4 = new VipClient(5, "Juana", 25, "Caceres");  
        Product p3 = new Product(6,"p3",100,10,10.0f);
        Product p4 = new Product(7,"p4",100,10,20.0f);
        Product p5 = new Product(8,"p5",100,10,30.8f);
        vC4.addProductFavourite(p3, "p3Fav");
        vC4.addProductFavourite(p4, "p4Fav");
        vC4.addProductFavourite(p5, "p5Fav");
        assertEquals(60.8f, vC4.getPriceOrder(vC4.prepareOrder()),0.001f);
        
        
    }
    @Test
    public void testMakeOrder(){
         VipClient vC5 = new VipClient(6, "Pepa", 25, "Caceres");  
        Product p3 = new Product(6,"p3",100,10,10.0f);
        Product p4 = new Product(7,"p4",100,10,20.0f);
        Product p5 = new Product(8,"p5",100,10,30.8f);
        vC5.addProductFavourite(p3, "p3Fav");
        vC5.addProductFavourite(p4, "p4Fav");
        vC5.addProductFavourite(p5, "p5Fav");
        
        vC5.makeOrder(vC5.prepareOrder());
        StockManager SM = StockManager.getInstance();
        
        assertEquals(3,SM.getOrderList().size());
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

