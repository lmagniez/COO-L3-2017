package com.model;

/**
 * Classe représentant une grille de jeu de manière logique
 * @author loick
 *
 */

public class GrilleModel {

	protected JeuModel model;
	protected int idPuzzle;
	protected String nom;
	protected int nbLigne,nbColonne;
	protected boolean[][] grille;
	protected String infoLigne[];
	protected String infoColonne[];
	protected boolean reussite;
	
	
	/*
	 On va d'abord recuperer chaque id dans une classe principale (jeu?)
	 
	 Pour chaque id, on va lancer le constructeur de GrilleModel et récuperer chaque donnée
	 
	 */
	
	public GrilleModel(JeuModel model, int idPuzzle, String nom, int nbLigne, int nbColonne, 
			String infoLigne[], String infoColonne[]){
		this.model=model;
		this.idPuzzle=idPuzzle;
		this.nom=nom;
		this.nbLigne=nbLigne;
		this.nbColonne=nbColonne;
		this.infoColonne=infoColonne;
		this.infoLigne=infoLigne;
		this.reussite=false;
		this.grille=new boolean[nbLigne][nbColonne];
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				grille[i][j]=false;
			}
		}
		
	}
	
	public boolean patternVerticalCorrect(int colonne){
		
		//convertit en tab de int
		String info=infoColonne[colonne];
		int indices[]= new int[info.length()];
		for(int i=0; i<info.length(); i++){
			indices[i]=Integer.parseInt(""+info.charAt(i));
		}
		
		for(int i=0; i<info.length(); i++)
		{
			System.out.println(indices[i]);
		}
		
		/*
		for(int i=info.length()-1; i>=0; i++){
			indices[cpt++]=Integer.parseInt(""+info.charAt(i));
		}*/
		
		//on parcoure la chaine
		int nbPattern=0;
		
		
		if(indices[nbPattern]==0){
			System.out.println("ici");
			for(int i=0; i<nbLigne; i++){
				if(grille[colonne][i])
					return false;
			}
			return true;
		}
		else{
			for(int i=0; i<nbLigne; i++){
				System.out.println(">>>"+i);
				//demarre pattern
				if(grille[colonne][i]){
					int cptPattern=indices[nbPattern];
					while(cptPattern!=0&&i<=nbLigne){
						if(!grille[colonne][i]){
							System.out.println("aouch");
							return false;
						}
						i++; cptPattern--;
						System.out.println("et de un : i:"+i +" cptPattern:"+cptPattern);
					}
					System.out.println("ok pattern");
					nbPattern++;
				}
					
			}
		}
		
		return (nbPattern==indices.length);
		
	}
	
	
public boolean patternHorizontalCorrect(int ligne){
		
		//convertit en tab de int
		String info=infoLigne[ligne];
		int indices[]= new int[info.length()];
		for(int i=0; i<info.length(); i++){
			indices[i]=Integer.parseInt(""+info.charAt(i));
		}
		
		for(int i=0; i<info.length(); i++)
			System.out.println(indices[i]);
		
		
		//on parcoure la chaine
		int nbPattern=0;
		
		if(indices[nbPattern]==0){
			System.out.println("ici");
			for(int i=0; i<nbLigne; i++){
				if(grille[i][ligne])
					return false;
			}
			return true;
		}
		else{
			for(int i=0; i<nbLigne; i++){
				System.out.println(">>>"+i);
				//demarre pattern
				if(grille[i][ligne]){
					int cptPattern=indices[nbPattern];
					while(cptPattern!=0&&i<=nbLigne){
						if(!grille[i][ligne]){
							System.out.println("aouch");
							return false;
						}
						i++; cptPattern--;
						System.out.println("et de un : i:"+i +" cptPattern:"+cptPattern);
					}
					System.out.println("ok pattern");
					nbPattern++;
				}
					
			}
		}
		
		return (nbPattern==indices.length);
		
	}
	
	public void verifWin(){
		
		afficherGrille();
		
		
		for(int i=0; i<nbColonne; i++){
			System.out.println("patternV"+i);
			if(!this.patternVerticalCorrect(i)){
				System.out.println("non...");
				this.model.notifyLose();
				return;
			}
		}
		
		for(int i=0; i<nbLigne; i++){
			System.out.println("patternH"+i);
			if(!this.patternHorizontalCorrect(i)){
				System.out.println("non...");
				this.model.notifyLose();
				return;
			}
		}
		
		this.model.notifyWin();
	}

	
	public void change(int x, int y){
		if(this.grille[x][y])
			this.grille[x][y]=false;
		else
			this.grille[x][y]=true;
		
		this.model.notifyChangeValue(x, y);
	}
	
	public void afficherGrille(){
		
		for(int i=0; i<nbColonne; i++){
			for(int j=0; j<nbLigne; j++){
				System.out.print(this.grille[j][i]+" ");
			}
			System.out.println();
		}
		
	}
	
	
	/*
	public static void main(String[] args) {
		
		String infoL[]={"1","1"};
		String infoC[]={"12"};
		
		GrilleModel m = new GrilleModel(null, 0, "test", 6,1,infoL,infoC );
	
		m.grille[0][0]=true;
		m.grille[1][0]=true;
		m.grille[2][0]=true;
		m.grille[3][0]=false;
		m.grille[4][0]=false;
		m.grille[5][0]=false;
		
		System.out.println(m.patternVerticalCorrect(0));
	}*/
}
