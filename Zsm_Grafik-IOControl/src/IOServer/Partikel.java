package IOServer;

import java.util.Timer;
import java.util.TimerTask;

import grafik.RenderManager;

/**
 *
 * @author Patrick Waltermann mit geringer Unterst�tzung von Lukas Hofmann
 *
 */
public class Partikel {
    private double xVel, yVel, down, yVelw, friction1, friction2, friction, width;
    private Rectangle gr;
    private Position pos;
    private boolean isJumping;
    long last_time = System.nanoTime();
    /**
     * Stellt die grundlegende Fallbewegung ein
     */

    public Partikel() {
        down = 200;
        friction1 = 80;
        friction2 = -friction1;
        gr = new Rectangle(new Position(0,0),1,1);
        pos = new Position(0, 0);
    }
    /**
     *
     * @param pos Position des Rechtecks
     * @param w Breite
     * @param gr Rechteck f�r das ein Partikelobjekt erstellt werden soll.
     */
    public Partikel(Position pos, double w){
        this();
        this.pos = pos;
        this.width = w;
        //this.f1 = f1;

    }
    /**
     * Thread der laufend den Standpunkt, Geschwindigkeit des OBjektes berechnet
     */
    public void play() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        	public void run() {
            long time = System.nanoTime();
            double dt = (double)((time - last_time));
            last_time = time;
            if (xVel < 0) {
                friction = friction1;
            } else if(xVel > 0) {
                friction = friction2;
            }
            if(!isJumping) {
                yVel = yVelw;
                pos.setXPos((int) (pos.getXPos()+dt / 1000000000* xVel));
                pos.setYPos((int) (gr.getTop()+ 1));
                if (xVel > 0 || xVel < 0) {
                    xVel = xVel + dt / 1000000000* friction;
                }
            } else {
                pos.setXPos((int) (pos.getXPos()+ dt / 1000000000* xVel));
                pos.setYPos((int) (pos.getYPos()+ dt / 1000000000* yVel));
                yVel = yVel + dt / 1000000000* down;
            }
            RenderManager.getGameManager().getpSelf().getHb().setPos(pos);
        }
        }, 0, 100);
    }
    /**
     * Gibt die Geschwindigkeit des Objektes zur�ck.
     * @return gibt die Geschwindigkeit des Objektes zur�ck
     */
    public double getYVel() {
        return yVel;
    }
    /**
     * Setzt die Geschwindigkeit in X-Richtung
     * @param x Geschwindigkeit in X-Richtung
     */
    public void addxVel(double x) {
        xVel = x;
    }
    /**
     * Setzt die Geschwindigkeit in X-Richtung
     * @param x Geschwindigkeit in X-Richtung
     */
    public void addyVel(double x) {
        yVel = -x;

    }
    /**
     * Setzt das Rechteck das der Boden ist
     * @param gr das Rechteck das der Boden ist
     */
    public void updateGround(Rectangle gr) {
        this.gr = gr;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
