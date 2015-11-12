package sk.upjs.ics.swib.tests;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyPohybDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author kubedo8
 */
public class DatabazovyPohybDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet pohybov sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyPohybDao databazovyPohybDao;
    private Ucet ucet;

    private static final int POCET_POHYBOV = 183;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        databazovyPohybDao = new DatabazovyPohybDao(jdbcTemplate);
        DatabazovyKlientDao dao1 = new DatabazovyKlientDao(jdbcTemplate);
        Klient klient = dao1.dajVsetkych().get(0);
        DatabazovyUcetDao dao2 = new DatabazovyUcetDao(jdbcTemplate);
        ucet = dao2.dajVsetky(klient).get(0);
    }

    @Test
    public void dajVsetkyTest() {
        List<Pohyb> zoznamPohybov = databazovyPohybDao.dajVsetky(ucet);
        assertEquals(POCET_POHYBOV, zoznamPohybov.size());
    }
}
