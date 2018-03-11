import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * @author Ruben Haag
 * Diese Klasse enthält Methoden um aus einem Bild Rechtecke(Plattformen) und Portale auszulesen.
 * Rechtecke werden durch ihre Rechteckige Form und ihre schwarze Farbe erkannt. Wärend Portale durch die Farbe Rot(255,0,0)
 * erkannt werden.
 */
class MapBuilder {
    // Anfang Methoden


    /**
     * Diese Methode erkennt alle Rechtecke(Plattformen)in dem angegebenem Bild und verarbeitet diese zu Rectangle Objekten.
     * @param path Der Pfad der Bilddatei, die ausgelesen werden soll.
     * @return Diese Methode gibt eine Liste aller schwarzen Rechtecke, die in dem eingelesenen Bild zu findensind, in Form einer Array List aus Rectangle Objekten, zurück.
     * @throws IOException Falls das mit dem Pfad Beschriebene Bild nicht existiert.
     */
  public static ArrayList<Rectangle> BildZuRechteck(String path) throws IOException {
    BufferedImage bim;
    bim = ImageIO.read(new File(path));
    
    
    boolean[][] schwarz = bildZuFarbe(bim, -16777216);
    
    
    //Erkennung der Rechtecke
    int laenge = 0;
    int hoehe = 0;
    int posX;
    int posY;
    
    //Array List mit allen Rechtecken
    ArrayList<Rectangle> rechtecke = new ArrayList<Rectangle>();
    
    for(int x = 0; x < schwarz.length; x++){
      for(int y = 0; y < schwarz[0].length; y++){
        if (schwarz[x][y]){
          posX = x;
          posY = y;
          laenge = 0;
          hoehe = 0;
          while(x < schwarz.length&&schwarz[x][y]){
            laenge++;
            x++;
          }
          x--;
          while(y < schwarz[0].length&&schwarz[x][y]){
            hoehe++;
            y++;
          }
          y--;
          //Rechteck im Array auf False Setzen
          for(int l = posX-1; l < (posX+laenge); l++){
            for(int h = posY-1; h < (posY + hoehe); h++){
              schwarz[l][h] = false;
            }
          }
          Position pos = new Position((double)posX, (double) posY);
          rechtecke.add(new Rectangle(pos, laenge, hoehe));
        }
      }
    }
    return rechtecke;
  }

    /**
     *
     * @param bim
     * @param farbe
     * @return
     */
  private static boolean[][] bildZuFarbe(BufferedImage bim, int farbe){
    boolean[][] istFarbe = new boolean[bim.getWidth()][bim.getHeight()];
    //Bild zu schwarz und nicht Schwarz
    for(int x = 0; x < bim.getWidth(); x++) {
      for(int y = 0; y < bim.getHeight(); y++){
        
        if(bim.getRGB(x , y) == farbe){
          istFarbe[x][y] = true;
        }
        else{
          istFarbe[x][y] = false;
          
        }
      }
    }
    return istFarbe;
  }
    /**
     * Diese Methode gibt ein Portal, welches aus dem Bild erkannt wird zurück.
     * @param path Der Pfad des Bildes
     * @return Ein Portal
     * @throws IOException Wenn das angegebene Bild nicht existiert
     */
  public static Portal BildZuPortal(String path)throws IOException{
    BufferedImage bim;
    bim = ImageIO.read(new File(path));
    boolean[][] rot = bildZuFarbe(bim, -131072);
    //Erkennung der Rechtecke
    int laenge = 0;
    int hoehe = 0;
    int posX;
    int posY;
    
    //Array List mit allen Rechtecken
    ArrayList<Rectangle> rechtecke = new ArrayList<Rectangle>();
    
    for(int x = 0; x < rot.length; x++){
      for(int y = 0; y < rot[0].length; y++){
        
        if (rot[x][y]){
          posX = x;
          posY = y;
          laenge = 0;
          hoehe = 0;
          while(x < rot.length){
            if(rot[x][y]){
              laenge++;
              x++;
            }else break;
          }
          x--;
          while(y < rot[x].length) {
            if(rot[x][y]){
              hoehe++;
              y++;
            }else break;
          }
          //Rechteck im Array auf False Setzen
          for(int l = posX-1; l < (posX+laenge); l++){
            for(int h = posY-1; h < (posY + hoehe); h++){
              
              rot[l][h] = false;
            }
          }
          Position pos = new Position((double)posX, (double) posY);
          rechtecke.add(new Rectangle(pos, laenge, hoehe));
        }
      }
    }
    if(rechtecke.isEmpty()){
      return null;
    }
    return new Portal(rechtecke.get(0), rechtecke.get(1));
  }
    // Ende Methoden
}
