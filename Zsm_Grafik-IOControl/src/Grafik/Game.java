package grafik;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    private static GameWindow window;
    private static GamePanel gamePanel;
    private static Player[] players;
    private static ArrayList<Projectile> projectiles;

    Game(String pn1, String pn2, String bn1) throws IOException {
        players = new Player[3];
        //Initialisieren Player:
        players[0] = new Player(new Position(0,0), 100, 200, true, pn1, "Assets/");
        players[1] = new Player(new Position(0,0), 100, 200, true, pn2, "Assets/");
        players[2] = new Player(new Position(0,0), 100, 200, true, bn1, "Assets/");

        gamePanel = new GamePanel(players, projectiles);
    }
    /**
     * Lüsst einen Spieler Schaden nehmen(grafisch)
     * @param player Spieler der Schaden bekommen soll
     */
    public static void takeDmg(int player) {
        players[player].takeDamage();
    }

    /**
     * Lüsst einen Spieler sterben
     * @param player Spieler der sterben soll
     */
    public static void die(int player) {
        players[player].takeDamage();
    }

    /**
     * Lüsst ein Projektil an der übergebenen PositionIO erscheinen
     * @param x X-PositionIO der oberen linken Ecke des Projektils
     * @param y Y-PositionIO der oberen linken Ecke des Projektils
     * @param right Flugrichtung des Projektils, true = rechts
     */
    public static void spawnProjectile(int x, int y, boolean right) throws IOException {

        projectiles.add(new Projectile(new Position(x, y), 200, 200, right,"/Assets/"));
    }

    /**
     * Lüsst das Projektil an der übergebenen PositionIO verschwinden
     * @param x X-PositionIO der oberen linken Ecke des Projektils
     * @param y Y-PositionIO der oberen linken Ecke des Projektils
     */
    public static void destroyProjectile(int x, int y) {
        for(Projectile p:projectiles) {
            if(p.pos.getXPos() == x && p.pos.getYPos() == y) {
                projectiles.remove(p);
            }
        }
    }

    /**Aktualisiert alle Parameter eines Spielers.
     * @param x x-PositionIO des zu aktualisierenden Spielers
     * @param y y-PositionIO des zu aktualisierenden Spielers
     * @param mt Art der Bewegung des zu aktualisierenden Spielers
     * @param at Art der Attacke des zu aktualisierenden Spielers 0 = NON , 1 = Normal, 2 = Special1, 3 = Special2
     * @param right Blickrichtung des Spielers
     * @param hp Lebenspunkte des Spielers als Prozent
     * @param mp Manapunkte des Spielers als Prozent
     */
    public static void updatePlayer(int player, int x, int y, MovementType mt, int at, boolean right, double hp, double mp){
        players[player].setPos(new Position(x, y));
        players[player].updateMovementType(mt, right);
        players[player].updateAttackType(at);
    }

    /**
     * Initialisiert die Charaktere des Spiels
     * NOTE: derzeit nicht im Gebrauch aufgrund fehlender Charakterauswahl
     * @param pn1 Charaktername des ersten Spielers
     * @param pn2 Charaktername des zweiten Spielers
     * @param bn1 Charaktername des Bosses
     * @throws IOException
     */
    /*public static void initGame(String pn1, String pn2, String bn1) throws IOException{
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
    */
    /**
     * Entfernt den übergebenen Spieler, der Spieler wird danach nicht mehr gezeichnet
     * @param p Spieler welcher vom Spiel entfernt werden soll
     */
    public static void delPlayer(Player p) {
        gamePanel.remove(p);
    }

    /**
     * Gibt das Panel, auf welchem gezeichnet wird, zurück
     * @return Panel, auf welchem gezeichnet wird
     */
    public JPanel getPanel() {
        return gamePanel;
    }

    /**
     * TODO
     */
    public static int getPlayerXPos(int player) {
        return players[player].pos.getXPos();

    }

    /**
     * TODO
     */
    public static int getPlayerYPos(int player) {
        return players[player].pos.getYPos();
    }


    //TODO Projectile hier implementieren
    /*
    public static void main(String[] args) {
        new GameWindow();
    }
    */
}
