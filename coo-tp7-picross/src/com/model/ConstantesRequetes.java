package com.model;

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
	+"indices int"
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
	+"indices int"
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

	
	public static String nomStar="star";
	public static int nbLigneStar=15;
	public static int nbColonneStar=15;
	
	public static int[] indicesLigneStar={77,66,66,55,55,11,1111,2112,33,44,33,33,212,232,171};
	public static int[] indicesColonnesStar={59,57,54,51,51,31,132,3,132,31,51,51,54,57,59};
	
	public static String nomToad="toad";
	public static int nbLigneToad=20;
	public static int nbColonneToad=20;
	
	public static int[] indicesLigneToad={6,44,55,66,66,66,55,33,181,1111,111111,211112,33,121,11,8,2222,1331,1331,1441};
	public static int[] indicesColonnesToad={0,0,8,522,612,6511,6123,615,4125,1111,1111,5125,615,6123,6511,612,522,8,0,0};

	public static String nomEasy="easy";
	public static int nbLigneEasy=4;
	public static int nbColonneEasy=4;
	
	public static int[] indicesLigneEasy={2,11,2,1};
	public static int[] indicesColonnesEasy={0,2,12,2};
	
	
			
}
