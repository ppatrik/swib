package sk.upjs.ics.swib.dao;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Klient;


public class DatabazovyKlientDao implements KlientDao {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<Klient> mapovac
            = new BeanPropertyRowMapper<>(Klient.class);

    public DatabazovyKlientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public List<Klient> dajVsetkych() {
        String sql = "SELECT * FROM Klient";
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Klient klient) {
        String sql = "INSERT INTO Klient VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, klient.getMeno(), klient.getPriezvisko(), klient.getCisloKarty(), klient.getDatumNarodenia(), klient.getRodneCislo(), klient.getCisloPreukazu());
    }

    @Override
    public void odstran(Klient klient) {
        String sql = "DELETE FROM Klient WHERE ID = ?";
        jdbcTemplate.update(sql, klient.getId());
    }

    @Override
    public void uprav(Klient klient) {
        String sql = "UPDATE Klient SET Meno = ?, Priezvisko = ?, C_Karty = ?, Dat_nar = ?, Rodne_cislo = ?, C_preukazu = ? WHERE ID = ?";
        jdbcTemplate.update(sql, klient.getMeno(), klient.getPriezvisko(), klient.getCisloKarty(), klient.getDatumNarodenia(), klient.getRodneCislo(), klient.getCisloPreukazu());
    }

    @Override
    public BigDecimal mozeNaMesiacMaximalneSplacat(Klient klient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
