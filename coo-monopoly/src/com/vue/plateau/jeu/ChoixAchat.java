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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
public class ChoixAchat extends JPanel{

	protected EcranJeu ecran;
	protected ButtonMenu confirmer;
	protected ButtonMenu annuler;
	protected JTextArea textBox;
	protected JPanel panneauLateral ;
	protected String messagePrincipal;
	
	protected JPanel carte;
	
	/**
	 * Constructeur 
	 * @param e Ecran de jeu
	 */
	public ChoixAchat(EcranJeu e){
		
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
		
		
		/*
		JLabel l2 = new JLabel();
		Icon image=new ImageIcon("Sprites/pieces/BOTTOM/"+1+".png");
		image=transform((ImageIcon) image,ConstantesVue.CASE_WIDTH/2,ConstantesVue.CASE_HEIGHT/2);
		l2.setIcon(image);
		l2.setAlignmentY(TOP_ALIGNMENT);
		panneauLateral.add(l2);
		*/
		
		
		textBox=initTextArea("Message");
		textBox.setEditable(false);
		
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(panneauLateral);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(textBox);
		
		
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
		
		panneauLateral.removeAll();
		
		Case c = this.ecran.getP().getCases()[position];
		if(c.type==TypeCase.TERRAIN)
			this.carte=new CarteAchatTerrain(c.idCase, c.nom, c.couleurTerrain, c.prixAchat, c.loyers, c.prixMaison);
		else if(c.type==TypeCase.GARE)
			this.carte=new CarteAchatGare(c.idCase, c.nom, c.prixAchat, c.loyers);
		else if(c.type==TypeCase.SERVICE)
			this.carte=new CarteAchatService(c.idCase, c.nom, c.prixAchat, c.loyers);
		
		panneauLateral.add(carte);
		
		this.messagePrincipal="Acheter \n'"+c.nom+"\npour "+c.prixAchat+" ?";
		this.textBox.setText(messagePrincipal);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			InfoJoueur info=ChoixAchat.this.ecran.getS().getJoueurs()[ChoixAchat.this.ecran.getTour()];
			int idJoueur=ChoixAchat.this.ecran.getTour();
			int posJoueur=ChoixAchat.this.ecran.getP().getPions()[idJoueur].position;
			
			if(command=="Accepter")
			{
				System.out.println("ACCEPTER");
				ChoixAchat.this.ecran.getVue().getControler().requestAchat(idJoueur,posJoueur);
				//ChoixAchat.this.setVisible(false);
			}
			
			
			if(command=="Refuser")
			{
				
				ChoixAchat.this.ecran.getVue().getControler().requestEnchere(idJoueur,posJoueur);
				ChoixAchat.this.setVisible(false);
			}
		} 
	}
	
	/**
	 * Initialisation de la zone de texte.
	 * @param s Le contenu de la JTextArea
	 * @return La JTextArea initialisé
	 */
	
	public JTextArea initTextArea(String s){
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setColumns(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		textArea.setMargin(new Insets(10,10,10,10));
		return textArea;

	}
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}

	public void setMessage(String msg) {
		System.out.println("set!! "+msg);
		this.textBox.setText(this.messagePrincipal+" \n"+msg);
	}
}
