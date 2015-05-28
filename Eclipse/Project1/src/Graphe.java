


public class Graphe {
	
	public void miseAjourCase(int x, int y, TerrainA terrainA, int[][] init){
		
		if(terrainA.getPlateau()[x][y].getType()=="terre") {
			   if(terrainA.getPlateau()[x][y].nbElem()==0) init[x][y]=1;
			   else if(terrainA.getPlateau()[x][y].nbElem()==1) init[x][y]=10;
			   else init[x][y]=11;	   
		}
		if(terrainA.getPlateau()[x][y].getType()=="mer"){
			 if(terrainA.getPlateau()[x][y].getContPA()){
				 if (terrainA.getPlateau()[x][y].getPA().nbElem()==1) init[x][y]=15;
				 else if (terrainA.getPlateau()[x][y].getPA().nbElem()>1) init[x][y]=16;
				 else  init[x][y]=14;
			 }
		}
	    else init[x][y]=4;
	}
		
	public static void main(String[] a){
		Transition t=new Transition();
		int choix=0;
		
		t.declarerImages();
		

		TerrainA terrain= t.RemplirInitialA();
		
		t.getIGPA().definirTerrain(t.getInit());
		t.getIGPA().creerFenetre();
		
		int v = 0; int w=0; int x=0; int y=0; String nom=new String();
		
		for(int i=0;i<7;i++){//on réalise une partie avec 7 tours
			//Menu d'affichage: le premier menu propose d'afficher les avions qui sont dans les airs ou bien ceux qui sont sur terre ou à bord d'un porte avion
			char continuer='o';
			while(continuer=='o'){
				choix=Menus.ActionChoix();
				if(choix==1){ //deplacer un avion: cette avion doit être dans les airs
				   Terminal.ecrireStringln("\n ATENTION: vous ne pouvez déplacer qu'un avion qui est dans les airs, autrement, il faudra le faire décoller avant d'envisager à le déplacer.");
				   Terminal.ecrireStringln("\nEntrez les coordonnées de l'avion");
				   try{
					   Terminal.ecrireStringln("Coordonnees x: ");
					   v=Terminal.lireInt();
					   Terminal.ecrireStringln("Coordonnees y: ");
					   w=Terminal.lireInt();
					   Menus.rentrerCoord(12,11,v,w);
					   try{
						   terrain.getPlateau()[v][w].getConteneur().afficherAvionAirs();
						   
						   Terminal.ecrireStringln("Quel avion voulez-vous déplacer?");
						   nom=Terminal.lireString();
						   
						   int indAvion=terrain.getPlateau()[v][w].getConteneur().trouverIndAvion(nom);

						   Terminal.ecrireStringln("Vers quelle position voulez-vous le déplacer?");
						   try{
							   Terminal.ecrireStringln("Coordonnees x: ");
							   x=Terminal.lireInt();

						   
							   Terminal.ecrireStringln("Coordonnees y: ");
							   y=Terminal.lireInt();
						   
							   Menus.rentrerCoord(12,11,x,y);
						   
							   terrain.getPlateau()[x][y].ajouterAvion(terrain.getPlateau()[v][w].getConteneur().getList().get(indAvion));
						   
							   indAvion=terrain.getPlateau()[x][y].getConteneur().trouverIndAvion(nom);
							   terrain.getPlateau()[v][w].deleteAvion(terrain.getPlateau()[x][y].getConteneur().getList().get(indAvion));
						   
						   //on a changé le contenu de deux cases, donc ces deux cases seront modifiées
						   
						   //terrain.getPlateau()[v][w].getConteneur().afficherAvionAirs();
						   
						   
							   t.miseAjourCase(v, w, terrain);
							   t.miseAjourCase(x, y, terrain);
							   
							   
							   t.getIGPA().modifierCase(v,w,t.getInit()[v][w]);
							   t.getIGPA().modifierCase(x,y,t.getInit()[x][y]);
							   t.getIGPA().reafficher();
						   }catch(Coordonnees_invalides e){
							   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
						   } 	   
					   }catch(ListeVide lv){
						   Terminal.ecrireStringln("Aucun avion n'est dans les airs dans la case sélectionnées...");
					   }
				   }catch(Coordonnees_invalides e){
					   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
				   }  
				  }
				   if(choix==2){//faire atterrir un avion
					   try{
					   Terminal.ecrireStringln("\nEntrez les coordonnées de l'avion à faire atterir");
					   Terminal.ecrireStringln("Coordonnees x: ");
					   v=Terminal.lireInt();
						   
					   Terminal.ecrireStringln("Coordonnees y: ");
					   w=Terminal.lireInt();
					   Menus.rentrerCoord(12,11,v,w);   
						   try{
							   terrain.getPlateau()[v][w].getConteneur().afficherAvionAirs();
							   
							   Terminal.ecrireStringln("Quel avion voulez-vous faire atterrir?");
							   nom=Terminal.lireString();
							   // Erreur a tester: si l'avion est au dessus de la mer, il ne pourra pas atterir
							   
							   int indAvion=terrain.getPlateau()[v][w].getConteneur().trouverIndAvion(nom);
							   
							   try{
									   terrain.getPlateau()[v][w].getConteneur().getList().get(indAvion).atterrirAvion(terrain.getPlateau()[x][y]);
							   }catch(AtterrisageImpossible e){
								   Terminal.ecrireStringln("L'avion ne peut pas atterrir\nCelui-ci se trouve au dessus de la mer et il n'y de porte-avion sur lequel il pourrait se poser...\nModifier son emplacement puis faites-le atterrir\n");
							   }
							   
							   //aucun modification de l'affichage n'est nécessaire dans ce cas
							
						   }catch(ListeVide lv){
							   Terminal.ecrireStringln("Aucun avion n'est dans les airs dans la case sélectionnée...");
						   }
					   }catch(Coordonnees_invalides e){
						   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
					   }
				   }
				   
				   
				   if(choix==3){ //décoller un avion du sol
					   try{
						   Terminal.ecrireStringln("Entrez les coordonnées de la case qui contient l'avion à faire décoller");
						   Terminal.ecrireStringln("Coordonnees x: ");
						   x=Terminal.lireInt();
					   
						   Terminal.ecrireStringln("Coordonnees y: ");
						   y=Terminal.lireInt();
						   
						   Menus.rentrerCoord(12,11,x,y);
						   
						   try{
					   
							   terrain.getPlateau()[x][y].getConteneur().afficherAvionPoses();
					   
							   Terminal.ecrireStringln("Quel avion voulez-vous faire décoller?");
							   nom=Terminal.lireString();
							   int indAvion=terrain.getPlateau()[x][y].getConteneur().trouverIndAvion(nom);
					   
							   terrain.getPlateau()[x][y].getConteneur().getList().get(indAvion).setEtat(true);
						   }catch(ListeVide e){
							   Terminal.ecrireStringln("Il n'y a aucun avion posé au niveau de la case séléctionnée");
						   }
					   }catch(Coordonnees_invalides e){
						   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
					   }
				   }
				   
				   if(choix==4){ //décoller un avion d'un porte-avion
					   try{
						   Terminal.ecrireStringln("Entrez les coordonnées du porte-Avion");
						   Terminal.ecrireStringln("Coordonnees x: ");
						   x=Terminal.lireInt();
					   
						   Terminal.ecrireStringln("Coordonnees y: ");
						   y=Terminal.lireInt();
						   
						   Menus.rentrerCoord(12,11,x,y);  
					   
						   nom=terrain.getPlateau()[x][y].getPA().getNom();
						   if(!terrain.getPlateau()[x][y].getContPA()){
							   Terminal.ecrireStringln("Il n'y a pas de porte-avion au niveau de la case séléctionnée");
							   continue;
						   }
						   try{
							   Terminal.ecrireStringln("Vous avez séléctionné le porte-avion "+nom+" ; liste des avions à bord: ");
							   terrain.getPlateau()[x][y].getPA().afficherAvion();
							   Terminal.ecrireStringln("Quel avion voulez-vous faire décoller?");
							   String nomAvion=Terminal.lireString();
							   int indAvion=terrain.getPlateau()[x][y].getPA().trouverIndAvion(nom);
						   
						   //pour le décollage, il n'y a aucune erreur à tester, il faudra juste le passer dans Conteneur et changer l'état de l'avion
						   
							   terrain.getPlateau()[x][y].getConteneur().add(terrain.getPlateau()[x][y].getPA().getList().get(indAvion)); //mettre l'avion dans Conteneur de la case
						   //j'ai utilisé la méthode add de la Classe Conteneur puisque celle de Case effectue des teste sur le nombre d'avion chose qui nous interresse pas dans ce cas
							   terrain.getPlateau()[x][y].getPA().getList().get(indAvion).setEtat(true);// changer l'état de l'avion qui sera dans les airs
							   terrain.getPlateau()[v][w].getPA().remove(terrain.getPlateau()[x][y].getConteneur().getList().get(indAvion)); //supprimer l'avion du porte-avion
						   
						   //il n'y aura rien à modifier dans l'affichage
						   
						   }catch(ListeVide lv){
							   Terminal.ecrireStringln("Il n'y a pas d'avions sur le porte-avion séléctionné...");
						   }
					   }catch(Coordonnees_invalides e){
						   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
					   }
				   }
				   if(choix==5){//Deplacer un porte-avion
					   try{
						   Terminal.ecrireStringln("\nEntrez les coordonnées du porte-avion à déplacer");
						   Terminal.ecrireStringln("Coordonnees x: ");
						   v=Terminal.lireInt();
					   
						   Terminal.ecrireStringln("Coordonnees y: ");
						   w=Terminal.lireInt();
					   
						   Menus.rentrerCoord(12,11,v,w);
						   
						   if(!terrain.getPlateau()[v][w].getContPA()){
							   Terminal.ecrireStringln("Il n'y a pas de porte-avion au niveau de la case séléctionnée"); continue;
						   }
						   try{
							   Terminal.ecrireStringln("\nEntrez ses nouvelles coordonnées");
							   Terminal.ecrireStringln("Coordonnees x: ");
							   x=Terminal.lireInt();
					   
							   Terminal.ecrireStringln("Coordonnees y: ");
							   y=Terminal.lireInt();
							   Menus.rentrerCoord(12,11,x,y);
							   
							   if(terrain.getPlateau()[x][y].getType()=="terre"){
								   Terminal.ecrireStringln("Erreur!!!! vous déplacez le porte-avion vers une case - terre- ... déplacement impossible"); continue;
							   }
					   //rajouter le porte avion à la case(x,y): 
					   /////// ******** probleme: vérifier s'il y a un porte-avion
					   //changer d'abord les coordonnées du porte-avion
							   terrain.getPlateau()[v][w].getPA().getCoordonnees().setX(x);terrain.getPlateau()[v][w].getPA().getCoordonnees().setY(y);
					   
							   terrain.getPlateau()[x][y].setPA(terrain.getPlateau()[v][w].getPA()); terrain.getPlateau()[x][y].setContPA(true);
					   //supprimer le porte-avion de la case (v,w): mettre un porte-avion null et la variable contientPA à false
							   terrain.getPlateau()[v][w].setPA(new PorteAvion()); terrain.getPlateau()[v][w].setContPA(false);
					  
					  
							   t.miseAjourCase(x, y, terrain);
							   t.miseAjourCase(v, w, terrain);
						
							   t.getIGPA().modifierCase(x,y,t.getInit()[x][y]);
							   t.getIGPA().modifierCase(v,w,t.getInit()[v][w]);
					   
							   t.getIGPA().reafficher();
						   }catch(Coordonnees_invalides e){
							   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
						   }
					   }catch(Coordonnees_invalides e){
						   Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
					   }
				}
				   
				   if(choix==6){ //Afficher les avions dans les airs d'une case
					   try{
						   Terminal.ecrireStringln("\nEntrez les coordonnées qui vous interessent");
						   Terminal.ecrireStringln("Coordonnees x: ");
						   x=Terminal.lireInt();
					   
						   Terminal.ecrireStringln("Coordonnees y: ");
						   y=Terminal.lireInt();
						   Terminal.sautDeLigne();
						   
						   Menus.rentrerCoord(12,11,x,y);
						   
						   Terminal.ecrireStringln("***************************************************************************************************");
						   try{
							   terrain.getPlateau()[x][y].getConteneur().afficherAvionAirs();
					   		}catch(ListeVide lv){
					   			Terminal.ecrireStringln("Aucun avion n'est dans les airs dans la case sélectionnée...");
					   		}finally{ //sera exécutée quelque soit le cas
					   			Terminal.sautDeLigne();
					   		}
					   //les avions posés
						   try{
							   terrain.getPlateau()[x][y].getConteneur().afficherAvionPoses();
						   }catch(ListeVide lv){
							   Terminal.ecrireStringln("Aucun avion ne s'est posé dans la case sélectionnée...");
						   }finally{
							   Terminal.sautDeLigne();
						   }
					   
						   if(terrain.getPlateau()[x][y].getContPA()){
							   Terminal.ecrireStringln("Il existe un porte avion au niveau de cette case. Il contient à son bord:");
							   terrain.getPlateau()[x][y].getPA().afficherAvionPoses();
							   Terminal.sautDeLigne();
						   }
						   Terminal.ecrireStringln("***************************************************************************************************");
						   Terminal.sautDeLigne();
					   }catch(Coordonnees_invalides e){
							  Terminal.ecrireString("Les coordonnées rentrées sont invalides.");
						}
				   }
				   
				   
				   Terminal.ecrireStringln("Voulez-vous continuer? taper o sinon n");
				   continuer=Terminal.lireChar();
				}
			//mise à jour du graphique: déplacer les porte-avion ds une direction donnée d'une case, les avions de 3 cases
			t.miseAjourTerrain(terrain);
			}
		}
}
	