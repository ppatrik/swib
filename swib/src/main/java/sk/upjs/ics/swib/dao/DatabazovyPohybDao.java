package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Pohyb;
import sk.upjs.ics.swib.mappers.PohybRowMapper;

public class DatabazovyPohybDao implements PohybDao {

    private JdbcTemplate jdbcTemplate;
    private PohybRowMapper mapovac;
    private static final String TABLE_NAME = "Pohyby";

    public DatabazovyPohybDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapovac = new PohybRowMapper();
    }

    @Override
    public List<Pohyb> dajVsetky() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, mapovac);
    }

}
