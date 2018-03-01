package IOServer;
import java.awt.event.KeyEvent;

import Grafik.Game;
import Grafik.MovementType;
import Grafik.RenderManager;
import Grafik.State;

import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;




/**
 *
 * @author Lukas Hofmann, Patrick Waltermann
 *
 */
public class GameManager{

    /**
     *
     * Anmerkung BrutForce soll jetzt benutzt werden (1/20 Sekunde)
     *
     * */
    private int sterbeHilfe=0;
    private int endHilfe =0;
    private boolean endGame, bosswin;
    private UUID id;
    private int hbAnzahl;
    private Attack amAllg;
    private Player pSelf;
    private Player pOther1;
    private Player pOther2;
    private Rectangle [] hbListe = new Rectangle[hbAnzahl];
    private ServerVerwaltung server;
    private Partikel pa;
    /**
     *
     * @param s Server, der angebunden werden soll.
     * Konstruktor: der GameManager bekommt ein Serverobjekt zum Methodenaufrufen
     */
    public GameManager(ServerVerwaltung s){
        this();
        server = s;

    }
    /**
     * Konstruktor ohne Serverobjekt
     */
    public GameManager(){
    	
        pSelf.setAttackMode(1);
        pSelf.setMana(0);
        pSelf.setHealth(0);
        pSelf.setJumpheight(100);
        pSelf.setMovementspeed(100);
        pSelf.setLookingRight(false);
        pSelf.setSprinting(false);
        pSelf.setBoss(false);
        pa.setJumping(false);
        pa = new Partikel(pSelf.getHb().getPos(), pSelf.getHb().getWidth(),pSelf.getGr());


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
            	Grafik.RenderManager.render();
                cUpdateG();
                intersect(hbListe, pSelf.getHb());
                if(pSelf.isHitted()) {cHit(pSelf);}
                if(pOther1.isHitted()) {cHit(pOther1);}
                if(pOther2.isHitted()) {cHit(pOther2);}
                if(pOther1.isJumping()){cJumpOtherG(pOther1);}
                if(pOther2.isJumping()){cJumpOtherG(pOther2);}
                if(pSelf.isDead()) {cSterben(pSelf.getNumberID());}
                if(endGame&&endHilfe==0) {
                    endHilfe++;
                    //TODO Grafik Ende senden
                    RenderManager.changeState(State.HAUPTMENUE);
                }
            }
        }, 0, 100);
        server = null;
    }
    public Player getpSelf() {
        return pSelf;
    }

    public Attack getAmAllg() {
        return amAllg;
    }
    public void setAmAllg(Attack amAllg) {
        this.amAllg = amAllg;
    }
    /**
     * Setzt den Server mit dem der Client kommuniziert
     * @param s der Server mit dem der Client kommuniziert
     */
    public void cSetServer(ServerVerwaltung s){
        server = s;
    }
    /**
     * Diese Methode wird vom InputListener aufgerufen und f�hrt die Methode cMoveSelf() aus.
     * Au�erdem werden die Attribute isLookingRight und isSprinting ver�ndert
     * @param e Die aktivierte Taste
     * w/leertaste muss noch erg�nzend werden
     */
    public void inputKey(KeyEvent e) {
        if(!pSelf.isDead()) {
            switch (e.getKeyChar()) {
                case 'a':
                    pSelf.setLookingRight(false);
                    pSelf.setSprinting(false);
                    cMoveSelf();
                    break;
                case 'd':
                    pSelf.setLookingRight(true);
                    pSelf.setSprinting(false);
                    cMoveSelf();
                    break;
                case ' ':
                    cJumpSelf();
                    break;
                case '1':
                case '2':
                case '3':
                    try {
                        pSelf.setAttackMode(Integer.parseInt(Character.toString(e.getKeyChar())));
                    } catch (Exception ignored) {
                    }
                    break;
            }
        }
    }

    /**
     Diese Methode wird vom InputListener aufgerufen, wenn ein Mausklick registriert wurde.
     Sie l�st die Methode attack() aus, wenn die linke Maustaste gedr�ckt wurde, wertet also den Mausinput aus
     */
    public void inputMouse(MouseEvent m) {
        if(m.getButton()==1&&!pSelf.isDead()){
            switch(pSelf.getAttackMode()){
                case 1:
                    cAttack(pSelf.getAmNormal());
                    break;
                case 2:
                    cAttack(pSelf.getAmSpec1());
                    break;
                case 3:
                    cAttack(pSelf.getAmSpec2());
                    break;
            }
        }
    }
    /**
     * �bertr�gt die eigene Bewegung an den Server
     */
    public void cMoveSelf(){
        if (pSelf.isLookingRight()) {
            pa.addxVel(pSelf.getMovementspeed());
        }else if (!pSelf.isLookingRight()) {
            pa.addxVel(-pSelf.getMovementspeed());
        }
    }
    
    public void cJumpSelf(){
       pa.addyVel(pSelf.getJumpheight());
    }
    
    /**
     * F�hrt die Methode sAttack(ID, attackMode) beim Server aus.
     */
    public void cAttack(Attack am) {
        if(pSelf.getMana()>=am.getCost()&&am.getCooldown()==0) {

            Position clone = new Position(pSelf.getHb().getLeft(), pSelf.getHb().getTop());
            if (pSelf.isLookingRight()) {
                clone.setXPos(pSelf.getHb().getRight());
                clone.setYPos(pSelf.getHb().getTop());
            } else {
                clone.setXPos(pSelf.getHb().getLeft() - am.getDamageBox().getWidth());
                clone.setYPos(pSelf.getHb().getTop());
            }
            am.setPosition(clone);
            amAllg = am;
            pSelf.setAttacking(true);

        }
    }
    /**
     * �bergibt der Grafik, dass Schaden an einen Spieler ausgef�hrt wurde
     */
    public void cHit(Player p) {
        //TODO an grafik senden
    	Game.takeDmg(p.getNumberID());
    }
    /**
     * F�hrt die sLogin(GameManager) beim Server auf, um eine Verbindung aufzubauen.
     */
    public void cLogin(){ //�bergabe der ServerID!!!
        id = UUID.randomUUID();
        server.sLogin(this);
    }
    /**
     * F�hrt die Methode sLogout(GameManager) beim Server aus, um seine Verbindung mit diesem zu trennen.
     */
    public void cLogout(){
        server.sLogout(this);
    }
    /**
     * Signalisiert welche Charackterauswahl dir angezeigt werden soll.
     * @param b Ob Boss oder nicht
     * @param k eigene Nummer beim Server
     */
    public void cSetBoss(boolean b, int k) {
        pSelf.setBoss(b);
        pSelf.setNumberID(k);

    }
    /**
     * Erm�glicht dem Server die UUID des Clients zu bekommen.
     * @return �bergibt die UUID des Clients
     */
    public UUID cGetUUID(){
        return id;

    }
    /**
     * �bergibt die interne ID zur Erkennung um welchen Client es sich handelt.
     * @return eigene ID
     */
    public int cGetNumberID(){
        return pSelf.getNumberID();

    }
    /**
     * Signalisiert wenn ein Client stirbt
     * @param i ID des sterbenden Spielers
     */
    public void cSterben(int i) {
        if(sterbeHilfe==0) {
            sterbeHilfe++;
            if (i == 0) {
                try {
                    pa.wait();
                } catch (InterruptedException e) {
                }
            }
            //TODO an Grafik TOD
            Game.die(i);
        }
    }
    /**
     * �bergibt die IDs der anderen Clients an diesen Client
     * @param id1 ID des ersten anderen Spielers
     * @param id2 ID des zweiten anderen Spielers
     */
    public void cSetNumberIDother(int id1, int id2) {
        pOther1.setNumberID(id1);
        pOther2.setNumberID(id2);
    }
    /**
     * Methode um die Grafik �ber erschaffene Projectiles zu informieren
     */
    public void cSpawnprojectile(){
        //an Grafik
    	Game.spawnProjectile((int) pSelf.getHb().getPos().getXPos(), (int) pSelf.getHb().getPos().getYPos(), pSelf.isLookingRight());
    }
    /**
     * Methode um die Grafik �ber zerst�rte Projectiles zu informieren
     */
    public void cDestroyprojectile(){
        //an Grafik
    	//Game.destroyProjectile(ProjektilPos.x, ProjektilPos.y);
    }
    /**
     * Alle Daten werden an die Grafik �bertragen
     */
    public static void cUpdateG(){
        //alle daten �bergeben
    	/* Game.updatePlayer(1, x, y, mt, at, hp, mp);
    	 Game.updatePlayer(2, x, y, mt, at, hp, mp);
    	 Game.updatePlayer(3, x, y, mt, at, hp, mp);*/
    	 
    }
    /**
     *
     */
    public CUpdate cGetUpdateS(){
        CUpdate r = new CUpdate(id, amAllg,pSelf);
        pSelf.setAttacking(false);
        return r;

    }
    public void cSetUpdateS(SUpdate update){
        //TODO fehlt!!!
    	if(pSelf.getNumberID()==0){
    		// pSelf aus p1 updaten
    		pSelf.setUpdateSSelf(update.getP1());
    		if(pOther1.getNumberID()==1) {
    			//pOther1 aus p2 updaten
    			pOther1.setUpdateSOther(update.getP2());
    			//pOther2 aus p3 updaten
    			pOther2.setUpdateSOther(update.getP3());
    		}
    		else {
    			//pOther1 aus p3updaten
    			pOther1.setUpdateSOther(update.getP3());
    			//pOther2 aus p2 updaten
    			pOther2.setUpdateSOther(update.getP2());
    		}
    	}
    	else if(pSelf.getNumberID()==1){
    		//pSelf aus p2updaten
    		pSelf.setUpdateSSelf(update.getP2());
    		if(pOther1.getNumberID()==0){
    			//pOther1 aus p1 updaten
    			pOther1.setUpdateSOther(update.getP1());
    			//pOther2 aus p3 updaten
    			pOther2.setUpdateSOther(update.getP3());
    		}
    		else{
    			//pOther1 aus p3updaten
    			pOther1.setUpdateSOther(update.getP3());
    			//pOther2 aus p1 updaten
    			pOther2.setUpdateSOther(update.getP1());
    		}
    	}
    	else{
    		//pSelf aus p3updaten
    		pSelf.setUpdateSSelf(update.getP3());
    		if(pOther1.getNumberID()==0) {
    			//pOther1 aus p1
    			pOther1.setUpdateSOther(update.getP1());
    			//pOther2 aus p2
    			pOther2.setUpdateSOther(update.getP2());
    		}
    		else{
    			//pOther1 aus p2
    			pOther1.setUpdateSOther(update.getP2());
    			//pOther2 aus p1
    			pOther2.setUpdateSOther(update.getP1());
    		}
    	}
    }
    //Update Nocheinmal mit anderen Parametern
    /*
    /**
     * �bergibt Ort wo der Spieler erschaffen werden soll.
     * Fehlt Differenzierung zwischen den Spielern
     * @param pos Spawnposition des Spielers
     * @param isLookingRight Die Richtung in die der Spieler schaut
     */
  /*public void cSpawn(Position pos, boolean isLookingRight){
    this.pos = pos;
    this.isLookingRight = isLookingRight;
  }*/
    /**
     * �bergibt den laufenden Countdown an die Grafik
     * @param countdown Countdown bis Spielbeginn
     */
    public void startCountdown(int countdown) {
        //TODO fehlt!!!
        //an grafik und Preparingphase initialisieren
    }
    /**
     * Wird vom Server aufgerufen, signalisiert die Preparingphase
     */
    public void cCharakterauswahlStarten() {

    }
    /**
     * Setzt den/die Character der Spieler
     */
    public void cSetCharakter() {
        //TODO fehlt!!!

    }

    public boolean intersect(Rectangle[] player, Rectangle damage) {
        for (Rectangle r : player) {
            if (!((damage.getRight() <= r.getLeft() || damage.getLeft() >= r.getRight()) && (damage.getBottom() >= r.getTop()))) {
                pa.setJumping(false);
                pa.updateGround(r);
                pSelf.setJumping(true);
                return true;
            }
        }
        pSelf.setJumping(false);
        pa.setJumping(true);
        return false;
    }
  
 /**
 * Einer der Spieler hat sich ausgeloggt oder das Spiel ist zu Ende
 * Der Spieler kehrt zum Hauptbildschirm zurück
 */
    
  public void logout(String s){
	  //zum Hauptbildschirm zurückkehren
	  zeigen(s);
  }
  
  /**
  * @param s String der auf dem Bildschirm angezeigt wird, um dem Spieler zu erklären was passiert ist
  * zum Beispiel welcher Spieler sich ausgeloggt hat oder wer gewonnen hat
  */  
  public void zeigen(String s){
	  //string an Grafik, vermutlich zu aufwendig
  }
  
  /**
  * Spiel wird gestartet; Map, andere Spieler, andere Anzeigen etc. werden angezeigt 
  * und das Spiel beginnt
  */
  public void spielstart(){
	  //Map und andere Spieler zeigen / Spielgrafik starten / 
  }
  
	
  /**
  * Der Spieler ist ein normaler Spieler,
  * es wird die Charakterauswahl für Spielercharaktere geöffnet
  */
  public void auswahlSpieler(){
	  //Spielerauswahl für Spieler oeffnen
  }
	
  /**
  * Der Spieler ist der Boss Player
  * es wird die Charakterauswahl für den Bosscharakter geöffnen
  */
  public void auswahlBoss(){           
	  //Spielerauswahl für Boss oeffnen
  }

    public Partikel getPartikel() {
        return pa;
    }

    public void setPartikel(Partikel pa) {
        this.pa = pa;
    }
}
