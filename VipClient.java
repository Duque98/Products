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

    }

    /**
     * Go through favourite product map and try to find a given product.If it finds the correct product, post a comment in it and like it.
     * @param product The product to be commented
     */
    public void PostComment(Product product){

        if(favouriteProducts.containsValue(product)){
            if(product instanceof FoodProduct){
                
            }else{
                product.PostComment("I really like this product", this.name, 4);
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
    public ArrayList PrepareOrder(){

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
    public Float GetPriceOrder(ArrayList<Product> favouriteOrder){
        Float totalPrice = 0.0f; 
        for(Product product : favouriteOrder){
            totalPrice += product.getPrice() + (product.getPrice()*product.getDiscount());            
        }  
        return totalPrice; 
    }

    /**
     * Make a order to the stockManager
     * @param   favouriteOrder  ArrayList of the order
     */
    @Override
    public void MakeOrder(ArrayList<Product> favouriteOrder){
        StockManager SM = StockManager.getInstance();
        this.moneySpent += this.GetPriceOrder(favouriteOrder); 
        SM.MakeVipOrder(favouriteOrder);
        for(Product product : favouriteOrder){
            PostComment(product);
        }
        IncreaseOrderNumber(); 
    }

    
}