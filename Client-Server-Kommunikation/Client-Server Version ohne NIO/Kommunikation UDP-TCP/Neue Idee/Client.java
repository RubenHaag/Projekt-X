import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/*
 * @author Oskar Moritz
 * @version 1.7 Zusammenführung von UDP und TCP Client
 */
public class Client extends Thread{
  
  private int port;
  private InetAddress ip;
  private Socket server;
  private DatagramSocket datagramSocketSend;
  private UDPserverListener listener1,listener2,listener3;
  private Update spieler1;
  
  
  public Client(String addresse) { //die IP des servers
    this.port = 3555; //anfangsport, um den Server zu erreichen
    try {
      //hier wird TCP verwendet um dem Client den Port zu geben, an den er senden soll
      ip = InetAddress.getByName(addresse);     
      server = new Socket(ip,port); 
      BufferedReader br = new BufferedReader(new InputStreamReader(server.getInputStream())); 
      port = Integer.parseInt(br.readLine()); //der anzusprechende Port wird aus dem Stream gelesen und "port" aktualisiert
      System.out.println(port);
      server.close();
      this.start();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  } 
  
  public void run() {
    try {
      datagramSocketSend =new DatagramSocket();
    } catch (SocketException e1) {
      e1.printStackTrace();
    }
    System.out.println(datagramSocketSend);
    //ein neuer Thread wird gestartet um alle eingehenden Packete zu empfangen
    listener1 = new UDPserverListener(3555);
    listener2 = new UDPserverListener(4409);
    listener3 = new UDPserverListener(4519);
    listener1.start();
    listener2.start();
    listener3.start();
    while ( true ){
      //zusätzlich sendet der CLient durch diese "while(true)" schleife alle 100ms eigene Packete
      try {
        send();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

     /**
       *  Die send()-Methode ist zum Senden, der Daten notwendig. 
       *  Mit dieser Methode werden Daten an den Server gesendet. 
       *  Dies passiert über den datagramSocketSend und das „DatagramPacket“.
       */
  private void send() throws IOException {
    byte[] sandData = spieler1.getbyte();
    DatagramPacket packet = new DatagramPacket( sandData, sandData.length, ip, port );
    datagramSocketSend.send(packet);      
  }
}

