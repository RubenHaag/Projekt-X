package ioserver;

import java.util.UUID;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class cLoginUpdateIO {
  int k= 0;                                               //Spielernummer des clients, 0: Boss, 1:Spieler 1, 2:Spieler2,
  private int mode;                                       //an elchem Schritt befindet sich der Client
  private boolean istBoss;                                //ist Spieler Boss oder nicht
  private int charakter;
  private UUID uuid = UUID.randomUUID();
  private boolean spielStart;
  /**
   * @author Oskar Moritz
   * Die Methode "getbyte" erstellt aus allen Attributen der Klasse "cLoginUpdateIO" einen Byte Array.
   * Diese Methode wird f�r das Versenden eines "cLoginUpdateIO-Objekts" verwendet.
   * @return data ist der erstellte Byte Array. Er enth�lt alle Attribute der Klasse.
   */
  public byte[] getbyte() throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(baos);
      out.writeInt(k);
      out.writeInt(mode);
      out.writeBoolean(istBoss);
      out.writeInt(charakter);
      System.out.println(uuid.toString());
      out.writeChars(uuid.toString());
      out.writeBoolean(spielStart);
      byte[] data = baos.toByteArray();
      return data;      
    }
  /**
   * @author Oskar Moritz
   * Die Methode "awaybyte" wandelt einen Byte Array in die Attribute der Klasse um.
   * Diese Methode wird zum Umwandeln von empfangenen Daten verwendet.
   * @param data ist ein Byte Array. 
   */
    public void awaybyte(byte[] data) throws IOException {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      DataInputStream in = new DataInputStream(bais);
      k = in.readInt();
      mode = in.readInt();
      istBoss = in.readBoolean();  
      charakter = in.readInt();
      System.out.println(in.readUTF());
      uuid= UUID.fromString(in.readUTF());
    }
  public cLoginUpdateIO(){
  }
  
  public cLoginUpdateIO(int mode, UUID uuid){
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
