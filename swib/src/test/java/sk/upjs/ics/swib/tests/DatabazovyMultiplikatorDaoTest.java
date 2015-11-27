package sk.upjs.ics.swib.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.entity.Multiplikator;
import sk.upjs.ics.swib.factory.DaoFactory;
import sk.upjs.ics.swib.generator.TestUtils;

/**
 *
 * @author kubedo8
 */
public class DatabazovyMultiplikatorDaoTest {

    private JdbcTemplate jdbcTemplate;
    private DatabazovyMultiplikatorDao databazovyMultiplikatorDao;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyMultiplikatorDao = DaoFactory.INSTANCE.databazovyMultiplikatorDao();
    }

    @Test
    public void dajVsetkyTest() {
        List<Multiplikator> vsetky = databazovyMultiplikatorDao.dajVsetky();
        assertEquals(TestUtils.pocetZDB("Multiplikator"), vsetky.size());
    }

    @Test
    public void dajIndexTest() {
        List<Multiplikator> vsetky = databazovyMultiplikatorDao.dajVsetky();
        if (vsetky.isEmpty()) {
            return;
        }
        Multiplikator multiplikator = vsetky.get(0);
        int id = databazovyMultiplikatorDao.dajIndex(multiplikator.getNazov());
        assertEquals(multiplikator.getId(), id);
    }

}
