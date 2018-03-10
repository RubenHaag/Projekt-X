
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
 * Die Klasse Client regelt client-seitig die Art der Verbindung. Für eine Verbindung werden sowohl das UDP als auch das TCP portocol benötigt.
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
	 * Dies ist der Konstruktor für die Klasse Client. Hier wird die TCP Verbindung zum server aufgebaut und die UDP Verbindung vorbereitet.
     * @param addresse IP-Addresse des Server
     * @param gameManager Der Game Manager wird dem Client übergeben, damit man die richtigen Objekte  übertragen kann.
     * Hinweis: Es werden keine Objekte versende sonder nur byte array. Jedes Objekte, das versendet werden soll, hat die Methoden fromByteArray() und toByteArray().
     * Mit diesen Methoden werden alle Attribute in einem byte array gespeichert bzw. aus einem byte array in ein Objekte eingefügt.
	 */
	public Client(String addresse, GameManager gameManager) { //die IP des servers
		this.port = 3555; //Anfangsport, um den server zu erreichen
		this.gameManager = gameManager;
		try {
			ip = InetAddress.getByName(addresse);

			server = new Socket(ip,port);
			BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream()));
			port = Integer.parseInt(br.readLine()); //der anzusprechende Port wird aus dem Stream gelesen und port aktualisiert
			System.out.println(port);
			server.close();
			this.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void run() {
		try {
			datagramSocketSend = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		//ein neuer Thread wird gestartet um alle eingehenden Packete zu empfangen
		//das ist zum Ablauf während des Spiels (Realtime)
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
     *  @throws IOException Wenn die Verbindung zum Server nicht zustande kommt.
	 */
	private void send() throws IOException {
		byte[] senden = gameManager.cGetUpdateS().toByteArray();
		byte[] sandData = new byte[1024];
		//Damit das gesendete Packet die selbe Grö0e hat, wie das Empfangene
		for(int i = 0; i<senden.length; i++){
		    sandData[i] = senden[i];
        }
		DatagramPacket packet = new DatagramPacket(sandData, sandData.length, ip, port);
		datagramSocketSend.send(packet);

	}

}
