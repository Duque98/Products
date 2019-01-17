import java.util.ArrayList; 
/**
 * Write a description of class VipClient here.
 *
 * @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class VipClient extends Client
{
    /**
     * Constructor for objects of class VipClient
     */
    public VipClient()
    {
        super();
    }

    /**
     * Constructor Parametrized for objects of class VipClient
     * @param id The client's identifying number
     * @param name The client's name
     * @param age The client's age 
     * @param actualLocation The actual location of the client
     */

    public VipClient(Integer id, String name, Integer age, String actualLocation){
        super(id, name, age, actualLocation); 
        this.orderNumber = 1; 
    }

    /**
     * Go through favourite product map and try to find a given product.If it finds the correct product, post a comment in it and like it.
     * @param product The product to be commented
     */
    public void postComment(Product product){

        if(favouriteProducts.containsValue(product)){
            if(product instanceof FoodProduct){
                
            }else{
                product.postComment("I really like this product", this.name, 4);
                if(product instanceof HomeProduct){
                    HomeProduct hp = (HomeProduct)product;
                    hp.Like();
                    
                }              
            }
        }
    }

    
    /**
     * Go through the map of favouriteProduct of this client and each product is added to an ArrayList to prepare a order.
     * @return favouriteOrder   ArrayList of a order
     */
    @Override
    public ArrayList prepareOrder(){
        ArrayList favouriteOrder = new ArrayList<Product>(); 
        for(Product product : favouriteProducts.values()){
            favouriteOrder.add(product);
        }
        return favouriteOrder; 
    }

    /**
     * Calculate the total price of a order
     * @return totalPrice   The total price of the order
     */
    public Float getPriceOrder(ArrayList<Product> favouriteOrder){
        Float totalPrice = 0.0f; 
        for(Product product : favouriteOrder){
            totalPrice +=  this.orderNumber * (product.getPrice() + (product.getPrice()*product.getDiscount()));            
        }  
        return totalPrice; 
    }

    /**
     * Make a order to the stockManager
     * @param   favouriteOrder  ArrayList of the order
     */
    @Override
    public void makeOrder(ArrayList<Product> favouriteOrder){
        StockManager SM = StockManager.getInstance();
        this.moneySpent += this.getPriceOrder(favouriteOrder); 
        SM.makeVipOrder(favouriteOrder, this.orderNumber);
        
        for(Product product : favouriteOrder){
            postComment(product);
        }
    }

    
}