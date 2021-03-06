
/**
 * Write a description of class Comments here.
 *
* @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class Comments
{
    private String text; 
    private String nameClient; 
    private Integer points;

    /**
     * Constructor for objects of class Comments
     */
    public Comments()
    {
        this.text = ""; 
        this.nameClient = ""; 
        this.points = 0; 

    }

    /**
     * Parametricsize Constructor for objects of class Comments
     * @param String text The body text of the comment. 
     * @param nameClient The name of the Client who want to comment.
     * @param Integer points The number of points to give to the product. 
     */
    public Comments(String text, String nameClient, Integer points)
    {
        this.text = text; 
        this.nameClient = nameClient; 
        checkCommentsPoints(points);

    }
    /**
     * Show the details of a Comment. This include the name of the CLient who has commented, the body text of the Comment and the value of points the Client has
     * give to the Product. 
     *  
     */
    public void showComment(){
     
        System.out.println("Client: " + this.nameClient); 
        System.out.println("Comment: " + this.text); 
        System.out.println("Points: " + this.points.toString()); 
    }

    /**
     * Get Text 
     *
     * @return    text
     */
    public String getText()
    {
        return this.text; 

    }

    /**
     * Get Name Client 
     *
     * @return    nameClient 
     */
    public String getNameClient()
    {
        return this.nameClient; 

    }

    /**
     * Get Points 
     *
     * @return    points
     */
    public Integer getPoints()
    {
        return this.points; 

    }
    
    /**
     * @return String that "textually represents" this object. 
     */
    public String toString(){
     return "ClientName: " + this.nameClient + " Text: " + this.text
     + " Score: " + this.points;
        
    }
    
    /**
     * Check the value of a Integer and fix when the number is out of bounds
     * @param points    Integer to be checked to make a valid value point
     */
    public void checkCommentsPoints(Integer points){
        if(points<=0){
            points=1;
            this.points = points; 
        }else if(points>5){
            points=5;
            this.points = points; 
        }else{
            this.points = points; 
        }
        
    }
    
        /**
     * Compares this Comments to the specified object. The result is true if and only if the argument is not null and is a String object that represents the same sequence of characters as this object.
     * @return true if the given object represents a Comments equivalent to this Comments, false otherwise 
     */
    @Override
    public boolean equals(Object obj) {
        Comments comments = (Comments) obj; 
        if(this == obj)
            return true;
        if(!(obj instanceof Comments))
            return false;
        return ((this.getNameClient().equals(comments.getNameClient())));
    }
    
}
