package com.observer;

import java.util.ArrayList;

import com.model.grid.CaseValue;
import com.model.grid.patternWin;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	
	public void updateWinner(int tour);	
	public void updateReinit();
	
	public void updatePosBalle(int idBalle, int posX, int posY, int diam);
	public void updatePosRacket(int idRacket, int posX, int posY, int width, int height);
	public void initMurH(int idMur, int posX, int posY, int width, int height);
	
}
