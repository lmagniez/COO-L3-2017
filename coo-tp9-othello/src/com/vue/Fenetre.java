package com.vue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.controler.AbstractControler;


/**
 * 
 * @author loick
 * Classe abstraite correspondant à une JFrame, dispose de méthodes génériques
 *
 */


public abstract class Fenetre extends JFrame {
	
	// L'instance de notre objet controleur
	
	
	
	/**
	 * Méthode générique affichant un panneau sur la fenêtre
	 * @param p
	 */
	public void afficherPanneau(JPanel p)
	{
		getContentPane().removeAll();
		getContentPane().repaint();
		getContentPane().add(p);
		p.requestFocus();
		p.setFocusable(true);
		getContentPane().validate();
		this.repaint();
	}

	
	
	


}