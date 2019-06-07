package Game;

import java.awt.Image;

public class Star {
	private double mass;
	private double x;
	private double y;
	private Image i;
	public Star (double X , double Y , double Mass, Image I){
		x=X;
		y=Y;
		mass=Mass;
		i=I;
	}
	public double getMass(){
		return mass;
	}
	public void setMass(double Mass){
		mass=Mass;
	}
	public double getX(){
		return x;
	}
	public void setX(double X){
		x=X;
	}
	public double getY(){
		return y;
	}
	public void setY(double Y){
		y=Y;
	}
	public Image getImage(){
		return i;
	}
}
