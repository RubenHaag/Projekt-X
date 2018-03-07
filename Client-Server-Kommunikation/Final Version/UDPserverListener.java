package netzwerk;
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
  private CUpdate cupdate;
  
  public UDPserverListener(int port, CUpdate cu ) {
    this.port = port;
    cupdate=cu;   
  }
  public void run() {
    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
    try {
      while(true) {
    	client = new DatagramSocket(port);
        packet = new DatagramPacket( new byte[1024], 1024 );
        client.receive( packet );
        cupdate.fromByteArray(packet.getData());       
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}
