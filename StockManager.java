import java.util.ArrayList;
import java.util.Map; 
import java.util.HashMap; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.Iterator; 
import java.io.*;
/**
 * Manage the stock in a business.
 * The stock is described by zero or more Products.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StockManager
{

    private String name; 
    // A list of the products.
    private ArrayList<Product> stock;
    // A list of the orders.
    private Map<Product, Integer> order;
    // An arrayList of the Clients. 
    private ArrayList<Client> clientsList; 

    //Singleton
    private static StockManager SM; 

    private ArrayList<String> defaultComments; 

    private BufferedWriter bw;

    private ArrayList<Product> replenished;

    /**
     * Initialise the stock manager.
     */
    private StockManager()
    {   
        this.name = ""; 
        this.stock = new ArrayList<Product>();
        this.order = new HashMap<Product, Integer>();
        this.clientsList = new ArrayList<Client>();
        this.defaultComments = new ArrayList<String>();  
        InitializeDefaultComments();
        try{
            this.bw =new BufferedWriter(new FileWriter(".\registro.log"));
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }
        this.replenished = new ArrayList<Product>();
    }

    private StockManager(String name){

        this.name=name; 
        this.stock = new ArrayList<Product>();
        this.order = new HashMap<Product, Integer>();
        this.clientsList = new ArrayList<Client>(); 
        this.defaultComments = new ArrayList<String>();
        InitializeDefaultComments(); 
        try{
            this.bw =new BufferedWriter(new FileWriter("registro.log"));
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }
        this.replenished = new ArrayList<Product>();
    }

    public static StockManager getInstanceParametrized(String name){

        if(SM == null){
            SM = new StockManager(name); 
        }
        return SM ;
    }

    public static StockManager getInstance(){

        if(SM == null){
            SM = new StockManager(); 
        }

        return SM;    
    }

    public void addClient(Client client){
        if(!clientsList.contains(client)){
            clientsList.add(client); 
        }else{
            System.out.println("The Client has alredy exists");
        }
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {
        if(!findProductBool(item)){
            stock.add(item);
        }else{
            System.out.println("The product has alredy exists");
        }
    }

    /**
     * Delete a product to the list.
     * @param item The item to be deleted.
     */
    public void deleteProduct(Product item)
    {
        if(findProductBool(item)){
            stock.remove(item);
        }else{
            System.out.println("The product has alredy deleted");
        }
    }

    /**
     * 
     */
    public boolean findProductBool(Product item){
        boolean aux = false; 
        Iterator<Product> it = stock.iterator(); 
        while(it.hasNext() && !aux){
            Product product = it.next(); 
            if(product.equals(item)){
                aux=true;       
            }
        }
        return aux;
    }

    /**
     * Add a product to the order list.
     * @param item The item to be added.
     */
    public void addProductOrder(Integer OrderQuantity, Product item)
    {
        boolean aux = false; 
        for(Map.Entry<Product, Integer> entry : order.entrySet()){
            if(item.equals(entry.getKey())){
                aux=true; 
                Integer aux1 =0;
                aux1=entry.getValue() + OrderQuantity;
                entry.setValue(aux1);                 
                item.AddSold(OrderQuantity); 
            }
        }
        if(!aux){
            order.put(item, OrderQuantity);
            item.AddSold(OrderQuantity);
        }
    }

    /**
     * Receive a delivery of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param id The ID of the product.
     * @param amount The amount to increase the quantity by.
     */
    public void delivery(Product product, int amount)
    {
        Product ProductoEncontrado=findProduct(product);
        if(ProductoEncontrado!=null){
            ProductoEncontrado.increaseQuantity(amount);
        }
    }

    /**
     * Try to find a product in the stock with the given id.
     * @return The identified product, or null if there is none
     *         with a matching ID.
     */
    public Product findProduct(Product item)
    {
        Product productReturn=new Product();
        boolean aux = true;
        Iterator<Product> it = stock.iterator(); 

        while(it.hasNext() && aux){
            Product product = it.next(); 
            if(product.equals(item)){
                productReturn = product; 
                aux = false; 
            }
        }
        if(aux) {
            System.out.println("The product does not exist");   
        }
        return productReturn;
    }

    /**
     * Try to find a product in the stock with the given id.
     * @return The identified product, or null if there is none
     *         with a matching ID.
     */
    public Product getProduct(Integer id)
    {
        boolean aux = true;
        Product productReturn = new Product(); 
        for(Product product : stock){    
            if(id == product.getID()){            
                productReturn = product; 
                aux = false; 
            }
        }
        if(aux) {
            System.out.println("The product does not exist");   
            return null; 
        }
        return productReturn;
    }

    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    public int numberInStock(Product product)
    {
        int quantity = 0; 
        Product ProductoEncontrado=findProduct(product);
        if(ProductoEncontrado!=null){
            quantity=ProductoEncontrado.getQuantity();
        }
        return quantity;
    }

    /**
     * Print details of all the products.
     */
    public void printProductDetails()
    {
        for(Product product : stock){
            System.out.println(product.toString()); 
        }
    }

    /**
     * Make a order with a specific quantity
     * 
     * @param Integer OrderQuantity The quantity of the product in a order  
     * @param Product product
     */
    public void AddToOrder(Integer OrderQuantity, Product product){

        if(OrderQuantity < product.getQuantity()){
            addProductOrder(OrderQuantity, product);
            product.sellOrder(OrderQuantity);      
        }else{
            delivery(product, OrderQuantity-product.getQuantity());
            addProductOrder(OrderQuantity, product);
            product.sellOrder(OrderQuantity);  
            setReplenished(this.replenished,product);   
        }
        if(product.getQuantity() < product.getStock()){
            delivery(product, product.getStock()-product.getQuantity());   
            setReplenished(this.replenished,product);
        }
    }   

    public void setReplenished( ArrayList<Product> replenished,Product product){
        if(!replenished.contains(product)){
            replenished.add(product);
        }
    }

    /**
     * Go throught the map and go calling the method AddToOrder
     * 
     * @param HashMap map The map with the favourite products
     */
    public void FavouriteOrder(HashMap<Product, Integer> map){

        for(Map.Entry<Product, Integer> entry : map.entrySet()){
            if(stock.contains(entry.getKey())){
               
                AddToOrder(entry.getValue(),entry.getKey());  
            }
        }
    }

    public void MakeVipOrder(ArrayList<Product> favouriteOrder){
        for(Product product : favouriteOrder){
            AddToOrder(1, product);
        }
    }

    public void MakeStandardOrder(Product product){
        AddToOrder(50, product);     
    }

    public String getDefaultComments (Integer points){
        return this.defaultComments.get(points);         
    }

    public Product getMostCommented(){
        Integer aux = 0; 
        Product p = new Product(); 
        for(Product product : stock){
            if(product.getNumberOfComments() > aux){
                aux = product.getNumberOfComments(); 
                p = product; 
            }                    
        }  
        return p;
    }

    public Client getBestClient(){
        Client bestClient = new Client(); 
        Float aux = 0.0f; 

        for(Client client : clientsList){
            if( client.getMoneySpent() > aux){
                bestClient = client; 
                aux = client.getMoneySpent(); 
            }                         
        }

        return bestClient; 
    }

    public Product getMostSold(){
        Product mostSold = new Product(); 
        for (Map.Entry<Product, Integer> entry : order.entrySet()){
            if(entry.getValue() > mostSold.getSoldCount()){
                mostSold = entry.getKey(); 
            }

        }
        return mostSold; 
    }

    public ArrayList getClientList(){        
        return this.clientsList; 
    }

    public Client getClient(Integer id){
        Client c = new Client();
        boolean aux = false; 
        Iterator<Client> it = clientsList.iterator(); 
        while(it.hasNext() && !aux){
            Client client = it.next();
            if(client.getId() == id){                
                aux = true; 
                c=client; 
            }            
        }
        return c; 
    }

    public Client getOrderNumber(){
        Integer aux = 0; 
        Client best = new Client(); 
        for(Client client: clientsList){
            if(client.getOrderNumber()  > aux){
                best = client; 
                aux = client.getOrderNumber();
            }            
        }
        return best;    

    }

    public void InitializeDefaultComments(){
        this.defaultComments.add("Bad product");
        this.defaultComments.add("Not very good product");
        this.defaultComments.add("Good product");
        this.defaultComments.add("Very good product");
        this.defaultComments.add("Excellent product");
    }

    public void WriteFile(){
        try{
            Integer i=1; 
            ArrayList<Product> aux=new ArrayList();
            Set<Comments> auxSet= new HashSet();
            for(Client client : clientsList){
                aux=client.PrepareOrder();
                this.replenished.clear();
                this.bw.write("(turn: " + i+")");
                this.bw.newLine();
                this.bw.write("Client: <"+ client.toString()+">");
                this.bw.newLine();

                for(Product product : aux){
                    this.bw.write("  -Product: <"+ product.toString()+">\n");
                    auxSet=product.getComments();
                    for(Comments comment : auxSet){
                        this.bw.write("   ~Comment: <" + comment.toString() + ">\n");
                    }
                }
                client.MakeOrder(aux);
                this.bw.write("The order is done and these products need to be replenished\n");

                for(Product product : this.replenished){
                    this.bw.write("  -Product: <"+ product.toString()+">\n");
                }
                i++;
            }
            this.bw.write("\n At the end of simulation\n");
            for(Map.Entry<Product, Integer> entry : order.entrySet()){
                this.bw.write("\nSoldProduct: " + entry.getKey().toString() + "\n");
                auxSet=entry.getKey().getComments();
                for(Comments comment : auxSet){
                    this.bw.write("  ~Comment: <" + comment.toString() + ">\n");
                }

            }
            Product p = getMostSold();
            this.bw.write("\nMostSoldProduct: " + p.toString() + " salesNumber: "+ p.getSoldCount() + "\n");
            
            p=getMostCommented();
            this.bw.write("MostCommentedProduct: " + p.toString() + " commentsNumber: "+ p.getComments().size()+"\n");
            
            Client c = getOrderNumber();
            this.bw.write("\nClientWithMoreOrders: " + c.toString() + " OrderNumber: " + c.getOrderNumber() + "\n");
            
            c=getBestClient();
            this.bw.write("ClientWhoSpentMoreMoney: " + c.toString() + " totalQuantityofMoneySpent: " + c.getMoneySpent());
            this.bw.close();
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }

    }
}
