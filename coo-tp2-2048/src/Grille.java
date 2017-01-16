import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Grille extends JPanel{

	
	public static final int NB_COLONNES = 4;
	public static final int NB_LIGNES = 4;
	
	private int nbSet;
	protected Case[][] grid;
	
	public Grille()
	{
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		this.grid=new Case[NB_COLONNES][NB_LIGNES];
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
	
	public void fusionVerticale()
	{
		
	}
	public void fusionHorizontale()
	{
	
	}
	
	public void deplacerCase(Direction d)
	{
		if(d==Direction.OUEST )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=1; i<NB_COLONNES; i++)
					for(int j=1; j<NB_COLONNES;j++)
						if(grid[ligne][j-1].value==0)
						{
							grid[ligne][j-1].changeValue(grid[ligne][j].value);
							grid[ligne][j].changeValue(0);
						}
		}
		
		if(d==Direction.EST )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=NB_COLONNES-2; i>=0; i--)
					for(int j=NB_COLONNES-2; j>=0;j--)
						if(grid[ligne][j+1].value==0)
						{
							grid[ligne][j+1].changeValue(grid[ligne][j].value);
							grid[ligne][j].changeValue(0);
						}
		}
		
		if(d==Direction.NORD )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=1; i<NB_COLONNES; i++)
					for(int j=1; j<NB_COLONNES;j++)
						if(grid[j-1][ligne].value==0)
						{
							grid[j-1][ligne].changeValue(grid[j][ligne].value);
							grid[j][ligne].changeValue(0);
						}
		}
		
		if(d==Direction.SUD )
		{
			for(int ligne=0; ligne<NB_LIGNES; ligne++)
				for(int i=NB_COLONNES-2; i>=0; i--)
					for(int j=NB_COLONNES-2; j>=0;j--)
						if(grid[j+1][ligne].value==0)
						{
							grid[j+1][ligne].changeValue(grid[j][ligne].value);
							grid[j][ligne].changeValue(0);
						}
		}
	}
	
	/**
	 * Recherche une case vide et la change en case de 2 ou 4
	 */
	public void apparitionCase()
	{
		int nbCaseRestante=NB_COLONNES*NB_LIGNES-nbSet;
		Random r= new Random();
		int position =r.nextInt(nbCaseRestante)+1; //on va remplacer la ième case non occupée
		System.out.println("position"+position);
		int cpt=0;
		
		for(int i=0; i<NB_LIGNES; i++)
		{
			for(int j=0; j<NB_COLONNES; j++)
			{
				if(this.grid[i][j].value==0)
					cpt++;
				if(cpt==position)
				{
					int res = r.nextInt(2);
					System.out.println(res);
					if(res==1)
						grid[i][j].changeValue(4);
					else
						grid[i][j].changeValue(2);
					this.nbSet++;
					return;
				}
			}
		}
		
		
	}
	
}
