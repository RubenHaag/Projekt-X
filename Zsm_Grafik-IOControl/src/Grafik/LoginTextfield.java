package grafik;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Element, welches im LoginMenue gebraucht wird zur Eingabe der Server IP
 * @author Fabian Scherer
 *
 */
public class LoginTextfield {
  private JPanel p;
  private BufferedImage img;
  private String text;
  private KeyListener kl = new KeyListener(){

    @Override
    public void keyPressed(KeyEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
      // TODO Auto-generated method stub
    	if(arg0.getKeyChar() >= 32 && arg0.getKeyChar() <= 126) {
    		text = text + arg0.getKeyChar();
    	}
    	if(arg0.getKeyChar() == 127) {
    		if(text.length() != 0) {
    			text = text.substring(0, text.length()-1);
    		}
      }
    }
    
  };
  
  /**
   * Konstruktor, Initialisiert alle Attribute
   * @param x Die X-Position der oberen linken Ecke
   * @param y Die Y-Position der oberen linken Ecke
   * @param width Die Breite des Textfields
   * @param height Die H�he des Textfields
   */
  LoginTextfield(int x, int y, int width, int height){
    p = new JPanel() {
      public void paint(Graphics g) {
        setBounds(x, y, width, height);
        if(img == null) {
          try {
            img = ImageIO.read(new File("Assets/GUI/Login_Eingabefeld.png"));
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        g.drawImage(img, 0, 0, width, height, null);
        g.setColor(Color.DARK_GRAY);
        Font font = new Font("TimesRoman", Font.BOLD, 50);
        g.setFont(font);
        g.drawString(text, 50, height/2 + 50);
        g.fillRect(50+25*text.length(), height/2, 10, 50);
      }
    };
    text = "";
    RenderManager.getFrame().addKeyListener(kl);
    
  }
  
  /**
   * Gibt den bisher geschrieben Text zur�ck
   * @return Text der bisher im Textfield geschrieben wurde
   */
  public String getText(){
    return text;
  }
  
  /**
   * Gibt das Panel, auf welchem gezeichnet wird, zur�ck
   * @return Panel, auf welchem gezeichnet wird
   */
  public JPanel getPanel() {
    return p;
  }
}
