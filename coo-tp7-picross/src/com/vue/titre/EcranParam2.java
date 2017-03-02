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
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import com.vue.Colors;
import com.vue.titre.EcranParam.ButtonListener;
import com.vue.ButtonMenu;

/**
 * Classe correspondant à l'écran de paramétrage de la partie.
 * Intervient dans la vue du menu.
 * @author loick
 *
 */

public class EcranParam2 extends JPanel{

	protected Vue1 f;
	
	protected ButtonMenu quit;
	protected ButtonMenu start;
	
	protected JLabel startLabel;
	
	
	protected JSlider nbRow;
	protected JTextArea inputNom;
	
	
	public static final int MIN_PARAM=3;
	public static final int MAX_PARAM=15;
	
	public ImageIcon checked= transform(new ImageIcon("./sprites/checked.png"),40,40);
	public ImageIcon unchecked= transform(new ImageIcon("./sprites/unchecked.png"),40,40);
	
	
	
	/**
	 * Initialisation du JPanel (1 fois)
	 * @param f Fenetre utilisé pour les ActionListener
	 */
	public EcranParam2(Vue1 f)
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
		
	    
		//init slider nombre de lignes
		nbRow = new JSlider(JSlider.HORIZONTAL, MIN_PARAM, MAX_PARAM, 7);
		nbRow.setMinorTickSpacing(2);
	    nbRow.setMajorTickSpacing(2);
		nbRow.setPaintTicks(true);
		nbRow.setPaintLabels(true);
		nbRow.setLabelTable(nbRow.createStandardLabels(1));
		nbRow.setMaximumSize(new Dimension(300,50));
		
		this.inputNom=this.initTextArea("grille");
		
	    /*
         * Placement des composants
         */
        
        this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
		
		
		
				
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		
        System.out.println(f.nbGrille);
		
        this.add(nbRow);
		
		
       
		
		this.add(Box.createRigidArea(new Dimension(5,40)));
		
		
		
        //Boutons start et quitter
        
		this.add(start);
		this.add(Box.createRigidArea(new Dimension(5,30)));
		this.add(quit);
		this.add(Box.createRigidArea(new Dimension(5,50)));
	
		
		
	}
	
	public JTextArea initTextArea(String s)
	{
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		return textArea;

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
					
					String nom=EcranParam2.this.inputNom.getText();
					int nbRow=EcranParam2.this.nbRow.getValue();
					int nbCol=nbRow;
					
					
					f.initFenetreCreation(nom, nbRow, nbCol);
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

