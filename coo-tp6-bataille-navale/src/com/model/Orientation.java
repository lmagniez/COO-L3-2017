package com.model;

public enum Orientation {
	VERTICAL,HORIZONTAL;
	
	public static Orientation fromInteger(int x) {
        switch(x) {
        case 0:
            return VERTICAL;
        case 1:
            return HORIZONTAL;
        }
        
        return null;
    }
	
}
