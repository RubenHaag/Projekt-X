package IOServer;
import java.util.UUID;

public class cLoginUpdate extends Update {
  private boolean ownIstBoss;                               //ist Spieler Boss oder nicht
  private boolean istBoss1;                                 //ist 1. anderer Spieler Boss oder nicht
  private boolean istBoss2;                                 //ist 2. anderer Spieler Boss oder nicht
  private int ownCharakter;									//eigener Charakter	
  private UUID ownUUID;										//eigene UUID
  private int s1Charakter;									//Charakter 1. anderer Spieler
  private UUID s1UUID;										//UUID 1.anderer Spieler
  private int s2Charakter;									//Charakter 2. anderer Spieler
  private UUID s2UUID;										//UUID 2. anderer Spieler
  private boolean spielStart;									//ob das Spiel schon startet

  public cLoginUpdate(){
  }
  
  public cLoginUpdate(int mode, UUID ownUUID){
    this.ownUUID=ownUUID;
  }
  
  public UUID getOwnUUID(){
    return ownUUID;
  }
  
  public void setOwnUUID(UUID ownUUID){
    this.ownUUID=ownUUID;
  }
  public UUID getS1UUID(){
    return ownUUID;
  }
	  
  public void setS1UUID(UUID ownUUID){
    this.ownUUID=ownUUID;
  }
 
 public UUID getS2UUID(){
    return ownUUID;
  }
  
  public void setS2UUID(UUID ownUUID){
    this.ownUUID=ownUUID;
  }
  
  public boolean getOwnIstBoss(){
    return ownIstBoss;
  }
  
  public void setOwnIstBoss(boolean ownIstBoss){
    this.ownIstBoss=ownIstBoss;
  }
 
 public boolean getS1IstBoss(){
    return ownIstBoss;
  }
  
  public void setS1IstBoss(boolean ownIstBoss){
    this.ownIstBoss=ownIstBoss;
  }
 
  public boolean getS2IstBoss(){
    return ownIstBoss;
  }
  
  public void setOwnS2Boss(boolean ownIstBoss){
    this.ownIstBoss=ownIstBoss;
  }
  
  public void setOwnCharakter(int c){
    this.ownCharakter=c;
  }
  
  public int getOwnCharakter(){
    return ownCharakter;
  }
  
  public void setS1Charakter(int c){
    this.ownCharakter=c;
  }
  
  public int getS1Charakter(){
    return ownCharakter;
  }
  
  public void setS2Charakter(int c){
    this.ownCharakter=c;
  }
  
  public int getS2Charakter(){
    return ownCharakter;
  }
 
  public boolean getSpielStart(){
	  return spielStart;
  }
  
  public void setSpielStart(boolean spielStart){
	  this.spielStart=spielStart;
  }
}
