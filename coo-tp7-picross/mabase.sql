
USE db;


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
indices int
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
indices int
);

CREATE TABLE IF NOT EXISTS IndiceJeuColonne
(
idPuzzle int,
idColonne int,
numColonne int,

FOREIGN KEY (idPuzzle) REFERENCES Jeu(idPuzzle),
FOREIGN KEY (idColonne) REFERENCES Colonne(idColonne)
);


insert into Jeu(nom,nbLigne,nbColonne,reussite) values("star",15,15,false);

insert into Ligne(indices) values(77);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),1
);
insert into Ligne(indices) values(66);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),2
);
insert into Ligne(indices) values(66);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),3
);
insert into Ligne(indices) values(55);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),4
);
insert into Ligne(indices) values(55);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),5
);
insert into Ligne(indices) values(11);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),6
);
insert into Ligne(indices) values(1111);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),7
);
insert into Ligne(indices) values(2112);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),8
);
insert into Ligne(indices) values(33);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),9
);
insert into Ligne(indices) values(44);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),10
);
insert into Ligne(indices) values(33);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),11
);
insert into Ligne(indices) values(33);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),12
);
insert into Ligne(indices) values(212);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),13
);
insert into Ligne(indices) values(232);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),14
);
insert into Ligne(indices) values(171);
insert into IndiceJeuLigne(idPuzzle,idLigne,numLigne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idLigne) FROM Ligne),15
);


insert into Colonne(indices) values(59);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(57);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(54);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(51);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(51);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(31);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(132);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(3);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(132);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(31);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(51);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(51);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(54);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(57);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);
insert into Colonne(indices) values(59);
insert into IndiceJeuColonne(idPuzzle,idColonne,numColonne) values
(
(SELECT idPuzzle FROM Jeu WHERE nom="star"),
(SELECT MAX(idColonne) FROM Colonne),1
);




