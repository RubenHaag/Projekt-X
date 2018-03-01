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
	private Player p1;			//Spieler1
	private Player p2;			//Spieler2
	private Player b1;			//Spieler3(Boss)
	private BufferedImage map = null;
	private GamePanel gp;
	private PlayerAnzeige lm;
	
	Game(){
		gp = new GamePanel();
		lm = new PlayerAnzeige(100, 100);
		gp.add(lm.getPanel());
	}
	
	
	/**Aktualisiert alle Parameter eines Spielers. 
	 * 
	 * @param cn Charaktername des zu aktualisierenden Spielers
	 * @param x	x-Position des zu aktualisierenden Spielers
	 * @param y y-Position des zu aktualisierenden Spielers
	 * @param mt Art der Bewegung des zu aktualisierenden Spielers
	 * @param at Art der Attacke des zu aktualisierenden Spielers
	 */
	public void updatePlayer(String cn, int x, int y, MovementType mt, AttackType at){
		if(p1.getCN().equals(cn)){
			p1.updatePos(x, y);
			if(mt != p1.getMT()){
				p1.updateMovementType(mt);
			}
			if(at != p1.getAT()){
				p1.updateAttackType(at);
			}
		}
		else if(p2.getCN().equals(cn)){
			p2.updatePos(x, y);
			if(mt != p2.getMT()){
				p2.updateMovementType(mt);
			}
			if(at != p2.getAT()){
				p2.updateAttackType(at);
			}
		}
		else if(b1.getCN().equals(cn)){
			b1.updatePos(x, y);
			if(mt != b1.getMT()){
				b1.updateMovementType(mt);
			}
			if(at != b1.getAT()){
				b1.updateAttackType(at);
			}
		}
		else{
			System.out.println("Irgendwas ist schiefgelaufen beim Update der Spieler");
		}
	}
	
	
	
    /** Initialisiert alle für das Spiel wichtige Grfikkomponenten
     *
     * @param name Name des Spielers der in das Spiel geht
     * @param server Name des Servers auf welchem das Spiel lÃ¤uft
     * @param p1 Charaktername des ersten Spielers
     * @param p2 Charaktername des zweiten Spielers
     * @param b1 Charaktername des Bosses
     */
    public void initGame(String name, String server, String p1, String p2, String b1){
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