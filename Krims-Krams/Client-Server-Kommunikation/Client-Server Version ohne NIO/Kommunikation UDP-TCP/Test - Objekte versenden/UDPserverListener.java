import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class UDPserverListener extends Thread{
  /*
   * @author Oskar Moritz
   * @version 1.0 
   * Beschreibung: ein Thread der sowohl vom CLient als auch Server genutzt wird um auf ankommende Packete zu horchen
   */
  private DatagramPacket packet;
  private DatagramSocket client;
  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
  private Update u;
  
  
  public UDPserverListener(int port) {
    this.port = port;
    u = new Update();
  }
  public void run() {
    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
    try {
      client = new DatagramSocket(port);
      while(true) {
        packet = new DatagramPacket( new byte[1024], 1024 );
        client.receive( packet ); //<-- der Grund für den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
        u = getObject(packet.getData());
        
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public Object getObject(byte[] bytes) throws java.io.IOException {
    Object obj = null;
    ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
    ObjectInputStream ois = new ObjectInputStream (bis);
    obj = ois.readObject();
    return obj;
  }
}
