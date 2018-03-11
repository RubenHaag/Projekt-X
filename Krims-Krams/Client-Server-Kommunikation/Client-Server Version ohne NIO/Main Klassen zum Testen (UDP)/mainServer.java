import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class mainServer {

  public static void main(String[] args) throws UnknownHostException, IOException {
    Thread l = new UDPServer(InetAddress.getLocalHost(), InetAddress.getLocalHost(), InetAddress.getLocalHost());
    l.run(); 

  }

}

