package com.vue.titre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

import com.vue.Colors;
import com.vue.ButtonMenu;

/**
 * Classe correspondant à l'écran de paramétrage de la partie.
 * Intervient dans la vue du menu.
 * @author loick
 *
 */

public class EcranParam extends JPanel{

	protected Vue1 f;
	
	protected ButtonMenu quit;
	protected ButtonMenu start;
	
	protected JLabel startLabel;
	
	protected JSlider nbRow,nbCol;
	protected JComboBox typeJoueurs[],nbJetonsG,couleurJeton;
	protected ButtonGroup bG;
    
	
	
	
	
	
	public static final int MIN_PARAM=3;
	public static final int MAX_PARAM=15;
	
	public ImageIcon checked= transform(new ImageIcon(getClass().getResource("/sprites/checked.png")),40,40);
	public ImageIcon unchecked= transform(new ImageIcon(getClass().getResource("/sprites/unchecked.png")),40,40);
	

	
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranParam(Vue1 f)
	{
		this.f=f;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		/*
		 * Init les composants
		 */
		
		//init bouton start
		
		this.start=new ButtonMenu("Démarrer",Colors.textColor2,Colors.case8);
		start.setEnabled(true);
		start.addActionListener(new ButtonListener());
		
		
		//init bouton quitter
		quit=new ButtonMenu("Retour",Colors.textColor2,Colors.case16);	
		quit.addActionListener(new ButtonListener());
		
		//init label titre
		startLabel=new JLabel("Paramètres de la partie ");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,20));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		bG=new ButtonGroup();		
		
	    
	    /*
         * Placement des composants
         */
        
        this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		
        for(int i=0; i<f.nbGrille; i++){

			JLabel checkedLabel=new JLabel();
			JToggleButton button = new JToggleButton(""+i);
			if(i==0)button.setSelected(true);
			bG.add(button);
			if(f.reussites[i])
				checkedLabel.setIcon(checked);
			else
				checkedLabel.setIcon(unchecked);
			
			
			JPanel p=new JPanel();
			p.setPreferredSize(new Dimension(400,50));
			p.setMaximumSize(new Dimension(400,50));
			
			
			p.setBorder(BorderFactory.createLineBorder(Color.black));
			
			p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
			p.add(Box.createRigidArea(new Dimension(10,5)));
			p.add(button);
			p.add(Box.createRigidArea(new Dimension(20,5)));
			p.add(new JLabel("Nom: "+f.noms[i]));
			p.add(Box.createRigidArea(new Dimension(20,5)));
			p.add(new JLabel("Dimension: "+f.nbLignes[i]+"x"+f.nbColonnes[i]));
			
			p.add(Box.createRigidArea(new Dimension(20,5)));
			p.add(checkedLabel);
			//this.add(p);
			p2.add(p);
			
		}
		
		
		
		JScrollPane scroll= new JScrollPane(p2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(425,400));
		scroll.setSize(new Dimension(425,400));
		scroll.setMaximumSize(new Dimension(425,400));
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		
		this.add(scroll);
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
        //Boutons start et quitter
        
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(quit);
		this.add(Box.createRigidArea(new Dimension(5,50)));
	
		
		
	}
	
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	
	
	
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	
	/**
	 * Listener des boutons de l'écran de paramètrage.
	 * @author loick
	 *
	 */
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Démarrer")
			{
				
				//f.initFenetreEcranJeu(nbLigne, nbColonne, nbJR, isIA, swapColor);				
				try {
					int indice=Integer.parseInt(EcranParam.this.getSelectedButtonText(bG));
					int id= EcranParam.this.f.ids[indice];
					
					f.initFenetreEcranJeu(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			if(command=="Retour")
				f.afficherPanneau(f.getPanneauTitre());
		} 
	}
	
}

