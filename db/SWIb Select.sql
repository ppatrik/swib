SELECT ID, UveryId, Nazov, Vacsi_ako, Vyska_bonusu, Poradie_bonusu, MultiplikatorID, CoPorovnavamID FROM Bonusy;
SELECT id, Nazov FROM CoPorovnavam;
SELECT ID, Meno, Priezvisko, C_Karty, Dat_nar, Rodne_cislo, C_preukazu FROM Klient;
SELECT id, Nazov FROM Multiplikator;
SELECT ID, UcetID, KamIBAN, Suma, Datum, Spracovane FROM Pohyby;
SELECT ID, KlientID, Nazov, Zostatok, C_uctu, Spor FROM Ucet;
SELECT UcetID, KlientID FROM Ucet_Klient;
SELECT ID, Nazov FROM Uvery;

