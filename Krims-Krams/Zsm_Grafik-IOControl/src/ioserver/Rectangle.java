package ioserver;
/**
 * 
 * @author Lukas Hofmann
 *
 */
public class Rectangle {
	private Position pos;
	private int width;
	private int heigth;
	
	public Rectangle(Position pos, int width, int heigth) {
		this.pos = pos;
		this.width = width;
		this.heigth = heigth;
	}
	
	public int getRight() {
		return pos.getXPos() + width;
	}
	
	public int getLeft() { return pos.getXPos(); }
	
	public int getTop() {
		return pos.getYPos();
	}
	
	public int getBottom() {
		return pos.getYPos() + heigth;
	}

	public int getWidth() {
		return width;
	}

	public int getHeigth() {
		return heigth;
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public void setHeigth(int h){
		heigth = h;
	}
	
	public void setWidth(int w){
		width =w;
	}
	/**
	 *
	 * @param rectangle
	 * @return �berlappt die Hitbox des eigenen Rechtecks mit einer Hitbox-> true oder false
	 */
	public boolean intersect(Rectangle rectangle) {
        return !((this.getRight() <= rectangle.getLeft() || this.getLeft() >= rectangle.getRight()) && (this.getBottom() >= rectangle.getTop()));
    }
}
