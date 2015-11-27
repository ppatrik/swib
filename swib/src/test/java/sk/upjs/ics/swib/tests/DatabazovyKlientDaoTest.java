package sk.upjs.ics.swib.tests;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.factory.DaoFactory;
import sk.upjs.ics.swib.generator.TestUtils;

/**
 *
 * @author kubedo8
 */
// anotacia zabezpeci, ze testy budu bezat v poradi podla abecedy
// POZOR treba mat junit-4.11 a vyssie
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabazovyKlientDaoTest {

    private JdbcTemplate jdbcTemplate;
    private DatabazovyKlientDao databazovyKlientDao;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyKlientDao = DaoFactory.INSTANCE.databazovyKlientDao();
    }

    @Test
    public void testAdajVsetkych() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(TestUtils.pocetZDB("Klient"), zoznamKlientov.size());
    }

    @Test
    public void testBpridaj() {
        List<Klient> zoznamKlientovPov = databazovyKlientDao.dajVsetkych();
        Klient klient = new Klient();
        klient.setMeno("skuska");
        klient.setPriezvisko("skuska");
        klient.setCisloPreukazu("xxxxxxxxxx");
        klient.setDatumNarodenia(new GregorianCalendar());
        klient.setRodneCislo("0000000000");
        klient.setId(1000000);

        databazovyKlientDao.pridaj(klient);
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(zoznamKlientovPov.size() + 1, zoznamKlientov.size());
    }

    @Test
    public void testCuprav() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        if (zoznamKlientov.isEmpty()) {
            return;
        }
        Klient klient = zoznamKlientov.get(zoznamKlientov.size() - 1);
        klient.setRodneCislo("2222222222");
        klient.setMeno("skuskaaa");
        klient.setPriezvisko("skuskaaa");
        klient.setCisloPreukazu("zzzzzzzzzz");
        GregorianCalendar cal = new GregorianCalendar(1993, 12, 24);
        klient.setDatumNarodenia(cal);

        databazovyKlientDao.uprav(klient);
        zoznamKlientov = databazovyKlientDao.dajVsetkych();
        klient = zoznamKlientov.get(zoznamKlientov.size() - 1);

        assertEquals("2222222222", klient.getRodneCislo());
        assertEquals("skuskaaa", klient.getMeno());
        assertEquals("skuskaaa", klient.getPriezvisko());
        assertEquals("zzzzzzzzzz", klient.getCisloPreukazu());
        assertEquals(cal, klient.getDatumNarodenia());
    }

    @Test
    public void testDodstran() {
        List<Klient> zoznamKlientovPov = databazovyKlientDao.dajVsetkych();
        if (zoznamKlientovPov.isEmpty()) {
            return;
        }
        Klient klient = zoznamKlientovPov.get(zoznamKlientovPov.size() - 1);

        databazovyKlientDao.odstran(klient);

        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(zoznamKlientovPov.size() - 1, zoznamKlientov.size());
    }

    @Test
    public void testEMozeNaMesiacMaximalneSplacat() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        if (zoznamKlientov.isEmpty()) {
            return;
        }
        Klient klient = zoznamKlientov.get(0);
        BigDecimal maxSplacat = databazovyKlientDao.mozeNaMesiacMaximalneSplacat(klient).setScale(4, RoundingMode.DOWN);
        assertEquals(TestUtils.maxSplatky(klient), maxSplacat);
    }
}
