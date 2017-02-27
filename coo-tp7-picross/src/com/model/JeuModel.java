package com.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class JeuModel extends AbstractModel{

	MySQLCli client;
	ArrayList<GrilleModel> grilles;
	
	public JeuModel() throws SQLException{
		this.client=new MySQLCli("//localhost:3306/db", "", "");
		this.grilles=new ArrayList<GrilleModel>();
		if (client.connect()) {
			
			System.out.println(client.execUpdate(ConstantesRequetes.createDataBase));
       		System.out.println(client.execUpdate(ConstantesRequetes.createJeu));
       		System.out.println(client.execUpdate(ConstantesRequetes.createLigne));
       		System.out.println(client.execUpdate(ConstantesRequetes.createColonne));
       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuColonne));
       		System.out.println(client.execUpdate(ConstantesRequetes.createIndiceJeuLigne));
       	 
       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomStar)==-1){
       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomStar, 
       					ConstantesRequetes.nbLigneStar, ConstantesRequetes.nbColonneStar, 
       					ConstantesRequetes.indicesLigneStar, ConstantesRequetes.indicesLigneStar);
       		}
       		if(client.getIdPuzzleFromName(ConstantesRequetes.nomToad)==-1){
       			client.ajoutPuzzleToDatabase(ConstantesRequetes.nomToad, 
       					ConstantesRequetes.nbLigneToad, ConstantesRequetes.nbColonneToad, 
       					ConstantesRequetes.indicesLigneToad, ConstantesRequetes.indicesLigneToad);
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
       
       System.out.println(grilles.size());
		
	}

	
	public GrilleModel recupGrille(int idPuzzle){
		
		GrilleModel g=null;
		
		if (client.connect()) {
			
			int nbColonne=client.getNbColonne(idPuzzle);
			int nbLigne=client.getNbLigne(idPuzzle);
			String nom =client.getNomFromIdPuzzle(idPuzzle);
			String[] infoLigne=client.getAllIndiceColonne(idPuzzle, nbLigne);
			String[] infoColonne=client.getAllIndiceColonne(idPuzzle, nbColonne);
			g=new GrilleModel(this,idPuzzle,nom,nbLigne,nbColonne,infoLigne,infoColonne);
			
		}
		else {
			System.out.println("Mysql connection failed !!!");
		}
	    client.close();
	    
	    return g;
	    
	}
	
	
	
	
	@Override
	public boolean ajoutJeton(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean columnFull(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifWin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajoutJetonIA(int x, CaseValue v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retirerJetonIA(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reinit() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws SQLException {
		JeuModel m=new JeuModel();
	}
	
	
}
