package bessereGrafik;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Projectile extends GameObject{
    private BufferedImage img;

    public Projectile(Position pos, int width, int height, boolean isLookingRight, String path) throws IOException {
        super(pos, width, height, isLookingRight);
        img = ImageIO.read(new File(path)); //TODO Das sollte nur am anfang einmal gelagen werden!!!!!!!!!!
    }


    @Override
    public void selfPaint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img, );
    }
}
