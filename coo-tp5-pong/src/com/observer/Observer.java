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
	
	public void updateNewBalle(int idBalle, int posX, int posY);
	public void updatePosBalle(int idBalle, int posX, int posY);
	public void updatePosRacket(int idRacket, int posX, int posY);
	public void initMurH(int idMur, int posX, int posY);
	
	
	
	public void updateNewBonus(int idB, int x, int y);
	public void updateEraseBonus(int idB);
	
}
