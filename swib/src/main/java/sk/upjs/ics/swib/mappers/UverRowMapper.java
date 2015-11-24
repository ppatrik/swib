package sk.upjs.ics.swib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.swib.entity.Uver;

/**
 *
 * @author kubedo8
 */
public class UverRowMapper implements RowMapper<Uver>{

    @Override
    public Uver mapRow(ResultSet rs, int i) throws SQLException {
        Uver uver = new Uver();
        uver.setId(rs.getInt("ID"));
        uver.setNazov(rs.getString("Nazov"));
        uver.setBonusNaManzelku(rs.getBigDecimal("Bonus_naManzelku"));
        uver.setBonusNaDieta(rs.getBigDecimal("Bonus_naDieta"));
        
        return uver;
    }
    
}
