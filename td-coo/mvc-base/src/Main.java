import com.controler.AbstractControler;
import com.model.AbstractModel;
import com.vue.NomVue;

public class Main {
	public static void main(String[] args) {
		//Instanciation de notre modele
		AbstractModel model = new XXX();
		//Creation du controleur
		AbstractControler controler = new XXXControler(model);
		//Creation de notre fenetre avec le controleur en parametre
		NomVue vue = new NomVue(controler);
		//Ajout de la fenetre comme observer de notre modele
		model.addObserver(vue);
	}
}