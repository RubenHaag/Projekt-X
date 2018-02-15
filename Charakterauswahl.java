package Grafik;

import java.awt.Graphics;

import javax.swing.JPanel;

abstract class Charakterauswahl{
  protected MenueButton auswahl;					// Button um die Auswahl zu best�tigen
  protected MenueButton bereit;						// Bereit-Button 
  protected CharakterList charakterList;			// Liste der Charaktere
  protected CharakterVorschau charakterVorschau;	// Zeigt eine Vorschau des aktuell ausgew�hlten Charakters
  protected Image BackgroundImage;					// Hintergrund des Men�s
  protected JPanel panel;							// Panel auf welchem das Men� gezeichnet wird
  
  public abstract void drawCharakterauswahl(Graphics g);


  /** Gibt den ausgew�hlten Charakter als String zur�ck
   *
   * @return vom Spieler augewählter Charakter
   */
  public abstract String getCharakter();


  /**Gibt zur�ck ob der Spieler bereit ist
   *
    */
  public boolean getReady() {
	  return false;
  }

}

