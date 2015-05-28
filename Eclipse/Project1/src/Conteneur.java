
import java.util.ArrayList;

public class Conteneur{ //définit l'ensemble des conteneurs simples (terre, ciel)
	
	//chaque conteneur peut avoir une liste d'avion
	private ArrayList<Avion> contenance;
	
	//constructeur
	public Conteneur(){
		this.contenance=new ArrayList<Avion>();
	}
	
	
	//geteurs: retourner la liste des avions du conteneur
	public ArrayList<Avion> getList(){ return this.contenance;}
	
	//Methodes
	public int nbElem(){return this.contenance.size();} //méthode qui retourne le nombre d'avions dans le conteneur
	public int nbElemAirs(){
		int compteur=0;
		for(int i=0;i<this.contenance.size();i++){
			//si l'avion est dans les airs, etat=true
			if(this.contenance.get(i).getEtat()) compteur++;
		}
		return compteur;
	}
	public int nbElemPoses(){
		int compteur=0;
		for(int i=0;i<this.contenance.size();i++){
			//si l'avion est dans les airs, etat=true
			if(!this.contenance.get(i).getEtat()) compteur++;
		}
		return compteur;
	}
	
	
	public void add(Avion a) {this.contenance.add(a);}//Ajouter un avion à la liste
	public void remove(Avion a){this.contenance.remove(a);} //supprimer un avion
	
	public void afficherAvionAirs(){
		if (this.nbElemAirs()==0) throw new ListeVide();
		else {
			Terminal.ecrireStringln("\nLa liste d'avions qui survolent les airs de cette case sont:");
			for(int i=0;i<this.contenance.size();i++){
				if (this.contenance.get(i).getEtat()) Terminal.ecrireStringln("L'avion "+this.contenance.get(i).getNom());
			}
		}
	}
	
	public void afficherAvionPoses(){
		if (this.nbElemPoses()==0) throw new ListeVide();
		else {
			Terminal.ecrireStringln("\nLa liste d'avions posés au niveau de cette case sont:");
			for(int i=0;i<this.contenance.size();i++){
				if (! (this.contenance.get(i).getEtat())) Terminal.ecrireStringln("L'avion "+this.contenance.get(i).getNom());
			}
		}
	}

	
	public void afficherAvion(){
		if (this.contenance.size()==0) throw new ListeVide();
		else {
			for(int i=0;i<this.contenance.size();i++){
				Terminal.ecrireStringln("L'avion "+this.contenance.get(i).getNom());
			}
		}
	}
	
	public int trouverIndAvion(String nom){
		int index=-1;
		int i=0;
		for(;i<this.contenance.size();){
			if(this.contenance.get(i).getNom().equals(nom)) {index=i;}
			i++;
		}
		return index;
	}

}

class ListeVide extends Error{}
