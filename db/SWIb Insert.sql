INSERT INTO Bonusy(ID, UveryId, Nazov, Vacsi_ako, Vyska_bonusu, MultiplikatorID, CoPorovnavamID) VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO CoPorovnavam(id, Nazov) VALUES (?, ?);
INSERT INTO Klient(ID, Meno, Priezvisko, C_Karty, Dat_nar, Rodne_cislo, C_preukazu) VALUES (?, ?, ?, ?, ?, ?, ?);
INSERT INTO Multiplikator(id, Nazov) VALUES (?, ?);
INSERT INTO Pohyby(ID, UcetID, KamIBAN, Suma, Datum, Spracovane) VALUES (?, ?, ?, ?, ?, ?);
INSERT INTO Ucet(ID, KlientID, Nazov, Zostatok, C_uctu, Spor) VALUES (?, ?, ?, ?, ?, ?);
INSERT INTO Ucet_Klient(UcetID, KlientID) VALUES (?, ?);
INSERT INTO Uvery(ID, Nazov) VALUES (?, ?);

