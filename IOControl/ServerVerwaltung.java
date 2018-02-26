import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.lang.Thread;
/**
 * 
 * @author Lukas Hofmann, Patrick Waltermann , Max Schulte
 *
 */
public class ServerVerwaltung {
  private GameManager[] spielerListe = new GameManager[3];
  private int i = 0;
  private int k = 0;
  private boolean s1=false;          //s1,s2,b : welche rolle ist schon vergeben, wird beim login benÃ¶tigt (s:Spieler b:Boss)
  private boolean s2=false;
  private boolean b=false;
  int n = 0;   			     // n: Anzahl der eingeloggten Spieler
  GameManager spieler1=new GameManager();
  GameManager spieler2=new GameManager();
  GameManager boss=new GameManager();
  private Attack amP1, amP2, amP3;
  long last_time = System.nanoTime();
  private double dt;
  public ServerVerwaltung(){

	  
  }
  
  public void sAttack(UUID id,Attack am){
    //überprüfen ob möglich
    int y = sGetNumberID(id);
    for(int i = 0; i <= spielerListe.length; i++){
      GameManager local = spielerListe[i];
      if(am.getDamageBox().intersect(local.getpSelf().getHb())){
        local.getpSelf().setHitted(true);
        local.getpSelf().setHealth(local.getpSelf().getHealth()-spielerListe[y].getAmall().getDamage());
        //TODO Klappt noch nicht
        }

      //int y = sGetNumberID(id);
      //local.cAttackg(y);
      //TODO hit usw. muss man noch einfügen
    }
  }
  /**
   * ÃœberprÃ¼ft die UUID mit der der Clients und liefert die interne ID zurÃ¼ck
   * @param id UUID
   * @return Interne ID
   */
  public int sGetNumberID(UUID id){
    int x = 5;//absichtlich falscher Int
    for(int i = 0; i <= spielerListe.length; i++){
      GameManager local = spielerListe[i];
      if(local.cGetUUID() == id){
        return local.cGetNumberID();
      }
    }
    return x;
  }
  /**
   * Initiert das Spiel
   */
  public void sStartGame(){
      Timer timer = new Timer();
      timer.schedule(new TimerTask() {
          public void run() {
              long time = System.nanoTime();
              dt = (double)((time - last_time));
              last_time = time;
              sUpdateHealth();
              sUpdateCooldown();
              sUpdateMana();
              for(int i = 0; i <= spielerListe.length; i++) {
                  GameManager local = spielerListe[i];
                  if (local.getpSelf().isAttacking()){ sAttack(local.cGetUUID(),local.getAmall());}
              }
          }
      }, 0, 100);
    
  }
  /**
   * 
   * @param g Der Client der die Methode aufruft
   * @return
   */
  public boolean sLogin(GameManager g){
    int n=0;                   //n: wie viele angemeldete Spieler
    boolean s1=false;          //s1,s2,b : wlecvhe rolle ist schon vergeben (s:Spieler b:Boss)
    boolean s2=false;
    boolean b=false;
    double z=Math.random();

    if (n==0&&z<=0.333333) {
      spielerIstBoss(g);
    } // end of if
    else if (n==0&&z>0.333333&&z<=0.666666) {
      spielerIst1(g);
    } // end of if
    else if (n==0&&z>0.666666) {
      spielerIst2(g);
    } // end of if

    else if (n==1&&b&&z<=0.5) {
      spielerIst1(g);
    } // end of if
    else if (n==1&&s1&&z<=0.5) {
      spielerIstBoss(g);
    } // end of if
    else if (n==1&&s2&&z<=0.5) {
      spielerIstBoss(g);
    } // end of if
    
    else if (n==1&&b&&z>0.5) {
      spielerIst2(g);
    } // end of if
    else if (n==1&&s1&&z>0.5) {
      spielerIst2(g);
    } // end of if
    else if (n==1&&s2&&z>0.5) {
      spielerIst1(g);
    } // end of if

    else if (n==2&&s1&&s2) {
      spielerIstBoss(g);
    } // end of if
    else if (n==2&&b&&s1) {
      spielerIst2(g);
    } // end of if
    else if (n==2&&b&&s2) {
      spielerIst1(g);
    } // end of if

    if (n==3) {
      //double aktZeit=System.currentTimeMillis();
      String s="Alle Spieler eingeloggt! Start in 15s!";
      for (int l=0; l<spielerListe.length; l++ ){
      spielerListe[l].zeigen(s);
        spielerListe[l].zeigen(s);
      }
      try{
        Thread.sleep(15000);
      }catch (InterruptedException e){}
      //Spielstart
      
      for (int j=0; j<spielerListe.length; j++){
        spielerListe[j].spielstart();
      }
    } // end of if
      
   return(true) ;
  }
  
 /* spielerIstBoss,spielerIst1,spielerIst2 werden beim Login aufgerufen,
 *  getrennt für bessere Übersichtlichkeit
 * @param Gamemanager, der die Login funktion aufgerufen hat und sich gerade einloggt
 */
  private void spielerIstBoss(GameManager g){// Das ist ein Konstruktor.. soll das ne Methode sein?
    spielerListe[0]=g;
    g.cSetBoss(true,0);
    n++;
    b=true;
    g.auswahlBoss();                //Boss auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
    //boss.loginErfolgreich(true);
  }
  private void spielerIst1(GameManager g){
    spielerListe[1]=g;
    g.cSetBoss(false,1);
    n++;
    s1=true;
    g.auswahlSpieler();             //Spieler auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
    //spieler1.loginErfolgreich(true);
  }
  private void spielerIst2(GameManager g){
    spielerListe[2]=g;
    g.cSetBoss(true,2);
    n++;
    s2=true;
    g.auswahlSpieler();             //Spieler auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
    //spieler2.loginErfolgreich(true);
  }
  
  
  //private void setSkin (int Skin, Gamemanager g){   //skin entweder int oder enum
  //  g.setSkin(skin);
  //}
 


  /**
   * Methode die vom Client aufgerufen wird  um den austritt aus der Session zu signalisieren
   * @param gameManager Client, der sich ausgeloggt hat
   * allen spielern wird gesagt, wer sich ausgeloggt hat, danach kehren alle Spieler zum HHauptbildschirm zurück
   */
  public void sLogout(GameManager gameManager) {
    String s="";
    if (gameManager.cGetUUID()==spielerListe[1].cGetUUID()) {
      s="Spieler 1";
    } // end of if
    if (gameManager.cGetUUID()==spielerListe[2].cGetUUID()) {
      s="Spieler 2";
    } // end of if
    if (gameManager.cGetUUID()==spielerListe[0].cGetUUID()) {
      s="Boss";
    } // end of if
    spielerListe[0].logout(s);
    spielerListe[1].logout(s);
    spielerListe[2].logout(s);
  }
  /**
   * Bewegt die Spieler
   */
  public void sMove() {
    //prÃ¼ft ob mÃ¶glich
    
  }
  /**
   * generelle Update Methode

   */
  public void sUpdate(){
    
  }
  
  public void sUpdateHealth() {
      for (int i = 0; i <= spielerListe.length; i++) {
          GameManager local = spielerListe[i];
          if (local.getpSelf().getHealth() < 100) {
              local.getpSelf().setHealth(local.getpSelf().getHealth() + dt/1000000000 *0.01* local.getpSelf().getRegSpeed());
          }
      }
  }
  public void sUpdateCooldown() {
      for (int i = 0; i <= spielerListe.length; i++) {
          GameManager local = spielerListe[i];
          if(local.getpSelf().getAmSpec1().getCooldown()>0){
            local.getpSelf().getAmSpec1().setCooldown(local.getpSelf().getAmSpec1().getCooldown()-dt/1000000000);
          }
          else {local.getpSelf().getAmSpec1().setCooldown(0);}
          if(local.getpSelf().getAmSpec2().getCooldown()>0){
              local.getpSelf().getAmSpec2().setCooldown(local.getpSelf().getAmSpec2().getCooldown()-dt/1000000000);
          }
          else {local.getpSelf().getAmSpec2().setCooldown(0);}
      }
  }
    public void sUpdateMana() {
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (local.getpSelf().getMana() < 100) {
                local.getpSelf().setMana(local.getpSelf().getMana() + dt/1000000000 *0.01* local.getpSelf().getRegSpeed());
            }
        }
    }

}
