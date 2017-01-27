package com.model;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.vue.grille.Vue2;

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
	
	protected final int[][] tabRep={{4,4},{4,-1},{4,1},{4,2},{4,3},{1,-1},{1,1},{1,2},{2,-1},{2,2},{3,-1},{3,1},{3,2},{3,3}}; 
	
	/**
	 * Lance le thread et execute des actions toutes les x frames
	 * @param f Fenetre de jeu
	 */
	public IA(GrilleModel g) {
		this.g = g;
		this.start();
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
	 * Décision aléatoire, non utilisé
	 * @param g Grille de jeu
	 * @param score Score du jeu
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
			
			//horizontal
			int res2=r.nextInt(g.nbLigne);
			int res3=r.nextInt(g.nbLigne+1);
			
			if(res==0)
				decided=g.ajoutTraitH(res2, res3);
			
			if(res==1)
				decided=g.ajoutTraitV(res3, res2);
		}
	}
	
	/**
	 * En fonction d'un trait vertical, on récupère un couple (c1,c2) 
	 * correspondant au nombre de trait des carrés liés au trait ajouté.
	 * 
	 * @param i abscisse du trait
	 * @param j ordonée du trait
	 * @return un couple int[2] correspondant au nombre de traits des 2 carrés (-1 si pas de carré. ex: bordures) 
	 */
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
	
	/**
	 * En fonction d'un trait horizontal, on récupère un couple (c1,c2) 
	 * correspondant au nombre de trait des carrés liés au trait ajouté.
	 * 
	 * @param i abscisse du trait
	 * @param j ordonée du trait
	 * @return un couple int[2] correspondant au nombre de traits des 2 carrés (-1 si pas de carré. ex: bordures) 
	 */
	public int[] getNbTraitsFromTraitH(int i, int j)
	{
		
		int res[]=new int[2];
		res[0]=-1;
		res[1]=-1;
		//déjà set: cas sortant
		if(g.coteH[i][j].v!=BoxValues.NONE)
		{
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
		return res;
	}
	
	
	/**
	 * Méthode de décision de l'IA
	 * On va regarder pour chaque trait quel est le coup le plus avantageux.
	 * On utilisera les deux méthodes précédentes et on cherchera les couples dans cet ordre:
	 * +++
	 * (4,4): optimal (On peut former deux carrés)
	 * (4,3)/(3,4): prioritaire (On peut former un carré)
	 * ...
	 * (3,2): à éviter (On laisse au joueur suivant former 1 carré)
	 * (3,3): pire cas (On laisse au joueur suivant former 2 carrés) 
	 * ---
	 */
	public void decider() {
		
		int coupleCotesH[][][]=new int[g.nbLigne][g.nbLigne+1][2];
		int coupleCotesV[][][]=new int[g.nbLigne+1][g.nbLigne][2];
		
		ArrayList<int[]> listH=new ArrayList<int[]>();
		ArrayList<int[]> listV=new ArrayList<int[]>();
		
		
		for(int i=0; i<g.nbLigne; i++){
			for(int j=0; j<g.nbLigne+1; j++){
				coupleCotesH[i][j]=this.getNbTraitsFromTraitH(i, j);
				coupleCotesV[j][i]=this.getNbTraitsFromTraitV(j, i);
			}
		}
		
		
		
		for(int i=0; i<tabRep.length; i++){
			int test[] = tabRep[i];
			int indices[];
			for(int j=0; j<g.nbLigne; j++){
				for(int k=0; k<g.nbLigne+1; k++){
					if((test[0]==coupleCotesH[j][k][0]&&test[1]==coupleCotesH[j][k][1])||
						(test[1]==coupleCotesH[j][k][0]&&test[0]==coupleCotesH[j][k][1])){
						//g.ajoutTraitH(j, k);
						//return;
						indices=new int[2];
						indices[0]=j;
						indices[1]=k;
						listH.add(indices);
						
					}
					if((test[0]==coupleCotesV[k][j][0]&&test[1]==coupleCotesV[k][j][1])||
						(test[1]==coupleCotesV[k][j][0]&&test[0]==coupleCotesV[k][j][1])){
						//g.ajoutTraitV(k, j);
						//return;
						indices=new int[2];
						indices[0]=k;
						indices[1]=j;
						listV.add(indices);
					}
				}
			}
			
			//on a trouvé un ou plusieurs couples (n1,n2)/(n2,n1)
			//on en choisit un au hasard
			if(!listH.isEmpty()&&listV.isEmpty())
			{
				
				Random r= new Random();
				int indice=r.nextInt(listH.size());
				g.ajoutTraitH(listH.get(indice)[0], listH.get(indice)[1]);
			}
			else 
			if(listH.isEmpty()&&!listV.isEmpty())
			{
				Random r= new Random();
				int indice=r.nextInt(listV.size());
				g.ajoutTraitV(listV.get(indice)[0], listV.get(indice)[1]);
			}
			else 
			if(!listH.isEmpty()&&!listV.isEmpty())
			{
				Random r= new Random();
				
				int listHOrV=r.nextInt(2);
				
				if(listHOrV==0)
				{
					int indice=r.nextInt(listV.size());
					
					g.ajoutTraitV(listV.get(indice)[0], listV.get(indice)[1]);
					return;
				}
				else if(listHOrV==1)
				{
					int indice=r.nextInt(listH.size());
	
					g.ajoutTraitH(listH.get(indice)[0], listH.get(indice)[1]);
					return;
				}
			}		
			
		}
	}

}
