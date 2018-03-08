
package server;
import ioserver.GameManager;
import ioserver.SUpdate;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPclientListener extends Thread {
	private DatagramPacket packet;
	private DatagramSocket client;
	private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	private GameManager gameManager;
	  
	  
	  public UDPclientListener(int port, GameManager gameManager ) {
	    this.port = port;
	    this.gameManager= gameManager;
	    
	  }
	  public void run() {
	    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
	    try {
	      while(true) {
	    	client = new DatagramSocket(port);
	        packet = new DatagramPacket( new byte[1024], 1024 );
	        client.receive( packet ); //<-- der Grund f�r den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
			if(packet != null) {
				gameManager.cGetUpdateS().fromByteArray(packet.getData());
			}
	      }
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	}

