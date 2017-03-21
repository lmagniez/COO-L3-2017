package com.vue.plateau.jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.model.ConstantesParam;
import com.model.ConstantesVue;
import com.model.plateau.cases.CouleurTerrain;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.plateau.CarteAchatGare;
import com.vue.plateau.CarteAchatService;
import com.vue.plateau.CarteAchatTerrain;
import com.vue.plateau.EcranJeu;
import com.vue.plateau.joueur.InfoJoueur;

/**
 * Représente un écran de choix d'achat
 * @author loick
 *
 */
public class ChoixEchange extends JPanel{

	protected EcranJeu ecran;
	protected ButtonMenu confirmer;
	protected ButtonMenu annuler;
	protected JPanel panneauLateral ;
	protected int position;
	
	protected JPanel panneauJoueur1 ;
	protected JPanel panneauJoueur2 ;
	protected JSpinner argent;
	protected JToggleButton buttonsJ2[]=new JToggleButton[3];
	protected int[] idJoueurs=new int[3];
	
	protected ButtonGroup groupeBoutons;
	
	protected String messagePrincipal;
	protected int idJoueur;
	
	protected JPanel carte;
	
	/**
	 * Constructeur 
	 * @param e Ecran de jeu
	 */
	public ChoixEchange(EcranJeu e){
		
		
		this.ecran=e;
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(200,200,200,90));
		
		
		JLabel l=new JLabel("Choix: ");
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLACK);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(l);
		
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		
		panneauLateral = new JPanel();
		panneauLateral.setLayout(new BoxLayout(panneauLateral,BoxLayout.PAGE_AXIS));
		panneauJoueur1 = new JPanel();
		panneauJoueur1.setLayout(new BoxLayout(panneauJoueur1,BoxLayout.PAGE_AXIS));
		panneauJoueur2 = new JPanel();
		panneauJoueur2.setLayout(new BoxLayout(panneauJoueur2,BoxLayout.PAGE_AXIS));
		
		this.groupeBoutons=new ButtonGroup();
		for(int i=0; i<ConstantesParam.NB_JOUEURS-1; i++){
			this.buttonsJ2[i]=new JToggleButton();
			groupeBoutons.add(buttonsJ2[i]);
		}
		buttonsJ2[0].setSelected(true);
    	
    	
		/*
		JLabel l2 = new JLabel();
		Icon image=new ImageIcon("Sprites/pieces/BOTTOM/"+1+".png");
		image=transform((ImageIcon) image,ConstantesVue.CASE_WIDTH/2,ConstantesVue.CASE_HEIGHT/2);
		l2.setIcon(image);
		l2.setAlignmentY(TOP_ALIGNMENT);
		panneauLateral.add(l2);
		*/
		
		p.add(panneauJoueur1);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(panneauLateral);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(panneauJoueur2);
		
		
		confirmer=new ButtonMenu("Accepter",Colors.textColor2,Colors.case8);
		confirmer.addActionListener(new ButtonListener());
		
		annuler=new ButtonMenu("Refuser",Colors.textColor2,Colors.case8);
		annuler.addActionListener(new ButtonListener());
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p2.add(confirmer);
		p2.add(annuler);
		
		
		this.add(p);
		this.add(p2);
		
		this.setVisible(false);
		
		
	}
	
	/**
	 * Génération du choix
	 * @param idJoueur id du joueur concerné
	 * @param position case à acheter
	 */
	public void genererChoixAchat(int idJoueur, int position){
		
		if(this.ecran.getChoixA().actif||this.ecran.getChoixP().actif)
			return;
		this.idJoueur=idJoueur;
		this.position=position;
		
		panneauLateral.removeAll();
		panneauJoueur1.removeAll();
		panneauJoueur2.removeAll();
		
		
		//ARGENT DEBUT PARTIE
    	SpinnerModel model = new SpinnerNumberModel(1500, 1, 1000000, 100);     
    	argent = new JSpinner(model);
    	argent.setPreferredSize(new Dimension(120,30));
    	
    	panneauJoueur1.add(new JLabel("Joueur "+idJoueur));
		panneauJoueur1.add(argent);
		
		int cpt=0;
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
			if(i!=idJoueur){
				this.buttonsJ2[cpt].setText("Joueur "+i);
				this.idJoueurs[cpt]=i;
				panneauJoueur2.add(buttonsJ2[cpt++]);
			}
		}
		
		
		
		
		//CASE
		Case c = this.ecran.getP().getCases()[position];
		if(c.type==TypeCase.TERRAIN)
			this.carte=new CarteAchatTerrain(c.idCase, c.nom, c.couleurTerrain, c.prixAchat, c.loyers, c.prixMaison);
		else if(c.type==TypeCase.GARE)
			this.carte=new CarteAchatGare(c.idCase, c.nom, c.prixAchat, c.loyers);
		else if(c.type==TypeCase.SERVICE)
			this.carte=new CarteAchatService(c.idCase, c.nom, c.prixAchat, c.loyers);
		
		panneauLateral.add(carte);
		
		this.messagePrincipal="Acheter \n'"+c.nom+"\npour "+c.prixAchat+" ?";
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			InfoJoueur info=ChoixEchange.this.ecran.getS().getJoueurs()[ChoixEchange.this.ecran.getTour()];
			int posJoueur=ChoixEchange.this.ecran.getP().getPions()[idJoueur].position;
			
			if(command=="Accepter")
			{
				int idJ2 = -1;
				for(int i=0; i<3; i++){
					if(ChoixEchange.this.buttonsJ2[i].isSelected())
						idJ2=ChoixEchange.this.idJoueurs[i];
				}
				ChoixEchange.this.ecran.getVue().getControler().requestEchange(ChoixEchange.this.idJoueur, idJ2, ChoixEchange.this.position,(int) ChoixEchange.this.argent.getValue());
				ChoixEchange.this.ecran.getS().getProprietes().refresh();
				//ChoixAchat.this.setVisible(false);
				
			}
			
			
			if(command=="Refuser")
			{
				
				ChoixEchange.this.ecran.getVue().getControler().requestEnchere(idJoueur,posJoueur);
				ChoixEchange.this.setVisible(false);
			}
		} 
	}
	


	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

}
