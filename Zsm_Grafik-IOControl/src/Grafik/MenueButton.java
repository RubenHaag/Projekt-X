package grafik;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Button-Klasse, dient zur Darstellung und Verwaltung eines Buttons
 * @author Fabian Scherer
 *
 */
class MenueButton{
  private MouseListener listener;                 // Listener für diesen Button
  private ButtonPanel panel;                    // Panel auf welchem gezeichnet wird
  private String text;                        // Buttontext
  private BufferedImage image_cl = null;    // Bild des Buttons
  private BufferedImage image_ncl = null;
  private boolean clicked;
  int x;
  int y;
  int width;
  int height;
  
  /**
   * 
   * @param name Text der auf dem Button stehen soll bzw. Dateianfang
   * @param l MouseListener, welcher den Button steuert
   * @param x X-Position der linken oberen Ecke
   * @param y Y-Position der linken oberen Ecke
   * @param width Breite des Buttons
   * @param height H�he des Buttons
   */
  public MenueButton(String name, MouseListener l, int x, int y, int width, int height) {
    panel = new ButtonPanel();
    text = name;
    panel.addMouseListener(l);
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    clicked = false;
   
    
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zur�ck
   * @return Panel, auf welchem gezeichnet wird
   */
  public JPanel getPanel() {
    return panel;
  }
  
  /**
   * Veraendert den Buttonstatus zu geklickt oder ungeklickt
   * @param clicked Wurde der Button geklickt?
   */
  public void changeClicked(boolean clicked){
    this.clicked = clicked;
  }
  
  /**
   * Veraendert die Buttonaufschrift
   * @param t Neue Buttonaufschrift
   */
  public void changeText(String t) {
    text = t;
  }
  
  /**
   * Gibt die Buttonaufschrift zur�ck
   * @return Buttonaufschrift
   */
  public String getText() {
    return text;
  }
  
  /**
   * Hilfsklasse zum Zeichnen des Buttons
   * @author F
   *
   */
  @SuppressWarnings("serial")
  class ButtonPanel extends JPanel{
    public ButtonPanel() {
      super();
    }
    
    @Override
    public void paint(Graphics g) {
      this.setBounds(x, y, width, height);
      if(!clicked){
        if(image_ncl == null){
          try {
            image_ncl = ImageIO.read(new File("Assets/GUI/" + text + "_non_click.png"));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        g.drawImage(image_ncl, 0, 0, width, height, null);
      }
      else{
        if(image_cl == null){
          try {
            image_cl = ImageIO.read(new File("Assets/GUI/" + text + "_click.png"));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        g.drawImage(image_cl, 0, 0, width, height, null);
      }
      
    }
  }
}
