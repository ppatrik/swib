package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;

public interface PohybDao {
    List<Pohyb> dajVsetky(Ucet ucet);
}
