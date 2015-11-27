package sk.upjs.ics.swib.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyPorovnavacDao;
import sk.upjs.ics.swib.entity.Porovnavac;
import sk.upjs.ics.swib.factory.DaoFactory;
import sk.upjs.ics.swib.generator.TestUtils;

/**
 *
 * @author kubedo8
 */
public class DatabazovyPorovnavacDaoTest {

    private JdbcTemplate jdbcTemplate;
    private DatabazovyPorovnavacDao databazovyPorovnavacDao;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyPorovnavacDao = DaoFactory.INSTANCE.databazovyPorovnavacDao();
    }

    @Test
    public void dajVsetkyTest() {
        List<Porovnavac> vsetky = databazovyPorovnavacDao.dajVsetky();
        assertEquals(TestUtils.pocetZDB("CoPorovnavam"), vsetky.size());
    }

    @Test
    public void dajIndexTest() {
        List<Porovnavac> vsetky = databazovyPorovnavacDao.dajVsetky();
        if (vsetky.isEmpty()) {
            return;
        }
        Porovnavac porovnavac = vsetky.get(0);

        int id = databazovyPorovnavacDao.dajIndex(porovnavac.getNazov());
        assertEquals(porovnavac.getId(), id);
    }

}
