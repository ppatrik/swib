package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Porovnavac;

public interface PorovnavacDao {
    List<Porovnavac> dajVsetky();
    int dajIndex(String nazov);
}
