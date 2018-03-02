package IOServer;
import java.util.UUID;

public class cLoginUpdate extends Update {
  int k= 0;                                               //Spielernummer des clients, 0: Boss, 1:Spieler 1, 2:Spieler2,
  private int mode;                                       //an elchem Schritt befindet sich der Client
  private boolean istBoss;                                //ist Spieler Boss oder nicht
  private int charakter;
  private UUID UUID;
  private boolean spielStart;

  public cLoginUpdate(){
  }
  
  public cLoginUpdate(int mode, UUID UUID){
    this.mode=mode;
    this.UUID=UUID;
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
    return UUID;
  }
  
  public void setUUID(UUID UUID){
    this.UUID=UUID;
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
