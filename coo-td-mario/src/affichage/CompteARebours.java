package affichage;

public class CompteARebours implements Runnable{

	private int compteurTemps;
	private String str;
	
	public CompteARebours(){
		this.compteurTemps=100;
		this.str="Temps restant: "+compteurTemps;
		
		Thread compteARebours=new Thread(this);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		compteurTemps=compteurTemps-1;
		
	}

}
