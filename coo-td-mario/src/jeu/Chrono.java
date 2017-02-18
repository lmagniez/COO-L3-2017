package jeu;

public class Chrono implements Runnable{

	public Chrono(){
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.scene.repaint();
	}

}
