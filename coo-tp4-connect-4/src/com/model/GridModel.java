package com.model;

import java.util.ArrayList;

/**
 * Classe correspondant au modèle de la grille
 * @author loick
 */

public class GridModel extends AbstractModel{

	protected int rows;
	protected int cols;
	
	protected CaseModel cases[][];
	protected boolean isIA[]; 
	private IA ia;

	protected int nbJoueur;
	
	private int nbJetons;
	private int nbJetonsRequis;
	
	protected int tour;

	/**
	 * Constructeur de la grille
	 * @param rows nombre de lignes
	 * @param cols nombre de colonnes
	 * @param nbJR nombre de jetons requis pour gagner
	 */
	public GridModel(int rows, int cols, int nbJR, boolean isIA[]){
		
		this.nbJoueur=2;
		this.rows=rows;
		this.cols=cols;
		this.tour=0;
		this.nbJetons=0;
		this.nbJetonsRequis=nbJR;
		this.isIA=isIA;
		
		this.cases=new CaseModel[rows][cols];
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cases[i][j]=new CaseModel();
			}
		}	
		ia= new IA(this);
	}


	/**
	 * Reinitialiser la grille
	 */
	@Override
	public void reinit() {
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cases[i][j].v=CaseValue.NONE;
			}
		}
		this.notifyReinit();
		System.out.println("ok");
		this.ia=new IA(this);
	}

	
	/**
	 * Teste si la grille est pleine ou non
	 * @return
	 */
	public boolean isFull()
	{
		return nbJetons==rows*cols;
	}
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une ligne d'ordonnée y
	 * @param y ligne à tester
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueLigne(int y)
	{
		int res[]=new int[2];
		int max=0, maxtmp=0;
		int xdeb=0,xmax=0;
		CaseValue val=cases[0][y].v;
		for(int x=0; x<rows;x++){
			//change le max
			if(val!=cases[x][y].v){
				if(max<maxtmp){
					max=maxtmp;
					xmax=x-max;
				}
				maxtmp=0; xdeb=x;
				val=cases[x][y].v;
			}
			//incremente
			if(cases[x][y].v!=CaseValue.NONE)
				maxtmp++;
			
				
		}
		if(max<maxtmp){
			max=maxtmp;
			xmax=rows-max;
		}
		res[0]=max;res[1]=xmax;
		return res;
	}
	
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une colonne d'ordonnée x
	 * @param x colonne à tester
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueCol(int x)
	{
		int res[]=new int[2];
		int max=0, maxtmp=0;
		int ydeb=0,ymax=0;
		CaseValue val=cases[x][0].v;
		for(int y=0; y<cols;y++){
			//change le max
			if(val!=cases[x][y].v){
				if(max<maxtmp)
				{
					max=maxtmp;
					ymax=y-max;
				}
				maxtmp=0;
				val=cases[x][y].v;
			}
			//incremente
			if(cases[x][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
		{
			max=maxtmp;
			ymax=cols-max;
		}
		//System.out.println("colonne début "+x + " "+ydeb);
		res[0]=max;res[1]=ymax;
		return res;
	}
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une diagonale bas 
	 * partant de l'ordonnée x.
	 * @param x colonne de départ (On part de grille[x][0])
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueDiagBasX(int x)
	{
		
		
		int res[]=new int[2];
		int max=0, maxtmp=0;
		int ymax=0;
		CaseValue val=cases[x][0].v;
		
		
		
		int xtmp=x-1;
		int cptNbBoucle=0;
		for(int y=0; y<cols;y++){
			cptNbBoucle++;xtmp++;
			if(xtmp==rows)
				break;
			
			//change le max
			if(val!=cases[xtmp][y].v){
				if(max<maxtmp){
					max=maxtmp;
					ymax=y-max;
				}
				maxtmp=0; 
				val=cases[xtmp][y].v;
			}
			//incremente
			if(cases[xtmp][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp){
			max=maxtmp;
			ymax=cptNbBoucle-max-1;
		}
		res[0]=max;res[1]=ymax;
		return res;
	}
	
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une diagonale bas 
	 * partant de l'abscisse y.
	 * @param y abscisse de départ (On part de grille[0][y]) et on descend
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueDiagBasY(int y)
	{
		int res[]=new int[2];
		int max=0, maxtmp=0;
		CaseValue val=cases[0][y].v;
		
		int xmax=0;
		
		int ytmp=y-1;
		int cptNbBoucle=0;
		
		for(int x=0; x<rows;x++){
			ytmp++;
			cptNbBoucle++;
			if(ytmp==cols)
				break;
			
			//change le max
			if(val!=cases[x][ytmp].v){
				if(max<maxtmp)
				{
					max=maxtmp;
					xmax=x-max;
				}
				maxtmp=0;
				val=cases[x][ytmp].v;
			}
			//incremente
			if(cases[x][ytmp].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp){
			max=maxtmp;
			xmax=cptNbBoucle-max-1;
		}
		res[0]=max;res[1]=xmax;
		return res;
	}
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une diagonale haut 
	 * partant de l'ordonnée x.
	 * @param x colonne de départ (On part de grille[x][cols-1]) et on remonte
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueDiagHautX(int x)
	{
		int res[]=new int[2];
		int max=0, maxtmp=0;
		CaseValue val=cases[x][cols-1].v;
		
		int xtmp=x-1;
		int cptNbBoucle=0;
		int ymax=0;
		
		for(int y=cols-1; y>=0;y--){
			xtmp++;
			
			cptNbBoucle++;
			if(xtmp==rows)
				break;
			
			//change le max
			if(val!=cases[xtmp][y].v){
				if(max<maxtmp){
					max=maxtmp;
					ymax=y+max;
				}
				maxtmp=0;
				val=cases[xtmp][y].v;
			}
			//incremente
			if(cases[xtmp][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp){
			max=maxtmp;
			ymax=cptNbBoucle+max;
		}
		res[0]=max;res[1]=ymax;
		return res;
	}
	
	/**
	 * Calcule le nombre de pions identiques à la suite pour une diagonale haut 
	 * partant de l'abscisse y.
	 * @param y abscisse de départ (On part de grille[0][y]) et on remonte
	 * @return nombre de pions identiques
	 */
	public int[] nbPionIdentiqueDiagHautY(int y)
	{
		System.out.println("diag haut : y="+y);
		int max=0, maxtmp=0;
		CaseValue val=cases[0][y].v;
		int res[]=new int[2];
		int ytmp=y+1;
		int xmax=0;
		int cptNbBoucle=0;
		
		for(int x=0; x<rows;x++){
			ytmp--;
			System.out.println("ytmp -->"+ytmp + " y:"+x);
			cptNbBoucle++;
			if(ytmp<0)
				break;
			
			//change le max
			if(val!=cases[x][ytmp].v){
				if(max<maxtmp){
					max=maxtmp;
					xmax=x-max;
				}
				maxtmp=0; 
				val=cases[x][ytmp].v;
			}
			//incremente
			if(cases[x][ytmp].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp){
			max=maxtmp;
			xmax=cptNbBoucle-max-1;
		}
		res[0]=max;res[1]=xmax;
		return res;
	}
	
	
	/**
	 * Associe chacune des fonctions de vérifications et notifie la 
	 * vue si on trouve un pattern de victoire.
	 */
	public boolean verifWin()
	{
		for(int x=0; x<rows; x++){
			int res;
			
			int res2[]=nbPionIdentiqueCol(x);
			
			if(res2[0]>=nbJetonsRequis)
			{
				//surligne les cases gagnantes
				for(int i=res2[1]; i<res2[1]+res2[0]; i++)
				{
					this.cases[x][i].v=CaseValue.WIN;
					this.notifyNewChip(x, i, CaseValue.WIN);
				}
				
				this.notifyWinner(tour);
				System.out.println("wincol!");
				return true;
			}
			
			
			res2=nbPionIdentiqueDiagBasX(x);
			if(res2[0]>=nbJetonsRequis)
			{
				System.out.println("res2 "+res2[0]+" "+res2[1]);
				x=x+res2[1];//met le x sur la bonne ordonée
				//surligne les cases gagnantes
				for(int i=res2[1]; i<res2[1]+res2[0]; i++)
				{	
					this.cases[x][i].v=CaseValue.WIN;
					this.notifyNewChip(x, i, CaseValue.WIN);
					x++;
				}
				
				this.notifyWinner(tour);
				System.out.println("winbasX!");
				return true;
			}
		
			
			res2=nbPionIdentiqueDiagHautX(x);
			if(res2[0]>=nbJetonsRequis)
			{
				x=x+(cols-1-res2[1]);//met le x sur la bonne ordonée
				
				//surligne les cases gagnantes
				for(int i=res2[1]; i>res2[1]-res2[0]; i--)
				{
					this.cases[x][i].v=CaseValue.WIN;
					this.notifyNewChip(x, i, CaseValue.WIN);
					x++;
				}
				this.notifyWinner(tour);
				System.out.println("winhaut1X!");
				return true;
			}
			
		}
		
		for(int y=0; y<cols; y++){
			int res;
			int res2[]=nbPionIdentiqueLigne(y);
			
			if(res2[0]>=nbJetonsRequis)
			{
				System.out.println("res2 "+res2[0]+" indice "+ res2[1]);
				//surligne les cases gagnantes
				for(int i=res2[1]; i<res2[1]+res2[0]; i++)
				{
					this.cases[i][y].v=CaseValue.WIN;
					this.notifyNewChip(i, y, CaseValue.WIN);
				}
				
				this.notifyWinner(tour);
				System.out.println("winligne!");
				return true;
			}
			
			res2=nbPionIdentiqueDiagBasY(y);
			if(res2[0]>=nbJetonsRequis)
			{
				int ytmp=y+res2[1];//met le y sur la bonne ordonée
				
				//surligne les cases gagnantes
				for(int i=res2[1]; i<res2[1]+res2[0]; i++)
				{
					this.cases[i][ytmp].v=CaseValue.WIN;
					this.notifyNewChip(i, ytmp, CaseValue.WIN);
					ytmp++;
				}
				
				this.notifyWinner(tour);
				return true;
			}
			
			res2=nbPionIdentiqueDiagHautY(y);
			if(res2[0]>=nbJetonsRequis)
			{
				int ytmp=y+(-res2[1]);
				//y=y+res2[1];//met le y sur la bonne ordonée
				System.out.println("res -> "+res2[0]+" "+res2[1]+" y:"+ytmp);
				
				//surligne les cases gagnantes
				for(int i=res2[1]; i<res2[1]+res2[0]; i++)
				{
					System.out.println("update i:"+i+" y:"+ytmp);
					this.cases[i][ytmp].v=CaseValue.WIN;
					this.notifyNewChip(i, ytmp, CaseValue.WIN);
					ytmp--;
				}
				
				this.notifyWinner(tour);
				System.out.println("winhauty!");
				return true;
			}
		}
		
		return false;

	}
	
	
	/**
	 * Teste si une colonne est entière
	 */
	public boolean columnFull(int x){
		return cases[x][0].v!=CaseValue.NONE;
	}
	
	/**
	 * Renvoie le nombre de jetons dans une colonne
	 * @param x abscisse de la colonne
	 * @return nombre de jetons dans la colonne
	 */
	public int nbJetonColumn(int x){
		int i=rows;
		int cpt=0;
		//columnFull(x) return -1;
		while(cases[x][rows-1].v==CaseValue.NONE&&i!=0)
		{
			rows--;
			cpt++;
		}
		return cpt;
	}
	
	/**
	 * Récupérer la prochaine valeur de la colonne ou ajouter le jeton
	 * @param x abscisse de la colonne
	 * @return ordonnée où ajouter le jeton
	 */
	public int getNextIntColumn(int x){
		int i=0;
		//columnFull(x) return -1;
		while(i!=cols-1&&cases[x][i+1].v==CaseValue.NONE)
		{
			i++;
		}
		return i;
		
	}
	
	/**
	 * Ajout d'un jeton dans la grille de manière logique,
	 * pui notifie la vue de l'ajout d'un jeton et du changement de tour.
	 * @param x abscisse de la colonne
	 */
	@Override
	public boolean ajoutJeton(int x) {
		this.nbJetons++;
		this.tour=(tour+1)%nbJoueur;
		int y=getNextIntColumn(x);
		if(cases[x][y].v==CaseValue.NONE){
			System.out.println("!! "+CaseValue.fromInteger(tour));
			cases[x][y].v=CaseValue.fromInteger(tour);
			this.notifyNewChip(x, y, cases[x][y].v);
			this.notifyTour(tour);
			
			return true;
		}	
		
		return false;
	}
	
	/**
	 * Test de console, affiche le contenu de la grille
	 */
	public void afficher()
	{
		for(int j=0; j<cols; j++){
			for(int i=0; i<rows; i++)
			{
			
				System.out.print(cases[i][j].v+"\t");
			}
			System.out.println();
		}
	}


	

	
}


