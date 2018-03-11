/**
 * 
 * @author Lukas Hofmann
 *
 */
public class Rectangle {
	private Position pos;
	private double width;
	private double heigth;
	
	public Rectangle(Position pos, double width, double heigth) {
		this.pos = pos;
		this.width = width;
		this.heigth = heigth;
	}
	
	public double getRight() {
		return pos.getXPos() + width;
	}
	
	public double getLeft() { return pos.getXPos(); }
	
	public double getTop() {
		return pos.getYPos();
	}
	
	public double getBottom() {
		return pos.getYPos() - heigth;
	}

	public double getWidth() {
		return width;
	}

	public double getHeigth() {
		return heigth;
	}
	
	public void setPos(Position pos){
		this.pos = pos;
	}
	
	public Position getPos(){
		return pos;
	}
	
	public void setHeigth(double h){
		heigth = h;
	}
	
	public void setWidth(double w){
		width =w;
	}
	/**
	 *
	 * @param rectangle
	 * @return �berlappt die Hitbox des eigenen Rechtecks mit einer Hitbox-> true oder false
	 */
	public boolean intersect(Rectangle rectangle) {
			if (!((this.getRight() <= rectangle.getLeft() || this.getLeft() >= rectangle.getRight()) && (this.getBottom() >= rectangle.getTop()))) {
				return true;
			}
		return false;
	}
}
