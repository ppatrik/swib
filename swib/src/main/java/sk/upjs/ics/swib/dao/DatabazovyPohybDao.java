
package sk.upjs.ics.swib.dao;

import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Pohyb;


public class DatabazovyPohybDao implements PohybDao {

   private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Pohyb> mapovac
            = new BeanPropertyRowMapper<>(Pohyb.class);
    
    public DatabazovyPohybDao(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public List<Pohyb> dajVsetky() {
        String sql = "SELECT * FROM pohyby";
        return jdbcTemplate.query(sql, mapovac);

    }

    
}
