CREATE TABLE Bonusy (ID int(10) NOT NULL AUTO_INCREMENT, UveryId int(10) NOT NULL, Nazov varchar(20) NOT NULL, Vacsi_ako decimal(19, 4) NOT NULL, Vyska_bonusu decimal(19, 4) NOT NULL, Poradie_bonusu int(10) NOT NULL, MultiplikatorID int(10) NOT NULL, CoPorovnavamID int(10) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE CoPorovnavam (id int(10) NOT NULL AUTO_INCREMENT, Nazov varchar(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Klient (ID int(10) NOT NULL AUTO_INCREMENT, Meno varchar(35) NOT NULL , Priezvisko varchar(35) NOT NULL , C_Karty int(10) NOT NULL, Dat_nar date NOT NULL, Rodne_cislo varchar(10) NOT NULL , C_preukazu varchar(10) NOT NULL , PRIMARY KEY (ID), UNIQUE KEY (meno,priezvisko,Rodne_cislo,C_preukazu))
CREATE TABLE Multiplikator (id int(10) NOT NULL AUTO_INCREMENT, Nazov varchar(10) NOT NULL, PRIMARY KEY (id));
CREATE TABLE Pohyby (ID int(10) NOT NULL AUTO_INCREMENT, UcetID int(10) NOT NULL, KamIBAN varchar(30) NOT NULL, Suma decimal(19, 4) NOT NULL, Datum date NOT NULL, Spracovane tinyint(1) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE Ucet (ID int(10) NOT NULL AUTO_INCREMENT, KlientID int(10) NOT NULL, Nazov int(20) NOT NULL, Zostatok decimal(19, 4) NOT NULL, C_uctu varchar(24) NOT NULL UNIQUE, Spor tinyint(1) NOT NULL, PRIMARY KEY (ID), INDEX (KlientID));
CREATE TABLE Ucet_Klient (UcetID int(10) NOT NULL, KlientID int(10) NOT NULL, PRIMARY KEY (UcetID, KlientID));
CREATE TABLE Uvery (ID int(10) NOT NULL AUTO_INCREMENT, Nazov varchar(20) NOT NULL, PRIMARY KEY (ID));
ALTER TABLE Ucet_Klient ADD INDEX FKUcet_Klien268104 (KlientID), ADD CONSTRAINT FKUcet_Klien268104 FOREIGN KEY (KlientID) REFERENCES Klient (ID);
ALTER TABLE Ucet_Klient ADD INDEX FKUcet_Klien572956 (UcetID), ADD CONSTRAINT FKUcet_Klien572956 FOREIGN KEY (UcetID) REFERENCES Ucet (ID);
ALTER TABLE Pohyby ADD INDEX FKPohyby756014 (UcetID), ADD CONSTRAINT FKPohyby756014 FOREIGN KEY (UcetID) REFERENCES Ucet (ID);
ALTER TABLE Bonusy ADD INDEX FKBonusy990495 (UveryId), ADD CONSTRAINT FKBonusy990495 FOREIGN KEY (UveryId) REFERENCES Uvery (ID);
ALTER TABLE Bonusy ADD INDEX FKBonusy187348 (MultiplikatorID), ADD CONSTRAINT FKBonusy187348 FOREIGN KEY (MultiplikatorID) REFERENCES Multiplikator (id);
ALTER TABLE Bonusy ADD INDEX FKBonusy233713 (CoPorovnavamID), ADD CONSTRAINT FKBonusy233713 FOREIGN KEY (CoPorovnavamID) REFERENCES CoPorovnavam (id);

