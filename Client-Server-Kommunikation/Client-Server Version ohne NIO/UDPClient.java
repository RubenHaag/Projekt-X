import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient extends Thread {
  private DatagramSocket datagramSocket, datagramSocketSend;
  private final int port = 1409;
  private  int portToServer;
  private InetAddress ia1;
  
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
   /*  Metode zum eröffen des Clients
     *  @since 0.0.1
     */
  private void connect() throws IOException {
      datagramSocket = new DatagramSocket(port);
      datagramSocketSend =new DatagramSocket();
    } 
  
   /**
     *  Metode zum senden von den Daten.
     */
    private void send() throws IOException {
    byte[] sandData = "Hallo".getBytes();
    DatagramPacket packet = new DatagramPacket( sandData, sandData.length, ia1, portToServer );
    datagramSocketSend.send(packet);
    }
    /**
     *  Metode zum empfangen von den Daten.
     */
    private void read() throws IOException {
      DatagramPacket packet= new DatagramPacket( new byte[1024], 1024 );
      datagramSocket.receive( packet );
    byte[] data = packet.getData();
    System.out.println("String: "+data.toString());
    }

}

