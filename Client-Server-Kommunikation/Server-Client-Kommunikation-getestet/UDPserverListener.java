import java.net.DatagramPacket;
import java.net.DatagramSocket;


/**
   * @author Oskar Moritz
   * @version 1.5 
   * Die Klasse "UDPserverListener" ist ein Thread, der sowohl vom CLient als auch vom Server genutzt wird, um ankommende Pakete zu empfangen.
   */
public class UDPserverListener extends Thread{
  private DatagramPacket packet;
  private DatagramSocket client;
  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
  private cLoginUpdate spieler;
  
  
  public UDPserverListener(int port, cLoginUpdate sp ) {
    this.port = port;
    spieler = sp;
  }
  /**
   *  Die cLoginUpdate-Methode gibt die vesendeten "Objekte" aus.
   *  @since 1.5
   */
  public cLoginUpdate getSpielerData() {
    return spieler;
  }
  
  public void run() {
    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
    try {
      client = new DatagramSocket(port);
      while(true) {
        packet = new DatagramPacket( new byte[1024], 1024 );
        client.receive( packet ); //<-- der Grund für den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
        spieler.awaybyte(packet.getData());       
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
