import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
 * @author Oskar Moritz
 * @version 0.2.1
 */

public class Client extends Thread{
	
	private int port;
	private Spieler sself; //bekommen wir von Lukas im z.b. Konstruktor
	private InetAddress ip;

	
	
	public Client(String addresse, int port, Spieler s) {
		this.sself = s;
		this.port = port;
		try {
			InetAddress ip = InetAddress.getByName(addresse);
			this.ip = ip;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		hello();
	} 

	
	
	public void run() {
		while(true) {
			send();
			read();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	private void send(){

	}
	
	private void read() {
		
	}
	
	private void hello() {
		System.out.println("Connect to Server...");
		//Shakehand-Aktion. Es wird sich auf den Port geeinig, an den gesendet wird
		String id = "Krokodeal"; //Der echte Username sollte mit get...() ausgelesen werden
		byte[] buffer = id.getBytes();
		byte[] get = new byte[1024];
		
		DatagramSocket toSocket = null;
		DatagramSocket getSocket = null;
		try {
			toSocket = new DatagramSocket();
			getSocket = new DatagramSocket(3555);
			while(true) {
				
				
				DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, ip, port);
				toSocket.send(sendPacket);
				DatagramPacket getPacket = new DatagramPacket(get,get.length);
				getSocket.receive(getPacket);
				
				if(getPacket.getData().length != 0) {
					port = getPacket.getPort();
					ip = getPacket.getAddress();
					System.out.println("Connected");
					toSocket.close();
					getSocket.close();
					return;
				}
				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
