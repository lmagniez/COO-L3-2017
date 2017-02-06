package balle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	protected Balle b,b2,b3,b4;
	
	public Fenetre()
	{
		Toolkit.getDefaultToolkit().sync();
		this.setSize(500, 500);
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		b=new Balle(this,"balle1");
		b2=new Balle(this,"balle2");
		b3=new Balle(this,"balle3");
		b4=new Balle(this,"balle4");
	}	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
        g.fillOval(b.x, b.y, b.hx, b.hy);
        g.fillOval(b2.x, b2.y, b2.hx, b2.hy);
        g.fillOval(b3.x, b3.y, b3.hx, b3.hy);
        g.fillOval(b4.x, b4.y, b4.hx, b4.hy);
        
        
        this.validate();
		this.revalidate();
        this.repaint();
    }
	
	public static void main(String[] args) throws InterruptedException {
		
		Fenetre f = new Fenetre();
		//f.lancer();
		
		Thread.sleep(100);
		
		f.b.start();
		f.b2.start();
		f.b3.start();
		f.b4.start();
		
	}
	
}
