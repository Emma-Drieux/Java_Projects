package arbreDecisionnel;

import java.util.ArrayList;


/*
 * Cette classe permet de stocker l'ensemble des decisions récoltées à la suite du parcours de l'arbre
 */
public class Decisions {
	private ArrayList<String> decisions;
	private ArrayList<String> types;
	
	//Constructeur
	public Decisions(){
		decisions=new ArrayList<String>();
	}
	
	//Geteur: retourne la liste des décisions
	public ArrayList<String> getDecisions() {return decisions;}
	public ArrayList<String> getTypes() {return types;}
	
	public void ajouter(String newPar, String type){this.decisions.add(newPar);this.types.add(type);}
}
