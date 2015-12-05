package sk.upjs.ics.swib.tests;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.KlientDao;
import sk.upjs.ics.swib.dao.PohybDao;
import sk.upjs.ics.swib.dao.UcetDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;
import sk.upjs.ics.swib.generator.TestUtils;

/**
 *
 * @author kubedo8
 */
public class DatabazovyPohybDaoTest {

    private JdbcTemplate jdbcTemplate;
    private PohybDao databazovyPohybDao;
    private Ucet ucet;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");

        KlientDao klientDao = DaoFactory.INSTANCE.klientDao();
        List<Klient> klienti = klientDao.dajVsetkych();
        if (!klienti.isEmpty()) {
            Klient klient = klienti.get(0);
            UcetDao ucetDao = DaoFactory.INSTANCE.ucetDao();
            List<Ucet> ucty = ucetDao.dajVsetky(klient);
            if (!ucty.isEmpty()) {
                this.ucet = ucty.get(0);
            }
        }
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyPohybDao = DaoFactory.INSTANCE.pohybDao();
    }

    @Test
    public void dajVsetkyTest() {
        if (ucet == null) {
            return;
        }
        List<Pohyb> zoznamPohybov = databazovyPohybDao.dajVsetky(ucet);
        assertEquals(TestUtils.pocetPohybov(ucet), zoznamPohybov.size());
    }
}
