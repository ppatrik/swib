
package sk.upjs.ics.swib.dao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Multiplikator;


public class DatabazovyMultiplikatorDao implements MultiplikatorDao {

   private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Multiplikator> mapovac
            = new BeanPropertyRowMapper<>(Multiplikator.class);
    
    public DatabazovyMultiplikatorDao(JdbcTemplate jdbcTemplate) {
       this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public List<Multiplikator> dajVsetky() {
        String sql = "SELECT * FROM multiplikator";
        return jdbcTemplate.query(sql, mapovac);

    }

    @Override
    public int dajIndex(String nazov){
    String sql = "SELECT id FROM multiplikator WHERE Nazov= ? ";
    return jdbcTemplate.query(sql,nazov,mapovac);
    
    }
    
}
