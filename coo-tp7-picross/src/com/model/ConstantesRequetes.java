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
	
	


			
}
