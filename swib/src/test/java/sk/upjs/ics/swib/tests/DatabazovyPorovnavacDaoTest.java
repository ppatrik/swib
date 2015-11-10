package sk.upjs.ics.swib.tests;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyPorovnavacDao;
import sk.upjs.ics.swib.entity.Porovnavac;
import sk.upjs.ics.swib.factory.DaoFactory;

/**
 *
 * @author kubedo8
 */
public class DatabazovyPorovnavacDaoTest {

    /*
    
     UPOZORNENIE!!!
    
     Pocet sa moze zmenit, teda treba pred testovanim overit,
     kolko ich v dazabaze naozaj je (napr cez squirell)

     */
    private JdbcTemplate jdbcTemplate;
    private DatabazovyPorovnavacDao databazovyPorovnavacDao;

    private static final int POCET = 1;

    public DatabazovyPorovnavacDaoTest() {
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyPorovnavacDao = new DatabazovyPorovnavacDao(jdbcTemplate);
    }

    @Test
    public void dajVsetkyTest() {
        List<Porovnavac> vsetky = databazovyPorovnavacDao.dajVsetky();
        assertEquals(POCET, vsetky.size());
    }

    @Test
    public void dajIndexTest() {
        int id = databazovyPorovnavacDao.dajIndex("skuska");
        assertEquals(id, 1);
    }

}
