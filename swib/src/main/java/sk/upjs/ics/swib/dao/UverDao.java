package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Uver;

public interface UverDao {
    List<Uver> dajVsetky();
    void pridaj(Uver uver);
    void odstran(Uver uver);
    void uprav(Uver uver);
}
