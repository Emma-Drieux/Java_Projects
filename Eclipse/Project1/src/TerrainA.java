import java.util.ArrayList;

public class TerrainA{
	
	private Case[][] p=new Case[13][12];
	private ArrayList<Avion> listeAvions;		//pour garder la main sur la liste d'avions présentes sur le terrain
	private ArrayList<PorteAvion> listePorteAvion;	//pour garder la main sur la liste de portes avions
	
	//Constructeur
	public TerrainA(){
		this.listeAvions=new ArrayList<Avion>();
		this.listePorteAvion=new ArrayList<PorteAvion>();
	}
	
	//geteur
	public Case[][] getPlateau(){return this.p;}
	public ArrayList<Avion> getListeAvions(){return this.listeAvions;}
	public ArrayList<PorteAvion> getListPorteAvion(){return this.listePorteAvion;}

	//Remplissage des coordonnees
	public void remplissageCases(){
		// on suppose que la terre s'étend sur 40% de la surface (40% de la longueur et 40% de la largeur)
		int longTer=(13*50)/100;
		//System.out.println(longTer);
		int largTer=(12*20)/100;
		//System.out.println(largTer);
			
		//Remplissage des cases terre
		for(int i=0;i<longTer;i++){
			for(int j=0;j<largTer;j++){
				p[i][j]=new Case(i,j,"terre");
			}
		}
		//Remplissage des cases mer
		for(int i=longTer;i<13;i++){
			for(int j=0;j<largTer;j++){
				p[i][j]=new Case(i,j,"mer");
			}
		}
			
		for (int i=0;i<13;i++){
				for(int j=largTer;j<12;j++){
					p[i][j]=new Case(i,j,"mer");
				}
			}
	
	//Rajouter trois porte-avion
		this.listePorteAvion.add(new PorteAvion(9,7,"France")); //premier PA; index=0
		this.listePorteAvion.add(new PorteAvion(2,5,"USA")); //index=1
		this.listePorteAvion.add(new PorteAvion(10,10,"UK")); //index=2
		
		this.listeAvions.add(new Avion("Paul",9,7));//index=0
		this.listeAvions.add(new Avion("Xenus",9,7));//index=1
		this.listeAvions.add(new Avion("Mercur",true,9,7));//index=2
		this.listeAvions.add(new Avion("Roosevelt",false,10,10));//index=3
		this.listeAvions.add(new Avion("Pascal",true,2,1));//index=4
		this.listeAvions.add(new Avion("Max",2,1));//index=5
		this.listeAvions.add(new Avion("Hercule",true,2,1));//index=6
		this.listeAvions.add(new Avion("Isis",2,1));//index=7
		this.listeAvions.add(new Avion("Zoodiaque",true,4,0));//index=8
		
		
	    p[9][7].setPA(this.listePorteAvion.get(0));
	    p[9][7].setContPA(true);
	    
	    p[9][7].ajouterAvion(this.listeAvions.get(0));
	    p[9][7].ajouterAvion(this.listeAvions.get(1));
	    p[9][7].ajouterAvion(this.listeAvions.get(2));
	    
	    p[2][5].setPA(this.listePorteAvion.get(1));
	    p[2][5].setContPA(true);
	    
	    p[10][10].setPA(this.listePorteAvion.get(2));
	    p[10][10].setContPA(true);
	    
	    p[10][10].getPA().add(this.listeAvions.get(3));
	    
	    //Rajouter 4 avion sur terre aux coordonnées (2,1)
	    p[2][1].ajouterAvion(this.listeAvions.get(4));
	    p[2][1].ajouterAvion(this.listeAvions.get(5));
	    p[2][1].ajouterAvion(this.listeAvions.get(6));
	    p[2][1].ajouterAvion(this.listeAvions.get(7));
	    
	    //Rajouter un avion sur terre aux coordonnees(4,0)
	    p[4][0].ajouterAvion(this.listeAvions.get(8));
	}
}
