import com.controler.AbstractControler;
import com.controler.GrilleControler;
import com.controler.MenuControler;
import com.model.AbstractModel;
import com.model.GrilleModel;
import com.vue.menu.VueMenu;

public class Main {
	public static void main(String[] args) {
		VueMenu v =new VueMenu(new MenuControler());
	}
}