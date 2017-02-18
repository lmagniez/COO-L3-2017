package jeu;

import javax.swing.JFrame;

public class Main {

	public static Scene scene;
	protected JFrame f;
	protected Scene s;
	
	public Main(){
		f=new JFrame();
		f.setAlwaysOnTop(true);
		f.setTitle("Mario");
		f.setSize(700, 360);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		//f.setFocusable(false);
		
		s=new Scene();
	}
	
	
}
