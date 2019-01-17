import java.util.Collection;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Iterator; 
import java.util.Objects; 
import java.util.ArrayList; 
/**
 * Write a description of class Client here.
 *
 * @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class Client
{
    // instance variables - replace the example below with your own

    protected String name;
    protected Integer id;
    protected Integer age;
    protected String actualLocation;
    protected Map<String, Product> favouriteProducts; 
    protected Float moneySpent; 
    protected Integer orderNumber; 

    /**
     * Constructor for objects of class Client
     */
    public Client()
    {
        this.name="";
        this.id=0;
        this.age=0;
        this.actualLocation="";
        this.favouriteProducts = new HashMap<>(); 
        this.moneySpent= 0.0f; 
        this.orderNumber = 0; 

    }

    /**
     * Constructor Parametrized for objects of class Client
     * @param name The client's name
     * @param id The client's identifying number
     * @param age The client's age 
     * @param actualLocation The actual location of the client
     */
    public Client(Integer id, String name, Integer age, String actualLocation){
        this.name=name;
        this.id=id;
        this.age=age;
        this.actualLocation=actualLocation;
        this.favouriteProducts = new HashMap<>(); 
        this.moneySpent= 0.0f; 
        this.orderNumber = 0; 

    }

    /**
     * Add a favourite product to the map 
     *
     * @param  product The product who will be added
     * @param namebyClient The name that the client put to the product
     */
    public void addProductFavourite(Product product, String namebyClient)
    {

        if(!favouriteProducts.containsKey(namebyClient)){
            favouriteProducts.put(namebyClient, product);   
        }

    }

    /**
     * Delete a favourite product to the map 
     *
     * @param  namebyClient The name of the product that you will remove
     */
    public void deleteProductFavourite(String namebyClient)
    {
        if(favouriteProducts.containsKey(namebyClient)){
            favouriteProducts.remove(namebyClient);   
        }

    }

   
    /**
     * Find a product in the map of favourite products
     *
     * @param  product The product that you want to find
     * @return aux  Boolean to determinate if a product is in the map of favourite
     */
    public boolean findProductFavourite(Product product){
        boolean aux=false;
        for(Map.Entry<String, Product> entry : favouriteProducts.entrySet()){
            if(favouriteProducts.containsValue(product)){
                aux=true;
            }
        }
      
        return aux;
    }

    /**
     * Get name
     *
     * @return    name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Get Id
     * 
     * @return    id
     */ 
    public int getId(){
        return this.id;
    }

    /**
     * Get Age
     *
     * @return    age
     */
    public int getAge(){
        return this.age;
    }
 /**
     * Get moneySpent
     *
     * @return   moneySpent
     */
    public Float getMoneySpent(){
        return this.moneySpent;
    }
    /**
     * Get ActualLocation
     * 
     * @return    actualLocation
     */
    public String getActualLocation(){
        return this.actualLocation;
    }

    /**
     * Set Name
     *
     * @param  name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * Set Id
     *
     * @param  id
     */ 
    public void setId(int id){
        this.id=id;
    }

    /**
     * Set Age
     *
     * @param  age
     */
    public void setAge(int age){
        this.age=age;
    }

    /**
     * Set ActualLocation
     *
     * @param   actualLocation
     */
    public void setActualLocation(String actualLocation){
        this.actualLocation=actualLocation;
    }
    
        /**
     * Get orderNumber
     *
     * @return    orderNumber
     */
    public Integer getOrderNumber(){
        return this.orderNumber;
    }
    /**
     * Get the map of favouriteProduct
     * @return favouriteProduct Map of favouriteProducts
     */
    public Map getFavouriteProduct(){
        return this.favouriteProducts;
    }
    /**
     * @return String that "textually represents" this object.
     * 
     */
    public String toString(){
        return "Id: " + this.id + " Name: "+ this.name + " Age: " + this.age +
                " City: " + this.actualLocation;
    }
    /**
     * Show every product in the favourite list of the client
     * 
     */
    public void showProducts(){
        System.out.println("The list of favourite products: ");
        for(Map.Entry<String, Product> entry : favouriteProducts.entrySet()){

            System.out.println(entry.toString()); 
        }

    }

    
    /**
     * Post a comment in determined products 
     *
     * @param   namebyClient A name given by the client to the product
     * @param   comment Comment to be posted
     * @param   points Integer to give [Range 1-5]
     *       
     */
    public void postComment(String namebyClient, String comment, Integer points){
        
        if(favouriteProducts.containsKey(namebyClient)){
            Product product = new Product(); 
            product = findProductbyName(namebyClient);
            
            product.postComment(comment, this.name, points);
            
        }
    }
    /**
     * Find a Product by a name given by the client
     *
     * @param namebyClient A name given by the client to the product
     * @return product  Product that has founded
     */
    public Product findProductbyName(String namebyClient){
        Product product = new Product(); 
        for (Map.Entry<String, Product> entry : favouriteProducts.entrySet()){
            if(entry.getKey()==namebyClient){
                product = entry.getValue();
            }
        }
        return product; 
    }
/**
   
     *
     * @param   favouriteOrder  ArrayList of favouriteProducts    
     */
    public void makeOrder(ArrayList<Product> favouriteOrder){

    }
/**
     * Make a order to the Store Manager of a product found in the Favourite List of the client. 
     * The quantity of the product is 1 of each. 
     *
     * @param   SM The Stock Manager who sells products  
     */
    public void makeOrderFavourites(StockManager SM){
        
        SM.getInstance(); 
        HashMap favouriteOrder = new HashMap<Product, Integer>(); 
        for(Map.Entry<String, Product> entry : favouriteProducts.entrySet()){            
            favouriteOrder.put(entry.getValue(), 1);             
        }
        SM.favouriteOrder(favouriteOrder);

    }
    
    /**
     * @return Hashcode value for the object
     */
    @Override
    public int hashCode(){
      return Objects.hash(this.id, this.name); 
    }
    
    /**
     * Compares this string to the specified object. The result is true if and only if the argument is not null and is a String object that represents the same sequence of characters as this object.
     * @return true if the given object represents a String equivalent to this string, false otherwise 
     */
    @Override
    public boolean equals(Object obj) {
        Client client = (Client) obj; 
     return (client.getName().equals(this.getName()) && client.getId() == (this.getId()));
        
    }
    
    /**
     * @return null
     */
    public ArrayList prepareOrder(){
        return null; 
    }
    
    /**
     * Increase the orderNumber
     */
    protected void increaseOrderNumber(){
        this.orderNumber++; 
    }
    
    /**
     * @return favouriteProduct Map of the favouriteProduct
     */
    public Map getFavouriteProductList(){
        return this.favouriteProducts; 
        
    }
}
