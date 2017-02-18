package com.reseau;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


/**
 * Frame du serveur 
 * @author loick
 *
 */
public class Serveur2 extends JFrame{

	protected JButton quit;
	protected ServerSocket socket;
	private PrintStream defaultOutStream = System.out;
	protected boolean hasClient1,hasClient2;
	
	protected int actualPort;
	
	protected Accepter_clients client1,client2;
	protected JTextArea field;
	SpinnerModel numberServer = new SpinnerNumberModel(1792, 1, 2000, 1);     
	JSpinner spinner = new JSpinner(numberServer);
	protected int countLine;
	
	
	/**
	 * Constructeur
	 * Initie un bouton pour démarrer et stopper le serveur
	 */
	public Serveur2(){
		
		this.setTitle("Serveur");
		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		field=initTextArea("Serveur stoppé");
		this.actualPort=-1;
		
		this.add(Box.createRigidArea(new Dimension(5,10)));
		
		
		JPanel p2=new JPanel();
		p2.add(Box.createRigidArea(new Dimension(50,5)));
		p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
		p2.add(new JLabel("Port du serveur: "));
		p2.add(spinner);
		p2.add(Box.createRigidArea(new Dimension(50,5)));
		this.add(p2);
		
		this.add(Box.createRigidArea(new Dimension(5,20)));
		
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
		JButton b1=new JButton("Démarrer serveur");
		b1.addActionListener(new ButtonListener());
		JButton b2=new JButton("Stopper serveur");
		b2.addActionListener(new ButtonListener());
		JButton b3=new JButton("Clear");
		b3.addActionListener(new ButtonListener());
		
		p1.add(b1);
		p1.add(b2);
		this.add(p1);
		this.add(Box.createRigidArea(new Dimension(5,20)));
		
		this.add(field);
		
		b3.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(b3);
		b3.setMaximumSize(new Dimension(400,20));
		
		this.setVisible(true);
		
		
	}
	
	public void addText(String s){
		if(this.countLine==19){
			countLine=0;
			this.field.setText("");
		}
		this.field.setText(field.getText()+"\n"+s);
		countLine++;
	}
	
	/**
	 * Initialisation de la zone de texte.
	 * @param s Le contenu de la JTextArea
	 * @return La JTextArea initialisé
	 */
	
	public JTextArea initTextArea(String s){
		
		JTextArea textArea = new JTextArea();
        textArea.setRows(15);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
		textArea.append(s);
		return textArea;

	}
	
	
	class ButtonListener implements ActionListener
	{ 
		public void actionPerformed(ActionEvent e) {
			String command = ((JButton) e.getSource()).getActionCommand();
			
			if(command=="Démarrer serveur"){
				Serveur2.this.field.setText("");
				Serveur2.this.countLine=0;
				Serveur2.this.initServeur();
				Serveur2.this.hasClient1=false;
				Serveur2.this.hasClient2=false;
			}
			
			if(command=="Stopper serveur"){
				Serveur2.this.field.setText("Serveur stoppé");
				Serveur2.this.countLine=0;
				Serveur2.this.killServeur();
				Serveur2.this.hasClient1=false;
				Serveur2.this.hasClient2=false;
			}
			if(command=="Clear"){
				Serveur2.this.field.setText("");
			}
		} 
	}
	
	/**
	 * Initie le serveur: lance les deux threads acceptant les clients
	 */
	public void initServeur(){
		try{
			
			Serveur2.this.field.setText("");
			
			if(this.actualPort!=-1){
				this.addText("Fermeture du serveur "+actualPort+"...");
				killServeur();
			}
			
			this.hasClient1=false;
			this.hasClient2=false;
			Runtime.getRuntime().addShutdownHook(new ServerSocketClosingThread());
			int numSocket=(int) spinner.getValue();
			socket = new ServerSocket(numSocket);
			this.actualPort=numSocket;
			this.addText("Lancement du serveur... (Port "+numSocket+")");
			client1 = new Accepter_clients(this, socket,0);
			client2 = new Accepter_clients(this, socket,1);
			
			Thread t=new Thread(client1);
			Thread t2=new Thread(client2); 
			t.start();
			t2.start();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Tuer le serveur (fermer le socket)
	 */
	public void killServeur(){
		try {
			this.client1.arreter();
			this.client2.arreter();
			
			this.socket.close();
			this.actualPort=-1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	private class ServerSocketClosingThread extends Thread {
		
		public void run() {
			try {
				socket.close();
				defaultOutStream.println("(II) Closing TCP connection");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		
		Serveur2 serveur= new Serveur2();
		
		
	}
	
	
	
}








