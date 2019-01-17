import java.util.Collection;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Iterator; 
import java.util.ArrayList;
import java.util.*; 
/**
 * Write a description of class ClientStandar here.
 *
 * @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class StandardClient extends Client
{

    /**
     * Constructor for objects of class StandardClient
     */
    public StandardClient()
    {
        super();
        this.orderNumber = 50; 
    }

    /**
     * Constructor Parametrized for objects of class Client
     * @param name The client's name
     * @param id The client's identifying number
     * @param age The client's age 
     * @param actualLocation The actual location of the client
     */
    public StandardClient(Integer id, String name, Integer age,String actualLocation){
        super(id, name, age, actualLocation);  
        this.orderNumber = 50; 

    }

    /**Go through the map of favouriteProduct of this client and add to order the two more expensive products.
     * @return favouriteOrder   ArrayList of a order
     */
    @Override
    public ArrayList prepareOrder(){
        ArrayList aux = new ArrayList<Product>(); 
        ArrayList favouriteOrder = new ArrayList<Product>(); 
        for(Product product : favouriteProducts.values()){
            aux.add(product); 

        }

        Collections.sort(aux, Collections.reverseOrder(new PriceComparator())); 
        favouriteOrder.add(aux.get(0)); 
        try{
            favouriteOrder.add(aux.get(1)); 
        }catch (IndexOutOfBoundsException e){
      
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
            totalPrice += this.orderNumber*( product.getPrice() + (product.getPrice()*(product.getDiscount())));
        }
        return totalPrice;
    }

    /**
     * Make a order to the stockManager
     * @param   favouriteOrder  ArrayList of the order
     */
    @Override
    public void makeOrder(ArrayList<Product> favouriteOrder){
        StockManager SM=StockManager.getInstance();
        for(Product product : favouriteOrder){
            SM.makeStandardOrder(product, this.orderNumber);
           // increaseOrderNumber(); 
            postComment(product);
        }   

        this.moneySpent += getPriceOrder(favouriteOrder); 
    }

    /**
     * Post a comment in determined products 
     *
     * @param   namebyClient A name given by the client to the product
     * @param   comment Comment to be posted
     * @param   points Points to rive [Range 1-5]
     *       
     */
    public void postComment(Product product){

        if(favouriteProducts.containsValue(product)){
            if(product instanceof FoodProduct){
            }else{                               
                checkPoint(product); 
            }
        }
    }

    private void checkPoint(Product product){

        Integer point = (product.name.length()%5)+1;
        String comment = StockManager.getInstance().getDefaultComments(point-1);

        if(product instanceof HomeProduct){
            HomeProduct hp = (HomeProduct)product;
            if(point >= 4){            
                hp.Like();
            }else if(point <= 2){
                hp.Unlike(); 
            }
        }
        product.postComment(comment, this.name, point);

    }

}
