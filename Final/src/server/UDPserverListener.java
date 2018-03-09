
package server;
import ioserver.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
	/**
   * @author Oskar Moritz
   * @version 1.5 
   * Die Klasse "UDPserverListener" ist ein Thread, der sowohl vom CLient als auch vom server genutzt wird, um ankommende Pakete zu empfangen.
   */
public class UDPserverListener extends Thread{
  private DatagramPacket packet;
  private DatagramSocket client;
  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
  private CUpdate cupdate;
  private ServerVerwaltung serverVerwaltung;
  
  public UDPserverListener(int port, ServerVerwaltung serverVerwaltung) {
    this.port = port;
    cupdate = new CUpdate(null,  new Attack(new Rectangle(new Position(0,0), 10, 10), 0, 0, 0), new Player());
    this.serverVerwaltung = serverVerwaltung;
  }
  public void run() {
    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
    try {
        client = new DatagramSocket(port);
        while(true) {
            System.out.println("Halöchen ich bin in der While Schleife");
            packet = new DatagramPacket( new byte[1024], 1024 );
            client.receive( packet );
            System.out.println("Test");

            cupdate.fromByteArray(packet.getData());
            System.out.println(packet.getData());
            serverVerwaltung.sSetUpdateC(cupdate);
      }
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
}

