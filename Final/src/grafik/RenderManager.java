package grafik;

import ioserver.*;
import server.Client;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import ioserver.GameManager;
//import ioserver.InputListenerIO;


/**
 * Hauptklasse der grafik, managed das Fenster und das Zeichnen auf diesem
 * @author Fabian Scherer
 *
 */
public class RenderManager {       // Hauptklasse in der grafik, managed wann was gezeichnet wird
    private static State state;         // In welchem State befindet sich das Programm? z.B Hauptmenü, Charakterauswahl, etc.
    private static GameWindow frame;            // Fenster auf dem alles dargestellt wird
    private static JPanel p;
    private static Menue menue;
    private static Game game;
    private static SettingsMenue settings;
    private static LoginMenue login;
    private static boolean running;
    private static GameManager gameManager;
    private static WindowAdapter wa = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            RenderManager.setRunning(false);
        }
    };
    private static Client client;
    private static ServerVerwaltung server;
    private static cLoginUpdate cLoginUpdate;

   
    /**
     * Main-Methode der grafik
     * Initialisiert das Fenster und s�mtliche anderen Attribute
     * @param args Die Übergebenen Argumente
     */
    public static void main(String[] args) {
        frame = new GameWindow();
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(wa);

        state = State.HAUPTMENUE;
        p = new JPanel();
        p.setLayout(new BorderLayout());

        menue = new Menue();
        settings = new SettingsMenue();
        login = new LoginMenue();

        frame.add(p);
        frame.setVisible(true);
        p.setVisible(true);
        menue.getPanel().setVisible(true);
        p.add(menue.getPanel());
        running = true;        
        SoundManager.playSound("Assets/Sound/Menu Music.wav");

        try {
            game = new Game("Boy", "Girl", "Boss");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(running) {
            render();
        }
        System.exit(0);
    }

    private static void initGame(){
        String ip = login.getLtxtf().getText();
        server = new ServerVerwaltung();
        gameManager = new GameManager(server);

        client = new Client(ip, gameManager);
        gameManager.spielstart();
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

                initGame();
                InputListenerIO listener = new InputListenerIO(gameManager);
                p.add(game.getPanel());
                p.validate();
                frame.addKeyListener(listener);
                frame.addMouseListener(listener);
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
        return frame.getWidth();
    }

    /**
     * Gibt die Fensterh�he zur�ck
     * @return Fensterh�he
     */
    public static int getFHeight(){
        return frame.getHeight();
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
        return frame;
    }

    //public static GameManager getGameManager() {
        //return g;
    //}

    public static State getState() {
        return state;
    }

    public static JPanel getP() {
        return p;
    }

    public static Menue getMenue() {
        return menue;
    }

    public static Game getGame() {
        return game;
    }

    public static SettingsMenue getSettings() {
        return settings;
    }

    public static LoginMenue getLogin() {
        return login;
    }

    public static boolean isRunning() {
        return running;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static WindowAdapter getWa() {
        return wa;
    }

    public static Client getClient() {
        return client;
    }

    public static ServerVerwaltung getServer() {
        return server;
    }

    public static ioserver.cLoginUpdate getcLoginUpdate() {
        return cLoginUpdate;
    }
}
