package com.vue.menu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.model.ConstantesParam;
import com.vue.ButtonMenu;
import com.vue.Colors;


/**
 * Ecran a appeler dans l'écran jeu
 * @author loick
 *
 */
public class EcranOpt extends Ecran implements ActionListener{
	
	
	protected JButton start;
	protected JButton option;
	protected JButton quit;
	
	protected JLabel startLabel;
	
	protected JComboBox typeJoueurs[], icons[];
	protected JTextField nomJoueurs[];
	
	
	protected JPanel panelJoueurs[];
	protected Image fond;
	
	public static final int MIN_PARAM=2;
	public static final int MAX_PARAM=9;
	
	
	/**
	 * Constructeur 
	 * @param vue Vue du menu
	 */
	public EcranOpt(VueMenu vue)
	{
		
		
		fond=new ImageIcon("./Sprites/aide.png").getImage();
		this.vue=vue;
		NB_BUTTONS_X=1;
		NB_BUTTONS_Y=15;
		int cptButton=0;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		/*
		 * Init les composants
		 */
		

		buttons=new JComponent[NB_BUTTONS_X][NB_BUTTONS_Y];
		typeJoueurs = new JComboBox[4];
		nomJoueurs = new JTextField[4];
		panelJoueurs = new JPanel[4]; 
		icons=new JComboBox[4];
		
		
		//init bouton start
		start=new ButtonMenu("Démarrer",Colors.textColor2,Colors.case8);
		start.addActionListener(new ButtonListener());
		start.addActionListener(this);
		
		
		option=new ButtonMenu("Règles additionnelles",Colors.textColor2,Colors.case8);	
		option.addActionListener(new ButtonListener());
		option.addActionListener(this);
		
		//init bouton quitter
		quit=new ButtonMenu("Retour",Colors.textColor2,Colors.case16);	
		quit.addActionListener(new ButtonListener());
		quit.addActionListener(this);
		
		//init label titre
		startLabel=new JLabel("Paramètres de la partie ");
		startLabel.setSize(new Dimension(150,50));
		startLabel.setFont(new Font("Arial",Font.BOLD,20));
		startLabel.setAlignmentX(this.CENTER_ALIGNMENT);
		
		
		
		//nombreCase.addActionListener(this);
		
		
		//Init Panel Joueurs
		
		
		//init combo box
		String str[]={"JOUEUR","IA"};
		String str2[]={"JOUEUR","NONE","IA"};
		
		Object items[]={
				transform(new ImageIcon("./Sprites/pion/1.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/2.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/3.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/4.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/5.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/6.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/7.png"),50,50),
				transform(new ImageIcon("./Sprites/pion/8.png"),50,50)
		};
		
	    
	    for(int i=0; i<4; i++)
	    {
	    	
	    	//Init panel
	    	panelJoueurs[i]=new JPanel();
	    	panelJoueurs[i].setLayout(new BoxLayout(panelJoueurs[i],BoxLayout.PAGE_AXIS));
	    	panelJoueurs[i].setBorder(BorderFactory.createLineBorder(Color.black));
	    	Border border = panelJoueurs[i].getBorder();
	    	Border margin = new EmptyBorder(10,10,10,10);
	    	panelJoueurs[i].setBorder(new CompoundBorder(border, margin));
	    	
	    	//Type de joueurs (IA/HUMAIN/NONE)
	    	if(i<2) typeJoueurs[i] = new JComboBox(str);
	    	else typeJoueurs[i] = new JComboBox(str2);
	    	typeJoueurs[i].setMaximumSize(new Dimension(150,50));
	    	typeJoueurs[i].setSelectedIndex(0);
	    	typeJoueurs[i].addActionListener(this);
	    	
	    	//Nom des joueurs
	    	nomJoueurs[i]=new JTextField(15);
	    	nomJoueurs[i].setMaximumSize(new Dimension(200,25));
	    	nomJoueurs[i].setText("JOUEUR"+(i+1));
	    	
	    	//Init icones
	    	icons[i]=new JComboBox(items);
	    	icons[i].setMaximumSize(new Dimension(75,60));
	    	icons[i].setSelectedIndex(i);
	    	
	    	//Ajout au tableau de boutons (déplacement clavier)
	    	buttons[0][cptButton++]=typeJoueurs[i];
	    	buttons[0][cptButton++]=nomJoueurs[i];
	    	buttons[0][cptButton++]=icons[i];
	    	
	    	
	    	
	    	//Ajout des éléments au panel
	    	panelJoueurs[i].add(new JLabel("J"+(i+1)+": "));
	    	panelJoueurs[i].add(typeJoueurs[i]);
	    	panelJoueurs[i].add(Box.createRigidArea(new Dimension(30,10)));
	    	
	    	panelJoueurs[i].add(new JLabel("Nom:"));
	    	panelJoueurs[i].add(nomJoueurs[i]);
	    	panelJoueurs[i].add(Box.createRigidArea(new Dimension(30,10)));
	    	
	    	panelJoueurs[i].add(icons[i]);
	    
	    }
	    
	    
	    
	    buttons[0][cptButton++]=start;
		buttons[0][cptButton++]=option;
		buttons[0][cptButton++]=quit;
		
	    

	    
	    
	    /*
	     * Placement des composants
	     */
	    
		this.add(Box.createRigidArea(new Dimension(5,50)));
	    this.add(startLabel);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		//Nombre de cases
		JPanel p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.LINE_AXIS));
		p.add(new JLabel("Nombre de cases :"));
		p.add(Box.createRigidArea(new Dimension(30,10)));
		this.add(p);
		this.add(Box.createRigidArea(new Dimension(5,50)));
		
		//Parametres des joueurs
		
		
		JPanel p2= new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		
		for(int i=0; i<4; i++)
		{
			p2.add(panelJoueurs[i]);
			p2.add(Box.createRigidArea(new Dimension(25,5)));
		}
		
		
		this.add(p2);
		this.add(Box.createRigidArea(new Dimension(30,50)));
		
		JPanel p3= new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
		
		
		
	    //Boutons start et quitter
		
		p3.add(start);
		p3.add(Box.createRigidArea(new Dimension(30,30)));
		
		p3.add(option);
		p3.add(Box.createRigidArea(new Dimension(30,30)));
		
		p3.add(quit);
		p3.add(Box.createRigidArea(new Dimension(30,50)));
		
		this.add(p3);
		
		
		//this.setBounds(300, 200, 800, 400);
		
		
		
		this.addListener();
		
		
	}
	
	public ImageIcon transform (ImageIcon img, int hx, int hy)
	{
		Image image=img.getImage();
		Image newImg= image.getScaledInstance(hx, hy, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newImg);
	}
	

	/**
	 * Gérer les paramètres et les enregistrer dans la classe constantesParam
	 */
	public void gererParam(){
		int cptJoueurs=0;
		for(int i=0; i<4; i++){
			if(!typeJoueurs[i].getSelectedItem().equals("NONE")){
				cptJoueurs++;
			}
		}
		
		ConstantesParam.NB_JOUEURS=cptJoueurs;
		
		ConstantesParam.NAMES=new String[ConstantesParam.NB_JOUEURS];
		ConstantesParam.ID_ICONES=new int[ConstantesParam.NB_JOUEURS];
		
		int cpt=0;
		for(int i=0; i<4; i++){
			if(nomJoueurs[i].isEnabled()){
				ConstantesParam.NAMES[cpt]=nomJoueurs[i].getText();
				ConstantesParam.ID_ICONES[cpt++]=icons[i].getSelectedIndex();
			}
		}
		
		boolean[] isIA=new boolean[cptJoueurs];
		cpt=0;
		for(int i=0; i<4; i++){
			if(typeJoueurs[i].getSelectedItem().equals("IA")){
				isIA[cpt++]=true;
			}
			if(typeJoueurs[i].getSelectedItem().equals("JOUEUR")){
				isIA[cpt++]=false;
			}
			
		}
		ConstantesParam.IS_IA=isIA;
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
		for(int i=2; i<4; i++){
			if(arg0.getSource()==this.typeJoueurs[i]){
				if(nomJoueurs[i].isEnabled()){
					if(typeJoueurs[i].getSelectedItem().equals("NONE")){
						this.nomJoueurs[i].setEnabled(false);		
						this.icons[i].setEnabled(false);
					}
				}
				else{
					this.nomJoueurs[i].setEnabled(true);		
					this.icons[i].setEnabled(true);
				}
				
			}
		}
		
		if(arg0.getSource() instanceof JButton){
			String command = ((JButton) arg0.getSource()).getActionCommand();
		
			
			if(command=="Démarrer")
			{	
				gererParam();
				vue.initFenetreEcranJeu();
				//afficherPanneau(vue.lePanneau);
			}
			
			if(command=="Règles additionnelles")
			{
				gererParam();
				this.focusNouvelEcran(vue.lePanneau6);
			}
			
			if(command=="Retour")
			{
				
				this.focusNouvelEcran(vue.lePanneau3);
				//vue.lePanneau3.requestFocus();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
    }
	
	
}
