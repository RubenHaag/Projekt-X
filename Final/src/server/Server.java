package server;
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
 * Die Klasse "server" regelt server-seitig die Art der Verbindung. Für eine Verbindung werden sowohl das UDP als auch das TCP portocol benötigt.
 * Hier werden das UDP und das TCP protcol zusammengeführt.
 * 
 */
public class Server extends Thread {

	private ServerSocket connect;
	private server.ClientData[] clientDatas = new ClientData[3];
	//damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
	private ArrayList<Integer> port = new ArrayList<Integer>();
	private DatagramSocket sendSocket;
	private UDPserverListener listener1, listener2, listener3;
	// Die cLoginUpdate Objekte, die versendet werden
	private ArrayList<cLoginUpdate> CLUs = new ArrayList<cLoginUpdate>();
	private SUpdate supdate;
	private ServerVerwaltung serverVerwaltung;

	/**
	 * Dies ist der Konstruktor für die Klasse server. Hier wird die TCP Verbindung zu den drei clients aufgebaut und die UDP Verbindung vorbereitet.
     * @param login1 Das is das erste cLoginUpdate Objekte was versendet werden soll
     * @param login2 Das is das zweite cLoginUpdate Objekte was versendet werden soll
     * @param login3 Das is das dritte cLoginUpdate Objekte was versendet werden soll
     * @param serverVerwaltung Die ServerVerwaltung, die geändert werden soll
     */
	public Server(cLoginUpdate login1, cLoginUpdate login2, cLoginUpdate login3, ServerVerwaltung serverVerwaltung) {
		CLUs.add(login1);
		CLUs.add(login2);
		CLUs.add(login3);

        //Intern Port festlegen, ein Port für jeden CLient
		port.add(3556);
		port.add(3557);
		port.add(3558);
        this.serverVerwaltung = serverVerwaltung;
		try {
			connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
			portsTauschen();
			sendSocket = new DatagramSocket();
			this.run(); //sobald alle freien Ports an verschiedene CLients vergeben wurden, startet die UDP Daten�bertragung

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		System.out.println("Mit allen Clients verbunden\nThread wird gestartet");
		//für jeden Client wird ein UDPserverListener gestartet
        System.out.println(clientDatas[0].getZugewiesenerPort());


        listener1 = new server.UDPserverListener(clientDatas[0].getZugewiesenerPort(), serverVerwaltung);
		listener1.start();
		listener2 = new UDPserverListener(clientDatas[1].getZugewiesenerPort(), serverVerwaltung);
		listener2.start();
		listener3 = new UDPserverListener(clientDatas[2].getZugewiesenerPort(), serverVerwaltung);
		listener3.start();

		while (true) {
		    System.out.println("I am in the while true");
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


	/**
	 * Die send()-Methode ist zum Senden der Spiel-Daten notwendig.
	 * Mit dieser Methode werden die Spiel-Daten an alle Clients gesendet.
     * Das passiert über den datagramSocketSend und das DatagramPacket.	 * Jeder Client bekommt alle drei "cLoginUpdate-Objekte".
	 * An jeden Client werden die packets mit Updates vom laufenden Spiel gesendet.
     * @throws IOException Wenn die Verbindung zum Client zustandekommt.
	 */
	private void send() throws IOException {
	    System.out.println("P1:  "+ serverVerwaltung.getSpielerListe()[0].getpSelf().getHb().getPos().getXPos()+ "\t"+ serverVerwaltung.getSpielerListe()[0].getpSelf().getHb().getPos().getYPos());
        System.out.println("P2:  "+ serverVerwaltung.sUpdate.getP2().getGr().getPos().getXPos()+ "\t"+ serverVerwaltung.sUpdate.getP2().getGr().getPos().getYPos());
        System.out.println("P3:  "+ serverVerwaltung.sUpdate.getP3().getGr().getPos().getXPos()+ "\t"+ serverVerwaltung.sUpdate.getP3().getGr().getPos().getYPos());
        for (ClientData i : clientDatas) {
            byte[] sandData = serverVerwaltung.sGetUpdateC().toByteArray();
            DatagramPacket packet = new DatagramPacket(sandData, sandData.length, i.getIa(), 3555);
            sendSocket.send(packet);
            System.out.println("gesendet");
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