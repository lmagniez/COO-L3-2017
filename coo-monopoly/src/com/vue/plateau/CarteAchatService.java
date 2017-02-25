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

public class CarteAchatService extends JPanel{

	
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
	
	
	public CarteAchatService(int idCase, String nom, int prixAchat, int[] loyers){
		
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
		
		
		labelLoyers[0]=new JLabel("LOYER: ");
		border = labelLoyers[0].getBorder();
		margin = new EmptyBorder(2,2,2,2);
		labelLoyers[0].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[0]);
		
		labelLoyers[1]=new JLabel(loyers[0]+"M x somme dés");
		border = labelLoyers[1].getBorder();
		margin = new EmptyBorder(2,2,2,2);
		labelLoyers[1].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[1]);
		
		labelLoyers[2]=new JLabel("Avec 2 services: ");
		border = labelLoyers[2].getBorder();
		margin = new EmptyBorder(2,2,2,2);
		labelLoyers[2].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[2]);
		
		labelLoyers[3]=new JLabel(loyers[1]+"M x somme dés");
		border = labelLoyers[3].getBorder();
		margin = new EmptyBorder(2,2,2,2);
		labelLoyers[3].setBorder(new CompoundBorder(border, margin));
		this.add(labelLoyers[3]);
		
		
		

		this.add(Box.createRigidArea(new Dimension(5,10)));
		
		this.labelHypotheque=new JLabel("Valeur Hypothécaire:     "+(prixAchat/2)+"M");
		labelHypotheque.setFont(labelHypotheque.getFont().deriveFont(10.0f));
		this.add(labelHypotheque);
		
	}
	
	
}
