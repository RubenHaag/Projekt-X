import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class mainClient {

  public static void main(String[] args) throws UnknownHostException, IOException {
    Thread e = new UDPClient(InetAddress.getLocalHost(), 1429);
    //d.run(); 
    //c.run();
    e.run();

  }

}

