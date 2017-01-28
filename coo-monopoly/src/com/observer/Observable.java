package com.observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(int x, int y, String s); // String est un exemple
	public void notifyWinner(String s);
}
