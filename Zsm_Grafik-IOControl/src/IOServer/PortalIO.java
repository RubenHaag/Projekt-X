package ioserver;
/**
 * 
 * @author Lukas Hofmann
 *
 */
public class PortalIO {
	private Rectangle portal1;
	private Rectangle portal2;

	public PortalIO(Rectangle portal1, Rectangle portal2) {
		this.portal1 = portal1;
		this.portal2 = portal2;
	}

	public Rectangle[] getRectangles() {
		Rectangle[] ret = new Rectangle[2];
		return ret;
	}

	//Wird bei noch freier Zeit implementiert
}
