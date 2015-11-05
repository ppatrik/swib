package sk.upjs.ics.swib.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.swib.entity.Ucet;

/**
 *
 * @author kubedo8
 */
public class UcetRowMapper implements RowMapper<Ucet> {

    @Override
    public Ucet mapRow(ResultSet rs, int i) throws SQLException {
        Ucet ucet = new Ucet();
        ucet.setId(rs.getInt("ID"));
        ucet.setKlientId(rs.getInt("KlientID"));
        ucet.setCisloUctu(rs.getString("C_uctu"));
        ucet.setSpor(rs.getBoolean("Spor"));
        ucet.setZostatok(rs.getBigDecimal("Zostatok"));
        ucet.setName(rs.getInt("Nazov"));
        return ucet;
    }
    
}
