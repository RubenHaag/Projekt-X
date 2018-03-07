package bessereGrafik;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Projectile extends GameObject{
    private BufferedImage img;
    AffineTransform transform = new AffineTransform();

    public Projectile(Position pos, int width, int height, boolean isLookingRight, String path) throws IOException {
        super(pos, width, height, isLookingRight);
        img = ImageIO.read(new File(path)); //TODO Das sollte nur am anfang einmal gelagen werden!!!!!!!!!!
        transform = new AffineTransform();
    }


    @Override
    public void selfPaint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if(isLookingRight()) {
            g2d.drawImage(img, pos.getYPos(), pos.getYPos(), getWidth(), getHeight(), null);
        }
        else{
            g2d.drawImage(img, pos.getYPos(), pos.getYPos(), -getWidth(), getHeight(), null);

        }
    }
}
