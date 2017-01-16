import javax.swing.JLabel;

/**
 * Classe représentant un pendu en ASCII
 * @author loick
 *
 */
public class Pendu extends JLabel
{
	 
	
	public Pendu()
	{
		this.setText(tabDraw[0]);
	}
	
	private static final String[] tabDraw=
	{
				"",
				
				"<html>"
				+"<br>"
				+"&nbsp  o "
				+"</html>",
				
				"<html>"
				+"<br>"
				+"&nbsp  o <br>"
				+"&nbsp	 | <br>"
				+"</html>",
				
				"<html>"
				+"<br>"
				+"&nbsp  o <br>"
				+"		-|- </html>",
				
				"<html>"
				+"<br>"
				+"&nbsp  o  <br>"
				+"		-|- <br>"
				+"  	/ \\</html>",
				
				"<html>"
				+"&nbsp  |<br>"
				+"&nbsp  o  <br>"
				+"		-|- <br>"
				+"  	/ \\<br>"
				+"</html>",
				
				"<html>"
				+"&nbsp  |-----<br>"
				+"&nbsp  o  <br>"
				+"		-|- <br>"
				+"  	/ \\<br>"
				+"</html>",
				
				"<html>"
				+"&nbsp  |-----<br>"
				+"&nbsp  o  &nbsp &nbsp 	 |<br>"
				+"		-|- &nbsp &nbsp 	 <br>"
				+"  	/ \\&nbsp &nbsp&nbsp <br>"
				+"&nbsp &nbsp &nbsp 		</html>",
				
				"<html>"
				+"&nbsp  |-----<br>"
				+"&nbsp  o  &nbsp &nbsp 	 |<br>"
				+"		-|- &nbsp &nbsp 	 |<br>"
				+"  	/ \\&nbsp &nbsp&nbsp <br>"
				+"&nbsp &nbsp &nbsp 		</html>",

				"<html>"
				+"&nbsp  |-----<br>"
				+"&nbsp  o  &nbsp &nbsp 	 |<br>"
				+"		-|- &nbsp &nbsp 	 |<br>"
				+"  	/ \\&nbsp &nbsp&nbsp |<br>"
				+"&nbsp &nbsp &nbsp 		</html>",
				
				"<html>"
				+"&nbsp  |-----<br>"
				+"&nbsp  o  &nbsp &nbsp 	 |<br>"
				+"		-|- &nbsp &nbsp 	 |<br>"
				+"  	/ \\&nbsp &nbsp&nbsp |<br>"
				+"&nbsp &nbsp &nbsp 		_|_</html>",
				
		};
		
	public void changerPendu(int i)
	{
		this.setText(tabDraw[i]);
	}
				
				
				
				
				
		
	
	
	
	
	/*
 |---
 o	|
-|- |
/ \ |
   _|_
	*/
	
}
