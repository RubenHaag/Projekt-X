package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlayerAnzeige{
	private Lifebar hp;
	private Manabar mp;
	private BufferedImage img = null;
	private AnzeigePanel p;
	
	PlayerAnzeige(int hp, int mp){
		p = new AnzeigePanel();
		this.hp = new Lifebar(hp);
		this.mp = new Manabar(mp);
		p.add(this.hp);
		p.add(this.mp.getPanel());
	}
	
	public JPanel getPanel() {
		return p;
	}
	
	
	class AnzeigePanel extends JPanel{
		AnzeigePanel(){
			super();
		}
		
		public void paint(Graphics g) {
			this.setBounds(10, 10, 300, 100);
			try {
					img = ImageIO.read(new File("Assets/GUI/Lifebar_v2_empty.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			g.drawImage(img, 0, 0, 300, 100, null);
			this.paintComponents(g);
		}
	}
}
