package IOServer;
/**
 * 
 * @author Lukas Hofmann, Patrick Waltermann
 *
 */
public class Position {
	private int x, y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setXPos(int x) {
		this.x = x;
	}

	public int getXPos() {
		return x;
	}

	public void setYPos(int y) {
		this.y = y;
	}

	public int getYPos() {
		return y;
	}
}
