
import java.sql.SQLException;

import com.controler.AbstractControler;
import com.controler.GridControler;
import com.model.AbstractModel;
import com.model.JeuModel;
import com.vue.titre.Vue1;

/**
 * Class Main.
 * Lance la vue du menu de démarrage. (écran titre + paramètrage)
 * @author loick
 *
 */

public class Main {
	public static void main(String[] args) throws SQLException {
		
		//Creation du modele de grille
		AbstractModel jeuModel = new JeuModel();
		//Creation du controleur
		AbstractControler gridControler = new GridControler(jeuModel);
		Vue1 vue= new Vue1(gridControler);

		jeuModel.addObserver(vue);
		vue.requestGrilles();
	}
}