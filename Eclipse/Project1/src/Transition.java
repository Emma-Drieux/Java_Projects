import java.util.Random;

public class Transition {
//cette classe permet de faire la transition entre le plateau (forme abstraite) vers une image type IGPA
	private IGPA igpa;
	private int[][]init;
	
	//constructeur1
	public Transition(){
		this.igpa=new IGPA(13,12);
	}
	
	//geteur
	public IGPA getIGPA(){return this.igpa;}
	public int[][] getInit(){return this.init;}
	
	public void declarerImages(){
		//***** Avions rouges ******
		///Sur terre: sable: terre; are: un seul avion; asrt: plusieurs avions
		this.igpa.declarerImage(1,"sable.png");
		this.igpa.declarerImage(2,"art.png");
		this.igpa.declarerImage(3,"asrt.png");
				
		///Sur mer: eau: mer; are: un seul avion; asre: plusieurs avions
		this.igpa.declarerImage(4,"eau.png");
		this.igpa.declarerImage(5,"are.png");
		this.igpa.declarerImage(6,"asre.png");
				
		///Porte-avion: seul: paje; paae: avec un avion; paase: avec plusieurs avions a bord
		this.igpa.declarerImage(7,"paje.png");
		this.igpa.declarerImage(8,"paae.png");
		this.igpa.declarerImage(9,"paase.png");
		
		
		//*****Avions noirs *******
		///Sur terre: 
		this.igpa.declarerImage(10,"avion-terre.png");
		this.igpa.declarerImage(11,"avions-terre.png");
		
		///Sur mer:
		this.igpa.declarerImage(12,"avion-eau.png");
		this.igpa.declarerImage(13,"avions-eau.png");
		
		///Porte-avion:
		this.igpa.declarerImage(14,"porte-avion-eau.png");
		this.igpa.declarerImage(15,"porte-avion-avion-eau.png");
		this.igpa.declarerImage(16,"porte-avion-avions-eau.png");
		
		
	}
	
	
	public TerrainA RemplirInitialA(){
		TerrainA terrainA=new TerrainA();
		init=new int[13][12];
		terrainA.remplissageCases();
		for (int i=0; i<13;i++){
			for(int j=0;j<12;j++){
			   if(terrainA.getPlateau()[i][j].getType()=="terre") {
				   if(terrainA.getPlateau()[i][j].nbElem()==0) init[i][j]=1;
				   else if(terrainA.getPlateau()[i][j].nbElem()==1) init[i][j]=10;
				   else init[i][j]=11;
			   }

			   if(terrainA.getPlateau()[i][j].getType()=="mer"){
				   if(terrainA.getPlateau()[i][j].getContPA()){
					   if (terrainA.getPlateau()[i][j].getPA().nbElem()==1) init[i][j]=15;
					   else if (terrainA.getPlateau()[i][j].getPA().nbElem()>1) init[i][j]=16;
					   else  init[i][j]=14;
					}
				   else init[i][j]=4;
				 }
			   }
			}
		return terrainA;
		}
		//mise à jour d'une case d'un terrain
		public void miseAjourCase(int x, int y, TerrainA terrainA){
			
			if(terrainA.getPlateau()[x][y].getType()=="terre") {
				   if(terrainA.getPlateau()[x][y].nbElem()==0) this.init[x][y]=1;
				   else if(terrainA.getPlateau()[x][y].nbElem()==1) this.init[x][y]=10;
				   else if (terrainA.getPlateau()[x][y].nbElem()>1)this.init[x][y]=11;	   
			}
			if(terrainA.getPlateau()[x][y].getType()=="mer"){
				 if(terrainA.getPlateau()[x][y].getContPA()){ //si la case contient un porte-avion
					 if (terrainA.getPlateau()[x][y].nbElem()==1) this.init[x][y]=15;
					 else if (terrainA.getPlateau()[x][y].nbElem()>1) this.init[x][y]=16;
					 else  this.init[x][y]=14;
				 }
				 
				 if(!terrainA.getPlateau()[x][y].getContPA()){ //si la case ne contient pas de porte-avion
					 if (terrainA.getPlateau()[x][y].getConteneur().nbElem()==1) this.init[x][y]=12;
					 else if (terrainA.getPlateau()[x][y].getConteneur().nbElem()>1) this.init[x][y]=13;
					 else this.init[x][y]=4;
				 }	
			}
		}
		
		public void miseAjourTerrain(TerrainA terrain){
			//on suppose que les avions se déplacent de 3 cases à chaque tour; les portes-avions d'une case
			Random aleaX=new Random() ; Random aleaY=new Random() ;
			int dirX=0; int dirY=0;
			int tempX; int tempY;
			int indAvion;
			//seuls le déplacement des avions et des porte-avions nous interesse
			for (int i=0;i<terrain.getListeAvions().size();i++){ 
				int x=terrain.getListeAvions().get(i).getCordonnees().getX(); //(x,y) représente les coordonnées de l'avion
				int y=terrain.getListeAvions().get(i).getCordonnees().getY();

				//un avion se déplace 8 x plus qu'un porte-avion
				dirX=aleaX.nextInt(17)-8 ; dirY=aleaY.nextInt(17)-8 ; //on génère aléatoirement un nombre ={-8 ... +8} qu'on réajutera en fonction des cas
				
				//!!! si l'avion est sur un porte avion, il n'y a aucun interet a le déplacer puisqu'il se déplace avec le porte avion
				if(x==0) dirX=aleaX.nextInt(9);
				if(y==0) dirY=aleaY.nextInt(9);
				if(y==11) dirY=aleaY.nextInt(9)-8;
				if(x==11) dirX=aleaX.nextInt(9)-8;
				
				//!!!vérifier si ça respecte les dimenssions
				if(x+dirX>12) dirX=dirX-12;
				if(y+dirY>11) dirY=dirY-11;
				
				if(x+dirX<0) dirX=dirX+12;
				if(y+dirY<0) dirY=dirY+11;
				
				//tester si en déplaçant un avion, on ne tombe pas sur une case sur laquelle il ne peut pas se mettre; si c'est le cas on remet dirX et dirY à 0
				if((terrain.getPlateau()[x+dirX][y+dirY].getType()=="mer") && (!terrain.getPlateau()[x+dirX][y+dirY].getContPA()) && (! terrain.getListeAvions().get(i).getEtat())){
					dirX=0;dirY=0;
					//terrain.getPlateau()[terrain.getListeAvions().get(i).getCordonnees().getX()+]=terrain.getPlateau()[terrain.getListeAvions().get(i).getCordonnees().getX()][terrain.getListeAvions().get(i).getCordonnees().getY()].getConteneur().trouverIndAvion(terrain.getListeAvions().get(i).getNom());		
				}
				
				//si l'avion est sur un porte-avion, il ne faut pas le déplacer puisqu'il se déplace avec le porte avion
				if((terrain.getPlateau()[x][y].getContPA()) && (! terrain.getListeAvions().get(i).getEtat())) {dirX=0;dirY=0;}

				//rajouter l'avion qq soit X ... il restera sur place sinon
				//System.out.println(x+"  "+y+"   "+(x+dirX)+"   "+(y+dirY));
				if(dirX!=0 && dirY!=0 && terrain.getPlateau()[x+dirX][y+dirY].nbElem()<=4){// s'il y a un réel déplacement et un nombre d'avion de la case destination <=4 alors ...
					//supprimer l'avion de la liste de l'ancienne case et le le mettre dans la nouvelle case !!! il faudra modifier les coordonnées de l'avion entre les deux
					terrain.getPlateau()[x][y].getConteneur().getList().remove(terrain.getListeAvions().get(i));
					terrain.getListeAvions().get(i).getCordonnees().setX(x+dirX);terrain.getListeAvions().get(i).getCordonnees().setY(y+dirY);
					terrain.getPlateau()[x+dirX][y+dirY].getConteneur().add(terrain.getListeAvions().get(i));
					
					this.miseAjourCase(x, y, terrain);
					this.miseAjourCase(x+dirX,y+dirY,terrain);
					
					this.getIGPA().modifierCase(x,y,this.getInit()[x][y]);
					this.getIGPA().modifierCase(x+dirX,y+dirY,this.getInit()[x+dirX][y+dirY]);
					this.getIGPA().reafficher();
				}
			}
			
			//mise à jour porte-avions
			for(int i=0;i<terrain.getListPorteAvion().size();i++){
				//un porte avion se déplace d'une case... lorsqu'il se déplace, l'ensemble des avions a bord changent aussi de coordonnées
				dirX=aleaX.nextInt(3)-1 ; dirY=aleaY.nextInt(3)-1 ;
				int x=terrain.getListPorteAvion().get(i).getCoordonnees().getX();
				int y=terrain.getListPorteAvion().get(i).getCoordonnees().getY();
				
				if(x==0) dirX=aleaX.nextInt(2);
				if(y==0) dirY=aleaY.nextInt(2);
				if(y==11) dirY=aleaY.nextInt(2)-1;
				if(x==11) dirX=aleaX.nextInt(2)-1;
				
				//un porte-avion reste en mer
				if(terrain.getPlateau()[x+dirX][y+dirY].getType()=="terre"){ dirX=0;dirY=0;}
				
				//mise à jour des coordonnees des avions à bord et celle du porte-avion si déplacement !!!! la case destination peut déjà contenir des avion, donc vérifier si on dépasse le nombre d'éléments autorisé, en d'autre termes 5
				if(dirX!=0 && dirY!=0 && (terrain.getPlateau()[x+dirX][y+dirY].nbElem()+terrain.getListPorteAvion().get(i).nbElem())<=5){
					//changer les coordonnées de l'ensemble des avions à bord
					for(int j=0;j<terrain.getListPorteAvion().get(i).getList().size();j++){
						terrain.getListPorteAvion().get(i).getList().get(j).getCordonnees().setX(x+dirX);
						terrain.getListPorteAvion().get(i).getList().get(j).getCordonnees().setY(y+dirY);
					}
					//changer les coordonnées du porte-avion
					terrain.getListPorteAvion().get(i).getCoordonnees().setX(x+dirX);
					terrain.getListPorteAvion().get(i).getCoordonnees().setY(y+dirY);
					
					//associer le porte-avion à la case destination
					terrain.getPlateau()[x+dirX][y+dirY].setPA(terrain.getListPorteAvion().get(i)); terrain.getPlateau()[x+dirX][y+dirY].setContPA(true);
					//supprimer le porte-avion de la case (x,y): mettre un porte-avion null et la variable contientPA à false
					terrain.getPlateau()[x][y].setPA(new PorteAvion()); terrain.getPlateau()[x][y].setContPA(false);
					
					this.miseAjourCase(x, y, terrain);
					this.miseAjourCase(x+dirX,y+dirY,terrain);
					
					this.getIGPA().modifierCase(x,y,this.getInit()[x][y]);
					this.getIGPA().modifierCase(x+dirX,y+dirY,this.getInit()[x+dirX][y+dirY]);
					this.getIGPA().reafficher();
				}
			}
		}
	}


