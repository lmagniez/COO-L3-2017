package com.model;

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
	public boolean ajoutTraitH(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajoutTraitV(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				cases[i][j].v=CaseValue.NONE;
			}
		}	
	}

	
	public boolean isFull()
	{
		return tour==rows*cols;
	}
	
	
	
	public boolean verifWin()
	{
		boolean found=false;
		boolean foundonce=true;
		for(int i=0; i<rows-nbJetonsRequis; i++){
			for(int j=0; j<cols-nbJetonsRequis; j++){
				CaseValue val=cases[i][j].v;
				
				found=true;
				for(int cpt=1; cpt<nbJetonsRequis; cpt++){
					if(val!=cases[i][j+cpt].v)
						found=false;
				}
				if(found){
					this.notifyWinner(i,j,patternWin.VERTICAL);
					foundonce=true;
				}
				
				
				found=true;
				for(int cpt=1; cpt<nbJetonsRequis; cpt++){
					if(val!=cases[i+cpt][j].v)
						found=false;
				}
				if(found){
					this.notifyWinner(i,j,patternWin.HORIZONTAL);
					foundonce=true;
				}
				
			}
		}	
////////////////////////////////////////
		//diag bas
		for(int i=0; i<rows-nbJetonsRequis; i++){
			for(int j=0; j<cols-nbJetonsRequis; j++){
				CaseValue val=cases[i][j].v;
				patternWin found=patternWin.NONE;
				for(int cpt=1; cpt<nbJetonsRequis; cpt++){
					if(val!=cases[i+cpt][j+cpt].v){
						found=patternWin.DIAGONALEBAS;
						this.notifyWinner(i,j,found);
					}
				}
			}
		}
		
		//diag haut
		for(int i=rows-1; i>nbJetonsRequis; i--){
			for(int j=cols-1; j<nbJetonsRequis; j--){
				CaseValue val=cases[i][j].v;
				patternWin found=patternWin.NONE;
				for(int cpt=1; cpt<nbJetonsRequis; cpt++){
					if(val!=cases[i+cpt][j-cpt].v){
						found=patternWin.DIAGONALEHAUT;
						this.notifyWinner(i,j,found);
					}
				}
			}
		}
		
		return foundonce;
		
	}
	
	
	@Override
	public boolean ajoutJeton(int x, int y) {
		this.nbJetons++;
		this.tour=(tour+1)%nbJoueur;
		if(cases[x][y].v==CaseValue.NONE){
			cases[x][y].v=CaseValue.fromInteger(tour);
			return true;
		}	
		
		return false;
	}
	
	
	

	
	
	
	
}
