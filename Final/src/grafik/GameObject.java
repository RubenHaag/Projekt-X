package grafik;

import java.awt.*;

public abstract class GameObject {
    protected Position pos;
    protected Position posTrans;
    private int width;
    private int height;
    private boolean isLookingRight;
    private MovementType moveType;

    /**
     * Konstruktor
     * @param pos Position des GameObjects
     * @param width Breite des GameObjects
     * @param height Höhe des GameObjects
     * @param isLookingRight Blickrichtung des GameObjects; true = rechts
     */
    public GameObject(Position pos, int width, int height, boolean isLookingRight) {
        this.pos = pos;
        this.posTrans = new Position(0, 0);
        this.width = width;
        this.height = height;
        this.isLookingRight = isLookingRight;
        moveType = MovementType.IDLE;
    }

    /**
     * Abstrakte Methode die in jedem Sohn überschrieben werden muss. Die Methode wird aufgerufen um den Sohn zu zeichnen
     * @param g
     */
    public abstract void selfPaint(Graphics g);

    /**
     * Updated den Bewegungstyp und die Blickrichtung des GameObjects
     * @param moveType Bewegungstyp des GameObjects
     * @param isLookingRight Sieht das GameObject nach rechts
     */
    public void updateMovementType(MovementType moveType, boolean isLookingRight) {
        this.isLookingRight = isLookingRight;
        this.moveType = moveType;
    }

    public Position getPos() {
        return pos;
    }
    
    /**
     * Setzt die Position eines GameObjects
     * @param pos Position auf die gesetzt werden soll
     */
    public void setPos(Position pos) {
        int x = pos.getXPos() - this.pos.getXPos(); //posTrans gibt an wieweit translatet werden soll
        int y = pos.getYPos() - this.pos.getYPos();
        posTrans.setXPos(x);
        posTrans.setYPos(y);
        this.pos = pos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLookingRight() {
        return isLookingRight;
    }

    public void setLookingRight(boolean lookingRight) {
        isLookingRight = lookingRight;
    }
}
