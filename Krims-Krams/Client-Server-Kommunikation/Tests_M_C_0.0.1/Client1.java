import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
  public static void main(String[] args) {
    try {
      Socket verbindung = new Socket("localhost", 12451);
      System.out.println("Verbunden");
      System.out.println("Test");
      
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Ende");
    } 
  }
}
