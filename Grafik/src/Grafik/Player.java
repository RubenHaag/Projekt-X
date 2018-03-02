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
  private String name;            // Name des Charakters WICHTIG: ungleich Name des Spielers
  private int width;
  private int height;
  private boolean right;
  
  private MovementType movement;
  private Image image;
  private AttackType attack;
  
  /**
   * 
   * @param name
   * @param x
   * @param y
   * @param w
   * @param h
   * @param r
   */
  Player(String name, int x, int y, int w, int h, boolean r){
      this.name = name;
      this.x = x;
      this.y = y;
      width = w;
      height = h;
      movement = MovementType.MOVE;
      attack = AttackType.NON;
      image = null;
      right = r;
    }
  
  /**
   * 
   * @param xPos
   * @param yPos
   */
  public void updatePos(int xPos, int yPos){
    x = xPos;
    y = yPos;
  }
  
  /**
   * 
   * @param mt
   * @param r
   */
  public void updateMovementType(MovementType mt, boolean r){
    movement = mt;
    right = r;
  }
  
  /**
   * 
   * @param at
   * @param right
   */
  public void updateAttackType(int at, boolean right){
    switch(at) {
    case 0:
      attack = AttackType.NON;
      break;
    case 1:
      attack = AttackType.NORMAL;
      break;
    case 2:
      attack = AttackType.SPECIAL1;
      break;
    case 3:
      attack = AttackType.SPECIAL2;
      break;
    }
  }
  
  /**
   * 
   */
  public void takeDmg() {
    
  }
  
  /**
   * 
   */
  public void die() {
    
  }
  
  /**
   * 
   */
  public void paint(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      this.setBounds(x, y, width, height);
      // erst Bewegungsanamation zeichnen
      switch(movement){
      case IDLE:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_idle.gif");
        break;
      case MOVE:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_move.gif");       
        break;
      case JUMPING:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_jumping.gif");       
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
      case NORMAL:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_attack.gif");
        break;
      case SPECIAL1:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special1.gif");
        break;
      case SPECIAL2:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_att_special2_r.gif");
        break;
      }
      if(right) {
    	  g2d.drawImage(image, 0, 0, width, height, this);
      }
      else {
    	  g2d.drawImage(image, width-1, 0, -width, height, this);
      }
  }
  
  /**
   * 
   * @return
   */
  public String getCN(){
    return name;
  }
  
  /**
   * 
   * @return
   */
  public MovementType getMT(){
    return movement;
  }
  
  /**
   * 
   * @return
   */
  public AttackType getAT(){
    return attack;
  }
}

