/**
 * Cette classe comporte les différentes fonctions qui permettent de modifier la facture
 * J'ai séparé les méthodes en plusieurs groupes:
 * * * (A) : FONCTIONS D'AJOUT: fonctions qui permettent d'ajouter une section ou une ligne
 * * * (B) : FONCTIONS DE SUPPRESSION OU DE CORRECTION: fonction de suppression d'une ligne ou de modification/insersion d'un prix
 * * * (C) : FONCTION DE CALCUL: du sous total de chaque section et du total de l'ensemble de la facture
 * @author Emma DRIEUX
 * @version Dernière modification le 10/01/14 à 14h15
 */

public class Fonctions {
	//FONCTIONS D'AJOUT// =========================================================================================
	  ////Les rajouts se font toujours à la fin des tableau de sections/nbLignes/lignes/prix
	  public static void ajoutSect(String[] sections, int[] ln, String section,int[] nbLignes){ //(ln) represente le nombre de sections
	    //cette methode permet de rajouter une ligne
	    sections[ln[0]]=section;
	    nbLignes[ln[0]]=0; //initialiser le nbr de lignes de la nouvelle section
	    ln[0]=ln[0]+1;
	  }
	    
	  public static void ajoutLigne (String[][] lignes, String ligne, int[] nbLignes, int position){
	    //cette fonction rajoute une ligne ___ donc changer le nbr de lignes pour une section donnee
	    lignes[position][nbLignes[position]]=ligne;
	    nbLignes[position]=nbLignes[position]+1;
	  }
	  //FONCTIONS D'AJOUT// =========================================================================================
	  
	  //FONCTION DE SUPPRESSION DE LIGNE ET DE CORRECTION DE PRIX // =========================================================================
	              //supprimer une ligne implique la suppression du prix
	  public static void deletLigne(String[][] lignes, int[] nbLignes, int position, int posLigne){//posLigne represente la position de la ligne ds la section et position la position de sa section
	    for(int i=posLigne;i<nbLignes[position];i++){
	      lignes[position][i]=lignes[position][i+1];
	    }
	    lignes[position][nbLignes[position]]=""; //vider la dernière case
	    nbLignes[position]=nbLignes[position]-1; // modifier le nbr de ligne
	  }
	  
	  public static void changePrix(double[][] prix, double newPrix, int position, int posLignes) { //position = numero de la section
	    prix[position][posLignes]=newPrix;
	  }

	 //FONCTION DE SUPPRESSION DE LIGNE ET DE CORRECTION DE PRIX // =========================================================================
	  
	  
	  //FONCTION DE CALCUL DU TOTAL ET SOUS TOTAL //================================================================
	  /* Les fonctions suivantes permettent de calculer le total et le sous total __ elles seront lancée à la fin avant l'edition de la facture*/
	  
	  public static double sousTotal(double[] sousTotal, double[][]prix, int[] nbLignes, int[] ln){ //cette fonction retourne le total net a payer
	    double total=0;
	    for (int i=0;i<ln[0];i++){ // i est le numero de la section
	      for (int j=0;j<nbLignes[i];j++){ //j est le numero de la ligne
	        sousTotal[i]=prix[i][j];
	        total=total+prix[i][j];
	      }
	    }
	    return total;
	  }
	  //FONCTION DE CALCUL DU TOTAL ET SOUS TOTAL //================================================================

}
