package sk.upjs.ics.swib.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyMultiplikatorDao;
import sk.upjs.ics.swib.entity.Multiplikator;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author kubedo8
 */
public class DatabazovyMultiplikatorDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyMultiplikatorDao databazovyMultiplikatorDao;

    private static final int POCET = 1;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyMultiplikatorDao = new DatabazovyMultiplikatorDao(jdbcTemplate);
    }

    @Test
    public void dajVsetkyTest() {
        List<Multiplikator> vsetky = databazovyMultiplikatorDao.dajVsetky();
        assertEquals(POCET, vsetky.size());
    }

    @Test
    public void dajIndexTest() {
        int id = databazovyMultiplikatorDao.dajIndex("skuska");
        assertEquals(id, 1);
    }

}
