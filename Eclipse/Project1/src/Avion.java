
public class Avion{
	private boolean etat; //false: l'avion est posé à terre
	private String nom; //l'avion est repéré grâce à son nom
	private Coordonnees cor;
	
	//Constructeur 1
	public Avion(String nom, Coordonnees cor){
		this.nom=nom;
		this.cor=cor;
		this.etat=false;
	}
	
	//Constructeur 2
	public Avion(String nom, boolean etat, Coordonnees cor){
		this.nom=nom;
		this.cor=cor;
		this.etat=etat;
	}
	
	//Constructeur 3
	public Avion(String nom, int x, int y){
		this.nom=nom;
		this.cor=new Coordonnees(x,y);
		this.etat=false;
	}
	
	//Constructeur 4
	public Avion(String nom, boolean etat, int x, int y){
		this.nom=nom;
		this.cor=new Coordonnees(x,y);
		this.etat=etat;
	}
	
	//geteurs
	
	public boolean getEtat(){return this.etat;}
	public String getNom(){return this.nom;}
	public Coordonnees getCordonnees(){return this.cor;}
	
	//seteurs
	public void setEtat(boolean etat){this.etat=etat;}
	public void setNom(String nom) {this.nom=nom;}
	
	
	public void atterrirAvion(Case c){
		if(c.getType()=="mer" && !(c.getContPA())) throw new AtterrisageImpossible();
		else this.setEtat(false);
	}
	
}

class AtterrisageImpossible extends Error{}
