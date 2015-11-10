package sk.upjs.ics.swib.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import sk.upjs.ics.swib.entity.Ucet;
import sk.upjs.ics.swib.mappers.UcetRowMapper;

public class DatabazovyUcetDao implements UcetDao {

    private JdbcTemplate jdbcTemplate;
    private UcetRowMapper mapovac;

    private static final String TABLE_NAME = "Ucet";

    public DatabazovyUcetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapovac = new UcetRowMapper();

    }

    @Override
    public List<Ucet> dajVsetky() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return jdbcTemplate.query(sql, mapovac);
    }

    @Override
    public void pridaj(Ucet ucet) {
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (? ,?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, ucet.getKlientId(), ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor() ? 1 : 0);
    }

    @Override
    public void odstran(Ucet ucet) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";
        jdbcTemplate.update(sql, ucet.getId());
    }

    @Override
    public void uprav(Ucet ucet) {
        String sql = "UPDATE " + TABLE_NAME + " SET KlientID = ?, Nazov = ?, Zostatok = ?, C_uctu = ?, Spor = ? WHERE ID = ?";
        jdbcTemplate.update(sql, ucet.getKlientId(), ucet.getName(), ucet.getZostatok(), ucet.getCisloUctu(), ucet.isSpor() ? 1 : 0, ucet.getId());
    }

}
