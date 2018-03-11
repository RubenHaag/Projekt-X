import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class MyDatagramServerExample {
 private static final int BUFFER_SIZE = 1024;
 static int port = 11;

 public static void main(String[] args) throws IOException {
  logger("Starting MyDatagramServerExample...");
  InetAddress hostIP = InetAddress.getLocalHost();
  InetSocketAddress address = new InetSocketAddress(hostIP, port);
  DatagramChannel datagramChannel = DatagramChannel.open();
  DatagramSocket datagramSocket = datagramChannel.socket();
  datagramSocket.bind(address);

  // Allocate a byte buffer
  ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

  while (true) {
   datagramChannel.receive(buffer);
   buffer.flip();
   System.out.print("\nData...: ");
   while (buffer.hasRemaining()) {
    System.out.write(buffer.get());
   }
   buffer.clear();
  }
 }

 public static void logger(String msg) {
  System.out.println(msg);
 }
}
