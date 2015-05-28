
public class Case {
	
	//une case est représentée par ses coordonnées fixes et chacune constitue un ensemble de trois types de conteneurs
	
	private Coordonnees c; //les coordonnees sont celle de la case
	private Conteneur cc; //en fonction de l'etat de l'avion, on saura s'il est dans les airs (sera forcément ds les airs pour mer) ou sur terre
	private PorteAvion pa; //ne sera rempli que pour les case où type=mer et si celui ci est vide
	private String type;
	private boolean contientPA; //pour savoir si la case mer contient ou pas un porte avion
	
	
	//Constructeur1
	public Case(Coordonnees c, String type){
		this.c=c;
		this.cc=new Conteneur();
		this.pa=new PorteAvion();
		this.type=type;
	}
	
	//Constructeur2
	public Case(int x, int y, String type){
		this.c=new Coordonnees(x,y);
		this.pa=new PorteAvion();
		this.type=type;
		this.cc=new Conteneur();
	}
	
	//geteurs
	public Coordonnees getCoord(){ return this.c;}
	public Conteneur getConteneur(){ return this.cc;}
	public PorteAvion getPA(){return this.pa;}
	public String getType(){ return this.type;}
	public boolean getContPA(){return this.contientPA;}
	
	//seteurs
	public void setType(String type){this.type=type;}
	public void setPA(PorteAvion pa){this.pa=pa;}
	public void setContPA(boolean b){this.contientPA=b;}
	
	public int nbElem(){
		return (this.getConteneur().nbElem()+this.pa.nbElem());
	}
	
	public int nbElemAirs(){ return this.getConteneur().nbElemAirs();}
	public int nbElemPoses(){ return this.getConteneur().nbElemPoses()+this.getPA().nbElemPoses();}
	
	public void ajouterAvion(Avion a){
		if (this.nbElem()==5) throw new CaseFull(); //maximum d'avion =5
		//l'avion peut etre ajouter dans le conteneur ciel
		else if (a.getEtat()) {
			a.getCordonnees().setX(this.getCoord().getX());//changer les coordonnées de a qui sera rajouter à la liste du conteneur
			a.getCordonnees().setY(this.getCoord().getY());
			this.getConteneur().add(a);//etat=true donc l'avion est dans les airs
		}
		else if(!a.getEtat()){//ATTENTION: un avion ne peut atterir que sur un porte-avion
			//si les coordonnées ne sont pas celle d'un porte avion === pas possible
			if(!this.contientPA && this.type=="mer") throw new AtterrissageImpossible();
			else if(this.contientPA){
				a.getCordonnees().setX(this.getCoord().getX());//changer les coordonnées de a qui sera rajouter à la liste du conteneur
				a.getCordonnees().setY(this.getCoord().getY());
				this.pa.add(a); //ajouter dans le conteneur mer de la case
			}
			else if(this.type=="terre"){
				a.getCordonnees().setX(this.getCoord().getX());//changer les coordonnées de a qui sera rajouter à la liste du conteneur
				a.getCordonnees().setY(this.getCoord().getY());
				this.cc.add(a);
			}
		}
	}
	
	
	public void deleteAvion(Avion a){
		if(a.getEtat()) this.cc.remove(a);//etat=true donc l'avion est dans les airs
		if(!a.getEtat()) this.pa.remove(a); //si il a atteri auparavant, c'est qu'il est certainement sur un porte-avion!!
	}
	

}

class CaseFull extends Error{}

class AtterrissageImpossible extends Error{}