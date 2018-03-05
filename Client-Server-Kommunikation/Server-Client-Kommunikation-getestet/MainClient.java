import java.io.IOException;
import java.net.UnknownHostException;
/**
 * @author Oskar Moritz
 * @version 1.0 
 * Die Klasse "MainClient" startet einen Client. Das ist lediglich eine Test-Klasse , um den Client zu testen.
 */
public class MainClient {

  public static void main(String[] args) throws UnknownHostException, IOException {
    Thread e = new Client("192.168.1.8");

  }

}
