
/**
 * Ecran de GameOver
 * @author loick
 *
 */

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

import com.model.ConstantesModel;
import com.model.ConstantesVue;
import com.model.plateau.cases.CouleurTerrain;
import com.model.plateau.pioche.TypePioche;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.plateau.CarteAchatGare;
import com.vue.plateau.CarteAchatService;
import com.vue.plateau.CarteAchatTerrain;
import com.vue.plateau.EcranJeu;
import com.vue.plateau.joueur.InfoJoueur;

/**
 * Ecran de paiement
 * @author loick
 *
 */
public class ChoixGameOver extends JPanel{

	protected EcranJeu ecran;
	protected ButtonMenu confirmer;
	protected JTextArea textBox;
	//protected JPanel panneauLateral ;
	protected String messagePrincipal;
	protected int idJ1, idCarte;
	protected boolean actif;
	
	protected int prixAPayer;
	protected TypePioche type;
	
	//protected JPanel carte;
	
	/**
	 * Constructeur
	 * @param e Ecran de jeu
	 */
	public ChoixGameOver(EcranJeu e){
		
		//afficher case en question + prix a payer
		//appuyer sur ok
		
		this.ecran=e;
		this.actif=false;
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
		
		//panneauLateral = new JPanel();
		//panneauLateral.setLayout(new BoxLayout(panneauLateral,BoxLayout.PAGE_AXIS));
		
		
		textBox=initTextArea("Message");
		textBox.setEditable(false);
		
		p.add(Box.createRigidArea(new Dimension(5,5)));
		//p.add(panneauLateral);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(textBox);
		
		
		confirmer=new ButtonMenu("Valider",Colors.textColor2,Colors.case8);
		confirmer.addActionListener(new ButtonListener());
		
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p2.add(confirmer);
		
		
		this.add(p);
		this.add(p2);
		
		this.revalidate();
		this.repaint();
		
		this.setVisible(false);
		
		
	}
	
	public void genererGameOver(int idJoueur){
		
		//panneauLateral.removeAll();
		this.actif=true;
		
		idJ1=idJoueur;
		this.messagePrincipal="GAME OVER: Joueur "+idJoueur+" est hors-jeu !!";
		this.textBox.setText(messagePrincipal);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			
			InfoJoueur info=ChoixGameOver.this.ecran.getS().getJoueurs()[ChoixGameOver.this.ecran.getTour()];
			
			int posJoueur=ChoixGameOver.this.ecran.getP().getPions()[idJ1].position;
			
			if(command=="Valider")
			{
				ChoixGameOver.this.ecran.getVue().getControler().requestFinPartie(idJ1);
				ChoixGameOver.this.setVisible(false);
				ChoixGameOver.this.actif=false;
				//ChoixAchat.this.setVisible(false);
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
		this.textBox.setText(this.messagePrincipal+" \n"+msg);
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
	
}
