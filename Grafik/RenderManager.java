package Grafik;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

class RenderManager{				// Hauptklasse in der Grafik, managed wann was gezeichnet wird
	private static State state;					// In welchem State befindet sich das Programm? z.B HauptmenÃ¼, Charakterauswahl, etc.
	private static JFrame f;						// Fenster auf dem alles dargestellt wird
	
	private static Menue menue;
	
	public static void main(String[] args) {
		f = new JFrame("Project-X");
		f.setSize(1024, 760);
		f.setVisible(true);
		f.setLayout(new BorderLayout());
		state = State.HAUPTMENUE;
		menue = new Menue();
		f.add(menue.getPanel());
		while(true){
			render();
		}
	}
	
	public static void changeState(State s){
		state = s;
	}
	
	public static void render() {
		switch(state) {
		case HAUPTMENUE: 
			menue.render();
			break;
		default:
			break;
		}
	}
	
	public static int getFWidth(){
		return f.getWidth();
	}
	
	public static int getFHeight(){
		return f.getHeight();
	}
}
