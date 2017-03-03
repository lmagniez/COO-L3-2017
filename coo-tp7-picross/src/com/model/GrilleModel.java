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
	
	
	/**
	 * Constructeur grille simple (pour création de grille)
	 * @param model modele de la grille
	 * @param nom nom de la grille
	 * @param nbLigne nombre de ligne
	 * @param nbColonne nombre de colonne
	 */
	public GrilleModel(JeuModel model, String nom, int nbLigne, int nbColonne){
		this.model=model;
		this.nom=nom;
		this.nbLigne=nbLigne;
		this.nbColonne=nbColonne;
		this.reussite=false;
		this.grille=new boolean[nbLigne][nbColonne];
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				grille[i][j]=false;
			}
		}
	}
	
	
	/**
	 * Constructeur grille complet (quand on récupère une grille)
	 * @param model modele de la grille
	 * @param idPuzzle id du puzzle
	 * @param nom nom du puzzle
	 * @param nbLigne nombre de ligne
	 * @param nbColonne nombre de colonne
	 * @param infoLigne tableau des indices de ligne
	 * @param infoColonne tableau des indices de colonnes
	 * @param reussite 
	 */
	public GrilleModel(JeuModel model, int idPuzzle, String nom, int nbLigne, int nbColonne, 
			String infoLigne[], String infoColonne[], boolean reussite){
		this.model=model;
		this.idPuzzle=idPuzzle;
		this.nom=nom;
		this.nbLigne=nbLigne;
		this.nbColonne=nbColonne;
		this.infoColonne=infoColonne;
		this.infoLigne=infoLigne;
		this.reussite=reussite;
		this.grille=new boolean[nbLigne][nbColonne];
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				grille[i][j]=false;
			}
		}
		
	}
	
	/**
	 * Verifie pour une colonne donnée si il n'y a pas de conflits avec les indices donnés
	 * @param colonne colonne a tester
	 * @return conflit ou pas
	 */
	public boolean patternVerticalCorrect(int colonne){
		
		//convertit en tab de int
		String[] info=infoColonne[colonne].split(",");
		int indices[]= new int[info.length];
				
				
		for(int i=0; i<info.length; i++){
			indices[i]=Integer.parseInt(info[i]);
		}
						
		int nbPattern=0;
		if(indices[nbPattern]==0){
			for(int i=0; i<nbLigne; i++){
				if(grille[colonne][i])
					return false;
			}
			return true;
		}
		else{
			for(int i=0; i<nbLigne; i++){
				
				if(grille[colonne][i]){
					if(nbPattern==indices.length)return false;
					int cptPattern=indices[nbPattern];
					while(cptPattern!=0&&i<=nbLigne){
						if(!grille[colonne][i]){
							return false;
						}
						i++; cptPattern--;
					}
					nbPattern++;
					//if(nbPattern>indices.length)return false;
				}
					
			}
		}
		
		return (nbPattern==indices.length);
		
	}
	
	/**
	 * Verifie pour une ligne donnée si il n'y a pas de conflits avec les indices donnés
	 * @param ligne ligne a tester
	 * @return conflit ou pas
	 */
	public boolean patternHorizontalCorrect(int ligne){
		
		//convertit en tab de int
		String[] info=infoLigne[ligne].split(",");
		int indices[]= new int[info.length];
					
					
		for(int i=0; i<info.length; i++){
			indices[i]=Integer.parseInt(info[i]);
		}
		
		//on parcoure la chaine
		int nbPattern=0;
		
		if(indices[nbPattern]==0){
			for(int i=0; i<nbLigne; i++){
				if(grille[i][ligne])
					return false;
			}
			return true;
		}
		else{
			for(int i=0; i<nbLigne; i++){
				//demarre pattern
				if(grille[i][ligne]){
					if(nbPattern==indices.length)return false;
					int cptPattern=indices[nbPattern];
					while(cptPattern!=0&&i<=nbLigne){
						if(!grille[i][ligne]){
							return false;
						}
						i++; cptPattern--;
					}
					nbPattern++;
					
				}
					
			}
		}
		
		return (nbPattern==indices.length);
		
	}
	
	/**
	 * Vérifier si une grille est correcte ou non
	 */
	public void verifWin(){
		
		for(int i=0; i<nbColonne; i++){
			if(!this.patternVerticalCorrect(i)){
				this.model.notifyLose();
				return;
			}
		}
		
		for(int i=0; i<nbLigne; i++){
			if(!this.patternHorizontalCorrect(i)){
				this.model.notifyLose();
				return;
			}
		}
		
		this.model.setReussiteGrille(this.idPuzzle);
		this.model.notifyWin();
	}

	
	/**
	 * Changer une valeur dans la grille
	 * @param x abscisse de la grille
	 * @param y ordonnée de la grille
	 */
	public void change(int x, int y){
		if(this.grille[x][y])
			this.grille[x][y]=false;
		else
			this.grille[x][y]=true;
		
		this.model.notifyChangeValue(x, y);
	}
	
	/**
	 * Affichage
	 */
	public void afficherGrille(){
		
		for(int i=0; i<nbColonne; i++){
			for(int j=0; j<nbLigne; j++){
				System.out.print(this.grille[j][i]+" ");
			}
			System.out.println();
		}
		
	}

	/**
	 * Générer les indices pour une colonne donnée
	 * @param indice indice de la colonne
	 * @return indices générés
	 */
	public String generateUneCol(int indice){
		int cpt=0;
		String res="";
		for(int i=0; i<nbLigne; i++){
			while(i<nbLigne&&this.grille[indice][i]){
				cpt++;i++;
			};
			if(cpt!=0){
				if(res.equals(""))
					res=""+cpt;
				else
					res=res+","+cpt;
			}
			cpt=0;
		}
		if(res.equals(""))
			res="0";
		return res;
	}
	
	/**
	 * Générer les indices pour une ligne donnée
	 * @param indice indice de la ligne
	 * @return indices générés
	 */
	public String generateUneLigne(int indice){
		int cpt=0;
		String res="";
		for(int i=0; i<nbColonne; i++){
			while(i<nbColonne&&this.grille[i][indice]){
				cpt++;i++;
			};
			if(cpt!=0){
				if(res.equals(""))
					res=""+cpt;
				else
					res=res+","+cpt;
			}
			cpt=0;
		}
		if(res.equals(""))
			res="0";
		return res;
	}
	
	/**
	 * Générer l'ensemble des indices de ligne
	 * @return ensemble des indices de ligne
	 */
	public String[] generateInfoLigne(){
		// TODO Auto-generated method stub
		String[] res = new String[nbColonne];
		for(int i=0; i<nbColonne; i++){
			String ligne;
			ligne=this.generateUneLigne(i);
			res[i]=ligne;
			//res[i]=Integer.parseInt(ligne);
		}
		return res;
	}
	
	/**
	 * Générer l'ensemble des indices de colonne
	 * @return ensemble des indices de colonne
	 */
	public String[] generateInfoColonne(){
		// TODO Auto-generated method stub
		String[] res = new String[nbLigne];
		for(int i=0; i<nbLigne; i++){
			String ligne;
			ligne=this.generateUneCol(i);
			res[i]=ligne;
		}
		return res;
	}
	
	public void reinit(){
		for(int i=0; i<nbLigne; i++){
			for(int j=0; j<nbColonne; j++){
				grille[i][j]=false;
			}
		}
		this.model.notifyReinit();
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
