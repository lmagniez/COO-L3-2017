import java.io.IOException;

import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.controler.MenuControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.grille.Vue2;
import com.vue.titre.Vue1;

/**
 * Class Main.
 * Lance une fenetre de parametrage de jeu
 * @author loick
 *
 */

public class Main {
	public static void main(String[] args) {
		
		Vue1 f = new Vue1(new MenuControler());
		
	}
}