import java.awt.event.KeyEvent;
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
    private UUID id;
    private int hbAnzahl;
    private Attack amall;
    private Player pSelf;
    private Player pOther1;
    private Player pOther2;
    private Rectangle [] hbListe = new Rectangle[hbAnzahl];
    private ServerVerwaltung server;
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
        pSelf.getPartikel().setJumping(false);
        pSelf.setPartikel(new Partikel(pSelf.getHb().getPos(), pSelf.getHb().getWidth(),pSelf.getGr()));


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                cUpdateG();
                intersect(hbListe, pSelf.getHb());
                if(pSelf.isHitted()) { cHit(pSelf.getDamage()); }
                if(pOther1.getPartikel().isJumping()){cJumpOtherG(pOther1);}
                if(pOther2.getPartikel().isJumping()){cJumpOtherG(pOther2);}
            }
        }, 0, 100);
        server = null;
    }
    public Player getpSelf() {
        return pSelf;
    }

    public Attack getAmall() {
        return amall;
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
                } catch (Exception ignored) {}
                break;
        }
    }

    /**
     Diese Methode wird vom InputListener aufgerufen, wenn ein Mausklick registriert wurde.
     Sie l�st die Methode attack() aus, wenn die linke Maustaste gedr�ckt wurde, wertet also den Mausinput aus
     */
    public void inputMouse(MouseEvent m) {
        if(m.getButton()==1){
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
            pSelf.getPartikel().addxVel(pSelf.getMovementspeed());
        }else if (!pSelf.isLookingRight()) {
            pSelf.getPartikel().addxVel(-pSelf.getMovementspeed());
        }
        //System.out.println("move - right?" + isLookingRight+ " - Sprint?" + isSprinting);
    }
    public void cJumpSelf(){
        pSelf.getPartikel().addyVel(pSelf.getJumpheight());
    }
    public void cJumpOtherG(Player p) {
        //TODO sage grafik das p springt
    }
    /*
  public void cMoveOther(GameManager other1, GameManager other2){
    
  }
  */
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
            amall = am;
            pSelf.setAttacking(true);
            pSelf.setMana(pSelf.getMana()-am.getCost());
            //TODO an grafik spieler attakiert senden

        }
    }
    /**
     * �bergibt der Grafik, dass Schaden an einen Spieler ausgef�hrt wurde
     */
    public void cHit(double damage) {
        //todo
        //damage methode an client
        //leben umsetzen etc.
    }
    /**
     * �bergibt der Grafik das ein Spieler attackiert
     * @param y welcher Spieler diese Methode ausf�hrt
     */
    public void cAttackg(int y) {
        //TODO an grafik weiterleiten
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
    }
    /**
     * Methode um die Grafik �ber zerst�rte Projectiles zu informieren
     */
    public void cDestroyprojectile(){
        //an Grafik
    }
    /**
     * Alle Daten werden an die Grafik �bertragen
     */
    public static void cUpdateG(){
        //alle daten �bergeben
    }
    /**
     *
     */
    public CUpdate cGetUpdateS(){
        CUpdate r = new CUpdate(id,amall,pSelf);
        pSelf.setAttacking(false);
        return r;

    }
    //Update Nocheinmal mit anderen Parametern
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

    }

    public boolean intersect(Rectangle[] player, Rectangle damage) {
        for (Rectangle r : player) {
            if (!((damage.getRight() <= r.getLeft() || damage.getLeft() >= r.getRight()) && (damage.getBottom() >= r.getTop()))) {
                pSelf.getPartikel().setJumping(false);
                pSelf.getPartikel().updateGround(r);
                return true;
            }
        }
        pSelf.getPartikel().setJumping(true);
        return false;
    }
    
    
  public void logout(String s){
	  //zum Hauptbildschirm zurückkehren
	  zeigen(s);
  }
  
  public void zeigen(String s){
	  //string an Grafik, vermutlich zu aufwendig
  }
  
  public void spielstart(){
	  //Map und andere Spieler zeigen / Spielgrafik starten / 
  }
  
  public void auswahlSpieler(){
	  //Spielerauswahl für Spieler oeffnen
  }
  public void auswahlBoss(){           
	  //Spielerauswahl für Boss oeffnen
  }



}
