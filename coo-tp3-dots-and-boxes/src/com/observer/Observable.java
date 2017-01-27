package com.observer;

import java.util.ArrayList;

import com.model.BoxValues;
import com.vue.titre.Vue1;

/**
 * Interface des différents elements observables
 * @author loick
 *
 */
public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyNewLineV(int x, int y, BoxValues v);
	public void notifyNewLineH(int x, int y, BoxValues v);
	public void notifyNewSquare(int x, int y, BoxValues v);
	public void notifyTour(int tour);
	
	public void notifyWinner(ArrayList<BoxValues> winner);
	public void notifyReinit();
	
}
