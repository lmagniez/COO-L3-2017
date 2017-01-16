import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Case extends JLabel{

	private static final int NB_COLORS=9;
	
	private static Color[] bgColor={
		Colors.case0, Colors.case2, Colors.case4, Colors.case8, Colors.case16,
		Colors.case32, Colors.case64, Colors.case128, Colors.case2048
	};
	private static Color[] textColor={
		Colors.textColor1, Colors.textColor1, Colors.textColor1, Colors.textColor2,
		Colors.textColor2, Colors.textColor2, Colors.textColor2, Colors.textColor2,
		Colors.textColor2
	};
	private static int[] values = {0,2,4,8,16,32,64,128,2048};
	
	protected int idColor;
	protected int value; 
	
	public Case()
	{
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);
		
		this.value=0;
		this.idColor=0;
		
		//this.setText("test");
		this.setPreferredSize(new Dimension(90,90));
		this.setSize(90,90);
		
		this.setBackground(bgColor[idColor]);
		this.setForeground(textColor[idColor]);
	
		this.setOpaque(true);
		this.setVisible(true);
		
		
	}
	
	public void changeValue(int value)
	{
		int id=getIdFromValue(value);
		this.idColor=id;
		this.value=value;
		if(value==0)
			this.setText("");
		else
			this.setText(value+"");
		this.setBackground(bgColor[idColor]);
		this.setForeground(textColor[idColor]);
	}
	
	/**
	 * Récupère l'id de couleur associé à la valeur 
	 * @param value valeur
	 * @return id de couleur associé
	 */
	public int getIdFromValue(int value)
	{
		for(int i=0; i<NB_COLORS; i++)
		{
			if(value==values[i])
				return i;
		}
		return values[NB_COLORS-1];
	}
	
	
}
