package Game;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 *  The <code>Figure</code> class allows the user to create shapes that contain an
 * arbitrary number of lines and arcs. Figures can be filled or unfilled, a variety
 * of colors, and there are ways to determine what color a given figure is and
 * whether or not it's filled. Coordinates begin at the top left corner with (0,0).
 * x-values increase to the right and y-values increase going downward.
 *
 *
 *
 *@author     David Dallmann
 *@created    October 28, 2008
 */
public class Figure {
	/**
	 *  White
	 */
	public final static Color WHITE = Color.white;
	/**
	 *  Light Gray
	 */
	public final static Color LIGHT_GRAY = Color.lightGray;
	/**
	 *  Gray
	 */
	public final static Color GRAY = Color.gray;
	/**
	 *  Dark Gray
	 */
	public final static Color DARK_GRAY = Color.darkGray;
	/**
	 *  Black
	 */
	public final static Color BLACK = Color.black;
	/**
	 *  Red
	 */
	public final static Color RED = Color.red;
	/**
	 *  Pink
	 */
	public final static Color PINK = Color.pink;
	/**
	 *  Orange
	 */
	public final static Color ORANGE = Color.orange;
	/**
	 *  Yellow
	 */
	public final static Color YELLOW = Color.yellow;
	/**
	 *  Green
	 */
	public final static Color GREEN = Color.green;
	/**
	 *  Magenta (kinda purple-y)
	 */
	public final static Color MAGENTA = Color.magenta;
	/**
	 *  Cyan (light blue)
	 */
	public final static Color CYAN = Color.cyan;
	/**
	 *  Blue
	 */
	public final static Color BLUE = Color.blue;

	private Color color;
	private BasicStroke width;
	private boolean filled;
	private GeneralPath path;
	private Font font;
	private double currentX, currentY;
	private double originX, originY;
	private Vector stringVector;


	/**
	 *  Creates a <code>Figure</code> object starting at coordinates (0, 0).
	 */
	public Figure() {
		this(0, 0);
	}


	/**
	 *  Creates a <code>Figure</code> object starting at coordinate (<code>originX</code>, <code>originY</code>
	 *
	 *@param  originX  the x-value where I should start this <code>Figure</code>
	 *@param  originY  the y-value where I should start this <code>Figure</code>
	 */
	public Figure(double originX, double originY) {
		this.originX = originX;
		this.originY = originY;
		this.currentX = originX;
		this.currentY = originY;
		this.filled = false;
		this.color = BLACK;
		this.path = new GeneralPath();
		this.width = new BasicStroke(1);
		this.path.moveTo((float) originX, (float) originY);
		this.font = new Font("Serif", Font.PLAIN, 12);
		this.stringVector = new Vector();
	}


	/**
	 *  Draws a line from the current position to a point <code>x</code> pixels to the right and <code>y</code>
	 * pixels down. (For left and up, you should use negative numbers.)
	 *
	 *@param  x  number of pixels to the right that the point the line will go to is
	 *@param  y  number of pixels down that the point the line will go to is
	 */
	public void draw(double x, double y) {
		this.currentX += x;
		this.currentY += y;
		this.path.lineTo((float) currentX, (float) currentY);
	}


	/**
	 *  Moves <code>x</code> pixels to the right and <code>y</code> pixels down without drawing a line.
	 * Also creates a new subpath for this <code>Figure</code> so that the next call to <code>close()</code> 
	 * will draw a line back to the point we've moved to. (Use negative numbers for moving left or up.)
	 *
	 *@param  x  number of pixels to the right to move
	 *@param  y  number of pixels down to move
	 */
	public void move(double x, double y) {
		this.currentX += x;
		this.currentY += y;
		this.path.moveTo((float) currentX, (float) currentY);
		this.originX = this.currentX;
		this.originY = this.currentY;
	}
	
	
	//MY STUFF__________________________________________________________________________________________-----------------------------------------------------------
	public int getX(){
		int x = (int)this.originX;
		return x;
	}
	public int getY(){
		int y = (int)this.originY;
		return y;
	}
	//MY STUFF____________________________________________________________________________________________----------------------------------------------------------
	
	//MY STUFF__________________________________________________________________________________________-----------------------------------------------------------
		public int getCX(){
			int x = (int)this.currentX;
			return x;
		}
		public int getCY(){
			int y = (int)this.currentY;
			return y;
		}
		//MY STUFF____________________________________________________________________________________________----------------------------------------------------------


	/**
	 *  Draws a line to the last location <code>moveTo()</code> was sent to. If no <code>moveTo()</code>
	 * has been called, goes back to the original location where the <code>Figure</code> was created.
	 */
	public void close() {
		this.path.closePath();
		this.currentX = this.originX;
		this.currentY = this.originY;
	}


	/**
	 *  This method is used by the <code>Picture</code> class to draw this shape.
	 *
	 *@return    a class that <code>Picture</code> can draw
	 */
	public GeneralPath getShape() {
		return this.path;
	}


	/**
	 *  Gets the color of this <code>Figure</code>.
	 *
	 *@return    The color value
	 */
	public Color getColor() {
		return this.color;
	}


	/**
	 *  Sets the color of this <code>Figure</code>. (You may use one of the <code>Color</code>s
	 * at the top of this class, or may create one using one of the <code>java.awt.Color</code>
	 * class's constructors.
	 *
	 *@param  c  The new color value
	 */
	public void setColor(Color c) {
		this.color = c;
	}


	/**
	 *  Tells whether this <code>Figure</code> is filled or not
	 *
	 *@return    <code>true</code> if this <code>Figure</code> is filled, <code>false</code> if not
	 */
	public boolean isFilled() {
		return this.filled;
	}


	/**
	 *  Sets whether this <code>Figure</code> is filled
	 *
	 *@param  b  should be <code>true</code> if you want this <code>Figure</code> filled, <code>false</code> if not
	 */
	public void setFilled(boolean b) {
		this.filled = b;
	}


	/**
	 *  Sets the line width that this figure will be drawn with.
	 *
	 *@param  w  The number of pixels wide to draw the lines
	 */
	public void setLineWidth(int w) {
		this.width = new BasicStroke(w);
	}


	/**
	 *  This method is used by the <code>Picture</code> object to draw this <code>Figure</code>
	 *
	 *@return    something <code>Picture</code> understands
	 */
	public Stroke getStroke() {
		return this.width;
	}
	
	/**
	 * sets the default font for all strings attached to this Figure
	 *
	 *@param font the Font to use
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	
	

	/**
	 *  This method draws an arc from the current point counterclockwise, assuming that the current
	 * point is at angle <code>startAngle</code> and drawing to the angle <code>endAngle</code>. The
	 * curve of the point is determined by an ellipse whose x-radius is <code>radX</code> and whose
	 * y-radius is <code>radY</code>. Angles are given in degrees, with 0 degrees being the positive
	 * x-axis and angles increasing counterclockwise.
	 *
	 *@param  radX        the x-radius of the ellipse of which this arc is a part
	 *@param  radY        the y-radius of the ellipse of which this arc is a part
	 *@param  startAngle  the angle represented by the current point
	 *@param  endAngle    the (greater) angle to which to draw an arc
	 */
	public void drawArc(double radX, double radY, double startAngle, double endAngle) {
		double centerX = currentX - radX * Math.cos(Math.toRadians(startAngle));
		double centerY = currentY + radY * Math.sin(Math.toRadians(startAngle));
		double extent = endAngle - startAngle;
		int numOfFragments = (int) Math.ceil(extent / 90.0);
		double fragAngle = extent / numOfFragments;
		double halfAngle = fragAngle * Math.PI / 360;
		double kappa = 4.0 / 3.0 * (1.0 - Math.cos(halfAngle)) / Math.sin(halfAngle);
		double theta0;
		double theta1;
		double x1;
		double x2;
		double x3;
		double y1;
		double y2;
		double y3;
		for (int i = 0; i < numOfFragments; i++) {
			theta0 = (startAngle + (i * fragAngle)) * Math.PI / 180.0;
			theta1 = (startAngle + (i + 1) * fragAngle) * Math.PI / 180.0;
			x1 = centerX + (radX * (Math.cos(theta0) - kappa * Math.sin(theta0)));
			y1 = centerY - (radY * (Math.sin(theta0) + kappa * Math.cos(theta0)));
			x2 = centerX + (radX * (Math.cos(theta1) + kappa * Math.sin(theta1)));
			y2 = centerY - (radY * (Math.sin(theta1) - kappa * Math.cos(theta1)));
			x3 = centerX + (radX * Math.cos(theta1));
			y3 = centerY - (radY * Math.sin(theta1));
			path.curveTo((float) x1, (float) y1, (float) x2, (float) y2, (float) x3, (float) y3);
			currentX = x3;
			currentY = y3;
		}
	}
	
	/**
	 * Draws text at a given point attached to this <code>Figure</code>
	 *
	 *@param s the <code>String</code> to draw
	 *@param x the x-value of the lower left corner of the first line of the text
	 *@param y the y-value of the lower left corner of the first line of the text
	 */
	public void drawString(String s, double x, double y) {
		this.stringVector.add(new DrawnString(s, x + this.originX, y + this.originY, this.font));
	}
	
	/**
	 * Draws text at a given point attached to this <code>Figure</code> in a
	 * given <code>Font</code>. To create
	 * a Font, you must <code>import java.awt.Font</code> and can create
	 * a Font by saying <code>Font f = new Font(name, style, size);</code>
	 * <code>name</code> can be one of "Serif", "Sans Serif", "Monospaced", or
	 * any font name installed on your system (try "Times", "Courier" or "Helvetica").
	 * <code>style</code> can be one of <code>Font.PLAIN</code>, <code>Font.BOLD</code>,
	 * <code>Font.ITALIC</code>, or <code>Font.BOLD + Font.ITALIC</code>. <code>size</code>
	 * is the point size: 10 is really small, 72 is big.
	 *
	 *@param s the <code>String</code> to draw
	 *@param x the x-value of the lower left corner of the first line of the text
	 *@param y the y-value of the lower left corner of the first line of the text
	 *@param f the font to draw the text in
	 */
	public void drawString(String s, double x, double y, Font f) {
		this.stringVector.add(new DrawnString(s, x + this.originX, y + this.originY, f));
	}
	
	/**
	 * gets the Strings attached to this Figure so that Picture can draw them.
	 */
	public Vector getStrings() {
		return this.stringVector;
	}
}

