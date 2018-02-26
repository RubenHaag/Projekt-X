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
  GameManager spieler1=new GameManager();
  GameManager spieler2=new GameManager();
  GameManager boss=new GameManager();
  private Attack amP1, amP2, amP3;
  
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
      else{

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
              sUpdateHealth();
              for(int i = 0; i <= spielerListe.length; i++) {
                  GameManager local = spielerListe[i];
                  if (local.getpSelf().isAttacking()){
                      sAttack(local.cGetUUID(),local.getAmall());
                  }
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
    /*if(i > 2) {return false;}
    spielerListe [i] = g;
    System.out.println("Yeah!");
    i++;
    if (k == 0){
    g.cSetBoss(true, k);
    k++;
    }else{
      g.cSetBoss(false, k);
      k++;
      
    }
    return true;   */


    //login der Spieler mit Zufallsauswahl ob der Spieler ein Spieler oder der Boss wird
    int n=0;                   //n: wie viele angemeldete Spieler
    boolean s1=false;          //s1,s2,b : wlecvhe rolle ist schon vergeben (s:Spieler b:Boss)
    boolean s2=false;
    boolean b=false;
    double z=Math.random();
    
    /*
    if (n==0&&z<=0.333333) {
      spielerIstBoss(g);
      /*spielerListe[0]=g;
      g.cSetBoss(true,0);
      n++;
      b=true;
      //spieler1.loginErfolgreich(true);
      *
    } // end of if
    else if (n==0&&z>0.333333&&z<=0.666666) {
      spielerIst1(g);
      /*spielerListe[1]=g;
      g.cSetBoss(false,1);
      n++;
      s1=true;
      //spieler2.loginErfolgreich(true);
      *
    } // end of if
    else if (n==0&&z>0.666666) {
      spielerIst2(g);
      /*spielerListe[2]=g;
      g.cSetBoss(false,2);
      n++;
      s2=true;
      //boss.loginErfolgreich(false);
      *
    } // end of if

    else if (n==1&&b&&z<=0.5) {
      spielerIst1(g);
      /*spielerListe[1]=g;
      g.cSetBoss(false,1);
      n++;
      s1=true;
      //spieler1.loginErfolgreich(true);
      *
    } // end of if
    else if (n==1&&s1&&z<=0.5) {
      spielerIst2(g);
      /*spielerListe[0]=g;
      g.cSetBoss(true,0);
      n++;
      b=true;
      //spieler2.loginErfolgreich(true);
      *
    } // end of if
    else if (n==1&&s2&&z<=0.5) {
      spielerIstBoss(g)
      /*spielerListe[0]=g;
      g.cSetBoss(true,0);
      n++;
      b=true;
      //boss.loginErfolgreich(true);
      *
    } // end of if
    else if (n==1&&b&&z>0.5) {
      spielerIst2(g);
      /*spielerListe[2]=g;
      g.cSetBoss(false,2);n++;
      n++;
      s2=true;
      //spieler2.loginErfolgreich(true);
      *
    } // end of if
    else if (n==1&&s1&&z>0.5) {
      spielerIst2(g);
      /*spielerListe[2]=g;
      g.cSetBoss(false,2);
      n++;
      s2=true;
      //spieler2.loginErfolgreich(true);
      *
    } // end of if
    else if (n==1&&s2&&z>0.5) {
      spielerIst1(g);
      /*spielerListe[1]=g;
      g.cSetBoss(false,1);
      n++;
      s1=true;
      //spieler1.loginErfolgreich(true);
      *
    } // end of if

    else if (n==2&&s1&&s2) {
      spielerIstBoss(g);
      /*spielerListe[0]=g;
      g.cSetBoss(true,0);
      n++;
      b=true;
      //boss.loginErfolgreich(false);
      *
    } // end of if
    else if (n==2&&b&&s1) {
      spielerIst2(g);
      /*spielerListe[2]=g;
      g.cSetBoss(false,2);
      n++;
      s2=true;
      //spieler2.loginErfolgreich(true);
      *
    } // end of if
    else if (n==2&&b&&s2) {
      spielerIst1(g)
      /*spielerListe[1]=g;
      g.cSetBoss(false,1);
      n++;
      s1=true;
      spieler1.loginErfolgreich(true);
      */
    } // end of if
    */
    
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
      double aktZeit=System.currentTimeMillis();
      String s="Alle Spieler eingeloggt! Start in 15s!";
      spieler1.zeigen(s);
      spieler2.zeigen(s);
      boss.zeigen(s);
      Thread.sleep(15000);
      //Spielstart
      for (int j; j<spielerListe.length; j++){
        spielerListe[j].spielstart;
    } // end of if
  }
  
 /* spielerIstBoss,spielerIst1,spielerIst2 werden beim Login aufgerufen,
 *  getrennt fÃ¼r bessere Ãœbersichtlichkeit
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
   * @param gameManager Client
   */
  public void sLogout(GameManager gameManager) {
    String s="";
    if (uuid==spieler1.uuid) {
      s="Spieler 1";
    } // end of if
    if (uuid==spieler2.uuid) {
      s="Spieler 2";
    } // end of if
    if (uuid==boss.uuid) {
      s="Boss";
    } // end of if
    spieler1.logout(s);
    spieler2.logout(s);
    boss.logout(s);
  }
  /**
   * Berechnet den Jump der Spieler
   */
  public void sJump(){
    //prÃ¼ft ob mÃ¶glich
  }
  /**
   * Bewegt die Spieler
   */
  public void sMove() {
    //prÃ¼ft ob mÃ¶glich
    
  }
  /**
   * generelle Update Methode
   * @param c Reduziertes Spielerobjekt
   */
  public void sUpdate(){
    
  }
  
  public void sUpdateHealth() {
      for (int i = 0; i <= spielerListe.length; i++) {
          GameManager local = spielerListe[i];
          if (local.getpSelf().getHealth() < 100) {
              local.getpSelf().setHealth(local.getpSelf().getHealth() + 0.01 * local.getpSelf().getRegSpeed());
          }


      }
  }

}