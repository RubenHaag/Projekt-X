import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
 * @author Oskar Moritz
 * @version 0.3
 */

public class Client extends Thread{
	
	private int port;
	private InetAddress ip;
	private Socket server = null;
	
	
	public Client(String addresse) {
		this.port = 3555;
		try {
			InetAddress ip = InetAddress.getByName(addresse);
			this.ip = ip;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
	} 

	
	
	public void run() {
		try {
			server = new Socket(ip,port);
			InputStream is = server.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			port = Integer.parseInt(br.readLine());
			System.out.println("Port erhalten: "+port);
			server.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
