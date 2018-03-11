/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 27.01.2018
 * @author Ruben Haag
 */

public class DateienLesen_Test {
  
  
  // Anfang Attribute
  // Ende Attribute
  
  // Anfang Methoden
  public static void main(String[] args){
    String[][] lines = DateienReadWrite.read("Settings.txt");
    for (int i = 0; i<lines[0].length; i++) {
      System.out.println(lines[0][i] + "\n" + lines[1][i]);
    } // end of for
    lines[0][0] = "0";
    DateienReadWrite.write("Settings.txt", lines);
  } 
  // Ende Methoden
} // end of DateienLesen_Test

