/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 4.0 -  15/10/2014 
 * @author Profesores DP
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList; 

public class ClasePrincipal {
    public static void main(String[] args) {
        /**  
        instancia asociada al fichero de entrada inicio.txt
        */
        StockManager sm = null;
        Cargador cargador = new Cargador(sm);
        try {
            /**  
            Método que procesa línea a línea el fichero de entrada inicio.txt
            */
             FicheroCarga.procesarFichero("init.txt", cargador);
             sm = cargador.obtenerStockManager();
        }
        catch (FileNotFoundException valor)  {
            System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
        }
        catch (IOException valor)  {
            System.err.println ("Excepción capturada al procesar fichero: "+valor.getMessage());
        }
        
        
        /*//////////////////////SIMULATION///////////////////////////*/
         System.err.println ("Starting Simulation...");
        // ArrayList<Client> cl = sm.getClientList(); 
         Integer turn = 0; 
         /*
         for(Client client : cl){
             turn += 1; 
             System.out.println ("\n" + "\n"+"Starting Turn " + turn);
             System.out.println ("Starting turn of client : " + client.getName());
             client.MakeOrder( client.PrepareOrder());
            }
         
          */
         sm.WriteFile();
         
    }

}
