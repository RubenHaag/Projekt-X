import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Moritz Gerlach
 * @version 0.00.0.1
 * 
 *
 */
public class Client {
  private static String address = "localhost";
  private static int port = 12451;
  public static void main(String[] args) {
    try {
      Socket verbindung = new Socket(address, port);
      System.out.println("Verbunden");
      System.out.println("Test");
      
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Ende");
    } 
  }
}
