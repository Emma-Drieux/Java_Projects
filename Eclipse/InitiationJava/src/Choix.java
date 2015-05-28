/**
 * Cette classe � �t� cr��e pour traiter le cas o� l'utilisateur choisit de modifier la facture
 * Dans ce cas plusieurs possibilit�s sont affich�es dans un menu (module menuB() du fichier Affichage.java)
 * En fonction du choix, le programme utilisera la ou les fonction(s) ad�quate(s) cod�e(s) dans le fichier Fonctions.java
 * 
 * @author Emma DRIEUX 
 * @version Derni�re modification le 10/01/14 � 14h15
 */

public class Choix {
	public static void premierChoix(String[] sections, int[] nbLignes, String[][] lignes, double[][] prix, int[] ln){ //si le choix dans le menu principal est de modifier la facture
	    int x=Affichage.menuB(); //x garde le choix de l'utilisateur
	    String section,ligne; //variable qui stockent le nom d'une nouvelle ligne ou section
	    int position, posLigne; //garde en m�moire le numero de la section ou le numero de la ligne
	    double newPrix;
	    switch(x){
	      case(1): // l'utilisateur a choisi d'ajouter une section
	        Affichage.sautDeLigne();
	        Terminal.ecrireString("Entrez le nom de la nouvelle section: ");
	        section=Terminal.lireString();
	        Fonctions.ajoutSect(sections, ln, section, nbLignes);
	        Affichage.sautDeLigne();
	        Terminal.ecrireStringln("Voici la nouvelle liste des sections: ");
	        Affichage.afficheSections(sections, ln);
	        break;
	      
	      case (2): // l'utilisateur a choisi d'ajouter une ligne
	        Affichage.sautDeLigne();
	        Terminal.ecrireString("Quel est le nom de la nouvelle ligne? ");
	        ligne=Terminal.lireString();
	        Affichage.sautDeLigne();
	        Terminal.ecrireStringln("A quelle section appartient cette nouvelle ligne? ");
	        Affichage.afficheSections(sections, ln);
	        Affichage.sautDeLigne();
	        Terminal.ecrireString("Quelle est le numero de la section? ");
	        position=Terminal.lireInt();
	        if (position>ln[0]){
	          Affichage.sautDeLigne();
	          Terminal.ecrireStringln("Le numero de la section saisi est sup�rieur au nombre de sections saisies jusqu'� pr�sent ...\n\nRetour au menu principal.\n");
	          break;
	        }
	        else{
	          Fonctions.ajoutLigne (lignes,ligne, nbLignes, position);
	          Affichage.sautDeLigne();
	          Terminal.ecrireString("Quel est le prix associ� � cette nouvelle entr�e: ");
	          newPrix=Terminal.lireDouble();
	          Fonctions.changePrix(prix, newPrix, position, (nbLignes[position]-1));//la ligne est rajoutee a la fin c'est pour �a qu'on prend (nbLignes[position]-1)
	          Affichage.afficherLignes(sections[position],nbLignes, lignes,prix, position);
	          break;
	        }
	      
	      case (3): //l'utilisateur a choisi de supprimer une ligne
	        Affichage.sautDeLigne();
	        Terminal.ecrireStringln("A quelle section appartient cette nouvelle ligne? ");
	        Affichage.afficheSections(sections, ln);
	        Affichage.sautDeLigne();
	        Terminal.ecrireString("Quelle est le numero de la section? ");
	        position=Terminal.lireInt();
	        if (position>ln[0]){
	          Affichage.sautDeLigne();
	          Terminal.ecrireStringln("Le numero de la section saisi est sup�rieur au nombre de sections saisies jusqu'� pr�sent ...\n\nRetour au menu principal.\n");
	          break;
	        }
	        else{
	          Affichage.afficherLignes(sections[position],nbLignes, lignes,prix, position);
	          Affichage.sautDeLigne();
	          Terminal.ecrireString("Quelle est la ligne que vous souhaitez supprimer? ");
	          posLigne=Terminal.lireInt();
	          if (posLigne>nbLignes[position]){
	            Affichage.sautDeLigne();
	            Terminal.ecrireStringln("Le numero saisi est sup�rieur au nombre de lignes saisies jusqu'� pr�sent pour la section "+sections[position]+" ...\n\nRetour au menu principal.\n");
	            break;
	          }
	          else{
	            Fonctions.deletLigne(lignes, nbLignes, position, posLigne);
	            break;
	          }
	        }
	      
	      case(4): //l'utilisateur a choisi de corriger un prix
	        Affichage.sautDeLigne();
	        Terminal.ecrireStringln("A quelle section appartient la ligne du prix? ");
	        Affichage.afficheSections(sections, ln);
	        Affichage.sautDeLigne();
	        Terminal.ecrireString("Quelle est le numero de la section? ");
	        position=Terminal.lireInt();
	        if (position>ln[0]){
	          Affichage.sautDeLigne();
	          Terminal.ecrireStringln("Le numero de la section saisi est sup�rieur au nombre de sections saisies jusqu'� pr�sent ...\n\nRetour au menu principal.\n");
	          break;
	        }
	        else{
	          Affichage.afficherLignes(sections[position],nbLignes, lignes,prix, position);
	          Affichage.sautDeLigne();
	          Terminal.ecrireString("Quelle est la ligne dont le prix sera modifi�? ");
	          posLigne=Terminal.lireInt();
	          if (posLigne>nbLignes[position]){
	            Affichage.sautDeLigne();
	            Terminal.ecrireStringln("Le numero saisi est sup�rieur au nombre de lignes saisies jusqu'� pr�sent pour la section "+sections[position]+" ...\n\nRetour au menu principal.\n");
	            break;
	          }
	          else {
	            Affichage.sautDeLigne();
	            Terminal.ecrireString("Quel est le nouveau prix � ins�rer?" );
	            newPrix=Terminal.lireDouble();
	            Fonctions.changePrix(prix, newPrix, position, posLigne);
	            break;
	          }
	        }
	      
	      default:
	        Terminal.ecrireStringln("\nVous n'avez pas saisi un choix permi ceux cit�s dans le menu ... \n\nRetour au menu principal\n");
	        break;
	    }
	  }

}
