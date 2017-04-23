package com.model.plateau;

import java.util.Random;

import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.plateau.cases.CaseModel;
import com.model.plateau.cases.CommunauteChanceModel;
import com.model.plateau.cases.CouleurTerrain;
import com.model.plateau.cases.DepartImpotTaxeModel;
import com.model.plateau.cases.GareModel;
import com.model.plateau.cases.GendarmeModel;
import com.model.plateau.cases.ParkingModel;
import com.model.plateau.cases.PrisonModel;
import com.model.plateau.cases.ServiceModel;
import com.model.plateau.cases.TerrainModel;
import com.model.plateau.pioche.PiocheModel;
import com.model.plateau.pioche.TypePioche;

/**
 * Modèle du plateau (gère les cases et les différents événements)
 * @author loick
 *
 */
public class PlateauModel {

	protected CaseModel[] cases;
	protected JoueurModel[] joueurs;
	protected int nbJoueur;
	
	protected PiocheModel piocheCommunaute;
	protected PiocheModel piocheChance;
	protected JeuModel model;
	
	/**
	 * Constructeur
	 * @param model Modèle du jeu
	 * @param nbJoueur Nombre de joueurs
	 * @param pionAleatoire position des joueurs aléatoires
	 * @param sommeDepart somme de départ des joueurs
	 */
	public PlateauModel(JeuModel model, int nbJoueur, boolean pionAleatoire, int sommeDepart, String[] nom)
	{
		this.setModel(model);
		this.setNbJoueur(nbJoueur);
		this.setJoueurs(new JoueurModel[nbJoueur]);
		for(int i=0; i<nbJoueur; i++){
			int positionDepart=0;
			
			
			
			if(pionAleatoire){
				Random r = new Random();
				positionDepart=r.nextInt(40);
			}
			this.getJoueurs()[i]=new JoueurModel(this, i, positionDepart, sommeDepart, nom[i]);
			//
			if(i==1&&ConstantesParam.J1_PRISON){
				this.getJoueurs()[i].allerEnPrison();
			}
		}
		
	}
	
	/**
	 * Constructeur pour XML
	 * @param model
	 */
	public PlateauModel(JeuModel model)
	{
		this.model=model;
	}
	

	/**
	 * Générer les cases du plateau de base
	 */
	public void genererCases()
	{
		int nb_cases=0;
		int nb_terrain=0;
		CaseModel cases[]=new CaseModel[ConstantesModel.NB_CASES];
		
		
		int tabLoyers[][]={
				{200,1000,3000,9000,16000,25000},
				{400,2000,6000,18000,32000,45000},
				
				{600,3000,9000,27000,40000,55000},
				{600,3000,9000,27000,40000,55000},
				{800,4000,10000,30000,45000,60000},
				
				{1000,5000,15000,45000,62500,75000},
				{1000,5000,15000,45000,62500,75000},
				{1200,6000,18000,50000,70000,90000},
				
				{1400,7000,20000,55000,75000,95000},
				{1400,7000,20000,55000,75000,95000},
				{1600,8000,22000,60000,80000,100000},
				
				{1800,9000,25000,70000,87500,105000},
				{1800,9000,25000,70000,87500,105000},
				{2000,10000,30000,75000,92500,110000},
				
				{2200,11000,33000,80000,97500,115000},
				{2200,11000,33000,80000,97500,115000},
				{2400,12000,36000,85000,102500,120000},
				
				{2600,13000,39000,90000,110000,127500},
				{2600,13000,39000,90000,110000,127500},
				{2800,15000,45000,100000,120000,140000},
				
				{3500,17500,50000,110000,130000,150000},
				{5000,20000,60000,140000,170000,200000}
				
		};
		
		int tabMaisons[]={5000,5000,10000,10000,15000,15000,20000,20000};
		int tabAchat[]={6000,6000,10000,10000,12000,14000,14000,16000,
				18000,18000,20000,22000,22000,24000,26000,26000,28000,
				30000,30000,32000,35000,40000
		};
		
		String tabNom[]={"Boulevard de la Belleville","Rue Lecourbe",
				"Rue de Vaugirard","Rue de Courcelles","Avenue de la République",
				"Boulevard de la Vilette", "Avenue de Neuilly","Rue du Paradis",
				"Avenue Mozart","Boulevard St-Michel", "Place Pigalle",
				"Avenue Matignon", "Boulevard Malesherbes", "Avenue Henri-Martin",
				"Foubourg St-Honoré", "Place de la Bourse", "Rue de la Fayette",
				"Avenue de Breuteuil", "Avenue Foch", "Boulevard des Capucines",
				"Avenue des Champs-Elysées", "Rue de la Paix"
		};
		
		
		TerrainModel.tabAssoTerrainJoueur=TerrainModel.initTab();
		GareModel.tabAssoGareJoueur=GareModel.initTab();
		ServiceModel.tabAssoServiceJoueur=ServiceModel.initTab();
		
		cases[nb_cases]=new DepartImpotTaxeModel(this,nb_cases,nb_cases++,0,"Depart");//case depart (octroyer 20000 dans le setPos)
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.MARRON,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.MARRON.ordinal()]);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Caisse Communauté",TypePioche.COMMUNAUTE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.MARRON,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.MARRON.ordinal()]);
		cases[nb_cases]=new DepartImpotTaxeModel(this,nb_cases,nb_cases++,-20000,"Impot sur le revenu");
		cases[nb_cases]=new GareModel(this,nb_cases,nb_cases++,"Gare de MontParnasse",20000);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.BLEU_CLAIR,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.BLEU_CLAIR.ordinal()]);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Chance",TypePioche.CHANCE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.BLEU_CLAIR,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.BLEU_CLAIR.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.BLEU_CLAIR,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.BLEU_CLAIR.ordinal()]);
		cases[nb_cases]=new PrisonModel(this, nb_cases,nb_cases++);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VIOLET,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VIOLET.ordinal()]);
		cases[nb_cases]=new ServiceModel(this, nb_cases, nb_cases++, "Compagnie d'électricité", 15000);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VIOLET,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VIOLET.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VIOLET,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VIOLET.ordinal()]);
		cases[nb_cases]=new GareModel(this,nb_cases,nb_cases++,"Gare de Lyon",20000);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ORANGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ORANGE.ordinal()]);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Caisse Communauté",TypePioche.COMMUNAUTE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ORANGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ORANGE.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ORANGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ORANGE.ordinal()]);
		cases[nb_cases]=new ParkingModel(this, nb_cases,nb_cases++);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ROUGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ROUGE.ordinal()]);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Chance",TypePioche.CHANCE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ROUGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ROUGE.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.ROUGE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.ROUGE.ordinal()]);
		cases[nb_cases]=new GareModel(this,nb_cases,nb_cases++,"Gare du Nord",20000);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.JAUNE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.JAUNE.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.JAUNE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.JAUNE.ordinal()]);
		cases[nb_cases]=new ServiceModel(this, nb_cases, nb_cases++, "Compagnie des eaux", 15000);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.JAUNE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.JAUNE.ordinal()]);
		cases[nb_cases]=new GendarmeModel(this, nb_cases,nb_cases++);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VERT,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VERT.ordinal()]);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VERT,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VERT.ordinal()]);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Caisse Communauté",TypePioche.COMMUNAUTE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.VERT,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.VERT.ordinal()]);
		cases[nb_cases]=new GareModel(this,nb_cases,nb_cases++,"Gare Saint-Lazare",20000);
		cases[nb_cases]=new CommunauteChanceModel(this,nb_cases,nb_cases++,"Chance",TypePioche.CHANCE);
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.BLEU_FONCE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.BLEU_FONCE.ordinal()]);
		cases[nb_cases]=new DepartImpotTaxeModel(this,nb_cases,nb_cases++,-10000,"Taxe de Luxe");
		cases[nb_cases]=new TerrainModel(this,nb_cases,nb_cases++,CouleurTerrain.BLEU_FONCE,tabNom[nb_terrain],tabAchat[nb_terrain],tabLoyers[nb_terrain++],tabMaisons[CouleurTerrain.BLEU_FONCE.ordinal()]);
		
		if(ConstantesParam.CASE_ALEA_ENABLED)
			for(int i=0; i<100; i++)
				cases=shuffleCases(cases);
		
		this.setCases(cases);
		this.getModel().notifyCases(this.cases);
		this.getModel().notifyInitTourDes();
	}
	
	public CaseModel[] shuffleCases(CaseModel[] cases){
		Random r = new Random();
		int i1=r.nextInt(cases.length);
		int i2=r.nextInt(cases.length);
		
		if(i1!=0&&i1!=10&&i1!=20&&i1!=30&&i2!=0&&i2!=10&&i2!=20&&i2!=30){
			cases[i1].setPosition(i2);
			cases[i2].setPosition(i1);
		
			CaseModel tmp=cases[i1];
			cases[i1]=cases[i2];
			cases[i2]=tmp;
		}
		
		return cases;
		
		
		
	}
	
	/**
	 * Générer la pioche
	 */
	public void genererPioche(){
		this.piocheCommunaute=new PiocheModel(this,TypePioche.COMMUNAUTE);
		this.piocheChance=new PiocheModel(this,TypePioche.CHANCE);
	}
	
	/**
	 * Renvoie le nombre de maisons total sur le terrain
	 * @return nb total de maisons
	 */
	public int getNbMaisonsTotal()
	{
		int nb=0;
		for(int i=0; i<joueurs.length; i++)
			nb+=joueurs[i].getNbMaison();
		return nb;
	}
	
	/**
	 * Renvoie le nombre d'hotels total sur le terrain
	 * @return nb total d'hotels
	 */
	public int getNbHotelsTotal()
	{
		int nb=0;
		for(int i=0; i<joueurs.length; i++)
			nb+=joueurs[i].getNbHotel();
		return nb;
	}
	
	
	
	/**
	 * Retourne l'id d'une case en fonction de son nom (utile pour carte chance/communauté)
	 * @param nom nom de la case
	 * @return indice de la case
	 */
	public int getIdCaseByName(String nom)
	{
		for(int i=0; i<getCases().length; i++)
			if(nom.equals(getCases()[i].getNom()))
				return i;
		return -1;
	}

	public CaseModel[] getCases() {
		return cases;
	}

	public void setCases(CaseModel[] cases) {
		this.cases = cases;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}

	public JoueurModel[] getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(JoueurModel[] joueurs) {
		this.joueurs = joueurs;
	}

	public PiocheModel getPiocheCommunaute() {
		return piocheCommunaute;
	}

	public void setPiocheCommunaute(PiocheModel piocheCommunaute) {
		this.piocheCommunaute = piocheCommunaute;
	}

	public PiocheModel getPiocheChance() {
		return piocheChance;
	}

	public void setPiocheChance(PiocheModel piocheChance) {
		this.piocheChance = piocheChance;
	}



	public JeuModel getModel() {
		return model;
	}



	public void setModel(JeuModel model) {
		this.model = model;
	}
}
