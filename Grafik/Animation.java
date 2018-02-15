package Grafik;

import java.awt.Graphics;

/**
 * Repräsentiert eine Animation
 * @author Fabian Scherer
 */
class Animation{			 		// evtl. auch als Thread
	private int maxAnimStates;					// wieviele Einzelbilder hat die Animation
	private int aktAnimState;					// aktuelles Bild
	private String name;						// Dateiname der Animation
	
	// Initialisiert die Animation
	Animation(String datei, int max){
		name = datei;
		maxAnimStates = max;
		aktAnimState = 0;
	}
	
	// setzt die Animation zur�ck
	public void resetAnimation() {
		
	}
	
	// zeichnet die aktuelle animationsphase an der �bergebenen Position
	public static void playAnimation(Graphics g, int x, int y, int width, int height){
    
	}

}

