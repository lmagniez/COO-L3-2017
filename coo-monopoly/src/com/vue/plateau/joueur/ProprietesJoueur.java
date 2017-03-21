package com.vue.plateau.joueur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.ConstantesModel;
import com.model.ConstantesVue;
import com.model.plateau.cases.CouleurTerrain;
import com.vue.ButtonMenu;
import com.vue.Colors;
import com.vue.plateau.CarteAchatGare;
import com.vue.plateau.CarteAchatService;
import com.vue.plateau.CarteAchatTerrain;
import com.vue.plateau.jeu.Case;
import com.vue.plateau.jeu.TypeCase;

/**
 * Ecran représentant les différentes propriétés d'un joueur.
 * Possibilité d'achat, vente, visualisation des cartes
 * @author loick
 *
 */
public class ProprietesJoueur extends JPanel{

	protected InfoJoueur infos;
	protected int idJoueur;
	
	protected JLabel nomJoueur;
	protected JLabel argentJoueur;
	
	private JButton echange=new JButton("Echange");
	protected JButton retour;
	private JButton maison=new JButton("Acheter Maison");
	private JButton maisonVente=new JButton("Vendre Maison");
	
	protected ArrayList<JButton> listePropri;
	
	protected JPanel[] couleurs;
	private JPanel carte;
	protected Case[] cases;
	protected int idSelected;
	
	/**
	 * Constructeur
	 * @param idJoueur id du joueur
	 * @param infos info du joueur
	 * @param cases cases du plateau
	 */
	public ProprietesJoueur(int idJoueur, InfoJoueur infos, Case[] cases){
		
		this.idSelected=-1;
		this.infos=infos;
		this.idJoueur=idJoueur;
		this.cases=cases;
		 listePropri=new ArrayList<JButton>();
		
		this.setMaximumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setMinimumSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		this.setPreferredSize(new Dimension(ConstantesVue.DIMENSION_SCORE_X,ConstantesVue.DIMENSION_SCORE_X*5/3));
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(new Color(200,200,200,90));
		
		getMaison().addActionListener(new ButtonListener());
		getMaisonVente().addActionListener(new ButtonListener());
		getEchange().addActionListener(new ButtonListener());
		
		this.couleurs=new JPanel[ConstantesModel.NB_COULEUR+2];
		for(int i=0; i<ConstantesModel.NB_COULEUR+2;i++){
			couleurs[i]=new JPanel();
			couleurs[i].setLayout(new BoxLayout(couleurs[i], BoxLayout.LINE_AXIS));
			
		}
		
		JLabel l[] = new JLabel[ConstantesModel.NB_COULEUR+2];
		for(int i=0; i<ConstantesModel.NB_COULEUR+2; i++){
			l[i]=new JLabel("  ");
			l[i].setSize(new Dimension(50,50));
			l[i].setVisible(true);
			l[i].setOpaque(true);

		}
		
		l[0].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.MARRON));
		couleurs[0].add(l[0]);
		
		l[1].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.BLEU_CLAIR));
		l[2].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.VIOLET));
		l[3].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.ORANGE));
		l[4].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.ROUGE));
		l[5].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.JAUNE));
		l[6].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.VERT));
		l[7].setBackground(CouleurTerrain.getColorFromEnum(CouleurTerrain.BLEU_FONCE ));
		l[8].setBackground(Color.black);
		
		
		for(int i=0; i<ConstantesModel.NB_COULEUR+2; i++){
			couleurs[i].add(l[i]);
		}
		
		for(int i=0; i<cases.length; i++){
			JButton b=new JButton(cases[i].getNom());
			if(!infos.getAcquisition()[i])
				b.setEnabled(false);
			b.addActionListener(new ButtonListener());
			listePropri.add(b);
			
			if(cases[i].getType()==TypeCase.TERRAIN){
				if(cases[i].getCouleurTerrain()==CouleurTerrain.MARRON)
					couleurs[0].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.BLEU_CLAIR)
					couleurs[1].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.VIOLET)
					couleurs[2].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.ORANGE)
					couleurs[3].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.ROUGE)
					couleurs[4].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.JAUNE)
					couleurs[5].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.VERT)
					couleurs[6].add(b);
				else if(cases[i].getCouleurTerrain()==CouleurTerrain.BLEU_FONCE)
					couleurs[7].add(b);
			}
			if(cases[i].getType()==TypeCase.GARE){
				couleurs[8].add(b);
			}
			if(cases[i].getType()==TypeCase.SERVICE){
				couleurs[9].add(b);
			}
			
				
		}
		
		
		
		retour=new ButtonMenu("Retour",Colors.textColor2,Colors.case8);
		retour.addActionListener(new ButtonListener());
		
		
		this.nomJoueur=new JLabel("Joueur "+this.idJoueur);
		this.argentJoueur=new JLabel("Argent: "+this.infos.getArgent());
		
		JPanel joueurPanel= new JPanel();
		joueurPanel.setLayout(new BoxLayout(joueurPanel,BoxLayout.LINE_AXIS));
		joueurPanel.add(nomJoueur);
		joueurPanel.add(Box.createRigidArea(new Dimension(30,25)));
		joueurPanel.add(argentJoueur);
		
		
		this.add(joueurPanel);		
		
		for(int i=0; i<ConstantesModel.NB_COULEUR+2; i++){
			this.add(couleurs[i]);
		}
		
		this.add(retour);
		
		
		
		
		
		
	}
	
	public void refresh(){
		for(int i=0; i<listePropri.size(); i++){
			JButton b=listePropri.get(i);
			if(!infos.getAcquisition()[i])
				b.setEnabled(false);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void setArgent(int argent){
		this.argentJoueur.setText("Argent: "+argent);
	}
	
	public JPanel getCarte() {
		return carte;
	}

	public void setCarte(JPanel carte) {
		this.carte = carte;
	}

	public JButton getEchange() {
		return echange;
	}

	public void setEchange(JButton echange) {
		this.echange = echange;
	}

	public JButton getMaison() {
		return maison;
	}

	public void setMaison(JButton maison) {
		this.maison = maison;
	}

	public JButton getMaisonVente() {
		return maisonVente;
	}

	public void setMaisonVente(JButton maisonVente) {
		this.maisonVente = maisonVente;
	}

	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			
			if(command=="Acheter Maison"){
				ProprietesJoueur.this.infos.score.ecran.getVue().getControler().requestAchatMaison(idJoueur, idSelected);
			}
			if(command=="Vendre Maison"){
				ProprietesJoueur.this.infos.score.ecran.getVue().getControler().requestVendreMaison(idJoueur, idSelected);
			}
			if(command=="Echange"){
				
				ProprietesJoueur.this.infos.score.ecran.getChoixE().genererChoixAchat(idJoueur, idSelected);
				
			}
			
			else if(command=="Retour"){
				ProprietesJoueur.this.infos.score.afficherInfosJoueur();
				ProprietesJoueur.this.infos.score.ecran.getChoixE().setVisible(false);
			}
			else{
				getMaison().setEnabled(true);
				getMaisonVente().setEnabled(true);
				for(int i=0; i<ConstantesModel.NB_CASES; i++){
					if(command.equals(cases[i].getNom())){
						ProprietesJoueur.this.idSelected=i;
						if(ProprietesJoueur.this.getCarte()!=null)
							ProprietesJoueur.this.remove(ProprietesJoueur.this.getCarte());
						//case terrain
						if(cases[i].getType()==TypeCase.TERRAIN){
							for(int j=0; j<ConstantesModel.NB_CASES; j++){
								if(cases[j].getType()==TypeCase.TERRAIN)
									if(cases[j].getCouleurTerrain()==cases[i].getCouleurTerrain()&&!infos.getAcquisition()[j])
										getMaison().setEnabled(false);
							}
							ProprietesJoueur.this.setCarte(new CarteAchatTerrain(i,cases[i].getNom(),
									cases[i].getCouleurTerrain(),cases[i].getPrixAchat(),cases[i].getLoyers(),
									cases[i].getPrixMaison()));
							
							if(cases[i].getNbMaisons()==0)
								getMaisonVente().setEnabled(false);
							
						}
						//case gare
						else if(cases[i].getType()==TypeCase.GARE){
							for(int j=0; j<ConstantesModel.NB_CASES; j++){
								if(cases[j].getType()==TypeCase.GARE&&!infos.getAcquisition()[j])
									getMaison().setEnabled(false);
							}
							ProprietesJoueur.this.setCarte(new CarteAchatGare(i,cases[i].getNom(),
									cases[i].getPrixAchat(),cases[i].getLoyers()));
						}
						else if(cases[i].getType()==TypeCase.SERVICE){
							for(int j=0; j<ConstantesModel.NB_CASES; j++){
								if(cases[j].getType()==TypeCase.SERVICE&&!infos.getAcquisition()[j])
									getMaison().setEnabled(false);
							}
							ProprietesJoueur.this.setCarte(new CarteAchatService(i,cases[i].getNom(),
									cases[i].getPrixAchat(),cases[i].getLoyers()));
						}
						
						
						ProprietesJoueur.this.add(ProprietesJoueur.this.getCarte());
						if(!(cases[i].getType()==TypeCase.SERVICE||cases[i].getType()==TypeCase.GARE)){
							ProprietesJoueur.this.add(getMaison());
							ProprietesJoueur.this.add(getMaisonVente());
							
						}
						ProprietesJoueur.this.add(getEchange());
						
						
						ProprietesJoueur.this.revalidate();
						ProprietesJoueur.this.repaint();
						
						break;
					}
				}
				
				
			}
		
		} 
	}
}
