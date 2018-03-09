
package server;

import ioserver.CUpdate;
import ioserver.GameManager;
import ioserver.SUpdate;
import ioserver.cLoginUpdate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
/**
 * @author Oskar Moritz
 * @version 2.5 
 * Die Klasse "Client" regelt client-seitig die Art der Verbindung. Für eine Verbindung werden sowohl das UDP als auch das TCP portocol benötigt.
 * Hier werden dasw UDP und das TCP protcol zusammengeführt.
 */
public class Client extends Thread{

	private int port;
	private InetAddress ip;
	private Socket server;
	private DatagramSocket datagramSocketSend;
	private UDPclientListener listener1;
	private cLoginUpdate loginSelf;
	private GameManager gameManager;

	/**
	 * Dies ist der Konstruktor f�r die Klasse "Client". Hier wird die TCP Verbindung zum server aufgebaut und die UDP Verbindung vorbereitet.
	 * @param addresse IP-Addresse des Server
	 */ 
	public Client(String addresse, GameManager gameManager) { //die IP des servers
		this.port = 3555; //Anfangsport, um den server zu erreichen
		this.gameManager = gameManager;
		//loginSelf = gameManager.getOwnCLU();
		try {
			ip = InetAddress.getByName(addresse);     
			//server = new Socket(ip,port);
			//cLOginUpdate Objekt erhalten genau E I N S!!!!!!!!!!!!!!!!

			//DataInputStream din = new DataInputStream(server.getInputStream());
			//byte[] bytes = din.readAllBytes();
			//loginSelf.awaybyte(bytes);
			//gameManager.setOwnCLU(loginSelf);
			//din.close();
			//server.close();
			//nachdem das CLU Objekt geschickt wurde, wird sich auf einen neues Port f�r UDP geeinigt
			server = new Socket(ip,port);
			BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream())); 
			port = Integer.parseInt(br.readLine()); //der anzusprechende Port wird aus dem Stream gelesen und "port" aktualisiert
			System.out.println(port);       
			server.close();
			this.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	} 

	public void run() {
		try {
			datagramSocketSend =new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		//ein neuer Thread wird gestartet um alle eingehenden Packete zu empfangen
		//das ist zum Ablauf w�hrend des Spiels (Realtime)
		listener1 = new UDPclientListener(port, gameManager);
		listener1.start();

		while ( true ){
			System.out.println("I bims in der While true");
			//zusätzlich sendet der CLient durch diese "while(true)" schleife alle 100ms eigene Packete
			try {
				send();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 *  Die send()-Methode ist zum Senden der Spiel-Daten notwendig. 
	 *  Mit dieser Methode werden die Spiel-Daten an den server gesendet.
	 *  Das passiert über den datagramSocketSend und das „DatagramPacket“.
	 */
	private void send() throws IOException {
		byte[] senden = gameManager.cGetUpdateS().toByteArray();
		byte[] sandData = new byte[1024];
		for(int i = 0; i<senden.length; i++){
		    sandData[i] = senden[i];
        }
		System.out.println(sandData.length);
		DatagramPacket packet = new DatagramPacket(sandData, sandData.length, ip, port);
		datagramSocketSend.send(packet);

	}

}
