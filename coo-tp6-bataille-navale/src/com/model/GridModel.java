package com.model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe correspondant au modèle de la grille
 * @author loick
 */

public class GridModel extends AbstractModel{

	protected int longueurX;
	protected int longueurY;
	
	
	protected CaseModel cases[][];
	protected BateauModel bateaux[];
	
	
	public static int nb_bateaux=0;
	
	
	protected boolean isIA[]; 
	private IA ia;

	private int nbBateauxTouches;

	/**
	 * Constructeur de la grille
	 * @param rows nombre de lignes
	 * @param longueurY nombre de colonnes
	 * @param nbJR nombre de jetons requis pour gagner
	 */
	public GridModel(int longueurX, int longueurY){
		
		this.longueurX=longueurX;
		this.longueurY=longueurY;
		this.bateaux=new BateauModel[Constantes.NB_BATEAUX];
		this.nbBateauxTouches=0;
		
		
		this.cases=new CaseModel[longueurX][longueurY];
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				cases[i][j]=new CaseModel();
			}
		}
		
		placerBateau(TypeBateau.BATEAU_2);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_4);
		placerBateau(TypeBateau.BATEAU_5);
		
		Random r=new Random();
		int cpt=0;
		while(cpt!=20){
			int posX=r.nextInt(longueurX);
			int posY=r.nextInt(longueurY);
			if(bomb(posX,posY))cpt++;
		}
		
		//ia= new IA(this);
	}

	public void placerBateau(TypeBateau t){
		int tailleBateau=TypeBateau.fromType(t);
		
		int positionX = 0,positionY = 0;
		Random r=new Random();
		
		Orientation orientation=Orientation.fromInteger(r.nextInt(2));
		
		boolean ok=false;
		while(!ok){
			if(orientation==Orientation.VERTICAL){
				positionX=r.nextInt(longueurX-tailleBateau);
				positionY=r.nextInt(longueurY);
				ok=true;
				for(int x=positionX; x<positionX+tailleBateau; x++){
					if(cases[x][positionY].idBateau!=-1)
						ok=false;		
				}
			}
			else if(orientation==Orientation.HORIZONTAL){
				positionX=r.nextInt(longueurX);
				positionY=r.nextInt(longueurY-tailleBateau);
				//checker positions libres
				ok=true;
				for(int y=positionY; y<positionY+tailleBateau; y++){
					if(cases[positionX][y].idBateau!=-1)
						ok=false;
				}
			}
		}
		
		bateaux[nb_bateaux]=new BateauModel(this,nb_bateaux,positionX,positionY,t,orientation);
		nb_bateaux++;
		
		
		
	}
	
	
	public boolean bomb(int x, int y){
		if(cases[x][y].v!=CaseValue.TOUCHE){
			
			int idB=cases[x][y].idBateau;
			cases[x][y].v=CaseValue.TOUCHE;
			if(idB!=-1){
				bateaux[idB].setNbTouches(bateaux[idB].getNbTouches()+1);
			}
			return true;
		}
		return false;
	}
	

	/**
	 * Reinitialiser la grille
	 */
	@Override
	public void reinit() {
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				cases[i][j].v=CaseValue.NONE;
			}
		}
		this.notifyReinit();
		this.ia=new IA(this);
		this.notifyTour(0);
	}

	
	
	/**
	 * Test de console, affiche le contenu de la grille
	 */
	public void afficher()
	{
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				int idB=cases[i][j].idBateau;
				if(idB!=-1)
					System.out.print(bateaux[idB].getType()+"("+cases[j][i].v+")"+"\t");
				else
					System.out.print(TypeBateau.NONE.name()+"("+cases[j][i].v+")"+"\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("dep");
		
		GridModel g=new GridModel(10,10);
		System.out.println("fin");
		g.afficher();
	}



	
}


