package com.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Classe représentant une grille de jeu de manière logique
 * @author loick
 *
 */

public class GrilleModel extends AbstractModel{

	protected int nbX,nbY;
	protected CaseModel[][] grille;
	protected CaseValue tour;
	protected int nbJetons;
	protected int nbJetonsJ1;
	protected int nbJetonsJ2;
	
	protected boolean[] isIA;
	protected IA ia;
	
	/**
	 * Constructeur normal
	 * @param nbX nombre de cases en abscisse
	 * @param nbY nombre de cases en ordonnée
	 * @param isIA types de joueurs (true:IA false:HUMAIN)
	 * @param J1Start 
	 */
	public GrilleModel(int nbX, int nbY,boolean[] isIA, boolean J1Start){
		
		this.isIA=isIA;
		
		this.ia=new IA(this);
		
		
		
		
		this.nbX=nbX;
		this.nbY=nbY;
		
		this.grille=new CaseModel[nbX][nbY];
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				grille[i][j]=new CaseModel();
			}
		}
		nbJetons=0;
		nbJetonsJ1=0;
		nbJetonsJ2=0;
		
		if(J1Start){
			tour=CaseValue.J1;
		}
		else{
			tour=CaseValue.J2;
		}
		
	}
	
	/**
	 * Constructeur vide (pour récupérer la sauvegarde ensuite)
	 */
	public GrilleModel(){
		this.ia=new IA(this);
		this.recupXML();
		ArrayList<int[]> liste= this.peutJouer(getTour());
		this.notifyPosJouable(liste);
	}
	
	/**
	 * Sauvegarde d'une partie (voir rapport pour la structure)
	 */
	public void genererXML(){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xml = builder.newDocument();
			Element root = xml.createElement("grille");
			
			Element trunk1 = xml.createElement("nbX");
			trunk1.setTextContent(this.nbX+"");
			root.appendChild(trunk1);
			
			Element trunk2 = xml.createElement("nbY");
			trunk2.setTextContent(this.nbY+"");
			root.appendChild(trunk2);
			
			Element trunk3 = xml.createElement("IAJ1");
			trunk3.setTextContent(this.isIA[0]+"");
			root.appendChild(trunk3);
			
			Element trunk4 = xml.createElement("IAJ2");
			trunk4.setTextContent(this.isIA[1]+"");
			root.appendChild(trunk4);
			
			for(int i=0; i<nbX; i++){
				for(int j=0; j<nbY; j++){
					Element trunk = xml.createElement("case");
					trunk.setAttribute("x", ""+i);
					trunk.setAttribute("y", ""+j);
					Element branche = xml.createElement("value");
					branche.setTextContent(grille[i][j].v+"");
					trunk.appendChild(branche);
					root.appendChild(trunk);
				}
			}
			
			Transformer t;
			t = TransformerFactory.newInstance().newTransformer();
			String resultFile = "save.xml";
			StreamResult XML = new StreamResult(resultFile);
			t.transform(new DOMSource(root), XML);
		} catch (ParserConfigurationException e) {
		e.printStackTrace();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
		e.printStackTrace();
		} catch (TransformerException e) {
		e.printStackTrace();
		}
		
	}
	
	/**
	 * Charger une partie (Voir le rapport pour la structure).
	 * Appelé dans le constructeur simple.
	 */
	public void recupXML(){
		
		// Une instance de factory se charge de nous fournir un parseur
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			// Creation du parseur via la factory
			DocumentBuilder builder = factory.newDocumentBuilder();
			//creation de notre objet d'erreurs
			ErrorHandler errHandler = new SimpleErrorHandler();
			//Affectation de notre objet au document pour interception des erreurs eventuelles
			builder.setErrorHandler(errHandler);
			try {
				File fileXML = new File("save.xml");
				
				// parsing de notre fichier via un objet File et recuperation d'un objet Document
				// Ce dernier represente la hierarchie d'objet creee pendant le parsing
				Document xml = builder.parse(fileXML);
				// Via notre objet Document, nous pouvons recuperer un objet Element, ici nous prenons la racine
				Element root = xml.getDocumentElement();
				
				NodeList list=root.getChildNodes();
				
				this.nbX=Integer.parseInt(list.item(1).getTextContent());
				this.nbY=Integer.parseInt(list.item(0).getTextContent());
				this.grille=new CaseModel[nbX][nbY];
				for(int i=0; i<nbX; i++){
					for(int j=0; j<nbY; j++){
						grille[i][j]=new CaseModel();
					}	
				}
					
				this.isIA=new boolean[2];
				this.isIA[0]=Boolean.valueOf(list.item(2).getTextContent());
				this.isIA[1]=Boolean.valueOf(list.item(3).getTextContent());
				
				this.nbJetons=0;
				this.nbJetonsJ1=0;
				this.nbJetonsJ2=0;
				
				for(int i=4; i<list.getLength(); i++){
					NamedNodeMap coordonees=list.item(i).getAttributes();
					int x=Integer.parseInt(coordonees.getNamedItem("x").getNodeValue());
					int y=Integer.parseInt(coordonees.getNamedItem("y").getNodeValue());
					CaseValue value=CaseValue.fromString(list.item(i).getTextContent());
					
					if(value!=CaseValue.EMPTY)
						this.placer(x, y, value);
					
				}
				
				if(nbJetons%2==0) tour=CaseValue.J1;
				else tour=CaseValue.J2;
				
			
			
			} catch (SAXParseException e){}
			
			
		} catch (ParserConfigurationException e) {
		e.printStackTrace();
		} catch (SAXException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Démarrer une IA
	 */
	public void startIA(){
		this.ia.start();
	}
	
	/**
	 * Stopper une IA
	 */
	public void stopIA(){
		this.ia.arret();
	}
	
	/**
	 * Placer les 4 pions de l'initialisation
	 * On notifie également la liste de pions jouables dans cette méthode.
	 * A utiliser une fois le lien entre vue et modèle réalisé.
	 */
	public void placerinit(){
		this.placer(this.nbX/2-1, this.nbY/2-1, CaseValue.J1);
		this.placer(this.nbX/2, this.nbY/2-1, CaseValue.J2);
		this.placer(this.nbX/2-1, this.nbY/2, CaseValue.J2);
		this.placer(this.nbX/2, this.nbY/2, CaseValue.J1);
		
		ArrayList<int[]> liste= this.peutJouer(getTour());
		this.notifyPosJouable(liste);
		
	}
	
	
	/**
	 * Placer une pion et notifier la vue
	 * (Pas de prise de pion)
	 * @param x abscisse
	 * @param y ordonnée
	 * @param v valeur
	 */
	public void placer(int x, int y, CaseValue v){
		this.nbJetons++;
		if(v==CaseValue.J1)
			this.nbJetonsJ1++;
		else
			this.nbJetonsJ2++;		
		
		this.grille[x][y].v=v;
		this.notifyChangeValue(x, y, v);
		int i,j;
	}
	
	/**
	 * Notifier la vue de l'ensemble des éléments une fois la grille chargée sur le modèle
	 */
	public void initCharger(){
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				if(grille[i][j].v!=CaseValue.EMPTY)
					this.notifyChangeValue(i, j, grille[i][j].v);
			}
		}
		this.notifyTour(this.tour);
		this.notifyScore(nbJetonsJ1, nbJetonsJ2);
		ArrayList<int[]> liste= this.peutJouer(getTour());
		this.notifyPosJouable(liste);
		
		
	}
	
	/**
	 * Vérifier si on peut placer un pion
	 * @param x abscisse 
	 * @param y ordonnée
	 * @return boolean[8] l'ensemble des directions ou des modifications vont avoir lieu (H,HD,D,BD,B,BG,G,HG)
	 */
	public boolean[] peutPlacer(int x, int y, CaseValue v){
		
		
		
		boolean res[]={false,false,false,false,false,false,false,false};
		
		if(grille[x][y].v!=CaseValue.EMPTY){
			System.out.println("not emptyyy");
			return res;
		}
		
		CaseValue valeurJ1=v;
		CaseValue valeurJ2 = null;
		if(valeurJ1==CaseValue.J1)valeurJ2=CaseValue.J2;
		if(valeurJ1==CaseValue.J2)valeurJ2=CaseValue.J1;
		
		int i=x, j=y, cpt=0;
		
		//haut
		j=y-1;cpt=0;
		while(j>=0&&grille[i][j].v==valeurJ2){
			j--;cpt++;
		}
		if(cpt>0&&j!=-1&&grille[i][j].v==valeurJ1)
			res[0]=true;
		
		//diag haut droite
		j=y-1; i=x+1;cpt=0;
		while(j>=0&&i<nbX&&grille[i][j].v==valeurJ2){
			j--;i++;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[1]=true;
		
		//droite
		i=x+1;j=y;cpt=0;
		while(i<this.nbX&&grille[i][j].v==valeurJ2){
			i++;cpt++;
		}
		if(cpt>0&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[2]=true;
		
		//diag bas droite
		j=y+1; i=x+1;cpt=0;
		while(j<nbY&&i<nbX&&grille[i][j].v==valeurJ2){
			j++;i++;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=nbX&&grille[i][j].v==valeurJ1)
			res[3]=true;
		
		//bas
		i=x;j=y+1;cpt=0;
		while(j<this.nbY&&grille[i][j].v==valeurJ2){
			j++;cpt++;
		}
		if(cpt>0&&j!=nbY&&grille[i][j].v==valeurJ1)
			res[4]=true;
		
		//diag bas gauche
		j=y+1; i=x-1;cpt=0;
		while(j<nbY&&i>=0&&grille[i][j].v==valeurJ2){
			j++;i--;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=-1&&grille[i][j].v==valeurJ1)
			res[5]=true;
		
		//gauche
		i=x-1;j=y;cpt=0;
		while(i>=0&&grille[i][j].v==valeurJ2){
			i--;cpt++;
		}
		if(cpt>0&&i!=-1&&grille[i][j].v==valeurJ1)
			res[6]=true;
		
		//diag haut gauche
		j=y-1; i=x-1;cpt=0;
		while(j>=0&&i>=0&&grille[i][j].v==valeurJ2){
			j--;i--;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=-1&&grille[i][j].v==valeurJ1)
			res[7]=true;
		
		return res;
		
	}
	
	/**
	 * Remplir une case et les autres cases affectées (utiliser peutPlacer au préalable)
	 * Notifie l'ensemble des changements de valeur de case, puis notifie le score.
	 * @param x abscisse
	 * @param y ordonnée
	 * @param directions ensemble de directions possibles (utiliser peutPlacer)
	 * @param v valeur
	 */
	public void remplirCase(int x, int y, boolean[] directions, CaseValue v){
		
		//ajoute un jeton
		this.nbJetons++;
		if(v==CaseValue.J1)
			this.nbJetonsJ1++;
		else
			this.nbJetonsJ2++;
		
		//change la valeur
		this.grille[x][y].v=v;
		this.notifyChangeValue(x, y, v);
		int i,j;
		
		//H
		if(directions[0]){
			i=x;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//HD
		if(directions[1]){
			i=x+1;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j--;i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//D
		if(directions[2]){
			i=x+1;j=y;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//BD
		if(directions[3]){
			i=x+1;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;i++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//B
		if(directions[4]){
			i=x;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//BG
		if(directions[5]){
			i=x-1;j=y+1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				j++;i--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//G
		if(directions[6]){
			i=x-1;j=y;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		//HG
		if(directions[7]){
			i=x-1;j=y-1;
			while(grille[i][j].v!=v){
				this.notifyChangeValue(i, j, v);
				grille[i][j].v=v;
				i--;j--;
				if(v==CaseValue.J1){
					this.nbJetonsJ1++;this.nbJetonsJ2--;
				}
				else{
					this.nbJetonsJ2++;this.nbJetonsJ1--;
				}
			}
		}
		
		this.notifyScore(nbJetonsJ1,nbJetonsJ2);
		
		
	}
	
	/**
	 * Nombres de cases prises pour un coup donné (Utilisé pour l'IA)
	 * @param x abscisse
	 * @param y ordonnée
	 * @param v valeur
	 * @return nombre de cases prises
	 */
	public int nbCasesPrises(int x, int y, CaseValue v){

		int res=0;
		
		if(grille[x][y].v!=CaseValue.EMPTY){
			return res;
		}
		
		CaseValue valeurJ1=v;
		CaseValue valeurJ2 = null;
		if(valeurJ1==CaseValue.J1)valeurJ2=CaseValue.J2;
		if(valeurJ1==CaseValue.J2)valeurJ2=CaseValue.J1;
		
		
		
		int i=x, j=y, cpt=0;
		
		//haut
		j=y-1;cpt=0;
		while(j>=0&&grille[i][j].v==valeurJ2){
			j--;cpt++;
		}
		if(cpt>0&&j!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag haut droite
		j=y-1; i=x+1;cpt=0;
		while(j>=0&&i<nbX&&grille[i][j].v==valeurJ2){
			j--;i++;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//droite
		i=x+1;j=y;cpt=0;
		while(i<this.nbX&&grille[i][j].v==valeurJ2){
			i++;cpt++;
		}
		if(cpt>0&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag bas droite
		j=y+1; i=x+1;cpt=0;
		while(j<nbY&&i<nbX&&grille[i][j].v==valeurJ2){
			j++;i++;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=nbX&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//bas
		i=x;j=y+1;cpt=0;
		while(j<this.nbY&&grille[i][j].v==valeurJ2){
			j++;cpt++;
		}
		if(cpt>0&&j!=nbY&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag bas gauche
		j=y+1; i=x-1;cpt=0;
		while(j<nbY&&i>=0&&grille[i][j].v==valeurJ2){
			j++;i--;cpt++;
		}
		if(cpt>0&&j!=nbY&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//gauche
		i=x-1;j=y;cpt=0;
		while(i>=0&&grille[i][j].v==valeurJ2){
			i--;cpt++;
		}
		if(cpt>0&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		//diag haut gauche
		j=y-1; i=x-1;cpt=0;
		while(j>=0&&i>=0&&grille[i][j].v==valeurJ2){
			j--;i--;cpt++;
		}
		if(cpt>0&&j!=-1&&i!=-1&&grille[i][j].v==valeurJ1)
			res+=cpt;
		
		return res;
		
	}
	
	
	/**
	 * Vérifier si la grille est pleine
	 * @return
	 */
	public boolean isFull(){
		return this.nbJetons==this.nbX*this.nbY;
	}
	
	/**
	 * Vérifie où le joueur peut jouer sur la grille
	 * @param tour joueur actuel
	 * @return liste de coordonnées (x,y) où le joueur peut jouer 
	 */
	public ArrayList<int[]> peutJouer(CaseValue tour){
		ArrayList<int[]> liste=new ArrayList<int[]>();
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				
				if(this.grille[i][j].v==CaseValue.EMPTY){
				
					boolean res[]=peutPlacer(i,j,tour);
					for(int k=0; k<8; k++){
						if(res[k]){
							int[] point=new int[2];
							point[0]=i;
							point[1]=j;
							liste.add(point);
							
						}
							
					}
				}
				
			}
		}
		return liste;
	}
	
	/**
	 * Notifier le winner à la vue
	 */
	public void getWinner(){
		if(this.nbJetonsJ1>this.nbJetonsJ2)
			this.notifyWin(CaseValue.J1);
		else
			this.notifyWin(CaseValue.J2);
	}
	
	/**
	 * Changer le tour et notifier la vue
	 */
	public void changerTour(){
		
		if(tour==CaseValue.J1){
			tour=CaseValue.J2;
		}
		else{
			tour=CaseValue.J1;
		}
		
		this.notifyTour(tour);
	}
	
	
	/**
	 * Affichage
	 */
	public void afficherGrille(){
		
		for(int i=0; i<nbY; i++){
			for(int j=0; j<nbX; j++){
				if(this.grille[j][i].v==CaseValue.EMPTY)System.out.print("EM ");
				else System.out.print(this.grille[j][i].v+" ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Réinitialisation et notification de la vue
	 */
	public void reinit(){
		for(int i=0; i<nbX; i++){
			for(int j=0; j<nbY; j++){
				grille[i][j].v=CaseValue.EMPTY;
			}
		}
		
		this.nbJetons=0;
		this.nbJetonsJ1=0;
		this.nbJetonsJ2=0;
		this.notifyReinit();
		
		this.placerinit();
		
	}
	
	public CaseValue getTour(){
		return tour;
	}

	public int getNbX() {
		return nbX;
	}

	public void setNbX(int nbX) {
		this.nbX = nbX;
	}

	public int getNbY() {
		return nbY;
	}

	public void setNbY(int nbY) {
		this.nbY = nbY;
	}

	@Override
	public void sauvegarder() {
		// TODO Auto-generated method stub
		this.genererXML();
	}
	
	/*
	
	public static void main(String[] args) {
		
		GrilleModel m = new GrilleModel(6,5, true);
		
		m.grille[2][2].v=CaseValue.J1;
		m.grille[2][3].v=CaseValue.J1;
		m.grille[2][4].v=CaseValue.J1;
		m.grille[3][2].v=CaseValue.J1;
		m.grille[3][3].v=CaseValue.J1;
		m.grille[3][1].v=CaseValue.J1;
		//m.grille[4][0].v=CaseValue.J2;
		m.grille[4][1].v=CaseValue.J1;
		m.grille[4][2].v=CaseValue.J2;
		m.grille[1][3].v=CaseValue.J2;
		m.grille[2][1].v=CaseValue.J2;
		//m.grille[5][2].v=CaseValue.J1;
		
		m.afficherGrille();
		
		boolean[] res=m.peutPlacer(4, 3, CaseValue.J2);
		m.remplirCase(4,3,res, CaseValue.J2);
		
		for(int i=0; i<8; i++){
			System.out.print(res[i]+" ");
			
		}
		System.out.println();
		
		m.afficherGrille();
		
	}
	*/

	
	
}
