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
 * 
 * @author Fabian Scherer
 *
 */
public class Menue {
  private MenueButton play;
  private MenueButton settings;
  private MenueButton quit;
  private BufferedImage bgi = null;
  private MouseListener pl = new MouseListener(){

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
      play.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      play.changeClicked(false);
      RenderManager.changeState(State.LOGIN);
    }
    
  };
  
  private MouseListener sl = new MouseListener(){

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
      settings.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      settings.changeClicked(false);
      RenderManager.changeState(State.SETTINGS);
    }
    
  };

  private MouseListener ql = new MouseListener(){

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
      quit.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      quit.changeClicked(false);
      RenderManager.setRunning(false);
    }
    
  };

  private MenuePanel p;
  
  /**
   * Konstruktor
   */
  public Menue(){
    p = new MenuePanel();
    play = new MenueButton("Play", pl, 850, 300, 400, 160);
    quit = new MenueButton("Quit", ql, 900, 700, 300, 120);
    settings = new MenueButton("Settings", sl, 900, 550, 300, 120);
    p.add(play.getPanel());
    p.add(quit.getPanel());
    p.add(settings.getPanel());
    
    
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zur�ck
   * @return Panel, auf welchem gezeichnet wird
   */
  public JPanel getPanel(){
    return p;
  }
  
  /**
   * Zeichnet das Panel neu
   */
  public void render(){
    p.repaint();
  }
  
  /**
   * Hilfsklasse f�r das Menue
   * @author Fabian Scherer
   *
   */
  @SuppressWarnings("serial")
    class MenuePanel extends JPanel{
      public MenuePanel() {
        super();
        
      }
      
      @Override
      public void paint(Graphics g) {
        this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
        if(bgi == null){
          try {
            bgi = ImageIO.read(new File("Assets/GUI/Main_Menue_v2.png"));  
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
