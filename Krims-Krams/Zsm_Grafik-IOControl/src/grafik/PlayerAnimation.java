package grafik;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Thread f�r die Spieleranimationen
 *
 * @author Fabian Scherer
 */
public class PlayerAnimation extends Thread {
    private BufferedImage[][] animations;
    private BufferedImage takeDamage;
    private BufferedImage idle;
    private BufferedImage aktImage;
    private String path;
    private String name;
    private boolean jumping;
    private short animZaehler;
    private short imageZaehler;

    /**
     * Konstruktor
     *
     * @param playerName Name des Spielers f�r welchen die Animationen sind
     */
    PlayerAnimation(String playerName, String imageDir) throws IOException {
        name = playerName;
        animZaehler = 0;
        imageZaehler = 1;
        animations = new BufferedImage[6][5];
        path = imageDir + "Charaktere/";
    }

    /**
     * Initialisiert die Animationen.
     * L�dt alle Bilder in den Speicher
     *
     * @throws IOException
     */


    public void initPlayerAnimations() throws IOException {
        for (int i = 1; i < 6; i++) {
            System.out.println(path + name + "/" + name + "_laufen(" + i + ").png");
            animations[0][i - 1] = ImageIO.read(new File(path + name + "/" + name + "_laufen(" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println(path + name + "/" + name + "_jump(" + i + ").png");
            animations[1][i - 1] = ImageIO.read(new File(path + name + "/" + name + "_jump (" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            animations[2][i - 1] = ImageIO.read(new File(path + name + "/" + name + "_attack(" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            animations[3][i - 1] = animations[2][i - 1];                            //ImageIO.read(new File("grafik.Assets/Charaktere/" + name + "/" + name + "_attack(" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            animations[4][i - 1] = animations[2][i - 1];                            //ImageIO.read(new File("grafik.Assets/Charaktere/" + name + "/" + name + "_attack(" + i + ").png"));
        }
        for (int i = 1; i <= 5; i++) {
            animations[5][i - 1] = ImageIO.read(new File(path + name + "/" + name + "_dead (" + i + ").png"));
        }
        takeDamage = animations[5][0];

        idle = animations[0][0];
        System.out.println(path + name + "/" + name + "_laufen.png");
    }

    /**
     * Setz die Animation
     * 0 = Move
     * 1 = Jump
     * 2 = Attack
     * 3 = SpecialAtt 1
     * 4 = SpecialAtt 2
     * 5 = Dead
     *
     * @param i Art der Animation
     */
    public void setAnimation(int i) {
        animZaehler = (short) i;
        if (animZaehler == 1) {
            jumping = true;
        } else {
            jumping = false;
        }
    }

    /**
     * Getter f�r das aktuelle Bild der Animation
     *
     * @return aktuelles Bild der Animation
     */
    public BufferedImage getImage() {
        return aktImage;
    }

    /**
     * Run-Methode
     * Geht in jedem Durchlauf ein Bild in der Animation weiter
     */
    public void run() {
        try {
            this.initPlayerAnimations();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!isInterrupted()) {
            if(animZaehler == 6){
                aktImage = idle;
            }
            else if(animZaehler == 7){
                aktImage = takeDamage;
            }
            else if(animZaehler<6) {
                aktImage = animations[animZaehler][imageZaehler];
            }
            else{
                System.out.println("irgendwas ist schief gelaufen\n \b ich hasse es");
            }
            if (jumping) {
                if (imageZaehler < 3) {
                    imageZaehler++;
                }
                if (imageZaehler >= 3) {
                    imageZaehler = 1;
                }
            } else {
                imageZaehler++;
                if (imageZaehler >= 5) {
                    imageZaehler = 1;
                }
            }

            try {
                sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
