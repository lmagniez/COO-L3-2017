package reseau;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fenetre extends JFrame implements ActionListener{

	private ImageIcon pierre2,feuille1,feuille2,ciseau1,ciseau2;
	private ImageIcon pierre1;
	
	private Client c;
	private Serveur2 s;
	
	private int id_joueur;
	
	private JButton b1,b2,b3;
	JLabel l1=new JLabel("J1: ");
	
	public Fenetre()
	{
		Toolkit.getDefaultToolkit().sync();
		this.setSize(500, 500);
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(new GridLayout(1,4));
		
		pierre1=transform(new ImageIcon("./img/pierre.png"),50,100);
		feuille1=transform(new ImageIcon("./img/feuille.png"),50,100);
		ciseau1=transform(new ImageIcon("./img/ciseau.png"),50,100);
		
		
		/*
		pierre2=new ImageIcon("./img/pierre.png").getImage();
		feuille1=new ImageIcon("./img/feuille.png").getImage();
		feuille2=new ImageIcon("./img/feuille.png").getImage();
		ciseau1=new ImageIcon("./img/ciseau.png").getImage();
		ciseau2=new ImageIcon("./img/ciseau.png").getImage();
		*/	
		
		
		
		
		 b1=new JButton();
		 b1.addActionListener(this);
		b1.setIcon((Icon) pierre1);
		
		 b2=new JButton();
		b2.addActionListener(this);
		b2.setIcon((Icon) feuille1);
		
		b3=new JButton();
		 b3.addActionListener(this);
		b3.setIcon((Icon) ciseau1);
		
		this.add(l1);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		
	}	
	
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		
		Fenetre f = new Fenetre();
		f.setVisible(true);
		
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		try{
			socket = new Socket(InetAddress.getLocalHost(),1791);
			
			System.out.println("Demande de connexion");
			in=new BufferedReader (new InputStreamReader(socket.getInputStream()));
			String message_distant = in.readLine();
			System.out.println(message_distant);
			
			String idJ = in.readLine();
			f.id_joueur=Integer.parseInt(idJ);
			System.out.println("id:"+f.id_joueur);	
			
			
			socket.close();
		}
		catch(UnknownHostException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

()
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==b1)
		{
			System.out.println("pierre!");
			l1.setText("pierre");
		}
	}
	
}