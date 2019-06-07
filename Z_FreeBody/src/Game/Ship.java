package Game;

import java.awt.Image;

public class Ship {
	private double mass;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private Image i;
	public Ship (double X, double Y, double Dx, double Dy ,double Mass, Image I){
		x=X;
		y=Y;
		vx=Dx;
		vy=Dy;
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
	public double getVX(){
		return vx;
	}
	public void setVX(double VX){
		vx=VX;
	}
	public double getVY(){
		return vy;
	}
	public void setVY(double VY){
		vy=VY;
	}
	public Image getImage(){
		return i;
	}
}
