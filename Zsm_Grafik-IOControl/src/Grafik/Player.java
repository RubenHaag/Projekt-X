package grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Repraesentiert einen Spieler mit all seinen Animation
 * @author Fabian Scherer
 */
@SuppressWarnings("serial")
class Player extends JComponent{          
  private int x;          
  private int y;      
  private String name;            // Name des Charakters bzw. der Datei
  private int width;
  private int height;
  private boolean right;
  private boolean takedmg;
  private boolean dead;
  private short deadZaehler;
  private short attackZaehler;
  private short zaehler2;
  private MovementType movement;
  private PlayerAnimation anim;
  private BufferedImage idle;
  private BufferedImage dmg;
  private AttackType attack;
  private int i;
  
  /**
   * Konstruktor des Player Objekts
   * Initialisiert alle Attribute mit den �bergebenen Werten und zus�tzlichen Standardwerten
   * @param name Dateianfang bzw. Charaktername
   * @param x X-Position der oberen linken Ecke
   * @param y Y-Position der oberen linken Ecke
   * @param w Breite des Spielers
   * @param h H�he des Spielers
   * @param r Blickrichtung des Spielers, true = rechts
 * @throws IOException 
   */
  Player(String name, int x, int y, int w, int h, boolean r){
      this.name = name;
      this.x = x;
      this.y = y;
      width = w;
      height = h;
      movement = MovementType.JUMPING;
      attack = AttackType.NON;
      right = r;
      deadZaehler = 0;
      zaehler2 = 0;
      attackZaehler = 0;
      anim = new PlayerAnimation(name);
      i = 0;
    }
  
  public void initPlayerAnimations() throws IOException {
	  anim.initPlayerAnimations();
	  anim.start();
	  anim.setAnimation(2);
  }
  
  /**
   * Aktualisiert die Position des Spielers
   * @param xPos neue X-Position
   * @param yPos neue Y-Position
   */
  public void updatePos(int xPos, int yPos){
    x = xPos;
    y = yPos;
  }
  
  /**
   * Aktualisiert die Bewegungsart und die Blickrichtung des Spielers (Stehend, Bewegend, Springend)
   * @param mt  Bewegungsart des Spielers
   * @param r Blickrichtung des Spielers, wenn true, Blickrichtung = rechts
   */
  public void updateMovementType(MovementType mt, boolean r){
    movement = mt;
    right = r;
    switch(movement){
    case IDLE:
  	  break;
    case MOVE:
    	anim.setAnimation(0);
    	break;
    case JUMPING:
    	anim.setAnimation(1);
    	break;
    default:
    	System.out.println("Irgendwas ist schiefgelaufen");
    	break;
    }
  }
  
  /**
   * Aktualisiert den Angriffstyp
   * NOTE: Es gibt trotzdem nur eine Angriffs-Animation pro Charakter
   * @param at Angriffstyp des Charakters
   */
  public void updateAttackType(int at){
    switch(at) {
    case 0:
      attack = AttackType.NON;
      break;
    case 1:
      attack = AttackType.NORMAL;
      anim.setAnimation(2);
      attackZaehler = 0;
      break;
    case 2:
      attack = AttackType.SPECIAL1;
      anim.setAnimation(3);
      attackZaehler = 0;
      break;
    case 3:
      attack = AttackType.SPECIAL2;
      anim.setAnimation(4);
      attackZaehler = 0;
      break;
    }
  }
  
  /**
   * L�sst den Spieler Schaden nehmen(grafisch)
   */
  public void takeDmg() {
    takedmg =  true;
  }
  
  /**
   * L�sst den Spieler sterben(grafisch)
   */
  public void die() {
    dead = true;
    anim.setAnimation(5);
  }
  
  public void repaint() {
	  super.repaint(0, x, y, width, height);
	  
  }
  
  /**
   * Paint Methode des Spielers
   * Zeichnet den Spieler je nach Bewegungsart, Angriffstyp und ob er gerade Schaden erlitten hat oder gestorben ist
   */
  public void paintComponent(Graphics g) {
	  this.setBounds(x, y, width, height); 
	  if(!dead) {
		  if(takedmg) {
		    	if(dmg == null){
		    		try {
		    			dmg = ImageIO.read(new File("Assets/Charaktere/" + name + "_damage.png"));
		    		} catch (IOException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    	}
		    	if(right) {
		        	g.drawImage(dmg, 0, 0, width, height, null);
		        	zaehler2++;
		        }
		        else {
		        	g.drawImage(dmg, width-1, 0, -width, height, null);
		        	zaehler2++;
		        }
		  }
		  else {
			  if(movement == MovementType.IDLE) {
				  if(idle == null){
			            try {
			              idle = ImageIO.read(new File("Assets/Charaktere/" + name + "_idle.png"));
			            } catch (IOException e) {
			              // TODO Auto-generated catch block
			              e.printStackTrace();
			            }
			      }
				  if(right) {
		        	g.drawImage(idle, 0, 0, width, height, null);
				  }
				  else {
		        	g.drawImage(idle, width-1, 0, -width, height, null);
				  }
			  }
			  else {
				  if(attack != AttackType.NON) {
				      attackZaehler++;
				  }
		    	if(right) {
		        	g.drawImage(anim.getImage(), 0, 0, width, height, this);
		    	}
		        else {
		        	g.drawImage(anim.getImage(), width-1, 0, -width, height, this);
		        }
			  }  		  
		   }  
	  }
	  else {
		if(right) {
      		g.drawImage(anim.getImage(), 0, 0, width, height, this);
  		}
      	else {
      		g.drawImage(anim.getImage(), width-1, 0, -width, height, this);
      	}
		deadZaehler++;
	  }
	  if(deadZaehler >= 5) {
		  Game.delPlayer(this);
	  }
	  if(zaehler2 >= 5) {
		  takedmg = false;
		  zaehler2 = 0;
	  }
	  if(attackZaehler >= 5) {
		  attack = AttackType.NON;
		  attackZaehler = 0;
	  }
	  try {
		Thread.sleep(5);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  /**
   * Gibt die Bewegungsart zur�ck
   * @return Bewegungsart des Spielers
   */
  public MovementType getMT(){
    return movement;
  }
  
  /**
   * Gibt den Angriffstyp des Spielers zur�ck
   * @return Angriffstyp des Spielers
   */
  public AttackType getAT(){
    return attack;
  }
  
  /**
   * TODO
   */
  public int getXPos() {
	  return x;
  }
  
  /**
   * TODO
   */
  public int getYPos() {
	  return y;
  }
}

