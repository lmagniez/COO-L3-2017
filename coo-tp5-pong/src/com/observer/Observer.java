package com.observer;

import java.util.ArrayList;

/**
 * Interface des différents observer. Contient les différents updates.
 */

public interface Observer {
	
	public void updateWinner(int idJoueur);	
	public void updateReinit();
	
	public void updateNewBalle(int idBalle, int posX, int posY);
	public void updatePosBalle(int idBalle, int posX, int posY);
	public void updatePosRacket(int idRacket, int posX, int posY);
	public void initMurH(int idMur, int posX, int posY);
	
	
	
	public void updateNewBonus(int idB, int x, int y);
	public void updateEraseBonus(int idB);
	
	public void updateScore(int sJ1, int sJ2);

	
}
