package arbreDecisionnel;

/*
 * 
 */
public class Parametre{
	private String type; //permet de determiner si c'est un parametre (noeud), une decision ou bien rien (null)
	//Type={paraM,decision,examenCom,rien}
	private String nom;//portera le nom
	private String examComp;//l'examen complementaire ou la decision
	private double valInf;
	private int signe; //certains parametres sotn pathologiques s'ils augmente;
	//la valInf dans ce cas de figure sera la borne supérieure (qui prendra une valeur négative) de l'interval et la valeur du parametre dans le tableau prendra un signe (-)
	//De cette façon, on descendra toujours vers le bras gauche pour les valeurs pathologiques
	
	//On suppose que les decisions pour chaque parametre reposent sur un choix binaire
	////inférieur à la borne supérieure, dans le cas contraire normal
	
	//Constructeur1: cas parametre
	public Parametre(String nom, double d,int signe){
		this.type="paraM";
		this.nom=nom;
		this.valInf=d;this.signe=signe;
	}
	
	//Constructeur2: cas decision ou examen complementaire
	public Parametre(String type,String examComp){
		this.type=type;
		this.examComp=examComp;
	}
	
	//Constructeur3: cas null
	public Parametre(){this.type="rien";}

	//Getteurs et Setteurs
	public String getType() {return type;}
	public String getNom() {return nom;}
	public String getExamComp() {return examComp;}
	public double getValInf() {return valInf;}
	
	public int getSigne() {return signe;}
	
	
}
