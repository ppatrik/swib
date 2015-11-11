package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Bonus;
import sk.upjs.ics.swib.entity.Uver;

public interface BonusDao {
    List<Bonus> dajVsetky(Uver uver);
    void pridaj(Bonus bonus);
    void odstran(Bonus bonus);
    void uprav(Bonus bonus);
}