UPDATE Bonusy SET UveryId = ?, Nazov = ?, Vacsi_ako = ?, Vyska_bonusu = ?, MultiplikatorID = ?, CoPorovnavamID = ? WHERE ID = ?;
UPDATE CoPorovnavam SET Nazov = ? WHERE id = ?;
UPDATE Klient SET Meno = ?, Priezvisko = ?, C_Karty = ?, Dat_nar = ?, Rodne_cislo = ?, C_preukazu = ? WHERE ID = ?;
UPDATE Multiplikator SET Nazov = ? WHERE id = ?;
UPDATE Pohyby SET UcetID = ?, KamIBAN = ?, Suma = ?, Datum = ?, Spracovane = ? WHERE ID = ?;
UPDATE Ucet SET KlientID = ?, Nazov = ?, Zostatok = ?, C_uctu = ?, Spor = ? WHERE ID = ?;
UPDATE Ucet_Klient SET  WHERE UcetID = ? AND KlientID = ?;
UPDATE Uvery SET Nazov = ? WHERE ID = ?;

