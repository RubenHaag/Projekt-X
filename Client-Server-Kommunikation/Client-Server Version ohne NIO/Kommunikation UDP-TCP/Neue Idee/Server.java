import java.io.ByteArrayOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.ObjectOutputStream;

   /*
   * @author Moritz Oskar 
   * @version 1.8 ZUsammenführung von UDP + TCP Server
   * 
   */
public class Server extends Thread{
  
  private ServerSocket connect;
  private ClientData[] clientDatas = new ClientData[3];
  //damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
  private ArrayList<Integer> port = new ArrayList<Integer>();
  private DatagramSocket sendSocket;
  private UDPserverListener listener1,listener2,listener3;
  // Die Spieler Objekte, die versendet werden.
  private cLoginUpdate spieler1, spieler2, spieler3;
  
  public Server() {
    port.add(3556);
    port.add(3557);
    port.add(3558);
    //ein Port für jeden CLient
    try {
      connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
      portsTauschen();
      sendSocket = new DatagramSocket( );
      this.run(); //sobald alle freien Ports an verschiedene CLients vergeben wurden, startet die UDP Datenübertragung
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void run() {
    
    System.out.println("Mit allen Clients verbunden\nThread wird gestartet");
    //für jeden Client wird ein UDPserverListener gestartet
    listener1 = new UDPserverListener(clientDatas[0].getZugewiesenerPort());
    listener1.start();
    listener2 = new UDPserverListener(clientDatas[1].getZugewiesenerPort());
    listener2.start();
    listener3 = new UDPserverListener(clientDatas[2].getZugewiesenerPort());
    listener3.start();
    
    while ( true ){
      try {
        send();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      try {
        Thread.sleep(100);  //servertickrate alle 100ms wird gesendet
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  private void send() throws IOException {
    //an jeden Client werden Packetet mit Updates vom laufenden Spiel geschickt
    // person 1
    for(ClientData i: clientDatas){
      
      byte[] sandData = spieler1.getbyte();
      DatagramPacket packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 3555 );
      sendSocket.send(packet);
      
      sandData = spieler2.getbyte();
      packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 4409 );
      sendSocket.send(packet);
      
      sandData = spieler3.getbyte();
      packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 4519 );
      sendSocket.send(packet);
    }
  }
  
  private void portsTauschen() throws IOException {
    while(port.size() != 0) {
      Socket client = connect.accept();
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
    connect.close(); //der TCP server wird jetzt nicht mehr gebraucht
  }
  
  private int getFreePort() {
    int p = port.get(port.size()-1);
    port.remove(port.size()-1);
    return p;
    //gibt einen noch nicht genutzen port zurück
  }
}
