import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * JPanel correspondant à une partie de 2048
 * Dispose d'un panel pour le menu, un pour la grille.
 * Gestion des déplacements.  
 * @author loick
 *
 */

public class EcranJeu extends JPanel{

	private Fenetre f;
	private JLabel title;
	private IA ia;
	
	protected Score score;
	protected JPanel menu;
	protected Grille g;
	
	protected boolean continuer;
	protected boolean is_IA;
	
	/**
	 * Créé un nouveau JPanel de jeu (Une fois)
	 * @param f Fenetre pour ajouter les ActionListener
	 * @throws IOException
	 */
	public EcranJeu(Fenetre f)
	{
		menu=new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		
		this.f=f;
		this.setFocusable(true);
		this.requestFocus();
		
		title=new JLabel("2048");
		title.setSize(new Dimension(150,50));
		title.setFont(new Font("Arial",Font.BOLD,40));
		//title.setAlignmentX(this.LEFT_ALIGNMENT);
		//title.setFont(new Font());
		score = new Score();
		
		
		
		g = new Grille();
		
		this.add(Box.createRigidArea(new Dimension(0,15)));
		menu.add(title);
		menu.add(Box.createRigidArea(new Dimension(50,0)));
		menu.add(score);
		this.add(menu);
		
		this.add(Box.createRigidArea(new Dimension(0,15)));
		this.add(g);
		

		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				EcranJeu.this.gestion(e);
			}
		});
		
		
	}
	
	/**
	 * Initialise l'IA si elle est demandé par l'utilisateur
	 * @throws InterruptedException
	 */
	public void initIA() throws InterruptedException
	{
		ia=new IA(f);
	}
	
	
	/**
	 * Appelé à chaque début de partie, on remet chaque label à 0, 
	 */
	public void reinit()
	{
		this.continuer=false;
		this.score.reinit();
		this.g.reinit();
		
	}
	
	/**
	 * Gestion clavier des directions et des touches quitter(ESC) aide(H) et recommencer(J)
	 * @param e
	 */
	public void gestion(KeyEvent e){
		int keyCode=e.getKeyCode();
		
		boolean has_moved=false, has_fused=false;
		
	    switch(keyCode) { 
	        case KeyEvent.VK_UP:
	        	has_fused=this.g.fusion(Direction.NORD,score,true);
	        	has_moved=this.g.deplacerCase(Direction.NORD,true);
	            break;
	        case KeyEvent.VK_DOWN:
	        	has_fused=this.g.fusion(Direction.SUD,score,true);
	        	has_moved=this.g.deplacerCase(Direction.SUD,true);
	            break;
	        case KeyEvent.VK_LEFT:
	        	has_fused=this.g.fusion(Direction.OUEST,score,true);
	        	has_moved=this.g.deplacerCase(Direction.OUEST,true);
	            break;
	        case KeyEvent.VK_RIGHT :
	        	has_fused=this.g.fusion(Direction.EST,score,true);
	        	has_moved=this.g.deplacerCase(Direction.EST,true);
	            break;
	        case KeyEvent.VK_ESCAPE:
	        	f.dispose();
	        	break;
	        case KeyEvent.VK_J:
	        	this.reinit();
	        	break;
	        case KeyEvent.VK_H:
	        	f.afficherPanneau(f.lePanneau4);
	        	break;
	     }
	    
	    if(has_moved||has_fused)
        	this.g.apparitionCase();
	    
	    //Verifier si déplacement possible : game over
	    if(this.g.nbSet==Grille.NB_COLONNES*Grille.NB_LIGNES&&!this.g.canMove())
	    {
	    	f.lePanneau2.s.setScoreValue(f.lePanneau.score.scoreValue);
	    	f.lePanneau2.gameOverLabel.setText("Game Over");
	    	f.lePanneau2.g=f.lePanneau.g.clone();
			f.lePanneau2.add(f.lePanneau2.g);//add car n'arrive pas à mettre a jour si on garde l'instance à chaque fois
			
			f.lePanneau2.b4.setVisible(false);
	    	f.afficherPanneau(f.lePanneau2);
	    	
	    }
	    
	    else 
	    	testGagne();
	    
	}



	/**
	 * Teste si une case de la grille est à 2048, et lance l'écran de victoire si c'est le cas.
	 * @return
	 */
	public boolean testGagne()
	{
		if(this.g.isInside(2048)&&!continuer)
	    {
	    	f.lePanneau2.s.setScoreValue(f.lePanneau.score.scoreValue);
	    	f.lePanneau2.gameOverLabel.setText("Gogné!");
	    	f.lePanneau2.g=f.lePanneau.g.clone();
			f.lePanneau2.add(f.lePanneau2.g);//add car n'arrive pas à mettre a jour si on garde l'instance à chaque fois
			
	    	f.lePanneau2.b4.setVisible(true);
	    	f.afficherPanneau(f.lePanneau2);
	    	return true;
	    }
		return false;
	}
}

