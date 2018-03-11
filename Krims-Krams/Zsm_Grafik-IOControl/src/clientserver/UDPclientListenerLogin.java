package clientserver;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPclientListenerLogin extends Thread {
	private DatagramPacket packet;
	  private DatagramSocket client;
	  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	  private cLoginUpdate spieler;
	  
	  
	  public UDPclientListenerLogin(int port, cLoginUpdate sp) {
	    this.port = port;
	    spieler = sp;
	    
	  }
	  
	  public void run() {
	    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
	    try {
	      client = new DatagramSocket(port);
	      packet = new DatagramPacket( new byte[1024], 1024 );
	      client.receive( packet );
	      spieler.awaybyte(packet.getData());
	    } catch(Exception e) {
	      e.printStackTrace();
	    }
	}

}