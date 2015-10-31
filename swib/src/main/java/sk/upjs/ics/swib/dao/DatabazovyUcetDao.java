
package sk.upjs.ics.swib.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
iimport sk.upjs.ics.swib.entity.Ucet;


public class DatabazovyUcetDao implements UcetDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Ucet> mapovac
            = new BeanPropertyRowMapper<>(Ucet.class);

    public DatabazovyUcetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public List<Ucet> dajVsetkych() {
        String sql = "SELECT * FROM Ucet";
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Ucet ucet) {
        String sql = "INSERT INTO Ucet VALUES (? ,?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, ucet.getKlientId(), ucet.getName(), ucet.getZostatok(),ucet.getCisloUctu());
    }

    @Override
    public void odstran(Ucet ucet) {
        String sql = "DELETE FROM Ucet WHERE ID = ?";
        jdbcTemplate.update(sql, ucet.getId());
    }

    @Override
    public void uprav(Ucet ucet) {
        String sql = "UPDATE Ucet SET KlientID = ?, Nazov = ?, Zostatok = ?, C_uctu = ?, Spor = ?,WHERE ID = ?";
        jdbcTemplate.update(sql,ucet.getKlientId(), ucet.getName(), ucet.getZostatok(),ucet.getCisloUctu(), ucet.getId() );
    }


}
