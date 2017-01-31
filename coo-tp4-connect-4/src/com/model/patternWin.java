package com.model;

public enum patternWin {
	HORIZONTAL,VERTICAL,DIAGONALEHAUT,DIAGONALEBAS,NONE;
	
	/**
	 * Accéder à la BoxValue en fonction d'un entier
	 * @param x entier 
	 * @return BoxValues associé à l'entier
	 */
	public static patternWin fromInteger(int x) {
        switch(x) {
        case 0:
            return HORIZONTAL;
        case 1:
            return VERTICAL;
        case 2:
        	return DIAGONALEHAUT;
        case 3:
        	return DIAGONALEBAS; 
        }
        return null;
    }
	
}