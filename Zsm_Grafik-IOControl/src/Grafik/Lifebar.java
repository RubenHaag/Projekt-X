package Grafik;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Lifebar extends JComponent{
	private BufferedImage bar = null;
	private int percent;
	
	Lifebar(int p){
		if(p > 100) {
			p = 100;
		}
		else if(p < 0) {
			p = 0;
		}
		percent = p;
	}
	
	public void update(int p) {
		if(p > 100) {
			p = 100;
		}
		else if(p < 0) {
			p = 0;
		}
		percent = p;
	}
	
	public void paint(Graphics g) {
		this.setBounds(0, 0, 300, 100);
		try {
			bar = ImageIO.read(new File("Assets/GUI/Healthbar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			g.drawImage(bar, 0, 0, 3*percent, 100, null);
			System.out.println("Lifebar gezeichnet");
	}
}
