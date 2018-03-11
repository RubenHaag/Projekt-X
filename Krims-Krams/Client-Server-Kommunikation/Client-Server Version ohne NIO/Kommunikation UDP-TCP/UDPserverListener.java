import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPserverListener extends Thread{
	/*
	 * @author Oskar Moritz
	 * @version 1.0 
	 * Beschreibung: ein Thread der sowohl vom CLient als auch Server genutzt wird um auf ankommende Packete zu horchen
	 */
	private DatagramPacket packet;
	private DatagramSocket client;
	private int port; //der port auf den der UDPserverListener horcht (den ein CLient anschreibt)
	
	
	public UDPserverListener(int port) {
		this.port = port;
	}
	public void run() {
		//Der thread horcht die ganze zeit ob vom Client ein Packet gesendet wurde
		try {
			client = new DatagramSocket(port);
			while(true) {
				packet = new DatagramPacket( new byte[1024], 1024 );
				client.receive( packet ); //<-- der Grund für den Thread: falls kein Packet ankommt, wartet .recieve() unendlich lange auf Packete
				byte[] data = packet.getData();
				System.out.println("String 1: "+data.toString()); //--> muss beim Kompletten zusammenfügen noch angepasst werden
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
