package sk.upjs.ics.swib.tests;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 * @author kubedo8
 */
// anotacia zabezpeci, ze testy budu bezat v poradi podla abecedy
// POZOR treba mat junit-4.11 a vyssie
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabazovyUcetDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet uctov sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyUcetDao databazovyUcetDao;
    private Klient klient;
    private static final int POCET_UCTOV = 1;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyUcetDao = new DatabazovyUcetDao(jdbcTemplate);
        DatabazovyKlientDao dao = new DatabazovyKlientDao(jdbcTemplate);
        klient = dao.dajVsetkych().get(0);

    }

    @Test
    public void testAdajVsetky() {
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky(klient);
        assertEquals(POCET_UCTOV, zoznamUctov.size());
    }

    @Test
    public void testBpridaj() {
        List<Ucet> zoznamUctovPov = databazovyUcetDao.dajVsetky(klient);
        Ucet ucet = new Ucet();
        ucet.setCisloUctu("xxxxxxxxxx");
        ucet.setId(100000);
        ucet.setKlientId(klient.getId());
        ucet.setName(10101010);
        ucet.setSpor(false);
        ucet.setZostatok(new BigDecimal(BigInteger.ONE));

        databazovyUcetDao.pridaj(ucet);
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky(klient);
        assertEquals(zoznamUctovPov.size() + 1, zoznamUctov.size());
    }

    @Test
    public void testCuprav() {
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky(klient);
        if (zoznamUctov.isEmpty()) {
            return;
        }
        Ucet ucet = zoznamUctov.get(zoznamUctov.size() - 1);
        ucet.setCisloUctu("zzzzzzzzzz");
        ucet.setSpor(true);
        ucet.setZostatok(new BigDecimal("11.1100"));

        databazovyUcetDao.uprav(ucet);
        zoznamUctov = databazovyUcetDao.dajVsetky(klient);
        ucet = zoznamUctov.get(zoznamUctov.size() - 1);

        assertEquals("zzzzzzzzzz", ucet.getCisloUctu());
        assertTrue(ucet.isSpor());
        assertEquals(new BigDecimal("11.1100"), ucet.getZostatok());
    }

    @Test
    public void testDodstran() {
        List<Ucet> zoznamUctovPov = databazovyUcetDao.dajVsetky(klient);
        if (zoznamUctovPov.isEmpty()) {
            return;
        }
        Ucet ucet = zoznamUctovPov.get(zoznamUctovPov.size() - 1);
        databazovyUcetDao.odstran(ucet);
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky(klient);
        assertEquals(zoznamUctovPov.size() - 1, zoznamUctov.size());

    }

}
