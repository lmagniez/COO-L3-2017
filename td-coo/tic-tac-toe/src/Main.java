import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.VuePrincipale;

public class Main {
	public static void main(String[] args) {
		//Instanciation de notre modele
		AbstractModel model = new GrilleModel();
		//Creation du controleur
		AbstractControler controler = new GrilleControler(model);
		//Creation de notre fenetre avec le controleur en parametre
		VuePrincipale vue = new VuePrincipale(controler);
		//Ajout de la fenetre comme observer de notre modele
		model.addObserver(vue);
	}
}