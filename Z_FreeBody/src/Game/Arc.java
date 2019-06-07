package Game;
public class Arc extends Figure{
  public Arc(int x, int y, int xrad, int yrad, int s, int f){
    super(x, y);   //constructor
    this.move(xrad*Math.cos(Math.toRadians(s)),-yrad*Math.sin(Math.toRadians(s)));
    this.drawArc(xrad, yrad, s, f);
  }
}
