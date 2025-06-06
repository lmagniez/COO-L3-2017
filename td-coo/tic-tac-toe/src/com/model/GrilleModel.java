package com.model;

import com.observer.Observer;

public class GrilleModel extends AbstractModel {

	private int nbSet=0;
	private Symbole grille[][];
	
	private static String[] tabSymbole={"o","x",""};
	
	
	
	public GrilleModel()
	{
		grille=new Symbole[NB_LIGNE][NB_LIGNE];
		init_grille();
	}

	@Override
	public void init_grille() {
		
		nbSet=0;
		
		for(int i=0; i<NB_LIGNE; i++)
			for(int j=0; j<NB_LIGNE; j++)
			{
				grille[i][j]=Symbole.VIDE;
				notifyObserver(i, j, tabSymbole[Symbole.VIDE.ordinal()]);
			}
	}
	
	public void set_grille(int x, int y) {
        
		//changer symbole string -> Symb
		//Symbole j=Symbole.fromString(s);
		
		Symbole j=Symbole.fromInt(nbSet%2);
		
		System.out.println(j);
		System.out.println(grille[x][y]);
		
		
		if(grille[x][y]==Symbole.VIDE&&j!=Symbole.VIDE)
		{	
			grille[x][y]=j;
        	notifyObserver(x, y, tabSymbole[j.ordinal()]);
        	nbSet++;
        	
        	Symbole s= getWinner();
        	if(s!=null)
        		notifyWinner(s.toString());
        }
        else
        	System.out.println("probleme deplacement");
        	//throw new IllegalMoveException();
        	
    } 

    
    public Symbole getWinner()
    {
    	
    	
    	
    	boolean pattern=false;
    	
    	//chaque ligne
    	for(int i=0; i<NB_LIGNE; i++)
    	{
    		pattern=true;
    		for(int j=0; j<NB_LIGNE-1; j++)
    			if(grille[i][j]!=grille[i][j+1]||grille[i][j]==Symbole.VIDE)
    			{
    				pattern=false;
    				break;
    			}
    		if(pattern)
    			{
    			System.out.println("ligne "+i);
    			return grille[i][0];
    			}
    	}
    	
    	//chaque colonne
    	for(int j=0; j<NB_LIGNE; j++)
    	{
    		pattern=true;
    		for(int i=0; i<NB_LIGNE-1; i++)
    			if(grille[i][j]!=grille[i+1][j]||grille[i][j]==Symbole.VIDE)
    			{
    				pattern=false;
    				break;
    			}
    		if(pattern)
    			{
    			System.out.println("colonne "+j);
    			return grille[0][j];
    			}
    	}
    	
    	//chaque diagonale
    	pattern=true;
    	for(int i=0; i<NB_LIGNE-1; i++)
    		if(grille[i][i]!=grille[i+1][i+1]||grille[i][i]==Symbole.VIDE)
			{
				pattern=false;
				break;
			}
    	if(pattern) 
    		{
    		System.out.println("diag 1");
    		return grille[0][0];
    		}
    			
    	pattern=true;
    	
    	int j=NB_LIGNE-1;
    	for(int i=0; i<NB_LIGNE-1; i++)
    	{
    		if(grille[i][j]!=grille[i+1][j-1]||grille[i][j]==Symbole.VIDE)
    			pattern=false;
    		j--;
    	}
    	if(pattern) 
    		{
    		System.out.println("diag 2");
    		return grille[0][NB_LIGNE-1];
    		}
    			
    	if(nbSet==NB_LIGNE*NB_LIGNE)
    	{
    		return Symbole.VIDE;
    	}
    	
    	return null;
		
    	
    }

    
    
	
	
}
