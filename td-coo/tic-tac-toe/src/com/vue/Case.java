package com.vue;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Case extends JButton
{
	protected int x;
	protected int y;
	
	
	public Case(int x, int y)
	{
		
		this.x=x;
		this.y=y;
		this.setText("");
		
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		
		
		this.setMinimumSize(new Dimension(45,45));
		this.setPreferredSize(new Dimension(90,90));
		
		this.setOpaque(true);
		this.setVisible(true);
		
		
	}
}