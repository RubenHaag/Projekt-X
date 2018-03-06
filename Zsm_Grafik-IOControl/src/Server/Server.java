package Server;

import ioserver.cLoginUpdate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

   /**
   * @author Moritz Oskar 
   * @version 2.0 
   * Die Klasse "Server" regelt server-seitig die Art der Verbindung. F�r eine Verbindung werden sowohl das UDP als auch das TCP portocol ben�tigt.
   * Hier werden das UDP und das TCP protcol zusammengef�hrt.
   * 
   */
public class Server extends Thread{
  
  private ServerSocket connect;
  private ClientData[] clientDatas = new ClientData[3]; 
  //damit die while-schleife aus portsTauschen() nicht so kompliziert ist, erstelle ich ein Array zum speichern der CLientObjekte
  private ArrayList<Integer> port = new ArrayList<Integer>();
  private DatagramSocket sendSocket;
  private UDPserverListener listener1,listener2,listener3;
  // Die cLoginUpdate Objekte, die versendet werden
  private cLoginUpdate spieler1, spieler2, spieler3;
  
  /**
  * Dies ist der Konstruktor f�r die Klasse "Server". Hier wird die TCP Verbindung zu den drei clients aufgebaut und die UDP Verbindung vorbereitet.
  * 
  */
  public Server() {
    spieler1 = new cLoginUpdate();
    spieler2 = new cLoginUpdate();
    spieler3 = new cLoginUpdate();
    port.add(3556);
    port.add(3557);
    port.add(3558);
    //ein Port f�r jeden CLient
    try {
      connect = new ServerSocket(3555); //zu Anfang verbinden sich alle Clients mit diesem Socket
      portsTauschen();
      sendSocket = new DatagramSocket( );
      this.run(); //sobald alle freien Ports an verschiedene CLients vergeben wurden, startet die UDP Daten�bertragung
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public void run() {
    System.out.println("Mit allen Clients verbunden\nThread wird gestartet");
    //f�r jeden Client wird ein UDPserverListener gestartet
    listener1 = new UDPserverListener(clientDatas[0].getZugewiesenerPort(), spieler1);
    listener1.start();
    listener2 = new UDPserverListener(clientDatas[1].getZugewiesenerPort(), spieler2);
    listener2.start();
    listener3 = new UDPserverListener(clientDatas[2].getZugewiesenerPort(), spieler3);
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
   *  Die send()-Methode ist zum Senden der Daten notwendig. 
   *  Mit dieser Methode werden Daten an alle Clients gesendet. 
   *  Das passiert �ber den datagramSocketSend und das �DatagramPacket�.
   *  Jeder Client bekommt alle drei "cLoginUpdate-Objekte".
   *  An jeden Client werden die packets mit Updates vom laufenden Spiel gesendet.
   */
  private void send() throws IOException {
    
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
    //gibt einen noch nicht genutzen port zur�ck
  }
  
//public Methoden f�r den zugriff von au�en
  /**
   *  Die setSpielerData-Methode speichert die Objekte, die versendet werden sollen, im Server und bereitet sie zum Versenden vor.
   *  @param s1 das erste cLoginUpdate Objekte
   *  @param s2 das zweite cLoginUpdate Objekte
   *  @param s3 das dritte cLoginUpdate Objekte
   *  @since 2.0
   */
  public void setSpielerData(cLoginUpdate s1, cLoginUpdate s2, cLoginUpdate s3) {
    spieler1=s1;
    spieler2=s2;
    spieler3=s3;
  }
  /**
   *  Die getcLoginUpdateObjOne()-Methode gibt das empfangene Objekt f�r den listener1 aus.
   *  @return listener1.getSpielerData();
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjOne() {
  return listener1.getSpielerData();
  }
  /**
   *  Die getcLoginUpdateObjOne()-Methode gibt das empfangene Objekt f�r den listener2 aus.
   *  @return listener2.getSpielerData();
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjTwo() {
    return listener2.getSpielerData();
  }
  /**
   *  Die getcLoginUpdateObjOne()-Methode gibt das empfangene Objekt f�r den listener3 aus.
   *  @return listener3.getSpielerData();
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjThree() {
    return listener3.getSpielerData();
  }
  
}
