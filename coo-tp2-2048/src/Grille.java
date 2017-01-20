import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Classe implémentant une grille de jeu (un ensemble de case)
 * C'est ici que se passera la plupart des actions de jeu.
 * @author loick
 *
 */

public class Grille extends JPanel implements Cloneable{

	
	public static final int NB_COLONNES = 4;
	public static final int NB_LIGNES = 4;
	
	protected int nbSet;
	protected Case[][] grid;
	
	/**
	 * Initialise la grille
	 */
	public Grille()
	{
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.setMaximumSize(new Dimension (400,400));
		
		this.grid=new Case[NB_LIGNES][NB_COLONNES];
		for(int i=0; i<NB_LIGNES; i++)
		{	
			for(int j=0; j<NB_COLONNES; j++)
			{
				c.gridx=j;
				c.gridy=i;
				c.insets=new Insets(3,3,3,3);;
				
				grid[i][j]=new Case();
				this.add(grid[i][j],c);
			}
		}
		
		this.nbSet=0;
		
		this.apparitionCase();
		
		this.repaint();
		this.revalidate();
		
		
	}
	
	/**
	 * Constructeur pour cloner l'objet
	 * @param nbSet
	 * @param g
	 */
	public Grille(int nbSet, Case[][] g)
	{
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		this.nbSet=nbSet;
		this.grid=new Case[NB_LIGNES][NB_COLONNES];
		for(int i=0; i<NB_LIGNES; i++)
		{	
			for(int j=0; j<NB_COLONNES; j++)
			{
				c.gridx=j;
				c.gridy=i;
				c.insets=new Insets(3,3,3,3);;
				grid[i][j]=g[i][j].clone();
				this.add(grid[i][j],c);
			}
		}
	}
	
	public void refresh()
	{
		for(int i=0; i<NB_LIGNES; i++)
		{	
			for(int j=0; j<NB_COLONNES; j++)
			{
			
				grid[i][j].revalidate();
				grid[i][j].repaint();
			}
		
		}
	}
	
	/**
	 * Essaye de fusionner des cases en allant dans une direction
	 * @param d Direction empruntée
	 * @param s Score (pour mettre à jour)
	 * @param change effectuer un changement (utile pour tester si on peut effectuer une action ou non)
	 * @return True/False si il y a eu fusion ou non
	 */
	public boolean fusion(Direction d, Score s, boolean change)
	{
		Case pred;
		boolean res=false;
		
		if(d==Direction.SUD)
		{
			//pour chaque colonne y
			for(int l=0; l<NB_COLONNES; l++)
			{
				//on regarde si on peut fusionner pred avec suiv
				for(int i=NB_LIGNES-1; i>=0; i--)
				{
					pred=grid[i][l];
					if(pred.value!=0)
						//cherche le suivant
						for(int j=i-1; j>=0; j--)
						{
							if(pred.value==grid[j][l].value)
							{	
								s.addScore(grid[j][l].value*2);
								if(!change)return true;
								grid[j][l].changeValue(grid[j][l].value*2);
								pred.changeValue(0);
								nbSet--;
								i=j;
								res=true;
								break;
							}
							
						}
				}
			}			
		}
		
		if(d==Direction.NORD)
		{
			//pour chaque colonne y
			for(int l=0; l<NB_COLONNES; l++)
			{
				//on regarde si on peut fusionner pred avec suiv
				for(int i=0; i<NB_LIGNES-1; i++)
				{
					pred=grid[i][l];
					if(pred.value!=0)
						//cherche le suivant
						for(int j=i+1; j<NB_LIGNES; j++)
						{
							if(pred.value==grid[j][l].value)
							{	
								s.addScore(grid[j][l].value);
								if(!change)return true;
								grid[j][l].changeValue(grid[j][l].value*2);
								pred.changeValue(0);
								nbSet--;
								i=j;
								res=true;
								break;
							}
							
							
						}
				}
			}	
		}
		//est=sud
		if(d==Direction.EST)
		{
			//pour chaque ligne x
			for(int l=0; l<NB_LIGNES; l++)
			{
				//on regarde si on peut fusionner pred avec suiv
				for(int i=NB_COLONNES-1; i>=0; i--)
				{
					pred=grid[l][i];
					if(pred.value!=0)
						//cherche le suivant
						for(int j=i-1; j>=0; j--)
						{
							if(pred.value==grid[l][j].value)
							{	
								s.addScore(grid[l][j].value);
								if(!change)return true;
								grid[l][j].changeValue(grid[l][j].value*2);
								pred.changeValue(0);
								nbSet--;
								i=j;
								res=true;
								break;
							}
							
						}
				}
			}			
		}
		
		if(d==Direction.OUEST)
		{
			//pour chaque ligne x
			for(int l=0; l<NB_LIGNES; l++)
			{
				//on regarde si on peut fusionner pred avec suiv
				for(int i=0; i<NB_COLONNES-1; i++)
				{
					pred=grid[l][i];
					if(pred.value!=0)
						//cherche le suivant
						for(int j=i+1; j<NB_COLONNES; j++)
						{
							//2 cases adjacentes identiques
							if(pred.value==grid[l][j].value)
							{	
								s.addScore(grid[l][j].value);
								if(!change)return true;
								grid[l][j].changeValue(grid[l][j].value*2);
								pred.changeValue(0);
								nbSet--;
								i=j;
								res=true;
								break;
							}
						}
				}
			}	
		}
		
		return res;
	}
	

	/**
	 * Essaye de déplacer des cases en allant dans une direction
	 * @param d Direction empruntée 
	 * @param change effectuer un changement (utile pour tester si on peut effectuer une action ou non)
	 * @return True/False si il y a eu déplacement ou non
	 */
	public boolean deplacerCase(Direction d, boolean change)
	{
		boolean res=false;
		if(d==Direction.OUEST )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=1; i<NB_COLONNES; i++)
					for(int j=1; j<NB_COLONNES;j++)
						if(grid[ligne][j-1].value==0&&grid[ligne][j].value!=0)
						{
							if(!change)return true;
							
							//animation ici?
							
							grid[ligne][j-1].changeValue(grid[ligne][j].value);
							grid[ligne][j].changeValue(0);
							res=true;
						}
		}
		
		if(d==Direction.EST )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=NB_COLONNES-2; i>=0; i--)
					for(int j=NB_COLONNES-2; j>=0;j--)
						if(grid[ligne][j+1].value==0&&grid[ligne][j].value!=0)
						{
							if(!change)return true;
							grid[ligne][j+1].changeValue(grid[ligne][j].value);
							grid[ligne][j].changeValue(0);
							res=true;
						}
		}
		
		if(d==Direction.NORD )
		{
			for(int col=0; col<NB_COLONNES; col++)
				for(int i=1; i<NB_LIGNES; i++)
					for(int j=1; j<NB_LIGNES;j++)
						if(grid[j-1][col].value==0&&grid[j][col].value!=0)

						{
							if(!change)return true;
							grid[j-1][col].changeValue(grid[j][col].value);
							grid[j][col].changeValue(0);
							res=true;
						}
		}
		
		if(d==Direction.SUD)
		{
			for(int col=0; col<NB_COLONNES; col++)
				for(int i=NB_LIGNES-2; i>=0; i--)
					for(int j=NB_LIGNES-2; j>=0;j--)
						if(grid[j+1][col].value==0&&grid[j][col].value!=0)
						{
							if(!change)return true;
							grid[j+1][col].changeValue(grid[j][col].value);
							grid[j][col].changeValue(0);
							res=true;
						}
		}
		
		return res;
	}
	
	
	/**
	 * Teste si on peut se déplacer ou non
	 * @return peut se déplacer ou non
	 */
	public boolean canMove()
	{
		Score s = new Score();
		return
		deplacerCase(Direction.NORD,false)
		||deplacerCase(Direction.SUD,false)
		||deplacerCase(Direction.EST,false)
		||deplacerCase(Direction.OUEST,false)
		||fusion(Direction.NORD,s,false)
		||fusion(Direction.SUD,s,false)
		||fusion(Direction.EST,s,false)
		||fusion(Direction.OUEST,s,false);
	}
	
	
	/**
	 * Recherche une case vide et la change en case de 2 ou 4
	 */
	public void apparitionCase()
	{
		int nbCaseRestante=NB_COLONNES*NB_LIGNES-nbSet;
		Random r= new Random();
		int position =r.nextInt(nbCaseRestante)+1; //on va remplacer la ième case non occupée
		int cpt=0;
		
		for(int i=0; i<NB_LIGNES; i++)
		{
			for(int j=0; j<NB_COLONNES; j++)
			{
				if(this.grid[i][j].value==0)
					cpt++;
				if(cpt==position)
				{
					int res = r.nextInt(5);
					if(res==4)
						grid[i][j].changeValue(4);
					else
						grid[i][j].changeValue(2);
					this.nbSet++;
					return;
				}
			}
		}
		
		
	}
	/**
	 * Teste si une case de valeur x est dans la grille 
	 * @param value valeur a tester
	 * @return case présente?
	 */
	public boolean isInside(int value)
	{
		for(int i=0; i<NB_LIGNES; i++)
			for(int j=0; j<NB_COLONNES; j++)
				if(this.grid[i][j].value==value)
					return true;
		return false;
	}
	
	/**
	 * Réinitialiser la grille
	 */
	public void reinit()
	{
		for(int i=0; i<Grille.NB_LIGNES; i++)
			for(int j=0; j<Grille.NB_COLONNES; j++)
			{
				this.grid[i][j].reinit();
			}
		this.nbSet=0;
		this.apparitionCase();
	}
	
	public Grille clone()
	{
		return new Grille(this.nbSet,this.grid);
		
		
	}
	
}
