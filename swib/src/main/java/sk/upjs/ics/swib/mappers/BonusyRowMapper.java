package sk.upjs.ics.swib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.swib.entity.Bonus;

/**
 *
 * @author kubedo8
 */
public class BonusyRowMapper implements RowMapper<Bonus> {
    
    @Override
    public Bonus mapRow(ResultSet rs, int i) throws SQLException {
        Bonus bonus = new Bonus();
        bonus.setId(rs.getInt("ID"));
        bonus.setUverId(rs.getInt("UveryId"));
        bonus.setNazov(rs.getString("Nazov"));
        bonus.setJeVacsiAko(rs.getBigDecimal("Vacsi_ako"));
        bonus.setKolkoJeBonus(rs.getBigDecimal("Vyska_bonusu"));
        bonus.setOrderNumber(rs.getInt("Poradie_bonusu"));
        bonus.setMultiplikatorId(rs.getInt("MultiplikatorID"));
        bonus.setPorovnavacId(rs.getInt("CoPorovnavamID"));
        
        return bonus;
    }
    
}
