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
	 * @param g Grille de jeu
	 * @param score Score du jeu
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
					if(!g.peutJouer(g.getTour())){
						g.changerTour();
						if(!g.peutJouer(g.getTour()))
							g.getWinner();
					}
					return;
				}
				
			}
			
			
			
		}
	}
	
	
	public void decider() {
		
		System.out.println(">>>>>>>>DECIDE");
		
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
		
		System.out.println(max+" "+imax+" "+jmax);
		

		boolean ok=false;
		
		boolean res[]=g.peutPlacer(imax, jmax, g.tour);
		for(int k=0; k<8; k++){
			if(res[k]){
				System.out.println("ok!");
				ok=true;
				break;
			}
		}
		
		if(ok){
			
			g.remplirCase(imax, jmax, res, g.tour);
			g.changerTour();
			if(!g.peutJouer(g.getTour())){
				g.changerTour();
				if(!g.peutJouer(g.getTour())){
					g.getWinner();
					this.arret();
				}
			}
			return;
		}
		
	}
	
}
	