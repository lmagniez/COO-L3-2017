
package com.vue.plateau.jeu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
 * Ecran d'enchere
 * @author loick
 *
 */
public class ChoixEnchere extends JPanel{

	protected EcranJeu ecran;
	protected ButtonMenu confirmer;
	protected ButtonMenu annuler;
	protected JTextArea textBox;
	protected JPanel panneauCentral, panneauDroite, panneauGauche ;
	protected String messagePrincipal;
	protected boolean actif;
	
	protected JLabel timerLabel;
	protected JLabel enchereActuelle;
	protected JLabel joueurActuel;
	
	protected int position;
	protected int idGagnant;
	protected int prixAPayer;
	protected ArrayList<JButton> listButtons;
	
	protected JPanel carte;
	
	protected boolean isStarted;
	private Thread timer;
	private boolean tourne=true;
	private boolean stopEnchere=true;
	
	/**
	 * Constructeur
	 * @param e Ecran de jeu
	 */
	public ChoixEnchere(EcranJeu e){
		
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
		
		
		JLabel l=new JLabel("ENCHERE");
		l.setFont(new Font("Arial", Font.BOLD, 16));
		l.setBackground(Color.BLACK);
		l.setForeground(Color.BLACK);
		l.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(5,7)));
		this.add(l);
		this.add(Box.createRigidArea(new Dimension(5,7)));
		
		
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		
		panneauCentral = new JPanel();
		panneauCentral.setLayout(new BoxLayout(panneauCentral,BoxLayout.PAGE_AXIS));
		panneauDroite = new JPanel();
		panneauDroite.setLayout(new BoxLayout(panneauDroite,BoxLayout.PAGE_AXIS));
		panneauGauche = new JPanel();
		panneauGauche.setLayout(new BoxLayout(panneauGauche,BoxLayout.PAGE_AXIS));
		panneauGauche.setAlignmentY(Component.TOP_ALIGNMENT);
		panneauDroite.setAlignmentY(Component.TOP_ALIGNMENT);
		panneauCentral.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		
		textBox=initTextArea("Message");
		textBox.setEditable(false);
		this.textBox.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X*3/7,ConstantesVue.DIMENSION_SCORE_Y*1/10));
		this.textBox.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X*3/7,ConstantesVue.DIMENSION_SCORE_Y*1/10));
		this.textBox.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X*3/7,ConstantesVue.DIMENSION_SCORE_Y*1/10));
		this.textBox.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X*3/7,ConstantesVue.DIMENSION_SCORE_Y*1/10));
		
		
		p.add(panneauGauche);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(panneauCentral);
		p.add(Box.createRigidArea(new Dimension(5,5)));
		p.add(panneauDroite);
		//p.add(textBox);
		
		
		confirmer=new ButtonMenu("Stopper l'enchère",Colors.textColor2,Colors.case8);
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
	
	

	/**
	 * Démarre le timer et met à jour le timer dans le modèle
	 */
	public void createTimer() {
			timer = new Thread(new Runnable() {
				int seconde=ConstantesParam.ENCHERE_TIME;
				
				public void run() {
					while (tourne&&seconde>0) {
						try {
							Thread.sleep(1000);
							seconde--;
							ChoixEnchere.this.timerLabel.setText("Temps Restant: "+seconde+"s");
							
						} catch (InterruptedException e) {
						}

					}
					if(stopEnchere){
						ChoixEnchere.this.isStarted=false;
						ChoixEnchere.this.ecran.getVue().getControler().requestAchatEnchere(idGagnant, position, prixAPayer);
						ChoixEnchere.this.setVisible(false);
						System.out.println("ENCORE???");
						ChoixEnchere.this.ecran.getChoixM().genererMessageEnchere(idGagnant, position, prixAPayer);
					}
				}
			});
	}
	
	public void stopper(){
		this.stopEnchere=false;
	}
	
	public void reprendre(){
		this.stopEnchere=true;
	}
	
	
	
	/**
	 * Génération de l'enchere
	 * @param position position de la case
	 */
	public void genererEnchere(int position){
		
		this.position=position;
		this.confirmer.setEnabled(false);
		panneauGauche.removeAll();
		panneauDroite.removeAll();
		panneauCentral.removeAll();
		
		listButtons=new ArrayList<JButton>();
		panneauGauche.add(Box.createRigidArea(new Dimension(5,70)));
		panneauDroite.add(Box.createRigidArea(new Dimension(5,70)));
		
		
		this.isStarted=false;
		this.actif=true;
		this.idGagnant=-1;
		
		//Generation carte
		Case c = this.ecran.getP().getCases()[position];
		Case[] cases= this.ecran.getP().getCases();
		if(c.type==TypeCase.TERRAIN){
			this.carte=new CarteAchatTerrain(c.idCase, c.nom, c.couleurTerrain, c.prixAchat, c.loyers, c.prixMaison);
			prixAPayer=c.prixAchat/2;
		}
		else if(c.type==TypeCase.GARE){
			this.carte=new CarteAchatGare(c.idCase, c.nom, c.prixAchat, c.loyers);
			
			int cpt=0;
			prixAPayer=c.prixAchat/2;
		}
		else if(c.type==TypeCase.SERVICE){
			this.carte=new CarteAchatService(c.idCase, c.nom, c.prixAchat, c.loyers);
			int cpt=0;
					
			prixAPayer=c.prixAchat/2;
			
		}
		
		//Génération boutons
		boolean noOne=true;
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++)
		{
			
			System.out.println(this.ecran.getS().getJoueurs()[i].getNomJoueur().getText());
			JButton b=new JButton("<html>"+this.ecran.getS().getJoueurs()[i].getNomJoueur().getText()+ "<br>+100M</html>");
			b.addActionListener(new ButtonListener());
			listButtons.add(b);
			
			
			if(this.ecran.getS().getJoueurs()[i].isGameOver()){
				listButtons.get(i).setEnabled(false);
			}
			else if(this.ecran.getS().getJoueurs()[i].getArgent()<prixAPayer+100){
				listButtons.get(i).setEnabled(false);
			}
			else{
				noOne=false;
			}
			
			if(i%2==0){
				panneauGauche.add(b);
			}
			else{
				panneauDroite.add(b);
			}
			if(i==1){
				panneauGauche.add(Box.createRigidArea(new Dimension(5,70)));
				panneauDroite.add(Box.createRigidArea(new Dimension(5,70)));
			}
			
			if(noOne){
				this.setVisible(false);
				this.ecran.getVue().getControler().requestTour();
				this.actif=false;
				
				return;
			}
			
		}
		
		
		enchereActuelle=new JLabel("Actuellement: "+prixAPayer);
		joueurActuel=new JLabel("Propriétaire: Personne");
		timerLabel=new JLabel("Temps Restant: "+ConstantesParam.ENCHERE_TIME+"s");
		
		enchereActuelle.setAlignmentX(Component.CENTER_ALIGNMENT);
		joueurActuel.setAlignmentX(Component.CENTER_ALIGNMENT);
		timerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		panneauCentral.add(Box.createRigidArea(new Dimension(5,7)));
		panneauCentral.add(enchereActuelle);
		panneauCentral.add(joueurActuel);
		panneauCentral.add(Box.createRigidArea(new Dimension(5,7)));
		panneauCentral.add(timerLabel);
		panneauCentral.add(Box.createRigidArea(new Dimension(5,7)));
		
		this.messagePrincipal="Enchere débutant à \n"+prixAPayer+" !";
		this.textBox.setText(messagePrincipal);
		
		carte.setAlignmentX(Component.CENTER_ALIGNMENT);
		textBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		panneauCentral.add(carte);
		panneauCentral.add(textBox);
		
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	/**
	 * Changer le gagnant de l'enchere (a chaque fois qu'on clique sur un joueur)
	 * @param idJoueur id joueur
	 * @param ajoutValeur valeur d'ajout au prix initial
	 */
	public void changerProprietaire(int idJoueur, int ajoutValeur){
		this.idGagnant=idJoueur;
		this.joueurActuel.setText("Propriétaire: "+this.ecran.getS().getJoueurs()[idJoueur].getNomJoueur().getText());
		this.prixAPayer+=ajoutValeur;
		this.enchereActuelle.setText("Actuellement: "+prixAPayer+"M");
		
		
		
		//met en rouge le gagnant, et supprime ceux qui ne peuvent pas encherir
		for(int j=0; j<ConstantesParam.NB_JOUEURS; j++){
			listButtons.get(j).setForeground(Color.black);
			if(this.ecran.getS().getJoueurs()[j].getArgent()<prixAPayer+100)
				listButtons.get(j).setEnabled(false);
		}
		listButtons.get(idJoueur).setForeground(Color.red);
		
		//
		
		
	}
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			JButton origin=(JButton) e.getSource();
			
			InfoJoueur info=ChoixEnchere.this.ecran.getS().getJoueurs()[ChoixEnchere.this.ecran.getTour()];
			int idJoueur=ChoixEnchere.this.ecran.getTour();
			int posJoueur=ChoixEnchere.this.ecran.getP().getPions()[idJoueur].position;
			
			if(command=="Stopper l'enchère")
			{
				//ChoixEnchere.this.ecran.getVue().getControler().requestPaiement(idJ1,idJ2,posJoueur);
				ChoixEnchere.this.ecran.getVue().getControler().requestAchatEnchere(idGagnant, position, prixAPayer);
				ChoixEnchere.this.setVisible(false);
				ChoixEnchere.this.ecran.getChoixM().genererMessageEnchere(idGagnant, position, prixAPayer);
				ChoixEnchere.this.timer.stop();
				
				//ChoixEnchere.this.stopper();
				
			}
			
			for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
				if(ChoixEnchere.this.listButtons.get(i)==origin){
					confirmer.setEnabled(true);
					
					if(!isStarted){
						reprendre();
						tourne=true;
						isStarted=true;
						confirmer.setEnabled(true);
						createTimer();
						timer.start();
						changerProprietaire(i,0);
					}
					else{
						changerProprietaire(i,100);
					}
					
					
				}
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
