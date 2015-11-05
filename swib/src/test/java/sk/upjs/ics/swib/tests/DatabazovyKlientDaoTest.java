package sk.upjs.ics.swib.tests;

import java.util.GregorianCalendar;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyKlientDao;
import sk.upjs.ics.swib.entity.Klient;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author kubedo8
 */
public class DatabazovyKlientDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet pre pocet klientov sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)
    
     Scenar:
     najprv spustit pridajTest
     potom upravTest
     a nakoniecc odstranTest
     */
    private JdbcTemplate jdbcTemplate;

    private DatabazovyKlientDao databazovyKlientDao;

    private static final int POCET_KLIENTOV = 136;

    public DatabazovyKlientDaoTest() {
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        databazovyKlientDao = new DatabazovyKlientDao(jdbcTemplate);
    }

    @Test
    public void dajVsetkychTest() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(POCET_KLIENTOV, zoznamKlientov.size());
    }

    @Test
    public void pridajTest() {
        Klient klient = new Klient();
        klient.setMeno("skuska");
        klient.setPriezvisko("skuska");
        klient.setCisloPreukazu("xxxxxxxxxx");
        klient.setDatumNarodenia(new GregorianCalendar());
        klient.setRodneCislo("0000000000");
        klient.setId(1000000);

        databazovyKlientDao.pridaj(klient);
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(POCET_KLIENTOV + 1, zoznamKlientov.size());
    }

    @Test
    public void upravTest() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();

        Klient klient = zoznamKlientov.get(zoznamKlientov.size() - 1);
        klient.setRodneCislo("1111111111");
        klient.setMeno("skuskaa");
        klient.setPriezvisko("skuskaa");
        klient.setCisloPreukazu("yyyyyyyyyy");
        GregorianCalendar cal = new GregorianCalendar(1993, 12, 24);
        klient.setDatumNarodenia(cal);

        databazovyKlientDao.uprav(klient);
        zoznamKlientov = databazovyKlientDao.dajVsetkych();
        klient = zoznamKlientov.get(zoznamKlientov.size() - 1);

        assertEquals("1111111111", klient.getRodneCislo());
        assertEquals("skuskaa", klient.getMeno());
        assertEquals("skuskaa", klient.getPriezvisko());
        assertEquals("yyyyyyyyyy", klient.getCisloPreukazu());
        assertEquals(cal, klient.getDatumNarodenia());
    }

    @Test
    public void odstranTest() {
        List<Klient> zoznamKlientov = databazovyKlientDao.dajVsetkych();
        Klient klient = zoznamKlientov.get(zoznamKlientov.size() - 1);

        databazovyKlientDao.odstran(klient);

        zoznamKlientov = databazovyKlientDao.dajVsetkych();
        assertEquals(POCET_KLIENTOV, zoznamKlientov.size());

    }
}
