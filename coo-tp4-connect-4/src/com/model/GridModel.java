package com.model;

import java.util.ArrayList;


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

	public GridModel(int rows, int cols, int nbJR){
		
		this.nbJoueur=2;
		this.rows=rows;
		this.cols=cols;
		this.tour=0;
		this.nbJetons=0;
		this.nbJetonsRequis=nbJR;
		
		
		this.cases=new CaseModel[rows][cols];
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cases[i][j]=new CaseModel();
			}
		}	
	}


	@Override
	public void reinit() {
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cases[i][j].v=CaseValue.NONE;
			}
		}	
	}

	
	public boolean isFull()
	{
		return nbJetons==rows*cols;
	}
	
	public int nbPionIdentiqueLigne(int y)
	{
		int max=0, maxtmp=0;
		int xdeb=0;
		CaseValue val=cases[0][y].v;
		for(int x=0; x<rows;x++){
			//change le max
			if(val!=cases[x][y].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; xdeb=x;
				val=cases[x][y].v;
			}
			//incremente
			if(cases[x][y].v!=CaseValue.NONE)
				maxtmp++;
			
				
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	
	public int nbPionIdentiqueCol(int x)
	{
		int max=0, maxtmp=0;
		int ydeb=0;
		CaseValue val=cases[x][0].v;
		for(int y=0; y<cols;y++){
			//change le max
			if(val!=cases[x][y].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; ydeb=y;
				val=cases[x][y].v;
			}
			//incremente
			if(cases[x][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	
	public int nbPionIdentiqueDiagBasX(int x)
	{
		int max=0, maxtmp=0;
		int ydeb=0;
		CaseValue val=cases[x][0].v;
		
		
		
		int xtmp=x-1;
		
		for(int y=0; y<cols;y++){
			
			xtmp++;
			if(xtmp==rows)
				break;
			System.out.print(cases[xtmp][y].v+"&&"+val+" ");
			
			//change le max
			if(val!=cases[xtmp][y].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; ydeb=y;
				val=cases[xtmp][y].v;
			}
			//incremente
			if(cases[xtmp][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	
	public int nbPionIdentiqueDiagBasY(int y)
	{
		int max=0, maxtmp=0;
		int ydeb=0;
		CaseValue val=cases[0][y].v;
		
		
		
		int ytmp=y-1;
		
		for(int x=0; x<rows;x++){
			ytmp++;
			if(ytmp==cols)
				break;
			System.out.print(cases[x][ytmp].v+"&&"+val+" ");
			
			//change le max
			if(val!=cases[x][ytmp].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; ydeb=y;
				val=cases[x][ytmp].v;
			}
			//incremente
			if(cases[x][ytmp].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	

	public int nbPionIdentiqueDiagHautX(int x)
	{
		int max=0, maxtmp=0;
		int ydeb=0;
		CaseValue val=cases[x][cols-1].v;
		
		int xtmp=x-1;
		
		for(int y=cols-1; y>=0;y--){
			xtmp++;
			if(xtmp==rows)
				break;
			System.out.print(cases[xtmp][y].v+"&&"+val+" ");
			//change le max
			if(val!=cases[xtmp][y].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; ydeb=y;
				val=cases[xtmp][y].v;
			}
			//incremente
			if(cases[xtmp][y].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	
	public int nbPionIdentiqueDiagHautY(int y)
	{
		int max=0, maxtmp=0;
		int ydeb=0;
		CaseValue val=cases[0][y].v;
		
		int ytmp=y+1;
		
		for(int x=0; x<rows;x++){
			ytmp--;
			if(ytmp<0)
				break;
			
			
			System.out.print(cases[x][ytmp].v+"&&"+val+" ");
			
			//change le max
			if(val!=cases[x][ytmp].v){
				if(max<maxtmp)
					max=maxtmp;
				maxtmp=0; ydeb=y;
				val=cases[x][ytmp].v;
			}
			//incremente
			if(cases[x][ytmp].v!=CaseValue.NONE)
				maxtmp++;
		}
		if(max<maxtmp)
			max=maxtmp;
		return max;
	}
	
	
	
	public boolean verifWin()
	{
		for(int x=0; x<rows; x++){
			int res=nbPionIdentiqueCol(x);
			if(res>=nbJetonsRequis)
				System.out.println("win!");
			
			res=nbPionIdentiqueDiagBasX(x);
			if(res>=nbJetonsRequis)
				System.out.println("win!");
			
			/*res=nbPionIdentiqueDiagHautX(x);
			if(res>=nbJetonsRequis)
				System.out.println("win!");*/
		}
		
		for(int y=0; y<cols; y++){
			int res=nbPionIdentiqueLigne(y);
			if(res>=nbJetonsRequis)
				System.out.println("win!");
			
			/*
			res=nbPionIdentiqueDiagBasY(y);
			if(res>=nbJetonsRequis)
				System.out.println("win!");
			
			res=nbPionIdentiqueDiagHautX(y);
			if(res>=nbJetonsRequis)
				System.out.println("win!");*/
		}
		
		
		return false;
	}
	
	
	
	public boolean columnFull(int x){
		return cases[x][0].v!=CaseValue.NONE;
	}
	
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
	 * @param x abscisse
	 * @return ordonnée où ajouter
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
	
	
	@Override
	public boolean ajoutJeton(int x) {
		this.nbJetons++;
		this.tour=(tour+1)%nbJoueur;
		int y=getNextIntColumn(x);
		if(cases[x][y].v==CaseValue.NONE){
			System.out.println("!! "+CaseValue.fromInteger(tour));
			cases[x][y].v=CaseValue.fromInteger(tour);
			this.notifyNewChip(x, y, cases[x][y].v);
			
			return true;
		}	
		
		return false;
	}
	
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
	

	
	
	
	

	
	public static void main(String[] args) {
		GridModel m = new GridModel(7, 6, 3);
		m.ajoutJeton(1);
		m.ajoutJeton(2);
		m.ajoutJeton(1);
		m.ajoutJeton(2);

		m.ajoutJeton(1);
		m.ajoutJeton(2);

		m.ajoutJeton(1);
		m.ajoutJeton(1);
		m.ajoutJeton(2);
		
		m.ajoutJeton(2);
		m.ajoutJeton(5);
		m.ajoutJeton(3);
		
		m.ajoutJeton(2);
		m.ajoutJeton(1);
		m.ajoutJeton(5);
		m.ajoutJeton(4);
		m.ajoutJeton(2);
		m.ajoutJeton(2);
		m.ajoutJeton(3);
		m.ajoutJeton(4);	
		m.ajoutJeton(6);
		m.ajoutJeton(0);
		m.ajoutJeton(4);
		m.ajoutJeton(6);
		m.ajoutJeton(0);
		
		m.ajoutJeton(6);
		m.ajoutJeton(0);
		m.ajoutJeton(6);
		m.ajoutJeton(0);
		m.ajoutJeton(6);
		m.ajoutJeton(0);
		m.ajoutJeton(0);
		
		
		
		
		m.afficher();
	
		System.out.println(m.nbPionIdentiqueCol(1));
		System.out.println(m.nbPionIdentiqueCol(2));
		System.out.println(m.nbPionIdentiqueLigne(2));
		System.out.println(m.nbPionIdentiqueLigne(1));
		
		/*
		System.out.println(m.nbPionIdentiqueDiagBasX(0));
		System.out.println(m.nbPionIdentiqueDiagBasX(1));
		System.out.println(m.nbPionIdentiqueDiagBasX(2));
		System.out.println(m.nbPionIdentiqueDiagBasX(3));
		System.out.println(m.nbPionIdentiqueDiagBasX(4));
		System.out.println(m.nbPionIdentiqueDiagBasX(5));
		*/
		
		System.out.println(m.nbPionIdentiqueDiagHautY(0));
		System.out.println(m.nbPionIdentiqueDiagHautY(1));
		System.out.println(m.nbPionIdentiqueDiagHautY(2));
		System.out.println(m.nbPionIdentiqueDiagHautY(3));
		System.out.println(m.nbPionIdentiqueDiagHautY(4));
		System.out.println(m.nbPionIdentiqueDiagHautY(5));
		//System.out.println(m.nbPionIdentiqueDiagHautX(6));
		
		//System.out.println(m.nbPionIdentiqueDiagHautY(1));
		
		
	}


	

	
}


