package com.observer;

import java.util.ArrayList;
import com.model.CaseValue;
import com.model.patternWin;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void updateTour(int tour);
	
	public void updateChip(int x, int y, CaseValue v);
	public void updateWinner(int tour);
		
	public void updateReinit();
}
