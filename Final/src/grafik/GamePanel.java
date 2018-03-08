package grafik;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    public static final String IMAGE_DIR = "Assets/";

    private final Dimension prefSize = new Dimension(1920, 1080);
    private final String backgroundImagePath = "Map/MapColor.png";
    private ImageIcon backgroundImage;
    private boolean gameOver = false;
    private Timer t;
    private Player[] players;
    private ArrayList<Projectile> projectiles;


    public GamePanel(Player[] players, ArrayList<Projectile> projectiles){
        this.setFocusable(true);
        setPreferredSize(prefSize);
        this.players = players;
        this.projectiles = projectiles;
        initGame();
        //startGame();
    }



    public boolean isGameOver() {
        return gameOver;
    }

    public GamePanel setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        return this;
    }

    private void initGame() {
        this.setBackgroundImage();
        this.creatGameObjects();

        t = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doOnTick();
            }
        });
    }

    private void creatGameObjects() {
        //Spielobjekte werden hier drinne erzeugt
    }

    private void initPlayerSprite() {
        //Initialisierung der Playerbilder des Spielers
    }

    private void setBackgroundImage() {
        String imagePath = IMAGE_DIR + backgroundImagePath;
        System.out.println(imagePath);
        Image image = null;
        try {
            image = ImageIO.read(new File( imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        backgroundImage = new ImageIcon(image);
    }

    public void remove(Player player){
        player.setRendered(false);
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

        backgroundImage.paintIcon(null, g, 0, 0);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 19));
        g.setColor(Color.BLUE);
        g.drawString("Spieler alive: ", 22, prefSize.height - 5);

        if (isGameOver()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", prefSize.width / 2 - 130, prefSize.height / 5);
        }
    }

}