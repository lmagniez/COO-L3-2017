package objets;

import javax.swing.ImageIcon;

public class TuyauRouge extends Objet{

	TuyauRouge(int x, int y){
		super(43,65,x,y);
		this.icoObjet=new ImageIcon(getClass().getResource("./images/tuyauRouge.png"));
		this.imgObjet=this.icoObjet.getImage();
	}
	
}
