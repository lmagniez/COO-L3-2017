package com.model;

public enum BoxValues {
	J1,J2,J3,J4,NONE,EMPTY_SQUARE;
	
	
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
