package Grafik;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

class MenueButton{
  private MouseListener listener;		// Listener für diesen Button
  private ButtonPanel panel; 			// Panel auf welchem gezeichnet wird
  private String text;					// Buttontext
  private String image;					// Bild des Buttons
  int x;
  int y;
  int width;
  int height;
  
  public MenueButton(String name, MouseListener l, int x, int y, int width, int height) {
	  panel = new ButtonPanel();
	  text = name;
	  image = "file";
	  panel.addMouseListener(l);
	  this.x = x;
	  this.y = y;
	  this.width = width;
	  this.height = height;
  }
  
  public JPanel getPanel() {
	  return panel;
  }
  
  
  @SuppressWarnings("serial")
  class ButtonPanel extends JPanel{
  	public ButtonPanel() {
  		super();
  	}
  	
  	@Override
  	public void paint(Graphics g) {
  		this.setBounds(x, y, width, height);
  		g.fillRect(0, 0, width, height);
  	}
  }
}
