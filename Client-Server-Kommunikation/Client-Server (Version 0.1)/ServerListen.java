import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
/**
 * ServerListen dient zum Daten emfangen, von den Clients.
 * @author  Oskar, Moritz
 * @version 0.1
 */
public class ServerListen extends Thread{
  
  private int port;
  private DatagramChannel c1;
  private SocketAddress ia;
  private ByteBuffer bb;
  private InetAddress myIP;
  
  /**
     * Konstruktor für die Klasse ServerListen
     * @param port der port an dem der Server hört
     */
  public ServerListen(int port) throws IOException {
    bb = ByteBuffer.allocate(1024);
    this.port = port;
    myIP = InetAddress.getLocalHost();
    SocketAddress ia = new InetSocketAddress(myIP, this.port);
    connect();
  } 

  public void run() {
    while(true) { 
        try {
          read();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }
  }
   /**
     *  Metode zum emfangen und verarbeiten der Daten.
     */
  private void read() throws IOException {
    c1.receive(bb);
    System.out.println(bb.getInt());
    bb.clear();
  }
   /**
     *  Metode zum eröffen des Servers
     *  @since 0.0.1
     */
  private void connect() throws IOException {
    c1 = DatagramChannel.open();
        c1.configureBlocking(false);
    c1.socket().bind(ia);
    
  }
}
