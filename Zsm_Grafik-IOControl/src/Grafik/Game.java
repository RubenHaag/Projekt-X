package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**Hauptklasse des Spiels innerhalb der Grafik. Verwaltet den Start und die Grafik des Spiels
 * @author Fabian Scherer
  */
public class Game{
	private static Player p1;			//Spieler1
	private static Player p2;			//Spieler2
	private static Player b1;			//Spieler3(Boss)
	private BufferedImage map = null;
	private GamePanel gp;
	private PlayerAnzeige lm;
	
	Game(){
		gp = new GamePanel();
		lm = new PlayerAnzeige(100, 100);
		gp.add(lm.getPanel());
	}
	
	public static void takeDmg(int player) {
		
	}
	
	public static void die(int player) {
		
	}
	
	public static void spawnProjectile(int x, int y, boolean right) {
		
	}
	
	public static void destroyProjectile(int x, int y) {
		
	}
	
	/**Aktualisiert alle Parameter eines Spielers. 
	 * 
	 * @param cn Charaktername des zu aktualisierenden Spielers
	 * @param x x-Position des zu aktualisierenden Spielers
	 * @param y y-Position des zu aktualisierenden Spielers
	 * @param mt Art der Bewegung des zu aktualisierenden Spielers
	 * @param at Art der Attacke des zu aktualisierenden Spielers 0 = NON , 1 = Normal, 2 = Special1, 3 = Special2
	 * @param right Blickrichtung des Spielers
	 * @param hp Lebenspunkte des Spielers als Prozent
	 * @param mp Manapunkte des Spielers als Prozent
	 */
	 public static void updatePlayer(int player, int x, int y, MovementType mt, int at, boolean right, double hp, double mp){
	    switch(player) {
	    case 0:
	    	b1.updatePos(x, y);
		    if(mt != b1.getMT()){
		    	b1.updateMovementType(mt);
		    }
		    b1.updateAttackType(at, right);
	    	break;
	    case 1:
	    	p1.updatePos(x, y);
		    if(mt != p1.getMT()){
		    	p1.updateMovementType(mt);
		    }
		    p1.updateAttackType(at, right);  
	    	break;
	    case 2:
	    	p2.updatePos(x, y);
		    if(mt != p2.getMT()){
		    	p2.updateMovementType(mt);
		    }
		    p2.updateAttackType(at, right);  
	    	break;
	    default:
	    	System.out.println("Irgendwas ist schiefgelaufen beim Update der Spieler");
	    	break;
	    }
	}	
	
    /** Initialisiert alle für das Spiel wichtige Grfikkomponenten
     * 
     * @param p1 Charaktername des ersten Spielers
     * @param p2 Charaktername des zweiten Spielers
     * @param b1 Charaktername des Bosses
     */
	public void initGame(String p1, String p2, String b1){
	      this.p1 = new Player(p1, 0, 0, 100, 200);
	      gp.add(this.p1);
	      this.p2 = new Player(p2, 0, 0, 100, 200);
	      gp.add(this.p2);
	      this.b1 = new Player(b1, 0, 0, 200, 400);
	      gp.add(this.b1);
	}
    
    public JPanel getPanel() {
    	return gp;
    	
    }
    
    /**Zeichnet das Panel neu
     * 
     */
    public void render() {
    	gp.repaint();
    }
    
    /** Hilfsklasse für das Game Objekt, auf diesem Panel wird gezeichnet
     * 
     * @author Fabian Scherer
     *
     */
    @SuppressWarnings("serial")
	 class GamePanel extends JPanel{
	  	public GamePanel() {
	  		super();
	  	}
	  	
	  	@Override
	  	public void paint(Graphics g) {
	  		this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
	  		try {
				map = ImageIO.read(new File("Assets/GUI/Login_screen_v2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(map, 0, 0, RenderManager.getFWidth(), RenderManager.getFHeight(), null);
	  		this.paintComponents(g);
	  		
	  	}
	 }
}