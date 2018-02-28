package Grafik;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SettingsMenue {
	private MenueButton music;
	private MenueButton sound;
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
				// Ändere Einstellung zu Sound Off
			}
			else {
				music.changeText("Music_On");
				// Ändere Einstellung zu Sound On
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
				// Ändere Einstellung zu Sound Off
			}
			else {
				sound.changeText("Sound_On");
				// Ändere Einstellung zu Sound On
			}
		}
		
	};
	
	SettingsMenue(){
		music = new MenueButton("Music_On", musicl, 100, 100, 200, 80);
		sound = new MenueButton("Sound_On", soundl, 100, 200, 200, 80);
		p = new SettingsPanel();
		p.add(music.getPanel());
		p.add(sound.getPanel());
		
	}
	
	class SettingsPanel extends JPanel{
		SettingsPanel(){
			super();
		}
		
		public void paint(Graphics g) {
			this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
	  		try {
  				bgi = ImageIO.read(new File("Assets/GUI/Settings_Screen_v1.png"));
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			g.drawImage(bgi, 0, 0, RenderManager.getFWidth(), RenderManager.getFHeight(), null);
	  		this.paintComponents(g);
		}
	}
}
