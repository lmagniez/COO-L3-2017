package com.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class JeuModel extends AbstractModel{

	MySQLCli client;
	ArrayList<GrilleModel> grilles;
	GrilleModel creation;
	
	public JeuModel() throws SQLException{
		this.client=new MySQLCli("//localhost:3306/", "", "");
		
		this.genererGrilles();
       
       System.out.println(grilles.size());
		
	}

	public void genererGrilles(){
		this.grilles=new ArrayList<GrilleModel>();
		if (client.connect()) {
				
				System.out.println(client.execUpdate(ConstantesRequetes.createDataBase));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createJeu));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createLigne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createColonne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuColonne));
	       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuLigne));
	       	 
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
	       		} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	//client.deletePuzzleFromDatabase(idPuzzle);
	       		//int idPuzzle=client.getIdPuzzleFromName("star");
	       		ArrayList<Integer> res= client.getAllIds();
	       		System.out.println("res "+res.size());
	       		
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
	 * @return
	 */
	public GrilleModel recupGrille(int idPuzzle){
		
		GrilleModel g=null;
		
		if (client.connect()) {
			
			int nbColonne=client.getNbColonne(idPuzzle);
			int nbLigne=client.getNbLigne(idPuzzle);
			String nom =client.getNomFromIdPuzzle(idPuzzle);
			
			System.out.println(nbLigne);
			
			String[] infoLigne=client.getAllIndiceLigne(idPuzzle, nbLigne);
			String[] infoColonne=client.getAllIndiceColonne(idPuzzle, nbColonne);
			g=new GrilleModel(this,idPuzzle,nom,nbLigne,nbColonne,infoLigne,infoColonne);
			
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
		
		
		System.out.println("notifyinfosGrille");
		
		System.out.println(nom[0]);
		
		this.notifyInfosGrilles(nbGrille, id, nom, reussite, nbLignes, nbColonnes);
	}
	
	
	public void requestGrilleDetail(int idPuzzle){
		
		
		for(int i=0; i<grilles.size(); i++){		
			if(this.grilles.get(i).idPuzzle==idPuzzle){
				GrilleModel grid=this.grilles.get(i);
				this.notifyGrilleDetail(grid.idPuzzle, grid.nom, grid.infoLigne, grid.infoColonne, grid.reussite);
				return;
			}
		}
		
	}
	
	
	
	
	@Override
	public void verifWin(int idGrille) {
		// TODO Auto-generated method stub
		
		this.getGrilleById(idGrille).verifWin();
		//grilles.get(idGrille).verifWin();
		
	}


	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
	}
	
	
	public GrilleModel getGrilleById(int idGrille){
		for(int i=0; i<grilles.size(); i++){
			if(grilles.get(i).idPuzzle==idGrille)
				return grilles.get(i);
		}
		return null;
	}
	


	@Override
	public void changeValue(int idGrille, int x, int y) {
		// TODO Auto-generated method stub
		GrilleModel g = this.getGrilleById(idGrille);
		g.change(x, y);
		//this.grilles.get(idGrille).change(x,y);
	}

	@Override
	public void changeValueCreation(int x, int y) {
		// TODO Auto-generated method stub
		this.creation.change(x, y);
	}

	@Override
	public void createGrille(String nom, int nbRow, int nbCol) {
		// TODO Auto-generated method stub
		this.creation=new GrilleModel(this,nom,nbRow,nbCol);
	}

	@Override
	public void saveTable() {
		// TODO Auto-generated method stub
		int[] infoLigne=creation.generateInfoLigne();
		int[] infoColonne=creation.generateInfoColonne();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void setReussiteGrille(int idGrille) {
		// TODO Auto-generated method stub
		GrilleModel g = this.getGrilleById(idGrille);
		g.reussite=true;
	}

	


	/*
	public static void main(String[] args) throws SQLException {
		JeuModel m=new JeuModel();
	}*/


	

	
	
}
