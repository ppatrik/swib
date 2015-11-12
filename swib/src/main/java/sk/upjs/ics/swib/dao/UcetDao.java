package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Ucet;

public interface UcetDao {
    List<Ucet> dajVsetky(Klient klient);
    void pridaj(Ucet ucet);
    void odstran(Ucet ucet);
    void uprav(Ucet ucet);
}
