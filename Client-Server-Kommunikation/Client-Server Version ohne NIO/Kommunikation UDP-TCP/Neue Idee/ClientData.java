import java.net.InetAddress;

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
