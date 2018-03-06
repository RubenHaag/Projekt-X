package bessereGrafik;

public abstract class GameObject {
    private Position pos;
    private int width;
    private int height;
    private boolean isJumping;
    private boolean isLookingRight;

    public GameObject(Position pos, int width, int height) {
        this.pos = pos;
        this.width = width;
        this.height = height;
        this.isJumping = false;
        this.isLookingRight = false;
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
