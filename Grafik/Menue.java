package Grafik;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Menue {
	private MenueButton play;
	//private MenueButton settings;
	private MenueButton quit;
	private String bgi;
	private MouseListener pl = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			// beginPlay()
			System.out.println("Lass mich spielen!");
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
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

	private MouseListener ql = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			// quit()
			System.out.println("Geh weg!");
			System.exit(0);
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
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

	private MenuePanel p;
	
	public Menue(){
		p = new MenuePanel();
		play = new MenueButton("Play", pl, 80, 80, 80, 30);
		quit = new MenueButton("Quit", ql, 80, 120, 80, 30);
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
	  		this.setBackground(Color.cyan);
	  		this.paintComponents(g);
	  	}
	  }
}
