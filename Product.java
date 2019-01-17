import java.util.Set; 
import java.util.HashSet; 
import java.util.Objects; 
import java.util.Iterator; 
/**
 * Model some details of a product sold by a company.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Product
{
    // An identifying number for this product.
    protected Integer id;
    // The name of this product.
    protected String name;
    // The quantity of this product in stock.
    protected Integer quantity;
    // A Set of Comments 
    protected Set<Comments> commentsList; 
    //Stock of product
    protected Integer stockMin;
    //Price of product
    protected Float price;
    //Discount of product
    protected Float discount;
    /**
     * Constructor for objects of class Product.
     * The initial stock quantity is zero.
     * 
     * 
     */
    public Product()
    {
        this.id = 0;
        this.name = "";
        this.quantity = 0;
        this.commentsList = new HashSet<Comments>(); 
        this.stockMin=0;        
        this.price=0.0f;
        this.discount=0.0f;
    }

    /**
     * Parametrized Constructor for objects of class Product.
     * 
     * @param id The product's identifying number.
     * @param name The product's name.
     * @param quantity  The product's quantity
     * @param stockMin  The minimun stock of the product
     * @param price The product's price
     */
    public Product(Integer id, String name, Integer quantity, Integer stockMin, Float price)
    {
        if(id<0){
            id=id*(-1);
        }
        if(quantity<0){
            quantity=quantity*(-1);
        }

        if(stockMin<0){
            stockMin=stockMin*(-1);
        }
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.commentsList = new HashSet<Comments>(); 
        this.stockMin=stockMin;
        this.price=price;

        this.discount=0.0f;

    }

    /**
     * Get id
     * @return The product's id.
     */
    public Integer getID()
    {
        return id;
    }

    /**
     * Get name
     * @return The product's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get quantity
     * @return The quantity in stock.
     */
    public Integer getQuantity()
    {
        return this.quantity;
    }

    /**
     * Get the minimun stock
     * @return The stock Min.
     */
    public Integer getStock(){
        return this.stockMin;
    }

    /**
     * Get the price
     * @return The price of the product.
     */
    public Float getPrice(){
        return this.price;
    }

    /**
     * Get the discount
     * @return The discount of the product.
     */
    public Float getDiscount(){
        return this.discount;
    }


    /**Return the set of comments
     * return commentsList  Set of comments
     */
    public Set getComments(){
        return this.commentsList;   
    }

    /**
     * @return String that "textually represents" this object.
     */
    public String toString()
    {

        return "Id: "+ this.id + " Name: " +
        this.name +
        " Stock level: " + this.quantity + " Min Quantity: " + this.stockMin +" Price: " + this.price;

    }

    /**
     * Restock with the given amount of this product.
     * The current quantity is incremented by the given amount.
     * @param amount The number of new items added to the stock.
     *               This must be greater than zero.
     */
    public void increaseQuantity(Integer amount)
    {
        if(amount > 0) {
            quantity += amount;
        }
    }

    /**
     * Sell one of these products.
     */
    public void sellOne()
    {
        if(quantity > 0) {
            quantity--;
        }
    }

    /**
     * Sell the list of order .
     * @param Integer OrderQuantity The quantity of a product the client want to order
     */
    public void sellOrder(Integer OrderQuantity)
    {
        if(quantity >= OrderQuantity) {
            quantity = quantity - OrderQuantity;

        }
    }

    /**
     * Post a comment in a product.
     * @param Integer OrderQuantity The quantity of a product the client want to order.
     * @param String nameClient The name of the client who want to comment.
     */
    public void postComment(String comment, String nameClient, Integer points){

        if(!hasCommented(nameClient)){

            Comments newComment = new Comments(comment, nameClient, points);
            commentsList.add(newComment);

        }
    }

    /**
     * 
     * @param String nameClient The name of the client who want to coment
     * @return True if the client has commented
     * 
     */
    public boolean hasCommented(String nameClient){
        boolean aux = false; 
        Iterator<Comments> it = commentsList.iterator(); 
        while(it.hasNext() && !aux){
            Comments c = it.next();
            if(c.getNameClient().equals(nameClient)){
                aux=true;       
            }            
        }

        return aux; 
    }

    /**
     * Show all the comments that a product have.
     *  
     */
    public void showComments(){

        for (Comments comments : commentsList){
            comments.showComment();             
        }

    }

    /**
     * @return The size of the set of comments
     */
    public Integer getNumberOfComments(){
        return commentsList.size(); 
    }

    /**
     * @return Hashcode value for the object
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name); 
    }

    /**
     * Compares this product to the specified object. The result is true if and only if the argument is not null and is a String object that represents the same sequence of characters as this object.
     * @return true if the given object represents a product equivalent to this product, false otherwise 
     */
    @Override
    public boolean equals(Object obj) {
        Product product = (Product) obj; 
        if(this == obj)
            return true;
        if(!(obj instanceof Product))
            return false;
        return ((this.getName().equals(product.getName())) && (this.getID().equals(product.getID())));

    }

    /**
     * @return the set of comments
     */
    public Set getCommentSet(){
        return this.commentsList;
    }
}
