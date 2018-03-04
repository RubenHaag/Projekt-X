//package IOServer;
import java.util.UUID;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class cLoginUpdate extends Update {
  int k= 0;                                               //Spielernummer des clients, 0: Boss, 1:Spieler 1, 2:Spieler2,
  private int mode;                                       //an elchem Schritt befindet sich der Client
  private boolean istBoss;                                //ist Spieler Boss oder nicht
  private int charakter;
  private UUID uuid;
  private boolean spielStart;
  /*
   * @author Oskar Moritz
   * getbyte
   * @return *Beschreibung*  
   */
  public byte[] getbyte() throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(baos);
      out.writeInt(k);
      out.writeInt(mode);
      out.writeBoolean(istBoss);
      out.writeInt(charakter);
      out.writeChars(uuid.toString());
      out.writeBoolean(spielStart);
      byte[] data = baos.toByteArray();
      return data;      
    }
  /*
   * @author Oskar Moritz
   * awaybyte
   * @param data *Beschreibung*
   */
    public void awaybyte(byte[] data) throws IOException {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      DataInputStream in = new DataInputStream(bais);
      k = in.readInt();
      mode = in.readInt();
      istBoss = in.readBoolean();  
      charakter = in.readInt();
      uuid= UUID.fromString(in.readUTF());
    }
  public cLoginUpdate(){
  }
  
  public cLoginUpdate(int mode, UUID uuid){
    this.mode=mode;
    this.uuid=uuid;
  }
  
  public int getMode(){
    return mode;
  }
  
  public void setMode(int mode){
    this.mode=mode;
  }
  
  public void modeErhoehen(){
    this.mode++;
  }
  
  public UUID getUUID(){
    return uuid;
  }
  
  public void setUUID(UUID uuid){
    this.uuid=uuid;
  }
  
  public boolean getIstBoss(){
    return istBoss;
  }
  
  public void setIstBoss(boolean istBoss){
    this.istBoss=istBoss;
    this.mode=1;
  }
  
  public void setCharakter(int c){
    this.charakter=c;
  }
  
  public int getCharakter(){
    return charakter;
  }
  
  public boolean getSpielStart(){
    return spielStart;
  }
  
  public void setSpielStart(boolean spielStart){
    this.spielStart=spielStart;
  }
  
}
