package grafik;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Menue um Einstellungen vorzunehmen
 * @author Fabian Scherer
 *
 */
public class SettingsMenue {
  private MenueButton music;
  private MenueButton sound;
  private MenueButton back;
  private BufferedImage bgi;
  private SettingsPanel p;
  private MouseListener musicl = new MouseListener() {

    @Override
    public void mouseClicked(MouseEvent arg0) {
      
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
      music.changeClicked(true);
      
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      music.changeClicked(false);
      if(music.getText().matches("Music_On")) {
        music.changeText("Music_Off");
        // Aendere Einstellung zu Music Off
      }
      else {
        music.changeText("Music_On");
        // Aendere Einstellung zu Music On
      }
    }
    
  };
  
  private MouseListener soundl = new MouseListener() {

    @Override
    public void mouseClicked(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      
    }

    @Override
    public void mouseExited(MouseEvent e) {
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
      sound.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      sound.changeClicked(false);
      if(sound.getText().matches("Sound_On")) {
        sound.changeText("Sound_Off");
        // Aendere Einstellung zu Sound Off
      }
      else {
        sound.changeText("Sound_On");
        // Aendere Einstellung zu Sound On
      }
    }
    
  };
  
  private MouseListener backl = new MouseListener() {

    @Override
    public void mouseClicked(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void mousePressed(MouseEvent e) {
      back.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      back.changeClicked(false);
      RenderManager.changeState(State.HAUPTMENUE);
    }
    
  };
  
  /**
   * Konstruktor
   */
  SettingsMenue(){
    music = new MenueButton("Music_On", musicl, 300, 200, 400, 160);
    sound = new MenueButton("Sound_On", soundl, 300, 380, 400, 160);
    back = new MenueButton("Button_Back", backl, 10, 620, 200, 80);
    p = new SettingsPanel();
    p.add(music.getPanel());
    p.add(sound.getPanel());
    p.add(back.getPanel());
    
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zur�ck
   * @return Panel, auf welchem gezeichnet wird
   */
  public JPanel getPanel() {
    return p;
  }
  
  /**
   * Hilfsklasse
   * @author Fabian Scherer
   *
   */
  class SettingsPanel extends JPanel{
    SettingsPanel(){
      super();
    }
    
    public void paint(Graphics g) {
      this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
        if(bgi == null){
          try {
            bgi = ImageIO.read(new File("Assets/GUI/Settings_Screen_v2.png"));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        g.drawImage(bgi, 0, 0, RenderManager.getFWidth(), RenderManager.getFHeight(), null);
        this.paintComponents(g);
    }
  }
}
