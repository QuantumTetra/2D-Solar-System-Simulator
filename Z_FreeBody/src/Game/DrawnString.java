package Game;
import java.awt.*;

/**
 * This class combines a String with a position and a Font so that
 * it can be drawn in a Picture.
 */
public class DrawnString {
	private String s;
	private double x;
	private double y;
	private Font font;
	
	public DrawnString(String s, double x, double y, Font font) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.font = font;
	}
	
	public String getString() {
		return this.s;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Font getFont() {
		return this.font;
	}
}

