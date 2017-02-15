package reseau.Shifumi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Accepter_clients implements Runnable {
	private Serveur2 serveur;
	private ServerSocket socketserver;
	private Socket socket;
	private int nbrClient=1;
	private int idClient;
	
	PrintWriter out;
	
	public Accepter_clients(Serveur2 serveur, ServerSocket s, int idClient)
	{
		this.serveur=serveur;
		this.socketserver=s;
		this.idClient=idClient;
		if(idClient==0)
			this.serveur.hasClient1=true;
		if(idClient==1)
			this.serveur.hasClient2=true;
	}
	public void run()
	{
		BufferedReader in;
		try{
			System.out.println("Serveur lancé");
			while(true){
				socket=socketserver.accept();
				System.out.println("Le client num "+nbrClient+" est connecte ! (Thread "+idClient+")");
				nbrClient++;
				
				out=new PrintWriter(socket.getOutputStream());
				out.println("Vous etes connecte joueur "+idClient+" !");
				out.flush();
				
				serveur.addText("ok");
				if(!this.serveur.hasClient2||!this.serveur.hasClient1){
					serveur.addText("ok1");
					out.println("En attente de l'adversaire... "+idClient+" !");
					out.flush();
				}
				serveur.addText("ok2");
				
				
					
				/*while(!this.serveur.hasClient2||!this.serveur.hasClient1){
					Thread.sleep(1000);
				}*/
				
				while(true){
					in=new BufferedReader (new InputStreamReader(socket.getInputStream()));
					String msg = in.readLine();
					System.out.println("Serveur: Message recu du Thread "+ idClient +" : "+msg);
					
					
					
					if(msg==null){
						break;
					}
				}
				if(idClient==0)
					this.serveur.hasClient1=false;
				if(idClient==1)
					this.serveur.hasClient2=false;
				System.out.println("(II) closing connection from "+socket.getInetAddress()+":"+socket.getPort());
				socket.close();
				
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	public void envoyer_message(String msg) throws IOException{
		out=new PrintWriter(socket.getOutputStream());
		out.println(msg);
		out.flush();
	}
	
	
	public void fermerServer(){
		try {
			System.out.println("Arret du serveur...");
			socketserver.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}