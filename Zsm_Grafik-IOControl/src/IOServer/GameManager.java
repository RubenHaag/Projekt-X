package IOServer;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

import IOServer.mapbuilder.MapBuilder;
import grafik.Game;
import grafik.MovementType;
import grafik.RenderManager;
import grafik.State;

/**
 * @author Lukas Hofmann, Patrick Waltermann, Max Schulte
 */
public class GameManager {
    /**
     * Anmerkung BrutForce soll jetzt benutzt werden (1/20 Sekunde)
     */
    private int sterbeHilfe = 0;
    private int endHilfe = 0;
    private boolean endGame, bosswin;
    private UUID id;
    private Player pSelf = new Player();
    private Player pOther1 = new Player();
    private Player pOther2 = new Player();
    private Attack amAllg = pSelf.getAmNormal();
    private List<Rectangle> hbListe;
    private ServerVerwaltung server;
    private Partikel pa = new Partikel(pSelf.getHb().getPos(), pSelf.getHb().getWidth(), pSelf.getGr());
    private cLoginUpdate CLU0=new cLoginUpdate();								//CLU0;CLU1;CLU2 : fuer Werteuebergabe von Server zu CLient
    private cLoginUpdate CLU1=new cLoginUpdate();
    private cLoginUpdate CLU2=new cLoginUpdate();
    private cLoginUpdate ownCLU=new cLoginUpdate(0,null);
    private int charakter;

    /**
     * @param s Server, der angebunden werden soll.
     *          Konstruktor: der GameManager bekommt ein Serverobjekt zum Methodenaufrufen
     */
    public GameManager(ServerVerwaltung s) {
        this();
        server = s;
    }

    /**
     * Konstruktor ohne Serverobjekt
     */
    public GameManager() {
//        hbListe = new ArrayList<>();
        try {
            hbListe = MapBuilder.BildZuRechteck("src/ioserver/mapbuilder/MapComputer.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter der den eigenen Spieler zurueck gibt
     *
     * @return Playerobjekt des Clients
     */
    public Player getpSelf() {
        return pSelf;
    }

    /**
     * Getter der die zuletzt ausgefuehrte Attacke zuruek gibt
     *
     * @return Attackobjekt
     */
    public Attack getAmAllg() {
        return amAllg;
    }

    /**
     * Setter der die Attacke die zuletzt ausgefuehrt wird setzt
     *
     * @param amAllg zuletzt ausgefuehrtes Attackobjekt
     */
    public void setAmAllg(Attack amAllg) {
        this.amAllg = amAllg;
    }

    /**
     * Setzt den Server mit dem der Client kommuniziert
     * @param s der Server mit dem der Client kommuniziert
     */
    public void cSetServer(ServerVerwaltung s) {
        server = s;
    }

    /**
     * Diese Methode wird vom InputListener aufgerufen und fuehrt die Methode cMoveSelf() aus.
     * Ausserdem werden die Attribute isLookingRight und isSprinting veraendert
     * Sie dient zur Verarbeitung der auf der Tastatur gedruekten Tasten
     * Ausserdem gibt es eine If-Abfrage die eine weitere Eingabe nach dem Tod des Spielers verhindert
     * @param e Die aktivierte Taste
     */
    public void inputKey(KeyEvent e) {
        if (!pSelf.isDead()) { //If-Abfrage: verhindert weitere Eingabe nach dem Tod des Spielers
            switch (e.getKeyChar()) {
                case 'a': //Bewegung nach Links bei dem Tastendruck von 'A'
                    pSelf.setLookingRight(false);
                    pSelf.setSprinting(false);
                    cMoveSelf();
                    break;
                case 'd': //Bewegung nach Rechts bei dem Tastendruck von 'D'
                    pSelf.setLookingRight(true);
                    pSelf.setSprinting(false);
                    cMoveSelf();
                    break;
                case ' ': //Sprung bei dem Tastendruck von ''bzw der Leertaste
                    cJumpSelf();
                    break;
                case '1': //Auswahl der Attackmodi
                case '2':
                case '3':
                    try {
                        pSelf.setAttackMode(Integer.parseInt(Character.toString(e.getKeyChar())));
                    } catch (Exception ignored) {}
                    break;
            }
        }
    }

    /**
     * Diese Methode wird vom InputListener aufgerufen, wenn ein Mausklick registriert wurde.
     * Sie loest die Methode attack() aus, wenn die linke Maustaste gedrueckt wurde, wertet also den Mausinput aus
     * Ausserdem gibt es eine If-Abfrage die eine weitere Eingabe nach dem Tod des Spielers verhindert
     */
    public void inputMouse(MouseEvent m) {
        if (m.getButton() == 1 && !pSelf.isDead()) { //If-Abfrage: verhindert weitere Eingabe nach dem Tod des Spielers
            switch (pSelf.getAttackMode()) { //Attacke wird in Abhaengigkeit zum eingegebenen Attackmodus ausgefuehrt
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
     * Umsetzung der bei InputKey() eingegebenen eigenen Bewegungen in X-Richtung
     * Hierfuer wird das Partikel bewegt
     */
    private void cMoveSelf() {
        if (pSelf.isLookingRight()) {
            pa.addxVel(pSelf.getMovementspeed());
        } else {
            pa.addxVel(-pSelf.getMovementspeed());
        }
    }

    /**
     * Umsetzung der bei InputKey() eingegebenen eigenen Sprungbewegung
     * Hierfuer wird das Partikel bewegt bzw. mit einer Y-Velocity ausgestattet
     */
    private void cJumpSelf() {
        pa.addyVel(pSelf.getJumpheight());
    }

    /**
     * TODO
     * @param p Playerobjekt
     */
    private void cJumpOtherG(Player p) {
        //TODO sage grafik das p springt
    }

    /**
     * Methode, die durch InputMouse ausgeloest wird und die Position des Spielers klont. Abhängig von der Richtung in die der Spieler schaut wird die Position des Klons gesetzt.
     * Der Klon uebergibt seine Koordinate an die Attacke bzw die Hitbox der Attacke.
     * Zu erwaehnen ist, dass dies nur passiert, wenn die Mana ausreichend vorhanden ist und der Cooldown der Attacke abgelaufen ist.
     * Abschliessend wird das abgeaenderte Attackobjekt im Attackobjekt amAllg abgespeichert.
     * Danach wird der Boolean isAttacking auf true gesetzt, sodass bei der naechsten Abfrage des Servers das Attackobjekt amAllg übertragen wird und isAttacking im Server auf true gesetzt.
     * @param am Attackobjekt das ausgewaehlt ist
     */
    private void cAttack(Attack am) {
        if (pSelf.getMana() >= am.getCost() && am.getCooldown() == 0) {

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
            //TODO an grafik spieler attakiert senden

        }
    }

    /**
     * Uebergibt der grafik, dass Schaden an einen Spieler ausgef�hrt wurde
     */
    private void cHit(Player p) {
        //TODO an grafik senden
    }

    /**
     * Fuehrt die sLogin(GameManager) beim Server auf, um eine Verbindung aufzubauen.

     */
    /*
    public void cLogin() { //�bergabe der ServerID!!!
        id = UUID.randomUUID();
        server.sLogin(this);
    }
    */
    /**
     * F�hrt die Methode sLogout(GameManager) beim Server aus, um seine Verbindung mit diesem zu trennen.
     */
    public void cLogout() {
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
     * Ermoeglicht die Auslese der UUID.
     * @return Gibt die UUid des Clients zurueck
     */
    public UUID cGetUUID() {
        return id;

    }

    /**
     * Uebergibt die interne ID des eigenen Players zur Erkennung um welchen Spieler es sich handelt.
     * @return Id des eigenen Spielers
     */
    public int cGetNumberID() {
        return pSelf.getNumberID();

    }

    /**
     * Signalisiert wenn ein Client stirbt
     * @param i ID des sterbenden Spielers
     */
    private void cSterben(int i) {
        if (sterbeHilfe == 0) {
            sterbeHilfe++;
            if (i == 0) {
                try {
                    pa.wait();
                } catch (InterruptedException e) {
                }
            }
            //TODO an grafik TOD
        }
    }

    /**
     * Uebergibt die IDs der anderen Clients an diesen Client
     * @param id1 ID des ersten anderen Spielers
     * @param id2 ID des zweiten anderen Spielers
     */
    public void cSetNumberIDother(int id1, int id2) {
        pOther1.setNumberID(id1);
        pOther2.setNumberID(id2);
    }

    /**
     * Methode um die grafik ueber erschaffene Projectiles zu informieren
     */
    public void cSpawnprojectile() {
        //an grafik
    }

    /**
     * Methode um die grafik �ber zerst�rte Projectiles zu informieren
     */
    public void cDestroyprojectile() {
        //an grafik
    }

    private MovementType bestimmenMovementType(Player p) {
        if(p.isJumping()) {
            return MovementType.JUMPING;
        }
        if(Game.getPlayerXPos(p.getNumberID()) ==p.getHb().getPos().getXPos()&& Game.getPlayerYPos(p.getNumberID()) ==p.getHb().getPos().getYPos()) {
            return MovementType.IDLE;
        }
        else {
            return MovementType.MOVE;
        }
    }

    private int bestimmenAttackType(Player p) {
        if(!p.isAttacking()) {
            return 0;
        }

        else if(amAllg==p.getAmNormal()) {
            return 1;
        }

        else if(amAllg==p.getAmSpec1()) {
            return 2;
        }

        else {
            return 3;
        }
    }

    /**
     * Alle Daten werden an die grafik �bertragen
     */
    private void cUpdateG() {
        //alle daten Uebergeben

       //System.out.println(pSelf.getHb().getPos().getXPos());
//        Game.updatePlayer(pSelf.getNumberID(), pSelf.getHb().getPos().getXPos(), 5, bestimmenMovementType(pSelf), bestimmenAttackType(pSelf), pSelf.isLookingRight(), pSelf.getHealth(), pSelf.getMana());
        Game.updatePlayer(pSelf.getNumberID(), pSelf.getHb().getPos().getXPos(), pSelf.getHb().getPos().getYPos(), bestimmenMovementType(pSelf), bestimmenAttackType(pSelf), pSelf.isLookingRight(), pSelf.getHealth(), pSelf.getMana());
    }

    /**
     *
     */
    public CUpdate cGetUpdateS() {
        CUpdate r = new CUpdate(id, amAllg, pSelf);
        pSelf.setAttacking(false);
        return r;

    }

    public void cSetUpdateS(SUpdate update) {
        endGame = update.isEndGame();
        bosswin = update.isBosswin();
        if (pSelf.getNumberID() == 0) {
            // pSelf aus p1 updaten
            pSelf.setUpdateSSelf(update.getP1());
            if (pOther1.getNumberID() == 1) {
                //pOther1 aus p2 updaten
                pOther1.setUpdateSOther(update.getP2());
                //pOther2 aus p3 updaten
                pOther2.setUpdateSOther(update.getP3());
            } else {
                //pOther1 aus p3updaten
                pOther1.setUpdateSOther(update.getP3());
                //pOther2 aus p2 updaten
                pOther2.setUpdateSOther(update.getP2());
            }
        } else if (pSelf.getNumberID() == 1) {
            //pSelf aus p2updaten
            pSelf.setUpdateSSelf(update.getP2());
            if (pOther1.getNumberID() == 0) {
                //pOther1 aus p1 updaten
                pOther1.setUpdateSOther(update.getP1());
                //pOther2 aus p3 updaten
                pOther2.setUpdateSOther(update.getP3());
            } else {
                //pOther1 aus p3updaten
                pOther1.setUpdateSOther(update.getP3());
                //pOther2 aus p1 updaten
                pOther2.setUpdateSOther(update.getP1());
            }
        } else {
            //pSelf aus p3updaten
            pSelf.setUpdateSSelf(update.getP3());
            if (pOther1.getNumberID() == 0) {
                //pOther1 aus p1
                pOther1.setUpdateSOther(update.getP1());
                //pOther2 aus p2
                pOther2.setUpdateSOther(update.getP2());
            } else {
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
     * Uebergibt den laufenden Countdown an die grafik
     *
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
    public void cSetCharakter(int charakter) {
        this.charakter = charakter;
        //TODO fehlt was?
    }

    private void intersect(List<Rectangle> hbListe, Rectangle player) {
        for (Rectangle r : hbListe) {
            if (!((player.getRight() <= r.getLeft() || player.getLeft() >= r.getRight()) && (player.getBottom() <= r.getTop()))) {
                pa.setJumping(false);
                pa.updateGround(r);
                pSelf.setJumping(false);
                System.out.println("intersect");
                return;
            }
        }
        pSelf.setJumping(true);
        pa.setJumping(true);
    }


    /**
     * Fuehrt die sLogin(GameManager) beim Server auf, um eine Verbindung aufzubauen.
     */
    
    /*public void cLogin() { //�bergabe der ServerID!!!
        id = UUID.randomUUID();
        server.sLogin(this);
    }*/

    /**
     * Einer der Spieler hat sich ausgeloggt oder das Spiel ist zu Ende
     * Der Spieler kehrt zum Hauptbildschirm zurück
     */

    public void logout(String s) {
        RenderManager.changeState(State.HAUPTMENUE);
        zeigen(s);
    }

    /**
     * @param s String der auf dem Bildschirm angezeigt wird, um dem Spieler zu erklären was passiert ist
     *          zum Beispiel welcher Spieler sich ausgeloggt hat oder wer gewonnen hat
     */
    public void zeigen(String s) {
        //string an grafik, vermutlich zu aufwendig
    }

    /**
     * Spiel wird gestartet; Map, andere Spieler, andere Anzeigen etc. werden angezeigt
     * und das Spiel beginnt
     */
    public void spielstart() {
        //Map und andere Spieler zeigen / Spielgrafik starten /

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                cUpdateG();
                intersect(hbListe, pSelf.getHb());
                if (pSelf.isHitted()) {
                    cHit(pSelf);
                }
                if (pOther1.isHitted()) {
                    cHit(pOther1);
                }
                if (pOther2.isHitted()) {
                    cHit(pOther2);
                }
                if (pOther1.isJumping()) {
                    cJumpOtherG(pOther1);
                }
                if (pOther2.isJumping()) {
                    cJumpOtherG(pOther2);
                }
                if (pSelf.isDead()) {
                    cSterben(pSelf.getNumberID());
                }
                if (endGame && endHilfe == 0) {
                    endHilfe++;
                    //TODO grafik Ende senden
                }
            }
        }, 0, 100);
        server = null;
    }

    /**
     * Fuehrt die sLogin(GameManager) beim Server auf, um eine Verbindung aufzubauen.
     */
    public void cLogin() { //�bergabe der ServerID!!!
        if (id==null){
            this.id = UUID.randomUUID();
            ownCLU.setUUID(id);
        }
        else if(id==CLU0.getUUID()){
            ownCLU=CLU0;
        }
        else if(id==CLU1.getUUID()){
            ownCLU=CLU1;
        }
        else if(id==CLU2.getUUID()){
            ownCLU=CLU2;
        }
        if (ownCLU.getMode()==1){
            if (ownCLU.getSpielStart()){
                this.spielstart();
            }
            else{

                if(ownCLU.getIstBoss()){
                    this.auswahlBoss(ownCLU);
                }
                else{
                    this.auswahlSpieler(ownCLU);
                }
            }
        }
        else{
        }
    }



    /**
     * Der Spieler ist ein normaler Spieler,
     * es wird die Charakterauswahl für Spielercharaktere geöffnet
     */
    public void auswahlSpieler(cLoginUpdate ownCLU){
        //ownCLU.setCharakter=(Spielerauswahl fÃ¼r Spieler oeffnen)/rückgabewert von grafik, sonst standardauswahl;
        ownCLU.setMode(2);
    }

    /**
     * Der Spieler ist der Boss Player
     * es wird die Charakterauswahl für den Bosscharakter geöffnen
     */
    public void auswahlBoss(cLoginUpdate ownCLU){
        //ownCLU.setCharakter=(Spielerauswahl fÃ¼r Boss oeffnen)/rückgabewert von grafik sonst standartauswahl
        ownCLU.setMode(2);
    }

    public Partikel getPartikel() {
        return pa;
    }

    public void setPartikel(Partikel pa) {
        this.pa = pa;
    }
}
