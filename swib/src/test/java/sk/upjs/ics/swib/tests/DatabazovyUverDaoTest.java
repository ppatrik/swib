package sk.upjs.ics.swib.tests;

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

/**
 *
 * @author kubedo8
 */
// anotacia zabezpeci, ze testy budu bezat v poradi podla abecedy
// POZOR treba mat junit-4.11 a vyssie
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabazovyUverDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet uverov sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyUverDao databazovyUverDao;

    private static final int POCET_UVEROV = 1;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyUverDao = new DatabazovyUverDao(jdbcTemplate);
    }

    @Test
    public void testAdajVsetky() {
        List<Uver> zoznamUverov = databazovyUverDao.dajVsetky();
        assertEquals(POCET_UVEROV, zoznamUverov.size());
    }

    @Test
    public void testBpridaj() {
        List<Uver> zoznamUverovPov = databazovyUverDao.dajVsetky();
        Uver uver = new Uver();
        uver.setNazov("skuska2");
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
        databazovyUverDao.uprav(uver);
        zoznamUverov = databazovyUverDao.dajVsetky();

        assertEquals("skuska3", zoznamUverov.get(zoznamUverov.size() - 1).getNazov());
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
