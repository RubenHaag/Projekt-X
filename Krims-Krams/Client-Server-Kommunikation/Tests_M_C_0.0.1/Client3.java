import java.net.Socket;

/**
 * Dies ist eine Datei die nur zum Testen entwickelt wurde.
 * Diese datei wurde nur erstelt um drei verbindung mit drei unterslichen Clients herzustellen.
 * @author Moritz Gerlach
 * @version 0.00.0.1
 * 
 */
public class Client3 {
  public static void main(String[] args) {
    try {
      Socket verbindung = new Socket("localhost", 12451);
      System.out.println("Verbunden");
      System.out.println("Test3");
      
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Ende");
    } 
  }
}

