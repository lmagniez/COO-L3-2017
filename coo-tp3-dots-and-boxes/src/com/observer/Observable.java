package com.observer;

import java.util.ArrayList;

import com.model.BoxValues;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyNewLineV(int x, int y, BoxValues v);
	public void notifyNewLineH(int x, int y, BoxValues v);
	public void notifyNewSquare(int x, int y, BoxValues v);
	
	public void notifyWinner(ArrayList<BoxValues> winner);
}
