package grafik;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import IOServer.GameManager;
import IOServer.InputListener;


/**
 * Hauptklasse der grafik, managed das Fenster und das Zeichnen auf diesem
 * @author Fabian Scherer
 *
 */
public class RenderManager {       // Hauptklasse in der grafik, managed wann was gezeichnet wird
    private static State state;         // In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
    private static JFrame f;            // Fenster auf dem alles dargestellt wird
    private static JPanel p;
    private static Menue menue;
    private static Game game;
    private static SettingsMenue settings;
    private static SoundManager music;
    private static LoginMenue login;
    private static boolean running;
    private static GameManager g = new GameManager();
    private static WindowAdapter wa = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            RenderManager.setRunning(false);
        }
    };

    /**
     * Main-Methode der grafik
     * Initialisiert das Fenster und s�mtliche anderen Attribute
     * @param args
     */
    public static void main(String[] args) {
        f = new JFrame("Project-X");
        f.setSize(1920, 1080);
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
        music = new SoundManager();
        music.start();
        while(running) {
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
                InputListener listener = new InputListener(g);

                p.add(game.getPanel());
                try {
                    Game.initGame("Boy", "Girl", "Boy");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.validate();
                g.spielstart();
                f.addKeyListener(listener);
                f.addMouseListener(listener);
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
     * Gibt die Fensterbreite zur�ck
     * @return Fensterbreite
     */
    public static int getFWidth(){
        return f.getWidth();
    }

    /**
     * Gibt die Fensterh�he zur�ck
     * @return Fensterh�he
     */
    public static int getFHeight(){
        return f.getHeight();
    }

    /**
     * Setter f�r running
     * @param b L�uft das Fenster noch?
     */
    public static void setRunning(boolean b) {
        running = b;
    }

    /**
     * Gibt das Fenster zur�ck
     * @return Fenster
     */
    public static JFrame getFrame() {
        return f;
    }

    public static GameManager getGameManager() {
        return g;
    }
}
