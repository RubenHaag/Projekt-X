package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Lebens- und Manaanzeige des spielenden Spielers
 * @author Fabian Scherer
 *
 */
public class PlayerAnzeige{
  private double hpp;
  private double mpp;
  private BufferedImage frame = null;
  private BufferedImage hpbar = null;
  private BufferedImage mpbar = null;
  //private BufferedImage hpwhite = null;
  //private BufferedImage mpwhite = null;
  private AnzeigePanel p;
  
  /**
   * Initialisiert die Lebens- und Manaanzeige
   * @param hp Lebenspunkte in Prozent
   * @param mp Manapunkte in Prozent
   */
  PlayerAnzeige(double hp, double mp){
    p = new AnzeigePanel();
    
    if(hp > 100) {
      hp = 100;
    }
    else if(hp < 0) {
      hp = 0;
    }
    hpp = hp;
    
    if(mp > 100) {
      mp = 100;
    }
    else if(mp < 0) {
      mp = 0;
    }
    mpp = mp;
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zurück
   * @return  Panel, auf welchem gezeichnet wird
   */
  public JPanel getPanel() {
    return p;
  }
  
  /**
   * Aktualisiert die Lebensanzeige
   * @param p Neuer Lebensprozentsatz
   */
  public void updateHP(int p) {
    if(p > 100) {
      p = 100;
    }
    else if(p < 0) {
      p = 0;
    }
    hpp = p;
  }
  
  /**
   * Aktualisiert die Manaanzeige
   * @param p Neuer Manaprozentsatz
   */
  public void updateMP(int p) {
    if(p > 100) {
      p = 100;
    }
    else if(p < 0) {
      p = 0;
    }
    mpp = p;
  }
  
  /**
   * Hilfsklasse
   * @author F
   *
   */
  class AnzeigePanel extends JPanel{
    AnzeigePanel(){
      super();
    }
    
    public void paint(Graphics g) {
      this.setBounds(10, 10, 300, 100);
      if(frame == null){
        try {
          frame = ImageIO.read(new File("Assets/GUI/Lifebar_v2_empty.png"));
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(hpbar == null) {
        try {
          hpbar = ImageIO.read(new File("Assets/GUI/Healthbar.png"));
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      if(mpbar == null) {
        try {
          mpbar = ImageIO.read(new File("Assets/GUI/Manabar.png"));
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      /*if(hpwhite == null) {
    	  try {
              hpwhite = ImageIO.read(new File("Assets/GUI/Healthbar_white.png"));
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
      }
      if(mpwhite == null) {
    	  try {
              mpwhite = ImageIO.read(new File("Assets/GUI/Manabar_white.png"));
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
      }*/
      g.drawImage(frame, 0, 0, 300, 100, null);
      g.drawImage(hpbar, 0, 0, 300, 100, null);
      g.drawImage(mpbar, 27, 0, 300, 100, null);
      /*if(hpp < 100.0) {
    	  g.drawImage(hpwhite, (int) (3*hpp), 0, (int) (300 - 3*hpp), 100, null);
      }
      if(mpp < 100.0) {
    	  g.drawImage(mpwhite, (int) (3*mpp + 27- 2*27*mpp/100 ), 0, (int) (300 - 3*mpp), 100, null);
      }*/
      this.paintComponents(g);
      
    }
  }
}
