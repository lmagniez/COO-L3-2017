package com.model;

/**
 * Enum correspondant aux différentes valeurs que peuvent avoir un trait/carré
 * @author loick
 *
 */
public enum BoxValues {
	J1,J2,J3,J4,NONE,EMPTY_SQUARE;
	
	/**
	 * Accéder à la BoxValue en fonction d'un entier
	 * @param x entier 
	 * @return BoxValues associé à l'entier
	 */
	public static BoxValues fromInteger(int x) {
        switch(x) {
        case 0:
            return J1;
        case 1:
            return J2;
        case 2:
            return J3;
        case 3:
            return J4;
        }
        return null;
    }
	
}
