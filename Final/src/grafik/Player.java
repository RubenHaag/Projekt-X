package grafik;

import java.awt.*;
import java.io.IOException;


public class Player extends GameObject {
    private PlayerAnimation animation;
    private boolean isRendered;

    /**
     * Konstruktor
     * @param pos Position des Spielers
     * @param width Breite des Spielers
     * @param height Höhe des Spielers
     * @param isLookingRight Sieht der Spieler nach rechts?
     * @param name Name des Charakters
     * @param imageDir DateiPfad zur ImageDirectory
     * @throws IOException Wenn eins der zu ladenden Spielerbilder nicht vorhanden ist.
     */
    public Player(Position pos, int width, int height, boolean isLookingRight, String name, String imageDir) throws IOException {
        super(pos, width, height, isLookingRight);

        this.animation = new PlayerAnimation(name, imageDir);
        this.animation.start();
        updateMovementType(MovementType.IDLE, isLookingRight);
        updateAttackType(0);
        System.out.println("fertig");
        isRendered = true;
    }

    public void takeDamage(){
        animation.setAnimation(7);
    }

    public boolean isRendered() {
        return isRendered;
    }

    public void setRendered(boolean rendered) {
        isRendered = rendered;
    }

    
    public void updateMovementType(MovementType mt, boolean r) {
        setLookingRight(r);
        switch (mt) {
            case IDLE:
                animation.setAnimation(6);
                break;
            case MOVE:
                animation.setAnimation(0);
                break;
            case JUMPING:
                animation.setAnimation(1);
                break;
            default:
                System.out.println("Irgendwas ist schiefgelaufen");
                break;
        }
    }

    /**
     * Updated den Attacktyp
     * @param attackInt Integer f�r den AttackType
     */
    public void updateAttackType(int attackInt) {
        switch(attackInt) {
            case 0:
                break;
            case 1:
                animation.setAnimation(2); //Attacke 1

                break;
            case 2:
                animation.setAnimation(3); //Special1
                break;
            case 3:
                animation.setAnimation(4); //Special2
                break;
        }
    }

    @Override
    public void selfPaint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (isLookingRight()) {
            g2d.drawImage(animation.getImage(), pos.getXPos(), pos.getYPos(), getWidth(), getHeight(), null);
        } else {
            g2d.drawImage(animation.getImage(), pos.getXPos(), pos.getYPos(), -getWidth(), getHeight(), null);

        }

    }
}
