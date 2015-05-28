/**
 * Cette classe comporte la méthode main qui permet de lancer le programme
 * 
 * @author Emma DRIEUX 
 * @version Dernière modification le 10/01/14 à 14h15
 */

public class Main {
	public static void main (String[] args){
	    /*=========INITIALISATION DES VARIABLES ==================*/
	    //declaration de l'ensemble des tableaux: lignes, nbStruc, structures,Prix
	    String[] sections=new String[100]; //contient le nom de sections -- Maximum =100
	    int[] nbLignes=new int[100]; //indique le nombre de lignes pour chaque structure
	    double[] sousTotal=new double[100]; //variable qui permet de garder le sous total lie a chaque section
	    double total; //est la variable qui garde en mémoire le total net a payer
	    
	    String[][] lignes=new String[100][100]; //matrice 2D des lignes -- Maximum = 1000 lignes
	    double[][] prix=new double[100][100]; //matrice des prix de chacune des lignes
	    
	    int[]ln={0}; //nombre de sections
	    
	    int section,ligne; //pour le choix 4 du menu principal --
	    
	    /*=========INITIALISATION DES VARIABLES ==================*/

	    int choix;
	    
	    do{
	      choix=Affichage.menuPrincipal();
	      
	      //faire plutot un swich des case ...
	      switch(choix){
	        case(1):
	          Choix.premierChoix(sections, nbLignes, lignes, prix, ln);
	          continue; //passer à la boucle suivante
	        
	        case(2):
	          continue;
	          
	        case(3): //dans le menu principal, on choisit d'afficher les sections
	          if (ln[0]==0){
	            Terminal.ecrireStringln("\nVous n'avez saisi aucune section jusqu'à présent ...\n\nRetour au menu principal");
	            Affichage.sautDeLigne();
	            continue;
	          }
	          else{
	            Affichage.sautDeLigne();
	            Terminal.ecrireStringln("Voici les sections entrées jusqu'à présent");
	            Affichage.afficheSections(sections,ln);
	            continue; //passer à la boucle suivante -- réafficher le menu principal
	          }
	 
	        case(4): //dans le menu principal, on choisit d'afficher les lignes d'une section
	          if(ln[0]==0){ //Aucune section n'a été saisi
	            Terminal.ecrireStringln("\nVous n'avez saisi aucune section jusqu'à présent ...\n\nRetour au menu principal");
	            Affichage.sautDeLigne();
	            continue;
	          }
	          else{
	            Affichage.sautDeLigne();
	            Terminal.ecrireStringln("Voici les sections entrées jusqu'à présent");
	            Affichage.afficheSections(sections,ln);
	            Affichage.sautDeLigne();
	            Terminal.ecrireString("Quelle est la section dont vous aimeriez afficher les lignes? ");
	            section=Terminal.lireInt();
	            Affichage.sautDeLigne();
	            if(nbLignes[section]==0){
	              Terminal.ecrireStringln("\nVous n'avez saisi aucune ligne dans la section "+sections[section]+" ... \n\nRetour au menu principal.\n");
	              continue;
	            }
	            else{
	              Affichage.afficherLignes(sections[section],nbLignes, lignes, prix, section);
	              continue;
	            }
	          }      
	        default : //aucun des choix cités n'a été sélectionné par l'utilisateur
	          Terminal.ecrireStringln("\nERREUR\n !!!!!!!!Vous n'avez saisi aucun des choix cités ... Retour au menu principal.!!!!!!!\n");
	          continue; //passer à la boucle suivante -- réafficher le menu principal
	        }
	    }while(choix!=2); //on reste dans le while tant que l'utilisateur ne demande pas l'édition de la facture
	    
	    //on lance la fonction sousTotal avant l'edition sur fichier de la facture
	    total=Fonctions.sousTotal(sousTotal, prix, nbLignes, ln);
	    Affichage.afficherFacture (sections,ln, nbLignes,lignes, prix,sousTotal,total);
	    Bill.creationFacture(sections,ln, nbLignes, lignes,prix ,sousTotal, total); //création de la facture .txt
	  }

}
