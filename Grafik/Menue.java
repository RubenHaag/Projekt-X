package Grafik;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Menue {
	private MenueButton play;
	//private MenueButton settings;
	private MenueButton quit;
	private BufferedImage bgi = null;
	private MouseListener pl = new MouseListener(){

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
			// TODO Auto-generated method stub
			play.changeClicked(true);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			play.changeClicked(false);
			//LoginToServer()
			//ChangeState(Heldenauswahl oder Bossauswahl)
			System.out.println("Lass mich spielen!");
		}
		
	};

	private MouseListener ql = new MouseListener(){

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
			// TODO Auto-generated method stub
			quit.changeClicked(true);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			quit.changeClicked(false);
			System.out.println("Geh weg!");
			System.exit(0);
		}
		
	};

	private MenuePanel p;
	
	public Menue(){
		p = new MenuePanel();
		play = new MenueButton("Play", pl, 180, 80, 200, 80);
		quit = new MenueButton("Quit", ql, 180, 200, 200, 80);
		//p.setLayout(new BorderLayout());
		p.add(play.getPanel());
		p.add(quit.getPanel());
	}
	
	public JPanel getPanel(){
		return p;
	}
	
	public void render(){
		p.repaint();
	}
	
	
	@SuppressWarnings("serial")
	  class MenuePanel extends JPanel{
	  	public MenuePanel() {
	  		super();
	  	}
	  	
	  	@Override
	  	public void paint(Graphics g) {
	  		this.setBounds(0, 0, RenderManager.getFWidth(), RenderManager.getFHeight());
	  		this.paintComponents(g);
	  		this.setBackground(Color.green);
	  	}
	  }
}

