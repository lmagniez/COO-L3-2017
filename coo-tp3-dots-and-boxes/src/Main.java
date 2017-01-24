import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.VuePrincipale2;

public class Main {
	public static void main(String[] args) {
		//Instanciation de notre modele
		AbstractModel model = new GrilleModel(2, 3);
		//Creation du controleur
		AbstractControler controler = new GrilleControler(model);
		//Creation de notre fenetre avec le controleur en parametre
		VuePrincipale2 vue = new VuePrincipale2(controler, 2,3);
		//Ajout de la fenetre comme observer de notre modele
		model.addObserver(vue);
	}
}