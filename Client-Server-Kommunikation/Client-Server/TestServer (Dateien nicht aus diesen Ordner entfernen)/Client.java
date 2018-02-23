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
	private DatagramChannel c1;
	private SocketAddress address;
	private ByteBuffer bb;
	private Spieler sself; //bekommen wir von Lukas im z.b. Konstruktor
	private InetAddress ip;

	
	
	public Client(String addresse, int port, Spieler s) {
		this.sself = s;
		this.port = port;
		bb = ByteBuffer.allocate(1024);
		try {
			InetAddress ip = InetAddress.getByName(addresse);
			this.ip = ip;
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		SocketAddress address  = new InetSocketAddress(addresse, port);
		this.address = address;
		
		try {
			c1 = DatagramChannel.open();
			c1.configureBlocking(false);

		} catch (IOException e) {
			e.printStackTrace();
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
		bb.clear();
		bb.putInt(sself.getUUID());
		bb.putDouble(sself.getZahl());
		if(bb.position() != 0) {
			//System.out.println("der sendbuffer enthält daten");
		}
		
		bb.flip();
		try {
			c1.send(bb, address);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bb.clear();	
	}
	
	private void read() {
		try {
			c1.receive(bb);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(bb.position() != 0) {
			bb.flip();
			System.out.println("first Int: "+bb.getInt());
			System.out.println("first double: "+bb.getDouble());
			//sself.setUUID(bb.getInt());
			//sself.setZahl(bb.getDouble());
		}
		bb.clear();
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
