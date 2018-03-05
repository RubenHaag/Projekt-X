import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerOneClient extends Thread{
	private ServerSocket connect;
	private ClientData clientDatas;
	//damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
	private ArrayList<Integer> port = new ArrayList<Integer>();
	private DatagramSocket sendSocket;
	private UDPserverListener listener1;
	
	public ServerOneClient() {
		//ein Port für jeden CLient
		try {
			connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
			portsTauschen();
		    sendSocket = new DatagramSocket( );
		    System.out.println("Kurz vor start");
			this.run();	//sobald alle freien Ports an verschiedene CLients vergeben wurden, startet die UDP Datenübertragung

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Mit allen Clients verbunden\nThread wird gestartet");
		//für jeden Client wird ein UDPserverListener gestartet
		  listener1 = new UDPserverListener(clientDatas.getZugewiesenerPort());
		  listener1.start();
		  
		  System.out.println("Threads Gestartet\n");
		  while ( true ){
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
	
	private void send() throws IOException {
		//an jeden Client werden Packetet mit Updates vom laufenden Spiel geschickt
	    byte[] sandData = "Hallo".getBytes();
	    DatagramPacket packet = new DatagramPacket( sandData, sandData.length, clientDatas.getIa(), 3555 );
	    sendSocket.send(packet);
	  }
	
	private void portsTauschen() throws IOException {
		
			Socket client = connect.accept();
			clientDatas = new ClientData(client.getInetAddress(), 3556);
			//neuer ClientData wird erstellt, seine IP und der port den der Client anspricht wird gespeichert
			
			OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            bw.write(String.valueOf(clientDatas.getZugewiesenerPort()));
            bw.flush();
            System.out.println("Port wurde mit " + clientDatas.getIa().getHostAddress() + " getauscht");
            //dem client wird mitgeteilt, welchen port er anzusprechen hat
            client.close();			
            connect.close(); //der TCP server wird jetzt nicht mehr gebraucht
            System.err.println("fertig mit portsTauschen()");
            return;
	}
}
