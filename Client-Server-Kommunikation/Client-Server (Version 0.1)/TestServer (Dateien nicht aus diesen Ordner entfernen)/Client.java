import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.UnknownHostException;

/*
 * @author Oskar Moritz
 * @version 0.1
 */

public class Client extends Thread{
  
  private int port;
  private DatagramChannel c1;
  private SocketAddress address;
  private ByteBuffer bb;
  private Spieler sself; //bekommen wir von Lukas im z.b. Konstruktor
  String adr;
  private InetAddress myIP; 
  
  
  public Client(String adr, int port, Spieler s) {
    this.sself = s;
    this.port = port;
    this.adr= adr;
    bb = ByteBuffer.allocate(1024); 
    SocketAddress socketAddress = new InetSocketAddress(adr, this.port);  //socketAddress
    this.address = socketAddress;
    connect();
  }
  
  public Client(int port, Spieler s)throws IOException {
    this.sself = s;
    this.port = port;
    myIP = InetAddress.getLocalHost();
    bb = ByteBuffer.allocate(1024); 
    SocketAddress socketAddress = new InetSocketAddress(myIP, this.port);  //socketAddress
    this.address = socketAddress;
    connect();
  } 
  
  public void run() {
    while(true) {
      System.out.println("Going to send soon");
      send();
      //read();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }    

    }
  }
  /**
     *  Metode zum senden, der Daten.
     */
  private void send(){
    bb.clear();
    bb.put(sself.getname().getBytes());
    //bb.putDouble(sself.getZahl().getBytes());
    if(bb.position() != 0) {
      System.out.println("der sendbuffer enthält daten");
    }
    
    bb.flip();
    try {
      c1.send(bb, address);
    } catch (IOException e) {
      e.printStackTrace();
    }
    bb.clear(); 
  }
  
  private void read() {
    try {
      c1.receive(bb);
    } catch (IOException e) {
      e.printStackTrace();
    }   
    System.out.println("kurz vor if abfrage");
    if(bb.position() != 0) {
      bb.flip();
      System.out.println("first Int: "+bb.get());
      System.out.println("first double: "+bb.getDouble());
      //sself.setUUID(bb.getInt());
      //sself.setZahl(bb.getDouble());
    }
    bb.clear();
  }
   /**
     *  Metode zum Verbinden
     *  @since 0.0.1
     */
  private void connect() {
    try {
      c1 = DatagramChannel.open();
      //c1.configureBlocking(false);
      c1.socket().bind(null);
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    System.out.println("Connected");   
  }
}
