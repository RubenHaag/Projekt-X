package grafik;

import java.awt.*;

public abstract class GameObject {
    protected Position pos;
    protected Position posTrans;
    private int width;
    private int height;
    private boolean isLookingRight;
    private MovementType moveType;

    public GameObject(Position pos, int width, int height, boolean isLookingRight) {
        this.pos = pos;
        this.posTrans = new Position(0, 0);
        this.width = width;
        this.height = height;
        this.isLookingRight = isLookingRight;
        moveType = MovementType.IDLE;
    }

    public abstract void selfPaint(Graphics g);

    public void updateMovementType(MovementType moveType, boolean isLookingRight) {
        this.isLookingRight = isLookingRight;
        this.moveType = moveType;
    }

    public Position getPos() {
        return pos;
    }

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
