package Grafik;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
			text = text + arg0.getKeyChar();
		}
		
	};
	
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
				g.drawString(text, 5, 20);
			}
		};
		text = "";
		p.addKeyListener(kl);
	}
	
	
	public String getText(){
		return text;
	}
	
	public JPanel getPanel() {
		return p;
	}
}
