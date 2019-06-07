package Game;

public class Delete {
	private  Planet planet;
	private Planet planet2;
	public Delete (Planet p , Planet p2){
		planet = p;
		planet2 = p2;
	}
	public Planet getPlanet1(){
		return planet;
	}
	public Planet getPlanet2(){
		return planet2;
	}
}
