package com.model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Modèle principal représentant le jeu
 * Conserve l'ensemble des grilles et fait les requetes de BD
 * @author loick
 *
 */
public class JeuModel extends AbstractModel{

	MySQLCli client;
	ArrayList<GrilleModel> grilles;
	GrilleModel creation;
	
	public JeuModel() throws SQLException{
		this.client=new MySQLCli("//localhost:3306/", "root", "root");
		
		this.genererGrilles();
       
		
	}
	
	/**
	 * Générer les grilles dans le modèle.
	 * Fait appel à la base de donnée, génère les grilles par défaut si elles n'existent pas. 
	 * Récupère ensuite l'ensemble des grilles dans le modèle.
	 */
	public void genererGrilles(){
		this.grilles=new ArrayList<GrilleModel>();
		if (client.connect()) {
				/*
				System.out.println(client.execUpdate(ConstantesRequetes.createDataBase));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createJeu));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createLigne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createColonne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuColonne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuLigne));
	       	 	*/
	       		try {
					
	       			if(client.getIdPuzzleFromName(ConstantesRequetes.nomStar)==-1){
						client.ajoutPuzzleToDatabase(ConstantesRequetes.nomStar, 
								ConstantesRequetes.nbLigneStar, ConstantesRequetes.nbColonneStar, 
								ConstantesRequetes.indicesLigneStar, ConstantesRequetes.indicesColonnesStar);
					}
				
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomToad)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomToad, 
		       					ConstantesRequetes.nbLigneToad, ConstantesRequetes.nbColonneToad, 
		       					ConstantesRequetes.indicesLigneToad, ConstantesRequetes.indicesColonnesToad);
		       		}
		       		
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomEasy)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomEasy, 
		       					ConstantesRequetes.nbLigneEasy, ConstantesRequetes.nbColonneEasy, 
		       					ConstantesRequetes.indicesLigneEasy, ConstantesRequetes.indicesColonnesEasy);
		       		}
		       		
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomBill)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomBill, 
		       					ConstantesRequetes.nbLigneBill, ConstantesRequetes.nbColonneBill, 
		       					ConstantesRequetes.indicesLigneBill, ConstantesRequetes.indicesColonnesBill);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomChat)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomChat, 
		       					ConstantesRequetes.nbLigneChat, ConstantesRequetes.nbColonneChat, 
		       					ConstantesRequetes.indicesLigneChat, ConstantesRequetes.indicesColonnesChat);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomGB)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomGB, 
		       					ConstantesRequetes.nbLigneGB, ConstantesRequetes.nbColonneGB, 
		       					ConstantesRequetes.indicesLigneGB, ConstantesRequetes.indicesColonnesGB);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomGoomba)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomGoomba, 
		       					ConstantesRequetes.nbLigneGoomba, ConstantesRequetes.nbColonneGoomba, 
		       					ConstantesRequetes.indicesLigneGoomba, ConstantesRequetes.indicesColonnesGoomba);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomNinja)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomNinja, 
		       					ConstantesRequetes.nbLigneNinja, ConstantesRequetes.nbColonneNinja, 
		       					ConstantesRequetes.indicesLigneNinja, ConstantesRequetes.indicesColonnesNinja);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomPalmier)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomPalmier, 
		       					ConstantesRequetes.nbLignePalmier, ConstantesRequetes.nbColonnePalmier, 
		       					ConstantesRequetes.indicesLignePalmier, ConstantesRequetes.indicesColonnesPalmier);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomSnail)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomSnail, 
		       					ConstantesRequetes.nbLigneSnail, ConstantesRequetes.nbColonneSnail, 
		       					ConstantesRequetes.indicesLigneSnail, ConstantesRequetes.indicesColonnesSnail);
		       		}
		       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomTrophy)==-1){
		       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomTrophy, 
		       					ConstantesRequetes.nbLigneTrophy, ConstantesRequetes.nbColonneTrophy, 
		       					ConstantesRequetes.indicesLigneTrophy, ConstantesRequetes.indicesColonnesTrophy);
		       		}


		       		
		       		
		       		
	       		} catch (SQLException e) {
					e.printStackTrace();
				}
	        	//client.deletePuzzleFromDatabase(idPuzzle);
	       		//int idPuzzle=client.getIdPuzzleFromName("star");
	       		ArrayList<Integer> res= client.getAllIds();
	       		
	       		for(int i=0; i<res.size(); i++){
	       			GrilleModel g = recupGrille(res.get(i));
	       			if(g!=null)
	       				grilles.add(g);
	       		}
	       		
	       } else {
	           System.out.println("Mysql connection failed !!!");
	       }
	       client.close();
	}
	
	/**
	 * Pour le constructeur, en fonction d'un id, va récuperer dans la base chaque attribut nécessaire
	 * @param idPuzzle
	 * @return modele de la grille en question
	 */
	public GrilleModel recupGrille(int idPuzzle){
		
		GrilleModel g=null;
		
		if (client.connect()) {
			
			int nbColonne=client.getNbColonne(idPuzzle);
			int nbLigne=client.getNbLigne(idPuzzle);
			String nom =client.getNomFromIdPuzzle(idPuzzle);
			boolean reussite=client.getReussite(idPuzzle);
			
			String[] infoLigne=client.getAllIndiceLigne(idPuzzle, nbLigne);
			String[] infoColonne=client.getAllIndiceColonne(idPuzzle, nbColonne);
			g=new GrilleModel(this,idPuzzle,nom,nbLigne,nbColonne,infoLigne,infoColonne,reussite);
			
		}
		else {
			System.out.println("Mysql connection failed !!!");
		}
	    client.close();
	    
	    return g;
	    
	}
	
	
	/**
	 * Envoie l'ensemble des informations de grille à la vue
	 */
	public void requestGrilles(){
		int nbGrille=this.grilles.size();
		int id[]=new int[nbGrille];
		String nom[]=new String[nbGrille];
		boolean reussite[]=new boolean[nbGrille];
		int nbLignes[]=new int[nbGrille];
		int nbColonnes[]=new int[nbGrille];
		
		
		for(int i=0; i<nbGrille; i++){
			id[i]=this.grilles.get(i).idPuzzle;
			nom[i]=this.grilles.get(i).nom;
			reussite[i]=this.grilles.get(i).reussite;
			nbLignes[i]=this.grilles.get(i).nbLigne;
			nbColonnes[i]=this.grilles.get(i).nbColonne;
			
		}
		
		this.notifyInfosGrilles(nbGrille, id, nom, reussite, nbLignes, nbColonnes);
	}
	
	
	/**
	 * Envoyer une grille en détail à la vue
	 * @param idPuzzle id du puzzle
	 */
	public void requestGrilleDetail(int idPuzzle){
		
		
		for(int i=0; i<grilles.size(); i++){		
			if(this.grilles.get(i).idPuzzle==idPuzzle){
				GrilleModel grid=this.grilles.get(i);
				this.notifyGrilleDetail(grid.idPuzzle, grid.nom, grid.infoLigne, grid.infoColonne, grid.reussite);
				return;
			}
		}
		
	}
	
	
	
	/**
	 * Vérifier si il y a une victoire
	 * @param idGrille id de la grille
	 */
	@Override
	public void verifWin(int idGrille) {
		this.getGrilleById(idGrille).verifWin();
		//grilles.get(idGrille).verifWin();
		
	}
	
	/**
	 * Récupérer une grille par son id
	 * @param idGrille id de la grille
	 * @return modele de la grille
	 */
	public GrilleModel getGrilleById(int idGrille){
		for(int i=0; i<grilles.size(); i++){
			if(grilles.get(i).idPuzzle==idGrille)
				return grilles.get(i);
		}
		return null;
	}
	

	/**
	 * Changer de valeur une case d'une grille donnée
	 * @param idGrille id de la grille
	 * @param x abscisse de la grille
	 * @param y ordonnée de la grille
	 */
	@Override
	public void changeValue(int idGrille, int x, int y) {
		GrilleModel g = this.getGrilleById(idGrille);
		g.change(x, y);
		//this.grilles.get(idGrille).change(x,y);
	}

	/**
	 * Changer de valeur une case de la grille de création
	 */
	@Override
	public void changeValueCreation(int x, int y) {
		this.creation.change(x, y);
	}

	/**
	 * Créer une nouvelle grille de création
	 * @param nom nom de la grille
	 * @param nbRow nombre de ligne
	 * @param nbCol nombre de colonne
	 */
	@Override
	public void createGrille(String nom, int nbRow, int nbCol) {
		this.creation=new GrilleModel(this,nom,nbRow,nbCol);
	}

	/**
	 * Sauvegarder la grille de création
	 */
	@Override
	public void saveTable() {
		String[] infoLigne=creation.generateInfoLigne();
		String[] infoColonne=creation.generateInfoColonne();
		try {
			if (client.connect()) {
				client.ajoutPuzzleToDatabase(this.creation.nom, this.creation.nbLigne, this.creation.nbColonne, 
						infoLigne, infoColonne);
			} else {
				System.out.println("Mysql connection failed !!!");
			}
			client.close();
			
			this.notifyReinitWindow();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * indiquer qu'une grille a été réussie
	 * @param idGrille id de la grille 
	 */
	@Override
	public void setReussiteGrille(int idGrille) {
		GrilleModel g = this.getGrilleById(idGrille);
		g.reussite=true;
	}

	@Override
	public void reinit(int idGrille) {
		GrilleModel g = this.getGrilleById(idGrille);
		g.reinit();
	}

	@Override
	public void reinitCreation() {
		// TODO Auto-generated method stub
		creation.reinit();
	}

	public void updateReussite() {
		
		if (client.connect()) {
			for(int i=0; i<this.grilles.size(); i++){
				this.client.setReussiteGrille(grilles.get(i).idPuzzle, grilles.get(i).reussite);
			}
		} else {
			System.out.println("Mysql connection failed !!!");
		}
		client.close();
		
		this.notifyReinitWindow();
		
		
		
	}
	


	/*
	public static void main(String[] args) throws SQLException {
		JeuModel m=new JeuModel();
	}*/


	

	
	
}
