package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Porovnavac;

public class DatabazovyPorovnavacDao implements PorovnavacDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Porovnavac> mapovac
            = new BeanPropertyRowMapper<>(Porovnavac.class);

    private static final String TABLE_NAME = "CoPorovnavam";

    public DatabazovyPorovnavacDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Porovnavac> dajVsetky() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, mapovac);

    }

    @Override
    public int dajIndex(String nazov) {
        String sql = "SELECT ID FROM " + TABLE_NAME + " WHERE Nazov= ? ";
        Integer id = (Integer) jdbcTemplate.queryForObject(sql, new Object[]{nazov}, Integer.class);
        return id.intValue();
    }

    @Override
    public String dajNazov(int index) {
        String sql = "SELECT Nazov FROM " + TABLE_NAME + " WHERE ID= ? ";
        String nazov = jdbcTemplate.queryForObject(sql, new Object[]{index}, String.class);
        return nazov;
    }
}
