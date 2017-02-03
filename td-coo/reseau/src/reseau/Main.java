package reseau;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	
	public Main() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
		InetAddress local;
		InetAddress serveur;
		
		try{
			local=InetAddress.getLocalHost();
			System.out.println("L'adresse locale est: "+local);
			serveur=InetAddress.getByName("www.cril.fr");
			System.out.println("L'adresse du cril est : "+ serveur);
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
	
	}
}
