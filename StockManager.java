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
 * @author Jose Ignacio Duque Blazquez
 * @author Alberto Valerio Burgueño
 * @version v.1
 */
public class StockManager
{

    private String name; 
    // A list of the products.
    private ArrayList<Product> stock;
    // A list of the orders.
    private Map<Product, Integer> order;
    // An arrayList of the clients. 
    private ArrayList<Client> clientsList; 
    //Singleton
    private static StockManager SM; 
    //An arrayList of the default comments
    private ArrayList<String> defaultComments; 
    //Buffer for the file
    private BufferedWriter bw;
    //An arrayList of the replenished products
    private ArrayList<Product> replenished;

    /**
     * Constructor for objects of class StockManager
     */
    private StockManager()
    {   
        this.name = ""; 
        this.stock = new ArrayList<Product>();
        this.order = new HashMap<Product, Integer>();
        this.clientsList = new ArrayList<Client>();
        this.defaultComments = new ArrayList<String>();  
        initializeDefaultComments();
        try{
            this.bw =new BufferedWriter(new FileWriter(".\registro.log"));
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }
        this.replenished = new ArrayList<Product>();
    }

    /**
     * Constructor Parametrized for object for the class StockManager
     * @param name  String of the name of StockManager
     */
    private StockManager(String name){
        this.name=name; 
        this.stock = new ArrayList<Product>();
        this.order = new HashMap<Product, Integer>();
        this.clientsList = new ArrayList<Client>(); 
        this.defaultComments = new ArrayList<String>();
        initializeDefaultComments(); 
        try{
            this.bw =new BufferedWriter(new FileWriter("registro.log"));
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }
        this.replenished = new ArrayList<Product>();
    }

    /**
     * Return the unique instance of the StockManager, if it doesn't exist, create it and return it.
     * @param name  String of the name of stockManager
     */
    public static StockManager getInstanceParametrized(String name){

        if(SM == null){
            SM = new StockManager(name); 
        }
        return SM ;
    }

    /**
     * Return the unique instance of the StockManager, if it doesn't exist, create it and return it.
     */
    public static StockManager getInstance(){
        if(SM == null){
            SM = new StockManager(); 
        }
        return SM;    
    }

    /**
     * Add a client to the ArrayList of clients
     * @param client    Client to be added
     */
    public void addClient(Client client){
        if(!clientsList.contains(client)){
            clientsList.add(client); 
        }else{
            System.out.println("The Client has alredy exists");
        }
    }

    /**
     * Delete a client to the ArrayList of clients
     * @param client    Client to be added
     */
    public void deleteClient(Client client){
        if(clientsList.contains(client)){
            clientsList.remove(client); 
        }else{
            System.out.println("The Client is not in the Client list");
        }
    }

    /**
     * Add a product to the list.
     * @param item The item to be added.
     */
    public void addProduct(Product item)
    {
        if(!stock.contains(item)){
            stock.add(item);
        }else{
            System.out.println("The product has alredy exists");
        }
    }

    /**
     * Delete a product to the list.
     * @param item The item to be deleted.
     */
    public void deleteProduct(Product item){
        if(stock.contains(item)){
            stock.remove(item);
        }else{
            System.out.println("The product is not in stock");
        }
    }

    /**
     * Go through the map. If it finds the given product, add a given quantity to a order.If it doesn't find the product, add it to the map
     * @param item The item to be added.
     * @param orderQuantity Integer of the quantity of product to be ordered
     */
    public void addProductOrder(Integer OrderQuantity, Product item)
    {
        boolean aux = false; 
        Iterator<Map.Entry<Product, Integer>> it  = order.entrySet().iterator(); 
        while(it.hasNext() && !aux){
            Map.Entry<Product, Integer> entry = it.next(); 
            if(entry.getKey().equals(item)){
                aux=true;                 
                Integer aux1 = 0;
                aux1 = entry.getValue() + OrderQuantity; 
                entry.setValue(aux1);
            }            
        }
        if(!aux){
            order.put(item, OrderQuantity);
        }
    }

    /**
     * Receive a delivery of a particular product.
     * Increase the quantity of the product by the given amount.
     * @param product Product to be replenished
     * @param amount The amount to increase the quantity by.
     */
    private void delivery(Product product, int amount)
    {
        Product ProductoEncontrado=findProduct(product);
        if(ProductoEncontrado!=null){
            ProductoEncontrado.increaseQuantity(amount);
        }
    }

    /**
     * Try to find a product in the stock list of stockManager
     * @param item  Product to be found
     * @return productReturn    Product that has found in the list
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
     * @param id    Integer id of a product
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
     * Locate a product with the given product, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param product Product to inspect.
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
    public void addToOrder(Integer OrderQuantity, Product product){
        if(OrderQuantity < product.getQuantity()){
            addProductOrder(OrderQuantity, product);
            product.sellOrder(OrderQuantity);      
        }else{
            delivery(product, (OrderQuantity-product.getQuantity()));
            addProductOrder(OrderQuantity, product);
            product.sellOrder(OrderQuantity);  
            setReplenished(this.replenished,product);   
        }
        
        if(product.getQuantity() < product.getStock()){
            delivery(product,(product.getStock()-product.getQuantity())+1);   
            setReplenished(this.replenished,product);
        }
    }   

    /**Each turn fill the arrayList with products to be replenished
     * @param replenished   ArrayList of products out of stock and replenished
     * @param product   Product 
     */
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
    public void favouriteOrder(HashMap<Product, Integer> map){
        for(Map.Entry<Product, Integer> entry : map.entrySet()){
            if(stock.contains(entry.getKey())){
                addToOrder(entry.getValue(),entry.getKey());  
            }
        }
    }

    /**Make a order of one of each favourite products in the favourite list of a client
     * @param favouriteOrder    ArrayList of products to be ordered
     */
    public void makeVipOrder(ArrayList<Product> favouriteOrder, Integer orderNumber){
        for(Product product : favouriteOrder){
            addToOrder(orderNumber, product);
        }
    }

    /**Make a order of 50 of this product
     * @param product   Product to be ordered
     */
    public void makeStandardOrder(Product product, Integer orderNumber){
        addToOrder(orderNumber, product);     
    }

    /**Return a comment based of a given point
     * @param points    Integer of points
     * @return the choosen comment of the default comments list 
     */
    public String getDefaultComments (Integer points){
        return this.defaultComments.get(points);         
    }

    /**Return the product in the stock list with the most comments
     * 
     * @return The product with the most comments
     */

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

    /**Return the client who spent more money 
     * 
     * @return bestClient the client who spent more money 
     */
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

    /**Return the product with more sales number in the stock list 
     * 
     * @return The product with more sales number 
     */
    public Product getMostSold(){
        Product mostSold = new Product(); 
        Integer aux=0;
        for (Map.Entry<Product, Integer> entry : order.entrySet()){
            if(entry.getValue() > aux){
                mostSold = entry.getKey(); 
                aux+=entry.getValue();
            }
        }
        return mostSold; 
    }

    /**Return the ArrayList of Clients 
     * 
     * @return ArrayList with all the Clients of StockManager 
     */
    public ArrayList getClientList(){        
        return this.clientsList; 
    }

    /**Find a Client in the Client List by a given id
     * @param Id Id to find a Client
     * @return Client with the given id  
     */
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

    /**Initialize an ArrayList of predetermined comments 
     */
    public void initializeDefaultComments(){
        this.defaultComments.add("Bad product");
        this.defaultComments.add("Not very good product");
        this.defaultComments.add("Good product");
        this.defaultComments.add("Very good product");
        this.defaultComments.add("Excellent product");
    }

    /**Write the file "registro.log" to save the simulation
     * 
     */
    public void writeFile(){
        try{
            Integer soldCount=0;
            Integer turn=1; 
            ArrayList<Product> aux=new ArrayList();
            Client client = new Client();
            for(int i=0;turn<=10;i++){
                client=clientsList.get(i%clientsList.size());
                aux=client.prepareOrder();
                this.replenished.clear();

                this.bw.write("\n(turn: " + turn +")");
                this.bw.newLine();
                this.bw.write("Client: <"+ client.toString()+">");
                this.bw.newLine();
                for(Product product : aux){
                    this.bw.write("  -Product: <" + " OrderQuantity: " + client.getOrderNumber()+ " " + product.toString()+">\n");        
                }
                client.makeOrder(aux);
                if(!this.replenished.isEmpty()){
                    this.bw.write("\nThe order is done and these products need to be replenished\n");
                    for(Product product : this.replenished){
                        this.bw.write("  -Product: <"+ product.toString()+">\n");
                    }
                }else{
                    this.bw.write("\nThe order is done\n");
                }
                turn++;
            }
            this.bw.write("\n At the end of simulation\n");
            for(Map.Entry<Product, Integer> entry : order.entrySet()){
                this.bw.write("\nSoldProduct: " + entry.getKey().toString() + "\n");
                if(entry.getValue() > soldCount){
                    soldCount = entry.getValue(); 
                }
            }

            Product p = getMostSold();
            this.bw.write("\n ~MostSoldProduct: \n" + p.toString() + "\n salesNumber: "+ soldCount + "\n");

            p=getMostCommented();
            this.bw.write("\n ~MostCommentedProduct: \n" + p.toString() + "\n commentsNumber: "+ p.getComments().size()+"\n");

            Client c=getBestClient();
            this.bw.write("\n ~ClientWhoSpentMoreMoney: \n" + c.toString() + "\n totalQuantityofMoneySpent: " + c.getMoneySpent());
            this.bw.close();
        }catch(IOException e){
            System.out.println("ERROR: IOException has ocurred");
        }
    }

    public ArrayList getStockList(){
        return this.stock;
    }

    public Map getOrderList(){
        return this.order;
    }
}

