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
import sk.upjs.ics.swib.dao.DatabazovyBonusDao;
import sk.upjs.ics.swib.dao.DatabazovyUverDao;
import sk.upjs.ics.swib.entity.Bonus;
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
public class DatabazovyBonusDaoTest {

    private JdbcTemplate jdbcTemplate;
    private DatabazovyBonusDao databazovyBonusDao;
    private Uver uver;

    @Before
    public void setUp() {
        System.setProperty("testovaciRezim", "true");

        DatabazovyUverDao dao = DaoFactory.INSTANCE.databazovyUverDao();
        List<Uver> uvery = dao.dajVsetky();
        if (!uvery.isEmpty()) {
            uver = uvery.get(0);
        }
        this.jdbcTemplate = DaoFactory.INSTANCE.jdbcTemplate();
        this.databazovyBonusDao = DaoFactory.INSTANCE.databazovyBonusDao();
    }

    @Test
    public void testAdajVsetky() {
        if (uver == null) {
            return;
        }
        List<Bonus> zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
        assertEquals(TestUtils.pocetBonusov(uver), zoznamBonusov.size());
    }

    @Test
    public void testBpridaj() {
        if (uver == null) {
            return;
        }
        List<Bonus> zoznamBonusovPov = databazovyBonusDao.dajVsetky(uver);
        Bonus bonus = new Bonus();
        bonus.setJeVacsiAko(new BigDecimal(BigInteger.ONE));
        bonus.setKolkoJeBonus(new BigDecimal(BigInteger.ONE));
        bonus.setNazov("nazov");
        bonus.setOrderNumber(2);
        bonus.setUverId(uver.getId());
        bonus.setPorovnavacId(1);
        databazovyBonusDao.pridaj(bonus);
        List<Bonus> zoznamBonusov = databazovyBonusDao.dajVsetky(uver);

        assertEquals(zoznamBonusovPov.size() + 1, zoznamBonusov.size());
    }

    @Test
    public void testCuprav() {
        if (uver == null) {
            return;
        }
        List<Bonus> zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
        if (zoznamBonusov.isEmpty()) {
            return;
        }
        Bonus bonus = zoznamBonusov.get(zoznamBonusov.size() - 1);
        bonus.setJeVacsiAko(new BigDecimal("10.1234"));
        bonus.setKolkoJeBonus(new BigDecimal("10.1234"));
        bonus.setOrderNumber(3);

        databazovyBonusDao.uprav(bonus);
        zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
        bonus = zoznamBonusov.get(zoznamBonusov.size() - 1);

        assertEquals(new BigDecimal("10.1234"), bonus.getJeVacsiAko());
        assertEquals(new BigDecimal("10.1234"), bonus.getKolkoJeBonus());
        assertEquals(3, bonus.getOrderNumber());
    }

    @Test
    public void testDodstran() {
        if (uver == null) {
            return;
        }
        List<Bonus> zoznamBonusovPov = databazovyBonusDao.dajVsetky(uver);
        if (zoznamBonusovPov.isEmpty()) {
            return;
        }
        Bonus bonus = zoznamBonusovPov.get(zoznamBonusovPov.size() - 1);
        databazovyBonusDao.odstran(bonus);

        List<Bonus> zoznamBonusov = databazovyBonusDao.dajVsetky(uver);
        assertEquals(zoznamBonusovPov.size() - 1, zoznamBonusov.size());
    }

}
