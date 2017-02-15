package com.model;

import java.util.ArrayList;
import java.util.Random;

import com.vue.grid.CaseValueVue;

/**
 * Classe correspondant au modèle de la grille
 * @author loick
 */

public class GridModel extends AbstractModel{

	protected int longueurX;
	protected int longueurY;
	
	protected CaseModel casesJoueur[][];//cases où le joueur intéragit
	protected CaseModel casesAdversaire[][];//cases où l'adversaire intéragit (contient les bateaux)
	
	protected BateauModel bateaux[];
	
	
	public static int nb_bateaux=0;
	
	
	protected boolean isIA[]; 

	private int nbBateauxTouchesJoueur,nbBateauxTouchesAdversaire;
	
	
	
	/**
	 * Constructeur de la grille
	 * @param rows nombre de lignes
	 * @param longueurY nombre de colonnes
	 * @param nbJR nombre de jetons requis pour gagner
	 */
	public GridModel(int longueurX, int longueurY){
		
		this.longueurX=longueurX;
		this.longueurY=longueurY;
		this.bateaux=new BateauModel[Constantes.NB_BATEAUX];
		this.nbBateauxTouchesJoueur=0;
		this.nbBateauxTouchesAdversaire=0;
		
		
		this.casesJoueur=new CaseModel[longueurX][longueurY];
		this.casesAdversaire=new CaseModel[longueurX][longueurY];
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				casesJoueur[i][j]=new CaseModel();
				casesAdversaire[i][j]=new CaseModel();
			}
		}
		genererBateaux();
	}
	
	public void notifierBateaux(){
		for(int i=0; i<Constantes.NB_BATEAUX; i++){
			this.notifyNewBateau(bateaux[i].debutX,bateaux[i].debutY,bateaux[i].type,
					bateaux[i].o,i);
		}
	}

	public void genererBateaux(){
		nb_bateaux=0;
		placerBateau(TypeBateau.BATEAU_2);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_3);
		placerBateau(TypeBateau.BATEAU_4);
		placerBateau(TypeBateau.BATEAU_5);
	}
	
	public void placerBateau(TypeBateau t){
		int tailleBateau=TypeBateau.fromType(t);
		
		int positionX = 0,positionY = 0;
		Random r=new Random();
		
		Orientation orientation=Orientation.fromInteger(r.nextInt(2));
		
		boolean ok=false;
		while(!ok){
			if(orientation==Orientation.VERTICAL){
				
				positionX=r.nextInt(longueurX-tailleBateau);
				positionY=r.nextInt(longueurY);
				ok=true;
				for(int x=positionX; x<positionX+tailleBateau; x++){
					if(casesAdversaire[x][positionY].idBateau!=-1)
						ok=false;		
				}
			}
			else if(orientation==Orientation.HORIZONTAL){
				positionX=r.nextInt(longueurX);
				positionY=r.nextInt(longueurY-tailleBateau);
				//checker positions libres
				ok=true;
				for(int y=positionY; y<positionY+tailleBateau; y++){
					if(casesAdversaire[positionX][y].idBateau!=-1)
						ok=false;
				}
			}
		}
		bateaux[nb_bateaux]=new BateauModel(this,nb_bateaux,positionX,positionY,t,orientation);
		this.notifyNewBateau(bateaux[nb_bateaux].debutX,bateaux[nb_bateaux].debutY,bateaux[nb_bateaux].type,
				bateaux[nb_bateaux].o,nb_bateaux);
		nb_bateaux++;
		

		
	}
	
	@Override
	public void verifWin() {
		if(this.nbBateauxTouchesJoueur==Constantes.NB_COUPS_NECESSAIRES)
			this.notifyWinner();
		if(this.nbBateauxTouchesAdversaire==Constantes.NB_COUPS_NECESSAIRES)
			this.notifyLoser();
	}
	
	public boolean isAlreadyShot(int x, int y){
		return (casesJoueur[x][y].v==CaseValue.TOUCHE);
	}
	
	/**
	 * Se faire bombarder par l'adversaire
	 * Recoit BOMB X Y du serveur
	 * Renvoie au serveur TOUCHE ou MISSED
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public void bombAdversaire(int x, int y){
			
		int idB=casesJoueur[x][y].idBateau;
		casesJoueur[x][y].v=CaseValue.TOUCHE;
		if(idB!=-1){
			bateaux[idB].setNbTouches(bateaux[idB].getNbTouches()+1);
			this.nbBateauxTouchesAdversaire++;
			this.notifyBombAdversaire(x, y, CaseValueVue.TOUCHE);
			//renvoyer TOUCHE
		}
		else{
			this.notifyBombAdversaire(x, y, CaseValueVue.PLOUF);
			//renvoyer MISSED
		}
	}
	
	/**
	 * Bombarder l'adversaire
	 * Envoie BOMB X Y au serveur
	 * Recoit MISSED/TOUCHE
	 * @param x
	 * @param y
	 * @return
	 */
	public void bombJoueur(int x, int y){
		
		//DEMANDER ET RECEVOIR REPONSE
		String reponse="TOUCHE";
		casesJoueur[x][y].v=CaseValue.TOUCHE;
		if(reponse.equals("TOUCHE")){
			this.notifyBombJoueur(x, y, CaseValueVue.TOUCHE);
			this.nbBateauxTouchesJoueur++;
		}
		if(reponse.equals("MISSED")){
			this.notifyBombJoueur(x, y, CaseValueVue.PLOUF);
		}
		this.notifyTour();
	}
	

	/**
	 * Reinitialiser la grille
	 */
	@Override
	public void reinit() {
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				casesJoueur[i][j].v=CaseValue.NONE;
			}
		}
		this.notifyReinit();
	}

	
	
	/**
	 * Test de console, affiche le contenu de la grille
	 */
	public void afficher()
	{
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				int idB=casesAdversaire[i][j].idBateau;
				if(idB!=-1){
					System.out.print(bateaux[idB].getType()+"("+casesAdversaire[i][j].v+")"+"\t");
				
				}else
					System.out.print(TypeBateau.NONE.name()+"("+casesAdversaire[i][j].v+")"+"\t");
			}
			System.out.println();
		}
		
		for(int i=0; i<longueurX; i++){
			for(int j=0; j<longueurY; j++){
				int idB=casesAdversaire[i][j].idBateau;
				System.out.print(idB+" ");
				
			}
			System.out.println();
		}
		
	}

	
	 
	
	

	



	
}


