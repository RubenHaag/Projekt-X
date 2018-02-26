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
    private int attackMode, numberIdSelf, numberIDother1, numberIDother2,mana, cooldown,jumpheight, movementspeed, playerwidth, playerheight, hbAnzahl;
    private boolean isLookingRight, isSprinting, isBoss, isJumping;
    private Position pos;
    private Partikel pa;
    private Attack amNormal,amSpec1,amSpec2;
    private Rectangle gr;
    private double health;
    public Rectangle getpSelf() {
        return pSelf;
    }

    private Rectangle pSelf;
    private Rectangle pOther1;
    private Rectangle pOther2;
    private Rectangle [] hbListe = new Rectangle[hbAnzahl];
    //private Map map;


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

        this.attackMode = 0;
        this.numberIdSelf = 0;
        this.numberIDother1 = 0;
        this.numberIDother2 = 0;
        this.mana = 0;
        this.health  = 0;
        this.cooldown  = 0;
        this.jumpheight = 100;
        this.movementspeed = 100;
        this.isLookingRight = true;
        this.isSprinting = false;
        this.isBoss = false;
        this.isJumping = false;
        pos = new Position(0, 0);
        pa = new Partikel(pos, playerwidth, gr);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                cUpdateG();
                intersect(hbListe, pSelf);
            }
        }, 0, 100);
        server = null;
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
                isLookingRight = false;
                isSprinting = false;
                cMoveSelf();
                break;
            case 'd':
                isLookingRight = true;
                isSprinting = false;
                cMoveSelf();
                break;
            case ' ':
                cJumpSelf();
                break;
            case '1':
            case '2':
            case '3':
                try {
                    attackMode = Integer.parseInt(Character.toString(e.getKeyChar()));
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
            System.out.println("click");
            switch(attackMode){
                case 1:
                    cAttack(amNormal);
                    break;
                case 2:
                    cAttack(amSpec1);
                    break;
                case 3:
                    cAttack(amSpec2);
                    break;
            }
        }
    }
    /**
     * �bertr�gt die eigene Bewegung an den Server
     */
    public void cMoveSelf(){
        System.out.println("bewegung");
        if (isLookingRight) {
            pa.addxVel(movementspeed);
        }else if (!isLookingRight) {
            pa.addxVel(-movementspeed);
        }
        //System.out.println("move - right?" + isLookingRight+ " - Sprint?" + isSprinting);
    }
    public void cJumpSelf(){
        pa.addyVel(jumpheight);
        isJumping = true;
    }
  /*
  public void cJumpOther(GameManager other1, GameManager other2){
    
  }
  
  public void cMoveOther(GameManager other1, GameManager other2){
    
  }
  */
    /**
     * F�hrt die Methode sAttack(ID, attackMode) beim Server aus.
     */
    public void cAttack(Attack am) {
        Position clone = new Position(pos.getXPos(), pos.getYPos());
       if(isLookingRight){
           clone.setXPos(pSelf.getRight());
           clone.setYPos(pSelf.getTop());
       }else{
           clone.setXPos(pSelf.getLeft()-am.getDamageBox().getWidth());
           clone.setYPos(pSelf.getTop());
       }
        am.setPosition(clone);
        server.sAttack(id, am);
        //TODO an grafik spieler attakiert senden


    }
    /**
     * �bergibt der Grafik, dass Schaden an einen Spieler ausgef�hrt wurde
     */
    public void cHit(int damage) {
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
        isBoss = b;
        numberIdSelf = k;

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
        return numberIdSelf;

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
        numberIDother1 = id1;
        numberIDother2 = id2;
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
     * Methode die im Sekundentakt vom Server an den Client �bertragen wird
     * @param mana Mana f�r die Spezialf�higkeit
     * @param health Lebenpunkte des Spielers
     * @param cooldown Cooldown seiner Spezialf�higkeit
     */
    public void cUpdateS(int mana, int health, int cooldown){
        this.mana = mana;
        this.health = health;
        this.cooldown = cooldown;
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

    public void cSetHealth( double h){
        this.health = h;
    }
    public double cGetHealth(){
        return health;

    }
    public boolean intersect(Rectangle[] player, Rectangle damage) {
        for (Rectangle r : player) {
            if (!((damage.getRight() <= r.getLeft() || damage.getLeft() >= r.getRight()) && (damage.getBottom() >= r.getTop()))) {
                pa.setGround(true);
                pa.updateGround(r);
                return true;
            }
        }
        pa.setGround(false);
        return false;
    }



}
