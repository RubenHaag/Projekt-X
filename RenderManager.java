package Grafik;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class RenderManager{				// Hauptklasse in der Grafik, managed wann was gezeichnet wird
	private State state;					// In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
	private JFrame f;						// Fenster auf dem alles dargestellt wird
	private DrawingPanel p;					// eigene Panelklasse in der alles gezeichnet wird
	
	
	public static void main(String[] args) {
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel{
		@Override
		public void paintComponents(Graphics g) {
			
		}
	}
}

