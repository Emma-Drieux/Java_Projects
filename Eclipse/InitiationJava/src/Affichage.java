
/**
 * Ce fichier comporte les fonctions d'affichage (de menu, sections ou lignes, espaces etc) utilisés par le programme
 * 
 * @author Emma DRIEUX 
 * @version Dernière modification le 10/01/14 à 14h15
 */



import java.io.*;


public class Affichage {
	//FONCTIONS D'AFFICHAGE ========================================================================================
	  
	  public static void espacesLigne(String ligne){
	    //cette fonction permet d'afficher correctement les lignes
	    //Longueur chaine-60=nbr d'espaces
	    Terminal.ecrireString(ligne);
	    int nbEspaces=60-ligne.length();
	    for(int i=0;i<nbEspaces;i++) Terminal.ecrireString(" ");
	    Terminal.ecrireString("");
	  }
	  
	  public static void espacesFichier(PrintWriter fw,String ligne){
	    fw.print(ligne);
	    int nbEspaces=60-ligne.length();
	    for(int i=0;i<nbEspaces;i++) fw.print(" ");
	    fw.print("");
	  }
	  
	  public static void afficherFacture (String[]sections,int[] ln, int[] nbLignes, String[][]lignes, double[][] prix ,double[] sousTotal, double total){
	    for (int i=0;i<ln[0];i++){
	      Terminal.ecrireStringln(sections[i]);
	      for(int j=0;j<nbLignes[i];j++){
	        Terminal.ecrireString("                               ");
	        espacesLigne(lignes[i][j]);
	        Terminal.ecrireDoubleln(prix[i][j]);
	      }
	      Terminal.ecrireString("                               ");
	      espacesLigne ("");
	      Terminal.ecrireStringln("________");
	      Terminal.ecrireString("                               ");
	      espacesLigne ("");
	      Terminal.ecrireDoubleln(sousTotal[i]);
	    }
	    Terminal.ecrireStringln(""); //saut de ligne avant d'afficher le total
	    Terminal.ecrireString("                               ");
	    espacesLigne ("Net à payer");
	    Terminal.ecrireDoubleln(total);
	  }
	  public static void afficheSections(String[] sections, int[] ln){
	  //Cette fonction permet a l'utilisateur de connaitre le contrnu en sections
	    for (int i =0;i<ln[0];i++){ 
	      Terminal.ecrireStringln(i+" - "+sections[i]);
	    }
	  }
	  public static void afficherLignes(String section,int[] nbLignes, String[][]lignes, double[][]prix, int position){ //position represente aussi le numero de la section
	    Terminal.ecrireStringln("Les lignes de la section "+section+" sont: ");
	    for (int i=0;i<nbLignes[position];i++){
	      Terminal.ecrireString(i+" - ");
	      espacesLigne(lignes[position][i]);
	      Terminal.ecrireDoubleln(prix[position][i]);
	    }
	  }
	  
	  public static void sautDeLigne(){
	    Terminal.ecrireStringln("");
	  }
	  
	  //FONCTIONS AFFICHAGE MENU //==================================================================================== 
	  public static int menuB(){
	    //Cette fonction permet de retourner le choix de l'utilisateur qui permettra de selectionner le programme a lancer
	    sautDeLigne();sautDeLigne();
	    Terminal.ecrireStringln("_____________________________________ MENU _____________________________________");
	    Terminal.ecrireStringln("Voulez vous:\n 1 - Ajouter une section;\n 2 - Ajouter une ligne; \n 3 - Supprimer une ligne;\n 4 - Inserer/Corriger un montant; \n Autres - Retour au menu principal;\n");
	    Terminal.ecrireStringln("________________________________________________________________________________");
	    sautDeLigne();sautDeLigne();
	    int x=Terminal.lireInt();
	    return x;
	  }
	  public static int menuPrincipal(){ //cette fonction  permet d'afficher le menu principal
	    sautDeLigne();sautDeLigne();
	    Terminal.ecrireStringln("_____________________________________ MENU _____________________________________");
	    Terminal.ecrireStringln("\nVoulez vous modifier la facture? Entrez le chiffre correspondant à votre choix:\n 1 - Oui;\n 2 - Non -- éditer la facture;\n 3 - Afficher les sections;\n 4 - Afficher les lignes d'une section avec les prix;\n Autres - Retour au menu principal;\n");
	    Terminal.ecrireStringln("________________________________________________________________________________");
	    sautDeLigne();sautDeLigne();
	    int choix=Terminal.lireInt();
	    return choix;
	  }

}
