package com.model;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.vue.grille.Vue2;

/**
 * Classe implémentant une IA
 * 
 * @author loick
 *
 */
public class IA extends Thread {

	protected GrilleModel g;
	protected volatile boolean running = true;

	/**
	 * Lance le thread et execute des actions toutes les x frames
	 * 
	 * @param f
	 *            Fenetre de jeu
	 */

	public IA(GrilleModel g) {
		this.g = g;
		this.start();
	}

	public void arret() { // Méthode 2
		running = false;
	}

	public void run() {
		while (running) {
			// if(f.lePanneau.testGagne())thread.stop();
			
			
			try {
				
				Thread.sleep(100);
				if(g.isIA[g.tour])
					IA.this.deciderAlea();
				
			} catch (InterruptedException e) {
				//
			}
		}

	}

	/**
	 * Décision aléatoire, non utilisé
	 * 
	 * @param g
	 *            Grille de jeu
	 * @param score
	 *            Score du jeu
	 */
	public void deciderAlea() {
		
		int CoupleCotesH[][][]=new int[g.nbLigne][g.nbLigne+1][2];
		
		for(int i=0; i<g.coteH.length; i++){
			for(int j=0; j<g.coteH[0].length; j++){
				this.getNbTraitsFromTraitH(i, j);
			}
		}	
		
		boolean decided=false;
		
		while(!decided)
		{
			Random r = new Random();
			int res = r.nextInt(2);
	
			System.out.println("res="+res);
			
			//horizontal
			int res2=r.nextInt(g.nbLigne);
			int res3=r.nextInt(g.nbLigne+1);
			
			if(res==0)
				decided=g.ajoutTraitH(res2, res3);
			
			if(res==1)
				decided=g.ajoutTraitV(res3, res2);
		}
	}
	
	
	public int[] getNbTraitsFromTraitV(int i, int j)
	{
		
		
		int res[]=new int[2];
		res[0]=-1;
		res[1]=-1;
		//déjà set: cas sortant
		if(g.coteV[i][j].v!=BoxValues.NONE)
			return res;
		
		
		if(i==0)
		{
			res[0]=-1;
			res[1]=1;//1 trait d'office en ajoutant
			if(g.coteH[i][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i+1][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i][j+1].v!=BoxValues.NONE)
				res[1]++;
		}
		else if(i==g.nbLigne)
		{
			res[1]=-1;
			res[0]=1;
			if(g.coteH[i-1][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i-1][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i-1][j+1].v!=BoxValues.NONE)
				res[1]++;
		}
		else
		{
			res[0]=1;
			res[1]=1;
			
			if(g.coteH[i][j].v!=BoxValues.NONE)
				res[0]++;
			if(g.coteV[i+1][j].v!=BoxValues.NONE)
				res[0]++;
			if(g.coteH[i][j+1].v!=BoxValues.NONE)
				res[0]++;
			
			if(g.coteH[i-1][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i-1][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i-1][j+1].v!=BoxValues.NONE)
				res[1]++;
			
		}
		
		
		return res;
	}
	
	
	public int[] getNbTraitsFromTraitH(int i, int j)
	{
		
		
		int res[]=new int[2];
		res[0]=-1;
		res[1]=-1;
		//déjà set: cas sortant
		if(g.coteH[i][j].v!=BoxValues.NONE)
		{
			System.out.println("i: "+i+" j: "+j+" --> "+res[0]+" "+res[1]);
			
			return res;
		}
		
		if(j==0)
		{
			res[0]=-1;
			res[1]=1;//1 trait d'office en ajoutant
			if(g.coteV[i][j].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i][j+1].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i+1][j].v!=BoxValues.NONE)
				res[1]++;
		}
		else if(j==g.nbLigne)
		{
			res[1]=-1;
			res[0]=1;
			if(g.coteV[i][j-1].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i][j-1].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i+1][j-1].v!=BoxValues.NONE)
				res[1]++;
		}
		else
		{
			res[0]=1;
			res[1]=1;
			
			if(g.coteV[i][j].v!=BoxValues.NONE)
				res[0]++;
			if(g.coteH[i][j+1].v!=BoxValues.NONE)
				res[0]++;
			if(g.coteV[i+1][j].v!=BoxValues.NONE)
				res[0]++;
			
			if(g.coteV[i][j-1].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteH[i][j-1].v!=BoxValues.NONE)
				res[1]++;
			if(g.coteV[i+1][j-1].v!=BoxValues.NONE)
				res[1]++;
			
		}
		
		System.out.println("i: "+i+" j: "+j+" --> "+res[0]+" "+res[1]);
		return res;
	}
	
	
	
	public void decider() {
		
		int CoupleCotesH[][][]=new int[g.nbLigne][g.nbLigne+1][2];
		
		for(int i=0; i<g.nbLigne; i++){
			for(int j=0; j<g.nbLigne+1; j++){
				this.getNbTraitsFromTraitH(i, j);
			}
		}
		
		boolean decided=false;
		
		while(!decided)
		{
			Random r = new Random();
			int res = r.nextInt(2);
	
			System.out.println("res="+res);
			
			//horizontal
			int res2=r.nextInt(g.nbLigne);
			int res3=r.nextInt(g.nbLigne+1);
			
			if(res==0)
				decided=g.ajoutTraitH(res2, res3);
			
			if(res==1)
				decided=g.ajoutTraitV(res3, res2);
		}
	}

}
