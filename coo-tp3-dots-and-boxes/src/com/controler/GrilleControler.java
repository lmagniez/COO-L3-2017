package com.controler;


import com.model.AbstractModel;
import com.model.BoxValues;
import com.model.Direction;

public class GrilleControler extends AbstractControler{

	 public GrilleControler(AbstractModel cal) {
		    super(cal);
	 }

	 public void control()
	 {
		 if(vertical==false)
			 this.calc.ajoutTraitH(x, y);
		 else
			 this.calc.ajoutTraitV(x, y);
	 }
	
}
