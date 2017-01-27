package com.vue.grille;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import com.vue.Colors;

/**
 * Bouton pour un menu
 * @author loick
 *
 */

public class ButtonMenu extends JButton{
	
	public ButtonMenu(String s, Color txtColor, Color bgColor)
	{
		this.setText(s);
		this.setAlignmentX(this.CENTER_ALIGNMENT);
		this.setForeground(Colors.textColor2);
		this.setBackground(Colors.case8);
		this.setBorder(new LineBorder (Color.BLACK, 1));
		this.setMaximumSize(new Dimension(150,50));
	}

}
