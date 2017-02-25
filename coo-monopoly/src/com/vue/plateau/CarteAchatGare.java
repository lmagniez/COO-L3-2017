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
import com.model.plateau.cases.GareModel;

public class CarteAchatGare extends JPanel{

	
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
	
	
	public CarteAchatGare(int idCase, String nom, int prixAchat,int[] loyers){
		
		this.setMaximumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setMinimumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		this.setPreferredSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,ConstantesVue.CASE_HEIGHT*2/3));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		this.nom=nom;
		this.idCase=idCase;
		this.prixAchat=prixAchat;
		this.prixMaison=prixMaison;
		
		this.labelNom=new JLabel(nom+" (achat:"+prixAchat+")");
		
		Border border = labelNom.getBorder();
		Border margin = new EmptyBorder(10,25,10,10);
		labelNom.setBorder(new CompoundBorder(border, margin));
		labelNom.setSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,30));
		labelNom.setMaximumSize(new Dimension(ConstantesVue.CASE_WIDTH*4/3,30));
		
		labelNom.setOpaque(true);
		
		this.add(labelNom);
		
		this.labelLoyers=new JLabel[4];
		
		
		labelLoyers[0]=new JLabel("LOYER: "+loyers[0]+"M");
		border = labelLoyers[0].getBorder();
		margin = new EmptyBorder(2,60,2,2);
		labelLoyers[0].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[0]);
		
		for(int i=1; i<4; i++){
			
			labelLoyers[i]=new JLabel("Avec "+(i+1)+" Gare:     "+loyers[i]+"M");
			border = labelLoyers[i].getBorder();
			margin = new EmptyBorder(2,10,2,2);
			labelLoyers[i].setBorder(new CompoundBorder(border, margin));
			this.add(labelLoyers[i]);
		}

		this.add(Box.createRigidArea(new Dimension(5,10)));
		
		this.labelHypotheque=new JLabel("Valeur Hypothécaire:     "+(prixAchat/2)+"M");
		labelHypotheque.setFont(labelHypotheque.getFont().deriveFont(10.0f));
		this.add(labelHypotheque);
		
	}
	
	
}
