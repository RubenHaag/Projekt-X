package Grafik;

import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

class CharakterButton{
	private boolean ausgewaehlt;				// ist dieser Charakter ausgew�hlt
	private String charaktername;			// Name des Charakters
	private String fertigkeit1;				// Fertigkeitstext der ersten Spezialattacke
	private String fertigkeit2;				// Fertigkeitstext der zweiten Spezialattacke
	private Image charakterBild;			// Vorschaubild des Charakters
	private Image fertigkeit1Bild;			// Vorschaubild der ersten Spezialattacke
	private Image fertigkeit2Bild;			// Vorschaubild der zweiten Spezialattacke
	private JPanel panel;					// Panel auf welchem gezeichnet wird
	private ActionListener listener;		// Listener f�r die Maus; Wurde dieses Panel angeklickt
	
	
	// zeichnet den CharakterButton
	public void drawCharakterButton(Graphics g, int x, int y, int width, int height){
		
	}

}

