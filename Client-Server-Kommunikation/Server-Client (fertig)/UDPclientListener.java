import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPclientListener extends Thread {
	private DatagramPacket packet;
	  private DatagramSocket client;
	  private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	  private cLoginUpdate spieler;
	  private SUpdate supdate;
	  
	  
	  public UDPclientListener(int port, cLoginUpdate sp, SUpdate su ) {
	    this.port = port;
	    spieler = sp;
	    supdate= su;
	    
	  }
	  
	  public void run() {
	    //Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
	    try {
	      client = new DatagramSocket(port);
	      packet = new DatagramPacket( new byte[1024], 1024 );
	      client.receive( packet );
	      spieler.awaybyte(packet.getData());
	      while(true) {
	        packet = new DatagramPacket( new byte[1024], 1024 );
	        client.receive( packet ); //<-- der Grund f�r den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
	        supdate.fromByteArray(packet.getData());       
	      }
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	}
