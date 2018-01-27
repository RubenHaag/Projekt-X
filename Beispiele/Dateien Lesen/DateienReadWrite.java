import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
/** Diese Klasse kann Textdateien, im entsprechenen Format, auslesen und aendern. ACHTUNG Funktioniert nur mit Java 7 und hoeheren Versionen!
 * @author Ruben Haag
 * @version 1.0.0
 * @since 1.0
*/


public class DateienReadWrite{

  /** Liest einen Text in einen Array ein und gibt diesen zurueck. Dabei werden jedoch nur die Werte, die hinter dem Trennzeichen ':' stehen beachtet.
  * @return Ein Zweidimensionaler String Array mit zwei Zeilen die erste entspricht dem, was vor dem Trennzeichen steht, und die zweite dem, was nach dem Trennzeichen steht.
  * @param file Die Textdatei, die eingelesen werden soll.
  */
  public static String[][] read(String file){
    BufferedReader br;
    ArrayList<String> lines = new ArrayList<>();
    try {
      br = new BufferedReader(new FileReader(file));
      
      String line;
      while((line=br.readLine()) != null){
        lines.add(line);
      }
      br.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    String[][] ret = new String[2][];
    ret[0] = lines.toArray(new String[lines.size()]);
    ret[1] = new String[ret[0].length];
    for(int i =0; i<ret[1].length; i++){
      String x = ret[0][i].split(":", 2)[1];
      String y = ret[0][i].split(":", 2)[0];
      System.out.println(x + "\n" +y);
      ret[0][i] = y;
      ret[1][i] = x;
    }
    return ret;
  }
  /** Schreibt die im Array in der ersten Zeile angegeben Werte vor und die in der zweiten Zeile hinter das Trennzeichen ':' in die Gegebene Datei.
   *@param file Die Textdatei, die eingelesen werden soll.
   *@param lines Ein String Array mit den Werten, die in die jeweilige Zeile eingegeben werden sollen.
  */
  public static void write(String file, String[][] lines){
    BufferedWriter wr;
    String line;
    String n = System.getProperty("line.separator");
    try{
      wr = new BufferedWriter(new FileWriter(file));
      wr.flush();
      for(int i = 0; i < lines[0].length; i++){
        line = lines[0][i] + ":" + lines[1][i] + n;
        wr.write(line, 0, line.length());
      }
      wr.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
  
}
