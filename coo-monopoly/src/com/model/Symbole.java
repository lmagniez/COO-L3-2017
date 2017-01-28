package com.model;

public enum Symbole {
	ROND(0),
	CROIX(1),
	VIDE(2);
	
	
	private final int symbol;
	private Symbole (int symbol) {
		this.symbol = symbol;
	}

	// getter traditionnel
	public int getSymbol() {
		  return symbol;
	}
	
	public static Symbole fromInt(int text) {
      for (Symbole b : Symbole.values()) {
    	  if (text==b.symbol) {
    		  return b;
	      }
	  }
	  return null;
	}
}



