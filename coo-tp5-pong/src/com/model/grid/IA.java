package com.model.grid;
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
	protected CaseValue joueur,adversaire;
	
	
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
					IA.this.decider();
				
			} catch (InterruptedException e) {
				//
			}
		}

	}

	/**
	 * Décision aléatoire
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
	
	/**
	 * Decision de l'IA en fonction du joueur
	 */
	public void decider() {
		
			CaseValue joueur,adversaire;
			if(g.tour==0){
				joueur=CaseValue.J1;
				adversaire=CaseValue.J2;
			}
			else{
				joueur=CaseValue.J2;
				adversaire=CaseValue.J1;
			}
		
		
			CaseValue res=CaseValue.NONE;
			
			//test pour soi
			for(int i=0; i<g.rows; i++)
			{
				if(!this.g.columnFull(i))
				{
					this.g.ajoutJetonIA(i,joueur);
					res=this.g.verifWinIA();
					this.g.retirerJetonIA(i);
					if(res!=CaseValue.NONE)
					{
						this.g.ajoutJeton(i);
						this.g.verifWin();
						this.arret();
						return;
					}
						
					
				}
			}
			
			//contre l'adversaire
			for(int i=0; i<g.rows; i++)
			{
				if(!this.g.columnFull(i))
				{
					this.g.ajoutJetonIA(i,adversaire);
					res=this.g.verifWinIA();
					this.g.retirerJetonIA(i);
					if(res!=CaseValue.NONE)
					{
						this.g.ajoutJeton(i);
						return;
					}
						
					
				}
			}
			
			System.out.println("deciderAlea");
			deciderAlea();
			
			this.g.afficher();
	}
	
	
	

}
