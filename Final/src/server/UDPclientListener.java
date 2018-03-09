
package server;
import ioserver.GameManager;
import ioserver.Player;
import ioserver.SUpdate;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPclientListener extends Thread {
	private DatagramPacket packet;
	private DatagramSocket client;
	private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	private GameManager gameManager;
	private  SUpdate update;
	  
	  public UDPclientListener(int port, GameManager gameManager ) {
	    this.port = port;
	    this.gameManager= gameManager;
	    update = new SUpdate(new Player(), new Player(), new Player(), false, false);
	    
	  }
	  public void run() {
	    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
	    try {
	      client = new DatagramSocket(3555);
            System.out.println("Ich bin jetzt in der while true schleife");
	      while(true) {
	        System.out.println("Test");
	        packet = new DatagramPacket( new byte[1024], 1024 );
	        client.receive( packet ); //<-- der Grund f�r den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
            update.fromByteArray(packet.getData());
			gameManager.cSetUpdateS(update);
	      }
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	}

