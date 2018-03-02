package Grafik;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Menue in welchem die Server ID eingegeben wird
 * @author Fabian Scherer
 *
 */
public class LoginMenue {
  private MenueButton login;
  private MenueButton back;
  private LoginTextfield ltxtf;
  private BufferedImage bgi = null;
  
  private MouseListener bl = new MouseListener() {

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
    
  };  private MouseListener ll = new MouseListener() {

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
    public void mousePressed(MouseEvent arg0) {
      login.changeClicked(true);
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
      login.changeClicked(false);
      //LoginToServer()
      //ChangeState(Heldenauswahl oder Bossauswahl)
      RenderManager.changeState(State.GAME);
    }
    
  };
  
  private JPanel mp;
  
  /**
   * Konstruktor, dient zur Initialisierung
   */
  LoginMenue(){
    login = new MenueButton("Login_button", ll, 200, 400, 600, 180);
    back = new MenueButton("Button_Back", bl, 0, 650, 200, 80);
    ltxtf = new LoginTextfield(100, 120, 800, 280);
    mp = new JPanel() {
      public void paint(Graphics g) {
        this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
          if(bgi == null){
            try {
              bgi = ImageIO.read(new File("Assets/GUI/Login_screen_v3.png"));  
            } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
          g.drawImage(bgi, 0, 0, RenderManager.getFWidth(), RenderManager.getFHeight(), null);
          this.paintComponents(g);
      }
    };
    mp.add(login.getPanel());
    mp.add(back.getPanel());
    mp.add(ltxtf.getPanel());
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zurück
   * @return
   */
  public JPanel getPanel() {
    return mp;
  }
  
}
