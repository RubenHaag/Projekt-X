import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
 * @author Oskar Moritz
 * @version 1.0
 */

public class TCPclient extends Thread{
	
	private int port;
	private InetAddress ip;
	private Socket server = null;
	
	
	public TCPclient(String addresse) { //die IP des servers
		this.port = 3555; //anfangsport, um den Server zu erreichen
		try {
			InetAddress ip = InetAddress.getByName(addresse);
			this.ip = ip;
			
			server = new Socket(ip,port);
			
			InputStream is = server.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			port = Integer.parseInt(br.readLine()); //der anzusprechende Port wird aus dem Stream gelesen und "port" aktualisiert
			server.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	} 

}
