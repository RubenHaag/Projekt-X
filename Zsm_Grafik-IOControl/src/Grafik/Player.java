package Grafik;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Repräsentiert einen Spieler mit all seinen Animation
 * @author Fabian Scherer
 */
class Player extends JComponent{			 		
	private int x;					
	private int y;				
	private int width;
	private int height;
	private String name;						// Name des Charakters WICHTIG: ungleich Name des Spielers
	private MovementType movement;
	private Image image;
	private AttackType attack;
	
	// Initialisiert den Spieler
	Player(String name, int x, int y, int w, int h){
		this.name = name;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		movement = MovementType.IDLE;
		attack = AttackType.NON;
		image = null;
	}
	
	public void updatePos(int xPos, int yPos){
		x = xPos;
		y = yPos;
	}
	
	public void updateMovementType(MovementType mt){
		movement = mt;
	}
	
	public void updateAttackType(AttackType at){
		attack = at;
	}
	
	public void paint(Graphics g) {
	    Graphics2D g2d = (Graphics2D) g;
	    Toolkit toolkit = Toolkit.getDefaultToolkit();
	    this.setBounds(x, y, width, height);
	    // erst Bewegungsanamation zeichnen
	    switch(movement){
	    case IDLE:
	    	image = toolkit.getImage("Assets/test.gif");
	    	break;
	    case MOVE_LEFT:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_move_left.gif");
	    	break;
	    case MOVE_RIGHT:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_move_right.gif");		   
	    	break;
	    case JUMPING:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_jumping.gif");		    
	    	break;
	    case FALLING:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_falling.gif");
	    	break;
	    default:
	    	System.out.println("Irgendwas ist schiefgelaufen");
	    	break;
	    }
	    //dann Angriff
	    switch(attack){
	    case NON:
	    	//falls kein Angriff ausgeführt wird, normal die Bewegung zeichnen
	    	break;
	    case NORMAL_L:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_normal_l.gif");
	    	break;
	    case NORMAL_R:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_normal_r.gif");
	    	break;
	    case SPECIAL1_L:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special1_l.gif");
	    	break;
	    case SPECIAL1_R:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special1_r.gif");
	    	break;
	    case SPECIAL2_L:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special2_l.gif");
	    	break;
	    case SPECIAL2_R:
	    	image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special2_r.gif");
	    	break;
	    }
	    g2d.drawImage(image, 0, 0, width, height, this);
	  }
	
	public String getCN(){
		return name;
	}
	
	public MovementType getMT(){
		return movement;
	}
	
	public AttackType getAT(){
		return attack;
	}
}

