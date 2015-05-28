/**
 * Cette classe permet d'éditer la facture finale sous format .txt
 * 
 * @author Emma DRIEUX 
 * @version Dernière modification le 10/01/14 à 14h15
 */

import java.io.*;

public class Bill {
	//cette classe permet de creer le fichier qui representera la facture finale au meme format que celui presenté sur le projet
	  public static void creationFacture(String[]sections,int[] ln, int[] nbLignes, String[][]lignes, double[][] prix ,double[] sousTotal, double total){
	    
	    File f=new File("Facture.txt");
	  try{
	    PrintWriter fw= new PrintWriter(new FileWriter(f)); 
	    for(int i=0;i<ln[0];i++){
	      fw.println(sections[i]);
	      for(int j=0;j<nbLignes[i];j++){
	        fw.print("                               ");
	        Affichage.espacesFichier(fw,lignes[i][j]);
	        fw.println(prix[i][j]);
	      }
	      fw.print("                               ");
	      Affichage.espacesFichier(fw,"");
	      fw.print("________\n                               ");
	      Affichage.espacesFichier(fw,"");
	      fw.println(sousTotal[i]);
	    }
	    fw.print("\n                               ");; //saut de ligne avant d'afficher le total
	    Affichage.espacesFichier(fw,"Net à payer");
	    fw.print(total);
	    fw.close();
	  }catch(IOException exception){
	    Terminal.ecrireStringln("Erreur"+exception.getMessage());
	  }
	 }

}
