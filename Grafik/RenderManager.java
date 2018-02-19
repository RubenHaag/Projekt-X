package Grafik;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

class RenderManager{				// Hauptklasse in der Grafik, managed wann was gezeichnet wird
	private State state;					// In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
	private static JFrame f;						// Fenster auf dem alles dargestellt wird
	private static MenueButton b;
	private static MouseListener ml = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Hi, ich bin ein Knopf.");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
	public static void main(String[] args) {
		f = new JFrame("Project-X");
		f.setSize(1024, 760);
		f.setVisible(true);
		f.setLayout(new BorderLayout());
		b = new MenueButton("Test", ml, 50, 60, 70, 80);
		f.add(b.getPanel(), BorderLayout.CENTER);
	}
	
	public void render() {
		switch(state) {
		case HAUPTMENUE: 
			break;
		default:
			break;
		}
	}
}
