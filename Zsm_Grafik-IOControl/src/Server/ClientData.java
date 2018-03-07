
package netzwerk;
import java.net.InetAddress;

/**
   * @author Oskar Moritz
   * @version 1.2 
   * Der Server besitzt 3 ClientData um spezielle CLientinformationen zu speichern
   */
public class ClientData {
  private InetAddress ia;
  private int zugewiesenerPort;
  public ClientData(InetAddress ia, int zugewiesenerPort) {
    this.setIa(ia);
    this.setZugewiesenerPort(zugewiesenerPort);
  }
  
  
  public int getZugewiesenerPort() {
    return zugewiesenerPort;
  }
  public void setZugewiesenerPort(int zugewiesenerPort) {
    this.zugewiesenerPort = zugewiesenerPort;
  }


  public InetAddress getIa() {
    return ia;
  }


  public void setIa(InetAddress ia) {
    this.ia = ia;
  }
}

