package ioserver;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements KeyListener, MouseListener {
	private GameManager game;
	/**
	 * @param g Client/GameManager
	 */
	public InputListener(GameManager g){
		this.game = g;
	}
	
	
	@Override
	/**
	 * Gibrt an ob ein Key gedr�ckt wurde
	 */
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	/**
	 * Gibt an ob ein Key losgelassen wurde
	 */
	public void keyReleased(KeyEvent e) {
		
	}
	

	@Override
	/**
	 * Gibt an ob ein Key eingegeben wurde
	 */
	public void keyTyped(KeyEvent e) {
		game.inputKey(e);
	}

	@Override
	/**
	 * Gibt an ob die Mouse geclicked wurden
	 */
	public void mouseClicked(MouseEvent m) {
		game.inputMouse(m);
        System.out.println("MOUSE");
	}

	@Override
	/**
	 * 
	 */
	public void mouseEntered(MouseEvent m) {
		
	}

	@Override
	public void mouseExited(MouseEvent m) {
		
	}

	@Override
	/**
	 * Gibt an ob die mouse gedr�ckt wurde
	 */
	public void mousePressed(MouseEvent m) {
		
	}

	@Override
	/** 
	 * Gibt an ob die Mouse geclicked wurde
	 */
	public void mouseReleased(MouseEvent m) {
		
	}
}
