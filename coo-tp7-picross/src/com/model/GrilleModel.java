package com.model;

/**
 * Classe représentant une grille de jeu de manière logique
 * @author loick
 *
 */

public class GrilleModel {

	private JeuModel model;
	private int idPuzzle;
	private String nom;
	private int nbLigne,nbColonne;
	protected boolean[][] grille;
	private String infoLigne[];
	private String infoColonne[];
	private boolean reussite;
	
	
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
		for(int i=0; i<nbLigne; i++){
			System.out.println(">>>"+i);
			//demarre pattern
			if(grille[i][colonne]){
				int cptPattern=indices[nbPattern];
				while(cptPattern!=0&&i<nbLigne){
					if(!grille[i][colonne]){
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
		
		return (nbPattern==indices.length);
		
	}
	
	
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
	}
}
