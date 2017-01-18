import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Score extends JLabel{

	protected int scoreValue;
	
	/**
	 * Création du score et initialisation du texte
	 */
	public Score()
	{
		this.scoreValue=0;
		this.setText("Score: "+scoreValue);
		
		this.setSize(new Dimension(150,50));
		this.setFont(new Font("Arial",Font.BOLD,15));
		
	}
	
	/**
	 * Ajoute nb au score actuel et met à jour le JLabel
	 * @param nb valeur ajouté au score
	 */
	public void addScore(int nb)
	{
		this.scoreValue+=nb;
		this.setText("Score: "+scoreValue);
	}
	
	/**
	 * Réinitialise le score à 0 et met à jour le label
	 */
	public void reinit()
	{
		this.scoreValue=0;
		this.setText("Score: "+scoreValue);
	}
	
}
