package bessereGrafik;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel{
    public static final String IMAGE_DIR = "Assets/";

    private final Dimension prefSize = new Dimension(1910, 1070);

    private ImageIcon backgroundImage;
    private final String backgroundImagePath = "Map/MapColor.png";

    private boolean gameOver = false;
    
    private Timer t;

    public GamePanel(){
        this.setFocusable(true);
        setPreferredSize(prefSize);
        
        initGame();
        startGame();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public GamePanel setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }
    
    private void initGame(){
        this.setBackgroundImage();
        this.creatGameObjects();

        t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();
            }
        });
    }
    private void creatGameObjects(){
        //Spielobjekte werden hier drinne erzeugt
    }
    private void initPlayerSprite(){
        //Initialisierung der Playerbilder des Spielers
    }

    private void setBackgroundImage() {
        String imagePath = IMAGE_DIR + backgroundImagePath;
        URL imageURL = getClass().getResource(imagePath);
        backgroundImage = new ImageIcon(imageURL);
    }
    private void startGame() {
        t.start();
    }
    public void pauseGame() {
        t.stop();
    }
    public void continueGame() {
        if(!isGameOver()) t.start();
    }

    // wird nicht benötigt
    public void restartGame() {
        setGameOver(false);
        creatGameObjects();
        startGame();
    }
    public void endGame(){
        setGameOver(true);
        pauseGame();
    }
    private void doOnTick() {
        //TODO jetzt wird Tankdestroyed gentutztum zuerfahren wann das spiel endet sollte bei uns eine meldung der IOKontrole sein?
        //endGame()

        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backgroundImage.paintIcon(null, g, 0,0);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        g.setColor(Color.BLUE);
        g.drawString("Spieler alive: " ,22, prefSize.height-5);

        if(isGameOver()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", prefSize.width/2 - 130, prefSize.height/5);
        }
    }

}
