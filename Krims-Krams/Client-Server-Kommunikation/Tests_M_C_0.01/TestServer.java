import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Dies ist eine Datei die nur zum Testen entwickelt wurde.
 * @author Moritz Gerlach
 * @version 1.0
 * 
 */

public class TestServer implements Runnable {
	private String address; 
    private int port;
    private ServerSocketChannel serverChannel;
    private Selector selector;
    
    public TestServer() {
    	/**
         * Der Port bei dem getestet wurde
         * @since 1.0
         */
        this.port =8512;
        /**
         * Die address bei dem getestet wurde
         * @since 1.0
         */
        this.address= "localhost";
    	initializing();
	}
    public TestServer(int port, String address) {
    	this.port =port;
        this.address= address;
    	initializing();
	}
    /**
     * initialisieren den Server. Eröffent den server noch nicht!
     * @since 1.0
     */
    private void initializing(){
        if (selector != null) { 
        	return;
        	}
        if (serverChannel != null) {
        	return;
        	}
        try {
            // open a Selector
            selector = Selector.open();
            // open a ServerSocketChannel
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            // set up address, port.
            serverChannel.socket().bind(new InetSocketAddress(address, port));
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("initializing server");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("server does not initializing!");
            System.err.println("the following has not been opened: Selector, ServerSocketChannel");
        }
        
    
    }
    @Override
	public void run() {
    	 System.out.println("Server laeuft...");
         // A run the server as long as the thread is not interrupted.
		 while (!Thread.currentThread().isInterrupted()){
  
		    
		 }
    }
}
