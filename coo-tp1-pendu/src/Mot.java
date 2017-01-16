import javax.swing.JLabel;

/**
 * Classe Texte correspondant à un mot.
 * On a à la fois le mot complet et ce qu'il reste à découvrir.
 * On y retrouve les différentes fonctions de gestion du mot, qui servent à la partie.
 * @author loick
 *
 */
public class Mot extends JLabel{

	private String mot;
	private String motActuel;
	private boolean[] motBool;
	private int lettreRestante;
	
	/**
	 * Constructeur test
	 */
	public Mot()
	{
		
		this.mot="DEFAUT";
		this.motActuel="";
		motBool=new boolean[mot.length()];
		for(int i=0; i<this.mot.length(); i++)
		{
			motActuel=motActuel+"*";
			motBool[i]=false;
		}
		this.lettreRestante=mot.length();
	}
	
	/**
	 * Constructeur d'un mot pour une chaine donnée
	 * @param n mot à deviner, on passe motBool à faux et 
	 * on essaye de tout passer à vrai durant la partie
	 */
	public Mot(String n)
	{
		this.mot=n;
		this.motActuel="";
		motBool=new boolean[mot.length()];
		for(int i=0; i<this.mot.length(); i++)
		{
			motActuel=motActuel+"*";
			motBool[i]=false;
		}
		this.lettreRestante=mot.length();
	}
	
	/**
	 * Change le mot actuel en ajoutant une lettre.
	 * Si la lettre i est présente, on passe motBool[i] à true
	 * Retourne true si present 
	 * @param c caractère à tester
	 */
	public boolean update(char c)
	{
		boolean present=false; 
		for(int i=0; i<mot.length(); i++)
		{
			if(mot.charAt(i)==c)
			{
				present=true;
				motBool[i]=true;
				this.lettreRestante--;
			}
		}
		
		genererMot();
		
		return present;
	}
	
	/**
	 * Génère le mot affiché à l'écran en fonction de ce qui a déjà été trouvé (mot avec étoiles)
	 */
	public void genererMot()
	{
		motActuel="";
		for(int i=0; i<this.mot.length(); i++)
		{
			if(motBool[i])
				motActuel=motActuel+mot.charAt(i);
			else
				motActuel=motActuel+"*";
		}
	}

	public void setMotActuel(String motActuel) {
		this.motActuel = motActuel;
	}
	
	public int getLettreRestante()
	{
		return this.lettreRestante;
	}
	
	public String getMotActuel()
	{
		return motActuel;
	}
	
	
	
	public String getMot() {
		return mot;
	}

	public void afficherMot()
	{
		System.out.println(mot);
	}
	
	public void afficherMotActuel()
	{
		System.out.println(motActuel);
	}
	
	
	
}