package sk.upjs.ics.swib.dao;

import java.util.List;
import sk.upjs.ics.swib.entity.Multiplikator;

public interface MultiplikatorDao {
    List<Multiplikator> dajVsetky();
    int dajIndex(String nazov);
}
