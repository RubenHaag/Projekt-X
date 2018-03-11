import java.io.IOException;
import java.net.*;

/**
 * Server dient zum Daten emfangen, von den Clients und zum senden, an die Clients. Ohne Nio
 * @author  Oskar, Moritz
 * @version 0.2.1
 */
public class Server extends Thread{
  private DatagramPacket packet;
  private DatagramSocket datagramSocket;
  private int port;
  private Entschlüsselung e;
  
  /**
     * Konstruktor für die Klasse Server
     * @param port der port an dem der Server hört
     */
  public Server(int port) throws IOException {
    this.port=port;
    connect(port);
  }
    
  public void run() {
      while ( true ){
        try {
        read();
        //send(ia, port)
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
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
      datagramSocket.receive( packet );
    byte[] data = packet.getData();
    System.out.println("first Int: "+data.toString());
    }
  /**
     *  Metode zum eröffen des Servers
     *  @since 0.0.1
     */
    private void connect(int port) throws IOException {
      datagramSocket = new DatagramSocket( port );
    packet= new DatagramPacket( new byte[1024], 1024 );
    } 
    
    /**
     *  Metode zum senden von den Daten.
     */
    private void send(InetAddress ia, int port) throws IOException {
//      Spieler s;
//    byte[] sanddata = e.toBytes(s);
//      packet = new DatagramPacket( sanddata, sanddata.length, ia, port );
//      datagramSocket.send( packet );
    }
    
    
}

