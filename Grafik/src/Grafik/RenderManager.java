package Grafik;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Hauptklasse der Grafik, managed das Fenster und das Zeichnen auf diesem
 * @author Fabian Scherer
 *
 */
public class RenderManager{       // Hauptklasse in der Grafik, managed wann was gezeichnet wird
  private static State state;         // In welchem State befindet sich das Programm? z.B HauptmenÃ¼, Charakterauswahl, etc.
  private static JFrame f;            // Fenster auf dem alles dargestellt wird
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
  
  /**
   * Main-Methode der Grafik
   * Initialisiert das Fenster und sämtliche anderen Attr
   * @param args
   */
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
  
  /**
   * Aktualisiert den Programmstatus
   * @param s State in dem sich das Programm befindet
   */
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
		try {
			Game.initGame("Boy", "Girl", "Boss");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
  
  /**
   * Zeichnet das Bild neu
   */
  public static void render() {
    p.repaint();
    
  }
  
  /**
   * Gibt die Fensterbreite zurück
   * @return Fensterbreite
   */
  public static int getFWidth(){
    return f.getWidth();
  }
  
  /**
   * Gibt die Fensterhöhe zurück
   * @return Fensterhöhe
   */
  public static int getFHeight(){
    return f.getHeight();
  }
  
  /**
   * Setter für running
   * @param b Läuft das Fenster noch?
   */
  public static void setRunning(boolean b) {
    running = b;
  }
  
  /**
   * Gibt das Fenster zurück
   * @return Fenster
   */
  public static JFrame getFrame() {
	  return f;
  }
}
