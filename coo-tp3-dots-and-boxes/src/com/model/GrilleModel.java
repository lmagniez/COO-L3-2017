package com.model;

import java.util.ArrayList;

/**
 * Modèle abstrait de la grille
 * 
 * @author loick
 *
 */
public class GrilleModel extends AbstractModel {

	protected int nbLigne;
	protected CoteModel coteH[][];
	protected CoteModel coteV[][];
	private CasesModel cases;
	protected boolean isIA[]; 
	private IA ia;

	protected int nbJoueur;
	private int nbTrait;
	protected int tour;

	public final static int NB_COTES = 4;

	/**
	 * Créé une grille et l'initialise (chaque cotés)
	 * 
	 * @param nbLigne
	 */
	public GrilleModel(int nbLigne, int nbJoueur,boolean isIA[]) {
		this.nbJoueur = nbJoueur;
		this.nbLigne = nbLigne;
		this.isIA=isIA;
		this.ia= new IA(this);
		cases = new CasesModel(nbLigne, nbJoueur);
		coteH = new CoteModel[nbLigne][nbLigne + 1];
		coteV = new CoteModel[nbLigne + 1][nbLigne];

		for (int i = 0; i < nbLigne + 1; i++)
			for (int j = 0; j < nbLigne; j++) {
				coteH[j][i] = new CoteModel();
				coteV[i][j] = new CoteModel();
			}

		nbTrait = 0;
		tour = 0;

	}

	/**
	 * @param x abscisse
	 * @param y ordonnée
	 * @return carré de 4 côtés dans l'ordre suivant: Nord, Est, Sud, Ouest
	 */
	public boolean isFullSquare(int x, int y) {
		CoteModel carre[] = new CoteModel[NB_COTES];

		carre[Direction.NORTH.ordinal()] = coteH[x][y];
		carre[Direction.EST.ordinal()] = coteV[x + 1][y];
		carre[Direction.SOUTH.ordinal()] = coteH[x][y + 1];
		carre[Direction.WEST.ordinal()] = coteV[x][y];

		for (int i = 0; i < NB_COTES; i++)
			if (carre[i].v == BoxValues.NONE)
				return false;

		return true;

	}

	/**
	 * Ajout d'un trait horizontal sur le modèle,
	 * Teste si un carré se forme suite à l'ajout,
	 * Teste si il y a fin de partie (tous les carrés sont remplis)
	 * Modifie le prochain joueur qui joue. (le joueur rejoue si il remplit un carré) 
	 * @param x abscisse du trait
	 * @param y ordonée du trait
	 */
	public boolean ajoutTraitH(int x, int y) {
		boolean rejouer;
		rejouer = false;
		//System.out.println("Tour: " + tour + "->" + BoxValues.fromInteger(tour));
		BoxValues v = BoxValues.fromInteger(tour);

		//test sortant: Le coté ne doit pas déjà être utilisé.
		if (coteH[x][y].v != BoxValues.NONE)
			return false;

		coteH[x][y].v = v;
		nbTrait++;
		this.notifyNewLineH(x, y, v);

		//carré du dessus (par rapport au coté)
		if (y != 0){
			if (isFullSquare(x, y - 1)) {
				if (cases.ajoutCarre(x, y - 1, v)) {
					this.notifyNewSquare(x, y - 1, v);
					rejouer = true;
				}
			}
		}
		
		//carré du dessous (par rapport au coté)
		if (y < nbLigne && x < nbLigne) {
			if (isFullSquare(x, y)) {
				if (cases.ajoutCarre(x, y, v)) {
					this.notifyNewSquare(x, y, v);
					rejouer = true;
				}
			}
		}
		
		//test de fin de partie
		if(cases.isGameOver()){
			ArrayList<BoxValues> gagnants= cases.getWinner();
			ia.arret();
			this.notifyWinner(gagnants);
		}
		
		if (!rejouer)
			tour = (tour + 1) % nbJoueur;

		notifyTour(tour);
		
		return true;

	}

	/**
	 * Ajout d'un trait vertical sur le modèle, notifie l'ajout.
	 * Teste si un carré se forme suite à l'ajout, notifie si un ajout.
	 * Teste si il y a fin de partie (tous les carrés sont remplis), notifie si fin de partie.
	 * Modifie le prochain joueur qui joue. (le joueur rejoue si il remplit un carré), notifie la vue.
	 * @param x abscisse du trait
	 * @param y ordonée du trait
	 */
	public boolean ajoutTraitV(int x, int y)
	{
		boolean rejouer;
		rejouer=false;
		BoxValues v=BoxValues.fromInteger(tour%(nbJoueur));
		//System.out.println("Tour: "+tour+"->"+BoxValues.fromInteger(tour));
		
		//test sortant: Le coté ne doit pas déjà être utilisé.
		if(coteV[x][y].v!=BoxValues.NONE)
			return false;
			
		coteV[x][y].v=v;
		nbTrait++;
		this.notifyNewLineV(x, y, v);
		
		//Carré à gauche (par rapport au coté)
		if(x!=0){
			if(isFullSquare(x-1,y)){
				if(cases.ajoutCarre(x-1,y,v)){
					this.notifyNewSquare(x-1, y, v);
					rejouer=true;
				}
			}
		}
		
		//Carré à droite (par rapport au coté)
		if(x<nbLigne&&y<nbLigne){
			if(isFullSquare(x,y)){
				if(cases.ajoutCarre(x,y,v)){
					this.notifyNewSquare(x, y, v);
					rejouer=true;
				}
			}
		}
		
		//Test fin de partie
		if(cases.isGameOver()){
			ArrayList<BoxValues> gagnants= cases.getWinner();
			ia.arret();
			this.notifyWinner(gagnants);
		}
		
		if(!rejouer)
			tour=(tour+1)%nbJoueur;
		
		notifyTour(tour);
		
		return true;
		
	}

	/**
	 * Initialise la grille.
	 * Enleve toute association de traits au joueur.
	 * Enleve toute association de carré au joueur.
	 * Notifie le changement à la vue.
	 */
	public void reinit() {
		for (int i = 0; i < nbLigne + 1; i++) {
			for (int j = 0; j < nbLigne; j++) {
				coteH[j][i].v = BoxValues.NONE;
				coteV[i][j].v = BoxValues.NONE;
			}
		}
		cases.reinit();
		nbTrait=0;
		tour=0;
		this.notifyReinit();
	}

}
