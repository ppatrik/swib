package sk.upjs.ics.swib.tests;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyUverDao;
import sk.upjs.ics.swib.entity.Uver;
import sk.upjs.ics.swib.factory.DaoFactory;
import sk.upjs.ics.swib.generator.TestUtils;

/**
 *
 * @author kubedo8
 */
// anotacia zabezpeci, ze testy budu bezat v poradi podla abecedy
// POZOR treba mat junit-4.11 a vyssie
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabazovyUverDaoTest {

    private JdbcTemplate jdbcTemplate;
    private DatabazovyUverDao databazovyUverDao;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyUverDao = DaoFactory.INSTANCE.databazovyUverDao();
    }

    @Test
    public void testAdajVsetky() {
        List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();
        assertEquals(TestUtils.pocetZDB("Uvery"), zoznamUverov.size());
    }

    @Test
    public void testBpridaj() {
        List<Uver> zoznamUverovPov = databazovyUverDao.dajVsetky();
        Uver uver = new Uver();
        uver.setId((int) (1000000 + Math.random() * 1000000));
        uver.setNazov("skuska2");
        uver.setBonusNaManzelku(BigDecimal.ONE);
        uver.setBonusNaDieta(BigDecimal.TEN);
        databazovyUverDao.pridaj(uver);
        List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();

        assertEquals(zoznamUverovPov.size() + 1, zoznamUverov.size());
    }

    @Test
    public void testCUprav() {
        List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();
        if (zoznamUverov.isEmpty()) {
            return;
        }
        Uver uver = zoznamUverov.get(zoznamUverov.size() - 1);
        uver.setNazov("skuska3");
        uver.setBonusNaManzelku(new BigDecimal("111.1111"));
        uver.setBonusNaDieta(new BigDecimal("123.1234"));
        databazovyUverDao.uprav(uver);
        zoznamUverov = databazovyUverDao.dajVsetky();

        Uver uverZDB = zoznamUverov.get(zoznamUverov.size() - 1);
        assertEquals("skuska3", uverZDB.getNazov());
        assertEquals(new BigDecimal("111.1111"), uverZDB.getBonusNaManzelku());
        assertEquals(new BigDecimal("123.1234"), uverZDB.getBonusNaDieta());
    }

    @Test
    public void testDVymaz() {
        List<Uver> zoznamUverovPov = databazovyUverDao.dajVsetky();
        if (zoznamUverovPov.isEmpty()) {
            return;
        }
        Uver uver = zoznamUverovPov.get(zoznamUverovPov.size() - 1);
        databazovyUverDao.odstran(uver);
        List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();

        assertEquals(zoznamUverovPov.size() - 1, zoznamUverov.size());
    }

}
