package com.observer;

public interface Observer {
	//public void update(String str); // Le meme type que la methode notify
	public void update(int x, int y, String s);
	public void updateWinner(String s);
}
