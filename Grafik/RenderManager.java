package Grafik;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderManager{				// Hauptklasse in der Grafik, managed wann was gezeichnet wird
	private static State state;					// In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
	private static JFrame f;						// Fenster auf dem alles dargestellt wird
	private static JPanel p;
	private static Menue menue;
	private static Game game;
	private static SettingsMenue settings;
	private static LoginMenue login;
	private static boolean running;
	private static WindowAdapter wa = new WindowAdapter() {
		public void windowClosing(WindowEvent e){
	         RenderManager.setRunning(false);
	    }
	};
	
	public static void main(String[] args) {
		f = new JFrame("Project-X");
		f.setSize(1024, 760);
		f.setVisible(true);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.addWindowListener(wa);
		
		state = State.HAUPTMENUE;
		p = new JPanel();
		p.setLayout(new BorderLayout());
		menue = new Menue();
		game = new Game();
		settings = new SettingsMenue();
		login = new LoginMenue();
		
		f.add(p);
		p.add(menue.getPanel());
		running = true;
		SoundManager.playSound("Assets/Sound/Menu Music.wav");
		while(running){
			render();
		}
		System.exit(0);
	}
	
	public static void changeState(State s){
		switch(state) {
		case HAUPTMENUE:
			p.remove(menue.getPanel());
			break;
		case SETTINGS:
			p.remove(settings.getPanel());
			break;
		case GAME:
			p.remove(game.getPanel());
			break;
		case LOGIN:
			p.remove(login.getPanel());
			break;
		default:
			break;
		}
		switch(s) {
		case GAME:
			p.add(game.getPanel());
			p.validate();
			break;
		case SETTINGS:
			p.add(settings.getPanel());
			p.validate();
			break;
		case HAUPTMENUE:
			p.add(menue.getPanel());
			p.validate();
			break;
		case LOGIN:
			p.add(login.getPanel());
			p.validate();
			break;
		default:
			break;	
		}
		state = s;
	}
	
	public static void render() {
		p.repaint();
		
	}
	
	public static int getFWidth(){
		return f.getWidth();
	}
	
	public static int getFHeight(){
		return f.getHeight();
	}
	
	public static void setRunning(boolean b) {
		running = b;
	}
}
