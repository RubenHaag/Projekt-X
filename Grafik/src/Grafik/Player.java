package Grafik;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
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
  private int zaehler;
  private int zaehler2;
  private MovementType movement;
  private Image image;
  private BufferedImage idle;
  private BufferedImage dmg;
  private AttackType attack;
  
  /**
   * Konstruktor des Player Objekts
   * Initialisiert alle Attribute mit den �bergebenen Werten und zus�tzlichen Standardwerten
   * @param name Dateianfang bzw. Charaktername
   * @param x X-Position der oberen linken Ecke
   * @param y Y-Position der oberen linken Ecke
   * @param w Breite des Spielers
   * @param h H�he des Spielers
   * @param r Blickrichtung des Spielers, true = rechts
   */
  Player(String name, int x, int y, int w, int h, boolean r){
      this.name = name;
      this.x = x;
      this.y = y;
      width = w;
      height = h;
      movement = MovementType.IDLE;
      attack = AttackType.NON;
      image = null;
      right = r;
      zaehler = 0;
      zaehler2 = 0;
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
  }
  
  /**
   * Paint Methode des Spielers
   * Zeichnet den Spieler je nach Bewegungsart, Angriffstyp und ob er gerade Schaden erlitten hat oder gestorben ist
   */
  public void paint(Graphics g) {
      //Graphics2D g2d = (Graphics2D) g;
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      this.setBounds(x, y, width, height);
      // erst Bewegungsanamation laden
      switch(movement){
      case IDLE:
    	  if(idle == null){
              try {
                idle = ImageIO.read(new File("Assets/Charaktere/" + name + "_idle.png"));
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
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
        image = toolkit.getImage("Assets/Charaktere/" + name + "_attack.gif");
        break;
      case SPECIAL2:
        image = toolkit.getImage("Assets/Charaktere/" + name + "_attack.gif");
        break;
      }
      if(dead) {
    	  image = toolkit.getImage("Assets/Charaktere/" + name + "_dead.gif");
    	  zaehler++;
      }
      if(takedmg) {
    	  if(dmg == null){
              try {
                dmg = ImageIO.read(new File("Assets/Charaktere/" + name + "_damage.png"));
              } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
          }
      }
      if(right && movement != MovementType.IDLE && !takedmg) {
    	  g.drawImage(image, 0, 0, width, height, this);
      }
      else if(!right && movement != MovementType.IDLE && !takedmg){
    	  g.drawImage(image, width-1, 0, -width, height, this);
      }
      else if(right && movement == MovementType.IDLE && !takedmg) {
    	  g.drawImage(idle, 0, 0, width, height, null);
      }
      else if(!right && movement == MovementType.IDLE && !takedmg) {
    	  g.drawImage(idle, width-1, 0, -width, height, null);
      }
      else if(right && takedmg) {
    	  g.drawImage(dmg, 0, 0, width, height, null);
    	  zaehler2++;
      }
      else {
    	  g.drawImage(dmg, width-1, 0, -width, height, null);
    	  zaehler2++;
      }
      if(zaehler >= 5) {
    	  Game.delPlayer(this);
      }
      if(zaehler2 >= 5) {
    	  takedmg = false;
    	  zaehler2 = 0;
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
}

