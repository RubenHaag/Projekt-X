package grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Hauptklasse f�r das Spiel im Grafikbereich. Beinhaltet die Schnittstellen f�r das Zeichnen der Charaktere
 * @author Fabian Scherer
 */
public class Game{
    private static Player p1;     //Spieler1
    private static Player p2;     //Spieler2
    private static Player b1;     //Spieler3(Boss)
    private BufferedImage map = null;
    private static GamePanel gp;
    private PlayerAnzeige lm;
    private static ArrayList<Projectile> projectiles;

    /**
     * Konstruktor des Game-Objekts
     * Initialisiert alle Attribute, darunter auch die Charaktere mit Standardwerten
     */
    Game(){
        gp = new GamePanel();
        lm = new PlayerAnzeige(50.0, 50.0);
        p1 = new Player("Boy", 400, 200, 100, 200, false);
        p2 = new Player("Girl", 700, 300, 100, 200, false);
        b1 = new Player("Boy", 0, 0, 200, 400, false);
        gp.add(lm.getPanel());
        gp.add(p1);
        gp.add(p2);
        gp.add(b1);
    }

    /**
     * L�sst einen Spieler Schaden nehmen(grafisch)
     * @param player Spieler der Schaden bekommen soll
     */
    public static void takeDmg(int player) {
        switch (player) {
            case 0:
                b1.takeDmg();
                break;
            case 1:
                p1.takeDmg();
                break;
            case 2:
                p2.takeDmg();
                break;
            default:
                break;
        }
    }

    /**
     * L�sst einen Spieler sterben
     * @param player Spieler der sterben soll
     */
    public static void die(int player) {
        switch (player) {
            case 0:
                b1.die();
                break;
            case 1:
                p1.die();
                break;
            case 2:
                p2.die();
                break;
            default:
                break;
        }
    }

    /**
     * L�sst ein Projektil an der �bergebenen Position erscheinen
     * @param x X-Position der oberen linken Ecke des Projektils
     * @param y Y-Position der oberen linken Ecke des Projektils
     * @param right Flugrichtung des Projektils, true = rechts
     */
    public static void spawnProjectile(int x, int y, boolean right) {
        projectiles.add(new Projectile(x, y, "Pfeil"));
    }

    /**
     * L�sst das Projektil an der �bergebenen Position verschwinden
     * @param x X-Position der oberen linken Ecke des Projektils
     * @param y Y-Position der oberen linken Ecke des Projektils
     */
    public static void destroyProjectile(int x, int y) {
        for(Projectile p:projectiles) {
            if(p.getX() == x && p.getY() == y) {
                projectiles.remove(p);
            }
        }
    }

    /**Aktualisiert alle Parameter eines Spielers.
     *
     * @param cn Charaktername des zu aktualisierenden Spielers
     * @param x x-Position des zu aktualisierenden Spielers
     * @param y y-Position des zu aktualisierenden Spielers
     * @param mt Art der Bewegung des zu aktualisierenden Spielers
     * @param at Art der Attacke des zu aktualisierenden Spielers 0 = NON , 1 = Normal, 2 = Special1, 3 = Special2
     * @param right Blickrichtung des Spielers
     * @param hp Lebenspunkte des Spielers als Prozent
     * @param mp Manapunkte des Spielers als Prozent
     */
    public static void updatePlayer(int player, int x, int y, MovementType mt, int at, boolean right, double hp, double mp){
       /* System.out.println(player);
        System.out.println(x + "    " + y);
        System.out.println(mt);
        System.out.println(right);*/
    	switch(player) {
            case 0:
                b1.updatePos(x, y-400);
                b1.updateMovementType(mt, right);
                b1.updateAttackType(at);
                break;
            case 1:
                p1.updatePos(x, y-200);
                p1.updateMovementType(mt, right);
                p1.updateAttackType(at);
                break;
            case 2:
                p2.updatePos(x, y-200);
                p2.updateMovementType(mt, right);
                p2.updateAttackType(at);
                break;
            default:
                System.out.println("Irgendwas ist schiefgelaufen beim Update der Spieler");
                break;
        }
    }

    /**
     * Initialisiert die Charaktere des Spiels
     * NOTE: derzeit nicht im Gebrauch aufgrund fehlender Charakterauswahl
     * @param pn1 Charaktername des ersten Spielers
     * @param pn2 Charaktername des zweiten Spielers
     * @param bn1 Charaktername des Bosses
     * @throws IOException
     */
    public static void initGame(String pn1, String pn2, String bn1) throws IOException{
        if(p1 != null) {
            gp.remove(p1);
        }
        p1 = new Player(pn1, 0, 0, 100, 200, true);
        gp.add(p1);
        if(p2 != null) {
            gp.remove(p2);
        }
        p2 = new Player(pn2, 0, 0, 100, 200, true);
        gp.add(p2);
        if(b1 != null) {
            gp.remove(b1);
        }

        b1 = new Player(bn1, 0, 0, 200, 400, true);
        gp.add(b1);
        gp.validate();
        p1.initPlayerAnimations();
        p2.initPlayerAnimations();
        b1.initPlayerAnimations();
    }

    /**
     * Entfernt den �bergebenen Spieler, der Spieler wird danach nicht mehr gezeichnet
     * @param p Spieler welcher vom Spiel entfernt werden soll
     */
    public static void delPlayer(Player p) {
        gp.remove(p);
    }

    /**
     * Gibt das Panel, auf welchem gezeichnet wird, zur�ck
     * @return Panel, auf welchem gezeichnet wird
     */
    public JPanel getPanel() {
        return gp;
    }

    /**
     * Zeichnet die Charaktere neu
     */
    public void render() {
        //gp.repaint();
        p1.repaint();
        p2.repaint();
        b1.repaint();

    }

    /**
     * TODO
     */
    public static int getPlayerXPos(int player) {
        switch(player) {
            case 0:
                return b1.getXPos();
            case 1:
                return p1.getXPos();
            case 2:
                return p2.getXPos();
            default:
                return -1;
        }

    }

    /**
     * TODO
     */
    public static int getPlayerYPos(int player) {
        switch(player) {
            case 0:
                return b1.getYPos();
            case 1:
                return p1.getYPos();
            case 2:
                return p2.getYPos();
            default:
                return -1;
        }

    }

    /**
     * Hilfsklasse fuer das Game Objekt, auf diesem Panel wird gezeichnet
     * @author Fabian Scherer
     *
     */
    @SuppressWarnings("serial")
    class GamePanel extends JPanel{
        public GamePanel() {
            super();
        }

        @Override
        public void paint(Graphics g) {
            this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
            if(map == null){
                try {
                    map = ImageIO.read(new File("Assets/Map/MapColor.png"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            g.drawImage(map, 0, 0, RenderManager.getFWidth(), RenderManager.getFHeight(), null);
            this.paintComponents(g);

        }
    }
}