package sk.upjs.ics.swib.dao;

import java.math.BigDecimal;
import java.util.List;
import sk.upjs.ics.swib.entity.Klient;

public interface KlientDao {
    List<Klient> dajVsetkych();
    void pridaj(Klient klient);
    void odstran(Klient klient);
    void uprav(Klient klient);
    BigDecimal mozeNaMesiacMaximalneSplacat(Klient klient);
    BigDecimal priemernyMesacnyPrijem(Klient klient);
}
