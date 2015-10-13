package sk.upjs.ics.swib.tests;

import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.factory.DaoFactory;

public class TemplateTest {

    private static JdbcTemplate jdbcTemplate;

    @BeforeClass
    public static void setUp() {
        System.setProperty("testovaciRezim", "true");
        jdbcTemplate = new JdbcTemplate(DaoFactory.INSTANCE.dataSource());
    }

    @Test
    public void komunikaciaSDatabazouTest() {
        String sql = "SELECT 1 = 1";
        String odpoved = (String) jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
        System.out.println("Odpoved je: " + odpoved);
        Assert.assertNotNull(jdbcTemplate);
    }
}
