

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/*
 * @author Oskar Moritz
 * @version 2.0 Zusammenführung von UDP und TCP Client
 */
public class Client extends Thread{
  
  private int port;
  private InetAddress ip;
  private Socket server;
  private DatagramSocket datagramSocketSend;
  private UDPserverListener listener1,listener2,listener3;
  private cLoginUpdate spieler1, spieler2, spieler3;
  
  
  public Client(String addresse) { //die IP des servers
    spieler1 = new cLoginUpdate();
    spieler2 = new cLoginUpdate();
    spieler3 = new cLoginUpdate();
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
    listener1 = new UDPserverListener(3555, spieler1);
    listener2 = new UDPserverListener(4409, spieler2);
    listener3 = new UDPserverListener(4519, spieler3);
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
  
  
  //public Methoden für den zugriff von außen
  /**
   *  Die setSpielerData(cLoginUpdate s1, cLoginUpdate s2, cLoginUpdate s3)-Methode ist dafür da, das die Objekte, die versendet werden sollen, in dem Server gespeichert werden.
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
   *  Die getcLoginUpdateObjOne()-Methode ist dafür da, das die Objekte, die versendet wurden, ausgegeben werden können. Für den Spieler eins.
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjOne() {
  return listener1.getSpielerData();
  }
  /**
   *  Die getcLoginUpdateObjTwo()-Methode ist dafür da, das die Objekte, die versendet wurden, ausgegeben werden können. Für den Spieler eins.
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjTwo() {
    return listener2.getSpielerData();
  }
  /**
   *  Die getcLoginUpdateObjThree()-Methode ist dafür da, das die Objekte, die versendet wurden, ausgegeben werden können. Für den Spieler eins.
   *  @since 1.5
   */
  public cLoginUpdate getcLoginUpdateObjThree() {
    return listener3.getSpielerData();
  }
}

