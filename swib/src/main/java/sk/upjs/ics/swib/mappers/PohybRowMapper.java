package sk.upjs.ics.swib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.swib.entity.Pohyb;

/**
 *
 * @author kubedo8
 */
public class PohybRowMapper implements RowMapper<Pohyb>{

    @Override
    public Pohyb mapRow(ResultSet rs, int i) throws SQLException {
        Pohyb pohyb = new Pohyb();
        pohyb.setId(rs.getInt("ID"));
        pohyb.setKamIBAN(rs.getString("KamIBAN"));
        pohyb.setSpracovane(rs.getBoolean("Spracovane"));
        pohyb.setSuma(rs.getBigDecimal("Suma"));
        
        Calendar datum = new GregorianCalendar();
        datum.setTime(rs.getDate("Datum"));
        pohyb.setDatum(datum);
        
        return pohyb;
    }
    
    
}
