
public class Menus {
	
	public static int ActionChoix(){
		int choix=0;
		Terminal.ecrireStringln("_________________________________ Projet NFA032 _________________________________");
		Terminal.ecrireStringln("Bienvenue dans ce jeu qui simule le déplacement d'avions et de porte-avion");
		Terminal.ecrireStringln("********************************************************************************\n\n");
		Terminal.ecrireStringln("Entrez votre choix parmis les propositions suivantes: ");
		Terminal.ecrireStringln("1 - Deplacer un avion;");
		Terminal.ecrireStringln("2 - Faire atterrir un avion;");
		Terminal.ecrireStringln("3 - Décoller un avion posé au sol;");
		Terminal.ecrireStringln("4 - Decoller un avion d'un porte-avion;");
		Terminal.ecrireStringln("5 - Deplacer un porte avion;");
		Terminal.ecrireStringln("6 - Afficher les avions d'une case;");
		Terminal.ecrireStringln("Autre - Ne rien faire / passer au tour suivant;");
		choix=Terminal.lireInt();
		return choix;
	}
	
	public static void rentrerCoord(int dimA, int dimB,int x, int y){
		if(x>dimA) throw new Coordonnees_invalides();
		if(y>dimB) throw new Coordonnees_invalides();
	}
}

class  Coordonnees_invalides extends Error{}