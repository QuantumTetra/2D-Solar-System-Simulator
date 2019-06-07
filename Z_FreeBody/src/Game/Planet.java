package Game;

import java.awt.Image;

public class Planet {
	private double mass;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private Image i;
	private double radius;
	private String name;
	private double rotationRad = 0;
	private double rotPerTickRad;
	public Planet (String Name, double rad, double X, double Y, double Dx, double Dy ,double Mass,double r, Image I){
		x=X;
		y=Y;
		vx=Dx;
		vy=Dy;
		mass=Mass;
		i=I;
		radius = rad;
		name = Name;
		rotPerTickRad = r;
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
	public void setImage(Image i2){
		i=i2;
	}
	public String getName(){
		return name;
	}
	public void setName (String Name){
		name = Name;
	}
	public double getRadius(){
		return radius;
	}
	public void setRadius (double rad){
		radius = rad;
	}
	public double getRot (){
		return rotPerTickRad;
	}
}
