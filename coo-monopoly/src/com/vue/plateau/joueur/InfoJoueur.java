package com.vue.plateau.joueur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.model.plateau.cases.TerrainModel;

/**
 * Représente les informations pour un joueur
 * @author loick
 *
 */
public class InfoJoueur extends JPanel{

	protected Score score;
	protected JLabel nomJoueur;
	protected JLabel argentJoueur;
	protected JLabel pionJoueur;
	protected JLabel enPrison;
	protected JLabel cartePrison;
	
	protected JButton lanceDes;
	protected JButton prop;
	
	private int patrimoine;
	protected int idJoueur;
	protected int argent;
	protected int idIcon;
	private boolean[] acquisition;
	private boolean gameOver;
	
	
	/**
	 * Constructeur
	 * @param s Score
	 * @param idJoueur id du joueur
	 * @param argent argent du joueur
	 * @param idIcon icone du joueur
	 */
	public InfoJoueur(Score s,int idJoueur, int argent, int idIcon){
		
		this.setAcquisition(new boolean[ConstantesModel.NB_CASES]);
		for(int i=0; i<ConstantesModel.NB_CASES; i++)
			this.getAcquisition()[i]=false;
		
		patrimoine=0;
		
		
		if(idJoueur==1&&ConstantesParam.J1_GOD_MODE){
			for(int i=0; i<ConstantesModel.NB_CASES; i++)
				this.getAcquisition()[i]=true;
			
		}
		
		
		
		this.gameOver=false;
		
//---->		
		/*
		Random r = new Random();
		for(int i=0; i<10;i++)
		{
			int res=r.nextInt(ConstantesModel.NB_CASES);
			this.acquisition[res]=true;
		}
		*/
		
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
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/1.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/2.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/3.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/4.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/5.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/6.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/7.png")),50,50),
				transform(new ImageIcon(getClass().getResource("/Sprites/pion/8.png")),50,50)
		};
		
		pionJoueur=new JLabel();
		pionJoueur.setIcon((Icon) items[idIcon]);
		nomJoueur=new JLabel(ConstantesParam.NAMES[idJoueur]);
		argentJoueur=new JLabel("Argent : "+argent);
		lanceDes=new JButton("Lancer dés");
		lanceDes.addActionListener(new ButtonListener());
		prop=new JButton("Propriétés du joueur");
		prop.addActionListener(new ButtonListener());
		
		enPrison= new JLabel("");
		cartePrison= new JLabel("");
		
		
		this.add(pionJoueur);
		this.add(nomJoueur);
		this.add(argentJoueur);
		this.add(lanceDes);
		this.add(prop);
		this.add(enPrison);
		this.add(cartePrison);
		
		pionJoueur.setLocation(50,50);
		pionJoueur.setSize(50,50);
		nomJoueur.setSize(nomJoueur.getPreferredSize());
		nomJoueur.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*1/4);
		argentJoueur.setSize(argentJoueur.getPreferredSize());
		argentJoueur.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*2/4);
		lanceDes.setSize(lanceDes.getPreferredSize());
		lanceDes.setLocation(ConstantesVue.DIMENSION_INFO_X/4,ConstantesVue.DIMENSION_INFO_Y*3/4);
		lanceDes.setEnabled(false);
		prop.setSize(prop.getPreferredSize());
		prop.setLocation(ConstantesVue.DIMENSION_INFO_X/2,ConstantesVue.DIMENSION_INFO_Y*1/4);
		enPrison.setLocation(ConstantesVue.DIMENSION_INFO_X/2,ConstantesVue.DIMENSION_INFO_Y*2/4);
		cartePrison.setLocation(ConstantesVue.DIMENSION_INFO_X/2,ConstantesVue.DIMENSION_INFO_Y*3/4);
		enPrison.setSize(enPrison.getPreferredSize());
		cartePrison.setSize(cartePrison.getPreferredSize());
		
		
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Lancer dés"){
				//Choix.this.setVisible(false);
				InfoJoueur.this.score.ecran.getVue().getControler().requestLancerDes(InfoJoueur.this.idJoueur);
				InfoJoueur.this.lanceDes.setEnabled(false);
			}
			if(command=="Propriétés du joueur"){
				InfoJoueur.this.score.setProprietes(new ProprietesJoueur(InfoJoueur.this.idJoueur,InfoJoueur.this,
						InfoJoueur.this.score.ecran.getP().getCases()));
				InfoJoueur.this.score.afficherProprietes();
			}
			
			
		} 
	}
	
	public void updateCartePrison(boolean has){
		if(has){
			this.cartePrison.setText("Dispose: Carte Prison");
			this.cartePrison.setForeground(Color.blue);
			
		}
		else{
			this.cartePrison.setText("");
		}
		this.revalidate();
		this.repaint();
	}
	
	public void updatePrison(int nbTour){
		if(nbTour>0){
			this.enPrison.setText("Prison: "+nbTour+" tours");
			this.enPrison.setSize(this.enPrison.getPreferredSize());
			this.enPrison.setForeground(Color.red);
		}
		else{
			this.enPrison.setText("");
		}
		this.revalidate();
		this.repaint();
	}
	
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		if(this.score.ecran.getTour()==this.idJoueur)
			g.fillOval(20, 20, 10, 10);
    }

	public boolean[] getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(boolean[] acquisition) {
		this.acquisition = acquisition;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
		this.argentJoueur.setText("Argent: "+argent);
	}

	public JLabel getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(JLabel nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public JLabel getArgentJoueur() {
		return argentJoueur;
	}

	public void setArgentJoueur(JLabel argentJoueur) {
		this.argentJoueur = argentJoueur;
	}

	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int idJoueur) {
		this.idJoueur = idJoueur;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
		if(gameOver==true){
			this.nomJoueur.setForeground(Color.red);
			this.prop.setEnabled(false);
		}
		this.revalidate();
		this.repaint();
	}

	public int getPatrimoine() {
		return patrimoine;
	}

	public void setPatrimoine(int patrimoine) {
		this.patrimoine = patrimoine;
		if(this.score.proprietes!=null)
			this.score.proprietes.patrimoineJoueur.setText("Valeur Patrimoine: "+patrimoine);
	}
	
	
	
}
