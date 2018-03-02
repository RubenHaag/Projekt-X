import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.lang.Thread;

/**
 * @author Lukas Hofmann, Patrick Waltermann , Max Schulte
 */
public class ServerVerwaltung {
    private GameManager[] spielerListe = new GameManager[3];
    private int deathcounter = 0;
    private boolean bosswin, endGame;
    private boolean s1 = false;          //s1,s2,b : welche rolle ist schon vergeben, wird beim login benÃ¶tigt (s:Spieler b:Boss)
    private boolean s2 = false;
    private boolean b = false;
    private int n = 0;                 // n: Anzahl der eingeloggten Spieler
    private GameManager spieler1 = new GameManager();
    private GameManager spieler2 = new GameManager();
    private GameManager boss = new GameManager();
    private long last_time = System.nanoTime();
    /**
     * dt ist die Deltatime
     */
    private double dt;

    public ServerVerwaltung() {


    }
    /**
     * Methode, die vom Boolean isAttacking in der run()-Methode ausgelöst wird.
     * Hier werden bei einer Attacke erst die Mana-Werte des Spielers gesenkt und der Cooldown
     * zurückgesetzt. Anschließend werden die getroffenen Spieler ermittelt, ihnen wird Leben
     * abgezogen und falls einer der Spieler tot ist, wird auch dies in den Boolean isDead gesichert
     * und gegebenenfalls wird das Spiel beendet indem der boolean endGame auf true gesetzt wird.
     * Außerdem wird bei einem Tod ein Todeszähler (deathcounter) hochgesetzt und wenn deathcounter > 1 oder ein
     * Boss ist, entschieden, wer das Spiel gewonnen hat. Hierzu wird bosswin entweder auf true oder false gesetzt.
     *
     * @param id UUID zur eindeutigen Identifikation des schlagenden Spielers
     * @param am Attack-Objekt um zwischen verschiedenen Attacken zu unterscheiden
     *
     *
     *
     * */
    private void sAttack(UUID id, Attack am) {
        int y = sGetNumberID(id);
        spielerListe[y].getpSelf().setMana(spielerListe[y].getpSelf().getMana() - am.getCost());
        if (am == spielerListe[y].getpSelf().getAmSpec1()) {
            spielerListe[y].getpSelf().getAmSpec1().resetCooldown();
        }
        if (am == spielerListe[y].getpSelf().getAmSpec2()) {
            spielerListe[y].getpSelf().getAmSpec2().resetCooldown();
        }
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (am.getDamageBox().intersect(local.getpSelf().getHb())) {
                local.getpSelf().setHitted(true);
                local.getpSelf().setHealth(local.getpSelf().getHealth() - spielerListe[y].getAmAllg().getDamage());
                if (local.getpSelf().getHealth() <= 0) {
                    local.getpSelf().setDead(true);
                    if (local.getpSelf().isBoss()) {
                        endGame = true;
                        bosswin = false;
                    } else if (deathcounter == 1) {
                        endGame = true;
                        bosswin = true;
                    }
                    deathcounter++;
                }
                //TODO noch nie getetstet
            }
        }
    }

    /**
     * Wandelt die UUID eines Spielers in eine interne ID um und liefert diese zurueck.
     * Wenn kein zur UUID passender Spieler gefunden wird, liefert die Methode einen zu hohen
     * Wert zurück, damit dieser als Fehler erkannt wird
     *
     * @param id UUID zur eindeutigen Identifikation eines Spielers
     * @return NumberID int um Spieler leichter zu identifizieren
     */
    private int sGetNumberID(UUID id) {
        int x = 5;//absichtlich falscher Int
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (local.cGetUUID() == id) {
                return local.cGetNumberID();
            }
        }
        return x;
    }

    /**
     * Initiert das Spiel. Beinhaltet einen Timer, d.h. den Thread der Serververwaltung.
     * hier wird getestet, ob eine Attacke vorliegt und wenn dies der Fall ist, wird sAttack() ausgeführt.
     */
    public void sStartGame() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                long time = System.nanoTime();
                dt = (double) ((time - last_time));
                last_time = time;
                sUpdateHealth();
                sUpdateCooldown();
                sUpdateMana();
                for (int i = 0; i <= spielerListe.length; i++) {
                    GameManager local = spielerListe[i];
                    if (local.getpSelf().isAttacking()) {
                        sAttack(local.cGetUUID(), local.getAmAllg());
                    }
                }
            }
        }, 0, 100);

    }

    /**
     * @param g Der Client der die Methode aufruft
     * @return
     */
    public boolean sLogin(GameManager g) {
        double z = Math.random();

        if (n == 0 && z <= 0.333333) {
            spielerIstBoss(g);
        } // end of if
        else if (n == 0 && z > 0.333333 && z <= 0.666666) {
            spielerIst1(g);
        } // end of if
        else if (n == 0 && z > 0.666666) {
            spielerIst2(g);
        } // end of if

        else if (n == 1 && b && z <= 0.5) {
            spielerIst1(g);
        } // end of if
        else if (n == 1 && s1 && z <= 0.5) {
            spielerIstBoss(g);
        } // end of if
        else if (n == 1 && s2 && z <= 0.5) {
            spielerIstBoss(g);
        } // end of if

        else if (n == 1 && b && z > 0.5) {
            spielerIst2(g);
        } // end of if
        else if (n == 1 && s1 && z > 0.5) {
            spielerIst2(g);
        } // end of if
        else if (n == 1 && s2 && z > 0.5) {
            spielerIst1(g);
        } // end of if

        else if (n == 2 && s1 && s2) {
            spielerIstBoss(g);
        } // end of if
        else if (n == 2 && b && s1) {
            spielerIst2(g);
        } // end of if
        else if (n == 2 && b && s2) {
            spielerIst1(g);
        } // end of if

        if (n == 3) {
            //double aktZeit=System.currentTimeMillis();
            String s = "Alle Spieler eingeloggt! Start in 15s!";
            for (int l = 0; l < spielerListe.length; l++) {
                spielerListe[l].zeigen(s);
                spielerListe[l].zeigen(s);
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
            }
            //Spielstart

            for (int j = 0; j < spielerListe.length; j++) {
                spielerListe[j].spielstart();
            }
        } // end of if

        return (true);
    }

    /**
     * spielerIstBoss,spielerIst1,spielerIst2 werden beim Login aufgerufen,
     * getrennt für bessere Übersichtlichkeit
     *
     * @param g Gamemanager, der die Login funktion aufgerufen hat und sich gerade einloggt
     */
    private void spielerIstBoss(GameManager g) {// Das ist ein Konstruktor.. soll das ne Methode sein?
        spielerListe[0] = g;
        g.cSetBoss(true, 0);
        n++;
        b = true;
        g.auswahlBoss();                //Boss auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
        //boss.loginErfolgreich(true);
    }

    private void spielerIst1(GameManager g) {
        spielerListe[1] = g;
        g.cSetBoss(false, 1);
        n++;
        s1 = true;
        g.auswahlSpieler();             //Spieler auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
        //spieler1.loginErfolgreich(true);
    }

    private void spielerIst2(GameManager g) {
        spielerListe[2] = g;
        g.cSetBoss(true, 2);
        n++;
        s2 = true;
        g.auswahlSpieler();             //Spieler auswahl Screen Ã¶ffnen, direkt an grafik weiterleiten
        //spieler2.loginErfolgreich(true);
    }


    //private void setSkin (int Skin, Gamemanager g){   //skin entweder int oder enum
    //  g.setSkin(skin);
    //}


    /**
     * Methode die vom Client aufgerufen wird  um den austritt aus der Session zu signalisieren
     *
     * @param gameManager Client, der sich ausgeloggt hat
     *                    allen spielern wird gesagt, wer sich ausgeloggt hat, danach kehren alle Spieler zum HHauptbildschirm zurück
     */
    public void sLogout(GameManager gameManager) {
        String s = "";
        if (gameManager.cGetUUID() == spielerListe[1].cGetUUID()) {
            s = "Spieler 1";
        } // end of if
        if (gameManager.cGetUUID() == spielerListe[2].cGetUUID()) {
            s = "Spieler 2";
        } // end of if
        if (gameManager.cGetUUID() == spielerListe[0].cGetUUID()) {
            s = "Boss";
        } // end of if
        spielerListe[0].logout(s);
        spielerListe[1].logout(s);
        spielerListe[2].logout(s);
    }

    /**
     * @return Update-Objekt, mit dem die GameManager der Clients aktualisiert werden
     */
    public SUpdate sGetUpdateC() {
        return new SUpdate(spielerListe[0].getpSelf(), spielerListe[1].getpSelf(), spielerListe[2].getpSelf(), bosswin, endGame);
    }
    /**
     * In dieser Methode werden alle relevanten Informationen aus den GameManagern der Clients mit den GameManagern der
     * Serververwaltung synchronisiert.
     *
     * @param update CUpdate-Objekt das vom GameManager in der cUpdateS() Methode erzeugt und
     *              von der Serveruebertragung an die ServerVerwaltung weitergeleitet wird.
     **/
    public void sSetUpdateC(CUpdate update) {
        int y = sGetNumberID(update.getId());
        spielerListe[y].getpSelf().getHb().setPos(update.getPlayer().getHb().getPos());
        spielerListe[y].setAmAllg(update.getAmAllg());
        spielerListe[y].getpSelf().setJumping(update.getPlayer().isJumping());
        spielerListe[y].getpSelf().setLookingRight(update.getPlayer().isLookingRight());
        spielerListe[y].getpSelf().setAttacking(update.getPlayer().isAttacking());
        spielerListe[y].getpSelf().setSprinting(update.getPlayer().isSprinting());
    }
    /**
     * In dieser Methode wird die Regeneration der Leben der Spieler umgesetzt.
     * Sie wird in der run() - Methode der Serververwaltung ausgeführt.
     * */
    private void sUpdateHealth() {
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (local.getpSelf().getHealth() < 100) {
                local.getpSelf().setHealth(local.getpSelf().getHealth() + dt / 1000000000 * 0.01 * local.getpSelf().getRegSpeed());
            }
        }
    }
    /**
     * In dieser Methode wird der Cooldown der Spieler herabgesetzt.
     * Sie wird in der run() - Methode der Serververwaltung ausgeführt.
     * */
    private void sUpdateCooldown() {
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (local.getpSelf().getAmSpec1().getCooldown() > 0) {
                local.getpSelf().getAmSpec1().setCooldown(local.getpSelf().getAmSpec1().getCooldown() - dt / 1000000000);
            } else {
                local.getpSelf().getAmSpec1().setCooldown(0);
            }
            if (local.getpSelf().getAmSpec2().getCooldown() > 0) {
                local.getpSelf().getAmSpec2().setCooldown(local.getpSelf().getAmSpec2().getCooldown() - dt / 1000000000);
            } else {
                local.getpSelf().getAmSpec2().setCooldown(0);
            }
        }
    }
    /**
     * In dieser Methode wird die Regeneration des Manas der Spieler umgesetzt.
     * Sie wird in der run() - Methode der Serververwaltung ausgeführt.
     * */
    private void sUpdateMana() {
        for (int i = 0; i <= spielerListe.length; i++) {
            GameManager local = spielerListe[i];
            if (local.getpSelf().getMana() < 100) {
                local.getpSelf().setMana(local.getpSelf().getMana() + dt / 1000000000 * 0.01 * local.getpSelf().getRegSpeed());
            }
        }
    }

}
