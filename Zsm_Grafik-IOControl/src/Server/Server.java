package Server;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import ioserver.*;

/**
 * @author Moritz Oskar 
 * @version 2.5 
 * Die Klasse "Server" regelt server-seitig die Art der Verbindung. F�r eine Verbindung werden sowohl das UDP als auch das TCP portocol ben�tigt.
 * Hier werden das UDP und das TCP protcol zusammengef�hrt.
 * 
 */
public class Server extends Thread {

	private ServerSocket connect;
	private ClientData[] clientDatas = new ClientData[3];
	//damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
	private ArrayList<Integer> port = new ArrayList<Integer>();
	private DatagramSocket sendSocket;
	private UDPserverListener listener1, listener2, listener3;
	// Die cLoginUpdate Objekte, die versendet werden
	private ArrayList<cLoginUpdateIO> CLUs = new ArrayList<cLoginUpdateIO>();
	private SUpdate supdate;

	/**
	 * Dies ist der Konstruktor f�r die Klasse "Server". Hier wird die TCP Verbindung zu den drei clients aufgebaut und die UDP Verbindung vorbereitet.
	 */
	public Server(cLoginUpdateIO login1, cLoginUpdateIO login2, cLoginUpdateIO login3) {
		CLUs.add(login1);
		CLUs.add(login2);
		CLUs.add(login3);

		//Intern Port festlegen, ein Port f�r jeden CLient
		port.add(3556);
		port.add(3557);
		port.add(3558);

		try {
			connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
			shareClientLogin();
			portsTauschen();
			sendSocket = new DatagramSocket();
			this.run(); //sobald alle freien Ports an verschiedene CLients vergeben wurden, startet die UDP Daten�bertragung

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("Mit allen Clients verbunden\nThread wird gestartet");
		//f�r jeden Client wird ein UDPserverListener gestartet
		listener1 = new Server.UDPserverListener(clientDatas[0].getZugewiesenerPort());
		listener1.start();
		listener2 = new UDPserverListener(clientDatas[1].getZugewiesenerPort());
		listener2.start();
		listener3 = new UDPserverListener(clientDatas[2].getZugewiesenerPort());
		listener3.start();
		while (true) {
			try {
				send();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(100);  //servertickrate alle 100ms wird gesendet
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void shareClientLogin() throws IOException {
		//in dieser Methode wird auf die cLoginUpdate objekte der drei CLients gewartet (TCP)
		while (CLUs.get(0).getSpielStart()) {

		}
		for (int i = 0; i < 3; i++) {
			Socket client = connect.accept();
			DataOutputStream dout = new DataOutputStream(client.getOutputStream());
			byte[] data = CLUs.get(i).getbyte();
			dout.write(data); //sendet sein CloginUpdate Objekt an den Serve
			dout.close();
			client.close();
		}

	}

	/**
	 * Die send()-Methode ist zum Senden der Spiel-Daten notwendig.
	 * Mit dieser Methode werden die Spiel-Daten an alle Clients gesendet.
	 * Das passiert �ber den datagramSocketSend und das �DatagramPacket�.
	 * Jeder Client bekommt alle drei "cLoginUpdate-Objekte".
	 * An jeden Client werden die packets mit Updates vom laufenden Spiel gesendet.
	 */
	private void send() throws IOException {

		for (ClientData i : clientDatas) {
			byte[] sandData = ServerVerwaltung.sGetUpdateC().toByteArray();
			DatagramPacket packet = new DatagramPacket(sandData, sandData.length, i.getIa(), 3555);
			sendSocket.send(packet);
		}
	}

	private void portsTauschen() throws IOException {
		while (port.size() != 0) {
			Socket client = connect.accept();
			int currentClient = port.size() - 1;

			clientDatas[currentClient] = new ClientData(client.getInetAddress(), getFreePort());
			//neuer ClientData wird erstellt, seine IP und der port den der Client anspricht wird gespeichert

			OutputStream os = client.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);

			bw.write(String.valueOf(clientDatas[currentClient].getZugewiesenerPort()));
			bw.flush();
			System.out.println("Port wurde mit " + clientDatas[currentClient].getIa().getHostAddress() + " getauscht");
			//dem client wird mitgeteilt, welchen port er anzusprechen hat
			client.close();
		}
		connect.close(); //der TCP server wird jetzt nicht mehr gebraucht
	}

	private int getFreePort() {
		int p = port.get(port.size() - 1);
		port.remove(port.size() - 1);
		return p;
		//gibt einen noch nicht genutzen port zur�ck
	}
}