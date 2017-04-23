package com.model.plateau;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;

import com.model.AbstractModel;
import com.model.ConstantesModel;
import com.model.ConstantesParam;
import com.model.SimpleErrorHandler;
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
import com.model.plateau.pioche.CarteModel;
import com.model.plateau.pioche.PiocheModel;
import com.model.plateau.pioche.TypePioche;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Modèle du jeu
 * @author loick
 *
 */
public class JeuModel extends AbstractModel{

	private PlateauModel p;
	protected int tour;
	protected int tourPartie;
	
	
	/**
	 * Constructeur
	 */
	public JeuModel(boolean usingSave)
	{
		if(!usingSave){
			this.setP(new PlateauModel(this,ConstantesParam.NB_JOUEURS, ConstantesParam.POSITION_ALEA_ENABLED, ConstantesParam.SOMME_DEPART, ConstantesParam.NAMES));
			this.setTour(0);
			this.tourPartie=1;
		}
		else{
			
			
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

					int cpt=0;
							
					this.setTour(Integer.parseInt(list.item(cpt++).getTextContent()));
					this.tourPartie= Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.NB_JOUEURS=Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.SOMME_DEPART=Integer.parseInt(list.item(cpt++).getTextContent());
					
					NodeList listTmp;
					
					listTmp=list.item(cpt++).getChildNodes();
					ConstantesParam.IS_IA=new boolean[ConstantesParam.NB_JOUEURS];
					for(int i=0; i<listTmp.getLength();i++){
						ConstantesParam.IS_IA[i]=Boolean.valueOf(listTmp.item(i).getTextContent());
					}
					
					listTmp=list.item(cpt++).getChildNodes();
					ConstantesParam.ID_ICONES=new int[ConstantesParam.NB_JOUEURS];
					for(int i=0; i<listTmp.getLength();i++){
						ConstantesParam.ID_ICONES[i]=Integer.parseInt(listTmp.item(i).getTextContent());
					}
					
					listTmp=list.item(cpt++).getChildNodes();
					ConstantesParam.NAMES=new String[ConstantesParam.NB_JOUEURS];
					for(int i=0; i<listTmp.getLength();i++){
						ConstantesParam.NAMES[i]=listTmp.item(i).getTextContent();
					}
					
					ConstantesParam.NB_MAISONS=Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.NB_PROPRIETES=Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.TOUR_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.NB_TOUR=Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.POSITION_ALEA_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.TROIS_DES_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.SOMME_PRISON_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.CHOIX_DES_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.SUICIDE_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.ENCHERE_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.TIMER_VALEUR_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.CASE_ALEA_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.CASE_MASQUE_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.TAUX_ACHAT=Double.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.TAUX_INTERET=Double.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.EGALISATION_ENABLED=Boolean.valueOf(list.item(cpt++).getTextContent());
					ConstantesParam.ENCHERE_TIME=Integer.parseInt(list.item(cpt++).getTextContent());
					ConstantesParam.NB_TOUR_PRISON=Integer.parseInt(list.item(cpt++).getTextContent());
					
					PlateauModel model= new PlateauModel(this);
					
					JoueurModel joueurs[]=new JoueurModel[ConstantesParam.NB_JOUEURS];
					for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
						NodeList joueur=list.item(cpt++).getChildNodes();
						int idJoueur=i;
						int position=Integer.parseInt(joueur.item(0).getTextContent());
						String nom=joueur.item(1).getTextContent();
						int argent=Integer.parseInt(joueur.item(2).getTextContent());
						int valeurPatrimoine=Integer.parseInt(joueur.item(3).getTextContent());
						boolean cartePrison=Boolean.valueOf(joueur.item(4).getTextContent());
						int enPrison=Integer.parseInt(joueur.item(5).getTextContent());
						int nbMaison=Integer.parseInt(joueur.item(6).getTextContent());
						int nbHotel=Integer.parseInt(joueur.item(7).getTextContent());
						int nbProprietes=Integer.parseInt(joueur.item(8).getTextContent());
						boolean gameOver=Boolean.valueOf(joueur.item(9).getTextContent());
						joueurs[i]= new JoueurModel(model, idJoueur, nom, argent, valeurPatrimoine, 
								cartePrison, enPrison, nbMaison, nbHotel, nbProprietes, gameOver, position);
					}
					
					//PLATEAU
					NodeList plateau = list.item(cpt++).getChildNodes();
					int cptPlateau = 0;
					//VARIABLES STATIQUES
					NodeList varStatic=plateau.item(cptPlateau++).getChildNodes();
					int cptStatic=0;
					
					//GARE MODEL
					
					NodeList tab=varStatic.item(cptStatic++).getChildNodes();
					
					GareModel.tabAssoGareJoueur=new int[tab.getLength()];
					for(int i=0; i<tab.getLength(); i++){
						System.out.println(varStatic.item(cptStatic).getNodeName());
						GareModel.tabAssoGareJoueur[i]=Integer.parseInt(tab.item(i).getTextContent());
					}
					GareModel.nb_gare=Integer.parseInt(varStatic.item(cptStatic++).getTextContent());
					tab=varStatic.item(cptStatic++).getChildNodes();
					GareModel.posGares=new int[tab.getLength()];
					for(int i=0; i<tab.getLength(); i++){
						GareModel.posGares[i]=Integer.parseInt(tab.item(i).getTextContent());
					}
					
					
					//SERVICE MODEL
					tab=varStatic.item(cptStatic++).getChildNodes();
					ServiceModel.tabAssoServiceJoueur=new int[tab.getLength()];
					for(int i=0; i<tab.getLength(); i++){
						ServiceModel.tabAssoServiceJoueur[i]=Integer.parseInt(tab.item(i).getTextContent());
					}
					ServiceModel.setNb_service(Integer.parseInt(varStatic.item(cptStatic++).getTextContent()));
					
					//TERRAIN MODEL
					tab=varStatic.item(cptStatic++).getChildNodes();
					TerrainModel.tabAssoTerrainJoueur=new int[tab.getLength()];
					for(int i=0; i<tab.getLength(); i++){
						TerrainModel.tabAssoTerrainJoueur[i]=Integer.parseInt(tab.item(i).getTextContent());
					}
					TerrainModel.setNbTerrain(Integer.parseInt(varStatic.item(cptStatic++).getTextContent()));
					
					//-----fin de variables statiques
					
					//CASES (Dans plateau)
					CaseModel[] cases=new CaseModel[ConstantesModel.NB_CASES];
					for(int i=0; i<ConstantesModel.NB_CASES; i++){
						NamedNodeMap attributs= plateau.item(cptPlateau).getAttributes();
						int idCase=Integer.parseInt(attributs.item(0).getTextContent());
						String nom=attributs.item(1).getTextContent();
						int position=Integer.parseInt(attributs.item(2).getTextContent());
						String typeCase=attributs.item(3).getTextContent();
						
						NodeList caseP=plateau.item(cptPlateau++).getChildNodes();
						int cptCase=0;
						if(typeCase.equals("TerrainModel")){
							int idTerrain = Integer.parseInt(caseP.item(cptCase++).getTextContent());
							System.out.println("idTerrain "+idTerrain);
							int nbMaisons = Integer.parseInt(caseP.item(cptCase++).getTextContent());
							int prixAchat = Integer.parseInt(caseP.item(cptCase++).getTextContent());
							
							NodeList loyersNode= caseP.item(cptCase++).getChildNodes();
							int[] loyers=new int[loyersNode.getLength()];
							for(int j=0; j<loyersNode.getLength(); j++){
								loyers[j]=Integer.parseInt(loyersNode.item(j).getTextContent());
							}
							int prixMaison = Integer.parseInt(caseP.item(cptCase++).getTextContent());
							CouleurTerrain couleur= CouleurTerrain.getColorFromString(caseP.item(cptCase++).getTextContent());
							cases[i]=new TerrainModel(model, idCase, position, couleur, nom, prixAchat, loyers, prixMaison, nbMaisons, idTerrain);

						}
						else if(typeCase.equals("DepartImpotTaxeModel")){
							int value=Integer.parseInt(caseP.item(cptCase++).getTextContent());
							cases[i]= new DepartImpotTaxeModel(model, idCase, position, value, nom);
						}
						else if(typeCase.equals("GareModel")){
							int idGare=Integer.parseInt(caseP.item(cptCase++).getTextContent());
							int prixAchat=Integer.parseInt(caseP.item(cptCase++).getTextContent());
							cases[i]= new GareModel(model, idCase, position, nom, prixAchat, idGare);							
						}
						else if(typeCase.equals("PrisonModel")){
							cases[i]=new PrisonModel(model, idCase, position);
						
						}
						else if(typeCase.equals("CommunauteChanceModel")){
							TypePioche type=TypePioche.getTypeFromString(caseP.item(cptCase++).getTextContent());
							cases[i]= new CommunauteChanceModel(model, idCase, position, nom, type);
							
						}
						else if(typeCase.equals("GendarmeModel")){
							cases[i]=new GendarmeModel(model, idCase, position);
							
						}
						else if(typeCase.equals("ServiceModel")){
							int idService=Integer.parseInt(caseP.item(cptCase++).getTextContent());
							int prixAchat=Integer.parseInt(caseP.item(cptCase++).getTextContent());
							cases[i]= new ServiceModel(model, idCase, position, nom, prixAchat, idService);
						}
						else if(typeCase.equals("ParkingModel")){
							cases[i]=new ParkingModel(model, idCase, position);	
						}
						else{
							System.out.println(typeCase);
							System.out.println("PROBLEME!!!!");
						}
						
					}
					
					
					
					
					CarteModel piocheChance[]= new CarteModel[ConstantesModel.NB_CARTES_CHANCE];
					NamedNodeMap argP1=list.item(cpt).getAttributes();
					NodeList pioche1 = list.item(cpt++).getChildNodes();
					int carteActuelle= Integer.parseInt(argP1.item(0).getTextContent());
							
					PiocheModel p1= new PiocheModel(model, TypePioche.CHANCE, carteActuelle);
					
					for(int i=0; i<pioche1.getLength(); i++){
						NodeList carteNode=pioche1.item(i).getChildNodes();
						int cptCarte=0;
						int effetMonnaie=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int effetPosition=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int argParHotel=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int argParMaison=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int changerPosition=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						boolean allerPrison=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						boolean sortirPrison=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						boolean repiocher=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						int anniversaire=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						String msg=carteNode.item(cptCarte++).getTextContent();
						
						CarteModel carte=new CarteModel(p1, effetMonnaie, effetPosition, changerPosition, allerPrison, 
								sortirPrison, argParHotel, argParMaison, anniversaire, 
								repiocher, msg);
						piocheChance[i]=carte;
	
					}
					p1.setCartes(piocheChance);
					
					
					CarteModel piocheCommunaute[]= new CarteModel[ConstantesModel.NB_CARTES_COMMUNAUTE];
					NamedNodeMap argP2=list.item(cpt).getAttributes();
					NodeList pioche2 = list.item(cpt++).getChildNodes();
					carteActuelle= Integer.parseInt(argP2.item(0).getTextContent());
							
					PiocheModel p2= new PiocheModel(model, TypePioche.COMMUNAUTE, carteActuelle);
					
					for(int i=0; i<pioche2.getLength(); i++){
						NodeList carteNode=pioche2.item(i).getChildNodes();
						int cptCarte=0;
						int effetMonnaie=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int effetPosition=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int argParHotel=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int argParMaison=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						int changerPosition=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						boolean allerPrison=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						boolean sortirPrison=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						boolean repiocher=Boolean.valueOf(carteNode.item(cptCarte++).getTextContent());
						int anniversaire=Integer.parseInt(carteNode.item(cptCarte++).getTextContent());
						String msg=carteNode.item(cptCarte++).getTextContent();
						
						CarteModel carte=new CarteModel(p2, effetMonnaie, effetPosition, changerPosition, allerPrison, 
								sortirPrison, argParHotel, argParMaison, anniversaire, 
								repiocher, msg);
						piocheCommunaute[i]=carte;
	
					}
					p2.setCartes(piocheCommunaute);
					
					
					
					model.setJoueurs(joueurs);
					model.setNbJoueur(ConstantesParam.NB_JOUEURS);
					model.cases=cases;
					model.piocheChance=p1;
					model.piocheCommunaute=p2;
					
					this.p=model;
					System.out.println("okkkkk");
					this.notifyCases(this.p.cases);
					this.notifyInitTourDes();
					
				} catch (SAXParseException e){}
				
				
			} catch (ParserConfigurationException e) {
			e.printStackTrace();
			} catch (SAXException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}
			
		}
		
		
	}
	
	public void notifierTerrain(){
		
		
		for(int i=0; i<ConstantesModel.NB_CASES; i++){
		
			CaseModel c = this.p.cases[i];
			if(c instanceof TerrainModel){
				int idTerrain=((TerrainModel) c).getIdTerrain();
				if(TerrainModel.tabAssoTerrainJoueur[idTerrain]!=-1){
					this.notifyAcquisitionJoueur(TerrainModel.tabAssoTerrainJoueur[idTerrain], i);
				}
				for(int j=0; j<((TerrainModel) c).getNbMaisons();j++){
					this.notifyAjoutMaison(c.getPosition());
				}
			}
			if(c instanceof GareModel){
				int idGare=((GareModel) c).getIdGare();
				if(GareModel.tabAssoGareJoueur[idGare]!=-1){
					this.notifyAcquisitionJoueur(TerrainModel.tabAssoTerrainJoueur[idGare], i);
				}
			}
			if(c instanceof ServiceModel){
				int idService=((ServiceModel) c).getIdService();
				if(ServiceModel.tabAssoServiceJoueur[idService]!=-1){
					this.notifyAcquisitionJoueur(ServiceModel.tabAssoServiceJoueur[idService], i);
				}
			}
		}
		
		for (int i=0; i<ConstantesParam.NB_JOUEURS;i++){
			this.notifyArgentJoueur(i, this.p.joueurs[i].argent);
			this.notifyPosJoueur(i, this.p.joueurs[i].position );
			
		}
		
	}
	
	
	public void assoTerrain(){
		//associe terrain aléatoire aux joueurs
		for(int j=0; j<ConstantesParam.NB_JOUEURS; j++){
			for(int k=0; k<ConstantesParam.NB_PROPRIETES; k++){
				Random r = new Random();
				boolean res=false;
				while(!res){
					int nb=r.nextInt(p.cases.length);
					CaseModel c = p.cases[nb];
					
					if(c instanceof TerrainModel){
						int idTerrain=((TerrainModel) c).getIdTerrain();
						if(TerrainModel.tabAssoTerrainJoueur[idTerrain]==-1){
							res=true;
							TerrainModel.tabAssoTerrainJoueur[idTerrain]=j;
							this.notifyAcquisitionJoueur(j, nb);
						}
					}
					if(c instanceof GareModel){
						int idGare=((GareModel) c).getIdGare();
						if(GareModel.tabAssoGareJoueur[idGare]==-1){
							res=true;
							GareModel.tabAssoGareJoueur[idGare]=j;
							this.notifyAcquisitionJoueur(j, nb);
						}
					}
					if(c instanceof ServiceModel){
						int idService=((ServiceModel) c).getIdService();
						if(ServiceModel.tabAssoServiceJoueur[idService]==-1){
							res=true;
							ServiceModel.tabAssoServiceJoueur[idService]=j;
							this.notifyAcquisitionJoueur(j, nb);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void lancerDes(int idJoueur)
	{
		JoueurModel j=this.getP().joueurs[idJoueur];
		//gerer le cas en prison
		Random r= new Random();
		
		int des;
		if(ConstantesParam.TROIS_DES_ENABLED){
			int de1=1+r.nextInt(5);
			int de2=1+r.nextInt(5);
			int de3=1+r.nextInt(5);
			if(ConstantesParam.SOMME_PRISON_ENABLED&&(de1*de2*de3>=50)){
				this.p.joueurs[idJoueur].allerEnPrison();
				return;
			}
			des=de1+de2+de3;
			
		}
		else des=2+r.nextInt(10);
		
		j.lastSumDes=des;
		j.setPosition((j.position+des)%ConstantesModel.NB_CASES);
		
		//this.notifyPosJoueur(j.idJoueur, j.position);
		
		this.p.cases[j.position].action(j);
		
	}
	
	@Override
	public void tourSuivant(){
		int tourPred=getTour();
		
		System.out.println("TOUR SUIVANT ");
		setTour((getTour()+1)%ConstantesParam.NB_JOUEURS);
		
		while(p.joueurs[getTour()].gameOver){
			setTour((getTour() + 1)%ConstantesParam.NB_JOUEURS);
		}

		if(tourPred>getTour()&&ConstantesParam.TOUR_ENABLED){
			this.tourPartie++;
			this.notifyTourPartie(tourPartie);
			if(tourPartie-1==ConstantesParam.NB_TOUR){
				int max=-1;
				int maxValue=-1;
				
				for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
					int valeurJoueur= p.joueurs[i].valeurPatrimoine+p.joueurs[i].argent;
					if(valeurJoueur>maxValue){
						max=i;
						maxValue=valeurJoueur;
					}
				}
				
				this.notifyMessageGameOver(max);
				this.notifyTour(getTour());
				this.notifyInitTourFenetre();
				return;
			}
		}
		
		this.notifyTour(getTour());
		this.notifyInitTourFenetre();
		/*
		for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
			if((!this.p.joueurs[i].gameOver)&&this.p.joueurs[i].argent<0){
				this.comblerDette(i);
			}
		}*/
		
		if(p.joueurs[getTour()].enPrison>0){
			System.out.println("ici prison "+p.joueurs[getTour()].enPrison);
			p.joueurs[getTour()].enPrison--;
			if(p.joueurs[getTour()].enPrison>0){
				this.notifyEnPrison(getTour(), p.joueurs[getTour()].enPrison);
			}
			else if(p.joueurs[getTour()].enPrison==0){
				this.notifyLiberePrison(getTour());
			}
		}
		else{
			this.notifyInitTourDes();
		}
		
	}
	@Override
	public void lancerEnchere(int position)
	{
		this.notifyEnchere(position);
	}
	
	
	@Override
	public void lancerNegociation()
	{
		
	}
	@Override
	public void comblerDette(int idJoueur)
	{
		this.notifyDette(idJoueur);
	}

	public PlateauModel getP() {
		return p;
	}

	public void setP(PlateauModel p) {
		this.p = p;
	}

	/**
	 * Calcule si le joueur peut acheter la case
	 * A implémenter pour Terrain,Gare,Service
	 * @param idJoueur id du joueur
	 * @param positionAchat position de la case
	 */
	@Override
	public boolean hasEnoughMoney(int idJoueur, int positionAchat) {
		CaseModel c=p.getCases()[positionAchat];
		if(c instanceof TerrainModel)
			return (p.getJoueurs()[idJoueur].argent>=((TerrainModel) c).getPrixAchat());
		if(c instanceof GareModel)
			return (p.getJoueurs()[idJoueur].argent>=((GareModel) c).getPrixAchat());
		if(c instanceof ServiceModel)
			return (p.getJoueurs()[idJoueur].argent>=((ServiceModel) c).getPrixAchat());
		
		//GARE, SERVICE...
		
		return false;
		
	}

	/**
	 * Retourne si le joueur est endetté ou non (pour lancer l'hypothèque)
	 * @param idJoueur id du joueur
	 */
	@Override
	public boolean isInDebt(int idJoueur){
		return p.getJoueurs()[idJoueur].argent<0;
	}
	
	/**
	 * Acheter une case
	 * Vérification déjà effectuée avant d'appeler (hasEnoughMoney)
	 */
	@Override
	public void achatCase(int idJoueur, int positionAchat) {
		CaseModel c=p.getCases()[positionAchat];
		JoueurModel j =p.getJoueurs()[idJoueur];
		j.nbProprietes++;
			
		if(c instanceof TerrainModel){
			((TerrainModel) c).associer(j);
			//j.setArgent(j.argent-((TerrainModel) c).getPrixAchat());
			//TerrainModel.tabAssoTerrainJoueur[((TerrainModel)c).getIdTerrain()]=idJoueur;
		}
		else if(c instanceof GareModel){
			((GareModel) c).associer(j);
			//j.setArgent(j.argent-((GareModel) c).getPrixAchat());
			//GareModel.tabAssoGareJoueur[((GareModel)c).getIdGare()]=idJoueur;
		}
		else if(c instanceof ServiceModel){
			((ServiceModel) c).associer(j);
			//j.setArgent(j.argent-((ServiceModel) c).getPrixAchat());
			//ServiceModel.tabAssoServiceJoueur[((ServiceModel)c).getIdService()]=idJoueur;
		}
		
		this.notifyAcquisitionJoueur(idJoueur, positionAchat);
		this.notifyArgentJoueur(idJoueur,j.getArgent());
		
		//GARE, SERVICE...
	}
	/**
	 * Acheter une maison
	 * Vérification déjà effectuée avant d'appeler (hasEnoughMoney)
	 */
	@Override
	public void achatMaison(int idJoueur, int positionAchat) {
		((TerrainModel) this.p.cases[positionAchat]).ajouterMaison();
	}
	
	/**
	 * Un joueur paye un autre 
	 * appelé depuis le controler en cliquant sur un choixPaiement
	 */
	@Override
	public void paiementJoueur(int idJoueur1, int idJoueur2, int position){
		
		CaseModel c=p.getCases()[position];
		JoueurModel j1 =p.getJoueurs()[idJoueur1];
		JoueurModel j2 =p.getJoueurs()[idJoueur2];
		
		if(c instanceof TerrainModel){
			int montant=((TerrainModel) c).getLoyers()[((TerrainModel)c).getNbMaisons()];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		else if(c instanceof GareModel){
			int montant=GareModel.getLoyers()[((GareModel)c).getNbGareMemeJoueur()-1];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		else if(c instanceof ServiceModel){
			int montant=ServiceModel.getLoyers()[((ServiceModel)c).getNbServiceMemeJoueur()];
			j2.setArgent(j2.getArgent()+montant);
			j1.setArgent(j1.getArgent()-montant);
		}
		/*
		if(this.p.joueurs[idJoueur1].argent<0){
			this.comblerDette(idJoueur1);
		}*/
		
		
	}
	
	public void notifyJoueurs(){
		for(int i=0; i<this.p.nbJoueur; i++){
			this.notifyPosJoueur(i, p.joueurs[i].position);
		}
	}

	@Override
	public void venteMaison(int idJoueur, int positionAchat) {
		((TerrainModel) this.p.cases[positionAchat]).retirerMaison();
	}

	@Override
	public void echangePropriete(int idJoueur1, int idJoueur2, int positionAchat, int somme) {
		if(p.cases[positionAchat] instanceof TerrainModel){
			((TerrainModel) this.p.cases[positionAchat]).associerEchange(this.p.joueurs[idJoueur2]);
		}
		if(p.cases[positionAchat] instanceof ServiceModel){
			((ServiceModel) this.p.cases[positionAchat]).associerEchange(this.p.joueurs[idJoueur2]);
		}
		if(p.cases[positionAchat] instanceof GareModel){
			((GareModel) this.p.cases[positionAchat]).associerEchange(this.p.joueurs[idJoueur2]);
		}
		
		this.p.joueurs[idJoueur1].setArgent(this.p.joueurs[idJoueur1].getArgent()+somme);
		this.p.joueurs[idJoueur2].setArgent(this.p.joueurs[idJoueur2].getArgent()-somme);
		this.notifyEchangeJoueur(idJoueur1, idJoueur2, positionAchat);

		
	}

	@Override
	public void effetPioche(int idJoueur, int idCarte, TypePioche type) {
		
		if(type==TypePioche.CHANCE){
			this.p.piocheChance.getCartes()[idCarte].execActionCarte(this.p.joueurs[idJoueur]);
		}
		if(type==TypePioche.COMMUNAUTE){
			this.p.piocheCommunaute.getCartes()[idCarte].execActionCarte(this.p.joueurs[idJoueur]);
		}
		
	}

	@Override
	public void hypothequer(int idJoueur1, int positionAchat) {

		if(this.p.cases[positionAchat] instanceof TerrainModel){
			((TerrainModel) this.p.cases[positionAchat]).hypothequer(this.p.joueurs[idJoueur1]);
		}
		if(this.p.cases[positionAchat] instanceof GareModel){
			((GareModel) this.p.cases[positionAchat]).hypothequer(this.p.joueurs[idJoueur1]);
		}
		if(this.p.cases[positionAchat] instanceof ServiceModel){
			((ServiceModel) this.p.cases[positionAchat]).hypothequer(this.p.joueurs[idJoueur1]);
		}
		
		this.notifyDesacquisitionJoueur(idJoueur1,positionAchat);
		
		
	}

	@Override
	public void gameOver(int idJoueur) {
		
		System.out.println("game over "+idJoueur);
		
		this.p.joueurs[idJoueur].gameOver=true;
		
		if(this.getTour()==idJoueur){
			this.tourSuivant();
		}
		
		int cptJoueurs=0;
		for(int i=0; i<p.joueurs.length; i++){
			if(!p.joueurs[i].gameOver)
				cptJoueurs++;
		}
		if(cptJoueurs==1){
			this.notifyWinner(idJoueur);
		}
		else{
			this.notifyGameOver(idJoueur);
		}
	}

	@Override
	public void achatCaseEnchere(int idJoueur, int positionAchat, int prix) {
		
		CaseModel c=p.getCases()[positionAchat];
		JoueurModel j =p.getJoueurs()[idJoueur];
		j.nbProprietes++;
			
		if(c instanceof TerrainModel){
			j.setArgent(j.argent-prix);
			TerrainModel.tabAssoTerrainJoueur[((TerrainModel)c).getIdTerrain()]=idJoueur;
			j.setValeurPatrimoine(j.getValeurPatrimoine()+(((TerrainModel) c).getPrixAchat()));
		}
		else if(c instanceof GareModel){
			j.setArgent(j.argent-prix);
			GareModel.tabAssoGareJoueur[((GareModel)c).getIdGare()]=idJoueur;
			j.setValeurPatrimoine(j.getValeurPatrimoine()+(((GareModel) c).getPrixAchat()));
		}
		else if(c instanceof ServiceModel){
			j.setArgent(j.argent-prix);
			ServiceModel.tabAssoServiceJoueur[((ServiceModel)c).getIdService()]=idJoueur;
			j.setValeurPatrimoine(j.getValeurPatrimoine()+(((ServiceModel) c).getPrixAchat()));
		}
		
		this.notifyAcquisitionJoueur(idJoueur, positionAchat);
		this.notifyArgentJoueur(idJoueur,j.getArgent());
		
	}
	
	
	public void addToTrunk(Document xml, Element trunk, String nom, String value){
		Element branche = xml.createElement(nom);
		branche.setTextContent(value);
		trunk.appendChild(branche);
	}
	public void addTabToTrunk(Document xml, Element trunk, String nom, boolean[] value){
		Element branche = xml.createElement(nom);
		for(int i=0; i<value.length; i++){
			Element eltBranch=xml.createElement("elt");
			eltBranch.setAttribute("i", i+"");
			eltBranch.setTextContent(value[i]+"");
			branche.appendChild(eltBranch);
		}
		trunk.appendChild(branche);
	}
	public void addTabToTrunk(Document xml, Element trunk, String nom, int[] value){
		Element branche = xml.createElement(nom);
		for(int i=0; i<value.length; i++){
			Element eltBranch=xml.createElement("elt");
			eltBranch.setAttribute("i", i+"");
			eltBranch.setTextContent(value[i]+"");
			branche.appendChild(eltBranch);
		}
		trunk.appendChild(branche);
	}
	public void addTabToTrunk(Document xml, Element trunk, String nom, String[] value){
		Element branche = xml.createElement(nom);
		for(int i=0; i<value.length; i++){
			Element eltBranch=xml.createElement("elt");
			eltBranch.setAttribute("i", i+"");
			eltBranch.setTextContent(value[i]+"");
			branche.appendChild(eltBranch);
		}
		trunk.appendChild(branche);
	}
	
	/**
	 * Sauvegarde d'une partie (voir rapport pour la structure)
	 */
	public void genererXML(){
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xml = builder.newDocument();
			Element root = xml.createElement("plateauModel");
			
			
			addToTrunk(xml, root, "tour", this.getTour()+"");
			addToTrunk(xml, root, "tourPartie", this.tourPartie+"");
			
			addToTrunk(xml, root, "NB_JOUEURS", ConstantesParam.NB_JOUEURS+"");
			addToTrunk(xml, root, "SOMME_DEPART", ConstantesParam.SOMME_DEPART+"");
			addTabToTrunk(xml, root, "IS_IA", ConstantesParam.IS_IA);
			addTabToTrunk(xml, root, "ID_ICONES", ConstantesParam.ID_ICONES);
			addTabToTrunk(xml, root, "NAMES", ConstantesParam.NAMES);
			addToTrunk(xml, root, "NB_MAISONS", ConstantesParam.NB_MAISONS+"");
			addToTrunk(xml, root, "NB_PROPRIETES", ConstantesParam.NB_PROPRIETES+"");
			addToTrunk(xml, root, "TOUR_ENABLED", ConstantesParam.TOUR_ENABLED+"");
			addToTrunk(xml, root, "NB_TOUR", ConstantesParam.NB_TOUR+"");
			
			addToTrunk(xml, root, "POSITION_ALEA_ENABLED", ConstantesParam.POSITION_ALEA_ENABLED+"");
			addToTrunk(xml, root, "TROIS_DES_ENABLED", ConstantesParam.TROIS_DES_ENABLED+"");
			addToTrunk(xml, root, "SOMME_PRISON_ENABLED", ConstantesParam.SOMME_PRISON_ENABLED+"");
			addToTrunk(xml, root, "CHOIX_DES_ENABLED", ConstantesParam.CHOIX_DES_ENABLED+"");
			addToTrunk(xml, root, "SUICIDE_ENABLED", ConstantesParam.SUICIDE_ENABLED+"");
			addToTrunk(xml, root, "ENCHERE_ENABLED", ConstantesParam.ENCHERE_ENABLED+"");
			addToTrunk(xml, root, "TIMER_VALEUR_ENABLED", ConstantesParam.TIMER_VALEUR_ENABLED+"");
			addToTrunk(xml, root, "CASE_ALEA_ENABLED", ConstantesParam.CASE_ALEA_ENABLED+"");
			addToTrunk(xml, root, "CASE_MASQUE_ENABLED", ConstantesParam.CASE_MASQUE_ENABLED+"");
			addToTrunk(xml, root, "TAUX_ACHAT", ConstantesParam.TAUX_ACHAT+"");
			addToTrunk(xml, root, "TAUX_INTERET", ConstantesParam.TAUX_INTERET+"");
			addToTrunk(xml, root, "EGALISATION_ENABLED", ConstantesParam.EGALISATION_ENABLED+"");
			addToTrunk(xml, root, "ENCHERE_TIME", ConstantesParam.ENCHERE_TIME+"");
			addToTrunk(xml, root, "NB_TOUR_PRISON", ConstantesParam.NB_TOUR_PRISON+"");
			
			
			
			
			for(int i=0; i<ConstantesParam.NB_JOUEURS; i++){
				Element trunk = xml.createElement("joueur");
				trunk.setAttribute("idJoueur", ""+i);
				
				addToTrunk(xml, trunk, "position", this.p.joueurs[i].position+"");
				addToTrunk(xml, trunk, "nom", this.p.joueurs[i].nom);
				addToTrunk(xml, trunk, "argent", this.p.joueurs[i].argent+"");
				addToTrunk(xml, trunk, "valeurPatrimoine", this.p.joueurs[i].valeurPatrimoine+"");
				addToTrunk(xml, trunk, "cartePrison", this.p.joueurs[i].cartePrison+"");
				addToTrunk(xml, trunk, "enPrison", this.p.joueurs[i].enPrison+"");
				addToTrunk(xml, trunk, "nbMaison", this.p.joueurs[i].nbMaison+"");
				addToTrunk(xml, trunk, "nbHotel", this.p.joueurs[i].nbHotel+"");
				addToTrunk(xml, trunk, "nbProprietes", this.p.joueurs[i].nbProprietes+"");
				addToTrunk(xml, trunk, "gameOver", this.p.joueurs[i].gameOver+"");
				
				
				root.appendChild(trunk);
				
			}
			
			Element trunk = xml.createElement("plateau");
			//Element trunkStatic=xml.createElement("varStatic");
			Element trunkVar=xml.createElement("varStatic");
			
			addTabToTrunk(xml, trunkVar, "tabAssoGareJoueur", GareModel.tabAssoGareJoueur);
			addToTrunk(xml, trunkVar, "nb_gare", GareModel.nb_gare+"");
			addTabToTrunk(xml, trunkVar, "posGares", GareModel.posGares);
			
			addTabToTrunk(xml, trunkVar, "tabAssoServiceJoueur", ServiceModel.tabAssoServiceJoueur);
			addToTrunk(xml, trunkVar, "nb_service", ServiceModel.getNb_service()+"");
			
			addTabToTrunk(xml, trunkVar, "tabAssoTerrainJoueur", TerrainModel.tabAssoTerrainJoueur);
			addToTrunk(xml, trunkVar, "nbTerrain", TerrainModel.getNbTerrain()+"");
			trunk.appendChild(trunkVar);

			
			for(int i=0; i<this.p.cases.length; i++){
				CaseModel c=p.cases[i];
				Element trunk2 = xml.createElement("case");
				trunk2.setAttribute("idCase", c.getIdCase()+"");
				trunk2.setAttribute("position", c.getPosition()+"");
				trunk2.setAttribute("nom", c.getNom()+"");
				
				if(c instanceof CommunauteChanceModel){
					trunk2.setAttribute("typeCase", "CommunauteChanceModel");
					addToTrunk(xml, trunk2, "type", ((CommunauteChanceModel) c).getType()+"");
				}
				if(c instanceof DepartImpotTaxeModel){
					trunk2.setAttribute("typeCase", "DepartImpotTaxeModel");
					addToTrunk(xml, trunk2, "value", ((DepartImpotTaxeModel) c).getValue()+"");
				}
				if(c instanceof GareModel){
					
					trunk2.setAttribute("typeCase", "GareModel");
					addToTrunk(xml, trunk2, "idGare", ((GareModel) c).getIdGare()+"");
					addToTrunk(xml, trunk2, "prixAchat", ((GareModel) c).getPrixAchat()+"");
					
				}
				if(c instanceof GendarmeModel){
					trunk2.setAttribute("typeCase", "GendarmeModel");
				}
				if(c instanceof ParkingModel){
					trunk2.setAttribute("typeCase", "ParkingModel");
				}
				if(c instanceof PrisonModel){
					trunk2.setAttribute("typeCase", "PrisonModel");
				}
				if(c instanceof ServiceModel){
					
					trunk2.setAttribute("typeCase", "ServiceModel");
					addToTrunk(xml, trunk2, "idService", ((ServiceModel) c).getIdService()+"");
					addToTrunk(xml, trunk2, "prixAchat", ((ServiceModel) c).getPrixAchat()+"");
					
					
				}
				if(c instanceof TerrainModel){
					
					trunk2.setAttribute("typeCase", "TerrainModel");
					addToTrunk(xml, trunk2, "idTerrain", ((TerrainModel) c).getIdTerrain()+"");
					addToTrunk(xml, trunk2, "nbMaisons", ((TerrainModel) c).getNbMaisons()+"");
					addToTrunk(xml, trunk2, "prixAchat", ((TerrainModel) c).getPrixAchat()+"");
					addTabToTrunk(xml, trunk2, "loyers", ((TerrainModel) c).getLoyers());
					addToTrunk(xml, trunk2, "prixMaison", ((TerrainModel) c).getPrixMaison()+"");
					addToTrunk(xml, trunk2, "couleurTerrain", ((TerrainModel) c).getCouleurTerrain()+"");
					
				}
				trunk.appendChild(trunk2);
				
			}
			root.appendChild(trunk);
			
			Element trunkPioche = xml.createElement("pioche");
			trunkPioche.setAttribute("typePioche", "CHANCE");
			trunkPioche.setAttribute("carteActuelle", this.p.piocheChance.getCarteActuelle()+"");
			
			for(int i=0; i<ConstantesModel.NB_CARTES_CHANCE; i++){
				CarteModel c = this.p.piocheChance.getCartes()[i];
				Element carte = xml.createElement("carte");
				addToTrunk(xml, carte, "effetMonnaie", c.getEffetMonnaie()+"");
				addToTrunk(xml, carte, "effetPosition", c.getEffetPosition()+"");
				addToTrunk(xml, carte, "argParHotel", c.getArgParHotel()+"");
				addToTrunk(xml, carte, "argParMaison", c.getArgParMaison()+"");
				addToTrunk(xml, carte, "changerPosition", c.getChangerPosition()+"");
				addToTrunk(xml, carte, "allerPrison", c.isAllerPrison()+"");
				addToTrunk(xml, carte, "sortirPrison", c.isSortirPrison()+"");
				addToTrunk(xml, carte, "repiocher", c.isRepiocher()+"");
				addToTrunk(xml, carte, "anniversaire", c.getAnniversaire()+"");
				addToTrunk(xml, carte, "msg", c.getMsg()+"");
				trunkPioche.appendChild(carte);
			}
			
			Element trunkPioche2 = xml.createElement("pioche");
			trunkPioche2.setAttribute("typePioche", "COMMUNAUTE");
			trunkPioche2.setAttribute("carteActuelle", this.p.piocheCommunaute.getCarteActuelle()+"");
			for(int i=0; i<ConstantesModel.NB_CARTES_COMMUNAUTE; i++){
				CarteModel c = this.p.piocheCommunaute.getCartes()[i];
				Element carte = xml.createElement("carte");
				addToTrunk(xml, carte, "effetMonnaie", c.getEffetMonnaie()+"");
				addToTrunk(xml, carte, "effetPosition", c.getEffetPosition()+"");
				addToTrunk(xml, carte, "argParHotel", c.getArgParHotel()+"");
				addToTrunk(xml, carte, "argParMaison", c.getArgParMaison()+"");
				addToTrunk(xml, carte, "changerPosition", c.getChangerPosition()+"");
				addToTrunk(xml, carte, "allerPrison", c.isAllerPrison()+"");
				addToTrunk(xml, carte, "sortirPrison", c.isSortirPrison()+"");
				addToTrunk(xml, carte, "repiocher", c.isRepiocher()+"");
				addToTrunk(xml, carte, "anniversaire", c.getAnniversaire()+"");
				addToTrunk(xml, carte, "msg", c.getMsg()+"");
				trunkPioche2.appendChild(carte);
			}
			root.appendChild(trunkPioche);
			root.appendChild(trunkPioche2);
			
			
			/*
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
			}*/
			
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

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}





	




	



	

	
	
	

	
	
}
