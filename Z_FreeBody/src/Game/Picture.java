package Game;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *  This class describes a <code>Picture</code> object which <code>Figure</code>s can be drawn on.
 *
 *@author     David Dallmann
 *@created    October 28, 2008
 */
public class Picture extends Frame {
	private Vector figureVector;
	private Image offScreenBuffer;
	private MouseEvent lastMouseEvent;
	//MINE
	private ArrayList<Double> x = new ArrayList<>();
	private ArrayList<Double> y = new ArrayList<>();
	private ArrayList<Image> Images = new ArrayList<>();

	public void addImageToList(Image i, double x2, double y2){
		Images.add(i);
		x.add(x2);
		y.add(y2);
	}
	
	public void removeImageFromList(int num){
		Images.remove(num);
		x.remove(num);
		y.remove(num);
	}
	
	
	
	/**
	 *  Creates a <code>Picture</code> object of size 500 by 400 called "My Picture".
	 */
	public Picture() {
		this("My Picture");
	}


	/**
	 *  Creates a <code>Picture</code> object of size 500 by 400 called <code>title</code>.
	 *
	 *@param  title  the title of this <code>Picture</code>
	 */
	public Picture(String title) {
		this(title, 500, 400);
	}

	/**
	 * Creates a <code>Picture</code> object of the given size called "My Picture".
	 *
	 *@param width the width of this <code>Picture</code>
	 *@param height the height of this <code>Picture</code>
	 */
	public Picture(int width, int height) {
		this("My Picture", 500, 400);
	}

	/**
	 *  Creates a <code>Picture</code> object of size <code>width</code> by <code>height</code> called <code>title</code>.
	 *
	 *@param  title   the title of this <code>Picture</code>
	 *@param  width   the width of this <code>Picture</code> in pixels
	 *@param  height  the height of this <code>Picture</code> in pixels
	 */
	public Picture(String title, int width, int height) {
		super(title);
		this.figureVector = new Vector();
		createUI(width, height);
		setVisible(true);
	}


	/**
	 *  creates the UI
	 *
	 *@param  width   
	 *@param  height  
	 */
	private void createUI(int width, int height) {
		setSize(width, height);
		center();

		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
					System.exit(0);
				}
			});
		addMouseListener(new MyMouseListener());
	}


	/**
	 *  Centers the picture on the screen
	 */
	private void center() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		int x = (screenSize.width - frameSize.width) / 2;
		int y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}


	/**
	 *  Pauses <code>millis</code> milliseconds.
	 *
	 *@param  millis  the amount of time to pause in milliseconds
	 */
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// do nothing
		}
	}
	
	/**
	 * This method will wait until the user clicks somewhere in this
	 * <code>Picture</code> and then return the MouseEvent they used
	 */
	public MouseEvent getMouseClick() {
		this.lastMouseEvent = null;
		while (lastMouseEvent == null) {
			//wait here
		}
		return lastMouseEvent;
	}
	
	/**
	 *  This method is called by the graphics system to paint this component
	 *
	 *@param  g  a Graphics object
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Figure f;
		Vector stringVector;
		for (int i = 0; i < figureVector.size(); i++) {
			f = (Figure) figureVector.get(i);
			g2.setPaint(f.getColor());
			g2.setStroke(f.getStroke());
			if (f.isFilled()) {
				g2.fill(f.getShape());
			} else {
				g2.draw(f.getShape());
			}
			stringVector = f.getStrings();
			DrawnString d;
			for (int j = 0; j < stringVector.size(); j++) {
				d = (DrawnString) stringVector.get(j);
				g2.setFont(d.getFont());
				g2.drawString(d.getString(), (float) d.getX(), (float) d.getY());
			}
		}
	}


	/**
	 *  This method adds the passed-in <code>Figure</code> to this <code>Picture</code>
	 *
	 *@param  f  the <code>Figure</code> to add to this <code>Picture</code>
	 */
	public void add(Figure f) {
		figureVector.add(f);
		this.repaint();
	}


	/**
	 *  Removes the given <code>Figure</code> from this <code>Picture</code>
	 *
	 *@param  f  the <code>Figure</code> to remove. Note that this must be exactly the <code>Figure</code>
	 *				you want to remove (i.e. it must be ==), an identical <code>Figure</code> will not work
	 */
	public void paintNewImage(Graphics g,ImageIcon a){
		super.paint(g);
		g.drawImage(a.getImage(), (1920-a.getIconWidth())/2, (1080-a.getIconHeight())/2, this);
	}
	
	
	
	public void setAnImage(Image a, int index, double x2, double y2){
		Image a2 = a;
		Images.set(index, a2);
		x.set(index, x2);
		y.set(index, y2);
	}
	
	
	public void remove(Figure f) {
		figureVector.remove(f);
		this.repaint();
	}

	/**
	 * This method draws the <code>Picture</code> on an image and then switches the images to
	 * reduce flicker.
	 *
	 *@param g a <code>Graphics</code> object
	 */
	
	public void resetOSB(){
		try{
			
			offScreenBuffer = null;
		}catch(Exception e){
		}
	}
	public void update(Graphics g)
    {
		Graphics gr; 
		// Will hold the graphics context from the offScreenBuffer.
		// We need to make sure we keep our offscreen buffer the same size
		// as the graphics context we're working with.
		
		
		if (offScreenBuffer==null ||
                (! (offScreenBuffer.getWidth(this) == this.getSize().width
                && offScreenBuffer.getHeight(this) == this.getSize().height))) {
					offScreenBuffer = this.createImage(getSize().width, getSize().height);
        }

		// We need to use our buffer Image as a Graphics object:
		gr = offScreenBuffer.getGraphics();
		gr.setColor(java.awt.Color.white);
		gr.fillRect(0,0,getSize().width, getSize().height);
		gr.setColor(java.awt.Color.black);
		paint(gr); // Passes our off-screen buffer to our paint method, which,
               // unsuspecting, paints on it just as it would on the Graphics
               // passed by the browser or applet viewer.
		
		
		//CHARLES CODE------------------------------------------------------------------------------------------------
				//                   For adding images to the offscreenbuffer for sprites (TEST)
				for(int c =0; c<Images.size(); c++){
					Graphics g3;
					try{
						g3 = offScreenBuffer.getGraphics();
					
					double tempx = x.get(c);
					double tempy = y.get(c);
					//System.out.println("Image "+c+" was added at X: "+(int)tempx + "  Y: "+(int)tempy);
					g3.drawImage(Images.get(c), (int)tempx, (int)tempy, Images.get(c).getWidth(null), Images.get(c).getHeight(null), null);
					}catch(Exception e){
						
					}
				}
				//--------------------------------FAILURE----------------------------------------------------------------------
				
				
				
		g.drawImage(offScreenBuffer, 0, 0, this);
               // And now we transfer the info in the buffer onto the
               // graphics context we got from the browser in one smooth motion.
    }
	
	private class MyMouseListener extends javax.swing.event.MouseInputAdapter {
		public void mouseClicked(MouseEvent e) {
			lastMouseEvent = e;
		}
	}
}


