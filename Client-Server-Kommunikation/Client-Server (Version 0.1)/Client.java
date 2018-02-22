import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
 * @author Oskar Moritz
 * @version 0.1
 */

public class Client extends Thread{
	
	private int port;
	private DatagramChannel c1;
	private SocketAddress address;
	private ByteBuffer bb;
	private Spieler sself; //bekommen wir von Lukas im z.b. Konstruktor

	
	
	public Client(String addresse, int port, Spieler s) {
		this.sself = s;
		this.port = port;
		bb = ByteBuffer.allocate(1024);
		SocketAddress address  = new InetSocketAddress(addresse, port);
		this.address = address;
		connect();
	} 

	
	
	public void run() {
		while(true) {
			System.out.println("Going to send soon");
			send();
			read();
			try {
				Thread.sleep(1000);
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
			System.out.println("der sendbuffer enthält daten");
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
			c1.is
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("kurz vor if abfrage");
		if(bb.position() != 0) {
			bb.flip();
			System.out.println("first Int: "+bb.getInt());
			System.out.println("first double: "+bb.getDouble());
			//sself.setUUID(bb.getInt());
			//sself.setZahl(bb.getDouble());
		}
		bb.clear();
	}
	
	private void connect() {
		try {
			c1 = DatagramChannel.open();
			c1.configureBlocking(false);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Connected");

		

		
	}

}
