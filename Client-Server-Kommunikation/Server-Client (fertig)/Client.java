import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @author Oskar Moritz
 * @version 2.0 
 * Die Klasse "Client" regelt client-seitig die Art der Verbindung. Für eine Verbindung werden sowohl das UDP als auch das TCP portocol benötigt.
 * Hier werden dasw UDP und das TCP protcol zusammengeführt.
 */
public class Client extends Thread{
  
  private int port;
  private InetAddress ip;
  private Socket server;
  private DatagramSocket datagramSocketSend;
  private UDPclientListener listener1,listener2,listener3;
  private cLoginUpdate login;
  private SUpdate supdate;
  private CUpdate cupdate;
  private UDPclientListenerLogin listenerLogin;
  
  /**
   * Dies ist der Konstruktor für die Klasse "Client". Hier wird die TCP Verbindung zum Server aufgebaut und die UDP Verbindung vorbereitet.
   * @param addresse IP-Addresse des Servers
   * @param login1 Das erste Login Objete was dann gesendet werden soll
   * @param login2 Das zweite Login Objete was dann gesendet werden soll
   * @param login3 Das dritte Login Objete was dann gesendet werden soll
   * @param s Das SUpdate Objete
   * @param c Das CUpdate Objete
   */ 
  public Client(String addresse, cLoginUpdate login1, cLoginUpdate login2, cLoginUpdate login3, SUpdate s, CUpdate c) { //die IP des servers
	this.login = login;
	this.supdate= s;
    this.port = 3555; //Anfangsport, um den Server zu erreichen
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
    listenerLogin = new UDPclientListenerLogin(3555, login1);
    listenerLogin.start();

    //ein neuer Thread wird gestartet um alle eingehenden Packete zu empfangen
    listener1 = new UDPclientListener(3555, supdate );
    listener1.start();
    try {
        sendLogin();
      } catch (IOException e) {
        e.printStackTrace();
      }
    //Problem
    try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
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
   *  Die send()-Methode ist zum Senden der Daten notwendig. 
   *  Mit dieser Methode werden Daten an den Server gesendet. 
   *  Das passiert über den datagramSocketSend und das „DatagramPacket“.
   */
  private void sendLogin() throws IOException {
    byte[] sandData = login.getbyte();
    DatagramPacket packet = new DatagramPacket( sandData, sandData.length, ip, port );
    datagramSocketSend.send(packet);      
  }
  /**
   *  Die send()-Methode ist zum Senden der Spiel-Daten notwendig. 
   *  Mit dieser Methode werden die Spiel-Daten an den Server gesendet. 
   *  Das passiert über den datagramSocketSend und das „DatagramPacket“.
   */
  private void send() throws IOException {
          byte[] sandData = cupdate.toByteArray();
          DatagramPacket packet = new DatagramPacket( sandData, sandData.length, ip, port );
          datagramSocketSend.send(packet);
  }

}

