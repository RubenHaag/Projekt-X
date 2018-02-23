import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * UDPClient ist der Client. UDPClient dient zum Daten emfangen, von dem Server und zum senden, an den Server. Ohne java.nio. Nur mit java.net. UDP-Verbindung: "verbindungslos" Verbindung 
 * @author  Oskar, Moritz
 * @version 0.3
 */
public class UDPClient extends Thread {
  private DatagramSocket datagramSocket, datagramSocketSend;
  private final int port = 1409;
  private  int portToServer;
  private InetAddress ia1;
  
  /**
     * Konstruktor für die Klasse UDPClient
     * @param ia ist die InetAddress von dem Server, an den der Client die Daten sendet.
     * @param port ist der port an dem der Client die Daten sendet soll.
     */
  public UDPClient(InetAddress ia, int port) throws IOException {
    ia1 = ia;
    portToServer = port;
    connect();
  }
  
  public void run() {
    while ( true ){
      try {
        send();
        read();
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
   /*  Diese Methode eröffnet den Client. Diese Methode erstellt zwei „datagramSocket“. Der erste (datagramSocket) wird genutzt um daten zu empfangen @see read() . Der andern (datagramSocketSend) wird zum Senden benötigt. @see send() 
     *  @since 0.0.1
     */
  private void connect() throws IOException {
    datagramSocket = new DatagramSocket(port);
    datagramSocketSend =new DatagramSocket();
  } 
  
   /**
     *  Die send()-Methode ist zum Senden, der Daten notwendig. Mit dieser Methode werden Daten an den Server gesendet. Dies passiert über den datagramSocketSend und das „DatagramPacket“.
     */
  private void send() throws IOException {
    byte[] sandData = "Hallo".getBytes();
    DatagramPacket packet = new DatagramPacket( sandData, sandData.length, ia1, portToServer );
    datagramSocketSend.send(packet);
  }
    /**
     *  Die Methode read() ist zum empfangen, der Daten notwendig. In dieser Methode wird ein „DatagramPacket“ erstellt. Dann wird gewartet bis das „DatagramPacket“ angekommen ist und kann das „DatagramPacket“ ausgelesen werden.
     */
  private void read() throws IOException {
    DatagramPacket packet= new DatagramPacket( new byte[1024], 1024 );
    datagramSocket.receive( packet );
    byte[] data = packet.getData();
    System.out.println("String: "+data.toString());
  }

}

