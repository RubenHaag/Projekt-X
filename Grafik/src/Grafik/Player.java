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
  private short zaehler;
  private short zaehler2;
  private MovementType movement;
  private Image[] image;
  private short animationZaehler;
  private short imageZaehler;
  private BufferedImage idle;
  private BufferedImage dmg;
  private BufferedImage[][] animations;
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
 * @throws IOException 
   */
  Player(String name, int x, int y, int w, int h, boolean r) throws IOException{
      this.name = name;
      this.x = x;
      this.y = y;
      width = w;
      height = h;
      movement = MovementType.MOVE;
      attack = AttackType.NON;
      image = null;
      right = r;
      zaehler = 0;
      zaehler2 = 0;
      image = new Image[6];
      animations = new BufferedImage[6][5];
      animationZaehler = 0;
      imageZaehler = 0;
      /*Toolkit toolkit = Toolkit.getDefaultToolkit();
      image[0] = toolkit.createImage("Assets/Charaktere/" + name + "_move.gif");
      image[1] = toolkit.createImage("Assets/Charaktere/" + name + "_jumping.gif");
      image[2] = toolkit.createImage("Assets/Charaktere/" + name + "_attack.gif");
      image[3] = toolkit.createImage("Assets/Charaktere/" + name + "_attack.gif");
      image[4] = toolkit.createImage("Assets/Charaktere/" + name + "_attack.gif");
      image[5] = toolkit.createImage("Assets/Charaktere/" + name + "_dead.gif");
      for(Image i:image) {
    	  i = i.getScaledInstance(width, height, 0);
    	  i.setAccelerationPriority((float) 0.999);
      }*/
      
      //@Ruben es gibt aus irgendeinem Grund jetzt eine FileNotFoundException obwohl die Dateinamen alle richtig sind
      for(int i = 1; i < 6; i++) {
    	 animations[0][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_laufen(" + i + ").png"));
      }
      for(int i = 0; i <= 5; i++) {
     	 animations[1][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_jump (" + i + ").png"));
       }
      for(int i = 0; i <= 5; i++) {
     	 animations[2][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_attack(" + i + ").png"));
       }
      for(int i = 0; i <= 5; i++) {
     	 animations[3][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_attack(" + i + ").png"));
       }
      for(int i = 0; i <= 5; i++) {
     	 animations[4][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_attack(" + i + ").png"));
       }
      for(int i = 0; i <= 5; i++) {
     	 animations[5][i-1] = ImageIO.read(new File("Assets/Charaktere/" + name + "/" + name + "_dead (" + i + ").png"));
       }
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
    System.out.println(r);
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
    	animationZaehler = 0;
    	break;
    case JUMPING:
    	animationZaehler = 1;
    	break;
    default:
    	System.out.println("Irgendwas ist schiefgelaufen");
    	break;
    }
    imageZaehler = 0;
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
      animationZaehler = 2;
      break;
    case 2:
      attack = AttackType.SPECIAL1;
      animationZaehler = 3;
      break;
    case 3:
      attack = AttackType.SPECIAL2;
      animationZaehler = 4;
      break;
    }
    imageZaehler = 0;
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
  public void repaint() {
	  super.repaint(0, x, y, width, height);
	  
  }
  
  /**
   * Paint Methode des Spielers
   * Zeichnet den Spieler je nach Bewegungsart, Angriffstyp und ob er gerade Schaden erlitten hat oder gestorben ist
   */
  public void paintComponent(Graphics g) {
      //Graphics2D g2d = (Graphics2D) g;
      this.setBounds(x, y, width, height);
      // erst Bewegungsanamation laden
      
      //dann Angriff
//      switch(attack){
//      case NON:
//        //falls kein Angriff ausgeführt wird, normal die Bewegung zeichnen
//        break;
//      case NORMAL:
//    	imageZaehler = 2;
//    	  break;
//      case SPECIAL1:
//    	imageZaehler = 3;
//        break;
//      case SPECIAL2:
//    	imageZaehler = 4;
//        break;
//      }
//      if(dead) {
//    	  imageZaehler = 5;
//    	  zaehler++;
//      }
      
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
    	  g.drawImage(animations[animationZaehler][imageZaehler], 0, 0, width, height, this);
    	  imageZaehler++;
      }
      else if(!right && movement != MovementType.IDLE && !takedmg){
    	  g.drawImage(animations[animationZaehler][imageZaehler], width-1, 0, -width, height, this);
    	  imageZaehler++;
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
      if(imageZaehler >= 5) {
    	  imageZaehler = 0;
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

