package com.vue.plateau;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesVue;

public class InfoJoueur extends JPanel{

	protected Score score;
	protected JLabel nomJoueur;
	protected JLabel argentJoueur;
	protected JLabel pionJoueur;
	protected JButton lanceDes;
	
	protected int idJoueur;
	protected int argent;
	protected int idIcon;
	protected boolean[] acquisition;
	
	
	public InfoJoueur(Score s,int idJoueur, int argent, int idIcon){
		
		this.acquisition=new boolean[ConstantesModel.NB_CASES];
		for(int i=0; i<ConstantesModel.NB_CASES; i++)
			this.acquisition[i]=false;
		
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_INFO_X,ConstantesVue.DIMENSION_INFO_Y));
		
		this.setLayout(null);
		
		this.score=s;
		this.idIcon=idIcon;
		this.argent=argent;
		this.idJoueur=idJoueur;
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(200,200,200,90));
		
		Object items[]={
				transform(new ImageIcon("./Sprites/pion/1.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/2.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/3.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/4.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/5.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/6.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/7.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/8.png"),50,50)
		};
		
		pionJoueur=new JLabel();
		pionJoueur.setIcon((Icon) items[idJoueur]);
		nomJoueur=new JLabel("Joueur "+idJoueur);
		argentJoueur=new JLabel("Argent : "+argent);
		lanceDes=new JButton("Lancer dés");
		lanceDes.addActionListener(new ButtonListener());
		
		this.add(pionJoueur);
		this.add(nomJoueur);
		this.add(argentJoueur);
		this.add(lanceDes);
		
		pionJoueur.setLocation(50,50);
		pionJoueur.setSize(50,50);
		nomJoueur.setSize(nomJoueur.getPreferredSize());
		nomJoueur.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*1/4);
		argentJoueur.setSize(argentJoueur.getPreferredSize());
		argentJoueur.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*2/4);
		lanceDes.setSize(lanceDes.getPreferredSize());
		lanceDes.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*3/4);
		lanceDes.setEnabled(false);
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Lancer dés")
			{
				//Choix.this.setVisible(false);
				InfoJoueur.this.score.ecran.vue.controler.requestLancerDes(InfoJoueur.this.idJoueur);
			}
			
		} 
	}
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		if(this.score.ecran.tour==this.idJoueur)
			g.fillOval(20, 20, 10, 10);
    }
	
	
}
