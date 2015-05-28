
public class Coordonnees {
	private int x; private int y;
	
	//Contructeur1: pas de coordonnees
	public Coordonnees(){
	}
	
	//Constructeur2
	public Coordonnees(int x, int y){
		this.x=x; this.y=y;
	}
	
	//geteurs
	public int getX(){return this.x;}
	public int getY(){return this.y;}
	
	//seteurs (modificateurs)
	public void setX(int a){this.x=a;}
	public void setY(int b){this.y=b;}
	
	//Affichage
	public String toString(){
		return ("( "+this.x+","+this.y+" )");
	}
}
