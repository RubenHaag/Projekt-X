package bessereGrafik;

import java.awt.*;

public abstract class GameObject {
    private Position pos;
    private int width;
    private int height;
    private boolean isJumping;
    private boolean isLookingRight;
    private MovementType moveType;

    public GameObject(Position pos, int width, int height, boolean isLookingRight) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.isJumping = false;
        this.isLookingRight = isLookingRight;
        moveType = MovementType.IDLE;
    }

    public abstract void selfPaint(Graphics g);

    public void updateMovementType (MovementType moveType, boolean isLookingRight) {
        this.isLookingRight = isLookingRight;
        this.moveType = moveType;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
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

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isLookingRight() {
        return isLookingRight;
    }

    public void setLookingRight(boolean lookingRight) {
        isLookingRight = lookingRight;
    }
}
