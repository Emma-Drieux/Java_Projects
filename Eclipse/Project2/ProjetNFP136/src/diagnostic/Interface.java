package diagnostic;



import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.*;

import javax.swing.SwingUtilities;

import arbreDecisionnel.Decisions;
import arbreDecisionnel.Parametre;
import arbreDecisionnel.Tree;
/**
 * 
 * @author Emma DRIEUX
 * Cette classe décrit l'interface du projet. Celle-ci va permettre d'accéder à une liste de fichiers
 * qu'on suppose appartenir à un groupe de patients. Une fois le dossier en question sélectionné, il sera systématiquement
 * chargé ce qui permettra de remplir les différents champs des paramètres de biochimie, coagulation et hématologiques.
 * Une fois celà réalisé, en cliquant sur le bouton "Analyser" qui permettra d'afficher le diagnostic ainsi que les examens complémentaires.
 */

public class Interface {
	
	//premier panel
	/*ce panel contiendra un monu déroulant qui présentera les différents fichiers des patients ainsi que le bouton Analyser*/
	private JLabel dossiers;
	private JComboBox menuDossiers; //liste déroulante des fichiers patients
	private JButton analyser;
	private JButton effacer;
	private JTextField hb, hpt, fer, gb,pcal,crp,prot,fib,na,k,cl,ra,u,cre,trop,ddi,asatIalat;
	private JLabel _hb, _hpt, _fer,_gb,_pcal,_crp,_prot,_fib,_na,_k,_cl,_ra,_u,_cre,_trop,_ddi,_asatIalat;
	private JLabel diagno;
	private JTextArea diag; JScrollPane scrollPane;
	
	private JLabel exam;
	private JTextArea examText; JScrollPane scrollPane2;
	
	private JLabel label1,label2,label3, label4, label5;
	Schema schema;

	
	public Interface(){
		dossiers=new JLabel("Dossiers");
		String[] tab={"Aucun","PatientA","PatientB"};
		schema=new Schema();
		menuDossiers=new JComboBox(tab);//initialiser la combobox à partir de la liste des dossiers
		menuDossiers.setEditable(false);//On ne peut choisir que ce qu'il y a dans la liste
		//menuDossiers.addActionListener(new chargerActionListener(this));//le choix dans la liste déclenche une action
		
		analyser=new JButton("Analyser");
		effacer=new JButton ("Effacer");
		
		
		label1=new JLabel("BILAN ANEMIQUE");
		_hb=new JLabel("Hb"); hb=new JTextField(4);
		_hpt=new JLabel("HPT"); hpt=new JTextField(4);
		_fer=new JLabel("FER"); fer=new JTextField(4);
		
		label2=new JLabel("BILAN INFLAMMATOIRE");
		_gb=new JLabel("GB"); gb=new JTextField(4);
		_pcal=new JLabel("PCAL"); pcal=new JTextField(4);
		_crp=new JLabel("CRP"); crp=new JTextField(4);
		
		label3=new JLabel("BILAN PROTEINES");
		_prot=new JLabel("PROT"); prot=new JTextField(4);
		_fib=new JLabel("FIB"); fib=new JTextField(4);
		
		label4=new JLabel("IONS ET ELECTROLITES");
		_na=new JLabel("NA"); na=new JTextField(4);
		_k=new JLabel("K"); k=new JTextField(4);
		_cl=new JLabel("CL"); cl=new JTextField(4);
		_ra=new JLabel("CO2"); ra=new JTextField(4);
		_u=new JLabel("UREE"); u=new JTextField(4);
		_cre=new JLabel("CRE"); cre=new JTextField(4);
		
		label5=new JLabel("AUTRES PARAMETRES");
		_trop=new JLabel("TROP"); trop=new JTextField(4); //explorer les fonctions cardiques
		_ddi=new JLabel("DDI"); ddi=new JTextField(4); //embolie pulmonaire
		_asatIalat=new JLabel("ASAT/ALAT"); asatIalat=new JTextField(4); //fonctions hépatiques
		
		
		
		diagno=new JLabel("Diagnostic");
		diag=new JTextArea(10,25);//(longueur,largeur)
		scrollPane=new JScrollPane(diag) ; 
		
		exam=new JLabel("Examens complémentaires");
		examText=new JTextArea(5,25);
		scrollPane2=new JScrollPane(examText) ; 
		menuDossiers.addActionListener(new ChoixMenu(this));
		analyser.addActionListener(new Analyser(this,this.schema));
		effacer.addActionListener(new Effacer(this));
		mettreEnPage();
	}
	
	/**
	 * Cette méthode permet de réaliser la mise en page de l'interface graphique
	 * Elle sera appelé au niveau du contructeur de la classe
	 */
	private void mettreEnPage() {
		JFrame frame=new JFrame();
	
		frame.setLayout(new GridBagLayout());
		
		GridBagConstraints haut=new GridBagConstraints();
		
		haut.gridx=0; haut.gridy=0;
		haut.gridwidth=1; haut.gridheight=1; //une seule cellule sera disponible pour ce composant
		haut.insets=new Insets(10,15,10,0); //marge à gauche=15; marge au dessus=10
		//Insets(margeSupérieure, margeGauche, margeInférieur, margeDroite)
		frame.add(dossiers,haut);
		
		haut.gridx=1; haut.gridy=0;
		haut.gridwidth=1; haut.gridheight=1; //une seule cellule sera disponible pour ce composant
		frame.add(menuDossiers,haut);
		
		haut.gridx=2; haut.gridy=0;
		haut.gridwidth=1; haut.gridheight=1; //une seule cellule sera disponible pour ce composant
		haut.insets=new Insets(10,15,10,10); //marge à gauche=15; marge au dessus=10
		frame.add(analyser,haut);
		
		haut.gridx=4; haut.gridy=0;
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1; //une seule cellule sera disponible pour ce composant
		haut.insets=new Insets(10,15,10,10); //marge à gauche=15; marge au dessus=10
		frame.add(effacer,haut);
		
		//Bilan anémie
		
		haut.gridx=0;haut.gridy=1;
		haut.insets=new Insets(50,15,0,0);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(label1,haut);

		haut.gridx=0; haut.gridy=2;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(_hb,haut);
		
		haut.gridx=1; haut.gridy=2;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(hb,haut);
		
		haut.gridx=0; haut.gridy=3;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(_hpt,haut);
		
		haut.gridx=1; haut.gridy=3;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(hpt,haut);
		
		haut.gridx=0; haut.gridy=4;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_fer,haut);
		
		haut.gridx=1; haut.gridy=4;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(fer,haut);
		
		//Bilan inflammatoire
		
		haut.gridx=0;haut.gridy=5;
		haut.insets=new Insets(30,15,0,0);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(label2,haut);
		
		haut.gridx=0; haut.gridy=6;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_gb,haut);
		
		haut.gridx=1; haut.gridy=6;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(gb,haut);
		
		haut.gridx=0; haut.gridy=7;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_pcal,haut);
		
		haut.gridx=1; haut.gridy=7;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(pcal,haut);
		
		haut.gridx=0; haut.gridy=8;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_crp,haut);
		
		haut.gridx=1; haut.gridy=8;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(crp,haut);
		
		//Protéines
		
		haut.gridx=0;haut.gridy=9;
		haut.insets=new Insets(30,15,0,0);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(label3,haut);
				
		haut.gridx=0; haut.gridy=10;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_prot,haut);
				
		haut.gridx=1; haut.gridy=10;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(prot,haut);
		
		haut.gridx=0; haut.gridy=11;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_fib,haut);
				
		haut.gridx=1; haut.gridy=11;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(fib,haut);
		
		
		//IONS et Electrolites
		
		haut.gridx=2;haut.gridy=1;
		haut.insets=new Insets(50,15,0,0);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(label4,haut);
						
		haut.gridx=2; haut.gridy=2;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_na,haut);
						
		haut.gridx=3; haut.gridy=2;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(na,haut);
		
		haut.gridx=2; haut.gridy=3;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_k,haut);
						
		haut.gridx=3; haut.gridy=3;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(k,haut);
		
		haut.gridx=2; haut.gridy=4;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_cl,haut);
						
		haut.gridx=3; haut.gridy=4;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(cl,haut);
		
		haut.gridx=2; haut.gridy=5;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_ra,haut);
						
		haut.gridx=3; haut.gridy=5;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(ra,haut);
		
		haut.gridx=2; haut.gridy=6;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_u,haut);
						
		haut.gridx=3; haut.gridy=6;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(u,haut);
		
		haut.gridx=2; haut.gridy=7;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_cre,haut);
						
		haut.gridx=3; haut.gridy=7;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(cre,haut);

		
		//Autres paramètres
		
		haut.gridx=2;haut.gridy=8;
		haut.insets=new Insets(50,15,0,0);
		haut.gridwidth=1;//dernier composant de la ligne 
		haut.gridheight=1;
		frame.add(label5,haut);
						
		haut.gridx=2; haut.gridy=9;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_trop,haut);
						
		haut.gridx=3; haut.gridy=9;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(trop,haut);
		
		haut.gridx=2; haut.gridy=10;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_ddi,haut);
						
		haut.gridx=3; haut.gridy=10;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(ddi,haut);
		
		haut.gridx=2; haut.gridy=11;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1; 
		haut.gridheight=1;
		frame.add(_asatIalat,haut);
						
		haut.gridx=3; haut.gridy=11;
		haut.insets=new Insets(10,15,10,10);
		haut.gridwidth=1;
		haut.gridheight=1;
		frame.add(asatIalat,haut);
	
	
		//..pareil pour le reste des paramètres si on aimerait en rajouter
		
		haut.gridx=4;haut.gridy=2; //placer le champs +Label Diagnostic
		haut.insets=new Insets(0,15,10,10);
		haut.gridwidth=GridBagConstraints.REMAINDER;//dernier composant de la ligne/colonne
		haut.gridheight=1;
		frame.add(diagno,haut);
		
		haut.gridx=4;haut.gridy=3; //placer le champs +Label Diagnostic
		haut.insets=new Insets(0,15,20,10);
		haut.gridwidth=2;
		haut.gridheight=4;
		frame.add(scrollPane,haut);
		
		haut.gridx=4;haut.gridy=7; //placer le champs +Label Diagnostic
		haut.insets=new Insets(0,15,10,10);
		haut.gridwidth=GridBagConstraints.REMAINDER;
		haut.gridheight=1;
		frame.add(exam,haut);
		
		haut.gridx=4;haut.gridy=8; //placer le champs +Label Diagnostic
		haut.insets=new Insets(0,15,20,10);
		haut.gridwidth=2;
		haut.gridheight=3;
		frame.add(scrollPane2,haut);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	

	/*
	 * <p>Cette classe permet de remplir les champs apres selection du fichier a etudier
	 */
	
	class ChoixMenu implements ActionListener{
		
		Interface interfaceG;
		double[] valParam;
		
		//Constructeur
		public ChoixMenu (Interface interfaceG){
				this.interfaceG=interfaceG;
			}
		@Override
		public void actionPerformed(ActionEvent e) {
			//ce tableau sera réinitialiser puisqu'il sera different en fonction du fichier selectionne
			remplir();
			diag.setText("");examText.setText("");//on initialise par l'occasion les champs TextArea diag et examText
		}
		
		/*
		 * 
		 */
		private void remplir() {
			double[] valParam=new double[17];
			String dossier=(String) menuDossiers.getSelectedItem(); //extraire le dossier selectionne dans la liste
			Fonctions.readFiles(dossier,valParam);
			//trop,ddi,asatIalat;
			hb.setText(""+valParam[0]);
			hpt.setText(""+valParam[1]);
			fer.setText(""+valParam[2]);
			gb.setText(""+valParam[3]);
			pcal.setText(""+valParam[4]);
			crp.setText(""+valParam[5]);
			prot.setText(""+valParam[6]);
			fib.setText(""+valParam[7]);
			na.setText(""+valParam[8]);
			k.setText(""+valParam[9]);
			cl.setText(""+valParam[10]);
			ra.setText(""+valParam[11]);
			u.setText(""+valParam[12]);
			cre.setText(""+valParam[13]);
			trop.setText(""+valParam[14]);
			ddi.setText(""+valParam[15]);
			asatIalat.setText(""+valParam[16]);
			
		}
	}
	
	class Analyser implements ActionListener{
		Interface interg;
		double[] valPar;
		Schema shema;
		
		public Analyser(Interface interg,Schema shema){
			interg=interg;
			valPar=new double[17];
			schema=schema;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			repriseVal(this.valPar);
			Hashtable<String, String> decisions=Fonctions.analyser(schema,valPar);
			Set<Map.Entry<String,String>> s=decisions.entrySet(); //permet de récupérer une collection de type Map.Entry<String,String>
			for (Map.Entry<String,String> asso : s) { 
				if(asso.getKey()=="decision") diag.setText(diag.getText()+asso.getValue());
				if(asso.getKey()=="examenCom") examText.setText(examText.getText()+asso.getValue());
			}
		}
		public void repriseVal(double[] valPar){
			valPar[0]=Double.parseDouble(hb.getText());
			valPar[1]=Double.parseDouble(hpt.getText());
			valPar[2]=Double.parseDouble(fer.getText());
			valPar[3]=Double.parseDouble(gb.getText());
			valPar[4]=Double.parseDouble(pcal.getText());
			valPar[5]=Double.parseDouble(crp.getText());
			valPar[6]=Double.parseDouble(prot.getText());
			valPar[7]=Double.parseDouble(fib.getText());
			valPar[8]=Double.parseDouble(na.getText());
			valPar[9]=Double.parseDouble(k.getText());
			valPar[10]=Double.parseDouble(cl.getText());
			valPar[11]=Double.parseDouble(ra.getText());
			valPar[12]=Double.parseDouble(u.getText());
			valPar[13]=Double.parseDouble(cre.getText());
			valPar[14]=Double.parseDouble(trop.getText());
			valPar[15]=Double.parseDouble(ddi.getText());
			valPar[16]=Double.parseDouble(asatIalat.getText());
		}
	}
	
	class Effacer implements ActionListener{
		
		Interface interfaceG;
		
		//Constructeur
		public Effacer(Interface interfaceG) {
			this.interfaceG = interfaceG;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			diag.setText("");examText.setText("");
			hb.setText("");hpt.setText("");fer.setText("");gb.setText("");
			pcal.setText("");crp.setText("");prot.setText("");fib.setText("");
			na.setText("");k.setText("");cl.setText("");ra.setText("");u.setText("");
			cre.setText("");trop.setText("");ddi.setText("");asatIalat.setText("");
			
		}
		
	}
	
}
