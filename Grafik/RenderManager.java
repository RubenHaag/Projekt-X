package Grafik;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

class RenderManager{				// Hauptklasse in der Grafik, managed wann was gezeichnet wird
	private State state;					// In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
	private static JFrame f;				// Fenster auf dem alles dargestellt wird
	private static DrawingPanel p;				// eigene Panelklasse in der alles gezeichnet wird
	
	
	public static void main(String[] args) {
		f = new JFrame("Project-X");
		f.setSize(1024, 760);
		f.setVisible(true);
		f.setLayout(new BorderLayout());
		p = new DrawingPanel();
		f.add(p, BorderLayout.CENTER);
	}
	
}

@SuppressWarnings("serial") 
class DrawingPanel extends JPanel{
	
	public DrawingPanel() {
		super();
	}
	
	@Override
	public void paint(Graphics g) {
		g.fillRect(40, 50, 30, 90);
	}
}
