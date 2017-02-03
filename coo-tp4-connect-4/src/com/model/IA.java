package com.model;
import java.util.Random;


/**
 * Classe implémentant une IA.
 * Utilisé dans le GrilleModel, on lance un thread et effectue une action du joueur IA quand c'est son tour.
 * 
 * @author loick
 *
 */
public class IA extends Thread {

	protected GridModel g;
	protected volatile boolean running = true;
	
	
	/**
	 * Lance le thread et execute des actions toutes les x frames
	 * @param f Fenetre de jeu
	 */
	public IA(GridModel g) {
		this.g = g;
		this.start();
	}

	/**
	 * Stoppe le thread
	 */
	public void arret() { // Méthode 2
		running = false;
	}
	public void reprendre() { // Méthode 2
		running = true;
	}

	/**
	 * Boucle effectué lorsque le thread est lancé.
	 * On calcule et ajoute un trait pour l'IA lorsque son tour arrive.
	 */
	public void run() {
		while (running) {
			// if(f.lePanneau.testGagne())thread.stop();
			
			
			try {
				
				Thread.sleep(100);
				if(g.isIA[g.tour])
					//IA.this.deciderAlea();
					IA.this.deciderAlea();
				
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
		
		boolean decided=false;
		
		while(!decided)
		{
			Random r = new Random();
			int res = r.nextInt(g.rows);
			
			if(g.ajoutJeton(res))
			{	
				if(g.verifWin())this.arret();
				decided=true;
			}
			
		}
	}
	

}
