package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Projektilklasse, verwaltet immer ein Projektil
 * @author Fabian Scherer
 *
 */
public class Projectile extends JComponent{
  private int x;
  private int y;
  private String dateiname;
  private BufferedImage pi = null;
  
  /**
   * Konstruktor
   * @param x X-Position der oberen linken Ecke
   * @param y Y-Position der oberen linken Ecke
   * @param dat Dateiname(kein Pfad und auch keine Dateiendung)
   */
  Projectile(int x, int y, String dat){
    dateiname = dat;
    this.x = x;
    this.y = y;
  }
  
  /**
   * Aktualisiert ddie Position des Projektils
   * @param x X-Position der oberen linken Ecke
   * @param y Y-Position der oberen linken Ecke
   */
  public void updatePos(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Gibt die X-Position der oberen linken Ecke zurück
   */
  public int getX() {
     return x;
  }
   
  /**
   * Gibt die Y-Position der oberen linken Ecke zurück
   */
  public int getY() {
     return y;
  }
  
  /**
   * Paint-Methode: Zeichnet das Projektil
   */
  public void paint(Graphics g) {
    this.setBounds(x, y, 50, 50);
      if(pi == null){
        try {
          pi = ImageIO.read(new File("Assets/GUI/" + dateiname + ".png"));  
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    g.drawImage(pi, 0, 0, 50, 50, null);
      
  }
  
}
