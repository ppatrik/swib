package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Ucet;

public interface UverDao {
    List<Ucet> dajVsetky();
    void pridaj(Ucet ucet);
    void odstran(Ucet ucet);
    void uprav(Ucet ucet);
}
