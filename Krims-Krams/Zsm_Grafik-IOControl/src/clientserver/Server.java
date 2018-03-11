package clientserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ioserver.*;

   /**
   * @author Moritz Oskar 
   * @version 2.0 
   * Die Klasse "server" regelt server-seitig die Art der Verbindung. Für eine Verbindung werden sowohl das UDP als auch das TCP portocol benötigt.
   * Hier werden das UDP und das TCP protcol zusammengeführt.
   * 
   */
public class Server extends Thread{
  
  private ServerSocket connect;
  private ClientData[] clientDatas = new ClientData[3]; 
  //damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
  private ArrayList<Integer> port = new ArrayList<Integer>();
  private DatagramSocket sendSocket;
  private UDPserverListener listener1,listener2,listener3;
  private UDPserverListenerLogin listenerLogin1,listenerLogin2,listenerLogin3;
  // Die cLoginUpdateIO Objekte, die versendet werden
  private cLoginUpdate login1, login2, login3;
  private SUpdate supdate;
  private CUpdate cupdate;
  
  /**
  * Dies ist der Konstruktor für die Klasse "server". Hier wird die TCP Verbindung zu den drei clients aufgebaut und die UDP Verbindung vorbereitet.
  * 
  */
  public Server(cLoginUpdate login1, cLoginUpdate login2, cLoginUpdate login3, SUpdate s, CUpdate c ) {
	this.login1 = login1;
	this.login2 = login2;
	this.login3 = login3;
	this.supdate= s;
	cupdate = c;
	//Intern Port festlegen, ein Port für jeden CLient
    port.add(3556);
    port.add(3557);
    port.add(3558);

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
    listenerLogin1 = new UDPserverListenerLogin(clientDatas[0].getZugewiesenerPort(), login1);
    listenerLogin1.start();
    listenerLogin2 = new UDPserverListenerLogin(clientDatas[1].getZugewiesenerPort(),login2);
    listenerLogin2.start();
    listenerLogin3 = new UDPserverListenerLogin(clientDatas[2].getZugewiesenerPort(),login3);
    listenerLogin3.start();
    try {
        sendLogin();
      } catch (IOException e) {
        e.printStackTrace();
      }
    //Problem?
    listener1 = new UDPserverListener(clientDatas[0].getZugewiesenerPort(), cupdate);
    listener1.start();
    listener2 = new UDPserverListener(clientDatas[1].getZugewiesenerPort(), cupdate);
    listener2.start();
    listener3 = new UDPserverListener(clientDatas[2].getZugewiesenerPort(), cupdate);
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
  /**
   *  Die sendLogin()-Methode ist zum Senden der Login-Daten notwendig. 
   *  Mit dieser Methode werden die Login-Daten an alle Clients gesendet. 
   *  Das passiert über den datagramSocketSend und das „DatagramPacket“.
   *  Jeder Client bekommt alle drei "cLoginUpdateIO-Objekte".
   *  An jeden Client werden die packets mit Updates vom laufenden Spiel gesendet.
   */
  private void sendLogin() throws IOException {
    for(ClientData i: clientDatas){
        byte[] sandData = login1.getbyte();
        DatagramPacket packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 3555 );
        sendSocket.send(packet);
        
        sandData = login2.getbyte();
        packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 4409 );
        sendSocket.send(packet);
        
        sandData = login3.getbyte();
        packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 4519 );
        sendSocket.send(packet);
    	}
    }
  /**
   *  Die send()-Methode ist zum Senden der Spiel-Daten notwendig. 
   *  Mit dieser Methode werden die Spiel-Daten an alle Clients gesendet. 
   *  Das passiert über den datagramSocketSend und das „DatagramPacket“.
   *  Jeder Client bekommt alle drei "cLoginUpdateIO-Objekte".
   *  An jeden Client werden die packets mit Updates vom laufenden Spiel gesendet.
   */
    private void send() throws IOException {
        for(ClientData i: clientDatas){
            byte[] sandData = supdate.toByteArray();
            DatagramPacket packet = new DatagramPacket( sandData, sandData.length, i.getIa(), 3555 );
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
