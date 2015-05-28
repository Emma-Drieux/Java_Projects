package diagnostic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/*
 * Cette classe définit les fonctions employées par l'interface (selection d'un fichier, appuyer sur le bouton analyse ou effacer
 * @autor Emma DRIEUX
 */

public class Fonctions {
	
	/*
	 * <p>Cette fonction permet de remplir le tableau d'entiers fourni en argument. Elle lit un fichier fourni en parametre
	 * et retour ce tableau avec les différentes valeur des parametres</p>
	 * 
	 * @param nomFichier
	 * @param parametres
	 * @throws IOException
	 */
	
	public static void readFiles(String nomFichier,double[] parametres){
		
		/*
		 * Voici comment s'organise le tableau des parametres
		 * [Hb][[HPT][FER][GB][PCAL][CRP][PROT][FIB][NA][K][CL][RA][UREE][CREE][TROP][DDI][ASAT|ALAT]
		 * Donc parametre[0] donne la valeur de l'hémoglobine. Si celle-ci est à 0, cela signifie qu'il n'y a pas de valeur pour ce parametre dans le compte-rendu des analyses
		 */
		
		try{ 
		     BufferedReader buff = new BufferedReader(new FileReader(nomFichier+".txt"));
		     try {
		          String line; //on garde en mémoire la ligne en cours
		          while ((line = buff.readLine()) != null){
		               String word[] = line.split("\t\t"); //la methode split permet de découper la ligne en plusieurs chaines de caractères en fonction du délimiteur "\t\t" par exemple ;
		               try{
		            		remplissage(parametres,word[0],word[1]);
		            	}catch(PasDIndice e){
		            		System.out.println("Le paramètre "+word[0]+" du fichier "+nomFichier+ " n'existe pas.\nPensez à le rajouter si nécessaire.");
		            	}
		          }
		  
		      } finally {
		              buff.close();
		        }
		      } catch (IOException ioe) { // erreur de fermeture des flux
		           System.out.println("Erreur --" + ioe.toString());
		        }

	}
	
	/*
	 * <p>Cette fonction sera utilisee pour remplir le tableau des parametres avec un element fourni en argument
	 * Le nom du parametre permet de definir la position</p>
	 * @param parametres
	 * @param nom
	 * @param para
	 * @throws PasDIndice
	 */
	private static void remplissage(double[] parametres,String nom, String para) {
		//parcourir le tableau nomsPara pour trouver l'indice de para
		int indice=parcourir(nom,0);
		if(indice==-1) throw new PasDIndice();
		else parametres[indice]=Double.parseDouble(para);
	}

	/*
	 * Fonction qui permet de lancer l'analyse pour obtenir le diagnostic adequat
	 * 
	 * @param 
	 * @return int
	 */
	
	public static int parcourir(String nom,int i) {
		/*
		 * <p>Cette methode permet de parcourir le tableau nomsPara de maniere recursive et de retourner l'indice de la premiere occurence de para
		 * i est l'indice de debut qui permetera de parcourir l'ensemble du tableau</p>
		 * 
		 * @param nomsPara
		 * @param para
		 */
		String[] nomsPara={"Hb","HPT","FER","GB","PCAL","CRP","PROT","FIB","NA","K","CL","RA","UREE","CRE","TROP","DDI","ASAT/ALAT"};
		if(i==nomsPara.length-1) return -1;
		if(nomsPara[i].equals(nom)) return i;
		else return parcourir(nom,i+1);
	}

	public static Hashtable<String,String> analyser (Schema schema,double[] parametres){
		Hashtable<String,String> diagnostic=schema.parcoursSchema(parametres);
		return diagnostic;
	}
	

}

class PasDIndice extends Error{}
