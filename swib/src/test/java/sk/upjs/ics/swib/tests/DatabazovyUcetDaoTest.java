package sk.upjs.ics.swib.tests;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyUcetDao;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 * @author kubedo8
 */
public class DatabazovyUcetDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet uctov sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyUcetDao databazovyUcetDao;

    private static final int POCET_UCTOV = 136;

    public DatabazovyUcetDaoTest() {
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyUcetDao = new DatabazovyUcetDao(jdbcTemplate);
    }

    @Test
    public void dajVsetkyTest() {
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky();
        assertEquals(POCET_UCTOV, zoznamUctov.size());
    }

    @Test
    public void pridajTest() {
        Ucet ucet = new Ucet();
        ucet.setCisloUctu("xxxxxxxxxx");
        ucet.setId(100000);
        ucet.setKlientId(1);
        ucet.setName(10101010);
        ucet.setSpor(false);
        ucet.setZostatok(new BigDecimal(BigInteger.ONE));

        databazovyUcetDao.pridaj(ucet);
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky();
        assertEquals(POCET_UCTOV + 1, zoznamUctov.size());
    }

    @Test
    public void upravTest() {
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky();
        Ucet ucet = zoznamUctov.get(zoznamUctov.size() - 1);
        ucet.setCisloUctu("yyyyyyyyyy");
        ucet.setSpor(true);
        ucet.setZostatok(new BigDecimal("11.1100"));

        databazovyUcetDao.uprav(ucet);
        zoznamUctov = databazovyUcetDao.dajVsetky();
        ucet = zoznamUctov.get(zoznamUctov.size() - 1);

        assertEquals("yyyyyyyyyy", ucet.getCisloUctu());
        assertTrue(ucet.isSpor());
        assertEquals(new BigDecimal("11.1100"), ucet.getZostatok());
    }

    @Test
    public void odstranTest() {
        List<Ucet> zoznamUctov = databazovyUcetDao.dajVsetky();
        Ucet ucet = zoznamUctov.get(zoznamUctov.size() - 1);

        databazovyUcetDao.odstran(ucet);
        zoznamUctov = databazovyUcetDao.dajVsetky();
        assertEquals(POCET_UCTOV, zoznamUctov.size());

    }

}
