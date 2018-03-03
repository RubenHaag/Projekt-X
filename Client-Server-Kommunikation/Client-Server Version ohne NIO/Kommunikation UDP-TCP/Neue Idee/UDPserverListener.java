import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/*
   * @author Oskar Moritz
   * @version 1.2 
   * Beschreibung: ein Thread der sowohl vom CLient als auch Server genutzt wird um auf ankommende Packete zu horchen
   */
public class UDPserverListener extends Thread{
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
        u.awaybyte(packet.getData());       
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
