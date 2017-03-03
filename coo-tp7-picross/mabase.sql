
USE Picross;


DROP TABLE IF EXISTS IndiceJeuLigne;
DROP TABLE IF EXISTS IndiceJeuColonne;
DROP TABLE IF EXISTS Jeu;
DROP TABLE IF EXISTS Ligne;
DROP TABLE IF EXISTS Colonne;

CREATE TABLE IF NOT EXISTS Jeu 
(
idPuzzle int NOT NULL AUTO_INCREMENT PRIMARY KEY,
nom varchar(255) NOT NULL,
nbLigne int,
nbColonne int,
reussite boolean
);

CREATE TABLE IF NOT EXISTS Ligne
(
idLigne int NOT NULL AUTO_INCREMENT PRIMARY KEY,
indices VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS IndiceJeuLigne
(
idPuzzle int,
idLigne int, 
numLigne int,

FOREIGN KEY (idPuzzle) REFERENCES Jeu(idPuzzle),
FOREIGN KEY (idLigne) REFERENCES Ligne(idLigne)
);

CREATE TABLE IF NOT EXISTS Colonne
(
idColonne int NOT NULL AUTO_INCREMENT PRIMARY KEY,
indices VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS IndiceJeuColonne
(
idPuzzle int,
idColonne int,
numColonne int,

FOREIGN KEY (idPuzzle) REFERENCES Jeu(idPuzzle),
FOREIGN KEY (idColonne) REFERENCES Colonne(idColonne)
);

