

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class StandardClientTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StandardClientTest
{
    /**
     * Default constructor for test class StandardClientTest
     */
    public StandardClientTest()
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
    public void testStandardClient()
    {
        StandardClient standard1 = new StandardClient(1, "Pedro", 25, "Caceres");
        assertEquals(25, standard1.getAge());
        assertEquals("Caceres", standard1.getActualLocation());
        assertEquals(1, standard1.getId());
        assertEquals("Pedro", standard1.getName());
    }
     
    @Test
    public void testStandardComment(){
        StandardClient sC1 = new StandardClient(12, "Juan", 25, "Caceres");
        StandardClient sC2 = new StandardClient(13, "Pepe", 25, "Caceres");
        Product p1 = new Product();
        assertEquals(0, p1.getCommentSet().size());
        sC1.addProductFavourite(p1, "pruebaStandard");
        sC1.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        sC1.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        sC2.postComment(p1);
        assertEquals(1, p1.getCommentSet().size());
        sC2.addProductFavourite(p1, "pruebaStandard1");
        sC2.postComment(p1);
        assertEquals(2, p1.getCommentSet().size());
        
    }
    
    @Test
    public void testPrepareOrder(){
       StandardClient vC3 = new StandardClient(14, "Sara", 25, "Caceres");  
       Product p2 = new Product();
       vC3.addProductFavourite(p2, "p2Fav");
       ArrayList prueba1 = new ArrayList<Product>();
       prueba1 = vC3.prepareOrder();
       assertEquals(1, prueba1.size());
    }
    
    @Test 
    public void testGetPriceOrder(){
        StandardClient vC4 = new StandardClient(15, "Juana", 25, "Caceres");  
        Product p3 = new Product(6,"p3",100,10,10.0f);
        Product p4 = new Product(7,"p4",100,10,20.0f);
        Product p5 = new Product(8,"p5",100,10,30.8f);
        vC4.addProductFavourite(p3, "p3Fav");
        vC4.addProductFavourite(p4, "p4Fav");
        vC4.addProductFavourite(p5, "p5Fav");
        assertEquals(2540.0f, vC4.getPriceOrder(vC4.prepareOrder()),0.001f);
        
        
    }
    
    @Test
    public void testMakeOrder(){
        StandardClient vC5 = new StandardClient(16, "Pepa", 25, "Caceres");  
        Product p3 = new Product(6,"p3",100,10,10.0f);
        Product p4 = new Product(7,"p4",100,10,20.0f);
        Product p5 = new Product(8,"p5",100,10,30.8f);
        vC5.addProductFavourite(p3, "p3Fav");
        vC5.addProductFavourite(p4, "p4Fav");
        vC5.addProductFavourite(p5, "p5Fav");
        
        vC5.makeOrder(vC5.prepareOrder());
        StockManager SM = StockManager.getInstance();
        
        assertEquals(2,SM.getOrderList().size());
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

