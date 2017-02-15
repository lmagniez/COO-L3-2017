package reseau.Shifumi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.vue.grid.VueGrid;


public class Serveur2 extends JFrame{

	protected JButton quit;
	protected ServerSocket socket;
	private PrintStream defaultOutStream = System.out;
	protected boolean hasClient1,hasClient2;
	
	protected Accepter_clients client1,client2;
	protected JTextArea field;
	
	public Serveur2(){
		
		this.setTitle("Serveur");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setFocusable(false);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		
		JButton b1=new JButton("Démarrer serveur");
		b1.addActionListener(new ButtonListener());
		JButton b2=new JButton("Stopper serveur");
		b2.addActionListener(new ButtonListener());
		
		field=initTextArea("message");
		
		this.add(b1);
		this.add(b2);
		this.add(field);
		
		this.setVisible(true);
		
		
	}
	
	public void addText(String s){
		this.field.setText(field.getText()+"\n"+s);
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
				Serveur2.this.initServeur();
				Serveur2.this.hasClient1=false;
				Serveur2.this.hasClient2=false;
			}
			
			if(command=="Stopper serveur"){
				Serveur2.this.killServeur();
				Serveur2.this.hasClient1=false;
				Serveur2.this.hasClient2=false;
			}
		} 
	}
	
	public void initServeur(){
		try{
			this.hasClient1=false;
			this.hasClient2=false;
			Runtime.getRuntime().addShutdownHook(new ServerSocketClosingThread());
			socket = new ServerSocket(1791);
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
	
	public void killServeur(){
		try {
			this.socket.close();
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








