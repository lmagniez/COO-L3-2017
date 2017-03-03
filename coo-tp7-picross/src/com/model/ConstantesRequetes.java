package com.model;

/**
 * Ensemble des constantes utilisées pour executer les requetes
 * @author loick
 *
 */
public abstract class ConstantesRequetes {

	public static String createDataBase=
			"CREATE DATABASE IF NOT EXISTS Picross";
	
	public static  String createJeu=
	"CREATE TABLE IF NOT EXISTS Picross.Jeu"
	+"("
	+"idPuzzle int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
	+"nom varchar(255) NOT NULL,"
	+"nbLigne int,"
	+"nbColonne int,"
	+"reussite boolean"
	+");";

	public static String createLigne=
	"CREATE TABLE IF NOT EXISTS Picross.Ligne"
	+"("
	+"idLigne int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
	+"indices varchar(100)"
	+");";

	
	public static String createIndiceJeuLigne=
	"CREATE TABLE IF NOT EXISTS Picross.IndiceJeuLigne"
	+"("
	+"idPuzzle int,"
	+"idLigne int, "
	+"numLigne int,"

	+"FOREIGN KEY (idPuzzle) REFERENCES Picross.Jeu(idPuzzle),"
	+"FOREIGN KEY (idLigne) REFERENCES Picross.Ligne(idLigne)"
	+");";

	public static String createColonne=
	"CREATE TABLE IF NOT EXISTS Picross.Colonne"
	+"("
	+"idColonne int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
	+"indices varchar(100)"
	+");";

	public static String createIndiceJeuColonne=
	"CREATE TABLE IF NOT EXISTS Picross.IndiceJeuColonne"
	+"("
	+"idPuzzle int,"
	+"idColonne int,"
	+"numColonne int,"

	+"FOREIGN KEY (idPuzzle) REFERENCES Picross.Jeu(idPuzzle),"
	+"FOREIGN KEY (idColonne) REFERENCES Picross.Colonne(idColonne)"
	+");";

	public static String nomToad="toad";
	public static int nbLigneToad=20;
	public static int nbColonneToad=20;
	public static String[] indicesLigneToad={"6","4,4","5,5","6,6","6,6","6,6","5,5","3,3","1,8,1","1,1,1,1","1,1,1,1,1,1","2,1,1,1,1,2","3,3","1,2,1","1,1","10","2,2,2,2","1,3,3,1","1,3,3,1","1,4,4,1"};
	public static String[] indicesColonnesToad={"0","0","8","5,2,2","6,1,2","6,5,1,1","6,1,2,3","6,1,5","4,1,2,5","1,1,1,1","1,1,1,1","4,1,2,5","6,1,5","6,1,2,3","6,5,1,1","6,1,2","5,2,2","8","0","0"};
	
	public static String nomStar="star";
	public static int nbLigneStar=15;
	public static int nbColonneStar=15;
	public static String[] indicesLigneStar={"7,7","6,6","6,6","5,5","5,5","1,1","1,1,1,1","2,1,1,2","3,3","4,4","3,3","3,3","2,1,2","2,3,2","1,7,1"};
	public static String[] indicesColonnesStar={"5,9","5,7","5,4","5,1","5,1","3,1","1,3,2","3","1,3,2","3,1","5,1","5,1","5,4","5,7","5,9"};
	
	
	
	public static String nomEasy="easy";
	public static int nbLigneEasy=4;
	public static int nbColonneEasy=4;
	public static String[] indicesLigneEasy={"2","1,1","2","1"};
	public static String[] indicesColonnesEasy={"0","2","1,2","2"};
	
	
	public static String nomNinja="ninja";
	public static int nbLigneNinja=20;
	public static int nbColonneNinja=20;
	public static String[] indicesLigneNinja={"3","5","5,1","1,6,1","2,8","2,7","2,6","2,7","2,8","2,9","2,5,5","5,6","13","13","2,6","7","7","7","7","7"};
	public static String[] indicesColonnesNinja={"2","2","2","2","2","2","2","4","3","5","5","5","4,3,2,4","6,4,2,5","10,9","20","19","17","16","14"};
	
	public static String nomGB="GameBoy";
	public static int nbLigneGB=15;
	public static int nbColonneGB=15;
	public static String[] indicesColonnesGB={"15","15","1,1","1,6,1,1","1,6,3,1","1,1,1,1,1","1,1,1,1","1,1,1,1","1,1,1,1","1,1,1,1,1","1,6,1,1","1,6,1,1","1,2","15","15"};
	public static String[] indicesLigneGB={"15","2,2","2,9,2","2,2,2,2","2,2,2,2","2,2,2,2","2,2,2,2","2,9,2","2,2","2,1,2","2,3,2","2,1,3,2","2,2","2,3","15"};
	
	public static String nomBill="bill-ball";
	public static int nbLigneBill=15;
	public static int nbColonneBill=15;
	public static String[] indicesColonnesBill={"5","9","2,7","3,6","2,7","1,2,4","6,3","1,4,3","1,5,4","1,2,4","1,2,5","13","11","1,2,4","1,11"};
	public static String[] indicesLigneBill={"0","8,2","3,1,2","3,9","1,1,9","2,3,2,1","3,4,2,1","6,1,2,1","5,2,1","5,3,1","5,7","14","13","8,2","0"};
	
	public static String nomGoomba="goomba";
	public static int nbLigneGoomba=15;
	public static int nbColonneGoomba=15;
	public static String[] indicesColonnesGoomba={"5,2","9,3","1,2,1,3","1,1,5","3,1,1,3","4,2,1,1,2","6,2,1,1","9,1,1","6,2,1,1","4,2,1,1,2","3,1,1,3","1,1,5","1,2,1,3","9,3","5,2"};
	public static String[] indicesLigneGoomba={"5","7","13","1,7,1","1,3,1","2,5,2","2,1,1,1,2","3,3,3","15","2,2","13","1,1","4,4","15","6,6"};
	
	public static String nomChat="chat";
	public static int nbLigneChat=10;
	public static int nbColonneChat=10;
	public static String[] indicesColonnesChat={"5","2,1","8","2,5","8","3","3","1,3","1,3","8"};
	public static String[] indicesLigneChat={"0","1,1","5,2","5,1,1","1,1,1,1","5,1","8","8","8","2,1"};
	
	public static String nomSnail="escargot";
	public static int nbLigneSnail=15;
	public static int nbColonneSnail=15;
	public static String[] indicesColonnesSnail={"2,4,3","5,3,3","1,1,1,4","5,3","2,2,3","4,3","2,2,1,2","2,5,2,1","1,2,2,4","1,1,3,1,1,2","1,1,1,1,1,2,1","1,2,2,1,4","2,3,3,1","2,5","6,2"};
	public static String[] indicesLigneSnail={"2,2","2,2,6","1,1,2,2","3,2,4,2","2,3,2,2,1","1,1,2,1,2,1,1","1,3,1,2,1","3,2,3,1","1,2,2","2,6","2,3","14","6,2,2,1","2,3,2,2","1,6"};
	
	public static String nomTrophy="escargot";
	public static int nbLigneTrophy=8;
	public static int nbColonneTrophy=8;
	public static String[] indicesColonnesTrophy={"2","1,1,1","5,2","1,5","8","5,2","1,1,1","2"};
	public static String[] indicesLigneTrophy={"4","3,4","1,1,2,1","6","4","2","4","6"};
	
	public static String nomPalmier="Palmier";
	public static int nbLignePalmier=15;
	public static int nbColonnePalmier=15;
	public static String[] indicesColonnesPalmier={"3","3","2,4","3,3","5,2","3,1","2,3,1","6,7","5,3,1,1,1","3,2,5,1","3,2,3","3,2,1","3","2","0"};
	public static String[] indicesLignePalmier={"2,3","5,5","5,6","2,5,2","1,2,4,2","1,2,1,2,1","3,3,2","1,3,1,1","1,4","1,1","3","1,1","4","1,1","6"};
	
	
	//{"5","9","","","","","","","","","","","","",""};
}
