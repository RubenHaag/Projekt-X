import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPclientListener extends Thread {
	private DatagramPacket packet;
	  private DatagramSocket client;
	  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	  private SUpdate supdate;
	  
	  
	  public UDPclientListener(int port, SUpdate su ) {
	    this.port = port;
	    supdate= su;
	    
	  }
	  
	  public void run() {
	    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
	    try {
	      while(true) {
	    	client = new DatagramSocket(port);
	        packet = new DatagramPacket( new byte[1024], 1024 );
	        client.receive( packet ); //<-- der Grund für den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
	        supdate.fromByteArray(packet.getData());       
	      }
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	}
