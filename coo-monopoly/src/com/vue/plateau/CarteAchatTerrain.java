package com.vue.plateau;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.model.ConstantesVue;
import com.model.plateau.cases.CouleurTerrain;

/**
 * Classe représentant une case terrain graphiquement
 * @author loick
 */
public class CarteAchatTerrain extends JPanel{

	protected int idCase;
	protected CouleurTerrain couleurTerrain;
	protected int prixAchat;
	protected int[] loyers;
	protected int prixMaison;//5000 marron, 10000 violet, orange 15000 rouge et jaune, 20000 vert
	protected String nom;
	protected int valeurAchat;
	
	protected JLabel labelNom;
	protected JLabel labelLoyers[];
	protected JLabel labelHypotheque;
	protected JLabel labelPrixMaison;
	
	/**
	 * Constructeur
	 * @param idCase id de la case
	 * @param nom nom de la case
	 * @param couleur couleur du terrain
	 * @param prixAchat prix d'achat
	 * @param loyers loyers
	 * @param prixMaison prix d'une maison
	 */
	public CarteAchatTerrain(int idCase, String nom, CouleurTerrain couleur, int prixAchat,
			int[] loyers, int prixMaison){
		
		this.setMaximumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setMinimumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setPreferredSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		this.nom=nom;
		this.idCase=idCase;
		this.couleurTerrain=couleur;
		this.prixAchat=prixAchat;
		this.prixMaison=prixMaison;
		
		this.labelNom=new JLabel(nom+" (achat:"+prixAchat+")");
		System.out.println("couleur!!");
		System.out.println(CouleurTerrain.getColorFromEnum(couleurTerrain));
		
		Border border = labelNom.getBorder();
		Border margin = new EmptyBorder(10,25,10,10);
		labelNom.setBorder(new CompoundBorder(border, margin));
		labelNom.setSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,30));
		labelNom.setMaximumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,30));
		
		labelNom.setOpaque(true);
		labelNom.setBackground(CouleurTerrain.getColorFromEnum(couleurTerrain));
		labelNom.setForeground(CouleurTerrain.getForegroundColorFromEnum(couleurTerrain));
		
		
		this.add(labelNom);
		
		this.labelLoyers=new JLabel[6];
		
		
		labelLoyers[0]=new JLabel("LOYER: "+loyers[0]+"M");
		border = labelLoyers[0].getBorder();
		margin = new EmptyBorder(2,60,2,2);
		labelLoyers[0].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[0]);
		
		for(int i=1; i<5; i++){
			
			labelLoyers[i]=new JLabel("Avec "+(i+1)+" Maison:     "+loyers[i]+"M");
			border = labelLoyers[i].getBorder();
			margin = new EmptyBorder(2,10,2,2);
			labelLoyers[i].setBorder(new CompoundBorder(border, margin));
			this.add(labelLoyers[i]);
		}
		labelLoyers[5]=new JLabel("Avec HÔTEL:     "+loyers[5]+"M");
		border = labelLoyers[5].getBorder();
		margin = new EmptyBorder(2,10,2,2);
		labelLoyers[5].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[5]);
		

		this.add(Box.createRigidArea(new Dimension(5,10)));
		
		this.labelHypotheque=new JLabel("Valeur Hypothécaire:     "+(prixAchat/2)+"M");
		labelHypotheque.setFont(labelHypotheque.getFont().deriveFont(10.0f));
		this.add(labelHypotheque);
		
		this.labelPrixMaison=new JLabel("Prix des Maisons: "+this.prixMaison+"M chacune");
		labelPrixMaison.setFont(labelPrixMaison.getFont().deriveFont(10.0f));
		this.add(labelPrixMaison);
		
		
	}
	
}
