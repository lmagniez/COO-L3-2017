package com.model;

/**
 * Classe représentant une grille de jeu de manière logique
 * @author loick
 *
 */

public class GrilleModel extends AbstractModel{

	protected int nbX,nbY;
	protected CaseModel[][] grille;
	protected CaseValue tour;
	protected int nbJetons;
	protected int nbJetonsJ1;
	protected int nbJetonsJ2;
	
	protected boolean[] isIA;
	protected IA ia;
	
	public GrilleModel(int nbX, int nbY,boolean[] isIA, boolean J1Start){
		
		this.isIA=isIA;
		/*
		isIA=new boolean[2];
		isIA[0]=false;
		isIA[1]=true;*/
		this.ia=new IA(this);
		
		
		
		this.nbX=nbX;
		this.nbY=nbY;
		
		this.grille=new CaseModel[nbX][nbY];
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				grille[i][j]=new CaseModel();
			}
		}
		nbJetons=0;
		nbJetonsJ1=0;
		nbJetonsJ2=0;
		
		if(J1Start){
			tour=CaseValue.J1;
		}
		else{
			tour=CaseValue.J2;
		}
		
	}
	
	public void startIA(){
		this.ia.start();
	}
	
	public void stopIA(){
		this.ia.arret();
	}
	
	public void placerinit(){
		this.placer(this.nbX/2-1, this.nbY/2-1, CaseValue.J1);
		this.placer(this.nbX/2, this.nbY/2-1, CaseValue.J2);
		this.placer(this.nbX/2-1, this.nbY/2, CaseValue.J2);
		this.placer(this.nbX/2, this.nbY/2, CaseValue.J1);
	}
	
	public void placer(int x, int y, CaseValue v){
		this.nbJetons++;
		if(v==CaseValue.J1)
			this.nbJetonsJ1++;
		else
			this.nbJetonsJ2++;		
		
		this.grille[x][y].v=v;
		this.notifyChangeValue(x, y, v);
		int i,j;
	}
	
	
	public boolean[] peutPlacer(int x, int y, CaseValue v){
		
		
		
		boolean res[]={false,false,false,false,false,false,false,false};
		
		if(grille[x][y].v!=CaseValue.EMPTY){
			System.out.println("not emptyyy");
			return res;
		}
		
		CaseValue valeurJ1=v;
		CaseValue valeurJ2 = null;
		if(valeurJ1==CaseValue.J1)valeurJ2=CaseValue.J2;
		if(valeurJ1==CaseValue.J2)valeurJ2=CaseValue.J1;
		
		//System.out.println(valeurJ1);
		//System.out.println(valeurJ2);
		
		
		int i=x, j=y, cpt=0;
		
		//haut
		j=y-1;cpt=0;
		while(j>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;cpt++;
		}
		if(cpt>0&&j!=-1&&grille[i][j].v==valeurJ1)
			res[0]=true;
		
		//diag haut droite
		j=y-1; i=x+1;cpt=0;
		while(j>=0&&i<nbX&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;i++;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[1]=true;
		
		//droite
		i=x+1;j=y;cpt=0;
		while(i<this.nbX&&grille[i][j].v==valeurJ2){
			i++;cpt++;
		}
		if(cpt>0&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[2]=true;
		
		//diag bas droite
		j=y+1; i=x+1;cpt=0;
		while(j<nbY&&i<nbX&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j++;i++;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[3]=true;
		
		//bas
		i=x;j=y+1;cpt=0;
		while(j<this.nbY&&grille[i][j].v==valeurJ2){
			j++;cpt++;
		}
		if(cpt>0&&j!=nbY&&grille[i][j].v==valeurJ1)
			res[4]=true;
		
		//diag bas gauche
		j=y+1; i=x-1;cpt=0;
		while(j<nbY&&i>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j++;i--;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=-1&&grille[i][j].v==valeurJ1)
			res[5]=true;
		
		//gauche
		i=x-1;j=y;cpt=0;
		while(i>=0&&grille[i][j].v==valeurJ2){
			i--;cpt++;
		}
		if(cpt>0&&i!=-1&&grille[i][j].v==valeurJ1)
			res[6]=true;
		
		//diag haut gauche
		j=y-1; i=x-1;cpt=0;
		while(j>=0&&i>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;i--;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=-1&&grille[i][j].v==valeurJ1)
			res[7]=true;
		
		return res;
		
	}
	
	public void remplirCase(int x, int y, boolean[] directions, CaseValue v){
		
		this.nbJetons++;
		if(v==CaseValue.J1)
			this.nbJetonsJ1++;
		else
			this.nbJetonsJ2++;
		
		for(int i=0; i<8; i++){
			System.out.print(directions[i]+" ");
		}
		System.out.println();
		
		this.grille[x][y].v=v;
		this.notifyChangeValue(x, y, v);
		int i,j;
		
		//H
		if(directions[0]){
			i=x;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//HD
		if(directions[1]){
			i=x+1;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j--;i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//D
		if(directions[2]){
			i=x+1;j=y;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//BD
		if(directions[3]){
			i=x+1;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//B
		if(directions[4]){
			i=x;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//BG
		if(directions[5]){
			i=x-1;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;i--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//G
		if(directions[6]){
			i=x-1;j=y;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i--;
				if(v==CaseValue.J1){
					System.out.println("yes");
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//HG
		if(directions[7]){
			i=x-1;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i--;j--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		
		this.notifyScore(nbJetonsJ1,nbJetonsJ2);
		
		
	}
	
public int nbCasesPrises(int x, int y, CaseValue v){
		
		
		
		int res=0;
		
		if(grille[x][y].v!=CaseValue.EMPTY){
			System.out.println("not emptyyy");
			return res;
		}
		
		CaseValue valeurJ1=v;
		CaseValue valeurJ2 = null;
		if(valeurJ1==CaseValue.J1)valeurJ2=CaseValue.J2;
		if(valeurJ1==CaseValue.J2)valeurJ2=CaseValue.J1;
		
		//System.out.println(valeurJ1);
		//System.out.println(valeurJ2);
		
		
		int i=x, j=y, cpt=0;
		
		//haut
		j=y-1;cpt=0;
		while(j>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;cpt++;
		}
		if(cpt>0&&j!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag haut droite
		j=y-1; i=x+1;cpt=0;
		while(j>=0&&i<nbX&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;i++;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//droite
		i=x+1;j=y;cpt=0;
		while(i<this.nbX&&grille[i][j].v==valeurJ2){
			i++;cpt++;
		}
		if(cpt>0&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag bas droite
		j=y+1; i=x+1;cpt=0;
		while(j<nbY&&i<nbX&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j++;i++;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//bas
		i=x;j=y+1;cpt=0;
		while(j<this.nbY&&grille[i][j].v==valeurJ2){
			j++;cpt++;
		}
		if(cpt>0&&j!=nbY&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag bas gauche
		j=y+1; i=x-1;cpt=0;
		while(j<nbY&&i>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j++;i--;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//gauche
		i=x-1;j=y;cpt=0;
		while(i>=0&&grille[i][j].v==valeurJ2){
			i--;cpt++;
		}
		if(cpt>0&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag haut gauche
		j=y-1; i=x-1;cpt=0;
		while(j>=0&&i>=0&&grille[i][j].v==valeurJ2){
			//System.out.println(grille[i][j].v+" puis j--");
			j--;i--;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		return res;
		
	}
	
	
	
	public boolean isFull(){
		return this.nbJetons==this.nbX*this.nbY;
	}
	
	
	
	public boolean peutJouer(CaseValue tour){
		
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				
				if(this.grille[i][j].v==CaseValue.EMPTY){
				
					boolean res[]=peutPlacer(i,j,tour);
					for(int k=0; k<8; k++){
						if(res[k])
							return true;
					}
				}
				
			}
		}
		return false;
	}
	
	public void getWinner(){
		if(this.nbJetonsJ1>this.nbJetonsJ2)
			this.notifyWin(CaseValue.J1);
		else
			this.notifyWin(CaseValue.J2);
	}
	
	
	public void changerTour(){
		
		if(tour==CaseValue.J1){
			tour=CaseValue.J2;
		}
		else{
			tour=CaseValue.J1;
		}
		
		this.notifyTour(tour);
	}
	
	
	/**
	 * Affichage
	 */
	public void afficherGrille(){
		
		for(int i=0; i<nbY; i++){
			for(int j=0; j<nbX; j++){
				if(this.grille[j][i].v==CaseValue.EMPTY)System.out.print("EM ");
				else System.out.print(this.grille[j][i].v+" ");
			}
			System.out.println();
		}
		
	}
	
	public void reinit(){
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				grille[i][j].v=CaseValue.EMPTY;
			}
		}
		
		this.nbJetons=0;
		this.nbJetonsJ1=0;
		this.nbJetonsJ2=0;
		this.notifyReinit();
		
		this.placerinit();
		
	}
	
	public CaseValue getTour(){
		return tour;
	}
	
	/*
	
	public static void main(String[] args) {
		
		GrilleModel m = new GrilleModel(6,5, true);
		
		m.grille[2][2].v=CaseValue.J1;
		m.grille[2][3].v=CaseValue.J1;
		m.grille[2][4].v=CaseValue.J1;
		m.grille[3][2].v=CaseValue.J1;
		m.grille[3][3].v=CaseValue.J1;
		m.grille[3][1].v=CaseValue.J1;
		//m.grille[4][0].v=CaseValue.J2;
		m.grille[4][1].v=CaseValue.J1;
		m.grille[4][2].v=CaseValue.J2;
		m.grille[1][3].v=CaseValue.J2;
		m.grille[2][1].v=CaseValue.J2;
		//m.grille[5][2].v=CaseValue.J1;
		
		m.afficherGrille();
		
		boolean[] res=m.peutPlacer(4, 3, CaseValue.J2);
		m.remplirCase(4,3,res, CaseValue.J2);
		
		for(int i=0; i<8; i++){
			System.out.print(res[i]+" ");
			
		}
		System.out.println();
		
		m.afficherGrille();
		
	}
	*/


	
}
