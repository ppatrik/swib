package sk.upjs.ics.swib.tests;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.dao.DatabazovyPohybDao;
import sk.upjs.ics.swib.entity.Pohyb;
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

    private static final int POCET_POHYBOV = 12512;

    public DatabazovyPohybDaoTest() {
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        databazovyPohybDao = new DatabazovyPohybDao(jdbcTemplate);
    }

    @Test
    public void dajVsetkyTest() {
        List<Pohyb> zoznamPohybov = databazovyPohybDao.dajVsetky();
        assertEquals(POCET_POHYBOV, zoznamPohybov.size());
    }
}
