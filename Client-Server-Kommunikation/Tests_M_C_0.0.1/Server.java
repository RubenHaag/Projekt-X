import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 * Dies ist eine Datei die nur zum Testen entwickelt wurde.
  * Bei diesen test wurde der Server und client noch mit java.net.; erstelt. Das wurde dann später verandert.
 * @author Moritz Gerlach
 * @version 1.0
 * 
 */
public class Server {
  public static void main(String[] args) {
    try {
      ServerSocket server = new ServerSocket(12451);
      System.out.println("Da"); 
      Socket client = server.accept();
      Socket client2 = server.accept();
      Socket client3 = server.accept();
      System.out.println("Ja alle drei sind da");
      server.close();

      
    } catch(IOException e) {
      System.err.println("Ende");
    }
  }
  
}

