import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.grille.Vue2;

public class Main {
	public static void main(String[] args) {
		//Instanciation de notre modele
		AbstractModel model = new GrilleModel(7, 3);
		//Creation du controleur
		AbstractControler controler = new GrilleControler(model);
		//Creation de notre fenetre avec le controleur en parametre
		Vue2 vue = new Vue2(controler, 7,3);
		//Ajout de la fenetre comme observer de notre modele
		model.addObserver(vue);
	}
}