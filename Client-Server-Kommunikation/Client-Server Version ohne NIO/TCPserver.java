import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TCPserver {
	/*
	 * @author Oskar 
	 * @version 1.0
	 */
	private ServerSocket connect;
	private ClientData[] clientDatas = new ClientData[3];
	//damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
	private ArrayList<Integer> port = new ArrayList<Integer>();
	
	public TCPserver() {
		port.add(3556);
		port.add(3557);
		port.add(3558);
		//ein Port für jeden CLient
		try {
			connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
			portsTauschen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void portsTauschen() throws IOException {
		while(port.size() != 0) {
			//connect.setSoTimeout( 600 ); // Timeout nach 1 Minute	
			Socket client = connect.accept();
            System.out.println("connect.accept() vorrüber");
			int currentClient = port.size()-1;
			
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
		connect.close();
	}
	
	private int getFreePort() {
		int p = port.get(port.size()-1);
		port.remove(port.size()-1);
		return p;
		//gibt einen noch nicht genutzen port zurück
	}
}
