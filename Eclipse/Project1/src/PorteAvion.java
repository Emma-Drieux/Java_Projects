
public class PorteAvion extends Conteneur{
	//un porte avion est aussi un conteneur mais dont les coordonnes changent
	
	private String nom;
	private Coordonnees cor;
	
	//Constructeur1
	public PorteAvion(){} //pour initialiser une classe Nulle (s'il n'y a pas de porte-avion)
	
	//Constructeur2
	public PorteAvion(int x, int y, String nom){
		super(); //constructeur classe mere
		this.nom=nom;
		this.cor=new Coordonnees(x,y);
	}
	
	// Constructeur3
	public PorteAvion(Coordonnees cord,String nom){
		super();
		this.nom=nom;
		this.cor=cor;
	}
	
	//geteur
	public String getNom(){return this.nom;}
	public Coordonnees getCoordonnees(){return this.cor;}
	
	//seteur
	public void setCoordonnees(Coordonnees cor){this.cor=cor;}
	
	public void afficherAvionPoses(){
		if (this.nbElemPoses()==0) throw new ListeVide();
		else {
			for(int i=0;i<this.getList().size();i++){
				if (! (this.getList().get(i).getEtat())) Terminal.ecrireStringln("L'avion "+this.getList().get(i).getNom());
			}
		}
	}
}
