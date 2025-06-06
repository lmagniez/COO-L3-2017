package com.model;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


/**
 * Classe implémentant une IA.
 * Utilisé dans le GrilleModel, on lance un thread et effectue une action du joueur IA quand c'est son tour.
 * 
 * @author loick
 *
 */
public class IA extends Thread {

	protected GrilleModel g;
	protected volatile boolean running = true;
	
	/**
	 * Lance le thread et execute des actions toutes les x frames
	 * @param f Fenetre de jeu
	 */
	public IA(GrilleModel g) {
		this.g = g;
	}


	/**
	 * Stoppe le thread
	 */
	public void arret() { // Méthode 2
		running = false;
	}

	/**
	 * Boucle effectué lorsque le thread est lancé.
	 * On calcule et ajoute un trait pour l'IA lorsque son tour arrive.
	 */
	public void run() {
		while (running) {
			// if(f.lePanneau.testGagne())thread.stop();
			
			
			try {
				
				Thread.sleep(200);
				if(g.isIA[g.tour.ordinal()-1])
					IA.this.decider();
				
			} catch (InterruptedException e) {
				//
			}
		}

	}

	/**
	 * Décision aléatoire, non utilisé
	 */
	public void deciderAlea() {
		
		for(int i=0; i<g.nbX; i++){
			for(int j=0; j<g.nbY; j++){
				
				boolean ok=false;
				
				boolean res[]=g.peutPlacer(i, j, g.tour);
				for(int k=0; k<8; k++){
					if(res[k]){
						ok=true;
						break;
					}
				}
				
				if(ok){
					
					g.remplirCase(i, j, res, g.tour);
					g.changerTour();
					
					ArrayList<int[]> liste=g.peutJouer(g.getTour());
					
					if(liste.size()==0){
						g.changerTour();
						liste=g.peutJouer(g.getTour());
						if(liste.size()==0)
							g.getWinner();
					}
					
					this.g.notifyPosJouable(liste);
					
					return;
				}
				
			}
			
			
			
		}
	}
	
	/**
	 * Décision de l'IA.
	 * Va chercher la case lui conférant le plus de points (voir rapport).
	 */
	public void decider() {
		
		int max=-1; int imax=-1; int jmax=-1;
		
		for(int i=0; i<g.nbX; i++){
			for(int j=0; j<g.nbY; j++){
				
				
				int res=g.nbCasesPrises(i, j, g.tour);
				if(res>max){
					max=res;
					imax=i;
					jmax=j;
				}
				
			}		
		}

		boolean ok=false;
		
		boolean res[]=g.peutPlacer(imax, jmax, g.tour);
		for(int k=0; k<8; k++){
			if(res[k]){
				ok=true;
				break;
			}
		}
		
		if(ok){
			
			g.remplirCase(imax, jmax, res, g.tour);
			g.changerTour();
			
			ArrayList<int[]> liste=g.peutJouer(g.getTour());
			if(liste.size()==0){
				g.changerTour();
				liste=g.peutJouer(g.getTour());
				if(liste.size()==0){
					g.getWinner();
					this.arret();
				}
			}
			
			this.g.notifyPosJouable(liste);
			return;
			
		}
		
	}
	
}
	