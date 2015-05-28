package diagnostic;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.SwingUtilities;

import arbreDecisionnel.Decisions;
import arbreDecisionnel.Parametre;
import arbreDecisionnel.Tree;

public class Schema {
	Tree Kdim,RA,RAD, RAutres;
	Tree Ddi, CrpN,CrpND;
	Tree Kaug, Trop,TropD, Uree,Cre,CreD,autres;
	Tree Prot, AsAl,AsAlD,AsAlExam;
	Tree Gb,Pcal,Crp, PcalD, CrpD,CrpExam;
	Tree Hb,Fer, FerD, Hpt,HptD, Fib, FibD, FibExam;
	
	private ArrayList<Tree> liste;
	
	public Schema(){
		//Arbre 5: Kdim racine
		Kdim=new Tree(new Parametre("K",3.5,1),null,null);
		RAD=new Tree(new Parametre("decision","Alcalose métabolique.\n"),null,null);
		RAutres=new Tree(new Parametre("decision","Hypokaliémie d'origine inconnue.\n"),null,null);
		RA=new Tree(new Parametre("RA",-30,-1),RAD,RAutres);
		Kdim=new Tree(new Parametre("K",3.5,1),RA,null);
						
		//Arbre 6: Racine Ddi
		CrpND=new Tree(new Parametre("decision","Suspicion d'embolie pulmonaire/thrombose veineuse profonde.\n"),null,null);
		CrpN=new Tree(new Parametre("CRP",50,1),CrpND, null); //Crp normale si <50
		Ddi=new Tree(new Parametre("DDI",-500,-1),CrpN,Kdim);
						
		//Arbre 4: racine est Kaug
		TropD=new Tree(new Parametre("decision","Troubles cardiques; réaliser un ECG.\n"),null,null);
		CreD=new Tree(new Parametre("decision","Insuffisance rénale; réaliser un bilan ions+electrolites sanguins.\n"),null, null);
		autres=new Tree(new Parametre("decision","Possible apport en potassium; vérifier cette hypothèse.\n"),null,null);
		Cre=new Tree(new Parametre("CRE",-250,-1),CreD,autres);
		Uree=new Tree(new Parametre("UREE",-9,-1),Cre,autres);
		Trop=new Tree(new Parametre("TROP",-15,-1),TropD,Uree);
		Kaug=new Tree(new Parametre("K",-5,-1),Trop,Ddi);
						
		//Arbre 3: Racine est PROT
		AsAlD=new Tree(new Parametre("decision","Insuffisance héptique: pour en déterminer l'origine,rajouter la GGT+PAL+BILT+TP+Plaquettes.\n"),null,null);
		AsAlExam=new Tree(new Parametre("examenCom","Explorer les fonction rénales:\nDosage de protéinurie, albuminémie et calcémie + électophorèse des protéines sériques.\n(Hypothèse à vérifier:Syndrôme néphrotique).\n"),null,null);
		AsAl=new Tree(new Parametre("ASAT/ALAT",-50,-1),AsAlD,AsAlExam);
		Prot=new Tree(new Parametre("PROT",50,1),AsAl,Kaug);
				
		//Arbre 2: racine est GB
		PcalD=new Tree(new Parametre("decision","Infection d'origine bactérienne; réaliser une hémoculture.\n"),null,null);
		CrpD=new Tree(new Parametre("decision","Inflammation d'origine non bactérienne (physiologique: rhomatisme ou autre).\n"),null,null);
		CrpExam=new Tree(new Parametre("examenCom","Réaliser un bilan de marqueurs tumoraux.\n"),null,null);
		Crp=new Tree(new Parametre("CRP",-50,-1),CrpD,CrpExam);
		Pcal=new Tree(new Parametre("PCAL",-10,-1),PcalD,Crp);
		Gb=new Tree(new Parametre("GB",-12,-1),Pcal,Prot);
						
		//Arbre 1: racine Hb
		FibExam=new Tree(new Parametre("examenCom","Anemie d'origine inconnue; dosage de la transferine+ferritine.\n"),null,null);
		FibD=new Tree(new Parametre("decision","Insuffisance médulaire.\n"),null,null);
		Fib=new Tree(new Parametre("FIB",3,1),FibD,FibExam);
		HptD=new Tree(new Parametre("decision","Hémolyse intravasculaire.\n"),null,null);
		Hpt=new Tree(new Parametre("HPT",0.5,1),HptD,Fib);
		FerD=new Tree(new Parametre("decision","Anémie ferriprive ou anémie inflamatoire.\n"),null, null);
		Fer=new Tree(new Parametre("FER",10,1),FerD,Hpt);
		Hb=new Tree(new Parametre("Hb",10,1),Fer,Gb);
				
				//La liste:
		liste=new ArrayList<Tree>();
		liste.add(Hb);liste.add(Gb);liste.add(Prot);liste.add(Kaug);liste.add(Ddi);liste.add(Kdim);
	}
	//geteur
	public ArrayList<Tree> getListe() {return liste;}
	
	public Hashtable<String,String> parcoursSchema(double[] valPar){
		//Le parcours s'effectue d'abord sur la liste puis dans un second plan en explorant les sous arbre de maniere récursive
		Tree replique;  //copie de la tete de liste qui permetra de parcourir la liste
		Hashtable<String,String> decisions=new Hashtable <String,String>() ;
		
		int index;
		int position;
		
		for(int i=0;i<liste.size();i++){
			index=Fonctions.parcourir(liste.get(i).getRoot().getNom(),0);
			if(valPar[index]==0) continue;
			replique=liste.get(i);
			while(replique.getFd()!=null && replique.getFg()!=null){ //tant qu'on n'est pas arrivé a une feuille
				position=Fonctions.parcourir(replique.getRoot().getNom(),0);
				if(valPar[position]*replique.getRoot().getSigne()<replique.getRoot().getValInf()){
					replique=replique.getFg();
				}
				else{
					replique=replique.getFd(); System.out.println("O.K.");
				}
			}
			//decisions.ajouter(replique.getRoot().getExamComp(), replique.getRoot().getType());
			decisions.put(replique.getRoot().getType(),replique.getRoot().getExamComp());
			//System.out.println(replique.getRoot().getExamComp());
			//System.out.println(replique.getRoot().getType());
		}
		return decisions;
	}
	
}
