package com.controler;



import java.sql.Time;

import com.model.AbstractModel;
import com.model.Direction;

/**
 * Class abstraite des controlers.
 * Chaque controler étendra cette classe.
 * @author loick
 *
 */

public abstract class AbstractControler {

	
	//Modèle
	protected AbstractModel calc;
	
	protected int id;
	protected Direction d;
	protected boolean bonus;
	protected boolean speed;
	protected boolean throwBall;
	
	
	

	/**
	 * Constructeur avec modèle
	 * @param cal modèle
	 */
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
	}
	
	//Definir toutes les methodes de modifications (les setters)

	/**
	 * Changer l'id de l'objet à modifier (pour la balle).
	 * @param id id de l'objet
	 */
	public void setChange(int id){
		this.id=id;
		control();
	}
	
	/**
	 * Changer l'id et la direction de l'objet à modifier (pour la raquette).
	 * @param id id de l'objet
	 * @param d direction de l'objet (Haut/Bas)
	 */
	public void setChange(int id, Direction d){
		this.id=id;
		this.d=d;
		control();
	}
	
	/**
	 * Prévenir l'apparition d'un nouveau bonus
	 * @param bonus booléen (toujours à true)
	 */
	public void setNouveauBonus(boolean bonus){
		this.bonus=bonus;
		control();
	}
	
	/**
	 * Prévenir l'augmentation de la vitesse
	 * @param vitesse booléen (toujours à true)
	 */
	public void setVitesse(boolean vitesse){
		this.speed=vitesse;
		control();
	}
	
	/**
	 * Prévenir le lancement de la balle.
	 */
	public void throwBall() {
		this.throwBall=true;
		control();
	}
	
	
	
	/**
	 * Reinitialiser le modèle
	 */
	public void reset(){
		calc.reinit();
	}
	
	/**
	 * Demander la décision d'une IA 
	 * @param idJoueur id de l'IA
	 */
	public void decide(int idJoueur) {
		calc.decide(idJoueur);
	}
	
	
	//Methode de controle
	abstract void control();

	

	
}
