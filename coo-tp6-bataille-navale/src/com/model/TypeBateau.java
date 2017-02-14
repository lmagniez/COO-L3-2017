package com.model;

public enum TypeBateau {
	NONE,BATEAU_2,BATEAU_3,BATEAU_4,BATEAU_5;
	
	/**
	 * Accéder à la CaseValue en fonction d'un entier
	 * @param x entier 
	 * @return CaseValue associé à l'entier
	 */
	
	public static TypeBateau fromInteger(int x) {
        switch(x) {
        case 2:
        	return BATEAU_2;
        case 3:
        	return BATEAU_3;
        case 4:
        	return BATEAU_4;
        case 5:
        	return BATEAU_5;
        case 0:
        	return NONE;
        }
        
        return null;
    }
	
	public static int fromType(TypeBateau t) {
        switch(t) {
        case BATEAU_2:
            return 2;
        case BATEAU_3:
            return 3;
        case BATEAU_4:
        	return 4;
        case BATEAU_5:
        	return 5;
        }
        
        return -1;
    }
	
}

