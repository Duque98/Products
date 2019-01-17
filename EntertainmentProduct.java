
/**
 * Write a description of class LeisureProduct here.
 *
 * @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class EntertainmentProduct extends Product
{
    /**
     * Constructor for objects of class LeisureProduct
     */
    public EntertainmentProduct()
    {
        super();
    }

    /**
     * Parametrized constructor for objects of class LeisureProduct
     */
    public EntertainmentProduct(Integer id, String name, Integer quantity, Integer stockMin, Float price)
    {
        super(id,name,quantity,stockMin, price);
        this.discount=0.2f;
    }

}
