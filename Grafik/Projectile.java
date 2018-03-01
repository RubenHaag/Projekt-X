package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Projectile extends JComponent{
	private int x;
	private int y;
	private String dateiname;
	private BufferedImage pi = null;
	
	Projectile(int x, int y, String dat){
		dateiname = dat;
		this.x = x;
		this.y = y;
	}
	
	public void updatePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	 public int getX() {
		 return x;
	 }
	 
	 public int getY() {
		 return y;
	 }
	
	public void paint(Graphics g) {
		this.setBounds(x, y, 50, 50);
  		if(pi == null){
  			try {
  				pi = ImageIO.read(new File("Assets/GUI/" + dateiname + ".png"));  
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		}
		g.drawImage(pi, 0, 0, 50, 50, null);
  		
	}
	
}
