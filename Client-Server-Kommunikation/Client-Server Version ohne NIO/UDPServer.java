import java.io.IOException;
import java.net.*;

/**
 * UDPServer dient zum Daten emfangen, von den Clients und zum senden, an die Clients. Ohne Nio. UDPServer
 * @author  Oskar, Moritz
 * @version 0.3
 */
public class UDPServer extends Thread{
  private DatagramPacket packet;
  private DatagramSocket datagramSocket1, datagramSocket2, datagramSocket3;
  private DatagramSocket datagramSocketSend;
  private final int portToC = 1409;
  private final int portRead1 = 1429;
  private final int portRead2 = 1439;
  private final int portRead3 = 1459;
  private InetAddress ia1, ia2, ia3;
    
  /**
     * Konstruktor für die Klasse UDPServer
     * @param ia1 ist die InetAddress von dem ersten Client, an den der Server daten sendet.
     * @param ia2 ist die InetAddress von dem zweiten Client, an den der Server daten sendet.
     * @param ia3 ist die InetAddress von dem dritten Client, an den der Server daten sendet.
     */
  public UDPServer(InetAddress ia1,InetAddress ia2, InetAddress ia3) throws IOException {
    this.ia1 =ia1;
    this.ia2 =ia2;
    this.ia3 =ia3;
    connect();
  }
    
  public void run() {
      while ( true ){
        try {
          read1();
          //read2();
          //read3();
        send();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
        
        try {
           Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }
  }
    
  /**
     *  Metoden zum emfangen von Daten und zum verarbeiten der Daten. von einem Client. (Client 1)
     */
  private void read1() throws IOException {
    packet= new DatagramPacket( new byte[1024], 1024 );
      datagramSocket1.receive( packet );
    byte[] data = packet.getData();
    System.out.println("String 1: "+data.toString());
    }
  /**
     *  Metoden zum emfangen von Daten und zum verarbeiten der Daten. von einem Client. (Client 2)
     */
  private void read2() throws IOException {
    packet= new DatagramPacket( new byte[1024], 1024 );
      datagramSocket2.receive( packet );
    byte[] data = packet.getData();
    System.out.println("String 2: "+data.toString());
    }
  /**
     *  Metoden zum emfangen von Daten und zum verarbeiten der Daten. von einem Client. (Client 3)
     */
  private void read3() throws IOException {
    packet= new DatagramPacket( new byte[1024], 1024 );
      datagramSocket3.receive( packet );
    byte[] data = packet.getData();
    System.out.println("String 3: "+data.toString());
    }
    /**
     *  Metode zum senden von den Daten.
     */
    private void send() throws IOException {
    byte[] sandData = "Hallo".getBytes();
      packet = new DatagramPacket( sandData, sandData.length, ia1, portToC );
      datagramSocketSend.send(packet);
      packet = new DatagramPacket( sandData, sandData.length, ia2, portToC );
      datagramSocketSend.send(packet);
      packet = new DatagramPacket( sandData, sandData.length, ia3, portToC );
    }
    
    /**
     *  Metode zum eröffen des Servers
     *  @since 0.0.1
     */
    private void connect() throws IOException {
      datagramSocket1 = new DatagramSocket( portRead1 );
      datagramSocket2 = new DatagramSocket( portRead2 );
      datagramSocket3 = new DatagramSocket( portRead3 );
      datagramSocketSend =new DatagramSocket( );
    } 
      
    
}

