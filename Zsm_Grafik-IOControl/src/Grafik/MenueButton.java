package Grafik;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class MenueButton{
  private MouseListener listener;									// Listener für diesen Button
  private ButtonPanel panel; 										// Panel auf welchem gezeichnet wird
  private String text;												// Buttontext
  private BufferedImage image_cl = null;		// Bild des Buttons
  private BufferedImage image_ncl = null;
  private boolean clicked;
  int x;
  int y;
  int width;
  int height;
  
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
  
  public JPanel getPanel() {
	  return panel;
  }
  
  public void changeClicked(boolean clicked){
	  this.clicked = clicked;
  }
  
  public void changeText(String t) {
	  text = t;
  }
  
  public String getText() {
	  return text;
  }
  
  @SuppressWarnings("serial")
  class ButtonPanel extends JPanel{
  	public ButtonPanel() {
  		super();
  	}
  	
  	@Override
  	public void paint(Graphics g) {
  		this.setBounds(x, y, width, height);
  		if(!clicked){
  			try {
  				image_ncl = ImageIO.read(new File("Assets/GUI/" + text + "_non_click.png"));
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			g.drawImage(image_ncl, 0, 0, width, height, null);
  		}
  		else{
  			 try {
  				image_cl = ImageIO.read(new File("Assets/GUI/" + text + "_click.png"));
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			g.drawImage(image_cl, 0, 0, width, height, null);
  		}
  		
  	}
  }
}
