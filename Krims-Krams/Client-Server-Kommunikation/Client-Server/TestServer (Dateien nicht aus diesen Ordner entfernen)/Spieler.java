

public class Spieler {
  private String name;
  private int UUID;
  private double zahl;
  private float wert;
  private boolean istDa;
  public Spieler() {
    name="Peter";
    UUID= 123455;
    zahl=123.123;
    wert=2;
    istDa=true;
  }
  
  public Spieler(String n, int u, double z, float w, boolean da) {
    name=n;
    UUID=u;
    zahl=z;
    wert=w;
    istDa=da;
  }
  
  public void setUUID(int UUID) {
    this.UUID = UUID;
  }
  public void setZahl(double zahl) {
    this.zahl = zahl;
  }
  public int getUUID() {
    return UUID;
  }
  public double getZahl() {
    return zahl;
  }
  public String getname() {
    return name;
  }
  

}
