package com.observer;

import java.util.ArrayList;

import com.model.BoxValues;
import com.model.CaseValue;
import com.model.patternWin;
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
	
	public void notifyNewChip(int x, int y, CaseValue v);
	public void notifyWinner(int x, int y, patternWin p);
	
	
	public void notifyWinner(ArrayList<BoxValues> winner);
	public void notifyReinit();
	
}
