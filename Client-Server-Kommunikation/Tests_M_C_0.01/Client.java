import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/**
 * @author Moritz Gerlach
 * @version 0.00.1
 * 
 *
 */
public class Client implements Runnable {
  
    private Selector selector;
    private String address = "";
    private int port;
    /**
     * Konstruktor für die Klasse Client (ohne eingeben)
     * 
     */
    public Client(){
         /**
         * Der Port bei dem getestet wurde
         * @since 0.00.1
         */
        this.port =8512;
        /**
         * Die address bei dem getestet wurde
         * @since 0.00.1
         */
        this.address= "localhost";
    }
    /**
     * Konstruktor fuer die Klasse Client
     * @param port der Port fuer die Verbindung
     * @param address andesse fuer die Verbindung
     */
    public Client(int port, String address){
        this.port =port;
        this.address= address;
    }
    
    @Override
    public void run() {
        SocketChannel channel;
        try {
            selector = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);

            channel.register(selector, SelectionKey.OP_CONNECT);
            channel.connect(new InetSocketAddress(address, port));
            System.out.println("b");

            while (!Thread.interrupted()){
               
                selector.select(1000);
                 
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

                while (keys.hasNext()){
                    SelectionKey key = keys.next();
                    keys.remove();

                    if (!key.isValid()) continue;

                    if (key.isConnectable()){
                        System.out.println("I am connected to the server");
                        connect(key);
                    }   
                }   
            }
                
        } catch (IOException e1) {
                e1.printStackTrace();
        } finally {
            close();
        }
   }
    /**
     *  Metode zum beenden der verbindung
     */
    private void close(){
        try {
            selector.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     *  Metode zum Verbinden
     */
    private void connect(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending()){
            channel.finishConnect();
        }
    }
    

}
