package com.model;

/**
 * Enum correspondant aux différentes valeurs que peuvent avoir un trait/carré
 * @author loick
 *
 */
public enum CaseValue {
	J1,J2,NONE;
	
	/**
	 * Accéder à la BoxValue en fonction d'un entier
	 * @param x entier 
	 * @return BoxValues associé à l'entier
	 */
	public static CaseValue fromInteger(int x) {
        switch(x) {
        case 0:
            return J1;
        case 1:
            return J2;
        }
        return null;
    }
	
}
