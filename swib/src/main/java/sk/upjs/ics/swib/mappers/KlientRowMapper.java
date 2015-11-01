package sk.upjs.ics.swib.mappers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.swib.entity.Klient;

/**
 *
 * @author kubedo8
 */
public class KlientRowMapper implements RowMapper<Klient> {
    
    @Override
    public Klient mapRow(ResultSet rs, int i) throws SQLException {
        Klient klient = new Klient();
        klient.setId(rs.getInt("ID"));
        klient.setCisloPreukazu(rs.getString("C_preukazu"));
        klient.setCisloKarty(rs.getInt("C_Karty"));
        klient.setMeno(rs.getString("Meno"));
        klient.setPriezvisko(rs.getString("Priezvisko"));
        klient.setRodneCislo(rs.getString("Rodne_cislo"));
        
        Calendar datNar = new GregorianCalendar();
        datNar.setTime(rs.getDate("Dat_nar"));
        
        klient.setDatumNarodenia(datNar);
        return klient;
    }
    
}
