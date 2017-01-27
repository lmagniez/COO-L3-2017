package com.observer;

import java.util.ArrayList;

import com.model.BoxValues;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void updateWinner(ArrayList<BoxValues> winners);
	public void updateSquare(int x, int y, BoxValues v);
	public void updateLineH(int x, int y, BoxValues v);
	public void updateLineV(int x, int y, BoxValues v);
	public void updateTour(int tour);
	public void updateReinit();
}
